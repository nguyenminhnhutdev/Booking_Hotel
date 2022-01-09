package com.example.booking_hotel.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.booking_hotel.Home;
import com.example.booking_hotel.MainActivity;
import com.example.booking_hotel.R;
import com.example.booking_hotel.adapter.AdapterHome.Rcv_noibatAdapter;
import com.example.booking_hotel.adapter.AdapterHome.Rcv_noibatModel;
import com.example.booking_hotel.adapter.Recyclerview_Search;
import com.example.booking_hotel.adapter.Recyclerview_noibat;
import com.example.booking_hotel.adapter.Recyclerview_điaiem;
import com.example.booking_hotel.adapter.RoomAdapter;
import com.example.lib.Data.Model.Room;
import com.example.lib.Data.Model.Room1;
import com.example.lib.Data.Remote.ApiUtils;
import com.example.lib.Data.Remote.Method;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class home extends Fragment {
    ListView listView;
    ArrayList<Room> list;
    ArrayList<Room1> list1;
    RoomAdapter adapter;
    Method method;
    RecyclerView rcv_noibat,rcv_diadiemdep,rvc_danhmuc;
    RecyclerView.Adapter rcv_adapter;
Recyclerview_noibat recyclerview_noibat;
Recyclerview_điaiem recyclerview_điaiem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        rcv_noibat = (RecyclerView) view.findViewById(R.id.rcv_noibat);
        rcv_diadiemdep = (RecyclerView) view.findViewById(R.id.rcv_diadiemdep);
        rvc_danhmuc = (RecyclerView) view.findViewById(R.id.rvc_danhmuc);

        // listView=view.findViewById(R.id.list_noibat); // thuận ẩn code này
//        adapter = new RoomAdapter(getContext(), R.layout.item_home_noibat);
       getRoom();
        getRoomdd();
       // load_rcv_noibat();
       // load_rcv_diadiemdep();
        load_rcv_danhmuc();
     //   getRoom();

        return view;
    }

//    public void load_rcv_noibat(){
//        rcv_noibat.setHasFixedSize(true);
//        rcv_noibat.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//
//        ArrayList<Rcv_noibatModel> rcv_noibatLocation = new ArrayList<>();
//
//        rcv_noibatLocation.add(new Rcv_noibatModel(R.drawable.viewhomestay3,"Đà Lạt", "Địa điểm đẹp thích hợp cho các cặp đôi"));
//        rcv_noibatLocation.add(new Rcv_noibatModel(R.drawable.viewhomestay2,"Đà Lạt", "Địa điểm đẹp thích hợp cho các cặp đôi"));
//        rcv_noibatLocation.add(new Rcv_noibatModel(R.drawable.viewhomestay4,"Đà Lạt", "Địa điểm đẹp thích hợp cho các cặp đôi"));
//        rcv_noibatLocation.add(new Rcv_noibatModel(R.drawable.viewhomestay5,"Đà Lạt", "Địa điểm đẹp thích hợp cho các cặp đôi"));
//
//        rcv_adapter = new Rcv_noibatAdapter(rcv_noibatLocation);
//        rcv_noibat.setAdapter(rcv_adapter);
//    }

