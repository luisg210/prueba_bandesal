package com.luis.Prueba_bandesal.Repository;

import com.luis.Prueba_bandesal.Entity.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends CrudRepository<Blog, Long> {
}
