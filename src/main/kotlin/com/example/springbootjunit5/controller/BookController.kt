package com.example.springbootjunit5.controller

import com.example.springbootjunit5.domain.Book
import com.example.springbootjunit5.service.BookService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class BookController(
    private val bookService: BookService
) {

    @PostMapping("/book")
    fun save(@RequestBody book: Book): ResponseEntity<*> {
        return ResponseEntity<Book>(bookService.saveBook(book), HttpStatus.CREATED)
    }

    @GetMapping("/book")
    fun findAll(): ResponseEntity<*> {
        return ResponseEntity<Any?>(bookService.getBooks(), HttpStatus.OK)
    }

    @GetMapping("/book/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<*> {
        return ResponseEntity<Book>(bookService.getBook(id), HttpStatus.OK)
    }

}