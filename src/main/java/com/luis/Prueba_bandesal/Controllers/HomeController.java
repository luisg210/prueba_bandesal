package com.luis.Prueba_bandesal.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    @GetMapping(value = {"/", "home", "index"})
    public String index() {

        return "index";
    }

}
