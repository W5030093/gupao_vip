package com.gupao.vip.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuraorSessionDemo {

    private static final String CONNECTSTRING = "192.168.154.130:2181,192.168.154.130:2182,192.168.154.130:2183";
    public static void main(String[] args) {

        CuratorFramework curatorFramework = CuratorFrameworkFactory.
                newClient(CONNECTSTRING,5000,5000,new ExponentialBackoffRetry(1000,3));
        curatorFramework.start();

        //normal fluent style
        CuratorFramework curatorFramework1 = CuratorFrameworkFactory.builder().connectString(CONNECTSTRING).sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000,3)).namespace("/curator").build();
        curatorFramework1.start();
        curatorFramework1.create();
    }

}
