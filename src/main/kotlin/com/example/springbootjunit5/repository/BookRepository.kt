package com.example.springbootjunit5.repository

import com.example.springbootjunit5.domain.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository: JpaRepository<Book, Long> {
}