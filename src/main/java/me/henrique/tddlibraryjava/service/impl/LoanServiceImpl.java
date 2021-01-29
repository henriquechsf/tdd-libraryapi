package me.henrique.tddlibraryjava.service.impl;

import me.henrique.tddlibraryjava.model.entity.Loan;
import me.henrique.tddlibraryjava.model.repository.LoanRepository;
import me.henrique.tddlibraryjava.service.LoanService;

public class LoanServiceImpl implements LoanService {
    private LoanRepository repository;

    public LoanServiceImpl(LoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public Loan save(Loan loan) {
        return repository.save(loan);
    }
}
