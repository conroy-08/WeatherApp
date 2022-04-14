package com.example.weather.fragment.settings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.weather.R;
import com.example.weather.fragment.FragmentListener;


public class SettingsFragment extends DialogFragment {


    private EditText mCity;
    private EditText mNumberDays;
    private FragmentListener listener;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.settings_fragment,null);
        dialog.setView(view)
                .setTitle("Settings")
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })
        .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String cityName = mCity.getText().toString();
                String numberDays = mNumberDays.getText().toString();
                listener.applyTexts(cityName,numberDays);
            }
        });

        mCity = view.findViewById(R.id.edit_settings_city);
        mNumberDays = view.findViewById(R.id.edit_settings_numberdays);

        return dialog.create();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (FragmentListener) context;
        }catch (ClassCastException e){
            //e.printStackTrace();
            throw new ClassCastException(context.toString() +"must implement SettingsFragmentListener ");
        }
    }



}
