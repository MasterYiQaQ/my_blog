package com.wang.controller;

import com.wang.pojo.Blog;
import com.wang.pojo.User;
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
import org.springframework.util.RouteMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.Lob;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String toBlogs(Model model, @PageableDefault(size = 10,sort = {"updatedate"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(Model model, @PageableDefault(size = 10,sort = {"updatedate"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "admin/blogs::blogList";
    }

    @GetMapping("/blogs/input")
    public String toAddBlogs(Model model){
        model.addAttribute("blog",new Blog());
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        return "admin/blog-input";
    }

    @PostMapping("/blogs")
    public String addBlogs(Blog blog, HttpSession session, RedirectAttributes attributes){
        blog.setUser((User)session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getIds()));
        Blog b;
        if (blog.getId()==null){
            b = blogService.saveBlog(blog);
        }else{
            b = blogService.updateBlog(blog.getId(),blog);
        }

        if (b == null){
            attributes.addAttribute("message","操作失败");
        }
        else{
            attributes.addAttribute("message","操作成功");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("blogs/{id}/input")
    public String toeditInput(Model model, @PathVariable("id")Long id){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return "admin/blog-input";
    }
    @GetMapping("blogs/{id}/delete")
    public String delete(@PathVariable("id")Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addAttribute("message","操作成功");
        return "redirect:/admin/blogs";
    }


}
