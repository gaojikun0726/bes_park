package com.yc.netty;

import com.yc.netty.nettyServer.core.NettyServer;
import com.yc.netty.nettyServer.handle.ThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class nettyApplicationRunner implements ApplicationRunner {

    @Autowired
    private NettyServer NettyServer;

    @Value("${neetServer.port}")
    private int port ;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ThreadPool.executor.execute(() -> {
            try {
                new NettyServer(port).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
