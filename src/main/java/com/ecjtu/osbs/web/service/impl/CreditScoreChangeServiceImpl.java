package com.ecjtu.osbs.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.osbs.pojo.DO.CreditScoreChangeDO;
import com.ecjtu.osbs.web.dao.CreditScoreChangeDao;
import com.ecjtu.osbs.web.service.CreditScoreChangeService;
import org.springframework.stereotype.Service;

/**
 * 信誉分变更历史service接口实现层
 *
 * @author CaoLongHui
 * @since 2024/4/3 01:59
 */
@Service
public class CreditScoreChangeServiceImpl extends ServiceImpl<CreditScoreChangeDao, CreditScoreChangeDO>
        implements CreditScoreChangeService {
}
