 package ahmed.dev.com.coding_challenge;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

 public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{



     EditText eTemail , eTpassword;
     private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        eTemail = (EditText) findViewById(R.id.signupemail);
        eTpassword = (EditText) findViewById(R.id.signuppassword);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signup).setOnClickListener(this);
    }

     @Override
     public void onClick(View v) {
        if  (v.getId()==R.id.signup) {
            registerUser();
        }
     }

     private void registerUser() {
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

         mAuth.createUserWithEmailAndPassword(email,password)
                 .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()){
                             Toast.makeText(getApplicationContext(),"User registred successfully",Toast.LENGTH_SHORT).show();
                         } else {
                             if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                 Toast.makeText(getApplicationContext(),"User already registred ",Toast.LENGTH_SHORT).show();
                             } else {
                                 Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                             }
                         }
                     }
                 });
     }
 }
