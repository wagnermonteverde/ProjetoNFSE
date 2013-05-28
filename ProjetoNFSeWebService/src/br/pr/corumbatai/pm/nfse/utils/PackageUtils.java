/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pr.corumbatai.pm.nfse.utils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo
 */
public class PackageUtils {

    private List<String> packages;
    private String path;
    private static final Logger log = Logger.getLogger(MD5.class.getName());

    public PackageUtils() {
        packages = new ArrayList<>();
    }

    public List<String> getPackagesInResource(String resource) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            Enumeration<URL> resources = classLoader.getResources("");
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                File file = new File(url.getFile());

                path = file.getPath();

                for (File f : file.listFiles()) {
                    if (f.isDirectory()) {
                        getPackageInFile(f);
                    }
                }
            }

        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return packages;
    }

    private void getPackageInFile(File file) {
        for (File f : file.listFiles()) {
            if (!f.isDirectory()) {
                continue;
            }
            if (containsSubDirs(f)) {

                packages.add(f.toString().replace(path, "").replace("/", ".").substring(1));
                getPackageInFile(f);
            } else {
                continue;
            }
        }

    }

    private boolean containsSubDirs(File file) {
        boolean ret = false;
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                return true;
            }
        }
        return ret;
    }
}
