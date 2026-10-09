package com.uth.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText inputNumero1, inputNumero2;
    private Button btnSuma, btnResta, btnDivision, btnMultiplicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputNumero1 = findViewById(R.id.InputNumero1);
        inputNumero2 = findViewById(R.id.InputNumero2);

        btnSuma = findViewById(R.id.btnSuma);
        btnResta = findViewById(R.id.btnResta);
        btnDivision = findViewById(R.id.btnDivision);
        btnMultiplicacion = findViewById(R.id.btnMultiplicacion);

        btnSuma.setOnClickListener(v -> calcularYEnviar("suma"));
        btnResta.setOnClickListener(v -> calcularYEnviar("resta"));
        btnMultiplicacion.setOnClickListener(v -> calcularYEnviar("multiplicacion"));
        btnDivision.setOnClickListener(v -> calcularYEnviar("division"));
    }

    private void calcularYEnviar(String operacion) {
        String strNum1 = inputNumero1.getText() != null ? inputNumero1.getText().toString().trim() : "";
        String strNum2 = inputNumero2.getText() != null ? inputNumero2.getText().toString().trim() : "";

        if (strNum1.isEmpty() || strNum2.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese ambos números", Toast.LENGTH_SHORT).show(); // este es el mensaje que se mostrara en el toast cuando no hay nada escrito en los campos de texto
            return;
        }

        try {
            Double num1 = Double.parseDouble(strNum1);
            Double num2 = Double.parseDouble(strNum2);


            Calculadora calculadora = new Calculadora(num1, num2);
            Double resultado = 0.0;
            String nombreOperacion = "";

            switch (operacion) {
                case "suma":
                    resultado = calculadora.suma();
                    nombreOperacion = "Resultado Suma:";
                    break;
                case "resta":
                    resultado = calculadora.resta();
                    nombreOperacion = "Resultado Resta:";
                    break;
                case "multiplicacion":
                    resultado = calculadora.multiplicacion();
                    nombreOperacion = "Resultado Multiplicación:";
                    break;
                case "division":
                    if (num2 == 0) {
                        Toast.makeText(this, "No se puede dividir entre cero dara error", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    resultado = calculadora.division();
                    nombreOperacion = "Resultado División:";
                    break;
            }

            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("RESULTADO", resultado);
            intent.putExtra("OPERACION", nombreOperacion);
            startActivity(intent);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingrese números válidos", Toast.LENGTH_SHORT).show();
        }
    }
}
