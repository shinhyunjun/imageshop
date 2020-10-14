package com.example.imageshop.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserItem {

    private int userItemNo;
    private int userNo;

    private int itemId;
    private String itemName;
    private Integer price;
    private String description;
    private String pictureUrl;

    private Date regDate;
}
