package ahmed.dev.com.coding_challenge;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {


    EditText eTemail , eTpassword;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        eTemail = (EditText) findViewById(R.id.signinemail);
        eTpassword = (EditText) findViewById(R.id.signinpassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signin).setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signin:
                SignIn();
                break;

            case R.id.register:
                startActivity(new Intent(this,SignUpActivity.class));
                break;
        }
    }

    private void SignIn() {
        String email = eTemail.getText().toString().trim();
        String password = eTpassword.getText().toString().trim();


        if (email.isEmpty()){
            eTemail.setError("Email is required");
            eTemail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            eTemail.setError("Please enter a valid email");
            eTemail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            eTpassword.setError("Password is required");
            eTpassword.requestFocus();
            return;
        }

        if (password.length()<6){
            eTpassword.setError("Min pass length is 6 char ");
            eTpassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent =  new Intent(SignInActivity.this,MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
