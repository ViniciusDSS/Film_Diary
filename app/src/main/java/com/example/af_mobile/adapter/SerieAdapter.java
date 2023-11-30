package com.example.af_mobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.af_mobile.R;
import com.example.af_mobile.model.Serie;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class SerieAdapter extends RecyclerView.Adapter<SerieAdapter.SerieViewHolder> {

    private List<Serie> seriesList;
    @NonNull
    @Override
    public SerieAdapter.SerieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_verserie, parent, false);
        return new SerieViewHolder(view);
    }

    public void setSeriesList(List<Serie> seriesList) {
        this.seriesList = seriesList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull SerieAdapter.SerieViewHolder holder, int position) {
        Serie serie = seriesList.get(position);
        holder.textTitulo.setText(serie.getTitulo());
        holder.temporada.setText(serie.getTemporada());
        holder.epAssistido.setText(serie.getEpAssistido());
        holder.diasemana.setText(serie.getDiasemana());
        holder.assistir.setText(serie.getAssistir());
        holder.plataforma.setText(serie.getPlataforma());

        holder.btnRemover.setOnClickListener(view -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION && seriesList != null) {
                Serie serieRemover = seriesList.get(adapterPosition);
                String serieTitulo = serieRemover.getTitulo();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    String userEmail = user.getEmail().replace(".", ",");
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(userEmail);

                    databaseReference.child(serieTitulo).removeValue()
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(view.getContext(), "Removido com sucesso", Toast.LENGTH_SHORT).show();
                                // Remova o item da lista local apenas se a remoção no Firebase for bem-sucedida
                                seriesList.remove(serieRemover);
                                notifyItemRemoved(adapterPosition);
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(view.getContext(), "Falha ao remover do Firebase", Toast.LENGTH_SHORT).show();
                            });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return seriesList != null ? seriesList.size() : 0;
    }

    static class SerieViewHolder extends RecyclerView.ViewHolder {
        TextView textTitulo, plataforma, diasemana,epAssistido,assistir,temporada;

        Button btnRemover;

        SerieViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitulo = itemView.findViewById(R.id.textTitulo);
            plataforma = itemView.findViewById(R.id.textPlataforma);
            diasemana = itemView.findViewById(R.id.textDiaSemana);
            epAssistido = itemView.findViewById(R.id.textUltimoEP);
            assistir = itemView.findViewById(R.id.textEpisodio);
            temporada = itemView.findViewById(R.id.textTemporada);
            btnRemover = itemView.findViewById(R.id.btRemoverSerie);
        }
    }
}
