package com.therxmv.project8.genre

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class GenreServiceTest {

    private val mockGenreEntity: GenreEntity = Mockito.mock()
    private val mockGenreRepository: GenreRepository = Mockito.mock()

    private val systemUnderTest = GenreService(mockGenreRepository)

    @Test
    fun `get all items`() {
        Mockito.`when`(mockGenreRepository.findAll()).thenReturn(listOf(mockGenreEntity))

        assertEquals(systemUnderTest.getAll(), listOf(mockGenreEntity))
    }

    @Test
    fun `add an item`() {
        Mockito.`when`(mockGenreRepository.save(mockGenreEntity)).thenReturn(mockGenreEntity)

        assertEquals(systemUnderTest.add(mockGenreEntity), mockGenreEntity)
    }

    @Test
    fun `update an item`() {
        val id = 1

        Mockito.`when`(mockGenreRepository.save(mockGenreEntity.copy(id = id))).thenReturn(mockGenreEntity)

        assertEquals(systemUnderTest.update(id, mockGenreEntity), mockGenreEntity)
    }

    @Test
    fun `test updating a library`() {
        Mockito.`when`(mockGenreRepository.save(mockGenreEntity)).thenReturn(mockGenreEntity)

        assertEquals(systemUnderTest.update(mockGenreEntity), mockGenreEntity)
    }

    @Test
    fun `remove an item`() {
        systemUnderTest.remove(1)

        Mockito.verify(mockGenreRepository).deleteById(1)
    }
}