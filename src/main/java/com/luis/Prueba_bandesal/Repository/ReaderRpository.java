package com.luis.Prueba_bandesal.Repository;

import com.luis.Prueba_bandesal.Entity.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRpository extends CrudRepository<Reader, Long> {

}
