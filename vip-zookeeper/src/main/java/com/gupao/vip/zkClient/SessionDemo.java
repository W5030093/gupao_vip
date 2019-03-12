package com.gupao.vip.zkClient;

import org.I0Itec.zkclient.ZkClient;

public class SessionDemo {
    private static final String CONNECTSTRING = "192.168.154.130:2181,192.168.154.130:2182,192.168.154.130:2183";
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient(CONNECTSTRING,40000);
        System.out.println(zkClient.getChildren("/wyc"));
    }
}
