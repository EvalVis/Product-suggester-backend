package ev.projects.productsuggester.restControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ev.projects.productsuggester.ProductSuggesterApplication;
import ev.projects.productsuggester.models.Answer;
import ev.projects.productsuggester.models.Product;
import ev.projects.productsuggester.models.dtos.ProductAnswer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ProductSuggesterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductRestControllerTest {

    @LocalServerPort
    private int port;

    private String baseUrl;

    @BeforeEach
    public void start() {
        baseUrl = "http://localhost:" + port;
    }

    @Test
    public void shouldCreateNewProduct() throws JsonProcessingException {
        //Arrange
        Product product = new Product("My product");
        Answer answer = new Answer("0-17", true, "0");
        ProductAnswer productAnswer = new ProductAnswer();
        productAnswer.setProduct(product);
        productAnswer.setAnswer(answer);
        String creationUrl = baseUrl + "/product/create";
        String retrievalUrl = baseUrl + "/product-suggestions/";
        //Act
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<ProductAnswer> creationRequest = new HttpEntity<>(productAnswer, headers);
        ResponseEntity<Void> creationResponse = restTemplate.postForEntity(creationUrl, creationRequest, Void.class);
        HttpEntity<Answer> retrievalRequest = new HttpEntity<>(answer, headers);
        ResponseEntity<String> retrievalResponse = restTemplate.postForEntity(retrievalUrl, retrievalRequest, String.class);
        //Assert
        assertEquals(HttpStatus.CREATED, creationResponse.getStatusCode());
        assertEquals(HttpStatus.OK, retrievalResponse.getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        List<Product> productSuggestions = Arrays.asList(mapper.readValue(retrievalResponse.getBody(), Product[].class));
        assertTrue(productSuggestions.contains(product));
    }

    @Test
    public void shouldReturnErrorOnCreateProductGivenEmptyBody() {
        //Arrange
        String url = baseUrl + "/product/create";
        //Act
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<ProductAnswer> creationRequest = new HttpEntity<>(new ProductAnswer(), headers);
        ResponseEntity<Void> creationResponse = restTemplate.postForEntity(url, creationRequest, Void.class);
        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, creationResponse.getStatusCode());
    }

}
