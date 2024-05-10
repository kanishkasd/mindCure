package com.example.mindcure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupScreen extends AppCompatActivity {

    Button callSignIn, regButton;
    TextInputLayout regName, regUsername, regEmail, regPassword, confirmPassword;


    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_screen);

        callSignIn = findViewById(R.id.login_screen);

        rootNode = FirebaseDatabase.getInstance();
        // Get a reference to the "users" node in the database
        reference = rootNode.getReference("users");


        callSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupScreen.this, LoginScreen.class);
                startActivity(intent);
            }
        });

        regButton = findViewById(R.id.register_button);
        regName = findViewById(R.id.register_fullName);
        regUsername = findViewById(R.id.register_username);
        regEmail = findViewById(R.id.register_email);
        regPassword = findViewById(R.id.register_password);
        confirmPassword = findViewById(R.id.confirmPassword);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                    registerUser(v);
            }
        });
    }


    private Boolean validateName() {
        String val = regName.getEditText().getText().toString();

        if (val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUserName() {
        String val = regUsername.getEditText().getText().toString();
        ;
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            regUsername.setError("Username is too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regUsername.setError("White space is not allowed!");
            return false;
        } else {
            regUsername.setError(null);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        } else {
            regPassword.setError(null);
            return true;
        }
    }

    private Boolean validateConfirmPassword() {
        String val = confirmPassword.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();

        if (val.isEmpty()) {
            confirmPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.equals(password)) {
            confirmPassword.setError("password do not match");
            return false;
        } else {
            confirmPassword.setError(null);
            return true;
        }
    }

    public void registerUser(View view) {

        if(!validateName() | validateUserName() | validateEmail() | validatePassword() | validateConfirmPassword()){
            return;
        }

        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();
        String confirmPass = confirmPassword.getEditText().getText().toString();
        UserHelperClass helperClass = new UserHelperClass(name, username, email, password, confirmPass);
        reference.child(username).setValue(helperClass);
    }

}
