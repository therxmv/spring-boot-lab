package com.therxmv.project8.book

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class BookServiceTest {

    private val mockBookEntity: BookEntity = Mockito.mock()
    private val mockBookRepository: BookRepository = Mockito.mock()

    private val systemUnderTest = BookService(mockBookRepository)

    @Test
    fun `get all items`() {
        Mockito.`when`(mockBookRepository.findAll()).thenReturn(listOf(mockBookEntity))

        assertEquals(systemUnderTest.getAll(), listOf(mockBookEntity))
    }

    @Test
    fun `add an item`() {
        Mockito.`when`(mockBookRepository.save(mockBookEntity)).thenReturn(mockBookEntity)

        assertEquals(systemUnderTest.add(mockBookEntity), mockBookEntity)
    }

    @Test
    fun `update an item`() {
        val id = 1

        Mockito.`when`(mockBookRepository.save(mockBookEntity.copy(id = id))).thenReturn(mockBookEntity)

        assertEquals(systemUnderTest.update(id, mockBookEntity), mockBookEntity)
    }

    @Test
    fun `test updating a library`() {
        Mockito.`when`(mockBookRepository.save(mockBookEntity)).thenReturn(mockBookEntity)

        assertEquals(systemUnderTest.update(mockBookEntity), mockBookEntity)
    }

    @Test
    fun `remove an item`() {
        systemUnderTest.remove(1)

        Mockito.verify(mockBookRepository).deleteById(1)
    }
}