package com.example.imageshop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class CodeGroup {

    public String groupCode;
    public String groupName;
    public String useYn;
    public Date regDate;
    public Date updDate;
}
