package com.example.imageshop.service;

import com.example.imageshop.domain.CodeGroup;
import com.example.imageshop.mapper.CodeGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeGroupServiceImpl implements CodeGroupService {

    @Autowired
    private CodeGroupMapper mapper;

    @Override
    public void register(CodeGroup codeGroup) throws Exception {
        mapper.create(codeGroup);
    }
}
