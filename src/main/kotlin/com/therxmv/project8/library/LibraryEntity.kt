package com.therxmv.project8.library

import com.therxmv.project8.genre.GenreEntity
import com.therxmv.project8.utils.Tables
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = Tables.LIBRARY_TABLE)
data class LibraryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    val name: String,

    @OneToMany(mappedBy = Tables.LIBRARY_TABLE, cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val genres: List<GenreEntity> = emptyList()
)

fun LibraryEntity.toDTO() = LibraryDTO(
    this.id,
    this.name,
)

data class LibraryDTO(
    val id: Int,
    val name: String,
)