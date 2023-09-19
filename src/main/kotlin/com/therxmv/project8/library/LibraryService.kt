package com.therxmv.project8.library

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LibraryService(
    private val libraryRepository: LibraryRepository
) {
    fun all(): Iterable<LibraryEntity> = libraryRepository.findAll()

    fun get(id: Int): LibraryEntity? = libraryRepository.findByIdOrNull(id)

    fun add(library: LibraryEntity): LibraryEntity = libraryRepository.save(library)

    fun edit(id: Int, library: LibraryEntity): LibraryEntity = libraryRepository.save(library.copy(id = id))

    fun remove(id: Int) = libraryRepository.deleteById(id)
}