package com.example.imageshop.controller;


import com.example.imageshop.common.security.domain.CustomUser;
import com.example.imageshop.common.security.domain.PageRequest;
import com.example.imageshop.common.security.domain.Pagination;
import com.example.imageshop.domain.Board;
import com.example.imageshop.domain.CodeLabelValue;
import com.example.imageshop.domain.Member;
import com.example.imageshop.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

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

    /*
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void list(Model model) throws Exception {
        model.addAttribute("list", service.list());
    }
    */

    //페이징 요청 정보를 매개변수로 받고 다시 뷰에 전달한다.
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void list(@ModelAttribute("pgrq") PageRequest pageRequest, Model model) throws Exception {

       // 뷰에 페이징 처리를 한 게시글 목록을 전달한다.
        model.addAttribute("list", service.list(pageRequest));


        // 페이징 네비게이션 정보를 뷰에 전달한다.
        Pagination pagination = new Pagination();
        pagination.setPageRequest(pageRequest);

        //페이지 네비게이션 정보에 검색처리된 게시글 건수를 저장한다.
        pagination.setTotalCount(service.count(pageRequest));

        model.addAttribute("pagination", pagination);

        // 검색유형의 코드명과 코드값을 정의한다.
        List<CodeLabelValue> searchTypeCodeValueList = new ArrayList<CodeLabelValue>();
        searchTypeCodeValueList.add(new CodeLabelValue("n", "---"));
        searchTypeCodeValueList.add(new CodeLabelValue("t", "Title"));
        searchTypeCodeValueList.add(new CodeLabelValue("c", "Content"));
        searchTypeCodeValueList.add(new CodeLabelValue("w", "Writer"));
        searchTypeCodeValueList.add(new CodeLabelValue("tc", "Title OR Content"));
        searchTypeCodeValueList.add(new CodeLabelValue("cw", "Content OR Writer"));
        searchTypeCodeValueList.add(new CodeLabelValue("tcw", "Title OR Content OR Writer"));

        model.addAttribute("searchTypeCodeValueList", searchTypeCodeValueList);

    }

    /*
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public void read(int boardNo, Model model) throws Exception {
        model.addAttribute(service.read(boardNo));
    }
    */


    //페이징 요청 정보를 매개변수로 받고 다시 뷰에 전달한다.
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public void read(int boardNo, @ModelAttribute("pgrq") PageRequest pageRequest, Model model) throws Exception {

        // 조회한 게시글 상세정보를 뷰에 전달한다.
        Board board = service.read(boardNo);
        model.addAttribute(board);
    }

    /*
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    public String remove(int boardNo, RedirectAttributes rttr) throws Exception {
        service.remove(boardNo);

        rttr.addFlashAttribute("msg", "SUCCESS");

        return "redirect:/board/list";
    }
    */


    //페이징 요청 정보를 매개변수로 받고 다시 뷰에 전달한다.
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    public String remove(int boardNo, PageRequest pageRequest, RedirectAttributes rttr) throws Exception {
        service.remove(boardNo);


        //RedirectAttributes 객체에 일회성 데이터를 지정한여 전달한다.
       rttr.addAttribute("page", pageRequest.getPage());
       rttr.addAttribute("sizePerPage", pageRequest.getSizePerPage());

       // 검색유형과 검색어를 뷰에 전달한다.
       rttr.addAttribute("searchType", pageRequest.getSearchType());
       rttr.addAttribute("keyword", pageRequest.getKeyword());


       //검색유형과 검색어를 뷰에 전달한다.
       rttr.addFlashAttribute("msg", "SUCCESS");

        return "redirect:/board/list";
    }

    /*
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    public void modifyForm(int boardNo, Model model) throws Exception {
        model.addAttribute(service.read(boardNo));
    }
    */

    //페이징 요청 정보를 매개변수로 받고 다시 뷰에 전달한다.
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    public void modifyForm(int boardNo, @ModelAttribute("pgrq") PageRequest pageRequest, Model model) throws Exception {

       // 조회한 게시글 상세정보를 뷰에 전달한다.
        Board board = service.read(boardNo);

        model.addAttribute(board);
    }

    /*
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    public String modify(Board board, RedirectAttributes rttr) throws Exception {
        service.modify(board);
        rttr.addFlashAttribute("msg", "SUCCESS");

        return "redirect:/board/list";
    }
    */


    //페이징 요청 정보를 매개변수로 받고 다시 뷰에 전달한다.
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    public String modify(Board board, PageRequest pageRequest, RedirectAttributes rttr) throws Exception {

        service.modify(board);

        // RedirectAttributes 객체에 일회성 데이터를 지정하면 전달한다.
        rttr.addAttribute("page", pageRequest.getPage());
        rttr.addAttribute("sizePerPage", pageRequest.getSizePerPage());

        // 검색유형과 검색어를 뷰에 전달한다.
        rttr.addAttribute("searchType", pageRequest.getSearchType());
        rttr.addAttribute("keyword", pageRequest.getKeyword());


        rttr.addFlashAttribute("msg", "SUCCESS");

        return "redirect:/board/list";
    }
}
