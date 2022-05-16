package com.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.books.app.entity.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Long>{

}
