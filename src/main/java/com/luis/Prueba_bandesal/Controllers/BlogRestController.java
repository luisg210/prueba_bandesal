package com.luis.Prueba_bandesal.Controllers;

import com.luis.Prueba_bandesal.Services.Interface.BlogServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/blogs")
@CrossOrigin(origins = "*")
public class BlogRestController {

    @Autowired
    private BlogServiceInterface blogService;

    @GetMapping("/")
    public ResponseEntity<?> getBlogs() {

        return ResponseEntity.ok(this.blogService.findAllBlogs());
    }
}