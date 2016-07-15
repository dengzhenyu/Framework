package com.zhy.framework.app;

import android.app.Application;
import android.content.Context;

import com.bugtags.library.Bugtags;
import com.bugtags.library.BugtagsOptions;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by dzy on 2016/7/14.
 */
public class FrameApplication extends Application {

    private static FrameApplication sFrameApplication=null;
    @Override
    public void onCreate() {
        super.onCreate();
        sFrameApplication=this;
        BugtagsOptions options=new BugtagsOptions.Builder()
                .trackingLocation(true)
                .trackingCrashLog(BuildConfig.ISDEBUG)
                .trackingConsoleLog(true)
                .trackingUserSteps(true)
                .build();
        Bugtags.start("1af99c210673b8694dbfaf85f00b6612",this, BuildConfig.ISSHOWBUG,options);

        MobclickAgent.setCatchUncaughtExceptions(false);
    }
    public static Context getContext(){return sFrameApplication;}
}
