package com.example.imageshop.service;

import com.example.imageshop.domain.CodeLabelValue;

import java.util.List;

public interface CodeService {

    //그룹 코드 목록 조회
    public List<CodeLabelValue> getCodeGroupList() throws Exception;

    //지정된 그룹코드에 해당하는 코드 목록 조회
    public List<CodeLabelValue> getCodeList(String groupCode) throws Exception;

}
