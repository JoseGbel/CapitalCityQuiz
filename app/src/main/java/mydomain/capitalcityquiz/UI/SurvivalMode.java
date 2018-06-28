package mydomain.capitalcityquiz.UI;

import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mydomain.capitalcityquiz.Country;
import mydomain.capitalcityquiz.DatabaseCountriesRepository;
import mydomain.capitalcityquiz.DatabaseHelper;
import mydomain.capitalcityquiz.MyApplicationContext;
import mydomain.capitalcityquiz.QuestionModel;
import mydomain.capitalcityquiz.R;
import mydomain.capitalcityquiz.SurvivalModePresenter;
import mydomain.capitalcityquiz.SurvivalModeView;
import mydomain.capitalcityquiz.WriteDataToSQLite;

public class SurvivalMode extends AppCompatActivity implements SurvivalModeView {
//TODO in database modify "sucre " for "sucre" in bolivia
//TODO refactor access modifiers for this class. private?
    public TextView timerTextView;
    public TextView countryTextView;
    public TextView counterTextView;
    public EditText capitalEditText;
    public Button enterBtn;
    private TextView resultTextView;

    public static MediaPlayer mpSuccess, mpFail;
    public static boolean gameOver, gameCompleted;
    public int textColorAndroidDefault;

    long millisLeft;
    private boolean resetCounterDown = true;

    public CountDownTimer timer;

    private SurvivalModePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survival_mode);

        mpSuccess = MediaPlayer.create(this, R.raw.success_beep);
        mpFail = MediaPlayer.create(this, R.raw.negative_beep);

        timerTextView = findViewById(R.id.timerTextView);
        countryTextView = findViewById(R.id.countryTextView);
        counterTextView = findViewById(R.id.counterTextView);
        capitalEditText = findViewById(R.id.capitalEditText);
        enterBtn = findViewById(R.id.enterBtn);
        resultTextView = findViewById(R.id.resultTextView);

        gameOver = false;
        gameCompleted = false;
        textColorAndroidDefault = counterTextView.getCurrentTextColor();

        presenter = new SurvivalModePresenter(
                this,
                new DatabaseCountriesRepository(getApplication()),
                new QuestionModel());

        DatabaseHelper dBHelper = new DatabaseHelper(MyApplicationContext.getAppContext());
        SQLiteDatabase db = dBHelper.getWritableDatabase();


        db.execSQL(DatabaseCountriesRepository.SQL_DELETE_ENTRIES);
        db.execSQL(DatabaseCountriesRepository.SQL_CREATE_ENTRIES);

        WriteDataToSQLite.dropDatatoDB(this, db);

        presenter.loadCountries();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putLong(getString(R.string.timerKey), millisLeft);

        outState.putParcelable(getString(R.string.countryKey), presenter.getCurrentCountry());

        outState.putParcelableArrayList(getString(R.string.listKey),
                new ArrayList<>(presenter.requestWholeList()));
        
        outState.putBoolean(getString(R.string.counterDownFlagKey), false);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        timer.cancel();
        resetCounterDown = savedInstanceState.getBoolean(getString(R.string.counterDownFlagKey));

        timer = new CountDownTimer((savedInstanceState.getLong(getString(R.string.timerKey)))
                                    ,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                millisLeft = millisUntilFinished;

                timerTextView.setText("00:" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                timerTextView.setText(R.string.gameOverTimer);
            }
        }.start();

        presenter.setCurrentCountry((Country)savedInstanceState.
                getParcelable(getString(R.string.countryKey)));

        presenter.sendCountryListToManager((savedInstanceState.
                <Country>getParcelableArrayList(getString(R.string.listKey))));

       displayQuestion(presenter.getCurrentCountry());
    }

    @Override
    public void displayError() {
        Toast.makeText(this, "Error loading countries", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayFailedResult() {
        enterBtn.setBackgroundColor(getResources().getColor(R.color.colorWrongAnswer));
        enterBtn.setText(R.string.tryAgainBtn);
        mpFail.start();
    }

    @Override
    public void displayQuestion(final Country country) {
        if (resetCounterDown)
            newCounterDown();

        countryTextView.setText (country.getCountryName());
        enterBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                presenter.checkAnswer(country, capitalEditText.getText().toString());
            }
        });
    }

    @Override
    public void displayNoCountries() {

    }

    @Override
    public void displayPassedResult() {

        resultTextView.setVisibility(View.VISIBLE);
        resultTextView.setText(getResources().getString(R.string.wellDoneTxt));
        resultTextView.setTextColor(getResources().getColor(R.color.colorRightAnswer));
        enterBtn.setBackgroundColor(getResources().getColor(R.color.colorRightAnswer));
        enterBtn.setText(getResources().getString(R.string.correctTxt));
        mpSuccess.start();
        resetCounterDown = true;
        timer.cancel();
        timerTextView.setTextColor(getResources().getColor(R.color.colorRightAnswer));
        counterTextView.setTextColor(getResources().getColor(R.color.colorRightAnswer));

        int counter = Integer.parseInt(counterTextView.getText().toString());
        counter++;
        counterTextView.setText(counter + "");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                resetScreenNewQuestion();
            }
        }, 1000);

    }

    private void newCounterDown(){
        timer = new CountDownTimer(31000, 1000) {


            public void onTick(long millisUntilFinished) {
                millisLeft = millisUntilFinished;

                timerTextView.setText("00:" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timerTextView.setText(R.string.gameOverTimer);
            }

        }.start();
    }

    private void resetScreenNewQuestion(){

        resultTextView.setVisibility(View.INVISIBLE);
        enterBtn.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        enterBtn.setText(R.string.enterBtn);
        capitalEditText.setText("");
        counterTextView.setTextColor(textColorAndroidDefault);
        timerTextView.setText("");
        timerTextView.setTextColor(textColorAndroidDefault);
    }

//End of Main activity
}

