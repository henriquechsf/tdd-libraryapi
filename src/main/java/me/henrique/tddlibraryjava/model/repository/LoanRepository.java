package me.henrique.tddlibraryjava.model.repository;

import me.henrique.tddlibraryjava.model.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
