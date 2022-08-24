package com.apollo.pharmacy.ocr.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.InputType
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.view.animation.TranslateAnimation
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.apollo.pharmacy.ocr.R
import com.apollo.pharmacy.ocr.controller.UserLoginController
import com.apollo.pharmacy.ocr.interfaces.UserLoginListener
import com.apollo.pharmacy.ocr.model.Send_Sms_Request
import com.apollo.pharmacy.ocr.receiver.ConnectivityReceiver
import com.apollo.pharmacy.ocr.utility.*
import com.apollo.pharmacy.ocr.widget.CustomKeyboard
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_user_login.*
import kotlinx.android.synthetic.main.view_faq_layout.*

class UserLoginActivity : AppCompatActivity(), UserLoginListener, ConnectivityReceiver.ConnectivityReceiverListener {

    val keyboard = null

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (isConnected) {
            findViewById<View>(R.id.networkErrorLayout).visibility = View.GONE
        } else {
            findViewById<View>(R.id.networkErrorLayout).visibility = View.VISIBLE
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onSendSmsSuccess() {
        Utils.dismissDialog()
        mobile_number_input_layout.visibility = View.GONE
        otp_parent_layout.visibility = View.VISIBLE
        entered_mobile_number.setText(mobileNum)
        SessionManager.setMobilenumber(mobileNum)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        setOtpNumberKeyboard()
    }

    override fun onSendSmsFailure() {
        Utils.dismissDialog()
        Utils.showCustomAlertDialog(this, resources.getString(R.string.label_server_err_message), false, resources.getString(R.string.label_ok), "")
    }

    lateinit var utils: Utils
    lateinit var userLoginController: UserLoginController
    var mobileNum: String = ""
    var otp: Int = 0
    var oldMobileNum: String = ""
    lateinit var countDownTimer: CountDownTimer
    lateinit var constraint_layout: ConstraintLayout;

    override fun onResume() {
        super.onResume()
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        Constants.getInstance().setConnectivityListener(this)
        if (!ConnectivityReceiver.isConnected()) {
            findViewById<View>(R.id.networkErrorLayout).visibility = View.VISIBLE
        }

        if (!NetworkUtils.isNetworkConnected(this@UserLoginActivity)) {
            findViewById<View>(R.id.networkErrorLayout).visibility = View.VISIBLE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);

        utils = Utils()
        userLoginController = UserLoginController()
        SessionManager.setBatchId("");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        edittext_mobileNum.setRawInputType(InputType.TYPE_CLASS_TEXT)
        edittext_mobileNum.setTextIsSelectable(true)

        setMobileNumberKeyboard()
val faq_layout=findViewById<ImageView>(R.id.faq);
        val customerCareImg = findViewById<ImageView>(R.id.customer_care_icon)
        val customerHelpLayout = findViewById<LinearLayout>(R.id.customer_help_layout)
        constraint_layout = findViewById(R.id.constraint_layout)
        customerHelpLayout.visibility = View.VISIBLE
        customerCareImg.setOnClickListener { v ->
            if (customerHelpLayout.visibility == View.VISIBLE) {
                customerCareImg.setBackgroundResource(R.drawable.icon_help_circle)
                val animate = TranslateAnimation(0f, customerHelpLayout.width.toFloat(), 0f, 0f)
                animate.duration = 2000
                animate.fillAfter = true
                customerHelpLayout.startAnimation(animate)
                customerHelpLayout.visibility = View.GONE
            } else {
                customerCareImg.setBackgroundResource(R.drawable.icon_help_circle)
                val animate = TranslateAnimation(customerHelpLayout.width.toFloat(), 0f, 0f, 0f)
                animate.duration = 2000
                animate.fillAfter = true
                customerHelpLayout.startAnimation(animate)
                customerHelpLayout.visibility = View.VISIBLE
            }
        }

        val custCareImg = findViewById<ImageView>(R.id.cust_care_icon)
        val custHelpLayout = findViewById<LinearLayout>(R.id.cust_help_layout)
        custHelpLayout.visibility = View.VISIBLE
        custCareImg.setOnClickListener { v ->
            if (custHelpLayout.visibility == View.VISIBLE) {
                custCareImg.setBackgroundResource(R.drawable.icon_help_circle)
                val animate = TranslateAnimation(0f, custHelpLayout.width.toFloat(), 0f, 0f)
                animate.duration = 2000
                animate.fillAfter = true
                custHelpLayout.startAnimation(animate)
                custHelpLayout.visibility = View.GONE
            } else {
                custCareImg.setBackgroundResource(R.drawable.icon_help_circle)
                val animate = TranslateAnimation(custHelpLayout.width.toFloat(), 0f, 0f, 0f)
                animate.duration = 2000
                animate.fillAfter = true
                custHelpLayout.startAnimation(animate)
                custHelpLayout.visibility = View.VISIBLE
            }
        }

        otp_view.setRawInputType(InputType.TYPE_CLASS_TEXT)
        otp_view.setTextIsSelectable(true)

        //Temporary Code
//        SessionManager.setMobilenumber("9440012212")
//        startActivity(Intent(applicationContext, HomeActivity::class.java))
//        finish()
//        this.overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out)

        back_icon.setOnClickListener {
            startActivity(Intent(this@UserLoginActivity, MainActivity::class.java))
            finish()
            overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out)
        }

        faq_layout.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@UserLoginActivity, FAQActivity::class.java))
        })
        back_otp_icon.setOnClickListener {
            otp_parent_layout.visibility = View.GONE
            mobile_number_input_layout.visibility = View.VISIBLE
            setMobileNumberKeyboard()
        }
        generate_otp_layout.setOnClickListener(View.OnClickListener {
            val MobilePattern = "[0-9]{10}"
            mobileNum = edittext_mobileNum.text.toString()
            if (mobileNum.length < 10) {
                send_otp_image.setImageResource(R.drawable.right_selection_green)
                edittext_error_layout.setBackgroundResource(R.drawable.phone_error_alert_bg)
                edittext_error_text.visibility = View.VISIBLE
                return@OnClickListener
            } else {
                send_otp_image.setImageResource(R.drawable.right_selection_green)
                edittext_error_layout.setBackgroundResource(R.drawable.phone_country_code_bg)
                edittext_error_text.visibility = View.INVISIBLE
                if (oldMobileNum.equals(edittext_mobileNum.text.toString()) && edittext_mobileNum.text.toString().length > 0 && (mobileNum.matches(MobilePattern.toRegex()))) {
                    mobile_number_input_layout.visibility = View.GONE
                    otp_parent_layout.visibility = View.VISIBLE
                } else {
                    if (edittext_mobileNum.text.toString().length > 0) {
                        oldMobileNum = edittext_mobileNum.text.toString()
                        if (mobileNum.matches(MobilePattern.toRegex())) {
                            Utils.showDialog(this, resources.getString(R.string.label_sending_otp))
                            otp = (Math.random() * 9000).toInt() + 1000
                            if (NetworkUtils.isNetworkConnected(applicationContext)) {
                                val sms_req = Send_Sms_Request()
                                sms_req.mobileNo = mobileNum
                                sms_req.message = "Dear Apollo Customer, Your one time password is " + otp.toString()+" and is valid for 3mins."
                                sms_req.isOtp = true
                                sms_req.otp = otp.toString()
                                sms_req.apiType = "KIOSk"
                                userLoginController.handleSendSmsApi(sms_req, this)
                            } else {
                                Utils.showSnackbar(applicationContext, constraint_layout, resources.getString(R.string.label_internet_error_text));
                            }
                        }
                    }
                }
            }
        })

        verify_otp_layout.setOnClickListener(View.OnClickListener {
            if (!TextUtils.isEmpty(otp_view.text.toString()) && otp_view.text.toString().length > 0) {
                if (otp == otp_view.text.toString().toInt()) {
                    verify_otp_image.setImageResource(R.drawable.right_selection_green)
                    SessionManager.setMobilenumber(mobileNum)
                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                    finishAffinity()
                    this.overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out)
                } else {
                    verify_otp_image.setImageResource(R.drawable.right_selection_green)
                    Utils.showSnackbar(this@UserLoginActivity, constraint_layout, applicationContext.resources.getString(R.string.label_invalid_otp_try_again))
                }
            } else {
                Utils.showSnackbar(this@UserLoginActivity, constraint_layout, applicationContext.resources.getString(R.string.label_invalid_otp_try_again))
            }
        })

        resend_otp_layout.setOnClickListener(View.OnClickListener {
            if (NetworkUtils.isNetworkConnected(applicationContext)) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
                resend_button.setTextColor(resources.getColor(R.color.hash_color))

                val sms_req = Send_Sms_Request()
                sms_req.mobileNo = mobileNum
                sms_req.message = "Your OTP is " + otp.toString()
                sms_req.isOtp = true
                sms_req.otp = otp.toString()
                sms_req.apiType = "KIOSk"
                userLoginController.handleSendSmsApi(sms_req, this)
                countDownTimer = object : CountDownTimer(5000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                    }

                    override fun onFinish() {
                        resend_button.setTextColor(Color.parseColor("#000000"))
                    }
                }.start()
            } else {
                Utils.showSnackbar(applicationContext, constraint_layout, resources.getString(R.string.label_internet_error_text));
            }
        })

        change_number_layout.setOnClickListener(View.OnClickListener {
            otp_view.setText("")
            otp_parent_layout.visibility = View.GONE
            mobile_number_input_layout.visibility = View.VISIBLE
            edittext_mobileNum.text?.clear()
            setMobileNumberKeyboard();
        })

        ok_btn_layout.setOnClickListener {
            startActivity(Intent(applicationContext, HomeActivity::class.java))
            finish()
            this.overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@UserLoginActivity, MainActivity::class.java))
        finish()
        overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out)
    }

    fun setMobileNumberKeyboard() {
        val ic = edittext_mobileNum.onCreateInputConnection(EditorInfo())
        val keyboard = CustomKeyboard(applicationContext)
        keyboard.setInputConnection(ic)
        if (keyboard != null) {
            keyboard_wrapper_layout.removeAllViews()
            keyboard_wrapper_layout.addView(keyboard)
        }
    }

    fun setOtpNumberKeyboard() {
        val ic = otp_view.onCreateInputConnection(EditorInfo())
        val keyboard = CustomKeyboard(applicationContext)
        keyboard.setInputConnection(ic)
        if (keyboard != null) {
            otp_keyboard_wrapper_layout.removeAllViews()
            otp_keyboard_wrapper_layout.addView(keyboard)
        }
    }
}