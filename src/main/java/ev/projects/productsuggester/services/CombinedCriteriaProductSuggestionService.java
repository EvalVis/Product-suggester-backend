package ev.projects.productsuggester.services;

import ev.projects.productsuggester.models.Answer;
import ev.projects.productsuggester.models.Product;
import ev.projects.productsuggester.repositories.ProductSuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombinedCriteriaProductSuggestionService implements ProductSuggestionService {

    private ProductSuggestionRepository productSuggestionRepository;

    @Autowired
    public CombinedCriteriaProductSuggestionService(ProductSuggestionRepository productSuggestionRepository) {
        this.productSuggestionRepository = productSuggestionRepository;
    }

    @Override
    public List<Product> getSuggestions(Answer answer) {
        return productSuggestionRepository.getSuggestions(answer);
    }
}
