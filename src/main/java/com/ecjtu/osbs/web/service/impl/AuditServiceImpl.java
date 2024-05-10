package com.ecjtu.osbs.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.osbs.pojo.DO.AuditDO;
import com.ecjtu.osbs.web.dao.AuditDao;
import com.ecjtu.osbs.web.service.AuditService;
import org.springframework.stereotype.Service;

/**
 * 审核service接口实现层
 *
 * @author CaoLongHui
 * @since 2024/4/3 01:57
 */
@Service
public class AuditServiceImpl extends ServiceImpl<AuditDao, AuditDO> implements AuditService {
}
