package ev.projects.productsuggester.repositories;

import ev.projects.productsuggester.models.Product;

import java.util.List;

public interface ProductSuggesterRepository {
    List<Product> getSuggestions(String requestorProperty);
}
