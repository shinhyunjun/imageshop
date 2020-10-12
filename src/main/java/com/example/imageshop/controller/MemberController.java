package com.example.imageshop.controller;

import com.example.imageshop.domain.CodeLabelValue;
import com.example.imageshop.domain.Member;
import com.example.imageshop.service.CodeService;
import com.example.imageshop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class MemberController {

    @Autowired
    private MemberService service;

    @Autowired
    private CodeService codeService;


    // 스프링 시큐리티의 비밀번호 암호화 처리기기
   @Autowired
    private PasswordEncoder passwordEncoder;



    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void registerForm(Member member, Model model) throws Exception {

        String groupCode = "Job";  //직업코드
        List<CodeLabelValue> jobList = codeService.getCodeList(groupCode);

        model.addAttribute("jobList", jobList);

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Validated Member member, BindingResult result, Model model, RedirectAttributes rttr) throws Exception {

        if(result.hasErrors()) {
            String groupCode = "Job";  //직업코드
            List<CodeLabelValue> jobList = codeService.getCodeList(groupCode);

            model.addAttribute("jobList", jobList);

            return "user/register";
        }

        // 비밀번호 암호화
        String inputPassword = member.getUserPw();
        member.setUserPw(passwordEncoder.encode(inputPassword));

        service.register(member);

        rttr.addFlashAttribute("userName", member.getUserName());
        return "redirect:/user/registerSuccess";
    }

    //등록 성공 화면
    @RequestMapping(value = "/registerSuccess", method = RequestMethod.GET)
    public void registerSuccess(Model model) throws Exception {

    }

    //목록 화면
    @PreAuthorize("hasRole('ROLE_ADMIN')") // 관리자 권한을 가진 사용자만 접근이 가능
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void list(Model model) throws Exception {
        model.addAttribute("list", service.list());
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public void read(int userNo, Model model) throws Exception {

       //작업코드 목록을 조회하여 뷰에 전달
        String groupCode = "Job";
        List<CodeLabelValue> jobList = codeService.getCodeList(groupCode);

        model.addAttribute("jobList", jobList);

        model.addAttribute(service.read(userNo));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')") // 관리자 권한을 가진 사용자만 접근이 가능
    public String remove(int userNo, RedirectAttributes rttr) throws Exception {
        service.remove(userNo);

        rttr.addFlashAttribute("msg", "SUCCESS");

        return "redirect:/user/list";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public void modifyForm(int userNo, Model model) throws Exception {

        //작업코드 목록을 조회하여 뷰에 전달
        String groupCode = "Job";
        List<CodeLabelValue> jobList = codeService.getCodeList(groupCode);

        model.addAttribute("jobList", jobList);

        model.addAttribute(service.read(userNo));
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(Member member, RedirectAttributes rttr) throws Exception {
        service.modify(member);

        rttr.addFlashAttribute("msg", "SUCCESS");

        return "redirect:/user/list";
    }



    @RequestMapping(value = "/setup", method = RequestMethod.POST)
    public String setupAdmin(Member member, RedirectAttributes rttr) throws Exception {

        //회원 테이블 데이터 건수를 확인하여 빈 테이블이면 최초관리자를 생성
        if(service.countAll() == 0) {
            String inputPassword = member.getUserPw();
            member.setUserPw(passwordEncoder.encode(inputPassword));

            member.setJob("00");

            service.setupAdmin(member);

            rttr.addFlashAttribute("userName", member.getUserName());
            return "redirect:/user/registerSuccess";
        }

        //회원 테이블에 데이터가 존재혀면 최초관리자를 생성할 수 없으므로 실패화면 이동
        return "redirect:/user/setupFailure";
    }

    @RequestMapping(value = "/setup", method = RequestMethod.GET)
    public String setupAdminForm(Member member, Model model) throws Exception {

        //회원 테이블 데이터 건수 확인하여 최초관리자 등록화면 표시
        if(service.countAll() == 0) {
            return "user/setup";
        }

        //회원 테이블에 데이터가 존재하면 최초관리자를 생성할 수 없으므로 실패 화면표시
        return "user/setupFailure";
    }
}
