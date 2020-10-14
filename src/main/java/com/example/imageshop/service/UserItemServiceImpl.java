package com.example.imageshop.service;

import com.example.imageshop.domain.Item;
import com.example.imageshop.domain.Member;
import com.example.imageshop.domain.PayCoin;
import com.example.imageshop.domain.UserItem;
import com.example.imageshop.mapper.CoinMapper;
import com.example.imageshop.mapper.UserItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserItemServiceImpl implements UserItemService{

    @Autowired
    private UserItemMapper mapper;

    @Autowired
    private CoinMapper coinMapper;

    //등록 처리
    @Transactional
    @Override
    public void register(Member member, Item item) throws Exception {

        int userNo = member.getUserNo();

        int itemId = item.getItemId();
        int price = item.getPrice();

        UserItem userItem = new UserItem();
        userItem.setUserNo(userNo);
        userItem.setItemId(itemId);

        PayCoin payCoin = new PayCoin();
        payCoin.setUserNo(userNo);
        payCoin.setItemId(itemId);
        payCoin.setAmount(price);

        //코인 지급
        coinMapper.pay(payCoin);
        //구매 내역 등록
        coinMapper.createPayHistory(payCoin);

        mapper.create(userItem);
    }


    @Override
    public UserItem read(Integer userItemNo) throws Exception {
        return mapper.read(userItemNo);
    }

    @Override
    public List<UserItem> list(Integer userNo) throws Exception {
        return mapper.list(userNo);
    }
}
