package com.springboot.rest.controller;

import com.springboot.rest.constants.CommonConstants;
import com.springboot.rest.entity.myspringappdb.Book;
import com.springboot.rest.repository.myspringappdb.BookRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.history.Revisions;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = CommonConstants.BOOK_SVC_PATH)
@RequiredArgsConstructor
@RestController
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(CommonConstants.API_PATH + CommonConstants.BOOK_SVC_PATH)
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @GetMapping("/findAllRevisions/{bookId}")
    public Revisions<Integer, Book> findAllRevisionsByBookId(@PathVariable Integer bookId){
        return bookRepository.findRevisions(bookId);
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Integer id){
        bookRepository.deleteById(id);
        return "Book deleted";
    }
}
