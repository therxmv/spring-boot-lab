package com.therxmv.project8.book

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {
    fun getAll(): Iterable<BookEntity> = bookRepository.findAll()

    fun getByIndex(id: Int): BookEntity? = bookRepository.findByIdOrNull(id)

    fun add(book: BookEntity): BookEntity = bookRepository.save(book)

    fun update(id: Int, book: BookEntity): BookEntity = bookRepository.save(book.copy(id = id))

    fun update(book: BookEntity): BookEntity = bookRepository.save(book)

    fun remove(id: Int) = bookRepository.deleteById(id)
}