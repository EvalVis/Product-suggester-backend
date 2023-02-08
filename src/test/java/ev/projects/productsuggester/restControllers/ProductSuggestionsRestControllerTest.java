package ev.projects.productsuggester.restControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ev.projects.productsuggester.ProductSuggesterApplication;
import ev.projects.productsuggester.models.Answer;
import ev.projects.productsuggester.models.Product;
import ev.projects.productsuggester.utils.ProductRetriever;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ProductSuggesterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductSuggestionsRestControllerTest {

    @LocalServerPort
    private int port;

    private String baseUrl;

    @BeforeEach
    public void start() {
        baseUrl = "http://localhost:" + port;
    }

    @Test
    public void shouldRetrieveCorrectSuggestionsGivenAnswers() throws JsonProcessingException {
        //Arrange
        Answer answer = new Answer("65+", true, "1-12000");
        String url = baseUrl + "/product-suggestions/";
        //Act
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Answer> request = new HttpEntity<>(answer, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        //Assert
        ObjectMapper mapper = new ObjectMapper();
        List<Product> productSuggestions = Arrays.asList(mapper.readValue(response.getBody(), Product[].class));
        List<Product> expectedSuggestions = new ArrayList<>(Arrays.asList(
                ProductRetriever.getCurrentAccountProduct(), ProductRetriever.getStudentAccountProduct(),
                ProductRetriever.getSeniorAccountProduct(), ProductRetriever.getDebitCardProduct()
        ));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertArrayEquals(expectedSuggestions.toArray(), productSuggestions.toArray());
    }

    @Test
    public void shouldReturnErrorWhenRetrievingSuggestionsGivenEmptyBody() {
        //Arrange
        String url = baseUrl + "/product-suggestions/";
        //Act
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Answer> request = new HttpEntity<>(new Answer(), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}
