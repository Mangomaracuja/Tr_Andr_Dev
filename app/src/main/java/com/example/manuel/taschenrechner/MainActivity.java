package com.example.manuel.taschenrechner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ExpressionManager em;

    private TextView tv_expression;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        em = ExpressionManager.getInstance();
        tv_expression = findViewById(R.id.tv_expression);
        tv_expression.setText(em.toExpressionString());
        tv_result = findViewById(R.id.tv_result);
        tv_result.setText(em.toResultString());
    }

    public void handleButton(View view){
        switch (view.getId()){
            case R.id.btn_zero: em.zero(); break;
            case R.id.btn_one: em.one(); break;
            case R.id.btn_two: em.two(); break;
            case R.id.btn_three: em.three(); break;
            case R.id.btn_four: em.four(); break;
            case R.id.btn_five: em.five(); break;
            case R.id.btn_six: em.six(); break;
            case R.id.btn_seven: em.seven(); break;
            case R.id.btn_eight: em.eight(); break;
            case R.id.btn_nine: em.nine(); break;
            case R.id.btn_comma: em.comma(); break;
            case R.id.btn_plus: em.plus(); break;
            case R.id.btn_minus: em.minus(); break;
            case R.id.btn_multiply: em.multiply(); break;
            case R.id.btn_divide: em.divide(); break;
            case R.id.btn_bracket_open: em.bracketOpen(); break;
            case R.id.btn_bracket_close: em.bracketClose(); break;
            case R.id.btn_calculate: em.calculate(); break;
            case R.id.btn_delete: em.delete(); break;
            case R.id.btn_delete_all: em.deleteAll(); break;
            case R.id.btn_more: handleMore(); break;
        }
        tv_expression.setText(em.toExpressionString());
        tv_result.setText(em.toResultString());
    }

    private void handleMore(){

    }
}
