package com.example.samuel.calcimposto;

import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBt(View view) {

        EditText ed   = (EditText) findViewById(R.id.edText);
        TextView tv06 = (TextView) findViewById(R.id.textView6);
        TextView tv07 = (TextView) findViewById(R.id.textView7);
        TextView tv15 = (TextView) findViewById(R.id.textView15);
        TextView tv14 = (TextView) findViewById(R.id.textView14);
        TextView tv08 = (TextView) findViewById(R.id.textView8);
        TextView tv09 = (TextView) findViewById(R.id.textView9);
        TextView tv10 = (TextView) findViewById(R.id.textView10);

        double salario = Double.valueOf(ed.getText().toString());
        String salarioString = Double.toString(salario);

        double resultInssTotal = InssTotal(salario);
        float resultInssTotalFloat = (float)resultInssTotal;
        String resultInssTotalString = Float.toString(resultInssTotalFloat);

        double resultInss = Inss(salario);

        String resultInssporcet = InssPorcent(salario);

        double resultImpostoRenda = ImpostoRenda(resultInss);
        float resultImpostoRendalFloat = (float)resultImpostoRenda;
        String resultImpostoRendaString = Float.toString(resultImpostoRendalFloat);

        String resultImpostoRendaPorcent = ImpostoRendaPorcent(resultInss);

        double resultValorliquido = ValorLiquido(salario,resultInssTotal,resultImpostoRenda,resultInss);
        float resultValorliquidolFloat = (float)resultValorliquido;
        String resultValorliquidoString = Float.toString(resultValorliquidolFloat);

        double resultDesconto = resultInssTotal + resultImpostoRenda;
        float resultDescontoFloat = (float)resultDesconto;
        String resultDescontoString = Float.toString(resultDescontoFloat);

        tv06.setText(resultInssporcet);
        tv07.setText(resultImpostoRendaPorcent);
        tv15.setText(resultInssTotalString);
        tv14.setText(resultImpostoRendaString);
        tv08.setText(resultDescontoString);
        tv09.setText(salarioString);
        tv10.setText(resultValorliquidoString);
    }

    public double Inss(double salario){
        double inss = 0;
        double insstotal = 0;
        if(salario <= 1556.94){
            inss = salario*0.08;
            insstotal = salario - inss;
        }else if (salario >= 1556.95 && salario <= 2594.92){
            inss = salario*0.09;
            insstotal = salario - inss;
        }else if (salario >= 2594.93 && salario <= 5189.81){
            inss = salario*0.11;
            insstotal = salario - inss;
        }else if (salario > 5189.82){
            inss = 5189.82*0.11;
            insstotal = salario - inss;
        }

        return insstotal;
    }

    public double InssTotal(double salario){
        double inss = 0;
        if(salario <= 1556.94){
            inss = salario*0.08;
        }else if (salario >= 1556.95 && salario <= 2594.92){
            inss = salario*0.09;
        }else if (salario >= 2594.93 && salario <= 5189.81){
            inss = salario*0.11;
        }else if (salario > 5189.82){
            inss = 5189.82*0.11;
        }
        return inss;
    }

    public String InssPorcent(double salario){
        String porcent = null;
        if(salario <= 1556.94){
            porcent = "8%";
        }else if (salario >= 1556.95 && salario <= 2594.92){
            porcent = "9%";
        }else if (salario >= 2594.93 && salario <= 5189.81){
            porcent = "11%";
        }else if (salario > 5189.82){
            porcent = "TETO";
        }
        return porcent;
    }

    public String ImpostoRendaPorcent(double resultInss){
        String irporcent = null;
        if (resultInss <= 1903.98){
            irporcent = "Isento";
        }else if (resultInss >= 1903.99 && resultInss <= 2826.65){
            irporcent = "7,5%";
        }else if (resultInss >= 2826.66 && resultInss <= 3751.05){
            irporcent = "15%";
        }else if (resultInss >= 3751.06 && resultInss <= 4664.68){
            irporcent = "22,5%";
        }else if (resultInss > 4664.69){
            irporcent = "27,5%";
        }
        return irporcent;
    }

    public double ImpostoRenda(double resultInss){
        double ir=0;
        if (resultInss <= 1903.98){
            ir = 0;
        }else if (resultInss >= 1903.99 && resultInss <= 2826.65){
            ir = ((resultInss*0.075) - 142.80);
        }else if (resultInss >= 2826.66 && resultInss <= 3751.05){
            ir = ((resultInss*0.15) - 354.80);
        }else if (resultInss >= 3751.06 && resultInss <= 4664.68){
            ir = ((resultInss*0.225) - 636.13);
        }else if (resultInss > 4664.69){
            ir = ((resultInss*0.275) - 869.36);
        }
        return ir;
    }

    public double ValorLiquido(double salario, double resultInssTotal, double resultImpostoRenda,double resultInss){
        double valorliquido = 0;
        if (resultInss <= 1903.98){
            valorliquido = salario - resultInssTotal - resultImpostoRenda;
        }else if (resultInss >= 1903.99 && resultInss <= 2826.65){
            valorliquido = salario - resultInssTotal - resultImpostoRenda;
        }else if (resultInss >= 2826.66 && resultInss <= 3751.05){
            valorliquido = salario - resultInssTotal - resultImpostoRenda;
        }else if (resultInss >= 3751.06 && resultInss <= 4664.68){
            valorliquido = salario - resultInssTotal - resultImpostoRenda;
        }else if (resultInss > 4664.69){
            valorliquido = salario - resultInssTotal - resultImpostoRenda;
        }
        return valorliquido;
    }
}