//    public void load_rcv_diadiemdep(){
//        rcv_diadiemdep.setHasFixedSize(true);
//        rcv_diadiemdep.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//
//        ArrayList<Rcv_noibatModel> rcv_noibatLocation = new ArrayList<>();
//
//        rcv_noibatLocation.add(new Rcv_noibatModel(R.drawable.viewhomestay3,"Đà Lạt", "Địa điểm đẹp thích hợp cho các cặp đôi"));
//        rcv_noibatLocation.add(new Rcv_noibatModel(R.drawable.viewhomestay2,"Đà Lạt", "Địa điểm đẹp thích hợp cho các cặp đôi"));
//        rcv_noibatLocation.add(new Rcv_noibatModel(R.drawable.viewhomestay4,"Đà Lạt", "Địa điểm đẹp thích hợp cho các cặp đôi"));
//        rcv_noibatLocation.add(new Rcv_noibatModel(R.drawable.viewhomestay5,"Đà Lạt", "Địa điểm đẹp thích hợp cho các cặp đôi"));
//        rcv_noibatLocation.add(new Rcv_noibatModel(R.drawable.viewhomestay1,"Đà Lạt", "Địa điểm đẹp thích hợp cho các cặp đôi"));
//        rcv_noibatLocation.add(new Rcv_noibatModel(R.drawable.viewhomestay6,"Đà Lạt", "Địa điểm đẹp thích hợp cho các cặp đôi"));
//        rcv_noibatLocation.add(new Rcv_noibatModel(R.drawable.viewhomestay7,"Đà Lạt", "Địa điểm đẹp thích hợp cho các cặp đôi"));
//        rcv_noibatLocation.add(new Rcv_noibatModel(R.drawable.viewhomestay8,"Đà Lạt", "Địa điểm đẹp thích hợp cho các cặp đôi"));
//
//        rcv_adapter = new Rcv_noibatAdapter(rcv_noibatLocation);
//        rcv_diadiemdep.setAdapter(rcv_adapter);
//    }

    public void load_rcv_danhmuc(){
        rvc_danhmuc.setHasFixedSize(true);
        rvc_danhmuc.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        ArrayList<Rcv_noibatModel> rcv_noibatLocation = new ArrayList<>();

        rcv_noibatLocation.add(new Rcv_noibatModel(R.drawable.viewhomestay3,"Đà Lạt", "Địa điểm đẹp thích hợp cho các cặp đôi"));
        rcv_noibatLocation.add(new Rcv_noibatModel(R.drawable.viewhomestay2,"Đà Lạt", "Địa điểm đẹp thích hợp cho các cặp đôi"));
        rcv_noibatLocation.add(new Rcv_noibatModel(R.drawable.viewhomestay4,"Đà Lạt", "Địa điểm đẹp thích hợp cho các cặp đôi"));
        rcv_noibatLocation.add(new Rcv_noibatModel(R.drawable.viewhomestay5,"Đà Lạt", "Địa điểm đẹp thích hợp cho các cặp đôi"));
        rcv_noibatLocation.add(new Rcv_noibatModel(R.drawable.viewhomestay10,"Đà Lạt", "Địa điểm đẹp thích hợp cho các cặp đôi"));
        rcv_noibatLocation.add(new Rcv_noibatModel(R.drawable.viewhomestay9,"Đà Lạt", "Địa điểm đẹp thích hợp cho các cặp đôi"));
        rcv_noibatLocation.add(new Rcv_noibatModel(R.drawable.viewhomestay,"Đà Lạt", "Địa điểm đẹp thích hợp cho các cặp đôi"));
        rcv_noibatLocation.add(new Rcv_noibatModel(R.drawable.viewhomestay7,"Đà Lạt", "Địa điểm đẹp thích hợp cho các cặp đôi"));

        rcv_adapter = new Rcv_noibatAdapter(rcv_noibatLocation);
        rvc_danhmuc.setAdapter(rcv_adapter);
    }




//    public void getRoom () {
//        Method method = ApiUtils.getSOService();
//        list = new ArrayList<>();
//        method.getRoom().enqueue(new Callback<List<Room>>() {
//            @Override
//            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
//                list = (ArrayList<Room>) response.body();
//                adapter.addAll(list);
//                listView.setAdapter(adapter);
//            //    Log.v("test",list[0])
//            }
//
//            @Override
//            public void onFailure(Call<List<Room>> call, Throwable t) {
//
//            }
//        });

//    }

    private void getRoom () {
         method = ApiUtils.getSOService();
        list = new ArrayList<>();
        method.getRoom().enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                list = (ArrayList<Room>) response.body();
                rcv_noibat .setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
                recyclerview_noibat = new Recyclerview_noibat(getContext(), list);
                rcv_noibat.setLayoutManager(layoutManager);

                rcv_noibat.setAdapter(recyclerview_noibat);
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {

            }
        });
    }
    private void getRoomdd () {
         method = ApiUtils.getSOService();
        list1 = new ArrayList<>();
        method.getRoomdd().enqueue(new Callback<List<Room1>>() {
            @Override
            public void onResponse(Call<List<Room1>> call, Response<List<Room1>> response) {
                list1 = (ArrayList<Room1>) response.body();
                rcv_diadiemdep .setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
               recyclerview_điaiem  = new Recyclerview_điaiem(getContext(), list1);
                rcv_diadiemdep.setLayoutManager(layoutManager);

                rcv_diadiemdep.setAdapter(recyclerview_điaiem);
            }

            @Override
            public void onFailure(Call<List<Room1>> call, Throwable t) {

            }
        });
    }
}