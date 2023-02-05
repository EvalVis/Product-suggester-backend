package ev.projects.productsuggester.repositories;

import ev.projects.productsuggester.models.Answer;
import ev.projects.productsuggester.models.Product;
import ev.projects.productsuggester.utils.ProductRetriever;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProductSuggestionByAgeRuntimeRepositoryTest {

    private ProductSuggestionRuntimeRepository productSuggestionsRuntimeRepository;

    @BeforeEach
    public void prepare() {
        productSuggestionsRuntimeRepository = new ProductSuggestionRuntimeRepository();
    }

    @Test
    public void shouldReturnCorrectSuggestionGivenAge() {
        //Arrange
        String age = "18-64";
        boolean isStudent = true;
        String income = "12001-40000";
        Answer answer = new Answer(age, isStudent, income);
        //Act
        List<Product> productSuggestions = productSuggestionsRuntimeRepository.getSuggestions(answer);
        //Assert
        List<Product> expectedSuggestions = new ArrayList<>(Arrays.asList(
                ProductRetriever.getCurrentAccountProduct(),
                ProductRetriever.getStudentAccountProduct(),
                ProductRetriever.getCreditCardProduct()));
        assertArrayEquals(expectedSuggestions.toArray(), productSuggestions.toArray());
    }

}