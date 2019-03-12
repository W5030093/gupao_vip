package com.gupao.vip;

import com.gupao.vip.impl.SayHelloImpl;

import javax.xml.ws.Endpoint;

public class StartWebService {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/vip/hello",new SayHelloImpl());
        System.out.println("发布成功");
    }
}
