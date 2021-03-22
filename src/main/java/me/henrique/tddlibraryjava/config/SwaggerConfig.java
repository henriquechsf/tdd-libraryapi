package me.henrique.tddlibraryjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("me.henrique.tddlibraryjava.api.resources"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    // informações da API
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Library API")
                .description("Projeto de Controle de Aluguel de Livros")
                .version("1.0.0")
                .contact(contact())
                .build();
    }

    private Contact contact() {
        return new Contact("Carlos Henrique S. Ferreira",
                "http://github.com/henriquechsf",
                "carloshenrique4m@gmail.com");
    }
}
