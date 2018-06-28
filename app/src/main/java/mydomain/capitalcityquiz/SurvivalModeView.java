package mydomain.capitalcityquiz;

public interface SurvivalModeView {

    void displayNoCountries();
    void displayError();
    void displayQuestion(Country country);
    void displayPassedResult();
    void displayFailedResult();
}
