package com.gupao.vip.zkClient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ZkClientApiOperatorDemo {

    private static final String CONNECTSTRING = "192.168.216.128:2180,192.168.216.128:2181,192.168.216.128:2182";
    private static ZkClient getInstance(){
        return new ZkClient(CONNECTSTRING,40000);
    }

    public static void main(String[] args) {
        ZkClient zkClient = getInstance();
        System.out.println(zkClient.getChildren("/dubbo-dev/com.gupao.vip.dubbo.order.IOrderService").get(0));
    }

}/*public static void main(String[] args) {
        ZkClient zkClient = getInstance();
        //zkClient.createEphemeral("/wyc/tt","123".getBytes());
       *//* zkClient.createPersistent("/wyc/dd",true);
        System.out.println(zkClient.readData("/wyc"));*//*
        //zkClient.deleteRecursive("/wyc");
        //zkClient.createPersistent("/wyc/123",true);
        List<String> childs = zkClient.getChildren("/wyc");
        zkClient.subscribeChildChanges("/wyc", new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println(s+"--->"+list);
            }
        });

        zkClient.createPersistent("/wyc/yy",true);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/