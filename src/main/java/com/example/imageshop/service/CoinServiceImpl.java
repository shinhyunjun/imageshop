package com.example.imageshop.service;


import com.example.imageshop.domain.ChargeCoin;
import com.example.imageshop.domain.CodeLabelValue;
import com.example.imageshop.domain.PayCoin;
import com.example.imageshop.mapper.CodeMapper;
import com.example.imageshop.mapper.CoinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CoinServiceImpl implements CoinService{

    @Autowired
    private CoinMapper mapper;

    @Transactional
    @Override
    public void charge(ChargeCoin chargeCoin) throws Exception {
        mapper.charge(chargeCoin);
        mapper.create(chargeCoin);
    }

    @Override
    public List<ChargeCoin> list(int userNo) throws Exception {
        return mapper.list(userNo);
    }

    // 사용자의 상품 구매내역 반환
    @Override
    public List<PayCoin> listPayHistory(int userNo) throws Exception {
        return mapper.listPayHistory(userNo);
    }
}
