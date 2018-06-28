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

public class GameModeSelectionFragment extends Fragment {

    Button survivalModeBtn;

    MenuFragmentInterface fInterface;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        survivalModeBtn = getActivity().findViewById(R.id.survivalModeBtn);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mode_selection_fragment, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        fInterface = (MenuFragmentInterface) context;

    }

    @Override
    public void onStart() {
        super.onStart();

        survivalModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fInterface.gameModeSelection(0);
            }
        });
    }
}

