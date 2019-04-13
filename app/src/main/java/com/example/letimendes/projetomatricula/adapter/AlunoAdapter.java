package com.example.letimendes.projetomatricula.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.letimendes.projetomatricula.R;

import static com.example.letimendes.projetomatricula.MainActivity.alunosAceitos;
import static com.example.letimendes.projetomatricula.MainActivity.alunosInscritos;
import static com.example.letimendes.projetomatricula.MainActivity.alunosMatriculados;
import static com.example.letimendes.projetomatricula.MainActivity.alunosPendentes;
import static com.example.letimendes.projetomatricula.MainActivity.alunosRejeitados;


public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder> {

    private final LayoutInflater mInflater;
    private int status;

    public AlunoAdapter(Context context, int status) {
        this.mInflater =  LayoutInflater.from(context);
        this.status = status;
    }

    @Override
    public AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.aluno_item, viewGroup, false);
        return new AlunoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoViewHolder alunoViewHolder, int i) {
        final int position = i;
        if(status == 0 && alunosInscritos.size() > 0) {
            alunoViewHolder.selecionado.setChecked(alunosInscritos.get(position).isSelecionado());
            alunoViewHolder.selecionado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    alunosInscritos.get(position).setSelecionado(isChecked);
                }
            });
            alunoViewHolder.cpf.setText("Nome: " + alunosInscritos.get(i).getNome());
            alunoViewHolder.nome.setText("CPF: " + alunosInscritos.get(i).getCpf());
            alunoViewHolder.email.setText("E-mail: " + alunosInscritos.get(i).getEmail());
        }else if(status == 1 && alunosPendentes.size() > 0){
            alunoViewHolder.selecionado.setChecked(alunosPendentes.get(position).isSelecionado());
            alunoViewHolder.selecionado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    alunosPendentes.get(position).setSelecionado(isChecked);
                }
            });
            alunoViewHolder.cpf.setText("Nome: " + alunosPendentes.get(i).getNome());
            alunoViewHolder.nome.setText("CPF: " + alunosPendentes.get(i).getCpf());
            alunoViewHolder.email.setText("E-mail: " + alunosPendentes.get(i).getEmail());
        }else if(status == 2 && alunosAceitos.size() > 0){
            alunoViewHolder.selecionado.setChecked(alunosAceitos.get(position).isSelecionado());
            alunoViewHolder.selecionado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    alunosAceitos.get(position).setSelecionado(isChecked);
                }
            });
            alunoViewHolder.cpf.setText("Nome: " + alunosAceitos.get(i).getNome());
            alunoViewHolder.nome.setText("CPF: " + alunosAceitos.get(i).getCpf());
            alunoViewHolder.email.setText("E-mail: " + alunosAceitos.get(i).getEmail());
        }else if(status == 3 && alunosRejeitados.size() > 0){
            alunoViewHolder.selecionado.setChecked(alunosRejeitados.get(position).isSelecionado());
            alunoViewHolder.selecionado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    alunosRejeitados.get(position).setSelecionado(isChecked);
                }
            });
            alunoViewHolder.cpf.setText("Nome: " + alunosRejeitados.get(i).getNome());
            alunoViewHolder.nome.setText("CPF: " + alunosRejeitados.get(i).getCpf());
            alunoViewHolder.email.setText("E-mail: " + alunosRejeitados.get(i).getEmail());
        }

    }

    @Override
    public int getItemCount() {
        if(status == 0){
            return alunosInscritos.size();
        }else if(status == 1){
            return alunosPendentes.size();
        }else if(status == 2){
            return alunosAceitos.size();
        }else if(status == 3){
            return alunosRejeitados.size();
        }else{
            return alunosMatriculados.size();
        }
    }

    class AlunoViewHolder extends RecyclerView.ViewHolder{

        private CheckBox selecionado;
        private TextView nome;
        private TextView cpf;
        private TextView email;

        public AlunoViewHolder(@NonNull View itemView) {
            super(itemView);
            selecionado = itemView.findViewById(R.id.selecionado);
            nome = itemView.findViewById(R.id.txtNomeAluno);
            cpf = itemView.findViewById(R.id.txtCpfAluno);
            email = itemView.findViewById(R.id.txtEmailAluno);
        }
    }

}
