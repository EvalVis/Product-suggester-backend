package ev.projects.productsuggester.repositories;

import ev.projects.productsuggester.models.Product;
import ev.projects.productsuggester.utils.ProductRetriever;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProductSuggesterByAgeRuntimeRepositoryTest {

    private ProductSuggesterRepository productSuggesterRepository;

    @BeforeEach
    public void prepare() {
        productSuggesterRepository = new ProductSuggesterByAgeRuntimeRepository();
    }

    @Test
    public void shouldReturnCorrectSuggestionGivenAge() {
        //Arrange
        String age = "0-17";
        //Act
        List<Product> suggestedProducts = productSuggesterRepository.getSuggestions(age);
        //Assert
        List<Product> expectedProducts = new ArrayList<>(Arrays.asList(ProductRetriever.getJuniorSaverAccountProduct()));
        assertArrayEquals(expectedProducts.toArray(), suggestedProducts.toArray());
    }

}