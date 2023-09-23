package com.therxmv.project8.book

import com.therxmv.project8.genre.GenreService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class BookControllerTest {
    private val mockNotFoundEntity = { id: Int -> ResponseEntity("Item with id $id not found", HttpStatus.NOT_FOUND) }
    private val mockOkEntity = { entity: Any -> ResponseEntity(entity, HttpStatus.OK) }
    private val mockInternalErrorEntity = { e: Exception -> ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR) }

    private val mockException: IllegalArgumentException = Mockito.mock()
    private val mockBookEntity: BookEntity = Mockito.mock()
    private val mockBookService: BookService = Mockito.mock()

    private val systemUnderTest = BookController(mockBookService)

    @Test
    fun `get all items from db`() {
        systemUnderTest.getAll()

        Mockito.verify(mockBookService).getAll()
    }

    @Test
    fun `getAll returns internal error when exception thrown`() {
        Mockito.`when`(mockBookService.getAll()).thenThrow(mockException::class.java)

        assertEquals(systemUnderTest.getAll(), mockInternalErrorEntity(mockException))
    }

    @Test
    fun `getByIndex returns NOT_FOUND when item not found`() {
        val id = -1

        Mockito.`when`(mockBookService.getByIndex(id)).thenReturn(null)

        assertEquals(systemUnderTest.getByIndex(id), mockNotFoundEntity(id))
    }

    @Test
    fun `getByIndex returns OK when item found`() {
        val id = 1

        Mockito.`when`(mockBookService.getByIndex(id)).thenReturn(mockBookEntity)

        assertEquals(systemUnderTest.getByIndex(id), mockOkEntity(mockBookEntity))
    }

    @Test
    fun `add item to the db`() {
        systemUnderTest.add(mockBookEntity)

        Mockito.verify(mockBookService).add(mockBookEntity)
    }

    @Test
    fun `add returns internal error when exception thrown`() {
        Mockito.`when`(mockBookService.add(mockBookEntity)).thenThrow(mockException::class.java)

        assertEquals(systemUnderTest.add(mockBookEntity), mockInternalErrorEntity(mockException))
    }

    @Test
    fun `put item to the db`() {
        systemUnderTest.update(1, mockBookEntity)

        Mockito.verify(mockBookService).update(1, mockBookEntity)
    }

    @Test
    fun `update returns internal error when exception thrown`() {
        Mockito.`when`(mockBookService.update(1, mockBookEntity)).thenThrow(mockException::class.java)

        assertEquals(systemUnderTest.update(1, mockBookEntity), mockInternalErrorEntity(mockException))
    }

    @Test
    fun `update returns NOT_FOUND when library is null when patch`() {
        val id = -1

        Mockito.`when`(mockBookService.getByIndex(id)).thenReturn(null)

        assertEquals(systemUnderTest.update(id, emptyMap()), mockNotFoundEntity(id))
    }

    @Test
    fun `patch item in the db`() {
        val id = 1

        Mockito.`when`(mockBookService.getByIndex(id)).thenReturn(mockBookEntity)

        systemUnderTest.update(id, emptyMap())

        Mockito.verify(mockBookService).update(mockBookEntity)
    }

    @Test
    fun `delete item if item is not null`() {
        val id = -1

        Mockito.`when`(mockBookService.getByIndex(id)).thenReturn(null)

        assertEquals(systemUnderTest.remove(id), mockNotFoundEntity(id))
    }

    @Test
    fun `don't delete item if item is null`() {
        val id = 1

        Mockito.`when`(mockBookService.getByIndex(id)).thenReturn(mockBookEntity)

        assertEquals(systemUnderTest.remove(id), mockOkEntity("Item with id $id deleted"))
    }
}