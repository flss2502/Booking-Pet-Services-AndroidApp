package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ZaloPayActivity extends AppCompatActivity {
    Button btnConfirm;
    EditText edQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_zalo_pay);

        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        edQuantity = (EditText) findViewById(R.id.edQuantity);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edQuantity.getText() == null || edQuantity.getText().toString().isEmpty()){
                    Toast.makeText(ZaloPayActivity.this,"Nhập số lượng muốn mua", Toast.LENGTH_SHORT).show();
                    return;
                }

                String quantity = edQuantity.getText().toString();
                double total = Double.parseDouble(quantity) * (double) 1000000;

                Intent intent = new Intent(ZaloPayActivity.this, OrderPaymentActivity.class);
                intent.putExtra("soluong", edQuantity.getText().toString());
                intent.putExtra("total", total);
                startActivity(intent);
            }
        });
    }
}