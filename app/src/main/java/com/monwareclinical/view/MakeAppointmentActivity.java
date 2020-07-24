package com.monwareclinical.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.DragEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.monwareclinical.R;
import com.ncorti.slidetoact.SlideToActView;

public class MakeAppointmentActivity extends AppCompatActivity {
    SlideToActView slideToActView;
    EditText editTitulo;
    EditText editFecha;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment);

        slideToActView = (SlideToActView) findViewById(R.id.sbConfirmation);
        context = this;
        editFecha = (EditText) findViewById(R.id.etFecha);
        editTitulo = (EditText)findViewById(R.id.etTituloR);
        slideToActView.setLocked(true);

        editTitulo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editFecha.length() == 0 && editFecha.length() == 0){
                    slideToActView.setLocked(true);
                } else{
                    slideToActView.setLocked(false);
                }
            }
        });

        editFecha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            if (editFecha.length() == 0 && editFecha.length() == 0){
                slideToActView.setLocked(true);
            } else{
                slideToActView.setLocked(false);
            }
            }
        });
        //slideToActView.setLocked(true);
        //slideToActView.setAnimDuration(0);
        slideToActView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (editFecha.length() != 0 && editTitulo.length() != 0){
                    if (slideToActView.isLocked()){
                        Toast.makeText(context,"Debes llenarlos los campos",Toast.LENGTH_SHORT).show();
                    }else {

                       // Toast.makeText(context,"Asegura que tus datos esten correctos",Toast.LENGTH_SHORT).show();
                    }


            }
        });




       slideToActView.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
           @Override
           public void onSlideComplete(SlideToActView slideToActView) {
               if (slideToActView.isLocked()) {
                   Toast.makeText(context, "debes llenar los espacios", Toast.LENGTH_SHORT).show();
               } else {


                   try {

                       //Aqui se almacena en la base de datos
                       Toast.makeText(context, "Datos enviados", Toast.LENGTH_SHORT).show();
                   } catch (Exception e) {
                       Toast.makeText(context, "Error desconocido, intenta mas tarde", Toast.LENGTH_SHORT).show();
                   }
               }
           }
       });




    }
}