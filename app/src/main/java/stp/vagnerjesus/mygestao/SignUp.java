package stp.vagnerjesus.mygestao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    //button para ja tenho uma cota
    public void jatenho_conta(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
