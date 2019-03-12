/**
 * FileName: ThreadDemo
 * Author:   王永超
 * Date:     2019/3/8 14:53
 * Description:
 * History:
 */
package com.gupao.vip;

import com.gupao.vip.curator.CuratorLocks;
import org.apache.curator.framework.recipes.locks.InterProcessLock;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/3/8
 * @since 1.0.0
 */
public class ThreadDemo implements Callable<String> {
    public static int count = 0;

    public static CountDownLatch countDownLatch = new CountDownLatch(100);

    static InterProcessLock interProcessLock = CuratorLocks.getInstance();

    static int spcount = 10;
    @Override
    public String call() throws Exception {

        try {
            interProcessLock.acquire();
            if(spcount<0){
                return "货抢光了";
            }
            spcount--;
        } catch (Exception e) {
            e.printStackTrace();
            return "系统繁忙，请稍后再试";
        }finally {
            try {
                interProcessLock.release();
            } catch (Exception e) {
                e.printStackTrace();
                return "系统繁忙，请稍后再试";
            }
        }
       System.out.println(Thread.currentThread().toString());
        return "你抢到了";
    }
}
