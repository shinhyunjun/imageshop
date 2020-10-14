package com.example.imageshop.mapper;

import com.example.imageshop.domain.UserItem;

import java.util.List;

public interface UserItemMapper {

    // 등록 처리
    public void create(UserItem userItem) throws Exception;

    // 상세 화면
    public UserItem read(Integer userItemNo) throws Exception;

    // 목록 화면
    public List<UserItem> list(Integer userNo) throws Exception;

}
