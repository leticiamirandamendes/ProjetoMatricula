package com.example.letimendes.projetomatricula;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.letimendes.projetomatricula.model.Aluno;

import java.util.ArrayList;

import static com.example.letimendes.projetomatricula.MainActivity.alunosInscritos;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private LinearLayout layoutInicio;
    private LinearLayout layoutLogin;
    private LinearLayout layoutMatricula;
    private Button solicitarMatricula;
    private Button doLogin;
    private Spinner disciplinas;
    private CardView matricula;
    private CardView login;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutInicio = getActivity().findViewById(R.id.layoutSelecionar);
        layoutLogin = getActivity().findViewById(R.id.layoutLogin);
        layoutMatricula = getActivity().findViewById(R.id.layoutMatricula);
        matricula = getActivity().findViewById(R.id.matricula);
        login = getActivity().findViewById(R.id.login);
        solicitarMatricula = getActivity().findViewById(R.id.btnMatricula);
        doLogin = getActivity().findViewById(R.id.btnLogin);
        disciplinas = getActivity().findViewById(R.id.codDisciplina);

        matricula.setOnClickListener(this);
        login.setOnClickListener(this);
        doLogin.setOnClickListener(this);
        solicitarMatricula.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.matricula: {
                String spinnerArray[] = {"CAES001 - Metodologias Ágeis para o Desenvolvimento de Software"};
                ArrayAdapter<String> spinnerAdapter =
                        new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                disciplinas.setAdapter(spinnerAdapter);
                layoutInicio.setVisibility(View.GONE);
                layoutMatricula.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.login: {
                layoutInicio.setVisibility(View.GONE);
                layoutLogin.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.btnLogin: {
                realizarLogin();
                break;
            }
            case R.id.btnMatricula: {
                realizarCadastro();
                break;
            }
        }
    }

    private void realizarCadastro(){
        EditText nome = getActivity().findViewById(R.id.nomeAluno);
        EditText email = getActivity().findViewById(R.id.emailAluno);
        EditText cpf = getActivity().findViewById(R.id.cpfAluno);
        Aluno aluno = new Aluno();
        aluno.setNome(nome.getText().toString());
        aluno.setCpf(cpf.getText().toString());
        aluno.setEmail(email.getText().toString());
        aluno.setCodDisciplina("CAES001");
        aluno.setStatus(0);
        if(alunosInscritos == null) alunosInscritos = new ArrayList<>();
        alunosInscritos.add(aluno);
        Toast.makeText(getActivity(), "Solicitação de matrícula efetuada.", Toast.LENGTH_SHORT).show();
    }

    private void realizarLogin(){
        EditText nome = getActivity().findViewById(R.id.nome);
        EditText codigo = getActivity().findViewById(R.id.codigo);

        if(nome.getText().toString().equalsIgnoreCase("professor") &&
                codigo.getText().toString().equalsIgnoreCase("123")){
            getActivity().getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new ProfessorFragment()).commit();
        }else if(nome.getText().toString().equalsIgnoreCase("coordenador") &&
                codigo.getText().toString().equalsIgnoreCase("456")){
            getActivity().getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new CoordenadorFragment()).commit();
        }else{
            Toast.makeText(getActivity(), "Usuário inválido. Verifique os dados inseridos e tente novamente", Toast.LENGTH_SHORT).show();
        }
    }


}
