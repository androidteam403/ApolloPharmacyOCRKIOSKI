package com.apollo.pharmacy.ocr.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.apollo.pharmacy.ocr.R;

import java.util.ArrayList;

/**
 * Created by Apollo on 10-01-2017.
 */

public class KeyboardFragment extends Fragment implements OnClickListener {

    public static KeyboardFragment newInstance(Context context, OnClickKeyboard onClickKeyboard) {
        KeyboardFragment fragment = new KeyboardFragment();
        mContext = context;
        mClickKeyboard = onClickKeyboard;
        return fragment;
    }

    private static OnClickKeyboard mClickKeyboard;

    public String str = "";
    public int cursorPos = 0;
    public String inputFilter = "";
    public String[] filterArray = {"text", "number", "text_number_special", "text_number", "number_special", "text_special"};
    public EditText editTextFocus;
    private static Context mContext;
    private Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero, btnA, btnB, btnC,
            btnD, btnE, btnF, btnG, btnH, btnI, btnJ, btnK, btnL, btnM, btnN, btnO, btnP, btnQ, btnR, btnS, btnT, btnU, btnV, btnW, btnX, btnY, btnZ,
            btnAt, btnHash, btnAnd, btnStar, btnApo, btnPercentage, btnComma, btnDot, btnHyfen, btnSlash, btnSpace, btnDel, btnClear, btnEnter, btnClearCustom, btnEnterCustom,
            btnSpaceCustom, btnNormalKeyBoard, btnSpecialKeyboard, btnSkinChange;
    private Button btnNormalHyfen, btnNormalAnd, btnNormalComma, btnNormalDot;
    private ImageButton btnHide, btnSymHide;
    private RelativeLayout rl_clear, rl_clear_custom;
    private LinearLayout ll_normal_keyboard, ll_custom_keyboard, llSecondRow, llThirdRow, llSecondRowRoot, llThirdRowRoot;
    private ArrayList<String> dynamicCharactersList = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_keyboard, container, false);
        initUI(view);
        getDynamicCharacters();
        return view;
    }

    private void getDynamicCharacters() {
        dynamicCharactersList.clear();
        dynamicCharactersList.add("^");
        dynamicCharactersList.add("(");
        dynamicCharactersList.add(")");
        addDynamicCharacters();
    }

    private void addDynamicCharacters() {
        try {
            if (dynamicCharactersList.size() > 0) {
                llSecondRowRoot.setVisibility(View.VISIBLE);
                if (llSecondRow.getChildCount() > 0) llSecondRow.removeAllViews();
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                for (int i = 0; i < dynamicCharactersList.size(); i++) {
                    View view = inflater.inflate(R.layout.item_keyboard_btn, null);
                    Button btn = view.findViewById(R.id.btn_extra);
                    btn.setBackgroundResource(R.drawable.kb_normal_btn);
                    btn.setText(dynamicCharactersList.get(i));
                    btn.setOnClickListener(v -> {
                        Button btn12 = (Button) v;
                        String txt = btn12.getText().toString().trim();
                        sendString(txt);
                    });
                    llSecondRow.addView(view);
                }
            }
            if (dynamicCharactersList.size() > 10) {
                llThirdRowRoot.setVisibility(View.VISIBLE);
                if (llThirdRow.getChildCount() > 0) llThirdRow.removeAllViews();
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                for (int i = 10; i < dynamicCharactersList.size(); i++) {
                    View view = inflater.inflate(R.layout.item_keyboard_btn, null);
                    Button btn = view.findViewById(R.id.btn_extra);
                    btn.setBackgroundResource(R.drawable.kb_normal_btn);
                    btn.setText(dynamicCharactersList.get(i));
                    btn.setOnClickListener(v -> {
                        Button btn1 = (Button) v;
                        String txt = btn1.getText().toString().trim();
                        sendString(txt);
                    });
                    llThirdRow.addView(view);
                }
            }
        } catch (Exception e) {

        }
    }

    private void initUI(View view) {
        btnOne = view.findViewById(R.id.btn_1);
        btnTwo = view.findViewById(R.id.btn_2);
        btnThree = view.findViewById(R.id.btn_3);
        btnFour = view.findViewById(R.id.btn_4);
        btnFive = view.findViewById(R.id.btn_5);
        btnSix = view.findViewById(R.id.btn_6);
        btnSeven = view.findViewById(R.id.btn_7);
        btnEight = view.findViewById(R.id.btn_8);
        btnNine = view.findViewById(R.id.btn_9);
        btnZero = view.findViewById(R.id.btn_0);
        btnA = view.findViewById(R.id.btn_a);
        btnB = view.findViewById(R.id.btn_b);
        btnC = view.findViewById(R.id.btn_c);
        btnD = view.findViewById(R.id.btn_d);
        btnE = view.findViewById(R.id.btn_e);
        btnF = view.findViewById(R.id.btn_f);
        btnG = view.findViewById(R.id.btn_g);
        btnH = view.findViewById(R.id.btn_h);
        btnI = view.findViewById(R.id.btn_i);
        btnJ = view.findViewById(R.id.btn_j);
        btnK = view.findViewById(R.id.btn_k);
        btnL = view.findViewById(R.id.btn_l);
        btnM = view.findViewById(R.id.btn_m);
        btnN = view.findViewById(R.id.btn_n);
        btnO = view.findViewById(R.id.btn_o);
        btnP = view.findViewById(R.id.btn_p);
        btnQ = view.findViewById(R.id.btn_q);
        btnR = view.findViewById(R.id.btn_r);
        btnS = view.findViewById(R.id.btn_s);
        btnT = view.findViewById(R.id.btn_t);
        btnU = view.findViewById(R.id.btn_u);
        btnV = view.findViewById(R.id.btn_v);
        btnW = view.findViewById(R.id.btn_w);
        btnX = view.findViewById(R.id.btn_x);
        btnY = view.findViewById(R.id.btn_y);
        btnZ = view.findViewById(R.id.btn_z);
        btnAt = view.findViewById(R.id.btn_at);
        btnHash = view.findViewById(R.id.btn_hash);
        btnAnd = view.findViewById(R.id.btn_and);
        btnStar = view.findViewById(R.id.btn_star);
        btnApo = view.findViewById(R.id.btn_apo);
        btnPercentage = view.findViewById(R.id.btn_percentage);
        btnComma = view.findViewById(R.id.btn_comma);
        btnDot = view.findViewById(R.id.btn_dot);
        btnHyfen = view.findViewById(R.id.btn_hyfen);

        btnNormalComma = view.findViewById(R.id.btn_comma_normal);
        btnNormalDot = view.findViewById(R.id.btn_dot_normal);
        btnNormalHyfen = view.findViewById(R.id.btn_hyfen_normal);
        btnNormalAnd = view.findViewById(R.id.btn_and_normal);
        btnHide = view.findViewById(R.id.btn_hide);
        btnSymHide = view.findViewById(R.id.btn_hide_sym);

        btnSlash = view.findViewById(R.id.btn_slash);
        btnEnter = view.findViewById(R.id.btn_enter);
        btnSpace = view.findViewById(R.id.btn_space);
        btnDel = view.findViewById(R.id.btn_del);
        btnClear = view.findViewById(R.id.btn_clear);
        btnClearCustom = view.findViewById(R.id.btn_clear_custom);
        btnEnterCustom = view.findViewById(R.id.btn_enter_custom);
        btnSpaceCustom = view.findViewById(R.id.btn_space_custom);
        btnNormalKeyBoard = view.findViewById(R.id.btn_toggle_normal);
        btnSpecialKeyboard = view.findViewById(R.id.btn_toggle_special);
        btnSkinChange = view.findViewById(R.id.btn_toggle_skin);
        rl_clear = view.findViewById(R.id.rl_clear);
        rl_clear_custom = view.findViewById(R.id.rl_clear_custom);

        ll_normal_keyboard = view.findViewById(R.id.ll_normal_keyboard);
        ll_custom_keyboard = view.findViewById(R.id.ll_custom_keyboard);
        ll_normal_keyboard.setTag("1");

        llSecondRow = view.findViewById(R.id.ll_second_row);
        llThirdRow = view.findViewById(R.id.ll_third_row);
        llSecondRowRoot = view.findViewById(R.id.ll_second_row_root);
        llThirdRowRoot = view.findViewById(R.id.ll_third_row_root);

        ll_normal_keyboard.setVisibility(View.VISIBLE);
        ll_custom_keyboard.setVisibility(View.GONE);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnE.setOnClickListener(this);
        btnF.setOnClickListener(this);
        btnG.setOnClickListener(this);
        btnH.setOnClickListener(this);
        btnI.setOnClickListener(this);
        btnJ.setOnClickListener(this);
        btnK.setOnClickListener(this);
        btnL.setOnClickListener(this);
        btnM.setOnClickListener(this);
        btnN.setOnClickListener(this);
        btnO.setOnClickListener(this);
        btnP.setOnClickListener(this);
        btnQ.setOnClickListener(this);
        btnR.setOnClickListener(this);
        btnS.setOnClickListener(this);
        btnT.setOnClickListener(this);
        btnU.setOnClickListener(this);
        btnV.setOnClickListener(this);
        btnW.setOnClickListener(this);
        btnX.setOnClickListener(this);
        btnY.setOnClickListener(this);
        btnZ.setOnClickListener(this);
        btnAt.setOnClickListener(this);
        btnHash.setOnClickListener(this);
        btnAnd.setOnClickListener(this);
        btnStar.setOnClickListener(this);
        btnApo.setOnClickListener(this);
        btnPercentage.setOnClickListener(this);
        btnComma.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnHyfen.setOnClickListener(this);

        btnNormalComma.setOnClickListener(this);
        btnNormalDot.setOnClickListener(this);
        btnNormalHyfen.setOnClickListener(this);
        btnNormalAnd.setOnClickListener(this);
        btnHide.setOnClickListener(this);
        btnSymHide.setOnClickListener(this);

        btnSlash.setOnClickListener(this);
        btnEnter.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnClear.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                sendString("longbackspace");
                return false;
            }
        });
        btnSpace.setOnClickListener(this);
        rl_clear.setOnClickListener(this);
        btnClearCustom.setOnClickListener(this);
        btnEnterCustom.setOnClickListener(this);
        btnSpaceCustom.setOnClickListener(this);
        btnNormalKeyBoard.setOnClickListener(this);
        btnSpecialKeyboard.setOnClickListener(this);
        btnSkinChange.setOnClickListener(this);
        rl_clear_custom.setOnClickListener(this);
    }

    private void sendString(String ch) {
        try {
            if (filterValidation(ch)) {
                if (ch.equals("delete")) {
                    if (str.length() == 1) {
                        str = "";
                        cursorPos = 0;
                    } else if (str.length() > 1 && cursorPos < str.length()) {
                        StringBuilder sb = new StringBuilder(str);
                        sb.deleteCharAt(cursorPos);
                        str = sb.toString();
                    }
                } else if (ch.equals("backspace")) {
                    if (str.length() == 1 || str.length() == 0) {
                        str = "";
                        cursorPos = 0;
                    } else if (str.length() > 1) {
                        if (cursorPos >= 1) {
                            --cursorPos;
                            StringBuilder sb = new StringBuilder(str);
                            sb.deleteCharAt(cursorPos);
                            str = sb.toString();
                        }
                    }
                } else if (ch.equals("longbackspace")) {
                    try {
                        int j = cursorPos;
                        for (int i = 0; i <= j; i++) {
                            if (str.length() == 1 || str.length() == 0) {
                                str = "";
                                cursorPos = 0;
                            } else if (str.length() > 1) {
                                if (cursorPos >= 1) {
                                    --cursorPos;
                                    StringBuilder sb = new StringBuilder(str);
                                    sb.deleteCharAt(cursorPos);
                                    str = sb.toString();
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (ch.equals("enter")) {
                    mClickKeyboard.onEnter(editTextFocus, str);
                } else {
                    int pos = cursorPos;
                    if (str.length() == 0 || pos == -1 || cursorPos == 0) pos = 0;
                    StringBuilder sb = new StringBuilder(str);
                    sb.insert(pos, ch);
                    str = sb.toString();
                    cursorPos++;
                }
                if (!ch.equals("enter"))
                    mClickKeyboard.getKeyboardText(editTextFocus, str, cursorPos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean filterValidation(String ch) {
        boolean flag = false;
        if (ch.equals("delete") || ch.equals("backspace") || ch.equals("longbackspace") || ch.equals("enter")) {
            flag = true;
        } else if (inputFilter.equals(filterArray[0])) {
            String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
            if (text.contains(ch)) {
                flag = true;
            }
        } else if (inputFilter.equals(filterArray[1])) {
            String text = "1234567890 ";
            if (text.contains(ch)) {
                flag = true;
            }
        } else if (inputFilter.equals(filterArray[2])) {
            String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890@#%&*,./-' ";
            if (text.contains(ch) || dynamicCharactersList.contains(ch)) {
                flag = true;
            }
        } else if (inputFilter.equals(filterArray[3])) {
            String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ";
            if (text.contains(ch)) {
                flag = true;
            }
        } else if (inputFilter.equals(filterArray[4])) {
            String text = "1234567890@#%&*,./-' ";
            if (text.contains(ch) || dynamicCharactersList.contains(ch)) {
                flag = true;
            }
        } else if (inputFilter.equals(filterArray[5])) {
            String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ@#%&*,./-' ";
            if (text.contains(ch) || dynamicCharactersList.contains(ch)) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btn_0:
                    sendString("0");
                    break;
                case R.id.btn_1:
                    sendString("1");
                    break;
                case R.id.btn_2:
                    sendString("2");
                    break;
                case R.id.btn_3:
                    sendString("3");
                    break;
                case R.id.btn_4:
                    sendString("4");
                    break;
                case R.id.btn_5:
                    sendString("5");
                    break;
                case R.id.btn_6:
                    sendString("6");
                    break;
                case R.id.btn_7:
                    sendString("7");
                    break;
                case R.id.btn_8:
                    sendString("8");
                    break;
                case R.id.btn_9:
                    sendString("9");
                    break;
                case R.id.btn_a:
                    sendString("A");
                    break;
                case R.id.btn_b:
                    sendString("B");
                    break;
                case R.id.btn_c:
                    sendString("C");
                    break;
                case R.id.btn_d:
                    sendString("D");
                    break;
                case R.id.btn_e:
                    sendString("E");
                    break;
                case R.id.btn_f:
                    sendString("F");
                    break;
                case R.id.btn_g:
                    sendString("G");
                    break;
                case R.id.btn_h:
                    sendString("H");
                    break;
                case R.id.btn_i:
                    sendString("I");
                    break;
                case R.id.btn_j:
                    sendString("J");
                    break;
                case R.id.btn_k:
                    sendString("K");
                    break;
                case R.id.btn_l:
                    sendString("L");
                    break;
                case R.id.btn_m:
                    sendString("M");
                    break;
                case R.id.btn_n:
                    sendString("N");
                    break;
                case R.id.btn_o:
                    sendString("O");
                    break;
                case R.id.btn_p:
                    sendString("P");
                    break;
                case R.id.btn_q:
                    sendString("Q");
                    break;
                case R.id.btn_r:
                    sendString("R");
                    break;
                case R.id.btn_s:
                    sendString("S");
                    break;
                case R.id.btn_t:
                    sendString("T");
                    break;
                case R.id.btn_u:
                    sendString("U");
                    break;
                case R.id.btn_v:
                    sendString("V");
                    break;
                case R.id.btn_w:
                    sendString("W");
                    break;
                case R.id.btn_x:
                    sendString("X");
                    break;
                case R.id.btn_y:
                    sendString("Y");
                    break;
                case R.id.btn_z:
                    sendString("Z");
                    break;
                case R.id.btn_percentage:
                    sendString("%");
                    break;
                case R.id.btn_comma:
                    sendString(",");
                    break;
                case R.id.btn_dot:
                    sendString(".");
                    break;
                case R.id.btn_hyfen:
                    sendString("-");
                    break;
                case R.id.btn_comma_normal:
                    sendString(",");
                    break;
                case R.id.btn_dot_normal:
                    sendString(".");
                    break;
                case R.id.btn_hyfen_normal:
                    sendString("-");
                    break;
                case R.id.btn_and_normal:
                    sendString("&");
                    break;
                case R.id.btn_hide:
                    mClickKeyboard.onHideKeyboard();
                    break;
                case R.id.btn_hide_sym:
                    mClickKeyboard.onHideKeyboard();
                    break;
                case R.id.btn_slash:
                    sendString("/");
                    break;
                case R.id.btn_at:
                    sendString("@");
                    break;
                case R.id.btn_hash:
                    sendString("#");
                    break;
                case R.id.btn_and:
                    sendString("&");
                    break;
                case R.id.btn_star:
                    sendString("*");
                    break;
                case R.id.btn_apo:
                    sendString("'");
                    break;
                case R.id.btn_space:
                    sendString(" ");
                    break;
                case R.id.btn_enter:
                    sendString("enter");
                    break;
                case R.id.btn_del:
                    sendString("delete");
                    break;
                case R.id.btn_clear:
                    sendString("backspace");
                    break;
                case R.id.rl_clear:
                    sendString("backspace");
                    break;
                case R.id.rl_clear_custom:
                    sendString("backspace");
                    break;
                case R.id.btn_clear_custom:
                    sendString("backspace");
                    break;
                case R.id.btn_enter_custom:
                    sendString("enter");
                    break;
                case R.id.btn_space_custom:
                    sendString(" ");
                    break;
                case R.id.btn_toggle_normal:
                    ll_custom_keyboard.setVisibility(View.GONE);
                    ll_normal_keyboard.setVisibility(View.VISIBLE);
                    break;
                case R.id.btn_toggle_special:
                    ll_custom_keyboard.setVisibility(View.VISIBLE);
                    ll_normal_keyboard.setVisibility(View.GONE);
                    break;
                case R.id.btn_toggle_skin:
                    changeSkin();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeSkin() {
        if (ll_normal_keyboard.getTag().toString().equals("1")) {
            ll_normal_keyboard.setTag("0");
            ll_normal_keyboard.setBackgroundResource(R.drawable.kb_skin_transparent);
            ll_custom_keyboard.setBackgroundResource(R.drawable.kb_skin_transparent);
        } else {
            ll_normal_keyboard.setTag("1");
            ll_normal_keyboard.setBackgroundResource(R.drawable.keyboard_outer_bg);
            ll_custom_keyboard.setBackgroundResource(R.drawable.keyboard_outer_bg);
        }
    }

    public interface OnClickKeyboard {
        void getKeyboardText(EditText editTextFocus, String str, int curPostion);

        void onEnter(EditText editTextFocus, String str);

        void onHideKeyboard();
    }
}
