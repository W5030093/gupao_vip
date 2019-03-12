package com.gupao.vip.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorClientUtils {
    private static CuratorFramework curatorFramework ;
     private static int sessionTimeout=5000;
    private static final String CONNECTSTRING = "192.168.216.128:2180,192.168.216.128:2181,192.168.216.128:2182";

    public static CuratorFramework getInstance(){
        CuratorFramework curatorFramework = CuratorFrameworkFactory.
                newClient(CONNECTSTRING,5000,5000,new ExponentialBackoffRetry(1000,3));
        curatorFramework.start();
        return curatorFramework;
    }
}
