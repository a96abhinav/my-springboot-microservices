/*
 * package com.springboot.controller;
 * 
 * import com.springboot.rest.SpringbootRestApiApplication; import
 * org.junit.jupiter.api.Test; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
 * import org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.http.MediaType; import
 * org.springframework.test.web.servlet.MockMvc; import
 * org.springframework.test.web.servlet.MvcResult; import
 * org.springframework.test.web.servlet.RequestBuilder; import
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
 * 
 * import static org.hamcrest.CoreMatchers.is; import static
 * org.junit.Assert.assertThat;
 * 
 * 
 * @SpringBootTest(classes = SpringbootRestApiApplication.class)
 * 
 * @AutoConfigureMockMvc class RestApiControllerTest {
 * 
 * @Autowired private MockMvc mockMvc;
 * 
 * @Test void test_findAllEmployees() throws Exception { RequestBuilder request
 * =
 * MockMvcRequestBuilders.get("/api/employee").accept(MediaType.APPLICATION_JSON
 * ); MvcResult result = mockMvc.perform(request).andReturn(); int status =
 * result.getResponse().getStatus(); assertThat(status, is(200)); }
 * 
 * }
 */