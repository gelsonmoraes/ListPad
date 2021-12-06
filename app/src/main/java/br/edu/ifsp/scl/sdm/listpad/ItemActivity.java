package br.edu.ifsp.scl.sdm.listpad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityItemBinding;
import br.edu.ifsp.scl.sdm.listpad.model.Itens;

public class ItemActivity extends AppCompatActivity {

    private ActivityItemBinding activityItemBinding;

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
            setResult(RESULT_OK, resultadoIntent);
            finish();
        });
    }
}