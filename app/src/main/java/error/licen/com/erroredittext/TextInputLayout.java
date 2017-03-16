package error.licen.com.erroredittext;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


public class TextInputLayout extends LinearLayout implements TextWatcher {

    private FrameLayout mInputFrame;
    private TextView messageTextView;

    private String TEXTVIEW_DEFAULT_STRING;

    public TextInputLayout(Context context) {
        super(context);
        initContentView();
    }

    public TextInputLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initContentView();
    }

    public TextInputLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initContentView();
    }

    private void initContentView() {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.text_input_layout, this);
        mInputFrame = (FrameLayout) root.findViewById(R.id.textInputLayout_contentLayout);
        messageTextView = (TextView) root.findViewById(R.id.textInputLayout_errorTextView);
    }

    public void setTextViewDefaultString(String text) {
        this.TEXTVIEW_DEFAULT_STRING = text;
    }

    @Override
    public void addView(View child, int index, final ViewGroup.LayoutParams params) {
        if (child instanceof EditText) {

            addChildViewToFrameLayout(child, params);
            setEditText((EditText) child);

        } else if (child instanceof TextView) {

            addChildViewToFrameLayout(child, params);
            setTextView((TextView) child);

        } else {
            super.addView(child, index, params);
        }
    }

    private void addChildViewToFrameLayout(View child, ViewGroup.LayoutParams params) {
        FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(params);
        flp.gravity = Gravity.CENTER_VERTICAL | (flp.gravity & ~Gravity.VERTICAL_GRAVITY_MASK);
        mInputFrame.addView(child, flp);
        mInputFrame.setLayoutParams(params);
    }

    private void setTextView(TextView textView) {
        textView.addTextChangedListener(this);
    }

    private void setEditText(EditText editText) {
        editText.addTextChangedListener(this);
    }

    public void showMessage(String message) {
        if (messageTextView == null) {
            return;
        }
        messageTextView.setVisibility(VISIBLE);
        messageTextView.setText(message);
    }

    public void hideMessage() {
        if (messageTextView != null) {
            messageTextView.setVisibility(GONE);
            messageTextView.setText("");
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.e("", "onTextChanged s = " + s);
        hideMessage();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}
