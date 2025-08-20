package com.luis.Prueba_bandesal.Services;

import com.luis.Prueba_bandesal.Entity.Blog;
import com.luis.Prueba_bandesal.Entity.Reader;
import com.luis.Prueba_bandesal.Repository.BlogRepository;
import com.luis.Prueba_bandesal.Repository.ReaderRpository;
import com.luis.Prueba_bandesal.Services.Interface.BlogServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BlogService implements BlogServiceInterface {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private ReaderRpository readerRpository;


    @Override
    @Transactional(readOnly = true)
    public List<Blog> findAllBlogs() {
        return (List<Blog>) this.blogRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Blog> findBlogById(Long id) {
        return this.blogRepository.findById(id);
    }

    @Override
    @Transactional()
    public Blog saveBlog(Blog blog) {
        Set<Reader> selectedReaders = new HashSet<>(
                (Collection) readerRpository.findAllById(
                        blog.getReaders().stream().map(Reader::getId).toList()
                )
        );

        blog.setReaders(selectedReaders);

        return this.blogRepository.save(blog);
    }

    @Override
    @Transactional
    public void deleteBlogById(Long id) {
        this.blogRepository.deleteById(id);
    }
}
