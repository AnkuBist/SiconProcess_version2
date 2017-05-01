package com.hgil.siconprocess_view.retrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.hgil.siconprocess_view.utils.API;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mohan.giri on 04-01-2017.
 */

public class RetrofitUtil {

    private static ProgressDialog loading = null;

    public static RetrofitService retrofitClient() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(300, TimeUnit.SECONDS)
                .connectTimeout(300, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);
        return service;
    }

    public static void showDialog(Context context, String title) {
        loading = ProgressDialog.show(context, title, "Please Wait...", false, false);
    }

    public static void updateDialogTitle(String title) {
        loading.setTitle(title);
    }

    public static void hideDialog() {
        if (loading != null && loading.isShowing())
            loading.dismiss();
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}


