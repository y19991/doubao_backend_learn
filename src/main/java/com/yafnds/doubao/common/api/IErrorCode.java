package com.yafnds.doubao.common.api;

/**
 * @program: doubao
 * @description: 返回到前端的错误信息
 * @author: y19991
 * @create: 2021-02-25 10:55
 **/

public interface IErrorCode {
    /**
     * 错误编码: -1失败;200成功
     *
     * @return 错误编码
     */
    Integer getCode();

    /**
     * 错误描述
     *
     * @return 错误描述
     */
    String getMessage();
}
