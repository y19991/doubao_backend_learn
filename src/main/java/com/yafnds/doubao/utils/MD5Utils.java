package com.yafnds.doubao.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @program: doubao
 * @description: MD5加密工具类
 * @author: y19991
 * @create: 2021-03-03 11:32
 **/

public class MD5Utils {

    /**
     * 使用 MD5 规则加密用户密码
     * @param pwd
     * @return 加密后的密码
     */
    public static String getPwd(String pwd) {

        try {

            // 创建加密对象
            MessageDigest digest = MessageDigest.getInstance("md5");
            // 调用加密对象的方法，加密动作已完成
            byte[] bytes = digest.digest(pwd.getBytes());

            /*
                接下来，我们要对加密后的结果进行优化，按照mysql的优化思路走
                mysql的优化思路：
                    第一步：将数据全部转换成正数
                    第二步：将所有的数据转换成16进制的形式
             */
            String hexString = "";
            for (byte b : bytes) {

                /*
                    第一步：将数据全部转换成正数
                    解释：为什么要用 b & 255
                        b:它本来是一个byte类型的数据(1个字节) 255：是一个int类型的数据(4个字节)
                        byte类型的数据与int类型的数据进行运算，会自动类型提升为int类型 eg: b: 1001 1100(原始数据)
                        运算时：
                            b:
                                0000 0000 0000 0000 0000 0000 1001 1100
                            255:
                                0000 0000 0000 0000 0000 0000 1111 1111
                        结果：
                                0000 0000 0000 0000 0000 0000 1001 1100
                        此时的temp是一个int类型的整数
                 */
                int temp = b & 255;

                /*
                    第二步：将所有的数据转换成16进制的形式
                    注意：
                        转换的时候注意 if( 正数 >= 0 && < 16)，那么如果使用 Integer.toHexString()，可能会造成缺少位数
                    因此，需要对temp进行判断
                 */
                if (temp < 16 && temp >= 0) {
                    // 手动补上一个“0”
                    hexString = hexString + "0" + Integer.toHexString(temp);
                } else {
                    hexString = hexString + Integer.toHexString(temp);
                }
            }
            return hexString;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }
}
