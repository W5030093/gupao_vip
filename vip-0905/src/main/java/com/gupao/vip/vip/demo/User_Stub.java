package com.gupao.vip.vip.demo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class User_Stub extends Person{

    private Socket socket;

    public User_Stub() throws IOException {
        socket = new Socket("localhost",8888);

    }
    public String getUsername(){
        ObjectOutputStream outputStream =null;
        ObjectInputStream objectInputStream=null;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject("username");
            outputStream.flush();

            objectInputStream = new ObjectInputStream(socket.getInputStream());
            String result = (String) objectInputStream.readObject();
            objectInputStream.close();
            outputStream.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(objectInputStream!=null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream!=null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }
}
