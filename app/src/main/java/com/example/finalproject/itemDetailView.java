package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class itemDetailView extends AppCompatActivity {
    ImageView imageView,contactImage;
    TextView itemName, price, user, phone,contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail_view);
        String itemUid = getIntent().getStringExtra("ITEM_UID");
        contact = (TextView) findViewById(R.id.contact);
        contactImage = (ImageView) findViewById(R.id.contactImage);
        imageView = (ImageView) findViewById(R.id.ivImageView);
        itemName = (TextView) findViewById(R.id.ivItemName);
        price = (TextView) findViewById(R.id.ivItemPrice);
        user = (TextView) findViewById(R.id.ivUsername);
        phone = (TextView) findViewById(R.id.ivPhone);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Items").child(Objects.requireNonNull(itemUid));
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Item item = snapshot.getValue(Item.class);

                    if(item!=null){

                        Glide.with(itemDetailView.this)
                                .load(item.getImage())
                                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark) // Placeholder image while loading
                                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal) // Error image if loading fails
                                .into(imageView);
                        Glide.with(itemDetailView.this)
                                .load(item.getPhotoURL())
                                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark) // Placeholder image while loading
                                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal) // Error image if loading fails
                                .into(contactImage);

                        itemName.setText(item.getName());
                        price.setText(String.valueOf(item.getPrice()));
                        if(item.getStatus().equals("Selling")){
                            contact.setText("Seller");
                        }else{
                            contact.setText("Buyer");
                        }
                        phone.setText(item.getPhoneNumber());
                        user.setText(item.getUid());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}