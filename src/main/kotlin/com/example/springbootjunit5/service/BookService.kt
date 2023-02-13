package com.example.springbootjunit5.service

import com.example.springbootjunit5.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {

}