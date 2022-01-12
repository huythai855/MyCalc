package com.example.mycalc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class Menu extends AppCompatActivity {

    static SharedPreferences password;

    public static int evaluate(String expression)
    {
        char[] tokens = expression.toCharArray();

        Stack<Integer> values = new Stack<Integer>();

        Stack<Character> ops = new Stack<Character>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] >= '0' && tokens[i] <= '9') {
                StringBuffer sbuf = new StringBuffer();
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(Integer.parseInt(sbuf.toString()));
                i--;
            }

            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.push(tokens[i]);
            }
        }

        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));

        return values.pop();
    }

    public static boolean hasPrecedence(char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    public static int applyOp(char op, int b, int a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    return -2147483648;
                return a / b;
        }
        return 0;
    }
    
    Button b_add, b_sub, b_mul, b_div;
    Button b_1, b_2, b_3, b_4, b_5, b_6, b_7, b_8, b_9, b_0;
    Button b_ac, b_dot, b_del, b_equal;
    boolean isWritting = true;
    String function_now = "";
    String NUL = "";

    boolean isSign(char c){
        if(c == '+' || c == '-' || c == '*' || c == '/' || c == '.')
            return true;
        return false;
    }

    String GetResult(){
        int len = function_now.length();

        char FirstLetter = function_now.charAt(0);
        char LastLetter = function_now.charAt(len-1);

        if(isSign(FirstLetter) == true)
            return "Error!";
        if(isSign(LastLetter) == true)
            return "Error!";

        if(len == 1)
            return function_now;

        for(int i=0; i<=len-2; i++)
            if(isSign(function_now.charAt(i))==true && isSign((function_now.charAt(i+1)))==true)
                return "Syntax Error";

        int res = evaluate(function_now);

        String Res = String.valueOf(res);

        if(Res == "-2147483648")
            return "Error!";
        else
            return Res;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        b_add = findViewById(R.id.b_add);
        b_sub = findViewById(R.id.b_sub);
        b_mul = findViewById(R.id.b_mul);
        b_div = findViewById(R.id.b_div);
        b_7 = findViewById(R.id.b_num7);
        b_8 = findViewById(R.id.b_num8);
        b_9 = findViewById(R.id.b_num9);
        b_del = findViewById(R.id.b_del);
        b_4 = findViewById(R.id.b_num4);
        b_5 = findViewById(R.id.b_num5);
        b_6 = findViewById(R.id.b_num6);
        b_ac = findViewById(R.id.b_AC);
        b_1 = findViewById(R.id.b_num1);
        b_2 = findViewById(R.id.b_num2);
        b_3 = findViewById(R.id.b_num3);
        b_dot = findViewById(R.id.b_dot);
        b_0 = findViewById(R.id.b_num0);
        b_equal = findViewById(R.id.b_equal);

        TextView result = findViewById(R.id.tv_result);
        TextView function = findViewById(R.id.tv_function);
        TextView result2 = findViewById(R.id.tv_result2);
        TextView function2 = findViewById(R.id.tv_function2);
        TextView result3 = findViewById(R.id.tv_result3);
        TextView function3 = findViewById(R.id.tv_function3);

        result.setText(String.valueOf(NUL));
        function.setText(String.valueOf(NUL));
        result2.setText(String.valueOf(NUL));
        function2.setText(String.valueOf(NUL));
        result3.setText(String.valueOf(NUL));
        function3.setText(String.valueOf(NUL));

        /// TODO: Number button
        b_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isWritting){
                    function3.setText(function2.getText());
                    result3.setText(result2.getText());
                    function2.setText(function.getText());
                    result2.setText(result.getText());
                    function.setText(NUL);
                    result.setText("");
                    function_now = "";
                    isWritting = true;
                }
                function_now = function_now + "0";
                function.setText(function_now);
            }
        });

        b_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isWritting == false){
                    function3.setText(function2.getText());
                    result3.setText(result2.getText());
                    function2.setText(function.getText());
                    result2.setText(result.getText());
                    System.out.println("Try1");
                    function.setText("");
                    result.setText("");
                    function_now = "";
                    isWritting = true;
                }
                function_now = function_now + "1";
                function.setText(function_now);
            }
        });

        b_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isWritting == false){
                    function3.setText(function2.getText());
                    result3.setText(result2.getText());
                    function2.setText(function.getText());
                    result2.setText(result.getText());
                    function.setText("");
                    result.setText("");
                    function_now = "";
                    isWritting = true;
                }
                function_now = function_now + "2";
                function.setText(function_now);
            }
        });

        b_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isWritting == false){
                    function3.setText(function2.getText());
                    result3.setText(result2.getText());
                    function2.setText(function.getText());
                    result2.setText(result.getText());
                    function.setText("");
                    result.setText("");
                    function_now = "";
                    isWritting = true;
                }
                function_now = function_now + "3";
                function.setText(function_now);
            }
        });

        b_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isWritting == false){
                    function3.setText(function2.getText());
                    result3.setText(result2.getText());
                    function2.setText(function.getText());
                    result2.setText(result.getText());
                    function.setText("");
                    result.setText("");
                    function_now = "";
                    isWritting = true;
                }
                function_now = function_now + "4";
                function.setText(function_now);
            }
        });

        b_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isWritting == false){
                    function3.setText(function2.getText());
                    result3.setText(result2.getText());
                    function2.setText(function.getText());
                    result2.setText(result.getText());
                    function.setText("");
                    result.setText("");
                    function_now = "";
                    isWritting = true;
                }
                function_now = function_now + "5";
                function.setText(function_now);
            }
        });

        b_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isWritting == false){
                    function3.setText(function2.getText());
                    result3.setText(result2.getText());
                    function2.setText(function.getText());
                    result2.setText(result.getText());
                    function.setText("");
                    result.setText("");
                    function_now = "";
                    isWritting = true;
                }
                function_now = function_now + "6";
                function.setText(function_now);
            }
        });

        b_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isWritting == false){
                    function3.setText(function2.getText());
                    result3.setText(result2.getText());
                    function2.setText(function.getText());
                    result2.setText(result.getText());
                    function.setText("");
                    result.setText("");
                    function_now = "";
                    isWritting = true;
                }
                function_now = function_now + "7";
                function.setText(function_now);
            }
        });

        b_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isWritting == false){
                    function3.setText(function2.getText());
                    result3.setText(result2.getText());
                    function2.setText(function.getText());
                    result2.setText(result.getText());
                    function.setText("");
                    result.setText("");
                    function_now = "";
                    isWritting = true;
                }
                function_now = function_now + "8";
                function.setText(function_now);
            }
        });

        b_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isWritting == false){
                    function3.setText(function2.getText());
                    result3.setText(result2.getText());
                    function2.setText(function.getText());
                    result2.setText(result.getText());
                    function.setText("");
                    result.setText("");
                    function_now = "";
                    isWritting = true;
                }
                function_now = function_now + "9";
                function.setText(function_now);
            }
        });

        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isWritting == false){
                    function3.setText(function2.getText());
                    result3.setText(result2.getText());
                    function2.setText(function.getText());
                    result2.setText(result.getText());
                    function.setText("");
                    result.setText("");
                    function_now = "";
                    isWritting = true;
                }
                function_now = function_now + "+";
                function.setText(function_now);
            }
        });

        b_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isWritting == false){
                    function3.setText(function2.getText());
                    result3.setText(result2.getText());
                    function2.setText(function.getText());
                    result2.setText(result.getText());
                    function.setText("");
                    result.setText("");
                    function_now = "";
                    isWritting = true;
                }
                function_now = function_now + "-";
                function.setText(function_now);
            }
        });

        b_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isWritting == false){
                    function3.setText(function2.getText());
                    result3.setText(result2.getText());
                    function2.setText(function.getText());
                    result2.setText(result.getText());
                    function.setText("");
                    result.setText("");
                    function_now = "";
                    isWritting = true;
                }
                function_now = function_now + "*";
                function.setText(function_now);
            }
        });

        b_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isWritting == false){
                    function3.setText(function2.getText());
                    result3.setText(result2.getText());
                    function2.setText(function.getText());
                    result2.setText(result.getText());
                    function.setText("");
                    result.setText("");
                    function_now = "";
                    isWritting = true;
                }
                function_now = function_now + "/";
                function.setText(function_now);
            }
        });

        b_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isWritting == false){
                    function3.setText(function2.getText());
                    result3.setText(result2.getText());
                    function2.setText(function.getText());
                    result2.setText(result.getText());
                    function.setText("");
                    result.setText("");
                    function_now = "";
                    isWritting = true;
                }
                function_now = function_now + ".";
                function.setText(function_now);
            }
        });


        //TODO: Function buttons
        b_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("");
                function.setText("");
                result2.setText("");
                function2.setText("");
                result3.setText("");
                function3.setText("");
                isWritting = true;
                function_now = "";
            }
        });

        b_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWritting = false;

                password = getSharedPreferences("password", Context.MODE_PRIVATE);
                String Pass = password.getString("password", "25/12/2003");



                if(function_now.equals(Pass)){
                    Intent intent = new Intent(Menu.this, Main.class);
                    startActivity(intent);
                }
                else
                    result.setText(GetResult());
            }
        });

        b_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(function_now.equals(""))
                    return;
                int len = function_now.length();

                if(len == 1){
                    function_now = "";
                    function.setText(function_now);
                    return;
                }

                String temp = function_now.substring(0,len-1);
                function_now = temp;
                function.setText(function_now);
            }
        });




    }

}