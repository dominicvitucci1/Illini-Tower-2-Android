package com.dominic.illinitower;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import com.parse.Parse;
import com.parse.ParseConfig;
import com.parse.ConfigCallback;
import com.parse.ParseException;

public class DiningActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


    }

    public void onSkilletChoiceClicked(View view) {
        final View dialogView = getLayoutInflater().inflate(R.layout.dialog_nominations, null);
        AlertDialog dialog = new AlertDialog.Builder(DiningActivity.this)
                .setView(dialogView)
                .setNeutralButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogI, int which) {
                        String name = ((EditText) dialogView.findViewById(R.id.et_nomination_name)).getText().toString();
                        String roomNo = ((EditText) dialogView.findViewById(R.id.et_nomination_room_no)).getText().toString();
                        String nomination =  ((EditText) dialogView.findViewById(R.id.et_nominations_nomination)).getText().toString();

                        String message = "Name: " + name + "\n" + "Room Number: " + roomNo + "\n" + "Nominated Food: " + nomination;

                        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Food Nomination");
                        intent.putExtra(Intent.EXTRA_TEXT, message);
                        intent.setData(Uri.parse("mailto:TheSkillet@illinitower.net")); // or just "mailto:" for blank
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                        sendEmail(intent);
                    }
                }).create();

        dialog.show();
    }

    public void onLatePlateClicked(View view) {
        final View dialogView = getLayoutInflater().inflate(R.layout.dialog_late_plate, null);
        AlertDialog dialog = new AlertDialog.Builder(DiningActivity.this)
                .setView(dialogView)
                .setNeutralButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogI, int which) {
                        String name = ((EditText) dialogView.findViewById(R.id.et_late_name)).getText().toString();
                        String meal = ((EditText) dialogView.findViewById(R.id.et_late_meal)).getText().toString();
                        String email =  ((EditText) dialogView.findViewById(R.id.et_late_email)).getText().toString();
                        String phoneNo = ((EditText) dialogView.findViewById(R.id.et_late_phone_no)).getText().toString();
                        String roomNo = ((EditText) dialogView.findViewById(R.id.et_late_room_no)).getText().toString();
                        String food = ((EditText) dialogView.findViewById(R.id.et_late_food)).getText().toString();
                        String notes = ((EditText) dialogView.findViewById(R.id.et_late_notes)).getText().toString();

                        String message = "Name: " + name + "\n" + "Meal: " + meal + "\n" + "Email: " + email + "\n" + "Phone Number: " + phoneNo + "\n" + "Room Number: " + roomNo + "\n" + "Food: " + food + "\n" + "Additional Notes: " + notes;

                        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Late Plate Request");
                        intent.putExtra(Intent.EXTRA_TEXT, message);
                        intent.setData(Uri.parse("mailto:TheSkillet@illinitower.net")); // or just "mailto:" for blank
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                        sendEmail(intent);
                    }
                }).create();

        dialog.show();
    }


    

    public void onMenuClicked(View view) {
        // TODO use Parse.com backend here

        ParseConfig.getInBackground(new ConfigCallback() {
            @Override
            public void done(ParseConfig config, ParseException e) {
                    String menu;
                menu = config.getString("menuLink");


                Intent openWebPage = new Intent(DiningActivity.this, WebActivity.class);
                String title = "Menu";
                String url = "https://drive.google.com/viewerng/viewer?embedded=true&url=" + menu;
                openWebPage.putExtra(WebActivity.TITLE, title);
                openWebPage.putExtra(WebActivity.URL, url);
                safeStartActivity(openWebPage);

            }
        });

//        Intent openWebPage = new Intent(DiningActivity.this, WebActivity.class);
//        String destinationUrl = "google,com";
//        String title = "Menu";
//        openWebPage.putExtra(WebActivity.TITLE, title);
//        openWebPage.putExtra(WebActivity.URL, destinationUrl);
//        safeStartActivity(openWebPage);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
