package br.edu.ifsp.scl.sdm.listpad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityComprasBinding;
import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityItemBinding;
import br.edu.ifsp.scl.sdm.listpad.model.Itens;

public class ComprasActivity extends AppCompatActivity {

    private ActivityComprasBinding activityComprasBinding;
    private int posicao = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComprasBinding = ActivityComprasBinding.inflate(getLayoutInflater());

        setContentView(activityComprasBinding.getRoot());

        }
    }

