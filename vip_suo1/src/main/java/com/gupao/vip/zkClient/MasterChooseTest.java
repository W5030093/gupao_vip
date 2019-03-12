package com.gupao.vip.zkClient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MasterChooseTest {

    private static final String CONNECTSTRING = "192.168.154.130:2181,192.168.154.130:2182,192.168.154.130:2183";


    public static void main(String[] args) throws IOException {
        ZkClient zkClient = new ZkClient(CONNECTSTRING,5000,5000,new SerializableSerializer());
        UserCenter userCenter = new UserCenter();
        userCenter.setMc_id("2");
        userCenter.setMc_name("客户端"+2);
        MasterSelector masterSelector = new MasterSelector(userCenter,zkClient); //触发选举操作
        while (true){

            masterSelector.start();

        }

    }
}
