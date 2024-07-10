package com.example.rest_api.model;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class BookQueryParam {
    private String category;
    private String issuedYear;
    private String issuedMonth;
    private String issuedDay;
}
