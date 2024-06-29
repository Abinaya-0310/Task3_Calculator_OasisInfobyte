package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result,solution;
    MaterialButton button_c,openBracket,closeBracket,division;
    MaterialButton button9,button8,button7,multiply,subtract,add,Clear;
    MaterialButton button4,button5,button6,button3,button2,button1,button0,buttondot,equalto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        solution = findViewById(R.id.solution);
        assignId(button_c,R.id.button_c);
        assignId(openBracket,R.id.openBracket);
        assignId(closeBracket,R.id.closeBracket);
        assignId(division,R.id.division);
        assignId(button9,R.id.button9);
        assignId(button8,R.id.button8);
        assignId(button7,R.id.button7);
        assignId(button6,R.id.button6);
        assignId(button5,R.id.button5);
        assignId(button4,R.id.button4);
        assignId(button3,R.id.button3);
        assignId(button2,R.id.button2);
        assignId(button1,R.id.button1);
        assignId(button0,R.id.button0);
        assignId(buttondot,R.id.buttondot);
        assignId(Clear,R.id.Clear);
        assignId(equalto,R.id.equalto);
        assignId(add,R.id.add);
        assignId(subtract,R.id.subtract);
        assignId(multiply,R.id.multiply);
    }
    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        MaterialButton button=(MaterialButton) v;
        String btntext=button.getText().toString();
        String datatocalculate=solution.getText().toString();
        if(btntext.equals("AC")){
            solution.setText("");
            result.setText("0");
            return;
        }
        if(btntext.equals("=")){
            solution.setText(result.getText());
            return;
        }
        if(btntext.equals("C")){
            datatocalculate=datatocalculate.substring(0,datatocalculate.length()-1);
        }
        else {
            datatocalculate = datatocalculate + btntext;
        }
        solution.setText(datatocalculate);
        String finalResult=getResult(datatocalculate);
        if(!finalResult.equals("Error")){
            result.setText(finalResult);
        }
    }
    String getResult(String data){
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable =context.initSafeStandardObjects();
            String finalResult=context.evaluateString(scriptable,data,"javascript",1,null).toString();
            return finalResult;
        }
        catch(Exception e){
            return "Error";
        }
    }

}