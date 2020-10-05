package com.example.imageshop.controller;

import com.example.imageshop.domain.CodeGroup;
import com.example.imageshop.service.CodeGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/codegroup")
public class CodeGroupController {

    @Autowired
    private CodeGroupService service;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void registerForm(Model model) throws Exception{

        CodeGroup codeGroup = new CodeGroup();
        model.addAttribute(codeGroup);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(CodeGroup codeGroup, RedirectAttributes rttr) throws Exception {
        service.register(codeGroup);

        rttr.addFlashAttribute("msg", "SUCCESS");
        return "redirect:/codegroup/list";
    }
}
