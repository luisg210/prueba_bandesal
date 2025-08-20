package com.luis.Prueba_bandesal.Services.Interface;

import com.luis.Prueba_bandesal.Entity.Blog;
import com.luis.Prueba_bandesal.Entity.Reader;

import java.util.List;
import java.util.Optional;

public interface BlogServiceInterface {

    List<Blog> findAllBlogs();
    Optional<Blog> findBlogById(Long id);
    Blog saveBlog(Blog blog);
    void deleteBlogById(Long id);

}
