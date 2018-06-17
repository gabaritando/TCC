package br.edu.anhembi.gabaritando;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import org.opencv.android.JavaCameraView;
import java.io.FileOutputStream;
import java.io.IOException;

public class CameraPreview extends JavaCameraView implements android.hardware.Camera.PictureCallback {

    private static final String TAG = "OpenCV";
    private String mPictureFileName;


    public CameraPreview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void takePicture(final String fileName){
        Log.i(TAG,"Taking picture");
        this.mPictureFileName = fileName;
        mCamera.setPreviewCallback(null);

        mCamera.takePicture(null,null,this);

    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        Log.i(TAG, "Saving a bitmap file");
        mCamera.startPreview();
        mCamera.setPreviewCallback(this);

        try {
            FileOutputStream fos = new FileOutputStream(mPictureFileName);

            fos.write(data);
            fos.close();

        } catch (IOException e) {
            Log.e("Picture demo", "Exception in photoCallback", e);
        }

        Intent i = new Intent(getContext(), CameraCaptured.class);

        Bundle bundle = new Bundle();
        bundle.putByteArray("photo", data);
        i.putExtras(bundle);

        getContext().startActivity(i);


    }

}
