package com.wang.service;

import com.wang.pojo.Blog;
import com.wang.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlog(Long id);
    Blog saveBlog(Blog blog);
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);
    Page<Blog> listBlog(Pageable pageable);
    Blog updateBlog(Long id,Blog blog);
    Page<Blog> listBlog(Long id, Pageable pageable);
    Map<String,List<Blog>> archiveBlog();
    void deleteBlog(Long id);
    Long countBlog();
    List<Blog> listRecommendBlogTop(Integer size);
}
