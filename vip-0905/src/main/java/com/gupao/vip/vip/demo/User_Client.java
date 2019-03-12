package com.gupao.vip.vip.demo;

import java.io.IOException;

public class User_Client {
    public static void main(String[] args) {
        try {
            Person person = new User_Stub();
            String username = person.getUsername();
            System.out.println(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
