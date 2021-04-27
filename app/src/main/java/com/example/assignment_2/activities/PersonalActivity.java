package com.example.assignment_2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.assignment_2.R;
import com.example.assignment_2.models.Personal;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class PersonalActivity extends AppCompatActivity {


    // Views
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText mobileNumber;
    private EditText address;
    private EditText aboutMe;
    private DatePicker birthDate;
    private RadioButton maleButton;
    private RadioButton femaleButton;


    // Prefs & GSON
    private SharedPreferences prefs;
    private SharedPreferences.Editor prefsEditor;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);


        // Get Views Data
        firstName = findViewById(R.id.firstNameEditText);
        lastName = findViewById(R.id.lastNameEditText);
        email = findViewById(R.id.emailEditText);
        mobileNumber = findViewById(R.id.mobileNumberEditText);
        address = findViewById(R.id.addressEditText);
        aboutMe = findViewById(R.id.aboutMeEditText);
        birthDate = findViewById(R.id.birthDatePicker);
        maleButton = findViewById(R.id.maleRadioButton);
        femaleButton = findViewById(R.id.femaleRadioButton);



        // Set Prefs & GSON
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefsEditor =  prefs.edit();
        gson = new Gson();

        // Load Data if exists
        String personalStr = prefs.getString("personalInfo", "");
        Personal personalObj = gson.fromJson(personalStr, Personal.class);

        if(personalObj != null){
            firstName.setText(personalObj.getFirstName());
            lastName.setText(personalObj.getLastName());
            email.setText(personalObj.getEmail());
            mobileNumber.setText(personalObj.getMobile());
            address.setText(personalObj.getAddress());
            aboutMe.setText(personalObj.getAboutMe());

            // Set birth date
            Calendar calender = Calendar.getInstance();
            calender.setTime(personalObj.getBirthDate());
            int day = calender.get(Calendar.DAY_OF_MONTH);
            int month = calender.get(Calendar.MONTH);
            int year = calender.get(Calendar.YEAR);
            birthDate.init(year,month,day,null);

            if(personalObj.getGender() != null){
                if(personalObj.getGender().equals("Male")) maleButton.setChecked(true);
                if(personalObj.getGender().equals("Female")) femaleButton.setChecked(true);
            }

        }

    }




    public void backOnClick(View view) {
        // Save data
        saveOnClick(view);

        // Go to previous activity
//        Intent intent = new Intent(this, SkillsActivity.class);
//        startActivity(intent);
    }

    public void nextOnClick(View view) {
        // Save data
        saveOnClick(view);

        // Go to next activity
        Intent intent = new Intent(this, SkillsActivity.class);
        startActivity(intent);
    }

    public void saveOnClick(View view) {
        String firstNameStr = firstName.getText().toString();
        String lastNameStr = lastName.getText().toString();
        String emailStr = email.getText().toString();
        String mobileNumberStr = mobileNumber.getText().toString();
        String aboutMeStr = aboutMe.getText().toString();
        String genderStr = null;



        if(maleButton.isChecked()) genderStr = "Male";
        if(femaleButton.isChecked()) genderStr = "Female";

        Personal personalObj = new Personal();
        personalObj.setFirstName(firstNameStr);
        personalObj.setLastName(lastNameStr);
        personalObj.setEmail(emailStr);
        personalObj.setMobile(mobileNumberStr);
        personalObj.setAboutMe(aboutMeStr);
        personalObj.setGender(genderStr);

        // Set Birth Date
        int day = birthDate.getDayOfMonth();
        int month = birthDate.getMonth();
        int year = birthDate.getYear();
        Calendar mCalender = Calendar.getInstance();
        mCalender.set(Calendar.YEAR, year);
        mCalender.set(Calendar.MONTH, month);
        mCalender.set(Calendar.DAY_OF_MONTH, day);

        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalender.getTime());
        personalObj.setBirthDate(new Date(selectedDate));

        String personalInfo = gson.toJson(personalObj);
        prefsEditor.putString("personalInfo",personalInfo);
        prefsEditor.commit();

    }

}