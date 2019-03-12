package com.gupao.vip.javaApiLock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

public class LockWatch implements Watcher {

    private CountDownLatch countDownLatch;

    public LockWatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {

         if(watchedEvent.getType()==Event.EventType.NodeDeleted){
            countDownLatch.countDown();
         }
    }
}
