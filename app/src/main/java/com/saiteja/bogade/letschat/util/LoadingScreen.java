package com.saiteja.bogade.letschat.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

/**
 * Created by saite_000 on 6/13/2016.
 */
public class LoadingScreen {

    private static ProgressDialog mProgressDialog = null;

    public static void showProgressDialog(Context pContext,String pText){

        if(mProgressDialog == null){
            mProgressDialog = new ProgressDialog(pContext);
            mProgressDialog.setMessage(pText);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

    }

    public static void updateIndicatorMessage(Context pContext,String pText){
        if(mProgressDialog != null){
            mProgressDialog.setMessage(pText);
        }else {
            showProgressDialog(pContext,pText);
        }
    }

    public static void dismissProgressDialog(){

        if(mProgressDialog != null){
            try {
                if(mProgressDialog.isShowing()){
                    mProgressDialog.dismiss();
                }
            }catch (Exception ex){
                Log.e("dismissProgressDialog", "dismissProgressDialog " + ex);
            }
            mProgressDialog = null;
        }
    }
}
