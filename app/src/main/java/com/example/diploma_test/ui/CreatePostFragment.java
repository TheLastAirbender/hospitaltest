package com.example.diploma_test.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.diploma_test.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class CreatePostFragment extends Fragment{
    private EditText editText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_createpost, container, false);

        editText = (EditText) view.findViewById(R.id.createNewPostEditText);
       // editText.setText("INSIDE");
        return view;
    }

}
