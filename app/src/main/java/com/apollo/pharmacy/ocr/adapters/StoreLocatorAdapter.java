package com.apollo.pharmacy.ocr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.fragments.MyListFragment;
import com.apollo.pharmacy.ocr.model.PharmacyLocatorCustomList;

import org.jetbrains.annotations.NotNull;

import java.util.List;

//import com.apollo.pharmacy.ocr.fragments.GlobalFragmentManager;

public class StoreLocatorAdapter extends RecyclerView.Adapter<StoreLocatorAdapter.MyViewHolder> {

    private List<PharmacyLocatorCustomList> locationList;
    Context context;

    public StoreLocatorAdapter(Context context, List<PharmacyLocatorCustomList> locationList) {
        this.locationList = locationList;
        this.context = context;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_store_locator_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PharmacyLocatorCustomList movie = locationList.get(position);
        holder.title.setText(movie.getStoreaddress());
        holder.distance.setText(movie.getStoredistance());

        holder.showMap.setOnClickListener(v -> {
            if (holder.isClicked) {
                holder.mapLayout.setVisibility(View.GONE);
                holder.isClicked = false;
                holder.showMap.setText("show on map");
            } else {
                MyListFragment fragmentEliminaPost = new MyListFragment();
                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(holder.gmapLayout.getId(), fragmentEliminaPost, "").commit();
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(holder.gmapLayout.getId(), fragmentEliminaPost);
                fragmentTransaction.commit();
                holder.mapLayout.setVisibility(View.VISIBLE);
                holder.isClicked = true;
                holder.showMap.setText("hide map");
            }
        });
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, distance;
        public Button showMap;
        LinearLayout mapLayout;
        FrameLayout gmapLayout;
        Boolean isClicked = false;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.locate_pharmacy_constanttext);
            distance = view.findViewById(R.id.store_distanace_textview);

            showMap = view.findViewById(R.id.locate_pharmacy_showonmap_button);
            gmapLayout = view.findViewById(R.id.gmapLayout);
            mapLayout = view.findViewById(R.id.map_layout);
        }
    }
}