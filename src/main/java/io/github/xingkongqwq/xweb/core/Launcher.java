package io.github.xingkongqwq.xweb.core;

import io.github.xingkongqwq.xweb.core.exceptions.IllegalMainClassException;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class Launcher {
    private static Launcher launcher = null;
    private final String mainClass;
    private String[] args;
    private static long beginTime;

    private Launcher(String mainClass, String[] args) {
        this.mainClass = mainClass;
        this.args = args;
    }

    /**
     * 运行应用程序
     *
     * @throws ClassNotFoundException    找不到主类
     * @throws InstantiationException    主类没有无参构造方法或构造方法为私有或主类为抽象类、接口、数组类、基元类型
     * @throws NoSuchMethodException     主类无参构造方法调用失败
     * @throws InvocationTargetException 主类构造函数引发的异常
     * @throws IllegalAccessException    无权访问构造方法
     * @throws IllegalMainClassException 非法主类
     */
    public void run() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IllegalMainClassException {
        String mainClass = this.mainClass;
        Class<?> clz = Class.forName(mainClass);
        Object obj = clz.getConstructor().newInstance();
        if (!(obj instanceof XWebApplication)) {
            throw new IllegalMainClassException("主类应当继承XWebApplication类");
        }
        XWebApplication app = (XWebApplication) obj;
        app.firstRun(this.args, beginTime);
    }

    /**
     * 获取一个Launcher实例对象
     *
     * @param mainClass 应用的主类
     * @return 实例对象
     */
    public static Launcher newInstance(String mainClass, String[] args, long beginTimes) {
        if (Objects.nonNull(launcher)) return launcher;
        beginTime = beginTimes;
        launcher = new Launcher(mainClass, args);
        return launcher;
    }
}
