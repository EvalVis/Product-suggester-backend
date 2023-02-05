package ev.projects.productsuggester.repositories;

import ev.projects.productsuggester.models.Answer;
import ev.projects.productsuggester.models.Product;
import ev.projects.productsuggester.utils.ProductRetriever;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductSuggestionRuntimeRepository implements ProductSuggestionRepository {

    private Map<String, List<Product>> productSuggestionsByAge;
    private Map<Boolean, List<Product>> productSuggestionsByStudentStatus;
    private Map<String, List<Product>> productSuggestionsByIncome;

    public ProductSuggestionRuntimeRepository() {
        initializeData();
    }

    private void initializeData() {
        initializeProductSuggestionsByAge();
        initializeProductSuggestionsByStudentStatus();
        initializeProductSuggestionsByIncome();
    }

    private void initializeProductSuggestionsByAge() {
        productSuggestionsByAge = new HashMap<>();

        productSuggestionsByAge.put("0-17", new ArrayList<>(Arrays.asList
                (ProductRetriever.getJuniorSaverAccountProduct()
                )));

        productSuggestionsByAge.put("18-64", new ArrayList<>(Arrays.asList(
                ProductRetriever.getCurrentAccountProduct(), ProductRetriever.getCurrentAccountPlusProduct(),
                ProductRetriever.getStudentAccountProduct(), ProductRetriever.getDebitCardProduct(),
                ProductRetriever.getCreditCardProduct(), ProductRetriever.getGoldCreditCardProduct()
        )));

        productSuggestionsByAge.put("65+", new ArrayList<>(Arrays.asList(
                ProductRetriever.getCurrentAccountProduct(), ProductRetriever.getCurrentAccountPlusProduct(),
                ProductRetriever.getStudentAccountProduct(),
                ProductRetriever.getSeniorAccountProduct(), ProductRetriever.getDebitCardProduct(),
                ProductRetriever.getCreditCardProduct(), ProductRetriever.getGoldCreditCardProduct()
        )));
    }

    private void initializeProductSuggestionsByStudentStatus() {
        productSuggestionsByStudentStatus = new HashMap<>();

        productSuggestionsByStudentStatus.put(true, new ArrayList<>(Arrays.asList(
                ProductRetriever.getStudentAccountProduct(),
                ProductRetriever.getCurrentAccountProduct(), ProductRetriever.getCurrentAccountPlusProduct(),
                ProductRetriever.getJuniorSaverAccountProduct(), ProductRetriever.getSeniorAccountProduct(),
                ProductRetriever.getDebitCardProduct(), ProductRetriever.getCreditCardProduct(),
                ProductRetriever.getGoldCreditCardProduct()
                )));

        productSuggestionsByStudentStatus.put(false, new ArrayList<>(Arrays.asList(
                ProductRetriever.getCurrentAccountProduct(), ProductRetriever.getCurrentAccountPlusProduct(),
                ProductRetriever.getJuniorSaverAccountProduct(), ProductRetriever.getSeniorAccountProduct(),
                ProductRetriever.getDebitCardProduct(), ProductRetriever.getCreditCardProduct(),
                ProductRetriever.getGoldCreditCardProduct()
        )));
    }

    private void initializeProductSuggestionsByIncome() {
        productSuggestionsByIncome = new HashMap<>();

        productSuggestionsByIncome.put("0", new ArrayList<>(Arrays.asList(
                ProductRetriever.getJuniorSaverAccountProduct(), ProductRetriever.getStudentAccountProduct(),
                ProductRetriever.getSeniorAccountProduct(), ProductRetriever.getDebitCardProduct()
        )));

        productSuggestionsByIncome.put("1-12000", new ArrayList<>(Arrays.asList(
                ProductRetriever.getCurrentAccountProduct(), ProductRetriever.getJuniorSaverAccountProduct(),
                ProductRetriever.getStudentAccountProduct(), ProductRetriever.getSeniorAccountProduct(),
                ProductRetriever.getDebitCardProduct()
        )));

        productSuggestionsByIncome.put("12001-40000", new ArrayList<>(Arrays.asList(
                ProductRetriever.getCurrentAccountProduct(), ProductRetriever.getJuniorSaverAccountProduct(),
                ProductRetriever.getStudentAccountProduct(), ProductRetriever.getSeniorAccountProduct(),
                ProductRetriever.getCreditCardProduct()
        )));

        productSuggestionsByIncome.put("40001+", new ArrayList<>(Arrays.asList(
                ProductRetriever.getCurrentAccountProduct(), ProductRetriever.getCurrentAccountPlusProduct(),
                ProductRetriever.getJuniorSaverAccountProduct(), ProductRetriever.getStudentAccountProduct(),
                ProductRetriever.getSeniorAccountProduct(), ProductRetriever.getCreditCardProduct(),
                ProductRetriever.getGoldCreditCardProduct()
        )));

    }

    @Override
    public List<Product> getSuggestions(Answer answer) {
        if(isBadAnswer(answer)) {
            throw new NullPointerException();
        }
        List<Product> suggestionsByAge = productSuggestionsByAge.get(answer.getAgeRange());
        List<Product> suggestionsByStudentStatus = productSuggestionsByStudentStatus.get(answer.isStudying());
        List<Product> suggestionsByIncome = productSuggestionsByIncome.get(answer.getIncomeRange());
        List<Product> suggestedProducts = suggestionsByAge.stream()
                .filter(suggestedProduct ->
                        suggestionsByStudentStatus.contains(suggestedProduct)
                                && suggestionsByIncome.contains(suggestedProduct))
                .toList();
        return suggestedProducts;
    }

    @Override
    public void addProduct(Product product, Answer answer) {
        if(isBadProduct(product) || isBadAnswer(answer)) {
            throw new NullPointerException();
        }
        productSuggestionsByAge.computeIfAbsent(answer.getAgeRange(), key -> new ArrayList<>()).add(product);
        productSuggestionsByStudentStatus.computeIfAbsent(answer.isStudying(), key -> new ArrayList<>()).add(product);
        productSuggestionsByIncome.computeIfAbsent(answer.getIncomeRange(), key -> new ArrayList<>()).add(product);
    }

    private boolean isBadProduct(Product product) {
        return Objects.isNull(product) ||
                Objects.isNull(product.getName());
    }

    private boolean isBadAnswer(Answer answer) {
        return Objects.isNull(answer) ||
                Objects.isNull(answer.getAgeRange()) ||
                Objects.isNull(answer.getIncomeRange());
    }

}
