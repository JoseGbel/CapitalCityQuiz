package mydomain.capitalcityquiz;

import java.util.List;

public interface QuestionModelInterface {

    Country passNewQuestion (List<Country> countries);
    boolean answer(Country askedCountry, String answer);

}
