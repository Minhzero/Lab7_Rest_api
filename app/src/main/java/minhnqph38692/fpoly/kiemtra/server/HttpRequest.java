package minhnqph38692.fpoly.kiemtra.server;

import static minhnqph38692.fpoly.kiemtra.server.ApiServices.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRequest {

    ApiServices apiServices;

    public HttpRequest(){
        apiServices = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiServices.class);
    }

    public  ApiServices callAPI(){
        return apiServices;
    }
}
