package com.example.prueba1.retrofit;

import com.example.prueba1.RequestLogin;
import com.example.prueba1.ResponseAuth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TeedService {
    @POST("login.php?op=verificar")
    Call<ResponseAuth> doLogin(@Body RequestLogin requestLogin);

}
