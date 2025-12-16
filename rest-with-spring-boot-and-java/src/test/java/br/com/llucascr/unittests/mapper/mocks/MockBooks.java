package br.com.llucascr.unittests.mapper.mocks;

import br.com.llucascr.data.dto.BooksDTO;
import br.com.llucascr.model.Books;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBooks {

    public Books mockEntity() {
        return mockEntity(0);
    }

    public BooksDTO mockDTO() {
        return mockDTO(0);
    }

    public List<Books> mockEntityList() {
        List<Books> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BooksDTO> mockDTOList() {
        List<BooksDTO> booksDTOS = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            booksDTOS.add(mockDTO(i));
        }
        return booksDTOS;
    }

    public Books mockEntity(Integer number) {
        Books books = new Books();
        books.setAuthor("Author" + number);
        books.setLaunch_date(new Date());
        books.setPrice(new BigDecimal("0.1"));
        books.setTitle("title" + number);
        return books;
    }

    public BooksDTO mockDTO(Integer number) {
        BooksDTO booksDTO = new BooksDTO();
        booksDTO.setAuthor("Author" + number);
        booksDTO.setLaunch_date(new Date());
        booksDTO.setPrice(new BigDecimal("0.1"));
        booksDTO.setTitle("title" + number);
        return booksDTO;
    }

}
