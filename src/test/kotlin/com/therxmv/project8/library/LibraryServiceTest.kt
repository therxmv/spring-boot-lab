package com.therxmv.project8.library

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class LibraryServiceTest {

    private val mockLibraryEntity: LibraryEntity = mock()
    private val mockLibraryRepository: LibraryRepository = mock()

    private val systemUnderTest = LibraryService(mockLibraryRepository)

    @Test
    fun `get all items`() {
        `when`(mockLibraryRepository.findAll()).thenReturn(listOf(mockLibraryEntity))

        assertEquals(systemUnderTest.getAll(), listOf(mockLibraryEntity))
    }

    @Test
    fun `add an item`() {
        `when`(mockLibraryRepository.save(mockLibraryEntity)).thenReturn(mockLibraryEntity)

        assertEquals(systemUnderTest.add(mockLibraryEntity), mockLibraryEntity)
    }

    @Test
    fun `update an item`() {
        val id = 1

        `when`(mockLibraryRepository.save(mockLibraryEntity.copy(id = id))).thenReturn(mockLibraryEntity)

        assertEquals(systemUnderTest.update(id, mockLibraryEntity), mockLibraryEntity)
    }

    @Test
    fun `test updating a library`() {
        `when`(mockLibraryRepository.save(mockLibraryEntity)).thenReturn(mockLibraryEntity)

        assertEquals(systemUnderTest.update(mockLibraryEntity), mockLibraryEntity)
    }

    @Test
    fun `remove an item`() {
        systemUnderTest.remove(1)

        verify(mockLibraryRepository).deleteById(1)
    }
}