package com.ecjtu.osbs.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.osbs.pojo.DO.TransactionDO;
import com.ecjtu.osbs.web.dao.TransactionDao;
import com.ecjtu.osbs.web.service.TransactionService;
import org.springframework.stereotype.Service;

/**
 * 交易service接口实现层
 *
 * @author CaoLongHui
 * @since 2024/4/3 04:21
 */
@Service
public class TransactionServiceImpl extends ServiceImpl<TransactionDao, TransactionDO> implements TransactionService {
}
