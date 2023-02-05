package ev.projects.productsuggester.models;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @ParameterizedTest
    @ValueSource(strings = {"a", "abc", "abdce", "test", "product"})
    public void shouldCreateCorrectProduct(String productName) {
        Product product = new Product(productName);
        assertEquals(productName, product.getName());
    }

}
