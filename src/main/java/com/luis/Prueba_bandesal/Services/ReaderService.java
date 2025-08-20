package com.luis.Prueba_bandesal.Services;

import com.luis.Prueba_bandesal.Entity.Blog;
import com.luis.Prueba_bandesal.Entity.Reader;
import com.luis.Prueba_bandesal.Repository.ReaderRpository;
import com.luis.Prueba_bandesal.Services.Interface.ReaderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReaderService implements ReaderServiceInterface {

    @Autowired
    private ReaderRpository readerRpository;

    @Override
    @Transactional(readOnly = true)
    public List<Reader> findAllReaders() {
        return (List<Reader>) this.readerRpository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Reader> findReaderById(Long id) {
        return this.readerRpository.findById(id);
    }

    @Override
    @Transactional
    public Reader saveReader(Reader reader) {
        return this.readerRpository.save(reader);
    }

    @Override
    @Transactional
    public void deleteReaderById(Long id) {
        this.readerRpository.deleteById(id);
    }

}
