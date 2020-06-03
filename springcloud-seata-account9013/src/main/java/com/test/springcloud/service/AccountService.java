package com.test.springcloud.service;

import java.math.BigDecimal;

public interface AccountService {
    public void decrease(Long userId, BigDecimal money);
}
