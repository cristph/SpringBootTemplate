package com.cristph.template.constraint;

import java.util.ArrayList;
import java.util.List;


public class Constraints {
    public static final String IPV6 = "ipv6";
    public static final String IPV4 = "ipv4";

    /**
     * 数据认证类型
     * ('01':mysql,'02':oracle,'03':sql server;'10':LDAP,'11':RADIUS,'12':AD)
     **/

    public static final String MAIN_AUTH_TYPE_LOCAL = "00";
    public static final String MAIN_AUTH_TYPE_MYSQL = "01";
    public static final String MAIN_AUTH_TYPE_ORACLE = "02";
    public static final String MAIN_AUTH_TYPE_SQL_SERVER = "03";
    public static final String MAIN_AUTH_TYPE_LDAP = "10";
    public static final String MAIN_AUTH_TYPE_RADIUS = "11";
    public static final String MAIN_AUTH_TYPE_AD = "12";

    public static final String APP_RDP = "RDP";

    public static final String CS_APP_SA = "SA";
    public static final String CS_APP_ST = "ST";
    public static final String CS_APP_EA = "EA";

    public static final String L3NET_APP_FN = "FN";
    public static final String L3NET_APP_TU = "TU";


    public static final String[] AUTH_TYPES = {"00", "01", "02", "03", "10", "11", "12"};
    public static final String[] ALGORITHMS = {"PAP", "CHAP", "MSCHAPv1", "MSCHAPv2", "EAPMD5", "EAPMSCHAPv2"};

    public static final String[] USER_STATUS = {"Enabled", "Disable", "Locked"};
    public static final String USER_STATUS_ENABLED = "Enabled";
    public static final String USER_STATUS_DISABLED = "Disabled";
    public static final String USER_STATUS_LOCKED = "LOCKED";

    //redis Session
    public static final String SUPER_ADMINISTRATOR = "超级管理员";
    public static final String USER_INFO = "userInfo";//用户登录session常量名
    public static final String LOGIN_COUNT = "loginCount";//登陆次数常量名
    public static final String LAST_LOGIN_FAIL_TIME = "lastLoginFailTime";//登陆次数常量名
    public static final String SESSIONID = "SESSIONID";//会话id
    public static final String USER_NAME = "userName";//用户名
    public static final String CREATION_TIME = "creationTime";//session创建时间
    public static final String R_USER_SESSION_SPRINGBOOT_PREFIX = "spring:session:";//保存session前缀
    public static final String R_USER_SESSION_SPRINGBOOT_PROJECT_NAME = "vpn";
    public static final String R_USER_SESSION_SPRINGBOOT_NAME_SPACE_CLIENT = ":client";
    public static final String R_USER_SESSION_SPRINGBOOT_NAME_SPACE_ADMIN = ":admin";
    public static final String R_USER_SESSION_SPRINGBOOT_SUFFIX = ":sessions:";//保存session后缀
    public static final String R_USER_SESSION_SPRINGBOOT_EXPIRATIONS_SUFFIX = ":expirations";//保存有效期session后缀
    public static final String R_USER_SESSION_SPRINGBOOT_EXPIRES_SUFFIX = ":sessions:expires:";//保存expires后缀
    public static final String R_USER_SESSION_SPRINGBOOT_ATTR_PREFIX = "sessionAttr:";//保存session后缀


    public static final long MAX_IMPORT_NUM = 6000L;


    /***
     *  propertyMap.put(0,"userName");
     propertyMap.put(1,"fullName");
     propertyMap.put(2,"groupName");
     propertyMap.put(3,"ruleName");
     propertyMap.put(4,"email");
     propertyMap.put(5,"tel");
     propertyMap.put(6,"status");
     */

    public static final String USER_NAME_PROPORTY="userName";
    public static final String USER_FULL_NAME_PROPORTY="fullName";
    public static final String USER_GROUP_NAME_PROPORTY="groupName";
    public static final String USER_RULE_NAME_PROPORTY="ruleName";
    public static final String USER_EMAIL_PROPORTY="email";
    public static final String USER_TEL_PROPORTY="tel";
    public static final String USER_STATUS_PROPORTY="status";



    public static final String EMAIL_REG="[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+";//邮箱正则
    public static final String PHONE_REG="1[3578][0-9]{9,9}";//手机正则

    public static List<String> authTypes() {
        List<String> list = new ArrayList<>();
        for (String item : AUTH_TYPES) {
            list.add(item);
        }
        return list;
    }

    public static  boolean isUserStatus(String status){
        for(String item:USER_STATUS){
            if(item.equals(status)){
                return true;
            }
        }

        return false;
    }

}
