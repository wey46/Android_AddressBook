package com.wey46.mcontacts;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wey46.mcontacts.database.DataSource;
import com.wey46.mcontacts.model.ContactItem;

import java.io.IOException;
import java.io.InputStream;

public class EditActivity extends AppCompatActivity {
    private TextView tvFName, tvLName;
    private ImageView itemImage;
    private EditText ed2Phone, ed2Email, ed2Address1, ed2City,
            ed2State, ed2Zip, ed2Country;
    private Button buttonEdit, buttonDelete;
    DataSource mDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ContactItem item = getIntent().getExtras().getParcelable(DataItemAdapter.ITEM_KEY);
        if (item == null) {
            throw new AssertionError("Null data item received!");
        }

        tvLName = findViewById(R.id.tv2Lname);
        tvLName.setText(item.getLName());

        tvFName = findViewById(R.id.tv2Fname);
        tvFName.setText(item.getFName());

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

        ed2Phone = findViewById(R.id.ed2Phone);
        String phone = item.getPhone();
        ed2Phone.setText(phone);

        ed2Email = findViewById(R.id.ed2Email);
        String email = item.getEmail();
        ed2Email.setText(email);

        ed2Address1 = findViewById(R.id.ed2Address1);
        String add1 = item.getAddress1();
        ed2Address1.setText(add1);

        ed2City = findViewById(R.id.ed2City);
        String city = item.getCity();
        ed2City.setText(city);

        ed2State = findViewById(R.id.ed2State);
        String state = item.getState();
        ed2State.setText(state);

        ed2Zip = findViewById(R.id.ed2Zip);
        String zip = item.getZip() + "";
        ed2Zip.setText(zip);

        ed2Country = findViewById(R.id.ed2Country);
        String country = item.getCountry();
        ed2Country.setText(country);

        buttonEdit = findViewById(R.id.buttonEdit);
        buttonEdit.setOnClickListener(view -> {
            String lname = item.getLName();
            String fname = item.getFName();
            String nphone = ed2Phone.getText().toString();
            String nemail = ed2Email.getText().toString();
            String naddress1 = ed2Address1.getText().toString();
            String ncity = ed2City.getText().toString();
            String ncountry = ed2Country.getText().toString();
            String nstate = ed2State.getText().toString();
            int nzip = item.getZip();
            String zipString = ed2Zip.getText().toString();
            if(isZipValid(zipString)){
                nzip = Integer.parseInt(zipString);
            }

            ContactItem newItem = new ContactItem(lname, fname, naddress1, item.getAddress2(), ncity, nstate, nzip, ncountry, nphone, nemail);
            newItem.setUId(item.getUId());

            mDataSource = new DataSource(getApplicationContext());
            mDataSource.open();
            mDataSource.updateItem(newItem);
            Toast.makeText(getApplicationContext(), "Contact Updated!",Toast.LENGTH_SHORT).show();
            mDataSource.close();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        buttonDelete = findViewById(R.id.buttonDelet);
        buttonDelete.setOnClickListener(view -> {
            mDataSource = new DataSource(getApplicationContext());
            mDataSource.open();
            mDataSource.deleteItem(item.getUId());
            Toast.makeText(getApplicationContext(), "Contact deleted!",Toast.LENGTH_SHORT).show();
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
