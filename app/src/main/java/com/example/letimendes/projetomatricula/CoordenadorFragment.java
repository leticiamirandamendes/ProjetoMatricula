package com.example.letimendes.projetomatricula;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.letimendes.projetomatricula.adapter.AlunoAdapter;
import com.example.letimendes.projetomatricula.model.Aluno;

import java.util.ArrayList;
import java.util.Iterator;

import static com.example.letimendes.projetomatricula.MainActivity.alunosAceitos;
import static com.example.letimendes.projetomatricula.MainActivity.alunosInscritos;
import static com.example.letimendes.projetomatricula.MainActivity.alunosMatriculados;
import static com.example.letimendes.projetomatricula.MainActivity.alunosPendentes;
import static com.example.letimendes.projetomatricula.MainActivity.alunosRejeitados;

public class CoordenadorFragment extends Fragment implements View.OnClickListener {

    private TextView tvSolicitadas;
    private TextView tvaAprovadas;
    private TextView tvRejeitadas;
    private Spinner spinnerDisciplinas;
    private RecyclerView rvMatriculas;
    private AlunoAdapter adapter;
    private FloatingActionButton fabCoordenador;
    private int status;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coordenador, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity.activeLayout = 2;

        spinnerDisciplinas = getActivity().findViewById(R.id.spinnerDisciplina);
        rvMatriculas = getActivity().findViewById(R.id.rvCoordenador);

        tvSolicitadas = getActivity().findViewById(R.id.tvMatriculasSolicitadas);
        tvaAprovadas = getActivity().findViewById(R.id.tvMatriculasAprovadas);
        tvRejeitadas = getActivity().findViewById(R.id.tvMatriculasRecusadas);
        fabCoordenador = getActivity().findViewById(R.id.fabCoordenador);

        tvSolicitadas.setOnClickListener(this);
        tvaAprovadas.setOnClickListener(this);
        tvRejeitadas.setOnClickListener(this);
        fabCoordenador.setOnClickListener(this);

        String spinnerArray[] = {"CAES001 - Metodologias Ágeis para o Desenvolvimento de Software"};
        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDisciplinas.setAdapter(spinnerAdapter);

        status = 0;
        setupRecyclerView(0);
    }

    private void setupRecyclerView(int id){
        if(id == 0){
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            rvMatriculas.setLayoutManager(layoutManager);
            adapter = new AlunoAdapter(getActivity(), 0);
            rvMatriculas.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else if(id == 2){
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            rvMatriculas.setLayoutManager(layoutManager);
            adapter = new AlunoAdapter(getActivity(), 2);
            rvMatriculas.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else{
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            rvMatriculas.setLayoutManager(layoutManager);
            adapter = new AlunoAdapter(getActivity(), 3);
            rvMatriculas.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvMatriculasSolicitadas: {
                setupRecyclerView(0);
                status = 0;
                tvSolicitadas.setTextColor(getResources().getColor(R.color.colorAccentDark));
                tvaAprovadas.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                tvRejeitadas.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                fabCoordenador.setImageDrawable(getResources().getDrawable(R.drawable.ic_send));
                break;
            }
            case R.id.tvMatriculasAprovadas: {
                setupRecyclerView(2);
                status = 2;
                tvaAprovadas.setTextColor(getResources().getColor(R.color.colorAccentDark));
                tvSolicitadas.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                tvRejeitadas.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                fabCoordenador.setImageDrawable(getResources().getDrawable(R.drawable.ic_mail));
                break;
            }
            case R.id.tvMatriculasRecusadas: {
                setupRecyclerView(3);
                status = 3;
                tvRejeitadas.setTextColor(getResources().getColor(R.color.colorAccentDark));
                tvSolicitadas.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                tvaAprovadas.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                fabCoordenador.setImageDrawable(getResources().getDrawable(R.drawable.ic_mail));
                break;
            }
            case R.id.fabCoordenador: {
                if(status == 0){
                    Iterator<Aluno> iter = alunosInscritos.iterator();
                    while (iter.hasNext()) {
                        Aluno aluno = iter.next();
                        if (aluno.isSelecionado()) {
                            iter.remove();
                            aluno.setSelecionado(false);
                            alunosPendentes.add(aluno);
                        }
                    }
                    Toast.makeText(getActivity(), "Solicitação de matrícula enviada ao professor responsável.", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                }else if(status == 2){
                    Iterator<Aluno> iter = alunosAceitos.iterator();
                    while (iter.hasNext()) {
                        Aluno aluno = iter.next();
                        if (aluno.isSelecionado()) {
                            iter.remove();
                            aluno.setSelecionado(false);
                            alunosMatriculados.add(aluno);
                        }
                    }
                    Toast.makeText(getActivity(), "Matrícula realizada! E-mail enviado ao aluno.", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                }else{
                    if(alunosRejeitados == null) alunosRejeitados = new ArrayList<>();
                    Iterator<Aluno> iter = alunosRejeitados.iterator();
                    while (iter.hasNext()) {
                        Aluno aluno = iter.next();
                        if (aluno.isSelecionado()) {
                            iter.remove();
                        }
                    }
                    Toast.makeText(getActivity(), "Matrícula recusada! E-mail enviado para o aluno.", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}
