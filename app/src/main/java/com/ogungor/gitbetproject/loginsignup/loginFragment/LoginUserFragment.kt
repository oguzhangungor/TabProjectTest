package com.ogungor.gitbetproject.loginsignup.loginFragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.ogungor.gitbetproject.R
import com.ogungor.gitbetproject.loginsignup.loginFragment.forgetpassword.ForgetPasswordFragment
import com.ogungor.gitbetproject.util.extentions.launchMainToFeedActivity
import com.ogungor.gitbetproject.util.extentions.showShortToast

class LoginUserFragment : Fragment(), LoginUserFragmentContract.View {

    private lateinit var loginUserFragmentPresenter: LoginUserFragmentContract.Presenter
    private lateinit var email: EditText
    private lateinit var forgetPass: TextView
    private lateinit var password: EditText
    private lateinit var rememberCheckBox: CheckBox
    private  var sharedPref:SharedPreferences?=null
    private var loginUserButton: ImageButton? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loginUserFragmentPresenter = LoginUserFragmentPresenter().apply {
            setView(this@LoginUserFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_user_layout, container, false)
        sharedPref=activity?.getSharedPreferences(R.string.preference_file_key.toString(),Context.MODE_PRIVATE)
        email = view.findViewById(R.id.login_email_editText)!!
        forgetPass = view.findViewById(R.id.forget_password_textview)
        password = view.findViewById(R.id.login_password_editText)
        loginUserButton = view.findViewById(R.id.login_user_button)
        rememberCheckBox=view.findViewById(R.id.rememberCheckBox)
        loginUserFragmentPresenter.createView()
        return view
    }

    override fun initUi() {
        initClickListener()
    }

    override fun initClickListener() {
        loginUserButton?.setOnClickListener {
            loginUserFragmentPresenter.apply {
                var boolean=checkBoxControl(rememberCheckBox)
            loginUserListener(
                email.text.toString(),
                password.text.toString(),
                boolean,
                sharedPref

            )
        }}
        forgetPass.setOnClickListener{
            var fm=fragmentManager
            var fragment=ForgetPasswordFragment()
            if (fm != null) {
                fragment.show(fm,"dede")
                fragment.dialog
            }
        }
    }

    override fun showCreateUserSuccessfulMessage() {
        activity?.showShortToast(getString(R.string.login_complete))
    }

    override fun showEmptyAreaMessage() {
        activity?.showShortToast(getString(R.string.empty_area))
    }

    override fun intentToMainsActivity() {
        activity?.run {
            launchMainToFeedActivity()
            finish()
        }
    }

    override fun showInvalidPasswordMessage() {
        activity?.showShortToast(getString(R.string.login_password_error_message))
    }

    override fun showInvalidEmailMessage() {
        activity?.showShortToast(getString(R.string.email_error_message))
    }

    override fun showLoginUserFailureMessage() {
        activity?.showShortToast(getString(R.string.email_not_registered))
    }
}