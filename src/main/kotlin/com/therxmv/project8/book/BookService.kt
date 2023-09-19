package com.therxmv.project8.book

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {
    fun all(): Iterable<BookEntity> = bookRepository.findAll()

    fun get(id: Int): BookEntity? = bookRepository.findByIdOrNull(id)

    fun add(book: BookEntity): BookEntity = bookRepository.save(book)

    fun edit(id: Int, book: BookEntity): BookEntity = bookRepository.save(book.copy(id = id))

    fun remove(id: Int) = bookRepository.deleteById(id)
}