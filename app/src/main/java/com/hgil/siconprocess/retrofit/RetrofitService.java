package com.hgil.siconprocess.retrofit;

import com.hgil.siconprocess.retrofit.loginResponse.loginResponse;
import com.hgil.siconprocess.retrofit.loginResponse.syncResponse;
import com.hgil.siconprocess.utils.API;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by mohan.giri on 04-01-2017.
 */

public interface RetrofitService {
  /*  @GET(API.LOGIN_URL)
    Call<loginResponse> getUserLogin(@Path("username") String username, @Path("password") String password);*/

    @FormUrlEncoded
    @POST(API.LOGIN_URL)
    Call<loginResponse> postUserLogin(@Field("route_id") String username, @Field("password") String password);

    /*@FormUrlEncoded
    @POST(API.LOGIN_URL)
    Call<ResponseBody> testLogin(@Field("username") String username, @Field("password") String password);*/

    @FormUrlEncoded
    @POST(API.SYNC_URL)
    Call<syncResponse> syncRouteData(@Field("route_id") String username, @Field("route_data") String route_data);
}
