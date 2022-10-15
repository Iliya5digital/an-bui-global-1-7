package androidx.appcompat.widget;

import android.graphics.Rect;
/* loaded from: classes2.dex */
public interface FitWindowsViewGroup {

    /* loaded from: classes2.dex */
    public interface OnFitSystemWindowsListener {
        void onFitSystemWindows(Rect rect);
    }

    void setOnFitSystemWindowsListener(OnFitSystemWindowsListener onFitSystemWindowsListener);
}
