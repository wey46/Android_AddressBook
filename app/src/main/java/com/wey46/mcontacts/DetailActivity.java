package com.wey46.mcontacts;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.wey46.mcontacts.model.ContactItem;

import java.io.IOException;
import java.io.InputStream;

public class DetailActivity extends AppCompatActivity {

    private TextView tvFName, tvLName, tvPhone, tvEmail, tvAddress1, tvAddress2, tvCity,
        tvState, tvZip, tvCountry;
    private ImageView itemImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ContactItem item = getIntent().getExtras().getParcelable(DataItemAdapter.ITEM_KEY);
        if (item == null) {
            throw new AssertionError("Null data item received!");
        }

        tvLName = findViewById(R.id.tvLname);
        tvLName.setText(item.getLName());

        tvFName = findViewById(R.id.tvFname);
        tvFName.setText(item.getFName());

        tvPhone = findViewById(R.id.tvPhone);
        String phone = "TEL: " + item.getPhone();
        tvPhone.setText(phone);

        tvEmail = findViewById(R.id.tvEmail);
        String email = "Email: " + item.getEmail();
        tvEmail.setText(email);

        tvAddress1 = findViewById(R.id.tvAddress1);
        String add1 = "Address1: " + item.getAddress1();
        tvAddress1.setText(add1);

        tvAddress2 = findViewById(R.id.tvAddress2);
        String add2 = "Address2: " + item.getAddress2();
        tvAddress2.setText(add2);

        tvCity = findViewById(R.id.tvCity);
        String city = "City: " + item.getCity();
        tvCity.setText(city);

        tvState = findViewById(R.id.tvState);
        String state = "State: " + item.getState();
        tvState.setText(state);

        tvZip = findViewById(R.id.tvZip);
        String zip = "ZIP: " + item.getZip();
        tvZip.setText(zip);

        tvCountry = findViewById(R.id.tvCountry);
        String country = "Country: " + item.getCountry();
        tvCountry.setText(country);

        itemImage = findViewById(R.id.itemImage);
        InputStream inputStream = null;
        try {
            String imageFile = "defaultimg.png";
            inputStream = getAssets().open(imageFile);
            Drawable d = Drawable.createFromStream(inputStream, null);
            itemImage.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
