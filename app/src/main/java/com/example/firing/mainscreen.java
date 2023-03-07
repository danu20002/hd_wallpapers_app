package com.example.firing;

import static com.example.firing.MainActivity.SHARED_PREFS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.WallpaperManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mainscreen extends AppCompatActivity {
   private ArrayList<Imagemodel> modelclasslist;
   private RecyclerView recyclerView;
   Adapter adapter;
   CardView mnature,mbus,mcar,mtrain,mtrending,logout;
   EditText editText;
   ImageButton search;
    public  static final String SHARED_PREFS="sharedprefs";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);

        recyclerView=findViewById(R.id.recyclerview);
        mnature=findViewById(R.id.nature);
        mbus=findViewById(R.id.bus);
        mcar=findViewById(R.id.car);
        mtrain=findViewById(R.id.train);
        mtrending=findViewById(R.id.trainding);
        logout=findViewById(R.id.logoutbtn);
        editText=findViewById(R.id.editingtext);
        search=findViewById(R.id.search);



        modelclasslist=new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        adapter=new Adapter(getApplicationContext(),modelclasslist);
        recyclerView.setAdapter(adapter);
        findphotos();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("name","");
                editor.apply();


                startActivity(new Intent(mainscreen.this,MainActivity.class));
                finish();
            }
        });

    }

    private void findphotos() {
        mnature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="nature";
                getsearchimage(query);
            }
        });
        mbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="bus";
                getsearchimage(query);
            }
        });
        mcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="car";
                getsearchimage(query);
            }
        });
        mtrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="train";
                getsearchimage(query);
            }
        });
        mtrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="trending";
                getsearchimage(query);
                findphotos();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query=editText.getText().toString().trim().toLowerCase();
                if(query.isEmpty()){
                    Toast.makeText(mainscreen.this, "Enter something to search ", Toast.LENGTH_SHORT).show();
                }else{
                    getsearchimage(query);
                }

            }
        });
    }

    private void getsearchimage(String query) {
        ApiUtilities.getApiInterface().getSearchImage(query,1,80).enqueue(new Callback<searchModel>() {
            @Override
            public void onResponse(Call<searchModel> call, Response<searchModel> response) {
               modelclasslist.clear();
                if(response.isSuccessful()){
                      modelclasslist.addAll(response.body().getPhotos());
                      adapter.notifyDataSetChanged();

                }else{
                    Toast.makeText(mainscreen.this, "NOT able to get use specific keywords to search", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<searchModel> call, Throwable t) {

            }
        });


    }

}