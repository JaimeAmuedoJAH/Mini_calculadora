package com.jah.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    Button btnMas, btnMenos, btnDividir, btnMultiplicar, btnRoot, btnInv;
    Button btnPunto, btnMasMenos, btnIgual, btnBorrar;
    TextView txtMostrar;
    String str;
    DecimalFormat formato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.btn0); btn1 = findViewById(R.id.btn1); btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3); btn4 = findViewById(R.id.btn4); btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6); btn7 = findViewById(R.id.btn7); btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9); btnMas = findViewById(R.id.btnMas); txtMostrar = findViewById(R.id.txtMostrar);
        btnMenos = findViewById(R.id.btnMenos); btnDividir = findViewById(R.id.btnDividir); btnMultiplicar = findViewById(R.id.btnMultiplicar);
        btnRoot = findViewById(R.id.btnRoot); btnInv = findViewById(R.id.btnInv); btnPunto = findViewById(R.id.btnPunto);
        btnMasMenos = findViewById(R.id.btnMasMenos); btnIgual = findViewById(R.id.btnIgual); btnBorrar = findViewById(R.id.btnBorrar);
        formato = new DecimalFormat("0.00"); str = "";

        btnBorrar.setOnClickListener(view -> borrarResultado());
        btn0.setOnClickListener(view -> aniadirValor("0"));
        btn1.setOnClickListener(view -> aniadirValor("1"));
        btn2.setOnClickListener(view -> aniadirValor("2"));
        btn3.setOnClickListener(view -> aniadirValor("3"));
        btn4.setOnClickListener(view -> aniadirValor("4"));
        btn5.setOnClickListener(view -> aniadirValor("5"));
        btn6.setOnClickListener(view -> aniadirValor("6"));
        btn7.setOnClickListener(view -> aniadirValor("7"));
        btn8.setOnClickListener(view -> aniadirValor("8"));
        btn9.setOnClickListener(view -> aniadirValor("9"));
        btnPunto.setOnClickListener(view -> aniadirValor("."));
        btnMas.setOnClickListener(view -> aniadirValor("+"));
        btnMenos.setOnClickListener(view -> aniadirValor("-"));
        btnDividir.setOnClickListener(view -> aniadirValor("/"));
        btnMultiplicar.setOnClickListener(view -> aniadirValor("X"));
        btnIgual.setOnClickListener(view -> operacion());
        btnMasMenos.setOnClickListener(view -> cambiarNegativo());
        btnRoot.setOnClickListener(view -> raizCuadrada());
        btnInv.setOnClickListener(view -> invertirNumero());
    }

    private void invertirNumero() {
        double numero = 1 / Double.parseDouble(txtMostrar.getText().toString().replace(",", "."));
        str = formato.format(numero);
        txtMostrar.setText(str);
    }

    private void raizCuadrada() {
        try {
            str = txtMostrar.getText().toString().replace(",", ".");
            double numero = Double.parseDouble(str);
            double resultado = Math.sqrt(numero);
            str = formato.format(resultado);
            txtMostrar.setText(str);
        } catch (NumberFormatException e) {
            System.err.println("ERROR: FORMATO INCORRECTO");
            txtMostrar.setText(getString(R.string.ERROR));
        } catch (ArithmeticException e) {
            System.err.println("ERROR: NO PUEDES REALIZAR ESA OPERACIÓN");
            txtMostrar.setText(getString(R.string.ERROR));
        } catch (Exception e) {
            System.err.println("ERROR: ALGO SALIÓ MAL");
            txtMostrar.setText(getString(R.string.ERROR));
        }
    }

    private void cambiarNegativo() {
        str = txtMostrar.getText().toString().replace(",", ".");
        double numero = Double.parseDouble(str);
        numero = numero * -1;
        str = formato.format(numero);
        txtMostrar.setText(str);
    }

    private void operacion() {
        double resultado = 0;
        str = txtMostrar.getText().toString().replace(",", ".");
        try {
            if (str.contains("+")) {
                String[] numeros = str.split("\\+");
                resultado = Double.parseDouble(numeros[0]) + Double.parseDouble(numeros[1]);
            } else if (str.contains("X")){
                if(str.charAt(0) == '-'){
                    str = str.replace("-", "");
                    String[] numeros = str.split("X");
                    resultado = Double.parseDouble(numeros[0]) * Double.parseDouble(numeros[1]) * -1;
                }else{
                    String[] numeros = str.split("X");
                    resultado = Double.parseDouble(numeros[0]) * Double.parseDouble(numeros[1]);
                }
            } else if (str.contains("/")) {
                if(str.charAt(0) == '-'){
                    str = str.replace("-", "");
                    String[] numeros = str.split("/");
                    resultado = Double.parseDouble(numeros[0]) / Double.parseDouble(numeros[1]) * -1;
                }else{
                    String[] numeros = str.split("/");
                    resultado = Double.parseDouble(numeros[0]) / Double.parseDouble(numeros[1]);
                }
            } else if (str.contains("-")) {
                if(str.charAt(0) == '-'){
                    int cero = 0;
                    String numero = cero + "";
                    String[] numeros = str.split("-");
                    numeros[0] = numero;
                    resultado = Double.parseDouble(numeros[0]) - Double.parseDouble(numeros[1]) - Double.parseDouble(numeros[2]);
                }else{
                    String[] numeros = str.split("-");
                    resultado = Double.parseDouble(numeros[0]) - Double.parseDouble(numeros[1]);
                }
            }
            str = formato.format(resultado);
            txtMostrar.setText(str);
        } catch (ArithmeticException e) {
            System.err.println("ERROR: NO PUEDES REALIZAR ESA OPERACIÓN");
            txtMostrar.setText(getString(R.string.ERROR));
        } catch (Exception e) {
            System.err.println("ERROR: ALGO SALIÓ MAL");
            txtMostrar.setText(getString(R.string.ERROR));
        }
    }

    private void aniadirValor(String valor) {
        str += valor;
        txtMostrar.setText(str);
    }


    private void borrarResultado() {
        txtMostrar.setText("");
        str = "";
    }
}