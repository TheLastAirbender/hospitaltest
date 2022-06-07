package com.example.diploma_test.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.diploma_test.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class WikiFragment extends Fragment {

    // набор данных, которые свяжем со списком
    String[] countries = { "Отделения", "Кабинеты", "Врачи", "Болезни", "Лекарственные средства"};

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_wiki, container, false);

        // получаем элемент ListView
        ListView countriesList = root.findViewById(R.id.countriesList);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(),
                android.R.layout.simple_list_item_1, countries);

        // устанавливаем для списка адаптер
        countriesList.setAdapter(adapter);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_wiki);

        return root;
    }
}
