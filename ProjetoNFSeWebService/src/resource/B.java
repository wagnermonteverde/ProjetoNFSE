/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 *
 * @author tchulla
 */
public class B {

    private ResourceBundle bundle;
    private File file;
    private FileInputStream fis;
    private final String FILE_PROPERTIES = "file.properties";

    public B() {
        bundle = ResourceBundle.getBundle("resource/Bundle");
    }

    public static boolean isNotInit(String... keys) {

        return true;
    }

    public static String getPreferencia(String key) {
        return new B().getPropertiesInFile(key, key).toString();
    }

    public static Object getPreferencia(String key, String sdefault) {
        return new B().getPropertiesInFile(key, sdefault);
    }

    public static Object getPreferencia(String key, Object sdefault) {
        return new B().getPropertiesInFile(key, sdefault);
    }

    public static Object getPreferenciaNaoSalvar(String key, String sdef) {
        return new B().getPreferenciaSemSalvar(key, sdef);
    }

    public void put(String key, Object value) {
        new B().save(key, value);
    }

    private void save(String key, Object value) {
        try {

            Properties props = new Properties();
            file = new File(FILE_PROPERTIES);

            fis = new FileInputStream(file);

            props.load(fis);
            fis.close();

            props.put(key, value);
            store(props);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object getPreferenciaSemSalvar(String key, String sdefault) {
        try {
            Properties props = new Properties();
            file = new File(FILE_PROPERTIES);
            fis = new FileInputStream(file);

            props.load(fis);
            fis.close();

            if (props.getProperty(key) == null) {
                return key;
            } else {
                return props.getProperty(key);
            }

        } catch (Exception e) {
            return key;
        }
    }

    private Object getPropertiesInFile(String key, Object sdefault) {
        synchronized (this) {

            try {
                Properties props = new Properties();
                file = new File(FILE_PROPERTIES);
                fis = new FileInputStream(file);

                props.load(fis);
                fis.close();

                //log.debug("CARREGANDO_PROPRIEDADE_ARQUIVO");

                if (props.getProperty(key) == null) {
                    props.put(key, sdefault);
                    store(props);
                }

                return props.getProperty(key);
            } catch (FileNotFoundException e) {
                store(new Properties());
                getPropertiesInFile(key, sdefault);
                return null;
            } catch (Exception e) {
                return key;
            }
        }

    }

    public void store(Properties p) {
        try {
            file = new File(FILE_PROPERTIES);
            p.store(new FileOutputStream(file), "");
        } catch (Exception e) {
            
        }
    }

    public static String getString(String key, Object... args) {
        return new B().get(key, args);
    }

    private String get(String key, Object... args) {
        try {
            return MessageFormat.format(bundle.getString(key), args);
        } catch (Exception e) {
            return key;
        }
    }
}
