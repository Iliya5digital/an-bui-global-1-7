package androidx.activity.result.contract;

import android.content.Context;
import android.content.Intent;
/* loaded from: classes3.dex */
public abstract class ActivityResultContract<I, O> {
    public abstract Intent createIntent(Context context, I input);

    /* renamed from: parseResult */
    public abstract O mo125parseResult(int resultCode, Intent intent);

    public SynchronousResult<O> getSynchronousResult(Context context, I input) {
        return null;
    }

    /* loaded from: classes3.dex */
    public static final class SynchronousResult<T> {
        private final T mValue;

        public SynchronousResult(T value) {
            this.mValue = value;
        }

        public T getValue() {
            return this.mValue;
        }
    }
}
