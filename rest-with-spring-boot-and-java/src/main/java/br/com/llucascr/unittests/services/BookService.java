package br.com.llucascr.unittests.services;

import br.com.llucascr.data.dto.BooksDTO;
import br.com.llucascr.exception.RequiredObjectIsNullException;
import br.com.llucascr.exception.ResourceNotFoundException;
import br.com.llucascr.model.Books;
import br.com.llucascr.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.llucascr.mapper.ObjectMapper.parseObject;
import static br.com.llucascr.mapper.ObjectMapper.parseListObjects;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<BooksDTO> findAll() {
        return parseListObjects(repository.findAll(), BooksDTO.class);
    }

    public BooksDTO findById(Long id) {
        Books book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        return parseObject(book, BooksDTO.class);
    }

    public BooksDTO create(BooksDTO dto) {
        if (repository.findById(dto.getId()).isPresent())
            throw new ResourceNotFoundException("Books aready exist");

        Books books = new Books();
        books.setAuthor(dto.getAuthor());
        books.setLaunch_date(dto.getLaunch_date());
        books.setPrice(dto.getPrice());
        books.setTitle(dto.getTitle());

        return parseObject(books, BooksDTO.class);
    }



}
