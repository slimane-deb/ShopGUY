package sd.shopguy.Main;

/**
 * Created by Slimane on 26/03/2016.
 */

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import sd.shopguy.Metier.Client;

public class LoginActivity extends AppCompatActivity {

    //private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    public static Client client;

    // utiliser the Butter Knife library to Inject view annotation
    @InjectView(R.id.input_email)
    EditText _emailText;
    @InjectView(R.id.input_password)
    EditText _passwordText;
    @InjectView(R.id.btn_login)
    Button _loginButton;
    @InjectView(R.id.link_signup)
    TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // pour l'inscription
                //Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                //startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        //Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        // format valide
        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authentication...");
        progressDialog.show();

        // on verifie l'email et le mdp
        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // onLoginSuccess ou onLoginFailed
                        if(email.equals("sd.man99@gmail.com")&&password.equals("user")) {
                            client =new Client("SD","SD","Zelfana",email,password);
                            onLoginSuccess();
                        }
                        else  onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: code inscription avec succes
                // par default on finit just l'Activity et on passe
                this.finish();
            }
        }
    }
*/
  /*
    @Override
    public void onBackPressed() {
        // pour ne pas revenir the MainActivity
        moveTaskToBack(true);
    }
*/
    public void onLoginSuccess() {
        _loginButton.setEnabled(true) ;
        MainActivity.login=true ;
        Toast.makeText(getBaseContext(), "Vous êtes connecté", Toast.LENGTH_LONG).show();
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login echoué", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enterer un Email valide");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("entre 4 et 10 alphanumérique");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}