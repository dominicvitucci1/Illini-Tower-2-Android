package com.dominic.illinitower;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

/**
 * Created by jon on 11/13/14.
 */
public class BaseActivity extends ActionBarActivity {

    public void sendEmail(Intent intent) {
        try {
            startActivity(intent);
        } catch (android.content.ActivityNotFoundException ex) {
            new AlertDialog.Builder(BaseActivity.this)
                    .setTitle("Could Not Send Email")
                    .setMessage("Your device could not send e-mail.  Please check e-mail configuration and try again.")
                    .setNeutralButton("Ok", null)
                    .show();
        }
    }

    public void safeStartActivity(Intent intent) {
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(BaseActivity.this, "Your device is not setup to handle this action.", Toast.LENGTH_SHORT).show();
        }

    }
}
