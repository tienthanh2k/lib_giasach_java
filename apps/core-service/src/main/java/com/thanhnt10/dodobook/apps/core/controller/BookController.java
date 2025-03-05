package com.thanhnt10.dodobook.apps.core.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    @PostMapping("/create")
    public String createChannel(String title) {
        return title;
    }
}
