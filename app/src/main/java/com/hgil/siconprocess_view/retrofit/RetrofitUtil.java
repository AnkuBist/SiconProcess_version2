package com.hgil.siconprocess_view.retrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.hgil.siconprocess_view.R;
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
        loading.setContentView(R.layout.custom_loading_dialog);

       /* WindowManager.LayoutParams wmlp =
                loading.getWindow().getAttributes();*/
    /*    int height =
                (context).getResources().getDisplayMetrics().heightPixels;
        wmlp.y =
                height / 4;
        loading.getWindow().setAttributes(wmlp);*/
        loading.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        ImageView imageView = (ImageView) loading.findViewById(R.id.cheatahRunning);
        imageView.setScaleX(-1);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
        Glide.with(context).load(R.drawable.real_cheetah).into(imageViewTarget);
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


