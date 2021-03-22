package me.henrique.tddlibraryjava.api.resources;

import lombok.RequiredArgsConstructor;
import me.henrique.tddlibraryjava.api.dto.BookDTO;
import me.henrique.tddlibraryjava.api.dto.LoanDTO;
import me.henrique.tddlibraryjava.api.dto.LoanFilterDTO;
import me.henrique.tddlibraryjava.api.dto.ReturnedLoanDTO;
import me.henrique.tddlibraryjava.model.entity.Book;
import me.henrique.tddlibraryjava.model.entity.Loan;
import me.henrique.tddlibraryjava.service.BookService;
import me.henrique.tddlibraryjava.service.LoanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;
    private final BookService bookService;
    @Autowired
    private ModelMapper modelMapper;

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

    @GetMapping
    public Page<LoanDTO> find(LoanFilterDTO dto, Pageable pageable) {
        Page<Loan> result = loanService.find(dto, pageable);
        List<LoanDTO> loans = result
                .getContent()
                .stream()
                .map(entity -> {
                    Book book = entity.getBook();
                    BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
                    LoanDTO loanDTO = modelMapper.map(entity, LoanDTO.class);
                    loanDTO.setBook(bookDTO);
                    return loanDTO;
                }).collect(Collectors.toList());
        return new PageImpl<LoanDTO>(loans, pageable, result.getTotalElements());
    }
}
