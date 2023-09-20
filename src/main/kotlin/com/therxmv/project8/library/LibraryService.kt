package com.therxmv.project8.library

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LibraryService(
    private val libraryRepository: LibraryRepository
) {
    fun getAll(): Iterable<LibraryEntity> = libraryRepository.findAll()

    fun getByIndex(id: Int): LibraryEntity? = libraryRepository.findByIdOrNull(id)

    fun add(library: LibraryEntity): LibraryEntity = libraryRepository.save(library)

    fun update(id: Int, library: LibraryEntity): LibraryEntity = libraryRepository.save(library.copy(id = id))

    fun update(library: LibraryEntity): LibraryEntity = libraryRepository.save(library)

    fun remove(id: Int) = libraryRepository.deleteById(id)
}