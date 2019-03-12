package com.gupao.vip.javaapi;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class CreateSessionDao {

    private static final String CONNECTSTRING = "192.168.154.130:2181,192.168.154.130:2182,192.168.154.130:2183";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) {


        try {
            ZooKeeper zooKeeper = new ZooKeeper(CONNECTSTRING, 5000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if(watchedEvent.getState()==Event.KeeperState.SyncConnected){
                        countDownLatch.countDown();
                        System.out.println(watchedEvent.getState());
                    }
                }
            });
            countDownLatch.await();
            System.out.println(zooKeeper.getState());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

