package com.wang.controller;

import com.wang.pojo.Tag;
import com.wang.pojo.Type;
import com.wang.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagsController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String toTagsPage(Model model, @PageableDefault(size = 10) Pageable pageable){
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String addInput(Model model){
        model.addAttribute("tag",new Type());
        return "admin/tag-input";
    }

    @PostMapping("/tags")
    public String addTags(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        if (tagService.getTagByname(tag.getName())!=null){
            result.rejectValue("name","nameError","不能新增重复标签");
            return "admin/tag-input";
        }
        Tag t=tagService.saveTag(tag);

        if (result.hasErrors()){
            return "admin/tag-input";
        }
        if (t==null){
            attributes.addFlashAttribute("message","操作失败");
        }
        else{
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tag-input";

    }

    @PostMapping("/tags/{id}")
    public String editTypes(@Valid Tag tag, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        if (tagService.getTagByname(tag.getName())!=null){
            result.rejectValue("name","nameError","已经存在该标签");
            return "admin/tag-input";
        }
        if (result.hasErrors()){
            return "admin/tag-input";
        }
        Tag t= tagService.updateTag(id,tag);
        if (t==null){
            attributes.addFlashAttribute("message","操作失败");
        }
        else{
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delet(@PathVariable("id") Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","操作成功");
        return "redirect:/admin/tags";
    }
}
