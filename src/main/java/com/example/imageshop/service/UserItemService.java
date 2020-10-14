package com.example.imageshop.service;

import com.example.imageshop.domain.Item;
import com.example.imageshop.domain.Member;
import com.example.imageshop.domain.UserItem;

import java.util.List;

public interface UserItemService {

    //등록 처리
    public void register(Member member, Item item) throws Exception;

    //구매 상품보기
    public UserItem read(Integer userItemNo) throws Exception;

    //사용자 구매 상품 목록
    public List<UserItem> list(Integer userNo) throws Exception;

}
