package com.example.imageshop.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class Item {

    private Integer itemId;

    private String itemName;

    private Integer price;

    private String description;

    private MultipartFile picture;

    private String pictureUrl;

    private MultipartFile preview;

    private String previewUrl;
}
