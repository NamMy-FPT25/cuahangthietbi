package com.example.onlineapplicationstore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlineapplicationstore.R;
import com.example.onlineapplicationstore.adapter.DienthoaiAdapter;
import com.example.onlineapplicationstore.adapter.LaptopAdapter;
import com.example.onlineapplicationstore.model.Sanpham;
import com.example.onlineapplicationstore.ultil.CheckConnection;
import com.example.onlineapplicationstore.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class LapTopActivity extends AppCompatActivity {

    Toolbar toolbarlaptop;
    ListView lvlaptop;
    LaptopAdapter laptopAdapter;
    ArrayList<Sanpham> manglaptop;
    int IDlaptop = 0;
    int page = 1;
    View footerview;
    boolean isLoading = false;
    boolean limitadata = false;
    mHandler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lap_top);
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            Anhxa();
            GetIdloaisp();
            ActionToolbar();
            GetData(page);
            LoadMoreData();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"ban hay kiem tra lai internet");
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), com.example.onlineapplicationstore.activity.Giohang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void LoadMoreData() {

        lvlaptop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),ChiTietSanPham.class);
                intent.putExtra("thongtinsanpham",manglaptop.get(i));
                startActivity(intent);
            }
        });
        lvlaptop.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int FirstItem, int VisibleItem, int TotalItem) {
                if (FirstItem + VisibleItem == TotalItem && TotalItem != 0 && isLoading == false && limitadata == false){
                    isLoading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();
                }
            }
        });
    }

    private void Anhxa() {
        toolbarlaptop = (Toolbar) findViewById(R.id.toolbarlaptop);
        lvlaptop = (ListView) findViewById(R.id.listviewlaptop);
        manglaptop = new ArrayList<>();
        laptopAdapter = new LaptopAdapter(getApplicationContext(),manglaptop);
        lvlaptop.setAdapter(laptopAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar,null);
        mHandler = new mHandler();
    }

    private void GetIdloaisp() {
        IDlaptop = getIntent().getIntExtra("idloaisanpham",-1);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarlaptop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarlaptop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = Server.Duongdandienthoai + String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int ID = 0;
                String Tenlaptop = "";
                int Gialaptop = 0;
                String Hinhanhlaptop = "";
                String Motalaptop = "";
                int IDsplaptop = 0;
                if (response != null && response.length() != 2){
                    lvlaptop.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            ID = jsonObject.getInt("ID");
                            Tenlaptop = jsonObject.getString("tensp");
                            Gialaptop = jsonObject.getInt("giasp");
                            Hinhanhlaptop = jsonObject.getString("hinhanhsp");
                            Motalaptop = jsonObject.getString("motasp");
                            IDsplaptop = jsonObject.getInt("IDsanpham");
                            manglaptop.add(new Sanpham(ID,Tenlaptop,Gialaptop,Hinhanhlaptop,Motalaptop,IDsplaptop));
                            laptopAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {
                    limitadata = true;
                    lvlaptop.removeFooterView(footerview);
                    CheckConnection.ShowToast_Short(getApplicationContext(),"da het du lieu");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String,String>();
                param.put("IDsanpham",String.valueOf(IDlaptop));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    public  class mHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    lvlaptop.addFooterView(footerview);
                    break;
                case 1:
                    GetData(++page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    public class ThreadData extends Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }
}