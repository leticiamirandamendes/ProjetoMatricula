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
import android.widget.Toast;

import com.example.letimendes.projetomatricula.adapter.AlunoAdapter;
import com.example.letimendes.projetomatricula.model.Aluno;

import java.util.ArrayList;
import java.util.Iterator;

import static com.example.letimendes.projetomatricula.MainActivity.alunosAceitos;
import static com.example.letimendes.projetomatricula.MainActivity.alunosPendentes;
import static com.example.letimendes.projetomatricula.MainActivity.alunosRejeitados;

public class ProfessorFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private FloatingActionButton adicionarMatricula;
    private FloatingActionButton recusarMatricula;
    private AlunoAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_professor, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity.activeLayout = 2;
        recyclerView = getActivity().findViewById(R.id.rvAlunoProfessor);


        adicionarMatricula = getActivity().findViewById(R.id.fabAceitar);
        recusarMatricula = getActivity().findViewById(R.id.fabRecusar);

        adicionarMatricula.setOnClickListener(this);
        recusarMatricula.setOnClickListener(this);

        setupRecyclerView(1);
        int i = alunosPendentes.size();
    }

    private void setupRecyclerView(int id) {
        if (id == 1) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            adapter = new AlunoAdapter(getActivity(), 1);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fabAceitar: {
                Iterator<Aluno> iter = alunosPendentes.iterator();
                while (iter.hasNext()) {
                    Aluno aluno = iter.next();
                    if (aluno.isSelecionado()) {
                        iter.remove();
                        aluno.setSelecionado(false);
                        alunosAceitos.add(aluno);
                    }
                }
                Toast.makeText(getActivity(), "Alunos(as) adicionados(as) à lista de matrículas aceitas! Notificação enviada para o coordenador.", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;
            }
            case R.id.fabRecusar: {
                Iterator<Aluno> iter = alunosPendentes.iterator();
                while (iter.hasNext()) {
                    Aluno aluno = iter.next();
                    if (aluno.isSelecionado()) {
                        iter.remove();
                        aluno.setSelecionado(false);
                        alunosRejeitados.add(aluno);
                    }
                }
                Toast.makeText(getActivity(), "Alunos(as) adicionados(as) à lista de matrículas rejeitadas! Notificação enviada para o coordenador.", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;
            }
        }
    }
}
