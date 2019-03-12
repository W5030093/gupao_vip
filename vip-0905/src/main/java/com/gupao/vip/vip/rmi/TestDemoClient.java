package com.gupao.vip.vip.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class TestDemoClient {
    public static void main(String[] args) {
        try {
            UserService userService = (UserService) Naming.lookup("rmi://localhost:8888/wyc");
            userService.say("王永超");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
