package com.devshyeon.numberbaseballgame2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    TextView result;
    Button button;
    EditText edit1;
    EditText edit2;
    EditText edit3;
    int com1,com2,com3;
    String s;
    String buf = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("숫자야구");

        Random rnd= new Random();
        do{
            com1=rnd.nextInt(10);
            com2=rnd.nextInt(10);
            com3=rnd.nextInt(10);
        }while(com1==com2 || com1==com3 || com2==com3);

        result=(TextView)findViewById(R.id.result);
        edit1=(EditText)findViewById(R.id.edit01);
        edit2=(EditText)findViewById(R.id.edit02);
        edit3=(EditText)findViewById(R.id.edit03);
        button = (Button)findViewById(R.id.button);

        s="";




        //텍스트뷰에 스크롤 적용하기
        result.setMovementMethod(new ScrollingMovementMethod());
    }


    public void reset(View view) {
        Random rnd= new Random();
        do{
            com1=rnd.nextInt(10);
            com2=rnd.nextInt(10);
            com3=rnd.nextInt(10);
        }while(com1==com2 || com1==com3 || com2==com3);

        String resettext="진행 상황";
        s="";
        result.setText(resettext);
        edit1.setText("");
        edit2.setText("");
        edit3.setText("");

    }

    public void check(View view) {
        String getEdit1 = edit1.getText().toString();
        String getEdit2 = edit2.getText().toString();
        String getEdit3 = edit3.getText().toString();
        getEdit1 = getEdit1.trim();
        getEdit2 = getEdit2.trim();
        getEdit3 = getEdit3.trim();

        if (getEdit1.getBytes().length <= 0||getEdit2.getBytes().length <= 0||getEdit3.getBytes().length <= 0) {
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).setMessage("숫자를 입력하세요").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }).create();

            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
        else {
            int n1 = Integer.parseInt(edit1.getText().toString());
            int n2 = Integer.parseInt(edit2.getText().toString());
            int n3 = Integer.parseInt(edit3.getText().toString());

            if(n1==n2||n2==n3||n1==n3){
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).setMessage("같은 수를 입력할 수 없습니다").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create();

                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }

            else {
                int strike = 0, ball = 0;
                buf = "";

                if (n1 == com1) strike++;
                else if (n1 == com2 || n1 == com3) ball++;

                if (n2 == com2) strike++;
                else if (n2 == com1 || n2 == com3) ball++;

                if (n3 == com3) strike++;
                else if (n3 == com1 || n3 == com2) ball++;
                buf = String.format(" %d %d %d  :     %d Strike, %d Ball\n", n1, n2, n3, strike, ball);
                s += buf;

                if (strike == 3) s += "정답입니다.";
                result.setText(s);
                edit1.setText("");
                edit2.setText("");
                edit3.setText("");
            }
        }

    }
}

