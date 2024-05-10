package com.ecjtu.osbs.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.osbs.pojo.DO.SpaceCategoryDO;
import com.ecjtu.osbs.web.dao.SpaceCategoryDao;
import com.ecjtu.osbs.web.service.SpaceCategoryService;
import org.springframework.stereotype.Service;

/**
 * 办公空间类别service接口实现层
 *
 * @author CaoLongHui
 * @since 2024/4/3 02:14
 */
@Service
public class SpaceCategoryServiceImpl extends ServiceImpl<SpaceCategoryDao, SpaceCategoryDO>
        implements SpaceCategoryService {
}
