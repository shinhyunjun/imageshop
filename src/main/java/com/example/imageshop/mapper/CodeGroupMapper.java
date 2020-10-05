package com.example.imageshop.mapper;

import com.example.imageshop.domain.CodeGroup;

import java.util.List;

public interface CodeGroupMapper {

    public void create(CodeGroup codeGroup) throws Exception;

    public List<CodeGroup> list() throws Exception;

    public CodeGroup read(String groupCode) throws Exception;

    public void update(CodeGroup codeGroup) throws Exception;

    public void delete(String groupCode) throws Exception;

}
