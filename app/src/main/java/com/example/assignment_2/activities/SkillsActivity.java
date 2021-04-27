package com.example.assignment_2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.example.assignment_2.R;
import com.example.assignment_2.models.Personal;
import com.example.assignment_2.models.Skills;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SkillsActivity extends AppCompatActivity {

    private Spinner firstLanguage;
    private Spinner secondLanguage;

    private CheckBox programming;
    private CheckBox graphicDesign;
    private CheckBox videoEditting;
    private CheckBox vfx;
    private CheckBox drawing;
    private CheckBox painting;
    private CheckBox uiux;
    private CheckBox translating;
    private CheckBox writting;
    private CheckBox contentCreation;


    // Prefs & GSON
    private SharedPreferences prefs;
    private SharedPreferences.Editor prefsEditor;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        // Get Views Data
        programming = findViewById(R.id.programmingCheckBox);
        graphicDesign = findViewById(R.id.graphicDesignCheckBox);
        videoEditting = findViewById(R.id.videoEdittingCheckBox);
        vfx = findViewById(R.id.vfxCheckBox);
        drawing = findViewById(R.id.drawingCheckBox);
        painting = findViewById(R.id.paintingCheckBox);
        uiux = findViewById(R.id.uiuxChechBox);
        translating = findViewById(R.id.translatingCheckBox);
        writting = findViewById(R.id.writtingCheckBox);
        contentCreation = findViewById(R.id.contentCreationCheckBox);


        // Set Prefs & GSON
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefsEditor =  prefs.edit();
        gson = new Gson();


        // Set Spinners
        setFirstLanguageSpinner();
        setSecondLanguageSpinner();

        // Load Data if exists
        String skillsStr = prefs.getString("skills", "");
        Skills skillsObj = gson.fromJson(skillsStr, Skills.class);

        if(skillsObj != null){
            for (int i=0;i<firstLanguage.getCount();i++){
                if (firstLanguage.getItemAtPosition(i).equals(skillsObj.getFirstLanguage())){
                    firstLanguage.setSelection(i);
                    break;
                }
            }

            for (int i=0;i<secondLanguage.getCount();i++){
                if (secondLanguage.getItemAtPosition(i).equals(skillsObj.getSecondLanguage())){
                    secondLanguage.setSelection(i);
                    break;
                }
            }

            programming.setChecked(skillsObj.isProgramming());
            graphicDesign.setChecked(skillsObj.isGraphicDesign());
            videoEditting.setChecked(skillsObj.isVideoEditting());
            vfx.setChecked(skillsObj.isVfx());
            drawing.setChecked(skillsObj.isDrawing());
            painting.setChecked(skillsObj.isPainting());
            uiux.setChecked(skillsObj.isUiux());
            translating.setChecked(skillsObj.isTranslating());
            writting.setChecked(skillsObj.isWritting());
            contentCreation.setChecked(skillsObj.isContentCreation());
        }
    }


    public void backOnClick(View view) {
        // Save data
        saveOnClick(view);

        // Go to previous activity
            Intent intent = new Intent(this, PersonalActivity.class);
        startActivity(intent);
    }

    public void nextOnClick(View view) {
        // Save data
        saveOnClick(view);

        // Go to next activity
        Intent intent = new Intent(this, CvActivity.class);
        startActivity(intent);
    }

    public void saveOnClick(View view) {
        Skills skillsObj = new Skills();

        skillsObj.setProgramming(programming.isChecked());
        skillsObj.setGraphicDesign(graphicDesign.isChecked());
        skillsObj.setVideoEditting(videoEditting.isChecked());
        skillsObj.setVfx(vfx.isChecked());
        skillsObj.setDrawing(drawing.isChecked());
        skillsObj.setPainting(painting.isChecked());
        skillsObj.setUiux(uiux.isChecked());
        skillsObj.setTranslating(translating.isChecked());
        skillsObj.setWritting(writting.isChecked());
        skillsObj.setContentCreation(contentCreation.isChecked());

        skillsObj.setFirstLanguage(firstLanguage.getSelectedItem().toString());
        skillsObj.setSecondLanguage(secondLanguage.getSelectedItem().toString());


        String skillsInfo = gson.toJson(skillsObj);
        prefsEditor.putString("skills",skillsInfo);
        prefsEditor.commit();
    }


    private void setFirstLanguageSpinner() {
        firstLanguage = findViewById(R.id.firstLanguageSpinner);
        ArrayList<String> contries = new ArrayList<String>();
        contries.add("Arabic");
        contries.add("English");
        contries.add("Spanish");
        contries.add("Hebrew");
        contries.add("French");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, contries);
        firstLanguage.setAdapter(arrayAdapter);
    }

    private void setSecondLanguageSpinner() {
        secondLanguage = findViewById(R.id.secondLanguageSpinner);
        ArrayList<String> contries = new ArrayList<String>();
        contries.add("Arabic");
        contries.add("English");
        contries.add("Spanish");
        contries.add("Hebrew");
        contries.add("French");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, contries);
        secondLanguage.setAdapter(arrayAdapter);
    }


}