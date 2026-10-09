package com.uth.calculadora;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    private TextView tvResultado;
    private Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvResultado = findViewById(R.id.tvResultado);
        btnRegresar = findViewById(R.id.btnRegresar);

        double resultado = getIntent().getDoubleExtra("RESULTADO", 0.0);
        String operacion = getIntent().getStringExtra("OPERACION");

        if (operacion != null) {
            tvResultado.setText(operacion + "\n" + resultado);
        } else {
            tvResultado.setText(String.valueOf(resultado));
        }

        btnRegresar.setOnClickListener(v -> finish());
    }
}
