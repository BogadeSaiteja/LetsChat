package com.saiteja.bogade.letschat;
/**
 * Created by saite_000 on 2/19/2017.
 */
import android.app.Application;

public class LetsChatApplication extends Application {
    private static boolean sIsChatActivityOpen = false;

    public static boolean isChatActivityOpen() {
        return sIsChatActivityOpen;
    }

    public static void setChatActivityOpen(boolean isChatActivityOpen) {
        LetsChatApplication.sIsChatActivityOpen = isChatActivityOpen;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
