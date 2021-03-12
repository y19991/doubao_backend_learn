package com.yafnds.doubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yafnds.doubao.mapper.BmsFollowMapper;
import com.yafnds.doubao.model.entity.BmsFollow;
import com.yafnds.doubao.service.IBmsFollowService;
import org.springframework.stereotype.Service;

/**
 * @author y19991
 * @version V1.0
 * @Package com.yafnds.doubao.service.impl
 * @date 2021/3/1321:40
 * @Description: 关注
 */
@Service
public class IBmsFollowServiceImpl extends ServiceImpl<BmsFollowMapper, BmsFollow>
        implements IBmsFollowService {
}
