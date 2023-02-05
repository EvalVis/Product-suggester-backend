package ev.projects.productsuggester.restControllers;

import ev.projects.productsuggester.models.dtos.ProductAnswer;
import ev.projects.productsuggester.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Void> createNewProduct(@RequestBody ProductAnswer productAnswer) {
        try {
            productService.addProduct(productAnswer.getProduct(), productAnswer.getAnswer());
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch(NullPointerException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
