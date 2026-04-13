package com.example.tarea12_04_26mika;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etNumero, etBase;
    Button btnConvertir;
    TextView tvResultado;

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

        etNumero = findViewById(R.id.etNumero);
        etBase = findViewById(R.id.etBase);
        btnConvertir = findViewById(R.id.btnConvertir);
        tvResultado = findViewById(R.id.tvResultado);

        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String numeroStr = etNumero.getText().toString();
                String baseStr = etBase.getText().toString();

                // Validar vacíos
                if (numeroStr.isEmpty() || baseStr.isEmpty()) {
                    tvResultado.setText("Error: campos vacíos");
                    return;
                }

                int base;

                try {
                    base = Integer.parseInt(baseStr);
                } catch (Exception e) {
                    tvResultado.setText("Error: base inválida");
                    return;
                }

                // Validar rango
                if (base < 2 || base > 9) {
                    tvResultado.setText("Error: base debe estar entre 2 y 9");
                    return;
                }

                // Validar dígitos
                for (int i = 0; i < numeroStr.length(); i++) {
                    int digito = Character.getNumericValue(numeroStr.charAt(i));

                    if (digito >= base || digito < 0) {
                        tvResultado.setText("Error: dígitos inválidos");
                        return;
                    }
                }

                // Conversión
                int resultado = 0;
                int potencia = 0;

                for (int i = numeroStr.length() - 1; i >= 0; i--) {
                    int digito = Character.getNumericValue(numeroStr.charAt(i));
                    resultado += digito * Math.pow(base, potencia);
                    potencia++;
                }

                tvResultado.setText("Resultado: " + resultado);
            }
        });
    }
}