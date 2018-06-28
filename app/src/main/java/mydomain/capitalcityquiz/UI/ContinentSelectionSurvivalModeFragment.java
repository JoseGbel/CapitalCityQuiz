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

public class ContinentSelectionSurvivalModeFragment extends Fragment {

    Button europeBtn;
    Button africaBtn;
    Button southAmericaBtn;
    Button northAmericaBtn;
    Button asiaBtn;
    Button australiaBtn;

    MenuFragmentInterface fInterface;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        europeBtn = getActivity().findViewById(R.id.europeSMBtn);
        africaBtn = getActivity().findViewById(R.id.africaSMBtn);
        southAmericaBtn = getActivity().findViewById(R.id.southAmericaSMBtn);
        northAmericaBtn = getActivity().findViewById(R.id.northAmericaSMBtn);
        asiaBtn = getActivity().findViewById(R.id.asiaSMBtn);
        australiaBtn = getActivity().findViewById(R.id.australiaSMBtn);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.continent_selection_survival_mode_fragment,
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

        europeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fInterface.continentSelection(getString(R.string.europe));
            }
        });

        africaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fInterface.continentSelection(getString(R.string.africa));
            }
        });

        southAmericaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fInterface.continentSelection(getString(R.string.southAmerica));
            }
        });

        northAmericaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fInterface.continentSelection(getString(R.string.northAmerica));
            }
        });

        asiaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fInterface.continentSelection(getString(R.string.asia));
            }
        });

        australiaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fInterface.continentSelection(getString(R.string.australia));
            }
        });
    }
}
