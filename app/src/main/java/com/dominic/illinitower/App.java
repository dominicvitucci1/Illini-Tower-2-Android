package com.dominic.illinitower;

/**
 * Created by Dominic on 12/3/14.
 */
import com.parse.Parse;
import com.parse.PushService;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "f42OCeAicD2YSb374hgdZH8yyV5HZciiw0wtQhGt", "pBJczdgkxpBihlG2GJpPye1hA7DuyEtTqZaVqJBZ");
        PushService.setDefaultPushCallback(this, HomeActivity.class);
    }
}