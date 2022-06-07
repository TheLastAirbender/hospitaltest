package com.example.diploma_test.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.diploma_test.R;
import com.example.diploma_test.viewmodel.MapViewModel;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Style;

public class MapFragment extends Fragment implements View.OnClickListener {

    private MapViewModel mapViewModel;
    private Button myButton;
    private MapView mapView;
    private MapboxMap mbMap;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Mapbox.getInstance(getContext(),"pk.eyJ1Ijoic2tyYXBpdm5veSIsImEiOiJjbDQzeDRscTIwOHc5M2pueW93NDZqZHV5In0.S56KqEQ33W52vO4cY08mvg");

        mapViewModel =
                new ViewModelProvider(this).get(MapViewModel.class);
        View root = inflater.inflate(R.layout.fragment_map, container, false);

        //final TextView textView = root.findViewById(R.id.text_notifications);
        myButton = root.findViewById(R.id.myNotiButton);

        // вместо стринга указываем нужные нам объекты, например, List<Doctors>
        mapViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });


        mapView = root.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new com.mapbox.mapboxsdk.maps.OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMap) {
                mbMap = mapboxMap;
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        //create this function & code further stuff there
                        //initMapStuff(style);
                    }
                });
            }
        });

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("BUTTON CLICKED");
                mapViewModel.setData("SUCK MY NICK");
            }
        });

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_map);
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