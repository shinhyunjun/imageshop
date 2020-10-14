package com.example.imageshop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class PayCoin {

    private int historyNo;
    private int userNo;
    private int itemId;
    private String itemName;
    private int amount;
    private Date regDate;
}
