package com.example.jose.calculadorajava;

import android.graphics.Color;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity /*implements View.OnClickListener*/ {

    //GLOBAL VARIABLES
    boolean decimal = false;
    boolean opSum = false;
    boolean oprRes = false;
    boolean opDiv = false;
    boolean opTimes = false;
    Double[] operations = new Double[20];
    Double result = 0.0;
    String numbers = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //create Linear Layout Global
        LinearLayout layoutG = new LinearLayout(this);
        layoutG.setOrientation(LinearLayout.VERTICAL);
        layoutG.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        layoutG.setPadding(dptopx(10),dptopx(20),dptopx(10),dptopx(20));;

        //create Text View, write operations
        final TextView myOperations = new TextView(this);
        myOperations.setBackgroundColor(Color.parseColor("#DCEDC8"));
        myOperations.setText("0.0");
        myOperations.setHeight(dptopx(70));
        myOperations.setTextSize(40);
        myOperations.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        myOperations.setPadding(dptopx(10), dptopx(20), dptopx(10), 0);
        operations[0] = Double.parseDouble(String.valueOf(myOperations.getText()));

        layoutG.addView(myOperations);

        //create Linear layout General Buttons
        LinearLayout layoutGBtn = new LinearLayout(this);
        layoutGBtn.setOrientation(LinearLayout.HORIZONTAL);
        layoutGBtn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        layoutGBtn.setBackgroundColor(Color.parseColor("#9CCC65"));
        layoutGBtn.setPadding(20, dptopx(40), 20, 20);
        layoutGBtn.setWeightSum(8);

        //Fill Button's Array
        Button[] listButtons = new Button[20];
        for (int i=0;i<listButtons.length;i++){
            //Create Button
            final Button buttonP = new Button(this);
            buttonP.setText(String.valueOf(i));
            buttonP.setTextColor(Color.parseColor("#558B2F"));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 2;
            params.height = 0;
            buttonP.setTextSize(40);
            if (i<=9){
                buttonP.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v) {
                        numbers += String.valueOf(buttonP.getText());
                        myOperations.setText(numbers);
                    }
                });
            }
           // buttonP.setId(i);
            buttonP.setLayoutParams(params);
            listButtons[i] = buttonP;
        }

        //Params Layout Buttons
        LinearLayout.LayoutParams paramsBtn = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        paramsBtn.weight = 2;
        paramsBtn.width = 0;

        //Create first Layout Vertical
        LinearLayout layoutFrtV = new LinearLayout(this);
        layoutFrtV.setOrientation(LinearLayout.VERTICAL);
        layoutFrtV.setWeightSum(10);
        layoutFrtV.setLayoutParams(paramsBtn);
        listButtons[19].setText("^");
        listButtons[19].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                funPow(myOperations);
            }
        });
        layoutFrtV.addView(listButtons[19]);
        layoutFrtV.addView(listButtons[7]);
        layoutFrtV.addView(listButtons[4]);
        layoutFrtV.addView(listButtons[1]);
        listButtons[10].setText(".");
        listButtons[10].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pointDec(myOperations);
            }
        });
        layoutFrtV.addView(listButtons[10]);

        layoutGBtn.addView(layoutFrtV);

        //Create Second Layout Vertical
        LinearLayout layoutSndV = new LinearLayout(this);
        layoutSndV.setOrientation(LinearLayout.VERTICAL);
        layoutSndV.setLayoutParams(paramsBtn);
        listButtons[18].setText("Sqrt");
        listButtons[18].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                funSqrt(myOperations);
            }
        });
        layoutSndV.addView(listButtons[18]);
        layoutSndV.addView(listButtons[8]);
        layoutSndV.addView(listButtons[5]);
        layoutSndV.addView(listButtons[2]);
        layoutSndV.addView(listButtons[0]);

        layoutGBtn.addView(layoutSndV);

        //Create Third Layout Vertical
        LinearLayout layoutTrdV = new LinearLayout(this);
        layoutTrdV.setOrientation(LinearLayout.VERTICAL);
        layoutTrdV.setLayoutParams(paramsBtn);
        listButtons[17].setText("DEL");
        listButtons[17].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                funDEL(myOperations);
            }
        });
        layoutTrdV.addView(listButtons[17]);
        layoutTrdV.addView(listButtons[9]);
        layoutTrdV.addView(listButtons[6]);
        layoutTrdV.addView(listButtons[3]);
        listButtons[11].setText("=");
        listButtons[11].setOnClickListener(new View.OnClickListener() {public void onClick(View v) {funEqual(myOperations);}});
        layoutTrdV.addView(listButtons[11]);

        layoutGBtn.addView(layoutTrdV);

        //Create Fourth Layout Vertical
        LinearLayout layoutFrdV = new LinearLayout(this);
        layoutFrdV.setOrientation(LinearLayout.VERTICAL);
        layoutFrdV.setLayoutParams(paramsBtn);
        listButtons[16].setText("AC");
        listButtons[16].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                funAC(myOperations);
            }
        });
        layoutFrdV.addView(listButtons[16]);
        listButtons[15].setText("/");
        listButtons[15].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                funDiv(myOperations);
            }
        });
        layoutFrdV.addView(listButtons[15]);
        listButtons[14].setText("*");
        listButtons[14].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                funTimes(myOperations);
            }
        });
        layoutFrdV.addView(listButtons[14]);
        listButtons[13].setText("-");
        listButtons[13].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                funRest(myOperations);
            }
        });
        layoutFrdV.addView(listButtons[13]);
        listButtons[12].setText("+");
        listButtons[12].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                funSum(myOperations);
            }
        });
        layoutFrdV.addView(listButtons[12]);

        layoutGBtn.addView(layoutFrdV);

        layoutG.addView(layoutGBtn);

        //SHOW CONTENT
        setContentView(layoutG);
    }

    //Convert dp to pixel
    public int dptopx(int dp){
        DisplayMetrics displayMetrics = getBaseContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT);
        return  px;
    }

    //Function AC
    public void funAC(TextView TV){
        numbers = "";
        decimal = false;
        operations[0] = 0.0;
        TV.setText("0.0");
    }

    //Function Sqrt
    public void funSqrt(TextView TV){
        opDiv = opTimes = oprRes = opSum = false;
        if (numbers.compareTo(".")==0){return;}
        if(!numbers.isEmpty()){
            operations[0] = Double.parseDouble(numbers);
        }
        Double rpt = Math.sqrt(operations[0]);
        operations[0] = rpt;
        numbers = "";
        decimal = false;
        TV.setText(String.valueOf(rpt));
    }

    //Function Pow
    public void funPow(TextView TV){
        opDiv = opTimes = oprRes = opSum = false;
        if (numbers.compareTo(".")==0){return;}
        if (!numbers.isEmpty()){
            operations[0] = Double.parseDouble(numbers);
        }
        Double rpt = Math.pow(operations[0], 2);
        operations[0] = rpt;
        numbers = "";
        decimal = false;
        TV.setText(String.valueOf(rpt));
    }

    //Point Decimal
    public void pointDec(TextView TV){
        if (!decimal){
            numbers += ".";
            decimal = true;
            TV.setText(numbers);
        }
    }

    //Delete one letter
    public void funDEL(TextView TV){
        int tam = numbers.length();
        if (tam>0){
            if(numbers.charAt(tam-1)=='.'){
                decimal = false;
            }
            numbers = numbers.substring(0,tam-1);
        }
        TV.setText(numbers);
    }

    //Function Sum
    public void funSum(TextView TV){
        opDiv = opTimes = oprRes = false;
        if (!opSum && !numbers.isEmpty() && numbers.compareTo(".")!=0){
            operations[0] = Double.parseDouble(numbers);
            numbers = "";
            opSum = true;
            decimal = false;
            TV.setText(String.valueOf(operations[0])+"+");
        }else{
            if(opSum && !numbers.isEmpty()){
                operations[0] += Double.parseDouble(numbers);
                numbers = "";
                decimal = false;
                TV.setText(String.valueOf(operations[0])+"+");
            }
        }
    }

    //Function Rest
    public  void funRest(TextView TV){
        opDiv = opTimes = opSum = false;
        if (!oprRes && !numbers.isEmpty() && numbers.compareTo(".")!=0){
            operations[0] = Double.parseDouble(numbers);
            numbers = "";
            oprRes = true;
            decimal = false;
            TV.setText(String.valueOf(operations[0])+"-");
        }else{
            if(oprRes && !numbers.isEmpty()){
                operations[0] -= Double.parseDouble(numbers);
                numbers = "";
                decimal = false;
                TV.setText(String.valueOf(operations[0])+"-");
            }
        }
    }

    //Function Times
    public void funTimes(TextView TV){
        opDiv = oprRes = opSum = false;
        if (!opTimes && !numbers.isEmpty() && numbers.compareTo(".")!=0){
            operations[0] = Double.parseDouble(numbers);
            numbers = "";
            opTimes = true;
            decimal = false;
            TV.setText(String.valueOf(operations[0])+"*");
        }else{
            if(opTimes && !numbers.isEmpty()){
                operations[0] *= Double.parseDouble(numbers);
                numbers = "";
                decimal = false;
                TV.setText(String.valueOf(operations[0])+"*");
            }
        }
    }

    //Function Division
    public void funDiv(TextView TV){
        opTimes = oprRes = opSum = false;
        if (!opDiv && !numbers.isEmpty() && numbers.compareTo(".")!=0){
            operations[0] = Double.parseDouble(numbers);
            numbers = "";
            opDiv = true;
            decimal = false;
            TV.setText(String.valueOf(operations[0])+"/");
        }else{
            if(opDiv && !numbers.isEmpty()){
                operations[0] /= Double.parseDouble(numbers);
                numbers = "";
                decimal = false;
                TV.setText(String.valueOf(operations[0])+"/");
            }
        }
    }

    //Function Equal
    public void funEqual(TextView TV){
        if (numbers.isEmpty() || numbers.compareTo(".")==0){
            operations[1] = 0.0;
        }else{
            operations[1] = Double.parseDouble(numbers);
        }
        if (opSum){
            result = operations[0] + operations[1];
            numbers = String.valueOf(result);
            opSum = false;
            TV.setText(numbers);
        }
        if (oprRes){
            result = operations[0] - operations[1];
            numbers = String.valueOf(result);
            oprRes = false;
            TV.setText(numbers);
        }
        if (opTimes){
            result = operations[0] * operations[1];
            numbers = String.valueOf(result);
            opTimes = false;
            TV.setText(numbers);
        }
        if (opDiv){
            result = operations[0] / operations[1];
            numbers = String.valueOf(result);
            opDiv = false;
            TV.setText(numbers);
        }
    }
}
