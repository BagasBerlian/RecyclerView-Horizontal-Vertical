package com.bagas.recyclerviewdetailed;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    RecyclerView horizontalrecyclerView, verticalrecyclerview;
    List<DataClass> dataList;
    List<Integer> iklanList;
    MyAdapter adapter;
    MyIklan myIklan;
    DataClass androidData;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        horizontalrecyclerView = findViewById(R.id.horizontalrecyclerView);
        verticalrecyclerview = findViewById(R.id.verticalrecyclerView);

        searchView = findViewById(R.id.search);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        horizontalrecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        verticalrecyclerview.setLayoutManager(gridLayoutManager);
        iklanList = new ArrayList<>();
        dataList = new ArrayList<>();

        iklanList.add(R.drawable.iklan1);
        iklanList.add(R.drawable.iklan2);
        iklanList.add(R.drawable.iklan3);
        iklanList.add(R.drawable.iklan4);
        iklanList.add(R.drawable.iklan5);
        iklanList.add(R.drawable.iklan6);

        androidData = new DataClass("Camera", R.string.camera, "Java", R.drawable.camera_detail);
        dataList.add(androidData);
        androidData = new DataClass("RecyclerView", R.string.recyclerview, "Kotlin", R.drawable.list_detail);
        dataList.add(androidData);
        androidData = new DataClass("Date Picker", R.string.date, "Java", R.drawable.date_detail);
        dataList.add(androidData);
        androidData = new DataClass("EditText", R.string.edit, "Kotlin", R.drawable.edit_detail);
        dataList.add(androidData);
        androidData = new DataClass("Rating Bar", R.string.rating, "Java", R.drawable.rating_detail);
        dataList.add(androidData);

        myIklan = new MyIklan(MainActivity.this, iklanList);
        adapter = new MyAdapter(MainActivity.this, dataList);
        horizontalrecyclerView.setAdapter(myIklan);
        verticalrecyclerview.setAdapter(adapter);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        horizontalrecyclerView.setLayoutManager(layoutManager);

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int position = layoutManager.findFirstCompletelyVisibleItemPosition();
                int totalItemCount = layoutManager.getItemCount();

                if (position == RecyclerView.NO_POSITION || position == totalItemCount - 1) {
                    position = 0; // Loop back to the first item when reaching the last one
                }
                horizontalrecyclerView.smoothScrollToPosition(position + 1);
                handler.postDelayed(this, 3000); // Scroll every 3 seconds
            }
        };
        handler.postDelayed(runnable, 3000);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void searchList(String text){
        List<DataClass> dataSearchList = new ArrayList<>();
        for (DataClass data : dataList){
            if (data.getDataTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()){
            Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }
    }
}