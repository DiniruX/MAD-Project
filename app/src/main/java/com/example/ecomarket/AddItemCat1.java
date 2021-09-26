package com.example.ecomarket;
//malki
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

//created by malshika peiris
//page starts here
public class AddItemCat1 extends AppCompatActivity {

    private TextInputEditText ItemNameEdit,ItemPriceEdit,ItemCategoryEdit,ItemImageEdit,ItemLinkEdit,ItemDescriptionEdit;
    private Button addItemBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String ItemID;
//this is the All Items Category1 page

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_cat1);
        ItemNameEdit=findViewById(R.id.idEditItemName);
        ItemPriceEdit=findViewById(R.id.idEditItemPrice);
        ItemCategoryEdit=findViewById(R.id.idEditItemCategory);
        ItemImageEdit=findViewById(R.id.idEditItemImageLink);
        ItemLinkEdit=findViewById(R.id.idEditItemLink);
        ItemDescriptionEdit=findViewById(R.id.idEditItemDesc);
        addItemBtn = findViewById(R.id.idBtnAddItem);
        loadingPB=findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("ItemsCategory1");

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String itemName = ItemNameEdit.getText().toString();
                String itemPrice = ItemPriceEdit.getText().toString();
                String itemCategory= ItemCategoryEdit.getText().toString();
                String itemImage = ItemImageEdit.getText().toString();
                String itemLink = ItemLinkEdit.getText().toString();
                String itemDescription = ItemDescriptionEdit.getText().toString();
                ItemID = itemName;
                ItemRVModal itemRVModal = new ItemRVModal(itemName,itemDescription,itemPrice,itemCategory,itemImage,itemLink,ItemID);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.child(ItemID).setValue(itemRVModal);
                        Toast.makeText(AddItemCat1.this, "Item Added...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddItemCat1.this,AllItemsCat1.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddItemCat1.this, "Error is"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });





    }
}
