/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pr.corumbatai.pm.nfse.utils.log;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.log4j.Logger;

/**
 *
 * @author tchulla
 */
public class MLog implements Log {

    private Class clazz;
    private Collection<MlogListener> listeners;

    private MLog(Class clazz) {
        this.clazz = clazz;
        listeners = new ArrayList<MlogListener>();
    }


    public static MLog getInstance(Object alvo) {
        return new MLog(alvo.getClass());
    }

    private void log(LogType type, String text, Object... args) {
        String msg = MessageFormat.format(text, args);
        switch (type) {
            case DEBUG:
                Logger.getLogger(clazz).debug(msg);
                break;
            case ERRO:
                Logger.getLogger(clazz).error(msg);
                break;
            case FATAL:
                Logger.getLogger(clazz).fatal(msg);
                break;
            case INFO_TEST:
                Logger.getLogger(clazz).info(msg);
                break;
            case INFO:
                Logger.getLogger(clazz).info(msg);
                break;
            case WARNING:
                Logger.getLogger(clazz).warn(msg);
                break;
        }
    }

    @Override
    public synchronized void addMlogListener(MlogListener ml) {
        if (!listeners.contains(ml)) {
            listeners.add(ml);
        }
    }

    @Override
    public synchronized void removeMlogListener(MlogListener ml) {
        listeners.remove(ml);
    }

    @Override
    public void err(String msg, Object... args) {
        log(LogType.ERRO, msg, args);
    }

    @Override
    public void fatal(String msg, Object... args) {
        log(LogType.FATAL, msg, args);
    }

    @Override
    public void info(String msg, Object... args) {
        log(LogType.INFO, msg, args);
    }

    @Override
    public void debug(String msg, Object... args) {
        log(LogType.DEBUG, msg, args);
    }

    @Override
    public void test(String msg, Object... args) {
        log(LogType.INFO_TEST, msg, args);
    }

    @Override
    public void warning(String msg, Object... args) {
        log(LogType.WARNING, msg, args);
    }

    @Override
    public void err(Throwable e) {
        StringBuilder sb = new StringBuilder();
        
        Class clazz = e.getClass();
        String sp = "";
        sb.append(clazz.getSimpleName());
        for (StackTraceElement s : e.getStackTrace()) {
            if(s.getClass() == clazz){
                sb.append("linha: ");
                sb.append(s.getLineNumber());
                sb.append(" - ");
                sb.append(s.getMethodName());
                sb.append("\n");
            }else{
                sp.concat(" ");
                clazz = s.getClass();
                sb.append(sp);
                sb.append(clazz.getName());
                sb.append("linha: ");
                sb.append(s.getLineNumber());
                sb.append(" - ");
                sb.append(s.getMethodName());
                sb.append("\n");
            }
        }
        
        log(LogType.DEBUG, sb.toString());
    }
}
