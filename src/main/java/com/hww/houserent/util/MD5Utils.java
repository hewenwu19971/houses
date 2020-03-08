package com.hww.houserent.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Utils {
    //可以使用随机数，这里用于测试，使用固定盐
    public static final String SALT = "Test_user_password";
    public static String computeMd5(String str) {
        /**
         * MD5
         * 将原始密码加盐（上面生成的盐），并且用md5算法加密三次，将最后结果存入数据库中
         */
        return new Md5Hash(str,SALT,3).toString();
    }

}
