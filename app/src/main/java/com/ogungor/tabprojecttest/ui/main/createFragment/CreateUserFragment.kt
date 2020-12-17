package com.ogungor.tabprojecttest.ui.main.createFragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.ogungor.tabprojecttest.R
import com.ogungor.tabprojecttest.feed.FeedActivity
import com.ogungor.tabprojecttest.util.extentions.launchFeedsActivity
import com.ogungor.tabprojecttest.util.extentions.showLongToast

class CreateUserFragment : Fragment(), CreateUserFragmentContract.View {

    private lateinit var createUserFragmentPresenter: CreateUserFragmentContract.Presenter
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var passwordRepeat: EditText
    private var createUserButton: ImageButton? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        createUserFragmentPresenter = CreateUserFragmentPresenter().apply {
            setView(this@CreateUserFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.create_user_layout, container, false)
        email = view?.findViewById(R.id.create_mail_address_editText)!!
        passwordRepeat = view.findViewById(R.id.password_repeat_editText)!!
        password = view.findViewById(R.id.create_password_editText)!!
        createUserButton = view.findViewById(R.id.create_user_button)
        createUserFragmentPresenter.createView()
        return view
    }

    override fun initUi() {
        initClickListeners()
    }

    override fun initClickListeners() {
        createUserButton?.setOnClickListener {
            createUserFragmentPresenter.createUserClicked(
                email.text.toString(),
                password.text.toString(),
                passwordRepeat.text.toString()
            )
        }
    }

    override fun showPasswordNotEqualMessage() {
        activity?.showLongToast("Parolaları Aynı girin")
    }

    override fun showEmptyAreaMessage() {
        activity?.showLongToast("Boş Bırakmayınız.")
    }

    override fun showCreateUserFailureMessage(message: String?) {
        message?.let {
            activity?.showLongToast(it)
        }
    }

    override fun showCreateUserSuccessfullMessage() {
        activity?.showLongToast("Kayıt Başarılı")
    }

    override fun showInvalidEmailMessage() {
        activity?.showLongToast("Lütfen geçerli bir email giriniz.")
    }

    override fun showInvalidPasswordMessage() {
        activity?.showLongToast("Lütfen gdaha güçlü bir parola giriniz.")
    }

    override fun intentToFeedsActivity() {
        activity?.run {
            launchFeedsActivity()
            finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        createUserFragmentPresenter.destroy()
    }
}
