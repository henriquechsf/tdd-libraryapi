package me.henrique.tddlibraryjava.model.repository;

import me.henrique.tddlibraryjava.model.entity.Book;
import me.henrique.tddlibraryjava.model.entity.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    // SQL customizado - JPQL
    @Query(value = " select case when (count (l.id) > 0) then true else false end " +
            " from Loan l where l.book = :book and ( l.returned is null or l.returned is false ) ")
    boolean existsByBookAndNotReturned(@Param("book") Book book);

    @Query(value = " select l from Loan as l join l.book as b " +
            " where b.isbn = :isbn or l.customer = :customer ")
    Page<Loan> findByBookIsbnOrCustomer(
            @Param("isbn") String isnbn,
            @Param("customer") String customer,
            Pageable pageable);

    Page<Loan> findByBook(Book book, Pageable pageable);
}
