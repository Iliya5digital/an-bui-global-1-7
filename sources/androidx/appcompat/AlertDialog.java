package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.R;
import androidx.appcompat.app.AlertController;
/* loaded from: classes2.dex */
public class AlertDialog extends AppCompatDialog implements DialogInterface {
    static final int LAYOUT_HINT_NONE = 0;
    static final int LAYOUT_HINT_SIDE = 1;
    final AlertController mAlert;

    protected AlertDialog(Context context) {
        this(context, 0);
    }

    protected AlertDialog(Context context, int themeResId) {
        super(context, resolveDialogTheme(context, themeResId));
        this.mAlert = new AlertController(getContext(), this, getWindow());
    }

    protected AlertDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        this(context, 0);
        setCancelable(cancelable);
        setOnCancelListener(cancelListener);
    }

    static int resolveDialogTheme(Context context, int resid) {
        if (((resid >>> 24) & 255) >= 1) {
            return resid;
        }
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, outValue, true);
        return outValue.resourceId;
    }

    public Button getButton(int whichButton) {
        return this.mAlert.getButton(whichButton);
    }

    public ListView getListView() {
        return this.mAlert.getListView();
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        this.mAlert.setTitle(title);
    }

    public void setCustomTitle(View customTitleView) {
        this.mAlert.setCustomTitle(customTitleView);
    }

    public void setMessage(CharSequence message) {
        this.mAlert.setMessage(message);
    }

    public void setView(View view) {
        this.mAlert.setView(view);
    }

    public void setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
        this.mAlert.setView(view, viewSpacingLeft, viewSpacingTop, viewSpacingRight, viewSpacingBottom);
    }

    void setButtonPanelLayoutHint(int layoutHint) {
        this.mAlert.setButtonPanelLayoutHint(layoutHint);
    }

    public void setButton(int whichButton, CharSequence text, Message msg) {
        this.mAlert.setButton(whichButton, text, null, msg, null);
    }

    public void setButton(int whichButton, CharSequence text, DialogInterface.OnClickListener listener) {
        this.mAlert.setButton(whichButton, text, listener, null, null);
    }

    public void setButton(int whichButton, CharSequence text, Drawable icon, DialogInterface.OnClickListener listener) {
        this.mAlert.setButton(whichButton, text, listener, null, icon);
    }

    public void setIcon(int resId) {
        this.mAlert.setIcon(resId);
    }

    public void setIcon(Drawable icon) {
        this.mAlert.setIcon(icon);
    }

    public void setIconAttribute(int attrId) {
        TypedValue out = new TypedValue();
        getContext().getTheme().resolveAttribute(attrId, out, true);
        this.mAlert.setIcon(out.resourceId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAlert.installContent();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (this.mAlert.onKeyDown(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (this.mAlert.onKeyUp(keyCode, event)) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private final AlertController.AlertParams P;
        private final int mTheme;

        public Builder(Context context) {
            this(context, AlertDialog.resolveDialogTheme(context, 0));
        }

        public Builder(Context context, int themeResId) {
            this.P = new AlertController.AlertParams(new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, themeResId)));
            this.mTheme = themeResId;
        }

        public Context getContext() {
            return this.P.mContext;
        }

        /* renamed from: setTitle */
        public Builder mo372setTitle(int titleId) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mTitle = alertParams.mContext.getText(titleId);
            return this;
        }

        /* renamed from: setTitle */
        public Builder mo373setTitle(CharSequence title) {
            this.P.mTitle = title;
            return this;
        }

        /* renamed from: setCustomTitle */
        public Builder mo344setCustomTitle(View customTitleView) {
            this.P.mCustomTitleView = customTitleView;
            return this;
        }

        /* renamed from: setMessage */
        public Builder mo350setMessage(int messageId) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mMessage = alertParams.mContext.getText(messageId);
            return this;
        }

        /* renamed from: setMessage */
        public Builder mo351setMessage(CharSequence message) {
            this.P.mMessage = message;
            return this;
        }

        /* renamed from: setIcon */
        public Builder mo345setIcon(int iconId) {
            this.P.mIconId = iconId;
            return this;
        }

        /* renamed from: setIcon */
        public Builder mo346setIcon(Drawable icon) {
            this.P.mIcon = icon;
            return this;
        }

        /* renamed from: setIconAttribute */
        public Builder mo347setIconAttribute(int attrId) {
            TypedValue out = new TypedValue();
            this.P.mContext.getTheme().resolveAttribute(attrId, out, true);
            this.P.mIconId = out.resourceId;
            return this;
        }

        /* renamed from: setPositiveButton */
        public Builder mo365setPositiveButton(int textId, DialogInterface.OnClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mPositiveButtonText = alertParams.mContext.getText(textId);
            this.P.mPositiveButtonListener = listener;
            return this;
        }

        /* renamed from: setPositiveButton */
        public Builder mo366setPositiveButton(CharSequence text, DialogInterface.OnClickListener listener) {
            this.P.mPositiveButtonText = text;
            this.P.mPositiveButtonListener = listener;
            return this;
        }

        /* renamed from: setPositiveButtonIcon */
        public Builder mo367setPositiveButtonIcon(Drawable icon) {
            this.P.mPositiveButtonIcon = icon;
            return this;
        }

        /* renamed from: setNegativeButton */
        public Builder mo355setNegativeButton(int textId, DialogInterface.OnClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mNegativeButtonText = alertParams.mContext.getText(textId);
            this.P.mNegativeButtonListener = listener;
            return this;
        }

        /* renamed from: setNegativeButton */
        public Builder mo356setNegativeButton(CharSequence text, DialogInterface.OnClickListener listener) {
            this.P.mNegativeButtonText = text;
            this.P.mNegativeButtonListener = listener;
            return this;
        }

        /* renamed from: setNegativeButtonIcon */
        public Builder mo357setNegativeButtonIcon(Drawable icon) {
            this.P.mNegativeButtonIcon = icon;
            return this;
        }

        /* renamed from: setNeutralButton */
        public Builder mo358setNeutralButton(int textId, DialogInterface.OnClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mNeutralButtonText = alertParams.mContext.getText(textId);
            this.P.mNeutralButtonListener = listener;
            return this;
        }

        /* renamed from: setNeutralButton */
        public Builder mo359setNeutralButton(CharSequence text, DialogInterface.OnClickListener listener) {
            this.P.mNeutralButtonText = text;
            this.P.mNeutralButtonListener = listener;
            return this;
        }

        /* renamed from: setNeutralButtonIcon */
        public Builder mo360setNeutralButtonIcon(Drawable icon) {
            this.P.mNeutralButtonIcon = icon;
            return this;
        }

        /* renamed from: setCancelable */
        public Builder mo342setCancelable(boolean cancelable) {
            this.P.mCancelable = cancelable;
            return this;
        }

        /* renamed from: setOnCancelListener */
        public Builder mo361setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.P.mOnCancelListener = onCancelListener;
            return this;
        }

        /* renamed from: setOnDismissListener */
        public Builder mo362setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            this.P.mOnDismissListener = onDismissListener;
            return this;
        }

        /* renamed from: setOnKeyListener */
        public Builder mo364setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
            this.P.mOnKeyListener = onKeyListener;
            return this;
        }

        /* renamed from: setItems */
        public Builder mo348setItems(int itemsId, DialogInterface.OnClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mItems = alertParams.mContext.getResources().getTextArray(itemsId);
            this.P.mOnClickListener = listener;
            return this;
        }

        /* renamed from: setItems */
        public Builder mo349setItems(CharSequence[] items, DialogInterface.OnClickListener listener) {
            this.P.mItems = items;
            this.P.mOnClickListener = listener;
            return this;
        }

        /* renamed from: setAdapter */
        public Builder mo341setAdapter(ListAdapter adapter, DialogInterface.OnClickListener listener) {
            this.P.mAdapter = adapter;
            this.P.mOnClickListener = listener;
            return this;
        }

        /* renamed from: setCursor */
        public Builder mo343setCursor(Cursor cursor, DialogInterface.OnClickListener listener, String labelColumn) {
            this.P.mCursor = cursor;
            this.P.mLabelColumn = labelColumn;
            this.P.mOnClickListener = listener;
            return this;
        }

        /* renamed from: setMultiChoiceItems */
        public Builder mo352setMultiChoiceItems(int itemsId, boolean[] checkedItems, DialogInterface.OnMultiChoiceClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mItems = alertParams.mContext.getResources().getTextArray(itemsId);
            this.P.mOnCheckboxClickListener = listener;
            this.P.mCheckedItems = checkedItems;
            this.P.mIsMultiChoice = true;
            return this;
        }

        /* renamed from: setMultiChoiceItems */
        public Builder mo354setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, DialogInterface.OnMultiChoiceClickListener listener) {
            this.P.mItems = items;
            this.P.mOnCheckboxClickListener = listener;
            this.P.mCheckedItems = checkedItems;
            this.P.mIsMultiChoice = true;
            return this;
        }

        /* renamed from: setMultiChoiceItems */
        public Builder mo353setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn, DialogInterface.OnMultiChoiceClickListener listener) {
            this.P.mCursor = cursor;
            this.P.mOnCheckboxClickListener = listener;
            this.P.mIsCheckedColumn = isCheckedColumn;
            this.P.mLabelColumn = labelColumn;
            this.P.mIsMultiChoice = true;
            return this;
        }

        /* renamed from: setSingleChoiceItems */
        public Builder mo368setSingleChoiceItems(int itemsId, int checkedItem, DialogInterface.OnClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mItems = alertParams.mContext.getResources().getTextArray(itemsId);
            this.P.mOnClickListener = listener;
            this.P.mCheckedItem = checkedItem;
            this.P.mIsSingleChoice = true;
            return this;
        }

        /* renamed from: setSingleChoiceItems */
        public Builder mo369setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, DialogInterface.OnClickListener listener) {
            this.P.mCursor = cursor;
            this.P.mOnClickListener = listener;
            this.P.mCheckedItem = checkedItem;
            this.P.mLabelColumn = labelColumn;
            this.P.mIsSingleChoice = true;
            return this;
        }

        /* renamed from: setSingleChoiceItems */
        public Builder mo371setSingleChoiceItems(CharSequence[] items, int checkedItem, DialogInterface.OnClickListener listener) {
            this.P.mItems = items;
            this.P.mOnClickListener = listener;
            this.P.mCheckedItem = checkedItem;
            this.P.mIsSingleChoice = true;
            return this;
        }

        /* renamed from: setSingleChoiceItems */
        public Builder mo370setSingleChoiceItems(ListAdapter adapter, int checkedItem, DialogInterface.OnClickListener listener) {
            this.P.mAdapter = adapter;
            this.P.mOnClickListener = listener;
            this.P.mCheckedItem = checkedItem;
            this.P.mIsSingleChoice = true;
            return this;
        }

        /* renamed from: setOnItemSelectedListener */
        public Builder mo363setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
            this.P.mOnItemSelectedListener = listener;
            return this;
        }

        /* renamed from: setView */
        public Builder mo374setView(int layoutResId) {
            this.P.mView = null;
            this.P.mViewLayoutResId = layoutResId;
            this.P.mViewSpacingSpecified = false;
            return this;
        }

        /* renamed from: setView */
        public Builder mo375setView(View view) {
            this.P.mView = view;
            this.P.mViewLayoutResId = 0;
            this.P.mViewSpacingSpecified = false;
            return this;
        }

        @Deprecated
        public Builder setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
            this.P.mView = view;
            this.P.mViewLayoutResId = 0;
            this.P.mViewSpacingSpecified = true;
            this.P.mViewSpacingLeft = viewSpacingLeft;
            this.P.mViewSpacingTop = viewSpacingTop;
            this.P.mViewSpacingRight = viewSpacingRight;
            this.P.mViewSpacingBottom = viewSpacingBottom;
            return this;
        }

        @Deprecated
        public Builder setInverseBackgroundForced(boolean useInverseBackground) {
            this.P.mForceInverseBackground = useInverseBackground;
            return this;
        }

        public Builder setRecycleOnMeasureEnabled(boolean enabled) {
            this.P.mRecycleOnMeasure = enabled;
            return this;
        }

        public AlertDialog create() {
            AlertDialog dialog = new AlertDialog(this.P.mContext, this.mTheme);
            this.P.apply(dialog.mAlert);
            dialog.setCancelable(this.P.mCancelable);
            if (this.P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(this.P.mOnCancelListener);
            dialog.setOnDismissListener(this.P.mOnDismissListener);
            if (this.P.mOnKeyListener != null) {
                dialog.setOnKeyListener(this.P.mOnKeyListener);
            }
            return dialog;
        }

        public AlertDialog show() {
            AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }
}
