package com.wang.controller;

import com.wang.pojo.Type;
import com.wang.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypesController {
    @Autowired
    private TypeService typeService;
    @GetMapping("/types")
    public String toTypesPage(Model model, @PageableDefault(size = 10)Pageable pageable){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }
    @PostMapping("/types")
    public String addTypes(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        if (typeService.getTypeByname(type.getName())!=null){
            result.rejectValue("name","nameError","不能新增重复分类");
            return "admin/type-input";
        }
        Type t=typeService.saveType(type);

        if (result.hasErrors()){
            return "admin/type-input";
        }
        if (t==null){
            attributes.addFlashAttribute("message","操作失败");
        }
        else{
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/types";
    }
    @GetMapping("/types/input")
    public String addInput(Model model){
        model.addAttribute("type",new Type());
        return "admin/type-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/type-input";

    }

    @PostMapping("/types/{id}")
    public String editTypes(@Valid Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        if (typeService.getTypeByname(type.getName())!=null){
            result.rejectValue("name","nameError","不能新增重复分类");
            return "admin/type-input";
        }
        if (result.hasErrors()){
            return "admin/type-input";
        }
        Type t= typeService.updateType(id,type);
        if (t==null){
            attributes.addFlashAttribute("message","操作失败");
        }
        else{
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delet(@PathVariable("id") Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","操作成功");
        return "redirect:/admin/types";
    }



}
