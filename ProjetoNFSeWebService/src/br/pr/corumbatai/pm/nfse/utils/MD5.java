/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pr.corumbatai.pm.nfse.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo
 */
public class MD5 {
    private static final Logger log = Logger.getLogger(MD5.class.getName());

    @SuppressWarnings("UnusedAssignment")
    public static String digest(String value) {
        String sen = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        BigInteger hash = new BigInteger(1, md.digest(value.getBytes()));
        sen = hash.toString(16);
        return sen;

    }
}
