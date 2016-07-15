package com.zhy.framework.app;

/**
 * Created by dzy on 2016/7/14.
 */
public final class BuildConfig {
    //bugtags的三种方式
    private static final int BTGInvocationEventNone = 0;// 静默模式，只收集 Crash 信息（如果允许）
    private static final int BTGInvocationEventShake = 1;// 通过摇一摇呼出 Bugtags
    private static final int BTGInvocationEventBubble = 2;// 通过悬浮小球呼出 Bugtags


    public static final boolean ISDEBUG = Boolean.parseBoolean("true");
    public static final int ISSHOWBUG = BTGInvocationEventBubble;

}
