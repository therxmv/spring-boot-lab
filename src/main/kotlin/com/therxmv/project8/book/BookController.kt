package com.therxmv.project8.book

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
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
    private val logger = LoggerFactory.getLogger(BookController::class.java)

    @GetMapping
    fun getAll() = try {
        ResponseEntity(bookService.getAll(), HttpStatus.OK)
    } catch (e: Exception) {
        logger.error(e.stackTraceToString())
        ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @GetMapping("{id}")
    fun getByIndex(@PathVariable id: Int) = try {
        bookService.getByIndex(id)?.let {
            ResponseEntity(it, HttpStatus.OK)
        } ?: ResponseEntity("Item with id $id not found", HttpStatus.NOT_FOUND)
    } catch (e: Exception) {
        logger.error(e.stackTraceToString())
        ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @PostMapping
    fun add(@RequestBody book: BookEntity) = try {
        ResponseEntity(bookService.add(book), HttpStatus.OK)
    } catch (e: Exception) {
        logger.error(e.stackTraceToString())
        ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @PutMapping("{id}")
    fun update(@PathVariable id: Int, @RequestBody book: BookEntity) = try {
        ResponseEntity(bookService.update(id, book), HttpStatus.OK)
    } catch (e: Exception) {
        logger.error(e.stackTraceToString())
        ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @PatchMapping("{id}")
    fun update(@PathVariable id: Int, @RequestBody partialBook: Map<String, Any>): ResponseEntity<Any> {
        return try {
            var genre = bookService.getByIndex(id) ?: return ResponseEntity("Item with id $id not found", HttpStatus.NOT_FOUND)

            partialBook.forEach { (key, value) ->
                when(key) {
                    "name" -> genre = genre.copy(name = value as String)
                    "author" -> genre = genre.copy(author = value as String)
                    "pages" -> genre = genre.copy(pages = value as Int)
                    "genreId" -> genre = genre.copy(genreId = value as Int)
                }
            }

            ResponseEntity(bookService.update(genre), HttpStatus.OK)
        } catch (e: Exception) {
            logger.error(e.stackTraceToString())
            ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("{id}")
    fun remove(@PathVariable id: Int) = try {
        if(bookService.getByIndex(id) != null) {
            bookService.remove(id)
            ResponseEntity("Item with id $id deleted", HttpStatus.OK)
        } else ResponseEntity("Item with id $id not found", HttpStatus.NOT_FOUND)
    } catch (e: Exception) {
        logger.error(e.stackTraceToString())
        ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}