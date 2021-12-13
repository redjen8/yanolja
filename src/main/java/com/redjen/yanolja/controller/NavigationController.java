package com.redjen.yanolja.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class NavigationController {

    @GetMapping("/")
    public String home() {
        return "forward:/index.html";
    }
}
