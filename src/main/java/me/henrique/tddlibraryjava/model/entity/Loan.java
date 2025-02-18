package me.henrique.tddlibraryjava.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Loan {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String customer;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column
    private LocalDate loanDate;

    @Column
    private Boolean returned;

    @JoinColumn(name = "id_book")
    @ManyToOne
    private Book book;
}
