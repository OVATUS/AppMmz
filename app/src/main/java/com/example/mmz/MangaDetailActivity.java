package com.example.mmz;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;



public class MangaDetailActivity extends AppCompatActivity {
    private String mangaName;
    private String mangaPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_detail);

        ImageView imageView = findViewById(R.id.imageViewDetail);
        String imageUrl = getIntent().getStringExtra("MANGA_URL");
        mangaName = getIntent().getStringExtra("MANGA_NAME"); // ไม่ประกาศใหม่
        mangaPrice = getIntent().getStringExtra("MANGA_PRICE"); // ไม่ประกาศใหม่

        // โหลดรูปภาพมังงะ
        Glide.with(this).load(imageUrl).into(imageView);

        TextView textViewName = findViewById(R.id.textViewName);
        TextView textViewPrice = findViewById(R.id.textViewPrice);
        textViewName.setText(mangaName);
        textViewPrice.setText(mangaPrice);

        Button buttonBuy = findViewById(R.id.buttonBuy);
        buttonBuy.setOnClickListener(v -> {
            Toast.makeText(this, "ซื้อสำเร็จ: " + mangaName + " ราคา: " + mangaPrice, Toast.LENGTH_SHORT).show();
            finish(); // กลับไปหน้าก่อน
        });
    }
}
