package com.example.prueba1.retrofit;


import com.example.prueba1.common.Constantes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeedClient {
    private static TeedClient instance = null;
    private TeedService teedService;
    private Retrofit retrofit;

    public TeedClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.API_TEED_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        teedService = retrofit.create(TeedService.class);
    }

    //Patron Singleton
    public static TeedClient getInstance(){
        if (instance == null){
            instance = new TeedClient();
        }
        return instance;
    }

    public TeedService getTeedService(){
        return teedService;
    }
}
