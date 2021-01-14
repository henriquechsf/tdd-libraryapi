package me.henrique.tddlibraryjava.api.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.henrique.tddlibraryjava.api.dto.BookDTO;
import me.henrique.tddlibraryjava.model.entity.Book;
import me.henrique.tddlibraryjava.service.BookService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// cria um mini contexto para rodar os testes
@ExtendWith(SpringExtension.class)
// seleciona o ambiente
@ActiveProfiles("test")
// testa o comportamento da API
@WebMvcTest
// configura um objeto para fazer as requisições
@AutoConfigureMockMvc
public class BookControllerTest {

    // rota da requisição
    static String BOOK_API = "/api/books";

    // mocka as requisições - simula as requisições para a API
    @Autowired
    MockMvc mvc;

    // mock que simula o service
    @MockBean
    BookService service;

    @Test
    @DisplayName("Deve criar um livro com sucesso")
    public void createBookTest() throws Exception {

        BookDTO dto = BookDTO.builder().author("Artur").title("As aventuras").isbn("001").build();

        Book savedBook = Book.builder().id(10L).author("Artur").title("As aventuras").isbn("001").build();

        BDDMockito.given(service.save(Mockito.any(Book.class))).willReturn(savedBook);

        String json = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("title").value(dto.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("author").value(dto.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("isbn").value(dto.getIsbn()))
        ;
    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver dados suficientes para a criação de um livro")
    public void createInvalidBookTest() throws Exception {

        String json = new ObjectMapper().writeValueAsString(new BookDTO());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(3)));
    }
}
