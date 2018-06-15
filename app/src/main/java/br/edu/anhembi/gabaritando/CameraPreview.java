package br.edu.anhembi.gabaritando;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import org.opencv.android.JavaCameraView;
import java.io.FileOutputStream;

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
    public void onPictureTaken(byte[] data, android.hardware.Camera camera) {
        Log.i(TAG, "Saving a bitmap file");
        mCamera.startPreview();
        mCamera.setPreviewCallback(this);

        try {
            FileOutputStream fos = new FileOutputStream(mPictureFileName);

            fos.write(data);
            fos.close();
        } catch (java.io.IOException e) {
            Log.e("Picture demo", "Exception in photoCallback", e);
        }


    }
}
