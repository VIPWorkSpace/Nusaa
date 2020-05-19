package com.workspace.NusaLicious.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.workspace.NusaLicious.naskotMenu;
import com.workspace.societybeta.R;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Beranda extends Fragment {


    private int[] mImages = new int[] {
            R.drawable.spanduk1, R.drawable.spanduk2, R.drawable.spanduk3
    };
    GridLayout homeGrid;
    DatabaseReference mref;

    public Beranda() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         getUsernameLocal();

        View view =  inflater.inflate(R.layout.fragment_beranda, container, false);

        //set Carousel Home
        CarouselView carouselView = view.findViewById(R.id.carousel);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });
        //Set GridLayout
        homeGrid = (GridLayout) view.findViewById(R.id.homeGrid);
        setSingleEvent(homeGrid);
        //Return
        return view;
    }





    //Gridlayout Click Listener
    private void setSingleEvent(GridLayout homeGrid) {
        for (int i = 0; i < homeGrid.getChildCount(); i++){
            CardView mCardView = (CardView) homeGrid.getChildAt(i);
            final int finall = i;
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finall == 0){
                        Intent intent = new Intent(getContext(), naskotMenu.class);
                        startActivity(intent);
                    }
                    else if (finall == 1){
                        Intent intent = new Intent(getContext(), naskotMenu.class);
                        startActivity(intent);
                    }
                    else if (finall == 2){
                        Intent intent = new Intent(getContext(), naskotMenu.class);
                        startActivity(intent);
                    }
                    else if (finall == 3){
                        Intent intent = new Intent(getContext(), naskotMenu.class);
                        startActivity(intent);
                    }
                    else if (finall == 4){
                        Intent intent = new Intent(getContext(), naskotMenu.class);
                        startActivity(intent);
                    }
                    else if (finall == 5){
                        Intent intent = new Intent(getContext(), naskotMenu.class);
                        startActivity(intent);
                    }
                    else if (finall == 6){
                        Intent intent = new Intent(getContext(), naskotMenu.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
    public void getUsernameLocal() {
        String userIdKey = "";
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(userIdKey, MODE_PRIVATE);
        String userId = sharedPreferences.getString("firebaseKey", "");

    }

}
