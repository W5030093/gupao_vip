package com.gupao.vip.vip.demo;

public class UserServer extends Person {
    public static void main(String[] args) {
        UserServer userServer = new UserServer();
        userServer.setUsername("xiaoming");
        User_Skeleton user_skeleton = new User_Skeleton(userServer);
        user_skeleton.start();
    }
}
