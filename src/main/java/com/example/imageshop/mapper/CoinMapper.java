package com.example.imageshop.mapper;

import com.example.imageshop.domain.ChargeCoin;
import com.example.imageshop.domain.PayCoin;

import java.util.List;

public interface CoinMapper {

    public void create(ChargeCoin chargeCoin) throws Exception;

    public void charge(ChargeCoin chargeCoin) throws Exception;

    public List<ChargeCoin> list(int userNo) throws Exception;

    //구매 내역 등록
    public void createPayHistory(PayCoin payCoin) throws Exception;

    //구매 내역 조회
    public List<PayCoin> listPayHistory(int userNo) throws Exception;

    //코인 지급
    public void pay(PayCoin payCoin) throws Exception;

}
