package com.example.imageshop.controller;


import com.example.imageshop.domain.Notice;
import com.example.imageshop.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService service;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void registerForm(Model model) throws Exception {
        Notice notice = new Notice();

        model.addAttribute(notice);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String register(Notice notice, RedirectAttributes rttr) throws Exception {
        service.register(notice);

        rttr.addFlashAttribute("msg", "SUCCESS");
        return "redirect:/notice/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void list(Model model) throws Exception {
        model.addAttribute("list", service.list());
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public void read(int noticeNo, Model model) throws Exception {
        model.addAttribute(service.read(noticeNo));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String remove(int noticeNo, RedirectAttributes rttr) throws Exception {
        service.remove(noticeNo);

        rttr.addFlashAttribute("msg", "SUCCESS");

        return "redirect:/notice/list";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void modifyForm(int noticeNo, Model model) throws Exception {
        model.addAttribute(service.read(noticeNo));
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String modify(Notice notice, RedirectAttributes rttr) throws Exception {
        service.modify(notice);
        rttr.addFlashAttribute("msg", "SUCCESS");

        return "redirect:/notice/list";
    }
}
