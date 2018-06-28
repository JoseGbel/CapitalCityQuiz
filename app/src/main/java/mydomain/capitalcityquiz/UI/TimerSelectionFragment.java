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

public class TimerSelectionFragment extends Fragment {

    Button tenSecondsBtn;
    Button twentySecondsBtn;
    Button thirtySecondsBtn;
    Button oneMinuteBtn;

    MenuFragmentInterface fInterface;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tenSecondsBtn = getActivity().findViewById(R.id.tenSecondsBtn);
        twentySecondsBtn = getActivity().findViewById(R.id.twentySecondsBtn);
        thirtySecondsBtn = getActivity().findViewById(R.id.thirtySecondsBtn);
        oneMinuteBtn = getActivity().findViewById(R.id.oneMinuteBtn);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.timer_selection_fragment,
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

        tenSecondsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fInterface.timerSelection(10000);
            }
        });


        twentySecondsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fInterface.timerSelection(20000);
            }
        });

        thirtySecondsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fInterface.timerSelection(30000);
            }
        });


        oneMinuteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fInterface.timerSelection(60000);
            }
        });
    }


}
