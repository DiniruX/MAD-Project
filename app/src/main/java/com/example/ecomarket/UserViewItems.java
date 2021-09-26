package com.example.ecomarket;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserViewItems extends AppCompatActivity implements ItemRVAdapter.ItemClickInterface {private RecyclerView itemRV;
    private ProgressBar loadingPB;
    private FloatingActionButton addFAB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<com.example.ecomarket.ItemRVModal> itemRVModalArrayList;
    private RelativeLayout bottomSheetRL;
    private ItemRVAdapter itemRVAdapter;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_items);

        itemRV = findViewById(R.id.idRVItems);
        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("ItemsCategory1");
        itemRVModalArrayList = new ArrayList<>();
        bottomSheetRL = findViewById(R.id.idRLBSheet);
        mAuth = FirebaseAuth.getInstance();
        itemRVAdapter = new ItemRVAdapter(itemRVModalArrayList,this,this);
        itemRV.setLayoutManager(new LinearLayoutManager(this));
        itemRV.setAdapter(itemRVAdapter);

        getAllItems();
    }

    private void getAllItems(){
        itemRVModalArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                itemRVModalArrayList.add(snapshot.getValue(com.example.ecomarket.ItemRVModal.class));
                itemRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull  DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                itemRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull  DataSnapshot snapshot) {
                loadingPB.setVisibility(View.GONE);
                itemRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable  String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                itemRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        displayBottomSheet(itemRVModalArrayList.get(position));
    }
    private void displayBottomSheet(com.example.ecomarket.ItemRVModal itemRVModal){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.user_item_bottom_sheet_dialog,bottomSheetRL);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        TextView itemNameTV = layout.findViewById(R.id.idTVItemName);
        TextView itemDescTV = layout.findViewById(R.id.idTVDescription);
        TextView itemCategoryTV = layout.findViewById(R.id.idTVCategory);
        TextView itemPriceTV = layout.findViewById(R.id.idTVPrice);
        ImageView itemIV = layout.findViewById(R.id.idIVItem);
        Button buyBtn = layout.findViewById(R.id.idBtnBuy);


        itemNameTV.setText(itemRVModal.getItemName());
        itemDescTV.setText(itemRVModal.getItemDescription());
        itemCategoryTV.setText(itemRVModal.getItemCategory());
        itemPriceTV.setText("Rs. "+itemRVModal.getItemPrice());
        Picasso.get().load(itemRVModal.getItemImage()).into(itemIV);


        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserViewItems.this, allPaymentsActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

}