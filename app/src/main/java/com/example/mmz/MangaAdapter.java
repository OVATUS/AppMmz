package com.example.mmz;
import java.util.ArrayList;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.mmz.model.Manga;
import java.util.List;

public class MangaAdapter extends RecyclerView.Adapter<MangaAdapter.ViewHolder> {
    private List<Manga> mMangaList;
    private List<Manga> fullMangaList; // เก็บข้อมูลต้นฉบับทั้งหมด

    public MangaAdapter(List<Manga> mangaList) {
        this.mMangaList = new ArrayList<>(mangaList); // สร้างสำเนาเพื่อใช้สำหรับกรอง
        this.fullMangaList = new ArrayList<>(mangaList); // เก็บรายการต้นฉบับทั้งหมด
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_manga, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Manga manga = mMangaList.get(position);
        holder.textViewName.setText(manga.getName());
        holder.textViewPrice.setText(manga.getPrice());
        Glide.with(holder.imageViewManga.getContext())
                .load(manga.getImageUrl())
                .into(holder.imageViewManga);

        // ตั้งค่า ClickListener สำหรับรูปมังงะ
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), MangaDetailActivity.class);
            intent.putExtra("MANGA_URL", manga.getImageUrl());
            intent.putExtra("MANGA_NAME", manga.getName()); // ส่งชื่อมังงะ
            intent.putExtra("MANGA_PRICE", manga.getPrice()); // ส่งราคามังงะ
            holder.itemView.getContext().startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return mMangaList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewManga;
        public TextView textViewName;
        public TextView textViewPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewManga = itemView.findViewById(R.id.imageViewManga);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
        }
    }

    // เพิ่มฟังก์ชันสำหรับการกรองข้อมูล
    public void filter(String text) {
        List<Manga> filteredList = new ArrayList<>();

        if (text.isEmpty()) {
            filteredList.addAll(fullMangaList); // fullMangaList คือรายการมังงะที่เก็บข้อมูลทั้งหมดไว้
        } else {
            text = text.toLowerCase();
            for (Manga item : fullMangaList) {
                if (item.getName().toLowerCase().contains(text)) {
                    filteredList.add(item);
                }
            }
        }

        mMangaList.clear();
        mMangaList.addAll(filteredList);
        notifyDataSetChanged(); // แจ้ง RecyclerView ว่ามีการเปลี่ยนแปลงข้อมูล
    }
}
