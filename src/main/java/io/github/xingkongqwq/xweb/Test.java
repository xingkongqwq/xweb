package io.github.xingkongqwq.xweb;

import io.github.xingkongqwq.xweb.core.XWebApplication;
import io.github.xingkongqwq.xweb.core.server.HttpServer;

public class Test extends XWebApplication {
    @Override
    public void run(String[] args, long time) {
        HttpServer
                .getServer()
                .run();
    }
}
