package com.example.ecomarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditItemCat1 extends AppCompatActivity {

    private TextInputEditText ItemNameEdit,ItemPriceEdit,ItemCategoryEdit,ItemImageEdit,ItemLinkEdit,ItemDescriptionEdit;
    private Button updateItemBtn,deleteItemBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String ItemID;
    private ItemRVModal itemRVModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item_cat1);
        firebaseDatabase = FirebaseDatabase.getInstance();
        ItemNameEdit=findViewById(R.id.idEditItemName);
        ItemPriceEdit=findViewById(R.id.idEditItemPrice);
        ItemCategoryEdit=findViewById(R.id.idEditItemCategory);
        ItemImageEdit=findViewById(R.id.idEditItemImageLink);
        ItemLinkEdit=findViewById(R.id.idEditItemLink);
        ItemDescriptionEdit=findViewById(R.id.idEditItemDesc);
        updateItemBtn = findViewById(R.id.idBtnUpdateItem);
        deleteItemBtn = findViewById(R.id.idBtnDeleteItem);
        loadingPB=findViewById(R.id.idPBLoading);
        itemRVModal =getIntent().getParcelableExtra("Item1");
        if(itemRVModal!=null){
            ItemNameEdit.setText(itemRVModal.getItemName());
            ItemPriceEdit.setText(itemRVModal.getItemPrice());
            ItemCategoryEdit.setText(itemRVModal.getItemCategory());
            ItemImageEdit.setText(itemRVModal.getItemImage());
            ItemLinkEdit.setText(itemRVModal.getItemLink());
            ItemDescriptionEdit.setText(itemRVModal.getItemDescription());
            ItemID = itemRVModal.getItemID();

        }

        databaseReference = firebaseDatabase.getReference("ItemsCategory1").child(ItemID);
        updateItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);
                String itemName = ItemNameEdit.getText().toString();
                String itemPrice = ItemPriceEdit.getText().toString();
                String itemCategory= ItemCategoryEdit.getText().toString();
                String itemImage = ItemImageEdit.getText().toString();
                String itemLink = ItemLinkEdit.getText().toString();
                String itemDescription = ItemDescriptionEdit.getText().toString();

                Map<String,Object> map = new HashMap<>();
                map.put("itemName",itemName);
                map.put("itemDescription",itemDescription);
                map.put("itemPrice",itemPrice);
                map.put("itemCategory",itemCategory);
                map.put("itemImage",itemImage);
                map.put("itemLink",itemLink);
                map.put("itemID",ItemID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        startActivity(new Intent(EditItemCat1.this,MainActivity.class));
                        Toast.makeText(EditItemCat1.this, "Item Updated..", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {
                        Toast.makeText(EditItemCat1.this, "Fail to Update Item..", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        deleteItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteItem();
            }
        });
    }
    private void deleteItem(){
        databaseReference.removeValue();
        startActivity(new Intent(EditItemCat1.this,MainActivity.class));
        Toast.makeText(this, "Item Deleted..", Toast.LENGTH_SHORT).show();
    }

}