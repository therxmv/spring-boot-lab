package com.therxmv.project8.system

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomeController {
    @GetMapping("/")
    fun welcome(model: Model) = "Welcome to the lab 8"
}