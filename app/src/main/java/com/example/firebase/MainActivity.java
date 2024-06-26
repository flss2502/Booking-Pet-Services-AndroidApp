package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.Activity.Pet.PetActivity;
import com.example.firebase.Activity.ServicesPet.ServicesPetActivity;
import com.example.firebase.Activity.User.UserActivity;
import com.example.firebase.Model.Booking;
import com.example.firebase.Model.Feedback;
import com.example.firebase.Model.Room;
import com.example.firebase.Model.Pet;
import com.example.firebase.Model.User;
import com.example.firebase.Model.Services;
import com.example.firebase.Model.Role;
import com.example.firebase.Model.Tracking;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private DatabaseReference petRef;
    private DatabaseReference serviceRef;
    private DatabaseReference bookingRef;
    private DatabaseReference roomRef;
    private DatabaseReference feedbackRef;
    private DatabaseReference trackingRef; // Add DatabaseReference for Tracking


    //Api
    private Button btnUserApi;
    private Button btnServiceApi;
    private Button btnPetApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Database
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("users");
        petRef = database.getReference("pets");
        serviceRef = database.getReference("services");
        bookingRef = database.getReference("bookings");
        roomRef = database.getReference("rooms");
        feedbackRef = database.getReference("feedback");
        trackingRef = database.getReference("tracking"); // Reference for "tracking" node

        // Add sample data
        addSampleUsers();
//        addSamplePets();
//        addSampleServices();
        addSampleBookings();
        addSampleRooms();
        addSampleFeedbacks();
        addSampleTracking(); // Call method to add sample Tracking data



        //Api
        btnUserApi = findViewById(R.id.buttonUser);
        btnServiceApi = findViewById(R.id.buttonServicesPet);
        btnPetApi = findViewById(R.id.bnt_PetActivity);

        btnUserApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });


        btnServiceApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServicesPetActivity.class);
                startActivity(intent);
            }
        });


        btnPetApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PetActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addSampleUsers() {
        ArrayList<User> userArrayList = new ArrayList<>();
        User admin = new User(1, "Anh Quan", "anhquanpro332002@gmail.com", "password123", "123456789", "123 Street", Role.admin);
        User customer = new User(2, "Hong Chanh", "hongchanh@gmail.com", "password123", "123456789", "123 Street", Role.customer);
        User staff = new User(3,"Xuan Binh","staff@gmail.com","123456","012345678","Ho CHi Minh",Role.staff);
        userArrayList.add(admin);
        userArrayList.add(customer);
        userArrayList.add(staff);

        // Push users to Firebase under "users" node
        for (User user : userArrayList) {
            userRef.child(String.valueOf(user.getUserId())).setValue(user)
                    .addOnSuccessListener(aVoid -> Log.d("TAG", "User added successfully: " + user.getFullName()))
                    .addOnFailureListener(e -> Log.w("TAG", "Error adding user: " + user.getFullName(), e));
        }
    }

    private void addSamplePets() {
        ArrayList<Pet> petArrayList = new ArrayList<>();
        Pet pet1 = new Pet(1, 2, "Coco", "Dog");
        Pet pet2 = new Pet(2, 2, "Whiskers", "Cat");
        petArrayList.add(pet1);
        petArrayList.add(pet2);

        // Push pets to Firebase under "pets" node
        for (Pet pet : petArrayList) {
            petRef.child(String.valueOf(pet.getPetId())).setValue(pet)
                    .addOnSuccessListener(aVoid -> Log.d("TAG", "Pet added successfully: " + pet.getPetName()))
                    .addOnFailureListener(e -> Log.w("TAG", "Error adding pet: " + pet.getPetName(), e));
        }
    }

    private void addSampleServices() {
        ArrayList<Services> servicesArrayList = new ArrayList<>();
        Services service1 = new Services(1, "Haircut", "Professional haircut service", 25.5);
        Services service2 = new Services(2, "Manicure", "Nail care service", 15.0);
        servicesArrayList.add(service1);
        servicesArrayList.add(service2);

        // Push services to Firebase under "services" node
        for (Services service : servicesArrayList) {
            serviceRef.child(String.valueOf(service.getServiceId())).setValue(service)
                    .addOnSuccessListener(aVoid -> Log.d("TAG", "Service added successfully: " + service.getServiceName()))
                    .addOnFailureListener(e -> Log.w("TAG", "Error adding service: " + service.getServiceName(), e));
        }
    }

    private void addSampleBookings() {
        ArrayList<Booking> bookingArrayList = new ArrayList<>();
        Booking booking1 = new Booking(1, 2, 1, 1, new Date(), new Date(), "confirmed");
        Booking booking2 = new Booking(2, 2, 2, 2, new Date(), new Date(), "completed");
        bookingArrayList.add(booking1);
        bookingArrayList.add(booking2);

        // Push bookings to Firebase under "bookings" node
        for (Booking booking : bookingArrayList) {
            bookingRef.child(String.valueOf(booking.getId())).setValue(booking)
                    .addOnSuccessListener(aVoid -> Log.d("TAG", "Booking added successfully: " + booking.getId()))
                    .addOnFailureListener(e -> Log.w("TAG", "Error adding booking: " + booking.getId(), e));
        }
    }

    private void addSampleRooms() {
        ArrayList<Room> roomArrayList = new ArrayList<>();
        Room room1 = new Room(1, 1, new Date(), new Date(), "Room 101");
        Room room2 = new Room(2, 2, new Date(), new Date(), "Room 102");
        roomArrayList.add(room1);
        roomArrayList.add(room2);

        // Push rooms to Firebase under "rooms" node
        for (Room room : roomArrayList) {
            roomRef.child(String.valueOf(room.getId())).setValue(room)
                    .addOnSuccessListener(aVoid -> Log.d("TAG", "Room added successfully: " + room.getRoomName()))
                    .addOnFailureListener(e -> Log.w("TAG", "Error adding room: " + room.getRoomName(), e));
        }
    }

    private void addSampleFeedbacks() {
        ArrayList<Feedback> feedbackArrayList = new ArrayList<>();
        Feedback feedback1 = new Feedback(1, "Great service!", "http://example.com", new Date());
        Feedback feedback2 = new Feedback(2, "Very satisfied!", "http://example.com", new Date());
        feedbackArrayList.add(feedback1);
        feedbackArrayList.add(feedback2);

        // Push feedbacks to Firebase under "feedback" node
        for (Feedback feedback : feedbackArrayList) {
            feedbackRef.push().setValue(feedback)
                    .addOnSuccessListener(aVoid -> Log.d("TAG", "Feedback added successfully: " + feedback.getUserId()))
                    .addOnFailureListener(e -> Log.w("TAG", "Error adding feedback", e));
        }
    }

    private void addSampleTracking() {
        ArrayList<Tracking> trackingArrayList = new ArrayList<>();
        Tracking tracking1 = new Tracking(1, "Walking", true, 1);
        Tracking tracking2 = new Tracking(2, "Feeding", false, 2);
        trackingArrayList.add(tracking1);
        trackingArrayList.add(tracking2);

        // Push tracking data to Firebase under "tracking" node
        for (Tracking tracking : trackingArrayList) {
            trackingRef.child(String.valueOf(tracking.getTrackingId())).setValue(tracking)
                    .addOnSuccessListener(aVoid -> Log.d("TAG", "Tracking added successfully: " + tracking.getTrackingId()))
                    .addOnFailureListener(e -> Log.w("TAG", "Error adding tracking: " + tracking.getTrackingId(), e));
        }
    }
}