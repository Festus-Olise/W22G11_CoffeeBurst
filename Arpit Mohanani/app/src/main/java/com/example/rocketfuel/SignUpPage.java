package com.example.rocketfuel;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class SignUpPage extends AppCompatActivity {

    EditText fullName;
    EditText userName;
    EditText newPassword;
    EditText confirmPassword;
    MaterialButton createAccount;
    MaterialButton showData;
    LoginDatabase myDb;
    boolean signUpSuccess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        myDb = new LoginDatabase(this);
        fullName = findViewById(R.id.fullName);
        userName = findViewById(R.id.userName);
        newPassword = findViewById(R.id.newPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        createAccount = findViewById(R.id.btnCreateAccount);
        showData = findViewById(R.id.btnShowDatabase);
        signUpSuccess = false;

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fullName.getText().toString().isEmpty()){
                    Toast.makeText(SignUpPage.this,"Please enter your full name", Toast.LENGTH_SHORT).show();
                }else if(userName.getText().toString().isEmpty()){
                    Toast.makeText(SignUpPage.this,"Please enter a username", Toast.LENGTH_SHORT).show();
                }else if(isSpecialCharacterPresent(userName.getText().toString())){
                    Toast.makeText(SignUpPage.this,"UserName must not contain any Special character", Toast.LENGTH_SHORT).show();
                }else if(userName.getText().toString().contains(" ")){
                    Toast.makeText(SignUpPage.this,"UserName must not contain any spaces", Toast.LENGTH_SHORT).show();
                }else if(newPassword.getText().toString().isEmpty()){
                    Toast.makeText(SignUpPage.this,"Please enter a password", Toast.LENGTH_SHORT).show();
                }else if(newPassword.getText().toString().length() < 8){
                    Toast.makeText(SignUpPage.this,"Password must contain atleast 8 characters", Toast.LENGTH_SHORT).show();
                }else if (!newPassword.getText().toString().equals(confirmPassword.getText().toString())){
                    Toast.makeText(SignUpPage.this,"Password does not match with confirmed password", Toast.LENGTH_SHORT).show();
                }else{
                    signUpSuccess = AddData();
                    if (!signUpSuccess){
                        Toast.makeText(SignUpPage.this,"Account creation failed due to an error. Please try again", Toast.LENGTH_SHORT).show();
                        }else{
                        Toast.makeText(SignUpPage.this,"Account created successfully", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(SignUpPage.this,SignInPage.class));
                    }
                }
            }
        });

        showData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if(res.getCount() == 0){
                    showMessage("error","nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Full Name: " + res.getString(0) + "\n");
                    buffer.append("Username: " + res.getString(1) + "\n");
                    buffer.append("Password: " + res.getString(2) + "\n\n");
                }

                showMessage("Data",buffer.toString());
            }
        });
    }

    public void showMessage(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }

    public boolean AddData(){
        return myDb.insertData(fullName.getText().toString(), userName.getText().toString(), newPassword.getText().toString());
    }

    public static boolean isSpecialCharacterPresent(String userName) {
        String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
        for (int i=0; i < userName.length() ; i++)
        {
            char ch = userName.charAt(i);
            if(specialCharactersString.contains(Character.toString(ch))) {
                return true;
            }
        }
        return false;
    }
}
