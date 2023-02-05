package ev.projects.productsuggester.services;

import ev.projects.productsuggester.models.Answer;
import ev.projects.productsuggester.models.Product;

public interface ProductService {

    void addProduct(Product product, Answer answer);

}
