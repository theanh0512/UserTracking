package pham.honestbee.usertracking.ui.login

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import pham.honestbee.usertracking.R
import pham.honestbee.usertracking.databinding.ActivityLoginBinding
import pham.honestbee.usertracking.ui.MainActivity
import java.util.regex.Pattern

/**
 * Created by Pham on 18/3/2019
 */

class LoginActivity : AppCompatActivity() {

    var binding: ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    fun validateAndLogin(view: View) {

        val email = binding?.username?.text.toString()
        if (!isValidEmail(email)) {
            //Set error message for email field
            binding?.username?.error = getString(R.string.email_validation_error)
        }

        val pass = binding?.password?.text.toString()
        if (!isValidPassword(pass)) {
            //Set error message for password field
            binding?.password?.error = getString(R.string.password_validation_error)
        }

        if (isValidEmail(email) && isValidPassword(pass)) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    // validating email id
    private fun isValidEmail(email: String): Boolean {
        val pattern = Pattern.compile(EMAIL_PATTERN)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    // validating password
    private fun isValidPassword(pass: String?): Boolean {
        return pass != null && pass.length >= 6
    }

    companion object {
        const val EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    }
}
