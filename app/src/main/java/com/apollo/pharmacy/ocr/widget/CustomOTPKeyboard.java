package com.apollo.pharmacy.ocr.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apollo.pharmacy.ocr.R;

public class CustomOTPKeyboard extends LinearLayout implements View.OnClickListener {

    private TextView button1, button2, button3, button4,
            button5, button6, button7, button8,
            button9, button0;
    private ImageButton deleteImg;

    private SparseArray<String> keyValues = new SparseArray<>();
    private InputConnection inputConnection;

    public CustomOTPKeyboard(Context context) {
        this(context, null, 0);
    }

    public CustomOTPKeyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomOTPKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.custom_otp_keyboard, this, true);
        button1 = (TextView) findViewById(R.id.one_btn_text);
        button1.setOnClickListener(this);
        button2 = (TextView) findViewById(R.id.two_btn_text);
        button2.setOnClickListener(this);
        button3 = (TextView) findViewById(R.id.three_btn_text);
        button3.setOnClickListener(this);
        button4 = (TextView) findViewById(R.id.four_btn_text);
        button4.setOnClickListener(this);
        button5 = (TextView) findViewById(R.id.five_btn_text);
        button5.setOnClickListener(this);
        button6 = (TextView) findViewById(R.id.six_btn_text);
        button6.setOnClickListener(this);
        button7 = (TextView) findViewById(R.id.seven_btn_text);
        button7.setOnClickListener(this);
        button8 = (TextView) findViewById(R.id.eight_btn_text);
        button8.setOnClickListener(this);
        button9 = (TextView) findViewById(R.id.nine_btn_text);
        button9.setOnClickListener(this);
        button0 = (TextView) findViewById(R.id.zero_btn_text);
        button0.setOnClickListener(this);
        deleteImg = (ImageButton) findViewById(R.id.delete_btn);
        deleteImg.setOnClickListener(this);

        keyValues.put(R.id.one_btn_text, "1");
        keyValues.put(R.id.two_btn_text, "2");
        keyValues.put(R.id.three_btn_text, "3");
        keyValues.put(R.id.four_btn_text, "4");
        keyValues.put(R.id.five_btn_text, "5");
        keyValues.put(R.id.six_btn_text, "6");
        keyValues.put(R.id.seven_btn_text, "7");
        keyValues.put(R.id.eight_btn_text, "8");
        keyValues.put(R.id.nine_btn_text, "9");
        keyValues.put(R.id.zero_btn_text, "0");
    }

    @Override
    public void onClick(View view) {
        if (inputConnection == null)
            return;

        if (view.getId() == R.id.delete_btn) {
            CharSequence selectedText = inputConnection.getSelectedText(0);

            if (TextUtils.isEmpty(selectedText)) {
                inputConnection.deleteSurroundingText(1, 0);
            } else {
                inputConnection.commitText("", 1);
            }
        } else {
            String value = keyValues.get(view.getId());
            inputConnection.commitText(value, 1);
        }
    }

    public void setInputConnection(InputConnection ic) {
        inputConnection = ic;
    }
}