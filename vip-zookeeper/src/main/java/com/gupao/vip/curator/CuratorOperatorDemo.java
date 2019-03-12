package com.gupao.vip.curator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.sf.json.JSONObject;
import netscape.javascript.JSObject;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CuratorOperatorDemo {
    public static void main(String[] args) {

        CuratorFramework curatorFramework = CuratorClientUtils.getInstance();
        System.out.println("----连接成功----");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        try {

            //创建节点
            /*String result = curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                    .forPath("/curator","haha".getBytes());
            System.out.println(result);*/


            //删除节点  默认情况下 version 为-1
            //curatorFramework.delete().deletingChildrenIfNeeded().forPath("/curator");

            //获取数据
            Stat stat = new Stat();
            byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/wyc");
            System.out.println(bytes.toString()+"------"+stat);

            //更新数据
           // Stat stat1 = curatorFramework.setData().forPath("/wyc","lala".getBytes());

            //异步操作
           /* curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
                @Override
                public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                    //回调
                    System.out.println(Thread.currentThread().getName()+"-->"+curatorEvent.getResultCode()+"->"+curatorEvent.getType());
                    countDownLatch.countDown();
                }
            },executorService).forPath("/wyc11","123".getBytes());*/
        } catch (Exception e) {
            e.printStackTrace();
        }
       // try {
            //countDownLatch.await();
           // executorService.shutdown();

            //事务操作
            try {
                Collection<CuratorTransactionResult> curatorTransactionResults = curatorFramework.inTransaction().create().forPath("/trans", "ggg".getBytes()).and().setData().forPath("/wyc", "xxx".getBytes()).and().commit();
                curatorTransactionResults.forEach(curatorTransactionResult -> {
                    System.out.println(JSONObject.fromObject(curatorTransactionResult).toString());
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        /*} catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
