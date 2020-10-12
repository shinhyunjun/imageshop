package com.example.imageshop.mapper;

import com.example.imageshop.domain.Board;

import java.util.List;

public interface BoardMapper {

    public void create(Board board) throws Exception;

    public Board read(Integer boardNo) throws Exception;

    public void update(Board board) throws Exception;

    public void delete(Integer boardNo) throws Exception;

    public List<Board> list() throws Exception;
}
