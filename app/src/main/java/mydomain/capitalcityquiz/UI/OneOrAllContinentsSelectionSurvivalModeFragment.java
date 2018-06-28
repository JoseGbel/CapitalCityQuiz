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

public class OneOrAllContinentsSelectionSurvivalModeFragment extends Fragment {

    Button oneContinent;
    Button allContinents;

    MenuFragmentInterface fInterface;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        oneContinent = getActivity().findViewById(R.id.oneContinentsSMBtn);
        allContinents = getActivity().findViewById(R.id.allContinentsSMBtn);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.one_or_all_continent_selection_sm_fragment,
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

        oneContinent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fInterface.allContinentsSelection(false);
            }
        });


        allContinents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fInterface.allContinentsSelection(true);
            }
        });
    }


}
