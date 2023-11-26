package com.example.af_mobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastrarActivity extends AppCompatActivity {


    Usuario usuario;
    FirebaseAuth autenticar;
    EditText campoEmail, campoSenha;
    Button btCadastrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        init();
    }

    private void init(){
    campoEmail = findViewById(R.id.editTextEmailCadastrar);
    campoSenha = findViewById(R.id.editTextSenhaCadastrar);
    btCadastrar = findViewById(R.id.btRegistrar);
    usuario = new Usuario();

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCampos();
            }
        });
    }

    public void validarCampos(){
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if (!email.isEmpty()){

            if (!senha.isEmpty()){

                usuario.setEmail(email);
                usuario.setSenha(senha);

                cadastrarUsuario(email, senha);

            }else{
                Toast.makeText(this,"Preencha a Senha",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"Preencha o Email",Toast.LENGTH_SHORT).show();
        }
    }

    private void cadastrarUsuario(String email, String senha){

        usuario = new Usuario(email, senha);
        autenticar = ConfigBD.autenticacao();

        autenticar.createUserWithEmailAndPassword(
                usuario.getEmail(),usuario.getSenha()

        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(CadastrarActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                }else{
                    String exception = "";

                    try{

                        throw task.getException();

                    }catch (FirebaseAuthWeakPasswordException e){
                        exception = "Digite uma senha mais forte.";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        exception = "Senha ou Email invalido.";
                    }catch (FirebaseAuthUserCollisionException e){
                        exception = "Este Email já esta cadastrado.";
                    }catch (Exception e){
                        exception = "Erro ao cadastrar"+ e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastrarActivity.this, exception, Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


}