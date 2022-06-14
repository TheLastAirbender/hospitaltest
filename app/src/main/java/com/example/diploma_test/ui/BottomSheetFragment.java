package com.example.diploma_test.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.diploma_test.R;
import com.example.diploma_test.entity.CabinetMissing;
import com.example.diploma_test.viewmodel.MapViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.firestore.core.SyncEngine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class BottomSheetFragment extends BottomSheetDialogFragment {
   MapViewModel mapViewModel;
   TextView bottomsheetHeader;

   public BottomSheetFragment() {

   }

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
   }

   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//      mapViewModel = new ViewModelProvider(this).get(MapViewModel.class);
      View root = inflater.inflate(R.layout.bottom_sheet, container, false);
      bottomsheetHeader = (TextView) root.findViewById(R.id.bottom_sheet_header);

      mapViewModel = MapViewModel.getInstance(getActivity().getApplication());
      mapViewModel.getCurrentRoom().observe(this, new Observer<CabinetMissing>() {
         @Override
         public void onChanged(CabinetMissing cabinetMissing) {
            System.out.println(cabinetMissing.getDescription());
            bottomsheetHeader.setText(cabinetMissing.getDescription());
         }
      });

      return root;

   }
}