package com.wey46.mcontacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wey46.mcontacts.database.DataSource;
import com.wey46.mcontacts.model.ContactItem;

public class AddNewActivity extends AppCompatActivity {

    private EditText etLname, etFname, etPhone, etEmail, etAddress1, etCity, etState, etCountry, etZip;
    DataSource mDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        Button button = findViewById(R.id.buttonAdd);
        button.setOnClickListener(view -> {
            etLname=findViewById(R.id.etLname);
            etFname=findViewById(R.id.etFname);
            etPhone=findViewById(R.id.etPhone);
            etEmail=findViewById(R.id.etEmail);
            etAddress1=findViewById(R.id.etAddress1);
            etCity=findViewById(R.id.etCity);
            etState=findViewById(R.id.etState);
            etCountry = findViewById(R.id.etCountry);
            etZip=findViewById(R.id.etZip);
            String lname = etLname.getText().toString();
            String fname = etFname.getText().toString();
            String phone = etPhone.getText().toString();
            String email = etEmail.getText().toString();
            String address1 = etAddress1.getText().toString();
            String city = etCity.getText().toString();
            String country = etCountry.getText().toString();
            String state = etState.getText().toString();
            int zip = 00000;
            String zipString = etZip.getText().toString();
            if(isZipValid(zipString)){
                zip = Integer.parseInt(zipString);
            }

            mDataSource = new DataSource(getApplicationContext());
            mDataSource.open();
            if(mDataSource.noDupName(fname,lname)){
                ContactItem newItem = new ContactItem(lname, fname, address1, "", city, state, zip, country, phone, email);
                mDataSource.createItem(newItem);
                Toast.makeText(getApplicationContext(), "New Contact Added!",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Error: Contact Already Exist", Toast.LENGTH_SHORT).show();
            }
            mDataSource.close();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        });
    }

    private boolean isZipValid(String zip){
        if(zip.length()==0) return false;
        for(char c: zip.toCharArray()){
            if(!Character.isDigit(c)) return false;
        }
        return true;
    }
}
