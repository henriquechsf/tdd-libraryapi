package me.henrique.tddlibraryjava.api.resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

// cria um mini contexto para rodar os testes
@ExtendWith(SpringExtension.class)
// seleciona o ambiente
@ActiveProfiles("test")
// testa o comportamento da API
@WebMvcTest
// configura um objeto para fazer as requisições
@AutoConfigureMockMvc
public class BookControllerTest {

    // mocka as requisições - simula as requisições para a API
    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("Deve criar um livro com sucesso")
    public void createBookTest() {

    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver dados suficientes para a criação de um livro")
    public void createInvalidBookTest() {

    }
}
