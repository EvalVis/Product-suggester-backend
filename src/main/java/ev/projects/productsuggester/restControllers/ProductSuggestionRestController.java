package ev.projects.productsuggester.restControllers;

import ev.projects.productsuggester.models.Answer;
import ev.projects.productsuggester.models.Product;
import ev.projects.productsuggester.services.ProductSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-suggestions/")
public class ProductSuggestionRestController {

    @Autowired
    private ProductSuggestionService productSuggestionService;

    @PostMapping("/")
    public List<Product> getSuggestionsByAnswers(@RequestBody Answer answer) {
        return productSuggestionService.getSuggestions(answer);
    }

}
