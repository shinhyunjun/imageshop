package com.example.imageshop.service;

import com.example.imageshop.common.security.domain.PageRequest;
import com.example.imageshop.domain.Board;
import com.example.imageshop.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper mapper;

    @Override
    public void register(Board board) throws Exception {
        mapper.create(board);
    }

    @Override
    public Board read(Integer boardNo) throws Exception {
        return mapper.read(boardNo);
    }

    @Override
    public void modify(Board board) throws Exception {
        mapper.update(board);
    }

    @Override
    public void remove(Integer boardNo) throws Exception {
        mapper.delete(boardNo);
    }

    /*
    @Override
    public List<Board> list() throws Exception {
        return mapper.list();
    }
    */

    // 페이징 요청 정보를 매개변수로 받아 페이징 처리를 한 게시글 목록을 반환한다.
    @Override
    public List<Board> list(PageRequest pageRequest) throws Exception {
        return mapper.list(pageRequest);
    }

    /* 게시글 전체 건수를 반환한다.
    @Override
    public int count() throws Exception {
        return mapper.count();
    }
    */

    // 검색처리된 게시글 건수를 반환한다.
    @Override
    public int count(PageRequest pageRequest) throws Exception {
        return mapper.count(pageRequest);
    }
}
