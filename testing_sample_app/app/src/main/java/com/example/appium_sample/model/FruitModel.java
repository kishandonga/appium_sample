package com.example.appium_sample.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FruitModel implements Parcelable {

    private String fruit;
    private int photoId;
    private String description;
    private float amount;

    public FruitModel(String fruit, float amount, int photoId, String description) {
        this.fruit = fruit;
        this.photoId = photoId;
        this.description = description;
        this.amount = amount;
    }

    private FruitModel(Parcel in) {
        fruit = in.readString();
        photoId = in.readInt();
        description = in.readString();
        amount = in.readFloat();
    }

    public static final Creator<FruitModel> CREATOR = new Creator<FruitModel>() {
        @Override
        public FruitModel createFromParcel(Parcel in) {
            return new FruitModel(in);
        }

        @Override
        public FruitModel[] newArray(int size) {
            return new FruitModel[size];
        }
    };

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(fruit);
        parcel.writeInt(photoId);
        parcel.writeString(description);
        parcel.writeFloat(amount);
    }
}
