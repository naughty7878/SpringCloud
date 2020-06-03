package com.test.springcloud.service.impl;

import com.test.springcloud.dao.AccountDao;
import com.test.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao storageDao;

    public void decrease(Long userId, BigDecimal money) {
        log.info("------->account-service中扣减账户余额开始");
        // 模拟业务异常,全局事务回滚
        int n = 10/0;
        storageDao.decrease(userId, money);
        log.info("------->account-service中扣减账户余额结束");
    }
}
