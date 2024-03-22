package com.example.googlefirebasedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SecondActivity extends AppCompatActivity {

    private TextView userNameTextView;
    private Button logoutButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initialize views
        TextView welcomeTextView = findViewById(R.id.welcomeText);
        userNameTextView = findViewById(R.id.userNameText);
        logoutButton = findViewById(R.id.logoutButton);

        // Get current user from Firebase Authentication
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        // Check if the user is logged in
        if (currentUser != null) {
            // Get the user's display name
            String userName = currentUser.getDisplayName();

            // Display the welcome message and user's name
            welcomeTextView.setText(getString(R.string.welcome));
            userNameTextView.setText(userName);
        } else {
            // If the user is not logged in, show a default message
            userNameTextView.setText(getString(R.string.default_user_name));
        }

        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Log out the user and go back to MainActivity
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Close this activity
            }
        });
    }
}
