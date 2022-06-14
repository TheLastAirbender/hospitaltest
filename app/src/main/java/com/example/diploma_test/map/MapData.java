package com.example.diploma_test.map;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.util.Log;

import com.example.diploma_test.R;
import com.google.android.material.shape.MarkerEdgeTreatment;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.GeoJson;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.utils.GeoJsonUtils;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.FillLayer;
import com.mapbox.mapboxsdk.style.layers.LineLayer;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.fillOpacity;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textAnchor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textField;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textFont;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textOpacity;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textSize;

public class MapData {
   Style style;
   public ArrayList<List<Feature>> features;
   MapboxMap mapboxMap;
   Feature userLocationPoint;
   GeoJsonSource userLocation;
   ArrayList<GeoJsonSource> sources = new ArrayList<>();
   String[] sourceNames= {"lib","mainwalls","walls", "400","401", "402", "404", "405", "403", "406","400point","401point", "402point", "404point", "405point", "403point", "406point"};
//   String[] sourceNames = {"lib", "mainWalls", "walls", "stairs", "400","401", "402", "404", "405", "403", "406", "WCF", "WCM", "Stairs1", "Stairs2", "400L", "401L", "402L", "403L", "404L", "405L", "406L", "WCML", "WCFL"};
//   String[] roomsNames = {"400","401", "402", "403", "404", "405", "406", "WCF", "WCM", "Stairs1", "Stairs2"};
public String[] roomsNames = {"400","401", "402", "403", "404", "405", "406"};
//   String [] description= {"Кабинет № 400", "Лабаратория", "Кабинет № 402", "Кабинет № 403", "Кабинет № 404", "Кабинет № 405", "Кабинет № 406", "Женский туалет", "Мужской туалет", "Лестница № 1", "Лестница № 2"};
public String [] description= {"Кабинет № 400", "Лабoратория", "Кабинет № 402", "Кабинет № 403", "Кабинет № 404", "Кабинет № 405", "Кабинет № 406"};

   public MapData(Style style, Context context){
      this.style = style;

      //подгружаем данные по отдельным кабинетам (тип: полигон) и добавляем в стиль полученные данные
//      File file = new File("asset://features.geojson");
//      String lol = loadData(context,"features.geojson");
//      //lol = FileUtils.readFileToString(file, Charsets.UTF_8);
//      System.out.println(lol);
//      FeatureCollection fColl = FeatureCollection.fromJson(lol);
//      System.out.println(fColl);
//      GeoJsonSource source = new GeoJsonSource("lib", fColl);
//      System.out.println(source);
//         GeoJsonSource source = new GeoJsonSource();




//         GeoJsonSource source;
//      style.addSource(source);
//      style.addLayer(new FillLayer());
//      System.out.println(source.querySourceFeatures(null));
         for (String sourceName:sourceNames){
//            File file = new File("asset://"+sourceName+".geojson");
            String geoJsonAsString = loadData(context,sourceName+".geojson");
//            FeatureCollection fColl = FeatureCollection.fromJson(geoJsonAsString);
            GeoJsonSource source = new GeoJsonSource(sourceName, geoJsonAsString);
          
            sources.add(source);
            style.addSource(source);
         }

      //устанавливаем стиль слоев
      style.addLayer(new FillLayer("lib", "lib").withProperties(PropertyFactory.fillColor(Color.WHITE),fillOpacity(1.0f)));
      style.addLayer(new LineLayer("mainwalls", "mainwalls").withProperties(PropertyFactory.lineGapWidth((float) 1.0),fillOpacity(1.0f)));
      style.addLayer(new LineLayer("walls", "walls").withProperties(fillOpacity(1.0f)));
//      style.addLayer(new LineLayer("stairs", "stairs").withProperties(PropertyFactory.lineColor(Color.BLACK),fillOpacity(1.0f)));
      for (String room: roomsNames) {
         style.addLayer(new FillLayer(room, room).withProperties(PropertyFactory.fillColor(Color.parseColor("#FF6F48")),fillOpacity(0.0f)));
         style.addLayer(new SymbolLayer(room+"point", room+"point").withProperties(PropertyFactory.textColor(Color.BLACK),
                 textField(room),textSize(12f), textOpacity(0.7f)));
      }

//        List<Feature> markerCoordinates = new ArrayList<>();
//        markerCoordinates.add(Feature.fromGeometry(Point.fromLngLat(104.26015672462, 52.250868099897)));
//        markerCoordinates.add(Feature.fromGeometry(Point.fromLngLat( 104.260241577899, 52.250755196808)));
//        markerCoordinates.add(Feature.fromGeometry(Point.fromLngLat(104.260276132228,	52.2508298507214)));
//        markerCoordinates.add(Feature.fromGeometry(Point.fromLngLat( 104.260522353784,52.2508988695676)));
        style.addImage("my-marker-image", BitmapFactory.decodeResource(context.getResources(), R.drawable.rec));
//        style.addSource(new GeoJsonSource("marker-source",FeatureCollection.fromFeatures(markerCoordinates)));
//        style.addLayer( new SymbolLayer("marker-layer", "marker-source")
//                .withProperties(iconImage("my-marker-image")));
   }

   public void getFeatures(MapboxMap mapboxMap, PointF finalPoint){
      this.mapboxMap = mapboxMap;
      features = new ArrayList<>();
      for (String room: roomsNames){
         features.add(this.mapboxMap.queryRenderedFeatures(finalPoint,room));
      }
   }

   public void setLocation(double longitude, double latitude){
      style.removeLayer("user-location");
      style.removeSource("user-location");

      userLocationPoint = Feature.fromGeometry(Point.fromLngLat(longitude,latitude));
      userLocation = new GeoJsonSource("user-location", userLocationPoint);
      style.addSource(userLocation);
      style.addLayer( new SymbolLayer("user-location", "user-location")
              .withProperties(iconImage("my-marker-image")));
   }

   public String loadData(Context context, String inFile) {
      String tContents = "";

      try {
         InputStream stream = context.getAssets().open(inFile);

         int size = stream.available();
         byte[] buffer = new byte[size];
         stream.read(buffer);
         stream.close();
         tContents = new String(buffer);
      } catch (IOException e) {
         // Handle exceptions here
      }

      return tContents;

   }
}