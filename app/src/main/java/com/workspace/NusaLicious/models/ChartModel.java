package com.workspace.NusaLicious.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ChartModel implements Parcelable {
    private String judul;
    private Integer harga;
    private Integer jumlahBeli;


    public ChartModel() {
    }

    public ChartModel(String judul, Integer harga, Integer jumlahBeli) {
        this.judul = judul;
        this.harga = harga;
        this.jumlahBeli = jumlahBeli;
    }



    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public Integer getJumlahBeli() {
        return jumlahBeli;
    }

    public void setJumlahBeli(Integer jumlahBeli) {
        this.jumlahBeli = jumlahBeli;
    }

    public static Creator<ChartModel> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.judul);
        dest.writeInt(this.harga);
        dest.writeInt(this.jumlahBeli);

    }

    protected ChartModel(Parcel in) {
        this.judul = in.readString();
        this.harga = in.readInt();
        this.jumlahBeli = in.readInt();
            }

    public static final Creator<ChartModel> CREATOR = new Creator<ChartModel>() {
        @Override
        public ChartModel createFromParcel(Parcel source) {
            return new ChartModel(source);
        }

        @Override
        public ChartModel[] newArray(int size) {
            return new ChartModel[size];
        }
    };


}
