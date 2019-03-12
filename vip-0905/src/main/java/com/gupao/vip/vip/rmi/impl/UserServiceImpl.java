package com.gupao.vip.vip.rmi.impl;

import com.gupao.vip.vip.rmi.UserService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends UnicastRemoteObject implements UserService {

    public UserServiceImpl() throws RemoteException {

    }

    @Override
    public void say(String name) throws RemoteException {
        System.out.println("测试rmi--》"+name);
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.forEach( System.out::println);
    }
}
