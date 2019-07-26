package com.example.prueba1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.prueba1.common.Constantes;
import com.example.prueba1.common.SharedPreferencesManager;

public class Servicio2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio2);

        String idusuario = SharedPreferencesManager.getString("idusuario");
        Toast.makeText(this, "Idusuario: "+idusuario, Toast.LENGTH_LONG).show();
    }
}
