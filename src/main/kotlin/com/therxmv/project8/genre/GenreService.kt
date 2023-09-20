package com.therxmv.project8.genre

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class GenreService(
    private val genreRepository: GenreRepository
) {
    fun getAll(): Iterable<GenreEntity> = genreRepository.findAll()

    fun getByIndex(id: Int): GenreEntity? = genreRepository.findByIdOrNull(id)

    fun add(genre: GenreEntity): GenreEntity = genreRepository.save(genre)

    fun update(id: Int, genre: GenreEntity): GenreEntity = genreRepository.save(genre.copy(id = id))

    fun update(genre: GenreEntity): GenreEntity = genreRepository.save(genre)

    fun remove(id: Int) = genreRepository.deleteById(id)
}