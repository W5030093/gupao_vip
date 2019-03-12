package com.gupao.vip.zkClient;

import java.io.Serializable;

public class UserCenter implements Serializable {

    private static final long serialVersionUID = 6252770361182939643L;

    private String mc_id;//机器信息

    private String mc_name;


    public void setMc_id(String mc_id) {
        this.mc_id = mc_id;
    }


    public String getMc_name() {
        return mc_name;
    }

    public void setMc_name(String mc_name) {
        this.mc_name = mc_name;
    }

    @Override
    public String toString() {
        return "UserCenter{" +
                "mc_id='" + mc_id + '\'' +
                ", mc_name='" + mc_name + '\'' +
                '}';
    }
}
