package stp.vagnerjesus.mygestao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {

    Button callSignUp, login_btn;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout username, password;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //All elements Hooks
         progressBar = findViewById(R.id.progressBar);
         progressBar.setVisibility(View.GONE);
        // callSignUp = findViewById(R.id.signup_screen);
        image = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        //sloganText = findViewById(R.id.slogan_name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login);
    }


    //Button de ir para criar novo usuario
    public void createnovo(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    public void loginin(View view) {
        if (!validateUsername1() | !validatePassword1()) {
        } else {
            isUser();
        }

    }

    private Boolean validateUsername1() {
        String val = username.getEditText().getText().toString();
        if (val.isEmpty()) {
            username.setError("O campo é obrigatório");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword1() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("O campo é obrigatório");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }


    private void isUser() {
        progressBar.setVisibility(View.VISIBLE);
        final String userEnteredUsername = username.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    username.setError(null);
                    username.setErrorEnabled(false);
                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userEnteredPassword)) {
                        username.setError(null);
                        username.setErrorEnabled(false);
                        String nameFromDB = dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String phoneNoFromDB = dataSnapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        Intent intent = new Intent(getApplicationContext(), UserPerfil.class);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("phoneNo", phoneNoFromDB);
                        intent.putExtra("password", passwordFromDB);
                        startActivity(intent);
                    } else {
                       // progressBar.setVisibility(View.GONE);
                        password.setError("Senha Incorreta");
                        password.requestFocus();
                    }
                } else {
                   // progressBar.setVisibility(View.GONE);
                    username.setError("Esse usuário não existe");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}




