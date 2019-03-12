/**
 * FileName: CuratorLocks
 * Author:   王永超
 * Date:     2019/2/22 14:40
 * Description:
 * History:
 */
package com.gupao.vip.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/2/22
 * @since 1.0.0
 */
public class CuratorLocks {
    private static CuratorFramework curatorFramework ;
    private static int sessionTimeout=5000;
    private static final String CONNECTSTRING = "192.168.216.128:2180,192.168.216.128:2181,192.168.216.128:2182";


   public static InterProcessMutex getInstance(){
       CuratorFramework curatorFramework = CuratorFrameworkFactory.
               newClient(CONNECTSTRING,5000,5000,new ExponentialBackoffRetry(1000,3));
       curatorFramework.start();
        return new InterProcessMutex(curatorFramework,"/locksCurator");
   }
}
