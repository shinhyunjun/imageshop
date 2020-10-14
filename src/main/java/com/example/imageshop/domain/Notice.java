package com.example.imageshop.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Notice {

    private int noticeNo;
    private String title;
    private String content;
    private Date regDate;
}
