package com.flaxen.interntask;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SignUp extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ImageView b;
    private EditText name, phone,password,confPassword;
    ImageView profile_image;
    EditText emailid;
    private Button submit,profbtn;

    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    userinfo userinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);

        name= findViewById(R.id.name);
        phone= findViewById(R.id.phone);
        emailid= findViewById(R.id.email);
        password= findViewById(R.id.password);
        confPassword= findViewById(R.id.confPassword);

        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("ProfileInfo");

        userinfo = new userinfo();

        submit = findViewById(R.id.submitBtn);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.
                String Name = name.getText().toString();
                String Phone = phone.getText().toString();
                String Email = emailid.getText().toString();
                String Password = password.getText().toString();
                String ConfPassword = confPassword.getText().toString();

                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(Phone) || TextUtils.isEmpty(Password) || TextUtils.isEmpty(ConfPassword)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(SignUp.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                }
                else if(!Password.equals(ConfPassword) || Password==null)
                {
                    Toast.makeText(SignUp.this, "Password doesn't match.", Toast.LENGTH_SHORT).show();
                }
                else {
                    // else call the method to add
                    // data to our database.
                    Intent intent=new Intent(SignUp.this, Login.class);
                    startActivity(intent);
                    addDatatoFirebase(Name, Phone,Email, ConfPassword);
                }
            }
        });
    }

    private void addDatatoFirebase(String name, String phone, String email,String password) {
        // below 3 lines of code is used to set
        // data in our object class.
        userinfo.setname(name);
        userinfo.setContactno(phone);
        userinfo.setEmailid(email);
        userinfo.setPassword(password);

        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(userinfo);

                // after adding this data we are showing toast message.
                Toast.makeText(SignUp.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(SignUp.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}