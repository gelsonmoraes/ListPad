package br.edu.ifsp.scl.sdm.listpad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityItemBinding;
import br.edu.ifsp.scl.sdm.listpad.model.Compra;
import br.edu.ifsp.scl.sdm.listpad.model.Itens;

public class ItemActivity extends AppCompatActivity {

    private ActivityItemBinding activityItemBinding;
    private Compra item;
    private int posicao = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityItemBinding = ActivityItemBinding.inflate(getLayoutInflater());

        setContentView(activityItemBinding.getRoot());

        activityItemBinding.salvarBtn.setOnClickListener((View view) -> {
            Compra item = new Compra(
                    activityItemBinding.nomeEt.getText().toString(),
                    activityItemBinding.descricaoEt.getText().toString()
            );
            Intent resultadoIntent = new Intent();
            resultadoIntent.putExtra(ComprasListaActivity.EXTRA_COMPRA, item);
            if (posicao != -1) {
                resultadoIntent.putExtra(ComprasListaActivity.EXTRA_POSICAO_COMPRA, posicao);
            }
            setResult(RESULT_OK, resultadoIntent);
            Toast.makeText(this, "Alteração salva", Toast.LENGTH_LONG).show();
            finish();
        });

        // Verificando se é uma edição ou consulta de item

        item = getIntent().getParcelableExtra(ComprasListaActivity.EXTRA_COMPRA);
        posicao = getIntent().getIntExtra(ComprasListaActivity.EXTRA_POSICAO_COMPRA, -1);

        if (item != null) {
            activityItemBinding.nomeEt.setText(item.getNomeCompra());
            activityItemBinding.descricaoEt.setText(item.getDescCompra());
            if (posicao == -1) {
                activityItemBinding.nomeEt.setEnabled(false);
                activityItemBinding.descricaoEt.setEnabled(false);
                activityItemBinding.salvarBtn.setVisibility(View.GONE);
            }

        }

    }
}