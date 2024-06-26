package com.example.firebase.Services;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.Api.CreateOrder;
import com.example.firebase.R;

import org.json.JSONObject;

import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class OrderPaymentActivity extends AppCompatActivity {
    TextView tvQuantity, tvTotal;
    Button btnPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_payment);

        tvQuantity = (TextView) findViewById(R.id.tvQuantityNumber);
        tvTotal = (TextView) findViewById(R.id.tvTotalNumber);
        btnPay = (Button) findViewById(R.id.btnPay);

        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ZaloPay SDK Init
        ZaloPaySDK.init(2553, Environment.SANDBOX);

        Intent intent = getIntent();

        tvQuantity.setText(intent.getStringExtra("soluong"));
        Double total = intent.getDoubleExtra("total",(double) 0);
        String totalString = String.format("%.0f", total);
        tvTotal.setText(Double.toString(total));

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateOrder orderApi = new CreateOrder();
                try {
                    JSONObject data = orderApi.createOrder(totalString);

                    String code = data.getString("return_code");


                    if (code.equals("1")) {
                        String token = data.getString("zp_trans_token");
                        ZaloPaySDK.getInstance().payOrder(OrderPaymentActivity.this, token, "demozpdk://app", new PayOrderListener() {
                            @Override
                            public void onPaymentSucceeded(String s, String s1, String s2) {
                                Intent intent = new Intent(OrderPaymentActivity.this, PaymentNotification.class);
                                intent.putExtra("result", "Thanh toán thành công");
                                startActivity(intent);
                            }

                            @Override
                            public void onPaymentCanceled(String s, String s1) {
                                Intent intent = new Intent(OrderPaymentActivity.this, PaymentNotification.class);
                                intent.putExtra("result", "Đã hủy thanh toán");
                                startActivity(intent);
                            }

                            @Override
                            public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {
                                Intent intent = new Intent(OrderPaymentActivity.this, PaymentNotification.class);
                                intent.putExtra("result", "Thanh toán không thành công");
                                startActivity(intent);
                            }
                        });

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }
}