package br.edu.ifsp.scl.sdm.listpad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityItemBinding;
import br.edu.ifsp.scl.sdm.listpad.model.Geral;

public class GeralActivity extends AppCompatActivity {

    private ActivityItemBinding activityItemBinding;
    private Geral item;
    private int posicao = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityItemBinding = ActivityItemBinding.inflate(getLayoutInflater());

        setContentView(activityItemBinding.getRoot());

        activityItemBinding.salvarBtn.setOnClickListener((View view) -> {
            Geral item = new Geral(
                    null,
                    activityItemBinding.nomeEt.getText().toString(),
                    activityItemBinding.descricaoEt.getText().toString()
            );
            Intent resultadoIntent = new Intent();
            resultadoIntent.putExtra(GeralListaActivity.EXTRA_GERAL, item);
            if (posicao != -1) {
                resultadoIntent.putExtra(GeralListaActivity.EXTRA_POSICAO_GERAL, posicao);
            }
            setResult(RESULT_OK, resultadoIntent);
            Toast.makeText(this, "Alteração salva", Toast.LENGTH_LONG).show();
            finish();
        });

        // Verificando se é uma edição ou consulta de item

        item = getIntent().getParcelableExtra(GeralListaActivity.EXTRA_GERAL);
        posicao = getIntent().getIntExtra(GeralListaActivity.EXTRA_POSICAO_GERAL, -1);

        if (item != null) {
            activityItemBinding.nomeEt.setText(item.getNomeGeral());
            activityItemBinding.descricaoEt.setText(item.getDescGeral());
            if (posicao == -1) {
                activityItemBinding.nomeEt.setEnabled(false);
                activityItemBinding.descricaoEt.setEnabled(false);
                activityItemBinding.salvarBtn.setVisibility(View.GONE);
            }

        }

    }
}