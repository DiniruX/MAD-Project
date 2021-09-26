package com.example.ecomarket;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemRVModal implements Parcelable {
    private  String itemName;
    private String itemDescription;
    private String itemPrice;
    private  String itemCategory;
    private String itemImage;
    private String itemLink;
    private String itemID;

    public ItemRVModal(){

    }

    protected ItemRVModal(Parcel in) {
        itemName = in.readString();
        itemDescription = in.readString();
        itemPrice = in.readString();
        itemCategory = in.readString();
        itemImage = in.readString();
        itemLink = in.readString();
        itemID = in.readString();
    }

    public static final Creator<ItemRVModal> CREATOR = new Creator<ItemRVModal>() {
        @Override
        public ItemRVModal createFromParcel(Parcel in) {
            return new ItemRVModal(in);
        }

        @Override
        public ItemRVModal[] newArray(int size) {
            return new ItemRVModal[size];
        }
    };

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemLink() {
        return itemLink;
    }

    public void setItemLink(String itemLink) {
        this.itemLink = itemLink;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public ItemRVModal(String itemName, String itemDescription, String itemPrice, String itemCategory, String itemImage, String itemLink, String itemID) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
        this.itemImage = itemImage;
        this.itemLink = itemLink;
        this.itemID = itemID;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(itemName);
        parcel.writeString(itemDescription);
        parcel.writeString(itemPrice);
        parcel.writeString(itemCategory);
        parcel.writeString(itemImage);
        parcel.writeString(itemLink);
        parcel.writeString(itemID);
    }
}
