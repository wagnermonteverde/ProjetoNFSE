/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pr.corumbatai.pm.nfse.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.trugger.scan.ClassScan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 *
 * @author tchulla
 */
public class HibernateUtil {

    private final AnnotationConfiguration conf;
    private Map<Class, Session> sessoes;
    private Session sessao;
    private static final Logger log = Logger.getLogger(HibernateUtil.class.getName());

    private HibernateUtil() {
        sessoes = new HashMap<>();
        conf = new AnnotationConfiguration();

        String url = conf.getProperty("hibernate.connection.url");
        String db = conf.getProperty("hibernate.connection.database");
        conf.setProperty("hibernate.connection.url", url.replace(url, url + "/" + db));

        for (Class class1 : ClassScan.findAll().recursively().in("br.pr.corumbatai.pm.nfse.persistencia.entidades")) {
            conf.addAnnotatedClass(class1);
        }
    }

    private void create(AnnotationConfiguration cfg) {
        new SchemaExport(cfg).create(true, true);
    }

    public Session getSession(Class type) {
        if (sessoes.containsKey(type)) {
            return sessoes.get(type);
        } else {
            SessionFactory sf = conf.buildSessionFactory();
            Session session = sf.openSession();
            sessoes.put(type, session);
            return session;
        }
    }

    public Session getNewSession() {
        SessionFactory sf = conf.buildSessionFactory();
        return sf.openSession();
    }

    public Session getSession() {
        SessionFactory sf = conf.buildSessionFactory();


        if (sessao == null) {
            sessao = sf.openSession();
            return sessao;
        } else {
            return sessao;
        }
    }

    public void gerarBancoDados() {
        try {
            String url = conf.getProperty("hibernate.connection.url");
            String user = conf.getProperty("hibernate.connection.username");
            String pass = conf.getProperty("hibernate.connection.password");
            String db = conf.getProperty("hibernate.connection.database");

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement s = conn.createStatement();
            s.executeUpdate(MessageFormat.format("DROP DATABASE IF EXISTS {0}", db));
            s.executeUpdate(MessageFormat.format("CREATE DATABASE {0}", db));

        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
        }

        SchemaExport se = new SchemaExport(conf);
        se.setOutputFile("mls.sql");

        se.drop(true, true);
        se.create(true, true);
    }

    @SuppressWarnings("empty-statement")
    public void apagarBancoDados() {
        SchemaExport se = new SchemaExport(conf);;
        se.setOutputFile("mls.sql");
        se.drop(false, false);
        se.create(true, true);
    }

    public static HibernateUtil getInstance() {
        return HibernateUtilHolder.INSTANCE;
    }

    private static class HibernateUtilHolder {

        private static final HibernateUtil INSTANCE = new HibernateUtil();
    }

    public static void main(String[] args) {
        new HibernateUtil().gerarBancoDados();
    }
}
