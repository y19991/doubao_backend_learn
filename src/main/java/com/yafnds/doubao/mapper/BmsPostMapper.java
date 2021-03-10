package com.yafnds.doubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yafnds.doubao.model.entity.BmsPost;
import com.yafnds.doubao.model.vo.PostVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @program: doubao
 * @description: 话题
 * @author: y19991
 * @create: 2021-03-09 10:36
 **/

@Repository
public interface BmsPostMapper extends BaseMapper<BmsPost> {

    /**
     * 分页查询首页话题列表
     * @param page PostVO Page
     * @param tab 查询目标 如：当前最火（hot）
     * @return
     */
    Page<PostVO> selectListAndPage(@Param("page") Page<PostVO> page, @Param("tab") String tab);
}
