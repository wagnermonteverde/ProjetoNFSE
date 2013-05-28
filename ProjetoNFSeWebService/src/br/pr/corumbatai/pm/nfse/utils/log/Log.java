/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pr.corumbatai.pm.nfse.utils.log;

/**
 *
 * @author tchulla
 */
public interface Log {
    public enum LogType{
        ERRO,INFO,FATAL,DEBUG, INFO_TEST, WARNING
    }
    
    public void addMlogListener(MlogListener ml);
    public void removeMlogListener(MlogListener ml);
    
    void err(Throwable e); 
    void err(String msg, Object ... args); 
    void fatal(String msg, Object ... args); 
    void info(String msg, Object ... args); 
    void debug(String msg, Object ... args); 
    void test(String msg, Object ... args); 
    void warning(String msg, Object ... args); 
}
