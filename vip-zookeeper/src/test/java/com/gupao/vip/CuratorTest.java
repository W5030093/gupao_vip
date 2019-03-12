/**
 * FileName: CuratorTest
 * Author:   王永超
 * Date:     2019/2/22 22:07
 * Description:
 * History:
 */
package com.gupao.vip;

import com.gupao.vip.curator.CuratorLocks;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/2/22
 * @since 1.0.0
 */
public class CuratorTest {
    public static int count = 0;

    public static CountDownLatch countDownLatch = new CountDownLatch(100);

    public static void main(String[] args) throws InterruptedException {

        for (int i=0;i<100;i++){
            CuratorLocks curatorLocks = new CuratorLocks();
           final InterProcessLock interProcessLock = CuratorLocks.getInstance();
            Thread thread = new Thread(){


                @Override
                public void run() {

                        //  distributeLock.lock();

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        interProcessLock.acquire();
                        for(int y=0;y<2000;y++){

                            count++;

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            interProcessLock.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                    countDownLatch.countDown();
                }
            };
            thread.start();
        }
        countDownLatch.await();
        System.out.println("结束了-->"+count);
    }

}
