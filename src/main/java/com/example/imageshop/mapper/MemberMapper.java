package com.example.imageshop.mapper;

import com.example.imageshop.domain.Member;
import com.example.imageshop.domain.MemberAuth;

import java.util.List;

public interface MemberMapper {

    //권한 생성
    public void createAuth(MemberAuth memberAuth);

    //권한 삭제
    public void deleteAuth(int userNo) throws Exception;

    //등록 처리
    public void create(Member member) throws Exception;

    //상세 화면
    public Member read(int userNo) throws Exception;

    //수정 처리
    public void update(Member member) throws Exception;

    //삭제 처리
    public void delete(int userNo) throws Exception;

    //목록 화면
    public List<Member> list() throws Exception;

    public int countAll() throws Exception;

    // 해당 사용자 아이디를 가진 회원정보를 조회한다.
    public Member readByUserId(String userId);
}
