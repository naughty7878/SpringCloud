package com.test.springcloud.service.impl;

import com.test.springcloud.dao.StorageDao;
import com.test.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageDao storageDao;

    public void decrease(Long productId, Integer count) {
        log.info("------->order-service中扣减库存开始");
        storageDao.decrease(productId, count);
        log.info("------->order-service中扣减库存结束");

    }
}
