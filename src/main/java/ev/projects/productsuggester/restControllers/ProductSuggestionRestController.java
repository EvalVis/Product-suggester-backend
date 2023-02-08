package ev.projects.productsuggester.restControllers;

import ev.projects.productsuggester.models.Answer;
import ev.projects.productsuggester.models.Product;
import ev.projects.productsuggester.services.ProductSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suggestions/")
public class ProductSuggestionRestController {

    @Autowired
    private ProductSuggestionService productSuggestionService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/")
    public ResponseEntity<List<Product>> getSuggestionsByAnswers(@RequestBody Answer answer) {
        try {
            return new ResponseEntity<>(productSuggestionService.getSuggestions(answer), HttpStatus.OK);
        }
        catch(NullPointerException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
