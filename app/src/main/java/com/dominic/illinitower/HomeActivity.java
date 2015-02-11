package com.dominic.illinitower;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.PushService;


public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ParseAnalytics.trackAppOpened(getIntent());


        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void onMyIlliniTowerClicked(View view){
        safeStartActivity(new Intent(HomeActivity.this, MyIlliniActivity.class));
    }

    public void onDiningClicked(View view){
        safeStartActivity(new Intent(HomeActivity.this, DiningActivity.class));
    }

    public void onCallCLicked(View view) {
        new AlertDialog.Builder(HomeActivity.this)
                .setTitle("Would you like to call the front desk?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri number = Uri.parse("tel:2173440400");
                        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                        startActivity(callIntent);
                    }
                }).show();

    }

    public void onNearbyClicked(View view) {
        double latitude = 40.106485;
        double longitude = -88.232341;
        String coordinates = String.format("geo:0,0?q=" + latitude + "," + longitude);
        Intent intentMap = new Intent( Intent.ACTION_VIEW, Uri.parse(coordinates) );
        safeStartActivity(intentMap);
    }

    public void onEmailClicked(View view){
        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
        intent.setType("text/plain");
        intent.setData(Uri.parse("mailto:info@illinitower.net")); // or just "mailto:" for blank
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
        try {
            safeStartActivity(intent);
        } catch (android.content.ActivityNotFoundException ex) {
            new AlertDialog.Builder(HomeActivity.this)
                    .setTitle("Could Not Open Map")
                    .setMessage("Your device could not open the coordinates.  Please make sure a maps application is installed and try again.")
                    .setNeutralButton("Ok", null)
                    .show();
        }
    }

    public void onSuggestionClicked(View view) {
        final View dialogView = getLayoutInflater().inflate(R.layout.dialog_suggestions, null);
        AlertDialog dialog = new AlertDialog.Builder(HomeActivity.this)
                .setView(dialogView)
                .setNeutralButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogI, int which) {
                        String messageName = ((EditText) dialogView.findViewById(R.id.et_suggestion_name)).getText().toString();
                        String messageAddress = ((EditText) dialogView.findViewById(R.id.et_suggestion_email)).getText().toString();
                        String messageSuggestion =  ((EditText) dialogView.findViewById(R.id.et_suggestion_suggestion)).getText().toString();

                        String message = "Name: " + messageName + "\n" + "Email Address: " + messageAddress + "\n" + "Suggestion: " + messageSuggestion;

                        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Suggestion");
                        intent.putExtra(Intent.EXTRA_TEXT, message);
                        intent.setData(Uri.parse("mailto:info@illinitower.net")); // or just "mailto:" for blank
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                        sendEmail(intent);
                    }
                }).create();

        dialog.show();
    }


    public void onOpenWebClicked(View view) {
        Intent openWebPage = new Intent(HomeActivity.this, WebActivity.class);
        String destinationUrl = "";
        String title = "";

        switch (view.getId()){
            case R.id.home_pay_rent:
                destinationUrl = "https://portal.campushousing.com/ILLINOIS-Illini-Tower/Default.aspx?Params=L9ezxPcQnQuRGKTzF%2b4sxeNblvAA%2b26c";
                title = "Pay Rent";
                break;
            case R.id.home_events:
                destinationUrl = "http://www.facebook.com/IlliniTower/events";
                title = "Events";
                break;
            case R.id.home_whats_nearby:
                destinationUrl = "";
                break;
            case R.id.home_instagram:
                destinationUrl = "http://instagram.com/illinitower";
                title = "Instagram";
                break;
            case R.id.home_twitter:
                destinationUrl = "http://www.twitter.com/illinitower";
                title = "Twitter";
                break;
            case R.id.home_facebook:
                destinationUrl = "http://www.facebook.com/illinitower";
                title = "Facebook";
                break;
        }

        openWebPage.putExtra(WebActivity.TITLE, title);
        openWebPage.putExtra(WebActivity.URL, destinationUrl);
        safeStartActivity(openWebPage);
    }

}
