package com.example.ta.API;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BaseApiService {
    @FormUrlEncoded
    @POST("/api/lecturer/login")
    Call<ResponseBody> logindosen(@Field("username") String username,
                                  @Field("password") String password);

    //Fungsi ini untuk mengambil data kelas dosen
    @POST("/api/lecturer/examschedule")
    Call<ResponseBody> getClass(@Header("Authorization") String authToken);

}
