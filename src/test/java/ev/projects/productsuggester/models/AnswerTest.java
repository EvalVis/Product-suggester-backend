package ev.projects.productsuggester.models;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AnswerTest {

    @Test
    public void shouldCreateCorrectAnswer() {
        String ageRange = "0-17";
        boolean isStudying = true;
        String incomeRange = "150-3000";
        Answer answer = new Answer(ageRange, isStudying, incomeRange);
        assertAll("Answer model tests",
                () -> assertEquals(ageRange, answer.getAgeRange()),
                () -> assertEquals(isStudying, answer.isStudying()),
                () -> assertEquals(incomeRange, answer.getIncomeRange()));
    }

}
