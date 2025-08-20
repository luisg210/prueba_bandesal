package com.luis.Prueba_bandesal.Controllers;

import com.luis.Prueba_bandesal.Entity.Blog;
import com.luis.Prueba_bandesal.Services.Interface.BlogServiceInterface;
import com.luis.Prueba_bandesal.Services.Interface.ReaderServiceInterface;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Objects;
import java.util.Optional;

import jakarta.validation.Valid;

@Log4j2
@Controller
@RequestMapping(value = "/blogs")
public class BlogController {

    @Autowired
    private BlogServiceInterface blogService;
    @Autowired
    private ReaderServiceInterface readerService;

    @GetMapping(value = {"/", "list", "home"})
    public String listsBlogs(Model model) {

        model.addAttribute("blogs", this.blogService.findAllBlogs());

        return "blogs";
    }

    @GetMapping(value = "/add_blog")
    public String addBlog(Model model) {

        model.addAttribute("blog", new Blog());
        model.addAttribute("readers", this.readerService.findAllReaders());

        return "add_blog";
    }

    @PostMapping(value = "/save_blog")
    public String saveBlog(@ModelAttribute("blog") @Valid Blog blog, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("blog", blog);

            return "add_blog";
        }

        try {

            Blog newBlog = this.blogService.saveBlog(blog);

            if (Objects.equals(newBlog, null)) {
                model.addAttribute("blog", blog);

                model.addAttribute("readers", this.readerService.findAllReaders());

                redirectAttributes.addFlashAttribute("msg", "Error al guardar");

                return "add_blog";
            }

        } catch (Exception ex) {
            log.error(String.valueOf(ex.getCause()));
        }

        redirectAttributes.addFlashAttribute("msg", "Guardado con exito");

        return "redirect:/blogs/list";
    }

    @GetMapping(value = "/update_blog/{id}")
    public String updateBlog(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {

        Optional<Blog> blogById = this.blogService.findBlogById(id);

        if (blogById.isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "Blog no encontrado");

            log.error("Blog no encontrado");

            return "redirect:/blogs/list";
        }

        model.addAttribute("blog", blogById.get());
        model.addAttribute("readers", this.readerService.findAllReaders());

        return "add_blog";
    }

    @GetMapping(value = "/delete_blog/{id}")
    public String deleteBlog(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Blog> blogById = this.blogService.findBlogById(id);

        if (blogById.isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "Blog no encontrado");

        } else {

            try {
                this.blogService.deleteBlogById(id);

                redirectAttributes.addFlashAttribute("msg", "Blog eliminado con exito");

            } catch (Exception ex) {
                ex.printStackTrace();
                log.error(ex.getCause(), ex.getCause());

                redirectAttributes.addFlashAttribute("msg", "Ocurrio un error");
            }
        }

        return "redirect:/blogs/list";
    }

    @GetMapping(value = "/blogs_readers")
    public String blogsReaders(Model model) {
        model.addAttribute("blogs", this.blogService.findAllBlogs());

        return "blogs_readers";
    }

}