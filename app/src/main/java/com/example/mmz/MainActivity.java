package com.example.mmz;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // เเพิ่มรูป
        ImageView imageView = findViewById(R.id.imageView);

        String URL_im = "https://www.flashfly.net/wp/wp-content/uploads/2017/06/Jump-Paint.jpg";

        Glide.with(this)
                .load(URL_im)
                .into(imageView);

        Button button = findViewById(R.id.button);
         button.setOnClickListener(view -> {
             Intent intent = new Intent(MainActivity.this,MainActivity2.class);
             startActivity(intent);
         });
    }
}