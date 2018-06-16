package br.edu.anhembi.gabaritando;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import br.edu.anhembi.gabaritando.helpers.BitmapHelper;

public class CameraCaptured extends AppCompatActivity implements View.OnClickListener {

    Bitmap bitmap;
    ImageView imgView;
    Button btnApplyFilter;
    ColorMatrixColorFilter cf;
    Mat rgba, edges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_captured);

        btnApplyFilter = (Button) findViewById(R.id.btnApplyFilter);
        btnApplyFilter.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        byte[] photo = extras.getByteArray("photo");

        bitmap  = BitmapFactory.decodeByteArray (photo, 0, photo.length);
        imgView = (ImageView)findViewById(R.id.imgPreview);

        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);

        cf = new ColorMatrixColorFilter(matrix);

        imgView.setRotation(imgView.getRotation() + 90);
        imgView.setImageBitmap(bitmap);


    }

    @Override
    public void onClick(View v) {
        /*imgView.setColorFilter(cf);
        imgView.setImageAlpha(128);*/

        rgba = new Mat();
        Utils.bitmapToMat(bitmap, rgba);

        edges = new Mat(rgba.size(), CvType.CV_8UC1);

        Imgproc.cvtColor(rgba, edges, Imgproc.COLOR_RGB2GRAY, 4);
        Imgproc.Canny(edges, edges, 80, 100);

        BitmapHelper.showBitmap(this, bitmap, imgView);
        Bitmap resultBitmap = Bitmap.createBitmap(edges.cols(), edges.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(edges, resultBitmap);
        BitmapHelper.showBitmap(this, resultBitmap, imgView);

    }
}
