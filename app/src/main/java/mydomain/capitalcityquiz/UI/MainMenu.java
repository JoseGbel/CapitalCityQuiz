package mydomain.capitalcityquiz.UI;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import mydomain.capitalcityquiz.R;
import mydomain.capitalcityquiz.Utils.GameConfig;
import mydomain.capitalcityquiz.Utils.MenuFragmentInterface;

public class MainMenu extends AppCompatActivity implements MenuFragmentInterface {

    GameConfig gameConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        GameModeSelectionFragment gameModeSelectionFragment = new GameModeSelectionFragment();


        Button practiceModeBtn = findViewById(R.id.practiceModeBtn);

        gameConfig = new GameConfig();

        fragmentTransaction.add(R.id.fragment_placeholder,
                                gameModeSelectionFragment,
                                getString(R.string.gameModeSelectionFragment));

        fragmentTransaction.addToBackStack(null).commit();
    }

    @Override
    public void gameModeSelection(int gameMode) {
        OneOrAllContinentsSelectionSurvivalModeFragment oneOrAllContinentsSelectionSurvivalModeFragment
                = new OneOrAllContinentsSelectionSurvivalModeFragment();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        if (gameMode == 0 ){         //SurvivalMode
            gameConfig.setGameMode(0);

            fragmentTransaction
                    .replace(R.id.fragment_placeholder,
                            oneOrAllContinentsSelectionSurvivalModeFragment,
                             getString(R.string.oneOrMoreContinentSelectionSMFragment))
                    .addToBackStack(null)
                    .commit();

        }else if (gameMode == 1){   //PracticeMode
            gameConfig.setGameMode(1);
            gameConfig.setMillis(0);
        }
    }

    @Override
    public void allContinentsSelection(boolean allContinents) {
        ContinentSelectionSurvivalModeFragment continentSelectionSurvivalModeFragment =
                new ContinentSelectionSurvivalModeFragment();

        NumberOfCountriesFragment numberOfCountriesFragment =
                new NumberOfCountriesFragment();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        if (!allContinents){
            gameConfig.setNumberOfQuestions(0);
            //Change Fragment to continent selection
            fragmentTransaction
                    .replace(R.id.fragment_placeholder,
                            continentSelectionSurvivalModeFragment,
                            getString(R.string.continentSelectionSMFragment))
                    .addToBackStack(null)
                    .commit();

        }else{
            fragmentTransaction
                    .replace(R.id.fragment_placeholder,
                             numberOfCountriesFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void continentSelection(String continent) {
        TimerSelectionFragment timerSelectionFragment =
                new TimerSelectionFragment();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        gameConfig.setContinentMode(continent);
        fragmentTransaction
                .replace(R.id.fragment_placeholder,
                        timerSelectionFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void numberCountriesSelection(int numberCountries) {
        gameConfig.setNumberOfQuestions(numberCountries);
        //launchGame (gameConfig)
    }

    @Override
    public void timerSelection(int millis) {
        gameConfig .setMillis(millis);
        //launchGame(gameConfig);
    }
/*
    @Override
    public void onBackPressed() {
        //TODO CHECK FOR A FRAGMENT CONTAINER INSTEAD
        FragmentManager fm = getFragmentManager();

        if (fm.getBackStackEntryCount() > 2){

            fm.popBackStack();
        }else{

            super.onBackPressed();
        }
    }*/
}
