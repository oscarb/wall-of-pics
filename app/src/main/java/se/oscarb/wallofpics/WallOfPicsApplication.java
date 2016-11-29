package se.oscarb.wallofpics;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;


public class WallOfPicsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
