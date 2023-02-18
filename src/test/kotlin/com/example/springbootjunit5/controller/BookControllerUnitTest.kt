package com.example.springbootjunit5.controller

import com.example.springbootjunit5.domain.Book
import com.example.springbootjunit5.service.BookService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers

import org.springframework.test.web.servlet.result.MockMvcResultMatchers

/**
 * Book controller unit test
 *
 * 스프링부트는 컨트롤러 계층 만을 테스트 할 수 있도록 (Unit Test) 도와주는 어노테이션인 @WebMvcTest 를 제공해준다.
 *
 * @WebMvcTest 는 웹 계층과 관련된 빈들만을 찾아서 빈으로 등록한다. (일반적인 @Component 나 @ConfigurationProperties 등을 사용하는 빈들은 스캔되지 않음.)
 *
 * 빈으로 등록
 *  @Controller, @RestController
    @ControllerAdvice, @RestControllerAdvice
    @JsonComponent
    Filter
    WebMvcConfigurer
    HandlerMethodArgumentResolver
 */
@WebMvcTest(BookController::class) // BookController::class 지정 가능.
class BookControllerUnitTest {

    //컨트롤러 테스트하는 용도인 MockMvc 객체를 주입 받음.
    @Autowired
    lateinit var mockMvc: MockMvc

    //@Service를 사용하는 bookService는 빈으로 등록되지 않기 때문에 @MockBean을 사용하여 가짜 객체를 만들어 컨테이너가 주입할 수 있도록 함.
    //해당 객체는 가짜 객체이므로 실제 행위를 하는 객체가 아님의 주의.
    //즉, 해당 객체 내부에서 의존하는 객체와 메서드들은 모두 가짜이며 실패하지만 않을 뿐 기존에 정해진 동작을 수행하지 않음.
    @MockBean
    lateinit var bookService: BookService

    @Test
    fun `save 테스트`() {
        // given
        val book = Book(title = "책 이름", author = "저자 이름")
        val content = ObjectMapper().writeValueAsString(Book(null, "책 이름", "저자 이름"))

        // stub - 행동 정의
        `when`(bookService.saveBook(book)).thenReturn(Book(1L, "책 이름", "저자 이름"))

        // when
        val resultAction = mockMvc.perform(
            MockMvcRequestBuilders.post("/book")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        )

        // then
        resultAction
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andDo(MockMvcResultHandlers.print())
    }

}