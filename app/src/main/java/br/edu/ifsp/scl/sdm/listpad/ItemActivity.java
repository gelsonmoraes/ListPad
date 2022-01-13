package br.edu.ifsp.scl.sdm.listpad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityItemBinding;
import br.edu.ifsp.scl.sdm.listpad.model.Itens;

public class ItemActivity extends AppCompatActivity {

    private ActivityItemBinding activityItemBinding;
    private Itens item;
    private int posicao = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityItemBinding = ActivityItemBinding.inflate(getLayoutInflater());

        setContentView(activityItemBinding.getRoot());

        activityItemBinding.salvarBtn.setOnClickListener((View view) -> {
            Itens item = new Itens(
                    activityItemBinding.nomeEt.getText().toString(),
                    activityItemBinding.descricaoEt.getText().toString()
            );
            Intent resultadoIntent = new Intent();
            resultadoIntent.putExtra(MainActivity.EXTRA_ITEM, item);
            if (posicao != -1){
                resultadoIntent.putExtra(MainActivity.EXTRA_POSICAO, posicao);
            }
            setResult(RESULT_OK, resultadoIntent);
            finish();
        });

        // Verificando se é uma edição ou consulta de item

        item = getIntent().getParcelableExtra(MainActivity.EXTRA_ITEM);
        posicao = getIntent().getIntExtra(MainActivity.EXTRA_POSICAO, -1);

        if(item != null && posicao != -1) {
            activityItemBinding.nomeEt.setText(item.getNomeLista());
            activityItemBinding.descricaoEt.setText(item.getDescLista());
            if (posicao == -1){
                activityItemBinding.nomeEt.setEnabled(false);
                activityItemBinding.descricaoEt.setEnabled(false);
                activityItemBinding.salvarBtn.setVisibility(View.GONE);
            }

        }


    }
}