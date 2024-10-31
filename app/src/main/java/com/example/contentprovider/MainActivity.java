package com.example.contentprovider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.contentprovider.R;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CONTACTS_ASK_PERMISSIONS = 1001;

    Button btn1, btn2;

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
        addControl();
        addEvents();
    }

    private void addEvents() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulymomanhinhdanhba();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulymomanhinhdinnhan();
            }
        });
    }

    private void xulymomanhinhdinnhan() {
    }

    private void xulymomanhinhdanhba() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CONTACTS_ASK_PERMISSIONS);
        } else {
            Intent intent = new Intent(MainActivity.this, BanhBa.class);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CONTACTS_ASK_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MainActivity.this, BanhBa.class);
                startActivity(intent);
            } else {
                Toast toast;
                Toast.makeText(this, "Bạn cần cấp quyền truy cập danh bạ để sử dụng tính năng này.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addControl() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
    }
}