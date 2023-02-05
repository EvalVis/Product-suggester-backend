package ev.projects.productsuggester.models.dtos;

import ev.projects.productsuggester.models.Answer;
import ev.projects.productsuggester.models.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductAnswer {

    private Product product;
    private Answer answer;

}
