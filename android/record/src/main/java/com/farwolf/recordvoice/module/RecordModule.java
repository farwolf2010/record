package com.farwolf.recordvoice.module;

import android.Manifest;
import android.app.Activity;

import com.farwolf.perssion.Perssion;
import com.farwolf.perssion.PerssionCallback;
import com.farwolf.recordvoice.ModuleResultListener;
import com.farwolf.recordvoice.RecorderManager;
import com.farwolf.weex.annotation.WeexModule;
import com.farwolf.weex.base.WXModuleBase;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;

import java.util.HashMap;

@WeexModule(name="record")
public class RecordModule extends WXModuleBase {


    @JSMethod
    public void start(final HashMap param){
//        Manifest.permission.RECORD_AUDIO

//        audioService=new AudioService();
        Perssion.check((Activity) mWXSDKInstance.getContext(), Manifest.permission.RECORD_AUDIO,new PerssionCallback(){


            @Override
            public void onGranted() {


                Perssion.check((Activity) mWXSDKInstance.getContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE , new PerssionCallback() {
                    @Override
                    public void onGranted() {

                        RecorderManager.getInstance().start(param, new ModuleResultListener() {
                            @Override
                            public void onResult(Object o) {

                            }
                        });
                    }
                });



            }
        });





    }

    @JSMethod
    public void pause(){
        RecorderManager.getInstance().pause(new ModuleResultListener() {
            @Override
            public void onResult(Object o) {

            }
        });
    }



    @JSMethod
    public void stop(final JSCallback callback){
        RecorderManager.getInstance().stop(new ModuleResultListener() {
            @Override
            public void onResult(Object o) {

                callback.invoke(o);
            }
        });
    }


}
