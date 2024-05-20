package com.example.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button btnLogout;
    TextView usrDetailsTv;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        auth=FirebaseAuth.getInstance();

        btnLogout=findViewById(R.id.logout);
        usrDetailsTv=findViewById(R.id.user_details);
        user=auth.getCurrentUser();//to get the current user
        if(user==null){//if the user is null it will finish the current activity and move to login activity
            Intent intent=new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        } else {
            usrDetailsTv.setText(user.getEmail());
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();//to sign out using firebase
                Intent intent=new Intent(getApplicationContext(), Login.class);//now the user will be signed out then we
                startActivity(intent);// move to login activity
                finish();
            }
        });
    }
}