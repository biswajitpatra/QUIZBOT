package com.bit.quizbot;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.annotation.TargetApi;
import android.app.Service;
import android.content.Intent;
import android.graphics.Path;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
//TODO: create a storage for prevoius question(static not working)

public class acc_service extends AccessibilityService {
    public acc_service() {
    }
    static String quesfs="a";
    //int mDebugDepth;
    AccessibilityNodeInfo mNodeInfo;
    AccessibilityNodeInfo parentInfo;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        //mDebugDepth = 0;
        mNodeInfo = event.getSource();
        if(mNodeInfo==null)
            return;
        parentInfo =gettosource(mNodeInfo);
        Nodeprinter(parentInfo, "");
        actiontaken(parentInfo);
        Log.v("FINAL:::", String.format("onAccessibilityEvent: type = [ %s ], class = [ %s ], package = [ %s ], time = [ %s ], text = [ %s ]", event.getEventType(), event.getClassName(), event.getPackageName(), event.getEventTime(), event.getText()));
    }
    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void actiontaken(AccessibilityNodeInfo node) {
        if(node.getChildCount()==5)
            if(node.getChild(1)!=null)
                if(node.getChild(1).getViewIdResourceName()!=null)
                    if(node.getChild(1).getViewIdResourceName().equalsIgnoreCase("com.bit.quizbot:id/question")){
                        String datatoc="";
                        String que=node.getChild(1).getText().toString().replace("?","").toLowerCase();
                        if(que.equals(quesfs))
                            return;
                        else
                        {
                            datatoc=que+"5662"+node.getChild(2).getText().toString().toLowerCase()+"5662"+node.getChild(3).getText().toString().toLowerCase()+"5662"+node.getChild(4).getText().toString().toLowerCase();
                            socketconn sendc=new socketconn();
                            sendc.execute(datatoc);
                            Log.e(":::","SEND QUE TO COMP:"+datatoc);
                        }


                    }


                        /*
        if (node.getChildCount() >= 2) {
            if (node.getChild(0) != null){
                if (node.getChild(0).getViewIdResourceName() != null) {
                    Log.e(":::", "done with:" + node.getChild(0).getViewIdResourceName());
                    if (node.getChild(0).getViewIdResourceName().equalsIgnoreCase("com.android.systemui:id/keyguard_indication_text")) {
                        Log.e(":::", "Finnaly execution");
                        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                        GestureDescription.Builder gestureBuilder = new GestureDescription.Builder();
                        Path path = new Path();
                        int YValue = displayMetrics.heightPixels / 5;
                        int middle = displayMetrics.widthPixels / 2;
                        // int rightSizeOfScreen = leftSideOfScreen * 3;

                        path.moveTo(middle, YValue * 4);
                        path.lineTo(middle, YValue);

                        gestureBuilder.addStroke(new GestureDescription.StrokeDescription(path, 0, 1));
                        dispatchGesture(gestureBuilder.build(), new GestureResultCallback() {
                            @Override
                            public void onCompleted(GestureDescription gestureDescription) {
                                Log.w(":::", "Gesture Completed");
                                super.onCompleted(gestureDescription);
                            }
                        }, null);


                    }
                    else if(node.getChild(0).getViewIdResourceName().equalsIgnoreCase("com.android.systemui:id/keyguard_host_view")){
                        Log.e(":::", "Finnaly execution password"+node.getChild(4).getViewIdResourceName());
                        node.getChild(0).getChild(4).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    }
                }
            }

        }
        */
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

        log= logu+ "("+mNodeInfo.getText()+"=="+((mNodeInfo.getViewIdResourceName() != null)?mNodeInfo.getViewIdResourceName():"NO VIEW ID")+"("+((mNodeInfo.isClickable())?"CLICKABLE":"")+")"+ "<--"+((mNodeInfo.getParent() != null)?mNodeInfo.getParent().getViewIdResourceName():"NO PARENT")+")";
        Log.d("::::", log);
        if(mNodeInfo.getChildCount()<1) return ;
       // mDebugDepth++;
        for(int i = 0; i < mNodeInfo.getChildCount(); i++){
            Nodeprinter(mNodeInfo.getChild(i),logu+"."+String.valueOf(i));
        }
       // mDebugDepth--;
    }
}