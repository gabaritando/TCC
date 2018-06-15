package br.edu.anhembi.gabaritando;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.ImageView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;


public class Gabaritos extends AppCompatActivity implements CvCameraViewListener2  {

    private static final String TAG = "MYAPP::OPENCV";
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private CameraBridgeViewBase mOpenCvCameraView;


    BaseLoaderCallback mCallBack = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status){
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "OpenCV loaded successfully");
                    mOpenCvCameraView.enableView();
                    break;
                }
                default:
                {
                    super.onManagerConnected(status);
                    break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gabaritos);

        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.HelloOpenCvView);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mCallBack);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mCallBack.onManagerConnected(mCallBack.SUCCESS);
        }
    }

    @Override
    public void onCameraViewStarted(int width, int height) {

    }

    @Override
    public void onCameraViewStopped() {

    }


    /*
     * Chamado quando é preciso ser feito a entrega de um frame,
     * dentro deste método vamos receber o frame capturado pela câmera,
     * fazer o processamento, e depois entregá-lo para ser exibido pela nossa aplicação.
     */

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {

        Mat rgba = inputFrame.rgba();
        Mat mRgbaT = rgba.t();
        Mat gray = new Mat();
        Mat canny = new Mat();

        Core.flip(rgba.t(), mRgbaT, 1);
        Imgproc.resize(mRgbaT, mRgbaT, rgba.size());

        Imgproc.cvtColor(mRgbaT, gray, Imgproc.COLOR_RGB2GRAY);

        Imgproc.Canny(gray, canny, 100,80);

        return canny;

    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            ImageView mImageCaptured = findViewById(R.id.mImageCaptured);

            mImageCaptured.setImageBitmap(imageBitmap);
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }*/

}
