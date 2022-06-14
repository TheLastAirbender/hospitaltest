package com.example.diploma_test.ui;

import android.graphics.PointF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.diploma_test.MainActivity;
import com.example.diploma_test.R;
import com.example.diploma_test.entity.CabinetMissing;
import com.example.diploma_test.map.MapData;
import com.example.diploma_test.viewmodel.MapViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mapbox.geojson.Feature;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.Layer;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapFragment extends Fragment implements OnMapReadyCallback, MapboxMap.OnMapClickListener {

    private MapViewModel mapViewModel;
    private Button myButton;
    private MapView mapView;
    private MapboxMap mbMap;
    private MapData mapData;
    ArrayDeque<Layer> rooms = new ArrayDeque<>();
    BottomSheetDialogFragment bottomSheetFragment;
    FragmentManager childFragmentManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Mapbox.getInstance(getContext(), "pk.eyJ1Ijoic2tyYXBpdm5veSIsImEiOiJjbDQzeDRscTIwOHc5M2pueW93NDZqZHV5In0.S56KqEQ33W52vO4cY08mvg");

//        mapViewModel = new ViewModelProvider(this).get(MapViewModel.class);
        mapViewModel = MapViewModel.getInstance(getActivity().getApplication());
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
        mapView.getMapAsync(this);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("BUTTON CLICKED");
                mapViewModel.setData("SUCK MY NICK");
            }
        });

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_map);

//        RelativeLayout bottomSheetLayout = root.findViewById(R.id.bottom_sheet);
//        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        bottomSheetFragment = new BottomSheetFragment();
        mapViewModel.setCurrentRoom(new CabinetMissing("INITIAL STATE"));

        return root;

    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        this.mbMap = mapboxMap;
//         mapboxMap.setStyle(new Style.Builder().fromUri("mapbox://username/styleid");
        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                //create this function & code further stuff there
                //initMapStuff(style);
                System.out.println("OnMapReady");
                mapData = new MapData(style, getContext());

            }
        });
        mbMap.addOnMapClickListener(this);
    }

    @Override
    public boolean onMapClick(@NonNull final LatLng point) {
        System.out.println("Clicked on " + point);
        mapData.setLocation(point.getLongitude(),point.getLatitude());
        bottomSheetFragment.show(getFragmentManager(), bottomSheetFragment.getTag());

        this.mbMap.getStyle(new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                //после клика получаем позицию, переводим полученные долготу и широту в положение на экране
                final PointF finalPoint = mbMap.getProjection().toScreenLocation(point);
                //получаем данные из слоя, если точка принадлежит ему
                mapData.getFeatures(mbMap, finalPoint);
                int onRoomClick = -1;
                for (int i=0;i< mapData.features.size();i++){
                    if (mapData.features.get(i).size()>0){
                        onRoomClick = i;
                        break;
                    }
                }

                //если данные не пусты, т.е. если клик был совершен на слое
                if (onRoomClick>=0){
                    for (Layer room: rooms)
                        room.setProperties(PropertyFactory.fillOpacity(0.0f));
                    rooms.add(style.getLayer(mapData.roomsNames[onRoomClick]));
                    myButton.setText(mapData.description[onRoomClick]);
                    mapViewModel.setCurrentRoom(new CabinetMissing(mapData.description[onRoomClick]));
//                    Log.d("onRoomClick", tv.getAlpha()+" " + tv.getTextColors());

                    //выделяем слой с кабинетом и делаем надпись
                    Layer room = rooms.getLast();
                    room.setProperties(PropertyFactory.fillOpacity(0.5f));
                    //запускаем asyncTask на 3 секунды, после которых надпись и подсветка исчезнут
                    Task t = new Task();
                    t.execute();
                }
            }
        });
        return true;
    }

    //необходимые методы

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
    }

    public class Task extends AsyncTask<Void,Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Layer room = rooms.getFirst();
            room.setProperties(PropertyFactory.fillOpacity(0.0f));
            rooms.removeFirst();
            myButton.setText("");
            bottomSheetFragment.dismiss();
        }
    }
}

