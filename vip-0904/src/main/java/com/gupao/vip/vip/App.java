package com.gupao.vip.vip;

import com.alibaba.fastjson.JSONObject;
import com.gupao.vip.vip.pojo.Person;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Person person = new Person();
        person.setUsername("11");
        person.setPassword("22");
        String result = JSONObject.toJSONString(person);
        JSONObject.parseObject(result).get("username");
        System.out.println(JSONObject.parseObject(result).get("username"));
    }
}
