package com.example.letimendes.projetomatricula;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.letimendes.projetomatricula.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    public static int activeLayout;
    public static List<Aluno> alunosInscritos; // status 0
    public static List<Aluno> alunosPendentes; // status 1
    public static List<Aluno> alunosAceitos; // status 2
    public static List<Aluno> alunosRejeitados; // status 3
    public static List<Aluno> alunosMatriculados; // status 4

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alunosInscritos = new ArrayList<>();
        alunosPendentes = new ArrayList<>();
        alunosAceitos = new ArrayList<>();
        alunosRejeitados = new ArrayList<>();
        alunosMatriculados = new ArrayList<>();
        inflateFragment(new LoginFragment());
    }

    public void inflateFragment(Fragment fragment){
        activeLayout = 1;
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if(activeLayout == 1){
            LinearLayout layoutInicio = findViewById(R.id.layoutSelecionar);
            LinearLayout layoutLogin = findViewById(R.id.layoutLogin);
            LinearLayout layoutMatricula = findViewById(R.id.layoutMatricula);

            layoutInicio.setVisibility(View.VISIBLE);
            layoutLogin.setVisibility(View.GONE);
            layoutMatricula.setVisibility(View.GONE);
        }else{
            super.onBackPressed();
        }
    }
}
