package com.gupao.vip.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

public class CuratorEventDemo {
    /*
    *
    * */
    public static void main(String[] args) {
        CuratorFramework curatorFramework = CuratorClientUtils.getInstance();

        //NodeCache nodeCache = new NodeCache(curatorFramework,"/wyc",false);
        try {
            /*nodeCache.start(true);
            nodeCache.getListenable().addListener(()->{
                System.out.println("节点发生变化"+ new String(nodeCache.getCurrentData().getData(),"UTF-8"));
            });
            curatorFramework.setData().forPath("/wyc","feifei".getBytes());*/

            //最后一个数据 是否缓存
            PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework,"/wyc1",true);

            pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);

            pathChildrenCache.getListenable().addListener((curatorFramework1,pathChildrenCacheEvent)->{
                switch (pathChildrenCacheEvent.getType()){
                    case CHILD_ADDED:
                        System.out.println("增加子节点");
                        break;
                    case CHILD_REMOVED:
                        System.out.println("删除子节点");
                        break;
                    case CHILD_UPDATED:
                        System.out.println("更新子节点");
                        break;
                     default:break;
                }
            });
            //  curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath("/wyc/test5 ","test".getBytes());
            TimeUnit.SECONDS.sleep(2);
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
