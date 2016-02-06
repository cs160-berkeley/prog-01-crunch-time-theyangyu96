package com.example.yang.crunchtime;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final Context context = this;
    protected double cal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeSpinner();
    }

    public void calc(View v){
        String exerciseType = ((Spinner)findViewById(R.id.spinner)).getSelectedItem().toString();
        String num = ((EditText)findViewById(R.id.editText)).getText().toString();
       //alert for nothing
        if (num.equals("")) {
            alertUser();
        } else {
            double calories = 0;
            double amt = Double.parseDouble(num);
            int exeType = ((Spinner)findViewById(R.id.spinner)).getSelectedItemPosition();
            if (exeType < 6 && !sanityCheck(amt)) {
                sanityAlert();

            } else {
                switch (exeType) {
                    case 0: calories = amt /350 * 100;
                        break;
                    case 1: calories = amt /200 * 100;
                        break;
                    case 2: calories = amt /225 * 100;
                        break;
                    case 3: calories = amt /100 * 100;
                        break;
                    case 4: calories = amt /10 * 100;
                        break;
                    case 5: calories = amt /25 * 100;
                        break;
                    case 6: calories = amt /20 * 100;
                        break;
                    case 7: calories = amt /25 * 100;
                        break;
                    case 8: calories = amt /13 * 100;
                        break;
                    case 9: calories = amt /15 * 100;
                        break;
                }
            }
            ((TextView)findViewById(R.id.textView2)).setText(""+(int)calories);
            cal = calories;

    }
    }
    public void startNewView(View v){
        setContentView(R.layout.equiv);
        ((TextView)findViewById(R.id.textView4)).setText("" + (int)(cal / 100 * 350) + " reps");

        ((TextView)findViewById(R.id.textView6)).setText("" + (int)(cal / 100 * 200) + " reps");

        ((TextView)findViewById(R.id.textView8)).setText("" + (int)(cal / 100 * 225) + " reps");

        ((TextView)findViewById(R.id.textView10)).setText("" + (int)(cal / 100 * 100) + " reps");

        ((TextView)findViewById(R.id.textView12)).setText("" + (int)(cal / 100 * 10) + " mins");

        ((TextView)findViewById(R.id.textView14)).setText("" + (int)(cal / 100 * 25) + " mins");

        ((TextView)findViewById(R.id.textView16)).setText("" + (int)(cal / 100 * 20) + " mins");

        ((TextView)findViewById(R.id.textView18)).setText("" + (int)(cal / 100 * 25) + " mins");

        ((TextView)findViewById(R.id.textView20)).setText("" + (int)(cal / 100 * 13) + " mins");

        ((TextView)findViewById(R.id.textView22)).setText("" + (int)(cal / 100 * 15) + " mins");
    }

    public void returnHere(View v){
        setContentView(R.layout.activity_main);
        cal = 0;
        makeSpinner();
    }
    public boolean sanityCheck(double v){
        if ((v == Math.floor(v)) && !Double.isInfinite(v)) {
            // integral type
            return true;
        }
        return false;
    }
    public void alertUser()
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Hey, you didn't enter anything!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Sure",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    public void sanityAlert()
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Please enter integer value for repetitions!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Got it!",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }
    public void makeSpinner(){
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.exercise_array, R.layout.whitespin);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                int index = arg0.getSelectedItemPosition();
                if (index < 4) {
                    ((TextView)findViewById(R.id.unit)).setText("repetition(s)");
                } else {
                    ((TextView)findViewById(R.id.unit)).setText("minute(s)");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }
}
