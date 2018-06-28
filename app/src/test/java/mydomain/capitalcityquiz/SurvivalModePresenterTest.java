package mydomain.capitalcityquiz;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SurvivalModePresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    CountriesRepository countriesRepository;

    @Mock
    SurvivalModeView view;

    @Mock
    QuestionModel question;

    SurvivalModePresenter presenter;
    private final List<Country> countriesList = Arrays.asList(new Country(),
                                                              new Country(),
                                                              new Country());
    private Country country = new Country("countryName", "capitalCity", "continent");
    private String answer = "";

    @Before
    public void setup(){
        presenter = new SurvivalModePresenter(view, countriesRepository, question);
    }

//    @Test
//    public void ShouldHandlePassCountries() {
//        when(countriesRepository.getCountries())
//                .thenReturn(countriesList);
//
//        presenter.loadCountries();
//
//        verify(view).(countriesList);
//    }

    @Test
    public void ShouldHandleNoCountries() {
        when(countriesRepository.getCountries())
                .thenReturn(EMPTY_LIST);

        presenter.loadCountries();

        verify(view).displayNoCountries();
    }

    @Test
    public void ShouldHandleError(){
        when(countriesRepository.getCountries()).thenThrow(new RuntimeException());

        presenter.loadCountries();

        verify(view).displayError();
    }

    @Test
    public void ShouldHandleNewQuestion(){
        when(question.passNewQuestion(countriesList)).thenReturn(country);

        presenter.requestNewQuestion();

        verify(view).displayQuestion(country);
    }

    @Test
    public void ShouldCheckAnAnswer(){

        presenter.checkAnswer(country, answer);

        verify(question).answer(country, answer);
    }

    @Test
    public void ShouldHandlePositiveResult(){

        when(question.answer(country, answer)).thenReturn(true);

        presenter.resultToView(true);

        verify(view).displayPassedResult();
    }

    @Test
    public void ShouldHandleNegativeResult(){

        when(question.answer(country, answer)).thenReturn(false);

        presenter.resultToView(false);

        verify(view).displayFailedResult();
    }
}