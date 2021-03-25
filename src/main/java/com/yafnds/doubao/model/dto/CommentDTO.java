package com.yafnds.doubao.model.dto;

import lombok.Data;
import org.apache.ibatis.javassist.SerialVersionUID;

import java.io.Serializable;

/**
 * @program: doubao
 * @description: 评论 接收前端数据
 * @author: y19991
 * @create: 2021-03-23 09:57
 **/

@Data
public class CommentDTO implements Serializable {

    private static final long serialVersionUID = -5957433707110390852L;

    /** 话题ID */
    private String topic_id;

    /** 内容 */
    private String content;

}
