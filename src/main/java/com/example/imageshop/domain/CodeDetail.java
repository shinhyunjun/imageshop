package com.example.imageshop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class CodeDetail {

    private String groupCode;
    private String codeValue;
    private String codeName;
    private int sortSeq;
    private String useYn;
    private Date regDate;
    private Date updDate;
}
