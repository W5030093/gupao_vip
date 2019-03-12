package com.gupao.vip.javaApiLock;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class DistributeLock {

    //根节点
    private  static final String ROOT_LOCK="/Locks2";

    private ZooKeeper zooKeeper;

    private int SessionTimeOut;

    private static final byte[] data={1,2};

    private String LockId;//记录锁的Id

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public DistributeLock(){
        try {
            this.zooKeeper = ZookeeperClient.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            this.SessionTimeOut = ZookeeperClient.getSessionTimeOut();

    }
    //获取锁的方法
    public boolean lock(){
        try {
            //创建一个临时有序节点
            LockId  = zooKeeper.create(ROOT_LOCK+"/",data,ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(Thread.currentThread().getName()+"成功的创建了lockId:"+LockId);
            //获取根节点所有子节点
            List<String> rootNodes = zooKeeper.getChildren(ROOT_LOCK,true);
            //排序
            SortedSet<String> sortedSet = new TreeSet();

            rootNodes.forEach(s -> sortedSet.add(ROOT_LOCK+"/"+s));

            String first = sortedSet.first();

            //成功获取锁
            if(LockId.equals(first)){
                System.out.println(Thread.currentThread().getName()+"->我拿到了锁->lock:"+LockId);
                return true;
            }
            //获取比它小的节点
            SortedSet<String> lessSortedSet = sortedSet.headSet(LockId);
            CountDownLatch countDownLatch1 = new CountDownLatch(1);
            if(!lessSortedSet.isEmpty()){
                    String prevLockId = lessSortedSet.last();//拿到比当前LOCKID这个节点更小的上一个节点
                    zooKeeper.exists(prevLockId, new Watcher() {
                        @Override
                        public void process(WatchedEvent watchedEvent) {
                            if(watchedEvent.getType()==Event.EventType.NodeDeleted){
                                countDownLatch1.countDown();
                            }
                        }
                    });
                    countDownLatch1.await(SessionTimeOut,TimeUnit.MILLISECONDS);
                    //上面节点删除或者释放
                System.out.println(Thread.currentThread().getName()+"成功获取锁:"+LockId);
            }
            return true;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public  boolean unlock(){
        System.out.println(Thread.currentThread().getName()+"-->开始释放锁:"+LockId);
        try {
            zooKeeper.delete(LockId,-1);
            System.out.println("节点:"+LockId+"成功删除");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        final CountDownLatch countDownLatch1 = new CountDownLatch(10);
        Random random=new Random();
        for (int i = 0; i <10 ; i++) {

            new Thread(()->{
                DistributeLock distributeLock = null;
                try {
                    distributeLock = new DistributeLock();
                    countDownLatch1.countDown();
                    countDownLatch1.await();
                    distributeLock.lock();
                    Thread.sleep(random.nextInt(500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if(distributeLock!=null){
                        distributeLock.unlock();
                    }
                }
            }).start();
        }
    }
}
