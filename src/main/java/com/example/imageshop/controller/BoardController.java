package com.example.imageshop.controller;


import com.example.imageshop.common.security.domain.CustomUser;
import com.example.imageshop.domain.Board;
import com.example.imageshop.domain.Member;
import com.example.imageshop.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService service;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_MEMBER')")
    public void registerForm(Model model, Authentication authentication) throws Exception {

        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        Member member = customUser.getMember();

        Board board = new Board();

        board.setWriter(member.getUserId());   // 등록 시 id를 미리 입력해 준다.

        model.addAttribute(board);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_MEMBER')")
    public String register(Board board, RedirectAttributes rttr) throws Exception {
        service.register(board);

        rttr.addFlashAttribute("msg", "SUCCESS");
        return "redirect:/board/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void list(Model model) throws Exception {
        model.addAttribute("list", service.list());
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public void read(int boardNo, Model model) throws Exception {
        model.addAttribute(service.read(boardNo));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    public String remove(int boardNo, RedirectAttributes rttr) throws Exception {
        service.remove(boardNo);

        rttr.addFlashAttribute("msg", "SUCCESS");

        return "redirect:/board/list";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    public void modifyForm(int boardNo, Model model) throws Exception {
        model.addAttribute(service.read(boardNo));
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    public String modify(Board board, RedirectAttributes rttr) throws Exception {
        service.modify(board);
        rttr.addFlashAttribute("msg", "SUCCESS");

        return "redirect:/board/list";
    }

}
