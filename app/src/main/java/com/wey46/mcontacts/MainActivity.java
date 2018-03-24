package com.wey46.mcontacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.wey46.mcontacts.database.DataSource;
import com.wey46.mcontacts.model.ContactItem;
import com.wey46.mcontacts.sample.SampleDataProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ContactItem> dataItemList = SampleDataProvider.contactItemList;

    DataSource mDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataSource = new DataSource(this);
        mDataSource.open();
        mDataSource.seedDatabase(dataItemList);

        List<ContactItem> listFromDB = mDataSource.getAllItems();

        DataItemAdapter adapter = new DataItemAdapter(this, listFromDB);
        RecyclerView recyclerView = findViewById(R.id.rvItems);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDataSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataSource.open();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_new:
                Intent intent = new Intent(this, AddNewActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
