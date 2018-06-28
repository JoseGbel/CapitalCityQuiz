package mydomain.capitalcityquiz.Utils;

/**
 * This class object will collect input from menu UI so we can send the information about
 * game config to the model
 *
 * integer gameMode. Survival Mode = 0, Practice Mode = 1
 * String countries. "North America" "South America" "Africa" "Asia" "Australia" "Europe" "Random"
 * int numberOfQuestions. Number of countries in the list.
 *          IF numberOfQuestion == 0; numberOfQuestions == ALL COUNTRIES
 * int millis. this variable holds the time in milliseconds for every question.
 *          IF millis == 0, there is no countDownTimer for that gameMode.
 */
//TODO check if this class should be a singleton

public class GameConfig {

    int gameMode;
    String continentMode;
    int numberOfQuestions;
    int millis;

    public GameConfig() { }

    public int getGameMode() {
        return gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    public String getContinentMode() {
        return continentMode;
    }

    public void setContinentMode(String continentMode) {
        this.continentMode = continentMode;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getMillis() {
        return millis;
    }

    public void setMillis(int millis) {
        this.millis = millis;
    }
}


