package com.example.imageshop.service;

import com.example.imageshop.domain.CodeGroup;
import com.example.imageshop.mapper.CodeGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeGroupServiceImpl implements CodeGroupService {

    @Autowired
    private CodeGroupMapper mapper;

    @Override
    public void register(CodeGroup codeGroup) throws Exception {
        mapper.create(codeGroup);
    }

    @Override
    public List<CodeGroup> list() throws Exception {
        return mapper.list();
    }

    @Override
    public CodeGroup read(String groupCode) throws Exception{
        return mapper.read(groupCode);
    }

    public void modify(CodeGroup codeGroup) throws Exception{
        mapper.update(codeGroup);
    }

    @Override
    public void remove(String groupCode) throws Exception {
        mapper.delete(groupCode);
    }

}
