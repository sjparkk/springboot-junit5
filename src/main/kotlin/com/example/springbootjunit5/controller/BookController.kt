package com.example.springbootjunit5.controller

import com.example.springbootjunit5.domain.Book
import com.example.springbootjunit5.service.BookService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

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


}