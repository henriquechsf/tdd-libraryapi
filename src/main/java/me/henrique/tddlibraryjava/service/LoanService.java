package me.henrique.tddlibraryjava.service;

import me.henrique.tddlibraryjava.model.entity.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanService {
    Loan save(Loan any);

    Optional<Loan> getById(Long id);

    Loan update(Loan loan);
}
