package br.com.llucascr.services;

import br.com.llucascr.data.dto.BooksDTO;
import br.com.llucascr.data.dto.PersonDTO;
import br.com.llucascr.model.Books;
import br.com.llucascr.model.Person;
import br.com.llucascr.repository.BookRepository;
import br.com.llucascr.unitetests.mapper.mocks.MockBooks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    private MockBooks input;

    @InjectMocks
    private BookService service;

    @Mock
    private BookRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockBooks();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() throws ParseException {
        List<Books> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);

        List<BooksDTO> listDTO = service.findAll();

        assertNotNull(listDTO);
        assertEquals(14, listDTO.size());

        BooksDTO bookOne = listDTO.get(1);

        assertEquals("Author1", bookOne.getAuthor());
        assertEquals(new BigDecimal("0.1"), bookOne.getPrice());
        assertEquals("title1", bookOne.getTitle());
        assertNotNull(bookOne.getLaunch_date());

        BooksDTO bookFour = listDTO.get(4);

        assertEquals("Author4", bookFour.getAuthor());
        assertEquals(new BigDecimal("0.1"), bookFour.getPrice());
        assertEquals("title4", bookFour.getTitle());
        assertNotNull(bookFour.getLaunch_date());

        BooksDTO bookSeven = listDTO.get(7);

        assertEquals("Author7", bookSeven.getAuthor());
        assertEquals(new BigDecimal("0.1"), bookSeven.getPrice());
        assertEquals("title7", bookSeven.getTitle());
        assertNotNull(bookSeven.getLaunch_date());

    }

    @Test
    void findById() {
        Books books = input.mockEntity(1);
        books.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(books));

        BooksDTO dto = service.findById(1L);

        assertNotNull(dto);
        assertNotNull(dto.getId());

        assertEquals("Author1", dto.getAuthor());
        assertEquals(new BigDecimal("0.1"), dto.getPrice());
        assertEquals("title1", dto.getTitle());
        assertNotNull(dto.getLaunch_date());
    }

//    @Test
//    void create() {
//        Books books = input.mockEntity(1);
//        Books persisted = books;
//        persisted.setId(1L);
//
//        BooksDTO dto = input.mockDTO(1);
//
//        when(repository.save(books)).thenReturn(persisted);
//
//        BooksDTO result = service.create(dto);
//
//        assertNotNull(result);
//        assertNotNull(result.getId());
//
//
//        assertEquals("Author1", result.getAuthor());
//        assertEquals(new BigDecimal("0.1"), result.getPrice());
//        assertEquals("title1", result.getTitle());
//        assertNotNull(result.getLaunch_date());
//
//    }

}