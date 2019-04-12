package com.example.user.ndktest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    public static native void ndkEmboss(int[] data, int width, int height);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv = (ImageView)findViewById(R.id.iv);
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.turtle);
        int[] pix = new int[b.getWidth() * b.getHeight()];
        b.getPixels(pix, 0, b.getWidth(), 0, 0, b.getWidth(), b.getHeight());

        ndkEmboss(pix, b.getWidth(), b.getHeight());

        Bitmap out = Bitmap.createBitmap(b.getWidth(), b.getHeight(), Bitmap.Config.ARGB_8888);
        out.setPixels(pix, 0, b.getWidth(), 0, 0, b.getWidth(), b.getHeight());
        iv.setImageBitmap(out);

        pix = null;
        b.recycle();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
}
