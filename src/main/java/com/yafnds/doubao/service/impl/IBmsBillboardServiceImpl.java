package com.yafnds.doubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yafnds.doubao.mapper.BmsBillboardMapper;
import com.yafnds.doubao.model.entity.BmsBillboard;
import com.yafnds.doubao.service.IBmsBillboardService;
import org.springframework.stereotype.Service;

/**
 * @program: doubao
 * @description: 公告栏
 * @author: y19991
 * @create: 2021-02-25 10:59
 **/

@Service
public class IBmsBillboardServiceImpl extends ServiceImpl<BmsBillboardMapper, BmsBillboard>
        implements IBmsBillboardService {
}
