package io.github.xingkongqwq.xweb;

import io.github.xingkongqwq.xweb.core.Launcher;
import io.github.xingkongqwq.xweb.core.exceptions.IllegalMainClassException;

import java.lang.reflect.InvocationTargetException;

public class XWebLauncher {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalMainClassException {
        Launcher launcher = Launcher.newInstance("io.github.xingkongqwq.xweb.Test", args, System.currentTimeMillis());
        launcher.run();
    }
}
