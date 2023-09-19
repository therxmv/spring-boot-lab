package com.therxmv.project8.genre

import com.therxmv.project8.library.toDTO
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
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
    @GetMapping
    fun index() = genreService.all().map { it.toREST() }

    @PostMapping
    fun create(@RequestBody genre: GenreEntity) = genreService.add(genre)

    @GetMapping("{id}")
    fun read(@PathVariable id: Int) = genreService.get(id)?.toREST()

    @PutMapping("{id}")
    fun update(@PathVariable id: Int, @RequestBody genre: GenreEntity) = genreService.edit(id, genre)

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Int) = genreService.remove(id)
}