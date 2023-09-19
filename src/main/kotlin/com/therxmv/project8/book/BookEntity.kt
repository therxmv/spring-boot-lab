package com.therxmv.project8.book

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.therxmv.project8.genre.GenreDTO
import com.therxmv.project8.genre.GenreEntity
import com.therxmv.project8.genre.toDTO
import com.therxmv.project8.utils.Tables
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = Tables.BOOK_TABLE)
data class BookEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    val title: String,

    @JsonIgnore
    @ManyToOne
    val genres: GenreEntity,

    val author: String,

    val pages: Int,
)

fun BookEntity.toDTO() = BookDTO(
    this.id,
    this.title,
    this.genres.toDTO(),
    this.author,
    this.pages,
)

data class BookDTO(
    val id: Int,
    val title: String,
    val genre: GenreDTO,
    val author: String,
    val pages: Int,
)