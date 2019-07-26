package com.example.prueba1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba1.common.SharedPreferencesManager;
import com.example.prueba1.retrofit.TeedClient;
import com.example.prueba1.retrofit.TeedService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    Button btnLogin;
    EditText etusername, etpassword;
    TeedService teedService;
    TeedClient teedClient;

    String idusuario;

    Button btnGPS;
    TextView tvLatitud;
    TextView tvLongitud;

    private LocationManager locManager;
    private Location loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofitInit();
        findViews();
        events();
    }

    private void retrofitInit() {
        teedClient = TeedClient.getInstance();
        teedService = teedClient.getTeedService();
    }

    private void findViews() {
        etusername = findViewById(R.id.editTextEmail);
        etpassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.buttonLogin);

        btnGPS = findViewById(R.id.buttonGPS);
        tvLatitud = findViewById(R.id.tvLatitud);
        tvLongitud = findViewById(R.id.tvLongitud);

    }

    private void events() {
        btnLogin.setOnClickListener(this);
        btnGPS.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.buttonLogin) {
            String username = etusername.getText().toString();
            String password = etpassword.getText().toString();

            if (username.isEmpty()){
                etusername.setError("El usuario es requerido");
            }else if(password.isEmpty()){
                etpassword.setError("La contraseña es requerida");
            }else{
                RequestLogin requestLogin = new RequestLogin(username,password);
                Call<ResponseAuth> call = teedService.doLogin(requestLogin);
                call.enqueue(new Callback<ResponseAuth>() {
                    @Override
                    public void onResponse(Call<ResponseAuth> call, Response<ResponseAuth> response) {
                        if (response.isSuccessful()){
                            String estado = response.body().getEstado();
                            String mensaje = response.body().getMensaje();
                            idusuario = response.body().getIdusuario();

                            //Toast.makeText(MainActivity.this, ""+estado, Toast.LENGTH_SHORT).show();

                            if (estado.equals("Exito")){
                                //Evaluando permisos
                                evaluando_permisos();

                            }else if(estado.equals("Error")){
                                Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(MainActivity.this, "Revise los datos de acceso", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseAuth> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Problemas de conexión", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }else if (id == R.id.buttonGPS) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {

            }else
            {
                locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                tvLatitud.setText(String.valueOf(loc.getLatitude()));
                tvLongitud.setText(String.valueOf(loc.getLongitude()));

                Log.i("cloglat",String.valueOf(loc.getLatitude()));
                Log.i("cloglong",String.valueOf(loc.getLongitude()));
            }
        }
    }

    private void evaluando_permisos() {

        //Log.d("clog1", String.valueOf(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)));
        //Log.d("clog2", String.valueOf(PackageManager.PERMISSION_GRANTED));

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //Log.d("clog3", String.valueOf(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)));

            //modal
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Se solicita el acceso al GPS del dispositivo para poder detectar si se encuentra dentro del rango permitido para marcar su asistencia.")
                    .setPositiveButton("Siguiente", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //pedir permiso
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            // Create the AlertDialog object and return it
            Dialog dialog = builder.create();
            dialog.show();

        }else{
            Toast.makeText(MainActivity.this, "Sesión iniciada correctamente", Toast.LENGTH_SHORT).show();

            SharedPreferencesManager.putString("idusuario",idusuario);

            Intent i = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Permiso exitoso
                    Toast.makeText(MainActivity.this, "Sesión iniciada correctamente", Toast.LENGTH_SHORT).show();

                    SharedPreferencesManager.putString("idusuario",idusuario);

                    Intent i = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(i);
                } else {
                    //Permiso denegado
                    Toast.makeText(this, "No deniegues pues xd", Toast.LENGTH_LONG).show();

                }
                return;
            }

        }
    }
}
