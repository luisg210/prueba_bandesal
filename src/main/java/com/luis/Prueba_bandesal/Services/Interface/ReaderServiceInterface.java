package com.luis.Prueba_bandesal.Services.Interface;


import com.luis.Prueba_bandesal.Entity.Reader;

import java.util.List;
import java.util.Optional;

public interface ReaderServiceInterface {

    List<Reader> findAllReaders();
    Optional<Reader> findReaderById(Long id);
    Reader saveReader(Reader reader);
    void deleteReaderById(Long id);

}
