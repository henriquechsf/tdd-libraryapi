package me.henrique.tddlibraryjava.api.dto;

import lombok.Data;

@Data
public class LoanFilterDTO {
    private String isbn;
    private String customer;
}
