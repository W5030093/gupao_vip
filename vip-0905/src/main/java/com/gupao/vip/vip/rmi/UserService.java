package com.gupao.vip.vip.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserService extends Remote {

    public void say(String name) throws RemoteException;
}
