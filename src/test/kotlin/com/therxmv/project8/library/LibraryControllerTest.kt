package com.therxmv.project8.library

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class LibraryControllerTest {

    private val mockNotFoundEntity = { id: Int -> ResponseEntity("Item with id $id not found", HttpStatus.NOT_FOUND) }
    private val mockOkEntity = { entity: Any -> ResponseEntity(entity, HttpStatus.OK) }
    private val mockInternalErrorEntity = { e: Exception -> ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR) }

    private val mockException: IllegalArgumentException = mock()
    private val mockLibraryEntity: LibraryEntity = mock()
    private val mockLibraryService: LibraryService = mock()

    private val systemUnderTest = LibraryController(mockLibraryService)

    @Test
    fun `get all items from db`() {
        systemUnderTest.getAll()

        verify(mockLibraryService).getAll()
    }

    @Test
    fun `getAll returns internal error when exception thrown`() {
        `when`(mockLibraryService.getAll()).thenThrow(mockException::class.java)

        assertEquals(systemUnderTest.getAll(), mockInternalErrorEntity(mockException))
    }

    @Test
    fun `getByIndex returns NOT_FOUND when item not found`() {
        val id = -1

        `when`(mockLibraryService.getByIndex(id)).thenReturn(null)

        assertEquals(systemUnderTest.getByIndex(id), mockNotFoundEntity(id))
    }

    @Test
    fun `getByIndex returns OK when item found`() {
        val id = 1

        `when`(mockLibraryService.getByIndex(id)).thenReturn(mockLibraryEntity)

        assertEquals(systemUnderTest.getByIndex(id), mockOkEntity(mockLibraryEntity))
    }

    @Test
    fun `add item to the db`() {
        systemUnderTest.add(mockLibraryEntity)

        verify(mockLibraryService).add(mockLibraryEntity)
    }

    @Test
    fun `add returns internal error when exception thrown`() {
        `when`(mockLibraryService.add(mockLibraryEntity)).thenThrow(mockException::class.java)

        assertEquals(systemUnderTest.add(mockLibraryEntity), mockInternalErrorEntity(mockException))
    }

    @Test
    fun `put item to the db`() {
        systemUnderTest.update(1, mockLibraryEntity)

        verify(mockLibraryService).update(1, mockLibraryEntity)
    }

    @Test
    fun `update returns internal error when exception thrown`() {
        `when`(mockLibraryService.update(1, mockLibraryEntity)).thenThrow(mockException::class.java)

        assertEquals(systemUnderTest.update(1, mockLibraryEntity), mockInternalErrorEntity(mockException))
    }

    @Test
    fun `update returns NOT_FOUND when library is null when patch`() {
        val id = -1

        `when`(mockLibraryService.getByIndex(id)).thenReturn(null)

        assertEquals(systemUnderTest.update(id, emptyMap()), mockNotFoundEntity(id))
    }

    @Test
    fun `patch item in the db`() {
        val id = 1

        `when`(mockLibraryService.getByIndex(id)).thenReturn(mockLibraryEntity)

        systemUnderTest.update(id, emptyMap())

        verify(mockLibraryService).update(mockLibraryEntity)
    }

    @Test
    fun `delete item if item is not null`() {
        val id = -1

        `when`(mockLibraryService.getByIndex(id)).thenReturn(null)

        assertEquals(systemUnderTest.remove(id), mockNotFoundEntity(id))
    }

    @Test
    fun `don't delete item if item is null`() {
        val id = 1

        `when`(mockLibraryService.getByIndex(id)).thenReturn(mockLibraryEntity)

        assertEquals(systemUnderTest.remove(id), mockOkEntity("Item with id $id deleted"))
    }
}