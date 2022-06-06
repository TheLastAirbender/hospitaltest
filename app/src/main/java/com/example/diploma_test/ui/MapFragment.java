package com.example.diploma_test.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.diploma_test.R;
import com.example.diploma_test.viewmodel.MapViewModel;

public class MapFragment extends Fragment implements View.OnClickListener {

    private MapViewModel mapViewModel;
    private Button myButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mapViewModel =
                new ViewModelProvider(this).get(MapViewModel.class);
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        myButton = root.findViewById(R.id.myNotiButton);

        // вместо стринга указываем нужные нам объекты, например, List<Doctors>
        mapViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("BUTTON CLICKED");
                mapViewModel.setData("SUCK MY NICK");
            }
        });

        return root;

    }

    @Override
    public void onClick(@NonNull View v) {
       /* switch(v.getId()){
            case R.id.myNotiButton:
                System.out.println("BUTTON CLICKED");
                notificationsViewModel.setData("SUCK MY NICK");

                Toast toast = Toast.makeText(getActivity(),
                        "Пора покормить кота!", Toast.LENGTH_SHORT);
                toast.show();
                *//** Do things you need to..
                 fragmentTwo = new FragmentTwo();

                 fragmentTransaction.replace(R.id.frameLayoutFragmentContainer, fragmentTwo);
                 fragmentTransaction.addToBackStack(null);

                 fragmentTransaction.commit();
                 *//*
                break;
        }*/
    }
}