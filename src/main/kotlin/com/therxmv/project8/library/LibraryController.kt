package com.therxmv.project8.library

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
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
    @GetMapping
    fun index() = libraryService.all()

    @PostMapping
    fun create(@RequestBody library: LibraryEntity) = libraryService.add(library)

    @GetMapping("{id}")
    fun read(@PathVariable id: Int) = libraryService.get(id)

    @PutMapping("{id}")
    fun update(@PathVariable id: Int, @RequestBody library: LibraryEntity) = libraryService.edit(id, library)

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Int) = libraryService.remove(id)
}