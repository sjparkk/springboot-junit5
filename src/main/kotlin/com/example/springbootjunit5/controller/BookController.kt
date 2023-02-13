package com.example.springbootjunit5.controller

import com.example.springbootjunit5.service.BookService
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController(
    private val bookService: BookService
) {

}