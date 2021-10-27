package com.wang.controller;

import com.wang.NotFoundException;
import com.wang.pojo.Blog;
import com.wang.service.BlogService;
import com.wang.service.TagService;
import com.wang.service.TypeService;
import com.wang.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size=8,sort = "updatedate",direction = Sort.Direction.DESC)Pageable pageable, Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(6));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(4));
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String toBlog(@PathVariable("id") Long id,Model model){
        Blog b = blogService.getBlog(id);
        String content = MarkdownUtils.markdownToHtml(b.getContent());
        model.addAttribute("blog",b);
        model.addAttribute("content",content);
        return "blog";
    }

    @GetMapping("/about")
    public String toAbout(){
        return "about";
    }


}
