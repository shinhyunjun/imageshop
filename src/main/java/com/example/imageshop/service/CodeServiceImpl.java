package com.example.imageshop.service;

import com.example.imageshop.domain.CodeLabelValue;
import com.example.imageshop.mapper.CodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeMapper mapper;

    @Override
    public List<CodeLabelValue> getCodeGroupList() throws Exception {
        return mapper.getCodeGroupList();
    }

    @Override
    public List<CodeLabelValue> getCodeList(String groupCode) throws Exception {
        return mapper.getCodeList(groupCode);
    }

}