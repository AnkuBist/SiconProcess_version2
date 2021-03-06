package com.hgil.siconprocess_view.retrofit;

import com.hgil.siconprocess_view.retrofit.loginResponse.loginResponse;
import com.hgil.siconprocess_view.utils.API;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by mohan.giri on 04-01-2017.
 */

public interface RetrofitService {
  /*  @GET(API.LOGIN_URL)
    Call<loginResponse> getUserLogin(@Path("username") String username, @Path("password") String password);*/

    @FormUrlEncoded
    @POST(API.LOGIN_URL)
    Call<loginResponse> postUserLogin(@Field("login_id") String username, @Field("password") String password,
                                      @Field("imei_number") String imei_number);

    @FormUrlEncoded
    @POST(API.SYNC_URL)
    Call<loginResponse> syncRemarkPlan(@Field("login_id") String username, @Field("lastSyncDate") String lastSyncDate,
                                       @Field("imei_number") String imei_number, @Field("sync_data") String sync_data);

   /* @FormUrlEncoded
    @POST(API.SYNC_URL)
    Call<syncResponse> syncRouteData(@Field("route_id") String username, @Field("route_data") String route_data);

    @FormUrlEncoded
    @POST(API.SYNC_CASHIER_URL)
    Call<syncResponse> syncRouteCashierCheck(@Field("route_id") String username, @Field("cashier_data") String cashier_data);
*/
}
