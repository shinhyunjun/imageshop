package com.example.imageshop.common.security;

import com.example.imageshop.common.security.domain.CustomUser;
import com.example.imageshop.domain.Member;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    //로그인 성공 처리자 메소드드
   @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        CustomUser customUser = (CustomUser)authentication.getPrincipal();
        Member member = customUser.getMember();

        log.info("Userid = " + member.getUserId());

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
