package stp.vagnerjesus.mygestao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;

public class UserPerfil extends AppCompatActivity {

    TextInputLayout fullName , email , phoneNo , senha ;
    TextView fullNameLabel , usernameLabel ;

    // Variáveis globais para armazenar dados do usuário dentro desta atividade
    String _USERNAME , _NAME , _EMAIL , _PHONENO , _PASSWORD ;

    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        reference = FirebaseDatabase. getInstance () .getReference ( "user" );

        setContentView(R.layout.activity_user_perfil);

        // Ganchos
        fullName = findViewById (R.id.full_name_profile);
        email = findViewById (R.id.email_profile);
        phoneNo = findViewById (R.id.phone_no_profile );
        senha = findViewById (R.id.password_profile );
        fullNameLabel = findViewById (R.id.fullname_field );
        usernameLabel = findViewById (R.id.username_field );
        // ShowAllData
        showAllUserData ();

    }
    private void showAllUserData() {

        Intent intent = getIntent();
        _USERNAME = intent.getStringExtra("username");
        _NAME = intent.getStringExtra("name");
        _EMAIL = intent.getStringExtra("email");
        _PHONENO = intent.getStringExtra("phoneNo");
        _PASSWORD = intent.getStringExtra("password");

        fullNameLabel.setText(_NAME);
        usernameLabel.setText(_USERNAME);
        fullName.getEditText().setText(_NAME);
        email.getEditText().setText(_EMAIL);
        phoneNo.getEditText().setText(_PHONENO);
        senha.getEditText().setText(_PASSWORD);
    }



    public void atualizar(View view) {

        if (isNameChanged() || isPasswordChanged()) {
            Toast.makeText(this, "Os dados foram atualizados", Toast.LENGTH_LONG).show();

        }
        else Toast.makeText(this, "Os dados são iguais, e não podem ser atualizados", Toast.LENGTH_LONG).show();

    }

    private boolean isPasswordChanged() {
        if (!_PASSWORD.equals(senha.getEditText().getText().toString())) {
            reference.child(_USERNAME).child("password").setValue(senha.getEditText().getText().toString());
            _PASSWORD = senha.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isNameChanged() {

        if (!_NAME.equals(fullName.getEditText().getText().toString())) {
            reference.child(_USERNAME).child("name").setValue(fullName.getEditText().getText().toString());
            _NAME = fullName.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }

    }

}

