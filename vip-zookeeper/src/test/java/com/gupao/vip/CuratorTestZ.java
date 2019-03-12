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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 〈测试排它锁是否可以正确给用户返回抢到了〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2019/2/22
 * @since 1.0.0
 */
public class CuratorTestZ {

    public static CountDownLatch countDownLatch = new CountDownLatch(100);
    static ExecutorService executorService =  Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        List<ThreadDemo> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ThreadDemo td = new ThreadDemo();
            list.add(td);
        }
        List<Future<String>> a = executorService.invokeAll(list);
        /*executorService.in*/
        int i =1;
        for (Future<String> future:a){
            System.out.println(future.get()+i++);
        }
    }
}
