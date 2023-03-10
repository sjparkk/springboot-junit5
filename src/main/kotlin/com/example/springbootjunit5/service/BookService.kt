package com.example.springbootjunit5.service

import com.example.springbootjunit5.domain.Book
import com.example.springbootjunit5.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(
    private val bookRepository: BookRepository
) {

    @Transactional
    fun saveBook(book: Book): Book {
        return bookRepository.save(book)
    }

    @Transactional(readOnly = true)
    fun getBooks(): List<Book> {
        return bookRepository.findAll()
    }

    @Transactional(readOnly = true)
    fun getBook(id: Long): Book {
        return bookRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Book id를 확인해주세요.") }
    }


}