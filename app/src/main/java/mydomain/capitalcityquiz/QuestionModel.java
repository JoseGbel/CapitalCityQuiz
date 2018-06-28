package mydomain.capitalcityquiz;

import java.util.List;

public class QuestionModel implements QuestionModelInterface {

    @Override
    public boolean answer(Country theCountry, String theAnswer) {

        return theCountry.getCapitalName().toLowerCase().equals(theAnswer.toLowerCase());
   }

    @Override
    public Country passNewQuestion(List<Country> countries) {

        int randomNum = (int)(Math.random() * (countries.size()));
        return countries.get(randomNum);
    }
}
