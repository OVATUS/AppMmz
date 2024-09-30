package com.example.mmz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;
import com.example.mmz.model.Manga;
import java.util.List;
import android.content.Intent;
import androidx.appcompat.widget.SearchView;
public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // ตั้งค่า RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // สร้างรายการมังงะ
        List<Manga> fullMangaList = new ArrayList<>();
        fullMangaList.add(new Manga("zee", "$10", "https://cdn.pixabay.com/photo/2024/07/06/19/22/ai-generated-8877663_1280.jpg"));
        fullMangaList.add(new Manga("pormmaifa", "$12", "https://f.ptcdn.info/549/004/000/1366997169-01-o.jpg"));
        fullMangaList.add(new Manga("nontapan", "$15", "https://f.ptcdn.info/549/004/000/1366998059-03-o.jpg"));
        fullMangaList.add(new Manga("My hero", "$14", "https://f.ptcdn.info/549/004/000/1366997729-02-o.jpg"));
        fullMangaList.add(new Manga("DUHU", "$19", "https://f.ptcdn.info/550/004/000/1366999256-05-o.jpg"));
        fullMangaList.add(new Manga("Something", "$16", "https://f.ptcdn.info/553/004/000/1367026771-wolfgirlbl-o.jpg"));
        fullMangaList.add(new Manga("AAAA", "$11", "https://f.ptcdn.info/554/004/000/1367030536-cover-o.jpg"));



        // สร้าง Adapter และตั้งค่าให้กับ RecyclerView
        MangaAdapter adapter = new MangaAdapter(fullMangaList);
        recyclerView.setAdapter(adapter);

        // ตั้งค่า SearchView
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query); // เรียก filter เมื่อค้นหา
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText); // เรียก filter เมื่อมีการเปลี่ยนข้อความในช่องค้นหา
                return true;
            }
        });

    }

    // เมื่อคลิกที่รูปมังงะ
    private void onMangaClick(Manga manga) {
        Intent intent = new Intent(this, MangaDetailActivity.class);
        intent.putExtra("MANGA_URL", manga.getImageUrl());
        intent.putExtra("MANGA_NAME", manga.getName());
        intent.putExtra("MANGA_PRICE", manga.getPrice());
        startActivity(intent);
    }
}





