package com.books.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.books.app.entity.Book;
import com.books.app.exception.ResourseNotFoundException;
import com.books.app.repository.BooksRepository;


@RestController()
@RequestMapping("/api/")
public class BooksController {

	@Autowired
	private BooksRepository repository;
	
	@GetMapping("/books")
	public List<Book> getAll() {
		return this.repository.findAll();
	}
	
	@PostMapping("/books")
    public Book addBook(@RequestBody Book book){
        return this.repository.save(book);
    }
	
	@GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook( @PathVariable Long id){
        Book book = this.repository.findById(id)
        .orElseThrow(()->new ResourseNotFoundException(this.builExceptionMessage(id)));
        return ResponseEntity.ok(book);
    }
    
    private String builExceptionMessage(Long id)
    {
        return "Resource book with id "+id+" not exists";
    }
    
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book)
    throws ResourseNotFoundException
    {
        Book oldBook = this.repository.findById(id)
        .orElseThrow(()->new ResourseNotFoundException(this.builExceptionMessage(id)));
        if(!book.getTitle().isEmpty())
        	oldBook.setTitle(book.getTitle());
        if(!book.getAuthor().isEmpty())
        	oldBook.setAuthor(book.getAuthor());
        this.repository.save(oldBook);
        return ResponseEntity.ok(oldBook);
    }

    @DeleteMapping("/Books/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id)
    throws ResourseNotFoundException
    {
        Book book = this.repository.findById(id)
        .orElseThrow(()->new ResourseNotFoundException(this.builExceptionMessage(id)));
        
        this.repository.delete(book);
        Map <String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok( response);
    }
}
