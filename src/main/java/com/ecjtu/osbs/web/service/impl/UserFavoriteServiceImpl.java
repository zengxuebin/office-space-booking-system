package com.ecjtu.osbs.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.osbs.pojo.DO.UserFavoriteDO;
import com.ecjtu.osbs.web.dao.UserFavoriteDao;
import com.ecjtu.osbs.web.service.UserFavoriteService;
import org.springframework.stereotype.Service;

/**
 * 用户收藏service接口实现层
 *
 * @author CaoLongHui
 * @since 2024/4/3 04:22
 */
@Service
public class UserFavoriteServiceImpl extends ServiceImpl<UserFavoriteDao, UserFavoriteDO>
        implements UserFavoriteService {
}
