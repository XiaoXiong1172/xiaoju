package com.example.xiaoju.ui.dialog;


import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.example.xiaoju.R;
import com.example.xiaoju.ui.MainActivity;


public class DialogManager {

    public static AlertDialog getProgressDialog() {
        View view = LayoutInflater.from(MainActivity.getActivity()).inflate(R.layout.mydialog,null,false);
        return new AlertDialog.Builder(MainActivity.getActivity())
                .setView(view)
                .create();
    }
}
