package com.example.imageshop.common.security;

import com.example.imageshop.common.security.domain.CustomUser;
import com.example.imageshop.domain.Member;
import com.example.imageshop.mapper.MemberMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Log
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("Load User By UserName : " + userName);

        Member member = memberMapper.readByUserId(userName);

        log.info("queried by member mapper: " + member);

        return member == null ? null : new CustomUser(member);
    }
}
