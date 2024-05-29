package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class post_item extends AppCompatActivity {
    Button create;
    EditText itemName, price, imageURL;
    RadioButton selectedStatus;
    RadioGroup createStatus;
    DatabaseReference myDB;
    FirebaseAuth myAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_item);

        create = findViewById(R.id.btnCreate);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createItem();
            }
        });
    }

    protected void createItem(){
        myDB = FirebaseDatabase.getInstance().getReference();
        itemName = findViewById(R.id.createItemName);
        price = findViewById(R.id.createPrice);
        createStatus = findViewById(R.id.createStatus);
        imageURL = findViewById(R.id.imageURL);

        int status = createStatus.getCheckedRadioButtonId();
        selectedStatus = findViewById(status);

        FirebaseAuth myAuth = FirebaseAuth.getInstance();
        FirebaseUser fbUser = myAuth.getCurrentUser();
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("userInfo");

        Query query = usersRef.orderByChild("uid").equalTo(fbUser.getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Handle the found user
                    User user = snapshot.getValue(User.class);
                    if(user != null){

                        String infoID = myDB.push().getKey();
                        Item item = new Item(itemName.getText().toString(),selectedStatus.getText().toString(),imageURL.getText().toString(),fbUser.getEmail(),user.photoURL,user.phoneNumber,Integer.parseInt(price.getText().toString()));
                        if(infoID!=null){
                            myDB.child("Items").child(infoID).setValue(item).addOnCompleteListener(post_item.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(post_item.this, "Successfully posted an item", Toast.LENGTH_SHORT).show();
                                    Intent redirectDashboard = new Intent(post_item.this,Dashboard.class);
                                    startActivity(redirectDashboard);
                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });


    }
}