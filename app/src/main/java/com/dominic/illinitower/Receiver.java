package com.dominic.illinitower;

/**
 * Created by Dominic on 2/5/15.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParsePushBroadcastReceiver;
import com.parse.PushService;
import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;


public class Receiver extends ParsePushBroadcastReceiver {


    @Override
    public void onPushOpen(Context context, Intent intent) {



        Intent i = new Intent(context, HomeActivity.class);
        i.putExtras(intent.getExtras());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);


    }


//    private static final String TAG = "MyNotificationsReceiver";
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        try {
//            JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
//
//            String notificationText = json.getString("alert");
//
//
//        new AlertDialog.Builder(context)
//                .setTitle("News")
//                .setMessage(notificationText)
//                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                })
//                .show();
//
//        } catch (JSONException e) {
//            Log.d(TAG, "JSONException: " + e.getMessage());
//        }
//    }
}