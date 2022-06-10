package io.github.xingkongqwq.xweb.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 所有的主类必须继承此类并实现相关方法
 */
public abstract class XWebApplication {
    /**
     * 获取logger（无缓存，注意性能！）
     * @param clz class对象
     * @return logger对象
     */
    public Logger getLogger(Class<?> clz) {
        return LogManager.getLogger(clz);
    }

    public abstract void run(String[] args, long time);

    public void firstRun(String[] args, long beginTime) {
        long startTime = System.currentTimeMillis() - beginTime;
        this.run(args, startTime);
    }
}
