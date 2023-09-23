package com.therxmv.project8.genre

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class GenreControllerTest {
    private val mockNotFoundEntity = { id: Int -> ResponseEntity("Item with id $id not found", HttpStatus.NOT_FOUND) }
    private val mockOkEntity = { entity: Any -> ResponseEntity(entity, HttpStatus.OK) }
    private val mockInternalErrorEntity = { e: Exception -> ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR) }

    private val mockException: IllegalArgumentException = Mockito.mock()
    private val mockGenreEntity: GenreEntity = Mockito.mock()
    private val mockGenreService: GenreService = Mockito.mock()

    private val systemUnderTest = GenreController(mockGenreService)

    @Test
    fun `get all items from db`() {
        systemUnderTest.getAll()

        Mockito.verify(mockGenreService).getAll()
    }

    @Test
    fun `getAll returns internal error when exception thrown`() {
        Mockito.`when`(mockGenreService.getAll()).thenThrow(mockException::class.java)

        assertEquals(systemUnderTest.getAll(), mockInternalErrorEntity(mockException))
    }

    @Test
    fun `getByIndex returns NOT_FOUND when item not found`() {
        val id = -1

        Mockito.`when`(mockGenreService.getByIndex(id)).thenReturn(null)

        assertEquals(systemUnderTest.getByIndex(id), mockNotFoundEntity(id))
    }

    @Test
    fun `getByIndex returns OK when item found`() {
        val id = 1

        Mockito.`when`(mockGenreService.getByIndex(id)).thenReturn(mockGenreEntity)

        assertEquals(systemUnderTest.getByIndex(id), mockOkEntity(mockGenreEntity))
    }

    @Test
    fun `add item to the db`() {
        systemUnderTest.add(mockGenreEntity)

        Mockito.verify(mockGenreService).add(mockGenreEntity)
    }

    @Test
    fun `add returns internal error when exception thrown`() {
        Mockito.`when`(mockGenreService.add(mockGenreEntity)).thenThrow(mockException::class.java)

        assertEquals(systemUnderTest.add(mockGenreEntity), mockInternalErrorEntity(mockException))
    }

    @Test
    fun `put item to the db`() {
        systemUnderTest.update(1, mockGenreEntity)

        Mockito.verify(mockGenreService).update(1, mockGenreEntity)
    }

    @Test
    fun `update returns internal error when exception thrown`() {
        Mockito.`when`(mockGenreService.update(1, mockGenreEntity)).thenThrow(mockException::class.java)

        assertEquals(systemUnderTest.update(1, mockGenreEntity), mockInternalErrorEntity(mockException))
    }

    @Test
    fun `update returns NOT_FOUND when library is null when patch`() {
        val id = -1

        Mockito.`when`(mockGenreService.getByIndex(id)).thenReturn(null)

        assertEquals(systemUnderTest.update(id, emptyMap()), mockNotFoundEntity(id))
    }

    @Test
    fun `patch item in the db`() {
        val id = 1

        Mockito.`when`(mockGenreService.getByIndex(id)).thenReturn(mockGenreEntity)

        systemUnderTest.update(id, emptyMap())

        Mockito.verify(mockGenreService).update(mockGenreEntity)
    }

    @Test
    fun `delete item if item is not null`() {
        val id = -1

        Mockito.`when`(mockGenreService.getByIndex(id)).thenReturn(null)

        assertEquals(systemUnderTest.remove(id), mockNotFoundEntity(id))
    }

    @Test
    fun `don't delete item if item is null`() {
        val id = 1

        Mockito.`when`(mockGenreService.getByIndex(id)).thenReturn(mockGenreEntity)

        assertEquals(systemUnderTest.remove(id), mockOkEntity("Item with id $id deleted"))
    }
}