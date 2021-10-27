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
public class TypeShowController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/type")
    public String toType(@PageableDefault(size=8,sort = "updatedate",direction = Sort.Direction.DESC)Pageable pageable, Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listType());
        return "types";
    }

    @GetMapping("/type/{id}")
    public String toOneType(@PageableDefault(size=8,sort = "updatedate",direction = Sort.Direction.DESC)Pageable pageable,@PathVariable("id")Long id, Model model){
        BlogQuery b = new BlogQuery();
        b.setTypeId(id);
        model.addAttribute("page",blogService.listBlog(pageable,b));
        model.addAttribute("types",typeService.listType());
        return "types";
    }



}
