package ev.projects.productsuggester.repositories;

import ev.projects.productsuggester.models.Answer;
import ev.projects.productsuggester.models.Product;

import java.util.List;

public interface ProductSuggesterRepository {
    List<Product> getSuggestions(Answer answer);
}
