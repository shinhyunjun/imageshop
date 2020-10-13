package com.example.imageshop.service;

import com.example.imageshop.common.security.domain.PageRequest;
import com.example.imageshop.domain.Board;

import java.util.List;

public interface BoardService {

    public void register(Board board) throws Exception;

    public Board read(Integer boardNo) throws Exception;

    public void modify(Board board) throws Exception;

    public void remove(Integer boardNo) throws Exception;

   // public List<Board> list() throws Exception;

    // 페이징요청 정보를 매개변수로 받아 페이징 처리를 한 게시글 목록을 반환한다.
    public List<Board> list(PageRequest pageRequest) throws Exception;

    //게시글 전체 건수를 반환한다.
    // public int count() throws Exception;

    //검색 처리된 게시글 건수를 반환한다.
    public int count(PageRequest pageRequest) throws Exception;

}
