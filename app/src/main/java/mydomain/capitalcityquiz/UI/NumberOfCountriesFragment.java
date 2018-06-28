package mydomain.capitalcityquiz.UI;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mydomain.capitalcityquiz.R;
import mydomain.capitalcityquiz.Utils.MenuFragmentInterface;

public class NumberOfCountriesFragment extends Fragment {

    Button twentyCountriesBtn;
    Button fiftyCountriesBtn;
    Button oneHundredCountriesBtn;
    Button allCountriesBtn;

    MenuFragmentInterface fInterface;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        twentyCountriesBtn = getActivity().findViewById(R.id.twentyCountriesBtn);
        fiftyCountriesBtn = getActivity().findViewById(R.id.fiftyCountriesBtn);
        oneHundredCountriesBtn = getActivity().findViewById(R.id.oneHundredCountriesBtn);
        allCountriesBtn = getActivity().findViewById(R.id.allCountriesBtn);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.number_countries_selection_fragment,
                                container,
                                false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        fInterface = (MenuFragmentInterface) context;

    }

    @Override
    public void onStart() {
        super.onStart();

        twentyCountriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fInterface.numberCountriesSelection(20);
            }
        });


        fiftyCountriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fInterface.numberCountriesSelection(50);
            }
        });

        oneHundredCountriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fInterface.numberCountriesSelection(100);
            }
        });


        allCountriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fInterface.numberCountriesSelection(0);
            }
        });
    }


}
