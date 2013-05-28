/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pr.corumbatai.pm.nfse.utils.log;

/**
 *
 * @author tchulla
 */
public interface MlogListener {

    void newLog(Log.LogType type, Class clazz, String msg);
}
