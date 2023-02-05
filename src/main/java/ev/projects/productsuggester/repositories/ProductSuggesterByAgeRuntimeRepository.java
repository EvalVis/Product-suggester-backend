package ev.projects.productsuggester.repositories;

import ev.projects.productsuggester.models.Product;
import ev.projects.productsuggester.utils.ProductRetriever;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("ProductSuggesterByAgeRuntimeRepository")
public class ProductSuggesterByAgeRuntimeRepository implements ProductSuggesterRepository {

    private Map<String, List<Product>> productSuggestions;

    public ProductSuggesterByAgeRuntimeRepository() {
        initializeProductSuggestions();
    }

    private void initializeProductSuggestions() {
        productSuggestions = new HashMap<>();

        productSuggestions.put("0-17", new ArrayList<>(Arrays.asList(ProductRetriever.getJuniorSaverAccountProduct())));

        productSuggestions.put("18-64", new ArrayList<>(Arrays.asList(
                ProductRetriever.getCurrentAccountProduct(), ProductRetriever.getCurrentAccountPlusProduct(),
                ProductRetriever.getStudentAccountProduct(), ProductRetriever.getDebitCardProduct(),
                ProductRetriever.getCreditCardProduct(), ProductRetriever.getGoldCreditCardProduct()
        )));
        productSuggestions.put("65+", new ArrayList<>(Arrays.asList(
                ProductRetriever.getCurrentAccountProduct(), ProductRetriever.getCurrentAccountPlusProduct(),
                ProductRetriever.getStudentAccountProduct(),
                ProductRetriever.getSeniorAccountProduct(), ProductRetriever.getDebitCardProduct(),
                ProductRetriever.getCreditCardProduct(), ProductRetriever.getGoldCreditCardProduct()
        )));
    }

    @Override
    public List<Product> getSuggestions(String requestorProperty) {
        return productSuggestions.get(requestorProperty);
    }

}