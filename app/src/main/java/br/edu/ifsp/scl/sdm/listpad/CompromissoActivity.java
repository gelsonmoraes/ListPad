package br.edu.ifsp.scl.sdm.listpad;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityItemBinding;
import br.edu.ifsp.scl.sdm.listpad.model.Compromisso;

public class CompromissoActivity extends AppCompatActivity {

    private ActivityItemBinding activityItemBinding;
    private Compromisso item;
    private int posicao = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityItemBinding = ActivityItemBinding.inflate(getLayoutInflater());

        setContentView(activityItemBinding.getRoot());

        activityItemBinding.salvarBtn.setOnClickListener((View view) -> {
            Compromisso item = new Compromisso(
                    activityItemBinding.nomeEt.getText().toString(),
                    activityItemBinding.descricaoEt.getText().toString()
            );
            Intent resultadoIntent = new Intent();
            resultadoIntent.putExtra(CompromissosListaActivity.EXTRA_COMPROMISSO, item);
            if (posicao != -1) {
                resultadoIntent.putExtra(CompromissosListaActivity.EXTRA_POSICAO_COMPROMISSO, posicao);
            }
            setResult(RESULT_OK, resultadoIntent);
            Toast.makeText(this, "Alteração salva", Toast.LENGTH_LONG).show();
            finish();
        });

        // Verificando se é uma edição ou consulta de item

        item = getIntent().getParcelableExtra(CompromissosListaActivity.EXTRA_COMPROMISSO);
        posicao = getIntent().getIntExtra(CompromissosListaActivity.EXTRA_POSICAO_COMPROMISSO, -1);

        if (item != null) {
            activityItemBinding.nomeEt.setText(item.getNomeCompromisso());
            activityItemBinding.descricaoEt.setText(item.getDescCompromisso());
            if (posicao == -1) {
                activityItemBinding.nomeEt.setEnabled(false);
                activityItemBinding.descricaoEt.setEnabled(false);
                activityItemBinding.salvarBtn.setVisibility(View.GONE);
            }

        }

    }
}