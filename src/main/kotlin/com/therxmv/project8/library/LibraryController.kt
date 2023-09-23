package com.therxmv.project8.library

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
@RequestMapping("libraries")
class LibraryController(
    private val libraryService: LibraryService
) {
    private val logger = LoggerFactory.getLogger(LibraryController::class.java)

    @GetMapping
    fun getAll() = try {
        ResponseEntity(libraryService.getAll(), HttpStatus.OK)
    } catch (e: Exception) {
        logger.error(e.stackTraceToString())
        ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @GetMapping("{id}")
    fun getByIndex(@PathVariable id: Int) = try {
        libraryService.getByIndex(id)?.let {
            ResponseEntity(it, HttpStatus.OK)
        } ?: ResponseEntity("Item with id $id not found", HttpStatus.NOT_FOUND)
    } catch (e: Exception) {
        logger.error(e.stackTraceToString())
        ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @PostMapping
    fun add(@RequestBody library: LibraryEntity) = try {
        ResponseEntity(libraryService.add(library), HttpStatus.OK)
    } catch (e: Exception) {
        logger.error(e.stackTraceToString())
        ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @PutMapping("{id}")
    fun update(@PathVariable id: Int, @RequestBody library: LibraryEntity) = try {
        ResponseEntity(libraryService.update(id, library), HttpStatus.OK)
    } catch (e: Exception) {
        logger.error(e.stackTraceToString())
        ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @PatchMapping("{id}")
    fun update(@PathVariable id: Int, @RequestBody partialLibrary: Map<String, Any>): ResponseEntity<Any> {
        return try {
            var library = libraryService.getByIndex(id) ?: return ResponseEntity("Item with id $id not found", HttpStatus.NOT_FOUND)

            partialLibrary.forEach { (key, value) ->
                when(key) {
                    "name" -> library = library.copy(name = value as String)
                }
            }

            ResponseEntity(libraryService.update(library), HttpStatus.OK)
        } catch (e: Exception) {
            logger.error(e.stackTraceToString())
            ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("{id}")
    fun remove(@PathVariable id: Int) = try {
        if(libraryService.getByIndex(id) != null) {
            libraryService.remove(id)
            ResponseEntity("Item with id $id deleted", HttpStatus.OK)
        } else ResponseEntity("Item with id $id not found", HttpStatus.NOT_FOUND)
    } catch (e: Exception) {
        logger.error(e.stackTraceToString())
        ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}