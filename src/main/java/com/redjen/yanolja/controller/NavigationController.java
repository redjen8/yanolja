package com.redjen.yanolja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    @GetMapping("/")
    public String home() {
        return "forward:/index.html";
    }
}
