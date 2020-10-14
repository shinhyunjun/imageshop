package com.example.imageshop.controller;


import com.example.imageshop.common.security.domain.CustomUser;
import com.example.imageshop.domain.Member;
import com.example.imageshop.domain.UserItem;
import com.example.imageshop.service.UserItemService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Controller
@RequestMapping("/useritem")
public class UserItemController {

    @Autowired
    private UserItemService service;

    @Value("${upload.path}")
    private String uploadPath;


    // 사용자 구매 상품 목록
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")

    public void list(Model model, Authentication authentication) throws Exception {
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        Member member = customUser.getMember();

        int userNo = member.getUserNo();

        model.addAttribute("list", service.list(userNo));
    }


    // 구매 상품 보기
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    public void read(int userItemNo, Model model) throws Exception {

        model.addAttribute(service.read(userItemNo));
    }


    // 구매 상품 다운로드드
    @ResponseBody
    @RequestMapping("/download")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    public ResponseEntity<byte[]> download(int userItemNo, Authentication authentication) throws Exception {

        UserItem userItem = service.read(userItemNo);

        String fullName = userItem.getPictureUrl();

        InputStream in = null;
        ResponseEntity<byte[]> entity = null;

        try {
            HttpHeaders headers = new HttpHeaders();

            in = new FileInputStream(uploadPath + File.separator + fullName);

            String fileName = fullName.substring(fullName.indexOf("_") + 1);

            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");

            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        } finally {
            in.close();
        }

        return entity;
    }

}
