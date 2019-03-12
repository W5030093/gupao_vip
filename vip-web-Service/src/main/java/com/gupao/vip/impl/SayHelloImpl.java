package com.gupao.vip.impl;

import com.gupao.vip.ISayHello;

import javax.jws.WebService;

@WebService
public class SayHelloImpl implements ISayHello {
    @Override
    public String sayHello(String name) {
        System.out.println("hello,"+name);
        return "hello,I'm"+name;
    }
}
