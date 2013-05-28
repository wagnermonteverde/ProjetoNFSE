/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pr.corumbatai.pm.nfse.persistencia;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import resource.B;

/**
 *
 * @author tchulla
 */
public abstract class DaoGenerico<T> {

    private static final Logger log = Logger.getLogger(DaoGenerico.class.getName());

    public T get(long l) {
        Session session = getSession();
        Criteria c = session.createCriteria(getClassForEntity());
        c.add(Expression.eq("id", l));
        return (T) c.uniqueResult();
    }

    public List<T> getAll(int max) {
        Criteria c = getCriteria();
        c.setMaxResults(max);
        return c.list();
    }
    public List<T> getAll() {
        Session session = getSession();
        Criteria c = getCriteria();
        return c.list();
    }

    public void deleteAll() throws RuntimeException{
        try {
            Session session = getSession();
            Criteria c = session.createCriteria(getClassForEntity());
            session.beginTransaction().begin();

            for (Object o : c.list()) {
                session.delete(o);
            }

            session.flush();
            session.beginTransaction().commit();

        } catch (Exception e) {
        }
    }

    public void delete(T t)throws RuntimeException {
        try {
            Session session = getSession();
            session.beginTransaction().begin();
            session.delete(t);
            session.flush();
            session.beginTransaction().commit();

        } catch (Exception e) {
        }
    }

    public void persist(List<T> list)throws RuntimeException {
        Session session = getSession();
        session.setCacheMode(CacheMode.PUT);
        session.setFlushMode(FlushMode.COMMIT);
        session.beginTransaction().begin();
        for (T t : list) {
            session.saveOrUpdate(t);
        }
        session.beginTransaction().commit();
    }

    
    public void persist(T t)throws RuntimeException {

        try {
            Method m = getMethod(t.getClass(), "getId");
            Object id = m.invoke(t);

            Session session = getSession();

            session.beginTransaction().begin();
            session.saveOrUpdate(t);
            session.flush();
            session.beginTransaction().commit();

        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | HibernateException e) {
            
        }
        
    }
    
    

    public Session getSession(Class type) {
        return HibernateUtil.getInstance().getSession(type);
    }

    public Session getSession() {
        return getSession(getClassForEntity());
    }

    public Class getClassForEntity() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void cloneEntity(T origem, T destino) {
        try {
            Class origemClass = origem.getClass();

            for (Method mGet : origemClass.getDeclaredMethods()) {
                processMethod(origem, destino, mGet);
            }
            for (Method mGet : origemClass.getMethods()) {
                processMethod(origem, destino, mGet);
            }

        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    private void processMethod(T origem, T destino, Method mGet) {
        if ("getClass".equals(mGet.getName())) {
            return;
        }

        if ("getId".equals(mGet.getName())) {
            return;
        }

        try {
            String mSGet = mGet.getName();
            String mSSet = "";
            if (mSGet.matches("(get|is)([A-Z])(\\w+)(\\S)")) {
                Object value = mGet.invoke(origem);

                if (mSGet.matches("(get)\\w+")) {
                    mSSet = "set" + mSGet.substring(3);

                    Method set = getMethod(destino.getClass(), mSSet, mGet.getReturnType());

                    if (set == null) {
                        return;
                    }
                    set.invoke(destino, value);
                }
            }

        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    private Method getMethod(Class type, String nome, Class... args) {
        try {
            Method m;
            try {
                m = type.getDeclaredMethod(nome, args);
            } catch (NoSuchMethodException e) {
                m = type.getMethod(nome, args);
            }

            return m;
        } catch (SecurityException | NoSuchMethodException e) {
            return null;
        }
    }

    public Criteria getCriteria() {
        return getCriteria(getClassForEntity());
    }

    public Criteria getCriteria(Class type) {
        return getSession().createCriteria(type);
    }
    
    public List<T> list(Criteria c){
        return c.list();
    }

    public boolean contains(Long id) {
        return get(id)!=null;
    }
    
    protected void validar(boolean check, String msgErr, Object ... args) throws RuntimeException{
        if(check){
           throw new RuntimeException(B.getString(msgErr, args));
        }
    } 
}
