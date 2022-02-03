package com.example.acer.kms_eek.history;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.kms_eek.R;
import com.example.acer.kms_eek.base.BaseUrl;
import com.example.acer.kms_eek.base.RecyclerViewScrollListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshRecyclerList;
    //FloatingActionButton fab;
    private FloatingActionButton fab;
    private HistoryListAdapter mAdapter;
    private RecyclerViewScrollListener scrollListener;
    private Gson gson;
    private ArrayList<EntitasHistory> modelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        gson = new GsonBuilder().create();
        loadHistory();
        findViews();
        initToolbar("History Kebudayaan");
        setAdapter();

        swipeRefreshRecyclerList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
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

    private void loadHistory() {
        modelList.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BaseUrl.URL_ALL_HISTORY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("response", response);
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.has("success")) {
                                JSONArray dataArray = obj.getJSONArray("data");
                                for (int i = 0; i < dataArray.length(); i++) {
                                    JSONObject jsonObject = dataArray.getJSONObject(i);
                                    EntitasHistory entitashistory = gson.fromJson(jsonObject.toString(), EntitasHistory.class);
                                    modelList.add(entitashistory);
                                }
                                mAdapter.notifyDataSetChanged();
                            }
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
        //fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    public void initToolbar(String title) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(title);
    }

    private void setAdapter() {
        mAdapter = new HistoryListAdapter(HistoryActivity.this, modelList, "Header");
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);


        scrollListener = new RecyclerViewScrollListener() {

            public void onEndOfScrollReached(RecyclerView rv) {
                Toast.makeText(HistoryActivity.this, "End of the RecyclerView reached", Toast.LENGTH_SHORT).show();
                scrollListener.disableScrollListener();
            }
        };

        recyclerView.addOnScrollListener(scrollListener);
        mAdapter.SetOnItemClickListener(new HistoryListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, EntitasHistory model) {
                Toast.makeText(HistoryActivity.this, "klik", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(), DetailHistoryActivity.class);
//                startActivity(intent);
            }
        });

        mAdapter.SetOnHeaderClickListener(new HistoryListAdapter.OnHeaderClickListener() {
            @Override
            public void onHeaderClick(View view, String headerTitle) {
                Toast.makeText(HistoryActivity.this, "Hey I am a header", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
