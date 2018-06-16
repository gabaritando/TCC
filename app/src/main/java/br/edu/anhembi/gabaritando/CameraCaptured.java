package br.edu.anhembi.gabaritando;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import br.edu.anhembi.gabaritando.CameraPreview;
import br.edu.anhembi.gabaritando.R;

public class CameraCaptured extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_captured);

        Bundle extras = getIntent().getExtras();
        byte[] photo = extras.getByteArray("photo");

        Bitmap bitmap  = BitmapFactory.decodeByteArray (photo, 0, photo.length);
        ImageView imgView = (ImageView)findViewById(R.id.imgPreview);
        imgView.setRotation(imgView.getRotation() + 90);

        imgView.setImageBitmap(bitmap);







    }

}
