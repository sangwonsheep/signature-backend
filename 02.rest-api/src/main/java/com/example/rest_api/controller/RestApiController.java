package com.example.rest_api.controller;

import com.example.rest_api.model.BookQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping("/hello")
    public String hello() {
        var html = "<html> <body> <h1> Hello Spring Boot </h1> </body> </html>";

        return html;
    }

    @GetMapping("/echo/{message}/age/{age}/is-man/{isMan}")
    public String echo(
            @PathVariable String message,
            @PathVariable int age,
            @PathVariable boolean isMan
    ) {
        System.out.println(message);
        System.out.println(age);
        System.out.println(isMan);
        return message.toUpperCase();
    }

    // http://localhost:8080/api/book?category=IT&issuedYear=2023&issued-month=01&issued_day=31
    @GetMapping("/book")
    public void queryParam(
            @RequestParam String category,
            @RequestParam String issuedYear,
            @RequestParam("issued-month") String issuedMonth,
            @RequestParam("issued_day") String issuedDay
    ) {
        System.out.println("category = " + category);
        System.out.println("issuedYear = " + issuedYear);
        System.out.println("issuedMonth = " + issuedMonth);
        System.out.println("issuedDay = " + issuedDay);
    }

    @GetMapping("/book2")
    public void queryParam2(@ModelAttribute BookQueryParam param) {
        System.out.println(param.getCategory());
        System.out.println(param.getIssuedYear());
        System.out.println(param.getIssuedMonth());
        System.out.println(param.getIssuedDay());
    }

    @GetMapping("/test")
    public void queryParamTest(
            @RequestParam int num1,
            @RequestParam int num2,
            @RequestParam boolean check
    ) {
        System.out.println(num1 + num2);
        System.out.println(num1 * num2);
        System.out.println("check = " + check);
    }

    @DeleteMapping({"/user/{userName}/delete", "/user/{userName}/del"})
    public void delete(
            @PathVariable String userName
    ) {
        log.info("username : {}", userName);
    }
}
