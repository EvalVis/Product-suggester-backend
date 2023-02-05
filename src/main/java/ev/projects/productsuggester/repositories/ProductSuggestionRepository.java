package ev.projects.productsuggester.repositories;

import ev.projects.productsuggester.models.Answer;
import ev.projects.productsuggester.models.Product;

import java.util.List;

public interface ProductSuggestionRepository {
    List<Product> getSuggestions(Answer answer);
    void addProduct(Product product, Answer answer);
}
