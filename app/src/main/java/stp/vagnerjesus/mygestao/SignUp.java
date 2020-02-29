package stp.vagnerjesus.mygestao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    //Variables
    TextInputLayout regName, regUsername,
            regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLoginBtn;


    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //activity_sign_up.xml
        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);
        //onCreate Method End
    }

    //button para ja tenho uma cota
    public void jatenho_conta(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    //// Esta função será executada quando o usuário clicar no botão Register
    public void registerUser(View view){

        if(!validateName() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUsername()){
            return;
        }
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("user");

        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String phoneNo = regPhoneNo.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();

        UserHelperClass helperClass = new UserHelperClass(name,username,email,phoneNo,password);

        reference.child(username).setValue(helperClass);
    }

    ///Validacoes
    private Boolean validateName() {
        String val = regName.getEditText().getText().toString();

        if (val.isEmpty()) {
            regName.setError("O campo é obrigatório");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";//para n ter espaços

        if (val.isEmpty()) {
            regUsername.setError("O campo é obrigatório");
            return false;
        } else if (val.length() >= 15) {
            regUsername.setError("Nome de usuário muito longo");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regUsername.setError("Espaços em branco não são permitidos");
            return false;
        } else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            regEmail.setError("O campo é obrigatório");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Endereço de email invalido");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePhoneNo() {
        String val = regPhoneNo.getEditText().getText().toString();

        if (val.isEmpty()) {
            regPhoneNo.setError("O campo é obrigatório");
            return false;
        } else {
            regPhoneNo.setError(null);
            regPhoneNo.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //pelo menos 1 dígito
                //"(?=.*[a-z])" +         //pelo menos 1 letra minúscula
                //"(?=.*[A-Z])" +         //pelo menos 1 letra maiúscula
                "(?=.*[a-zA-Z])" +      //qualquer carta
                "(?=.*[@#$%^&+=])" +    //pelo menos 1 caractere especial
                "(?=\\S+$)" +           //sem espaços em branco
                ".{4,}" +               //pelo menos 4 caracteres
                "$";

        if (val.isEmpty()) {
            regPassword.setError("O campo é obrigatório");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("A senha é muito fraca");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }
}
