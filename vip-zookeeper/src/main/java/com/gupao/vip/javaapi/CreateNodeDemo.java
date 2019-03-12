package com.gupao.vip.javaapi;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class CreateNodeDemo implements Watcher{
    private static final String CONNECTSTRING = "192.168.154.130:2181,192.168.154.130:2182,192.168.154.130:2183";
    private static ZooKeeper zooKeeper;
    private  CountDownLatch countDownLatch = new CountDownLatch(1);
    private static Stat stat=new Stat();
    public static void main(String[] args) throws KeeperException {
        try {
            zooKeeper = new ZooKeeper(CONNECTSTRING, 5000, new CreateNodeDemo());
            //zooKeeper.create("/yy1/yy","123".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            //zooKeeper.getData("/yy",new CreateNodeDemo(),stat);
            //zooKeeper.setData("/yy","41".getBytes(),-1);
            zooKeeper.delete("/yy1",-1);
            Thread.sleep(2000);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //注意每次都要调用zooKeeper.getData(watchedEvent.getPath(),true,stat));进行注册 因为watch的通知是一次性的
        public void process(WatchedEvent watchedEvent) {
                    if(watchedEvent.getState()==Event.KeeperState.SyncConnected){
                        if(Event.EventType.None==watchedEvent.getType()&&null==watchedEvent.getPath()){
                            countDownLatch.countDown();
                            System.out.println(watchedEvent.getState()+"-->"+watchedEvent.getType());
                        } else if(watchedEvent.getType()==Event.EventType.NodeCreated){
                            try {
                                System.out.println("数据新增触发"+watchedEvent.getPath()+zooKeeper.getData(watchedEvent.getPath(),true,stat));
                            } catch (KeeperException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else if(watchedEvent.getType()== Event.EventType.NodeChildrenChanged){//子节点的数据变化会触发
                            try {
                                System.out.println("子节点数据变更路径："+watchedEvent.getPath()+"->节点的值："+
                                        zooKeeper.getData(watchedEvent.getPath(),true,stat));
                            } catch (KeeperException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else if(watchedEvent.getType()== Event.EventType.NodeCreated){//创建子节点的时候会触发
                            try {
                                System.out.println("节点创建路径："+watchedEvent.getPath()+"->节点的值："+
                                        zooKeeper.getData(watchedEvent.getPath(),true,stat));
                            } catch (KeeperException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else if(watchedEvent.getType()== Event.EventType.NodeDeleted){//子节点删除会触发
                            System.out.println("节点删除路径："+watchedEvent.getPath());
                        }else if(watchedEvent.getType()==Event.EventType.NodeDataChanged){
                            try {
                                System.out.println("节点数据改变："+watchedEvent.getPath()+"-->"+zooKeeper.getData(watchedEvent.getPath(),true,stat));
                            } catch (KeeperException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    System.out.println(watchedEvent.getType());
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

}
