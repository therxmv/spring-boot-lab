package com.therxmv.project8.book

import com.therxmv.project8.utils.Tables
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = Tables.BOOK_TABLE)
data class BookEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val name: String = "",
    val author: String = "",
    val pages: Int = 0,
    @Column(name = "genre_id") val genreId: Int = 0,
)