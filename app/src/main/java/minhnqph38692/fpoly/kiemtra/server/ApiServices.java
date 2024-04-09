package minhnqph38692.fpoly.kiemtra.server;

import java.util.ArrayList;

import minhnqph38692.fpoly.kiemtra.modal.KiemtraDTO;
import minhnqph38692.fpoly.kiemtra.modal.ResponseDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiServices {

    public static  String BASE_URL="http://10.0.2.2:3000/api/";

    @GET("get-list-KT")
    Call<ResponseDTO<ArrayList<KiemtraDTO>>> getListKT();

    @POST("add-kiemtra")
    Call<ResponseDTO<KiemtraDTO>>  addKT(@Body KiemtraDTO kiemtraDTO);

    @PUT("update-kiemtra/{id}")
    Call<ResponseDTO<KiemtraDTO>> updateKtById(@Path("id") String id, KiemtraDTO kiemtraDTO);

    @DELETE("delete-Kiemtra/{id}")
    Call<ResponseDTO<KiemtraDTO>> deleteKTById(@Path("id") String id);

}
