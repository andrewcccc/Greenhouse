package com.example.greenhouse.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.greenhouse.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    ImageView teacherDetailImageView;

//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View layoutView = inflater.inflate(R.layout.fragment_slideshow, container, false);
//        teacherDetailImageView= (ImageView) layoutView.findViewById(R.id.teacherDetailImageView);
//        return layoutView;
//    }
//
//    private void receiveAndShowData(){
//        //RECEIVE DATA FROM ITEMS ACTIVITY VIA INTENT
//        Intent i=this.getIntent();
//        String imageURL=i.getExtras().getString("IMAGE_KEY");
//        Picasso.get().load(imageURL).placeholder(R.drawable.placeholder).into(teacherDetailImageView);
//
//    }
//
//    protected void onCreate(LayoutInflater inflater, ViewGroup Container, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail);
//        receiveAndShowData();
//    }
}