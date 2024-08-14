package com.itheima.bigevent;

import org.junit.jupiter.api.Test;

public class TreadLocalTest {

    @Test
    public void testThreadLocalSetAndGet() {
        // 提供一个TreadLocal对象
        ThreadLocal tl = new ThreadLocal();

        // 开启两个线程
        new Thread(()->{
            tl.set("aa");
            System.out.println(Thread.currentThread().getName()+": "+tl.get());
        },"蓝色").start();
        new Thread(()->{
            tl.set("bb");
            System.out.println(Thread.currentThread().getName()+": "+tl.get());
        },"绿色").start();
    }
}
