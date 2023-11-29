package com.example.af_mobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.af_mobile.R;
import com.example.af_mobile.config.ConfigBD;
import com.example.af_mobile.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginMainActivity extends AppCompatActivity {

    Usuario usuario;
    EditText campoEmail, campoSenha;
    Button btEntrar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = ConfigBD.autenticacao();
        init();
    }

    private void init(){
        campoEmail = findViewById(R.id.editTextEmailEntrar);
        campoSenha = findViewById(R.id.editTextSenhaEntrar);
        btEntrar = findViewById(R.id.btEntrar);
    }
    public void Cadastrar(View v){
        Intent i = new Intent(this,CadastrarActivity.class);
        startActivity(i);
    }

    public void validarCampos(View view){
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if (!email.isEmpty()){

            if (!senha.isEmpty()){
            Usuario usuario = new Usuario();

            usuario.setEmail(email);
            usuario.setSenha(senha);
            
            logar(usuario);

            }else{
                Toast.makeText(this,"Preencha a Senha",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"Preencha o Email",Toast.LENGTH_SHORT).show();
        }
    }

    private void logar(Usuario usuario) {

        auth.signInWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()

        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                        OpenHome();
                }else{
                    String exception;

                    try {
                        throw task.getException();
                    }catch (FirebaseAuthInvalidUserException e){
                        exception = "Usuario não existe.";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        exception = "Email ou Senha incorreto.";
                    }catch (Exception e){
                        exception = "Error ao efetuar login."+ e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginMainActivity.this, exception, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void OpenHome(){
        Intent h = new Intent(LoginMainActivity.this, VerSeriesActivity.class);
        startActivity(h);
    }

}