package com.dominic.illinitower;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.DatePicker;
import android.widget.EditText;

import com.parse.ConfigCallback;
import com.parse.ParseConfig;
import com.parse.ParseException;

import java.util.Calendar;
import java.util.Date;


public class MyIlliniActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_illini);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void onReferFriendClicked(View view) {
        final View dialogView = getLayoutInflater().inflate(R.layout.dialog_refer, null);
        AlertDialog dialog = new AlertDialog.Builder(MyIlliniActivity.this)
                .setView(dialogView)
                .setNeutralButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogI, int which) {
                        String name = ((EditText) dialogView.findViewById(R.id.et_ref_name)).getText().toString();
                        String email = ((EditText) dialogView.findViewById(R.id.et_ref_email)).getText().toString();
                        String roomNo = ((EditText) dialogView.findViewById(R.id.et_ref_room_no)).getText().toString();
                        String friendName = ((EditText) dialogView.findViewById(R.id.et_ref_friend_name)).getText().toString();
                        String friendEmail = ((EditText) dialogView.findViewById(R.id.et_ref_friend_email)).getText().toString();

                        DatePicker moveInDate = ((DatePicker) dialogView.findViewById(R.id.dp_ref_move_in));
                        int year = moveInDate.getYear();
                        int month = moveInDate.getMonth() + 1;
                        int day = moveInDate.getDayOfMonth();

                        String friendMoveIn = month + "/" + day + "/" + year;

                        String message = "Resident's Name: " + name + "\n"
                                + "Resident's Room Number: " + roomNo + "\n" + "Resident's Email: "
                                + email + "\n" + "Friend's Name: " + friendName
                                + "\n" + "Friend's Email:" + friendEmail + "\n"
                                + "Friend's Movie In Date: " + friendMoveIn;

                        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Refer A Friend");
                        intent.putExtra(Intent.EXTRA_TEXT, message);
                        intent.setData(Uri.parse("mailto:ben.bytheway@clvusa.com")); // or just "mailto:" for blank
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                        sendEmail(intent);
                    }
                }).create();

        dialog.show();
    }

    public void onOpenWebClicked(View view) {
        Intent openWebPage = new Intent(MyIlliniActivity.this, WebActivity.class);
        String destinationUrl = "";
        String title = "";

        switch (view.getId()){
            case R.id.my_contact_ra:
                //openWebPage.putExtra(WebActivity.MAIL_TO_LINKS, true);
//                destinationUrl = "file:////android_asset/contact_your_ra.html";
//                title = "Contact RA";

                ParseConfig.getInBackground(new ConfigCallback() {
                    @Override
                    public void done(ParseConfig config, ParseException e) {
                        String menu;
                        menu = config.getString("nonAtomicStratLink");


                        Intent openWebPage = new Intent(MyIlliniActivity.this, WebActivity.class);
                        String title = "Menu";
                        String data = menu;
                        openWebPage.putExtra(WebActivity.MAIL_TO_LINKS, true);
                        openWebPage.putExtra(WebActivity.TITLE, title);
                        openWebPage.putExtra(WebActivity.DATA, data);

                        safeStartActivity(openWebPage);

                    }
                });

                break;
            case R.id.my_work_ord:
                destinationUrl =  "https://portal.campushousing.com/ILLINOIS-Illini-Tower/Default.aspx?Params=L9ezxPcQnQuRGKTzF%2b4sxeNblvAA%2b26c";
                title = "Submit Work Order";
                break;
            case R.id.my_review:
                destinationUrl = "https://plus.google.com/local/Champaign%2C%20IL/s/Illini%20Tower";
                title = "Write Review";
                break;
        }

        openWebPage.putExtra(WebActivity.TITLE, title);
        openWebPage.putExtra(WebActivity.URL, destinationUrl);
        safeStartActivity(openWebPage);
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
