package com.therxmv.project8.genre

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.therxmv.project8.book.BookEntity
import com.therxmv.project8.library.LibraryDTO
import com.therxmv.project8.library.LibraryEntity
import com.therxmv.project8.library.toDTO
import com.therxmv.project8.utils.Tables
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = Tables.GENRE_TABLE)
data class GenreEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    val name: String,

    @JsonIgnore
    @ManyToOne
    val libraries: LibraryEntity,

    @OneToMany(mappedBy = Tables.GENRE_TABLE, cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val books: List<BookEntity> = emptyList()
)

fun GenreEntity.toDTO() = GenreDTO(
    this.id,
    this.name,
    this.libraries.toDTO(),
)

fun GenreEntity.toREST() = GenreREST(
    this.id,
    this.name,
    this.libraries.toDTO(),
    this.books,
)

data class GenreDTO(
    val id: Int,
    val name: String,
    val library: LibraryDTO,
)

data class GenreREST(
    val id: Int,
    val name: String,
    val library: LibraryDTO,
    val books: List<BookEntity>,
)