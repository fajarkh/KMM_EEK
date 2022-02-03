package com.example.acer.kms_eek.kategori;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import android.support.v4.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.kms_eek.R;
import com.example.acer.kms_eek.base.BaseUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.widget.Toast;
import android.os.Handler;
import android.support.v7.widget.Toolbar;


import android.support.design.widget.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class KategoriActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;

    //@BindView(R.id.toolbar)
    //Toolbar toolbar;
    private Toolbar toolbar;

    // @BindView(R.id.swipe_refresh_recycler_list)
    // SwipeRefreshLayout swipeRefreshRecyclerList;

    private SwipeRefreshLayout swipeRefreshRecyclerList;
    //@BindView(R.id.fab)
    //FloatingActionButton fab;
    private FloatingActionButton fab;
    private KategoriListAdapter mAdapter;
    private RecyclerViewScrollListener scrollListener;
    private Gson gson;
    private ArrayList<EntitasKategori> modelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);
        gson = new GsonBuilder().create();
        loadBerita();
        findViews();
        initToolbar("Kategori");
        setAdapter();

        swipeRefreshRecyclerList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // Do your stuff on refresh
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (swipeRefreshRecyclerList.isRefreshing())
                            swipeRefreshRecyclerList.setRefreshing(false);
                    }
                }, 5000);

            }
        });

    }

    private void loadBerita() {
        modelList.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BaseUrl.url_berita,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {
                                //getting product object from json array
                                JSONObject jsonObject = array.getJSONObject(i);
                                EntitasKategori entitasKategori = gson.fromJson(jsonObject.toString(), EntitasKategori.class);
                                modelList.add(entitasKategori);
                            }
                            mAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipeRefreshRecyclerList = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_recycler_list);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    public void initToolbar(String title) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(title);
    }

    private void setAdapter() {
        mAdapter = new KategoriListAdapter(KategoriActivity.this, modelList, "Header");
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        scrollListener = new RecyclerViewScrollListener() {

            public void onEndOfScrollReached(RecyclerView rv) {
                Toast.makeText(KategoriActivity.this, "End of the RecyclerView reached. Do your pagination stuff here", Toast.LENGTH_SHORT).show();
                scrollListener.disableScrollListener();
            }
        };
        recyclerView.addOnScrollListener(scrollListener);

        mAdapter.SetOnItemClickListener(new KategoriListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, EntitasKategori model) {
               Toast.makeText(KategoriActivity.this, "Hey " + model.getJudul_berita(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), DetailKategoriActivity.class);
                startActivity(intent);
            }
        });

        mAdapter.SetOnHeaderClickListener(new KategoriListAdapter.OnHeaderClickListener() {
            @Override
            public void onHeaderClick(View view, String headerTitle) {
                Toast.makeText(KategoriActivity.this, "Hey I am a header", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
