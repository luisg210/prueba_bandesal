package com.luis.Prueba_bandesal.Controllers;

import com.luis.Prueba_bandesal.Entity.Blog;
import com.luis.Prueba_bandesal.Services.Interface.BlogServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/blogs")
@CrossOrigin(origins = "*")
public class BlogRestController {

    @Autowired
    private BlogServiceInterface blogService;

    @GetMapping(value = "/")
    public List<Blog> listAllBlogs() {
        List<Blog> blogs;

        try {
            blogs = this.blogService.findAllBlogs();

        } catch (Exception ex) {
            System.out.println(Arrays.toString(ex.getStackTrace()));
            log.error(ex.getMessage());

            blogs = new ArrayList<>();
        }

        return blogs;
    }

}