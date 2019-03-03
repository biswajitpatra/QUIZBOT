package com.bit.quizbot;

import android.accessibilityservice.AccessibilityService;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class acc_service extends AccessibilityService {
    public acc_service() {
    }

    //int mDebugDepth;
    AccessibilityNodeInfo mNodeInfo;
    AccessibilityNodeInfo parentInfo;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        //mDebugDepth = 0;
        mNodeInfo = event.getSource();
        parentInfo =gettosource(mNodeInfo);
        Nodeprinter(parentInfo, "");
        Log.v("FINAL:::", String.format("onAccessibilityEvent: type = [ %s ], class = [ %s ], package = [ %s ], time = [ %s ], text = [ %s ]", event.getEventType(), event.getClassName(), event.getPackageName(), event.getEventTime(), event.getText()));
    }

    @Override
    public void onInterrupt() {

    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private AccessibilityNodeInfo gettosource(AccessibilityNodeInfo node){
        if(node.getParent()==null)
            return node;
        else
            return gettosource(node.getParent());
    }
    /*
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void printAllViews(AccessibilityNodeInfo mNodeInfo) {
        if (mNodeInfo == null) return;
        String log = "";
        for (int i = 0; i < mDebugDepth; i++) {
            log += ".";
        }
        log += "(" + mNodeInfo.getText() + "==" + ((mNodeInfo.getViewIdResourceName() != null) ? mNodeInfo.getViewIdResourceName() : "NO VIEW ID") + "<--" + ((mNodeInfo.getParent() != null) ? mNodeInfo.getParent().getViewIdResourceName() : "NO PARENT ID") + ")";
        Log.d("::::", log);
        if (mNodeInfo.getChildCount() < 1) return;
        mDebugDepth++;
        for (int i = 0; i < mNodeInfo.getChildCount(); i++) {
            printAllViews(mNodeInfo.getChild(i));
        }
        mDebugDepth--;
    }*/
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void Nodeprinter(AccessibilityNodeInfo mNodeInfo,String logu){
        if(mNodeInfo == null) return ;
        String log = "";
        //for(int i = 0; i < mDebugDepth; i++){
       //    log+=".";
        //}

        log= logu+ "("+mNodeInfo.getText()+"=="+((mNodeInfo.getViewIdResourceName() != null)?mNodeInfo.getViewIdResourceName():"NO VIEW ID")+ "<--"+((mNodeInfo.getParent() != null)?mNodeInfo.getParent().getViewIdResourceName():"NO PARENT")+")";
        Log.d("::::", log);
        if(mNodeInfo.getChildCount()<1) return ;
       // mDebugDepth++;
        for(int i = 0; i < mNodeInfo.getChildCount(); i++){
            Nodeprinter(mNodeInfo.getChild(i),logu+"."+String.valueOf(i));
        }
       // mDebugDepth--;
    }
}