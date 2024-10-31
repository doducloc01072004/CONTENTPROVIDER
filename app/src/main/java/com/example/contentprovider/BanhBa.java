package com.example.contentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.contentprovider.model.Contact;

import java.util.ArrayList;

public class BanhBa extends AppCompatActivity {

    private static final int REQUEST_CODE_ASK_PERMISSIONS = 1001;

    ListView lvdb;
    ArrayList<Contact> dsdanhba;
    ArrayAdapter<Contact> adapterDanhBa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_banh_ba);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addControl();
        showAllContactFromDevice();
    }

    private void showAllContactFromDevice() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        dsdanhba.clear();
        while (cursor.moveToNext()){
            String tenCotName = ContactsContract.Contacts.DISPLAY_NAME;
            String tenCotPhone = ContactsContract.CommonDataKinds.Phone.NUMBER;
            int viTriCotName = cursor.getColumnIndex(tenCotName);
            int viTriCotPhone = cursor.getColumnIndex(tenCotPhone);
            String name = cursor.getString(viTriCotName);
            String phone = cursor.getString(viTriCotPhone);
            Contact contact = new Contact(name,phone);
            dsdanhba.add(contact);
            adapterDanhBa.notifyDataSetChanged();
        }
    }

    private void addControl() {
        lvdb = findViewById(R.id.lvdb);
        dsdanhba = new ArrayList<>();
        adapterDanhBa = new ArrayAdapter<>(
                BanhBa.this, android.R.layout.simple_list_item_1,dsdanhba
        );
        lvdb.setAdapter(adapterDanhBa);
    }
}