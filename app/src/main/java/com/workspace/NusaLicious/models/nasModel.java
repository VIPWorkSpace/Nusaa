package com.workspace.NusaLicious.models;

import android.os.Parcel;
import android.os.Parcelable;

public class nasModel implements Parcelable {
    String judul;
    Integer harga;
    String desc;
    String gambar;

    //Constr
    public nasModel() {
    }
    public nasModel(String judul, Integer harga, String desc, String gambar) {
        this.judul = judul;
        this.harga = harga;
        this.desc = desc;
        this.gambar = gambar;
    }

    //Getter n Setter
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    //Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.judul);
        dest.writeInt(this.harga);
        dest.writeString(this.desc);
        dest.writeString(this.gambar);
    }

    protected nasModel(Parcel in) {
        this.judul = in.readString();
        this.harga = in.readInt();
        this.desc = in.readString();
        this.gambar = in.readString();
    }

    public static final Parcelable.Creator<nasModel> CREATOR = new Parcelable.Creator<nasModel>() {
        @Override
        public nasModel createFromParcel(Parcel source) {
            return new nasModel(source);
        }

        @Override
        public nasModel[] newArray(int size) {
            return new nasModel[size];
        }
    };
}
