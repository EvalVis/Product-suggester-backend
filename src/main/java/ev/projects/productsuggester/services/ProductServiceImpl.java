package ev.projects.productsuggester.services;

import ev.projects.productsuggester.models.Answer;
import ev.projects.productsuggester.models.Product;
import ev.projects.productsuggester.repositories.ProductSuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductSuggestionRepository productSuggestionRepository;

    @Override
    public void addProduct(Product product, Answer answer) {
        productSuggestionRepository.addProduct(product, answer);
    }
}
