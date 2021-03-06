package com.ogungor.gitbetproject.feed.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.ogungor.gitbetproject.R
import com.ogungor.gitbetproject.feed.FeedActivity
import com.ogungor.gitbetproject.feed.FeedActivityContract
import com.ogungor.gitbetproject.util.extentions.launchLogOutToMainActivity
import com.ogungor.gitbetproject.util.extentions.launchProfileToChgPasswordActivity

class ProfileFragment : Fragment(), ProfileFragmentContract.View {
    private lateinit var profileFragmentPresenter: ProfileFragmentContract.Presenter
    private var mail_adress: TextView? = null
    private lateinit var change_password: AppCompatButton
    private lateinit var log_out: AppCompatButton
    private lateinit var feedFragmentView: FeedActivityContract.View

    override fun onAttach(context: Context) {
        super.onAttach(context)
        feedFragmentView=FeedActivity()
        profileFragmentPresenter = ProfileFragmentPresenter().apply {
            setView(this@ProfileFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        mail_adress = view.findViewById(R.id.profile_mail_adress_textView)
        change_password = view.findViewById(R.id.profile_change_password_button)
        log_out = view.findViewById(R.id.profile_logut_button)

        profileFragmentPresenter.apply {
            create()
            getUserMailAddress()
        }
        return view
    }

    override fun initUi() {
        initClickListeners()
    }

    override fun setUserMailAddress(userMailAddress: String) {
        mail_adress?.text = userMailAddress
    }

    override fun initClickListeners() {
        log_out?.setOnClickListener {
            profileFragmentPresenter.logOutUser()
        }
        change_password.setOnClickListener{
           profileFragmentPresenter.setChangePassFragmentViePage()
        }
    }

    override fun intentLogOutToMainActivity() {
        activity?.run {
            launchLogOutToMainActivity()
            finish()
        }
    }

    override fun passwordChangeInit(){
        activity?.run {
            launchProfileToChgPasswordActivity()
        }
    }
}