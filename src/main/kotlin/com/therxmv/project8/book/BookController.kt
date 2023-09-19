package com.therxmv.project8.book

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("books")
class BookController(
    private val bookService: BookService
) {
    @GetMapping
    fun index() = bookService.all().map { it.toDTO() }

    @PostMapping
    fun create(@RequestBody book: BookEntity) = bookService.add(book)

    @GetMapping("{id}")
    fun read(@PathVariable id: Int) = bookService.get(id)?.toDTO()

    @PutMapping("{id}")
    fun update(@PathVariable id: Int, @RequestBody book: BookEntity) = bookService.edit(id, book)

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Int) = bookService.remove(id)
}