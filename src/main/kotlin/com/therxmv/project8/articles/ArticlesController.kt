package com.therxmv.project8.articles

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("articles")
class ArticlesController {

    private val logger = LoggerFactory.getLogger(ArticlesController::class.java)
    private val service = ArticlesService()

    @GetMapping
    fun getAll() = try {
        ResponseEntity(service.getArticles(), HttpStatus.OK)
    } catch (e: Exception) {
        logger.error(e.stackTraceToString())
        ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}