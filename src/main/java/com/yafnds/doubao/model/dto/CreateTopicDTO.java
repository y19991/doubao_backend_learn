package com.yafnds.doubao.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @program: doubao
 * @description: 创建话题
 * @author: y19991
 * @create: 2021-03-11 10:52
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTopicDTO implements Serializable {

    private static final long serialVersionUID = -5957433707110390852L;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签
     */
    private List<String> tags;
}
