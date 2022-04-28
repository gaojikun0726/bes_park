package com.zc.netty.polelight.server;

import com.zc.netty.polelight.timer.PduThread;
import com.zc.netty.polelight.util.ThreadPool;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * describe: NettyServer启动类
 *
 * @author zs
 * @date 2021/3/5
 */
@Component
public class NettyServerApplicationRunner implements ApplicationRunner {


    @Resource
  private PduThread pduThread;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ThreadPool.executor.execute(() -> {
            NettyServer.getInstance().run();
        });

        pduThread.pushThread();

    }
}
