package com.example.assignment_2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.assignment_2.R;
import com.example.assignment_2.models.Personal;
import com.example.assignment_2.models.Skills;
import com.google.gson.Gson;

import java.util.Calendar;

public class CvActivity extends AppCompatActivity {

    private TextView name;
    private TextView email;
    private TextView mobile;
    private TextView address;
    private TextView about;
    private TextView birthDate;
    private TextView gender;
    private TextView languages;
    private TextView skills;


    // Prefs & GSON
    private SharedPreferences prefs;
    private SharedPreferences.Editor prefsEditor;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv);

        name = findViewById(R.id.nameTextView);
        email = findViewById(R.id.emailTextView);
        mobile = findViewById(R.id.mobileTextView);
        address = findViewById(R.id.addressTextView);
        about = findViewById(R.id.aboutTextView);
        birthDate = findViewById(R.id.birthDateTextView);
        gender = findViewById(R.id.genderTextView);
        languages = findViewById(R.id.languagesTextView);
        skills = findViewById(R.id.skillsTextView);


        // Set Prefs & GSON
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefsEditor =  prefs.edit();
        gson = new Gson();

        // Load Data if exists
        String personalStr = prefs.getString("personalInfo", "");
        Personal personalObj = gson.fromJson(personalStr, Personal.class);
        String skillsSt = prefs.getString("skills", "");
        Skills skillsObj = gson.fromJson(skillsSt, Skills.class);


        if(personalObj != null){
            name.setText(personalObj.getFirstName() + " " + personalObj.getLastName());
            email.setText("Email : " + personalObj.getEmail());
            mobile.setText("Mobile : " + personalObj.getMobile());
            address.setText("Address : " + personalObj.getAddress());
            about.setText("About Me : " + personalObj.getAboutMe());

            // Set birth date
            Calendar calender = Calendar.getInstance();
            calender.setTime(personalObj.getBirthDate());
            birthDate.setText("Birth Date : " + calender.getTime().toString());

            if(personalObj.getGender() != null){
                if(personalObj.getGender().equals("Male")) gender.setText("Gender : Male");
                if(personalObj.getGender().equals("Female")) gender.setText("Gender : Female");
            }

        }

        if(skillsObj != null){
            languages.setText("Languages : " + skillsObj.getFirstLanguage() + "," + skillsObj.getSecondLanguage());


            String skillsStr = "Skills : ";

            if(skillsObj.isProgramming()) skillsStr += "| Programming |";
            if(skillsObj.isGraphicDesign()) skillsStr += "| Graphic Design |";
            if(skillsObj.isVideoEditting()) skillsStr += "| Video Editing |";
            if(skillsObj.isVfx()) skillsStr += "| VFX |";
            if(skillsObj.isDrawing()) skillsStr += "| Drawing |";
            if(skillsObj.isPainting()) skillsStr += "| Painting |";
            if(skillsObj.isUiux()) skillsStr += "| UI / UX |";
            if(skillsObj.isTranslating()) skillsStr += "| Translating |";
            if(skillsObj.isWritting()) skillsStr += "| Writting |";
            if(skillsObj.isContentCreation()) skillsStr += "| Content Creation |";

            languages.setText(skillsStr);
        }

    }
}