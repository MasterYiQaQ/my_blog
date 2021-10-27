package com.wang.controller;

import com.wang.service.BlogService;
import com.wang.service.TagService;
import com.wang.service.TypeService;
import com.wang.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TagShowController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @GetMapping("/tag")
    public String toTag(@PageableDefault(size=8,sort = "updatedate",direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("tags",tagService.listTag());
        return "tags";
    }

    @GetMapping("/tag/{id}")
    public String toOneTag(@PageableDefault(size=8,sort = "updatedate",direction = Sort.Direction.DESC)Pageable pageable, @PathVariable("id")Long id, Model model){
        model.addAttribute("page",blogService.listBlog(id, pageable));
        model.addAttribute("tags",tagService.listTag());
        return "tags";
    }
}
