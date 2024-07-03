//package com.example.firebase;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.firebase.Model.Role;
//import com.example.firebase.Model.User;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class SignupActivity extends AppCompatActivity {
//    EditText signupName, signupPhoneNumber, signupEmail, signupPassword;
//    TextView loginRedirectText;
//    Button signupButton;
//    FirebaseDatabase database;
//    DatabaseReference reference;
//
//    FirebaseAuth firebaseAuth;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
//        signupName = findViewById(R.id.signup_name);
//        signupEmail = findViewById(R.id.signup_email);
//        signupPhoneNumber = findViewById(R.id.signup_phonenumber);
//        signupPassword = findViewById(R.id.signup_password);
//        loginRedirectText = findViewById(R.id.loginRedirectText);
//        signupButton = findViewById(R.id.signup_button);
//
//        firebaseAuth = FirebaseAuth.getInstance();
//        signupButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = signupName.getText().toString();
//                String email = signupEmail.getText().toString();
//                String phone = signupPhoneNumber.getText().toString();
//                String password = signupPassword.getText().toString();
//
//                if (!name.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !password.isEmpty()) {
//                    firebaseAuth.createUserWithEmailAndPassword(email, password)
//                            .addOnCompleteListener(task -> {
//                                if (task.isSuccessful()) {
//                                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//                                    if (firebaseUser != null) {
//                                        firebaseUser.sendEmailVerification()
//                                                .addOnCompleteListener(task1 -> {
//                                                    if (task1.isSuccessful()) {
//                                                        Toast.makeText(SignupActivity.this, "Verification email sent. Please check your email.", Toast.LENGTH_SHORT).show();
//                                                        User user = new User(0, name, email, password, phone, null, Role.customer);
//                                                        database = FirebaseDatabase.getInstance();
//                                                        reference = database.getReference("users");
//                                                        reference.child(firebaseUser.getUid()).setValue(user);
//                                                        firebaseAuth.signOut();
//                                                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
//                                                        startActivity(intent);
//                                                    } else {
//                                                        Toast.makeText(SignupActivity.this, "Failed to send verification email.", Toast.LENGTH_SHORT).show();
//                                                    }
//                                                });
//                                    }
//                                } else {
//                                    Toast.makeText(SignupActivity.this, "Failed to sign up. Please try again.", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                } else {
//                    Toast.makeText(SignupActivity.this, "Please fill all the fields.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        loginRedirectText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//}
