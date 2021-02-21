package com.ogungor.gitbetproject.loginsignup.loginFragment.forgetpassword

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.ogungor.gitbetproject.R
import com.ogungor.gitbetproject.util.extentions.showShortToast

class ForgetPassword : DialogFragment(), ForgetPasswordContract.View {
    private lateinit var forgetPasswordPresenter: ForgetPasswordContract.Presenter
    private lateinit var userMailAddressEditText: EditText
    private lateinit var sendMailAddressButton: Button

    override fun onAttach(context: Context) {
        super.onAttach(context)
        forgetPasswordPresenter = ForgetPasswordPresenter().apply {
            setView(this@ForgetPassword)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.forgetpass_layout, container, false)
        userMailAddressEditText = view.findViewById(R.id.mail_address_text_forget)
        sendMailAddressButton = view.findViewById(R.id.confirm_button_forget)
        forgetPasswordPresenter.create()
        return view
    }

    override fun onResume() {
        super.onResume()
    }

    override fun getTheme(): Int= android.R.style.Theme_Material_Light_Dialog_Alert

    override fun initUi() {
        sendInitClickListener()
    }

    override fun sendInitClickListener() {
        sendMailAddressButton.setOnClickListener {
            forgetPasswordPresenter.sendPasswordToMail(userMailAddressEditText.text.toString())
        }
    }

    override fun showSendSuccessMessage() {
        activity?.showShortToast(getString(R.string.send_mail_complete))
    }

    override fun showSendFailedMessage() {
        activity?.showShortToast(getString(R.string.send_mail_failed))
    }
}