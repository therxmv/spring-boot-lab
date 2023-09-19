package com.therxmv.project8.book

import org.springframework.data.repository.CrudRepository

interface BookRepository: CrudRepository<BookEntity, Int>