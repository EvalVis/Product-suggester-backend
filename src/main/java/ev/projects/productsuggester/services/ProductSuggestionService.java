package ev.projects.productsuggester.services;

import ev.projects.productsuggester.models.Answer;
import ev.projects.productsuggester.models.Product;

import java.util.List;

public interface ProductSuggestionService {

    List<Product> getSuggestions(Answer answer);

}
