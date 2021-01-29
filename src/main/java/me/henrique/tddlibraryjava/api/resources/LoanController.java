package me.henrique.tddlibraryjava.api.resources;

import lombok.RequiredArgsConstructor;
import me.henrique.tddlibraryjava.api.dto.LoanDTO;
import me.henrique.tddlibraryjava.api.dto.ReturnedLoanDTO;
import me.henrique.tddlibraryjava.model.entity.Book;
import me.henrique.tddlibraryjava.model.entity.Loan;
import me.henrique.tddlibraryjava.service.BookService;
import me.henrique.tddlibraryjava.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;
    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody LoanDTO dto) {
        Book book = bookService
                .getBookByIsbn(dto.getIsbn())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book not found for passed isbn"));

        Loan entity = Loan.builder()
                .book(book).customer(dto.getCustomer())
                .loanDate(LocalDate.now()).build();

        entity = loanService.save(entity);
        return entity.getId();
    }

    @PatchMapping("{id}")
    public void returnBook(@PathVariable Long id, @RequestBody ReturnedLoanDTO dto) {
        Loan loan = loanService.getById(id).get();
        loan.setReturned(dto.getReturned());

        loanService.update(loan);
    }
}
