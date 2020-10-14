package com.example.imageshop.service;

import com.example.imageshop.domain.Member;

import java.util.List;

public interface MemberService {

    public void register(Member member) throws Exception;

    public Member read(int userNo) throws Exception;

    public void modify(Member member) throws Exception;

    public void remove(int userNo) throws Exception;

    public List<Member> list() throws Exception;

    //회원 테이블 건수 조회
    public int countAll() throws Exception;

    //최초 관리자 생성을 위한 데이터 등록
    public void setupAdmin(Member member) throws Exception;

    public int getCoin(int userNo) throws Exception;
}
