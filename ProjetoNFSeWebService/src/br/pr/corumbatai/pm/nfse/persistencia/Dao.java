package br.pr.corumbatai.pm.nfse.persistencia;

import java.util.List;
import org.hibernate.Criteria;

public interface Dao<T> {

    void close();

    void delete(T e);

    List<T> get();

    T get(Object id);

    Class getClassForEntity();

    DaoGenerico<T> getDao();

    List<T> list(Object value, String... fields);

    List<T> list(Criteria criteria);

    void persist(T e);
    
    
    
}
