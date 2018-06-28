package mydomain.capitalcityquiz;

import java.util.List;

import mydomain.capitalcityquiz.Utils.CountryListManager;

public class SurvivalModePresenter {

    private SurvivalModeView view;
    private CountriesRepository countriesRepository;
    private QuestionModelInterface questionModel;
    private CountryListManager countryListManager;
    private Country currentCountry;

    public SurvivalModePresenter(SurvivalModeView view,
                                 CountriesRepository countriesRepository,
                                 QuestionModel questionModel) {
        this.view = view;
        this.countriesRepository = countriesRepository;
        this.questionModel = questionModel;
         countryListManager = new CountryListManager();
    }

    public void checkAnswer (Country countryAsked, String answer){
        boolean result;
        result = questionModel.answer(countryAsked, answer);
        resultToView(result);
    }

    public void loadCountries() {
        try {
            List<Country> countries = countriesRepository.getCountries();
            if (countries.isEmpty()) {
                view.displayNoCountries();
            } else {
                countryListManager.setList(countries);
                view.displayQuestion(requestNewQuestion());
            }
        }catch (Exception e){
                view.displayError();
        }
    }

    public void resultToView(boolean result) {
        if (result)
            if(countryListManager.getList().size()!=0){
                view.displayPassedResult();
                view.displayQuestion(requestNewQuestion());
            }else{
                //TODO Game completed!
            }
        else
            view.displayFailedResult();
    }

    public Country requestNewQuestion(){ //THIS METHOD IS VERY COUPLED, not cool
        Country country;
        country = questionModel.passNewQuestion(countryListManager.getList());
        //Keep a copy of country inside presenter for future use
        currentCountry = country;
        removeCountryFromList(country);
        return country;
    }

//    public void sendQuestionToView (Country c){
//        removeCountryFromList(c); //TODO should this be Async?? Should this method be called in this specific task? !SOLID
//        view.displayQuestion(c);
//    }

    private void removeCountryFromList (Country c){
        countryListManager.removeCountry(c);
    }

    public List<Country> requestWholeList (){
        return countryListManager.getList();
    }

    public void sendCountryListToManager (List<Country> l)
    {
        countryListManager.setList(l);
    }

    public Country getCurrentCountry(){
        return currentCountry;
    }

    public void setCurrentCountry(Country currentCountry){
        this.currentCountry = currentCountry;
    }

    public void countryToDisplay(Country c){

    }
}
