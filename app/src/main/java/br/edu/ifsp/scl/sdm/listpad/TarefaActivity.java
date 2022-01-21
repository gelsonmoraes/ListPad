package br.edu.ifsp.scl.sdm.listpad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityItemBinding;
import br.edu.ifsp.scl.sdm.listpad.model.Tarefa;

public class TarefaActivity extends AppCompatActivity {

    private ActivityItemBinding activityItemBinding;
    private Tarefa item;
    private int posicao = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityItemBinding = ActivityItemBinding.inflate(getLayoutInflater());

        setContentView(activityItemBinding.getRoot());

        activityItemBinding.salvarBtn.setOnClickListener((View view) -> {
            Tarefa item = new Tarefa(
                    null,
                    activityItemBinding.nomeEt.getText().toString(),
                    activityItemBinding.descricaoEt.getText().toString()
            );
            Intent resultadoIntent = new Intent();
            resultadoIntent.putExtra(TarefasListaActivity.EXTRA_TAREFA, item);
            if (posicao != -1) {
                resultadoIntent.putExtra(TarefasListaActivity.EXTRA_POSICAO_TAREFA, posicao);
            }
            setResult(RESULT_OK, resultadoIntent);
            Toast.makeText(this, "Alteração salva", Toast.LENGTH_LONG).show();
            finish();
        });

        // Verificando se é uma edição ou consulta de item

        item = getIntent().getParcelableExtra(TarefasListaActivity.EXTRA_TAREFA);
        posicao = getIntent().getIntExtra(TarefasListaActivity.EXTRA_POSICAO_TAREFA, -1);

        if (item != null) {
            activityItemBinding.nomeEt.setText(item.getNomeTarefa());
            activityItemBinding.descricaoEt.setText(item.getDescTarefa());
            if (posicao == -1) {
                activityItemBinding.nomeEt.setEnabled(false);
                activityItemBinding.descricaoEt.setEnabled(false);
                activityItemBinding.salvarBtn.setVisibility(View.GONE);
            }

        }

    }
}