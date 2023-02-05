package ev.projects.productsuggester.services;

import ev.projects.productsuggester.models.Answer;
import ev.projects.productsuggester.models.Product;
import ev.projects.productsuggester.repositories.ProductSuggesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombinedCriteriaProductSuggestionService implements ProductSuggesterService {

    private ProductSuggesterRepository productSuggesterRepository;

    @Autowired
    public CombinedCriteriaProductSuggestionService(ProductSuggesterRepository productSuggesterRepository) {
        this.productSuggesterRepository = productSuggesterRepository;
    }

    @Override
    public List<Product> getSuggestions(Answer answer) {
        return productSuggesterRepository.getSuggestions(answer);
    }
}
