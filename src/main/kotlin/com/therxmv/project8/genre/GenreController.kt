package com.therxmv.project8.genre

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
@RequestMapping("genres")
class GenreController(
    private val genreService: GenreService
) {
    private val logger = LoggerFactory.getLogger(GenreController::class.java)

    @GetMapping
    fun getAll() = try {
        ResponseEntity(genreService.getAll(), HttpStatus.OK)
    } catch (e: Exception) {
        logger.error(e.stackTraceToString())
        ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @GetMapping("{id}")
    fun getByIndex(@PathVariable id: Int) = try {
        genreService.getByIndex(id)?.let {
            ResponseEntity(it, HttpStatus.OK)
        } ?: ResponseEntity("Item with id $id not found", HttpStatus.NOT_FOUND)
    } catch (e: Exception) {
        logger.error(e.stackTraceToString())
        ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @PostMapping
    fun add(@RequestBody genre: GenreEntity) = try {
        ResponseEntity(genreService.add(genre), HttpStatus.OK)
    } catch (e: Exception) {
        logger.error(e.stackTraceToString())
        ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @PutMapping("{id}")
    fun update(@PathVariable id: Int, @RequestBody genre: GenreEntity) = try {
        ResponseEntity(genreService.update(id, genre), HttpStatus.OK)
    } catch (e: Exception) {
        logger.error(e.stackTraceToString())
        ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @PatchMapping("{id}")
    fun update(@PathVariable id: Int, @RequestBody partialGenre: Map<String, Any>): ResponseEntity<Any> {
        return try {
            var genre = genreService.getByIndex(id) ?: return ResponseEntity("Item with id $id not found", HttpStatus.NOT_FOUND)

            partialGenre.forEach { (key, value) ->
                when(key) {
                    "name" -> genre = genre.copy(name = value as String)
                    "libraryId" -> genre = genre.copy(libraryId = value as Int)
                }
            }

            ResponseEntity(genreService.update(genre), HttpStatus.OK)
        } catch (e: Exception) {
            logger.error(e.stackTraceToString())
            ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("{id}")
    fun remove(@PathVariable id: Int) = try {
        if(genreService.getByIndex(id) != null) {
            genreService.remove(id)
            ResponseEntity("Item with id $id deleted", HttpStatus.OK)
        } else ResponseEntity("Item with id $id not found", HttpStatus.NOT_FOUND)
    } catch (e: Exception) {
        logger.error(e.stackTraceToString())
        ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}