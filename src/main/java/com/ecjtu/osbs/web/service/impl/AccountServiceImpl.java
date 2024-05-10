package com.ecjtu.osbs.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.osbs.pojo.DO.AccountDO;
import com.ecjtu.osbs.web.dao.AccountDao;
import com.ecjtu.osbs.web.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * 账户service接口实现层
 *
 * @author CaoLongHui
 * @since 2024/4/3 01:54
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountDao, AccountDO> implements AccountService {
}
