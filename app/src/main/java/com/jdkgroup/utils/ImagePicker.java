package com.jdkgroup.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.widget.AppCompatImageView;
import android.widget.Toast;
import com.jdkgroup.customview.rximagepicker.RxImageConverters;
import com.jdkgroup.customview.rximagepicker.RxImagePicker;
import com.jdkgroup.customview.rximagepicker.Sources;
import java.io.File;
import io.reactivex.Observable;

public class ImagePicker {

    public void pickImageFromSource(Activity activity, Sources source, AppCompatImageView appIv, int no) {
        RxImagePicker.with(activity.getFragmentManager()).requestImage(source)
                .flatMap(uri -> {
                    switch (no) {
                        case 1:
                            return RxImageConverters.uriToFile(activity, uri, createTempFile(activity));
                        case 2:
                            return RxImageConverters.uriToBitmap(activity, uri);
                        default:
                            return Observable.just(uri);
                    }
                })
                .subscribe(this::onImagePicked, throwable -> Toast.makeText(activity, String.format("Error: %s", throwable), Toast.LENGTH_LONG).show());
    }

    private void onImagePicked(Object result) {
        System.out.println("Tag" + result);
        if (result instanceof Bitmap) {

        } else {

        }
    }

    private File createTempFile(Activity activity) {
        return new File(activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES), System.currentTimeMillis() + "_image.jpeg");
    }

}
