package com.example.barcode;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SqlConnect extends AppCompatActivity {

    Button bt;
    TextView res;
    BackgroundTask dt;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_connect);

        bt = findViewById(R.id.button_res);
        res = (TextView)findViewById(R.id.textview_res);
        dt = new BackgroundTask();

        try {
            res.setText("");
            bt.setOnClickListener(view -> {
                dt.execute();
                String temp = dt.result;
                res.setText(temp);

            });
        }catch(Exception e){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            System.out.println(e.getMessage());
        }
    }

    //모든회원에 대한 정보를 가져오기 위한 쓰레드
    static class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;
        String result;

        @Override
        protected void onPreExecute() {
            //List.php은 파싱으로 가져올 웹페이지
            target = "http://192.168.55.169/getjson.php";
        }

        @Override
        protected String doInBackground(Void... voids) {

            try{
                URL url = new URL("http://192.168.55.169/get_Barcode.php");//URL 객체 생성

                //URL을 이용해서 웹페이지에 연결하는 부분
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                //바이트단위 입력스트림 생성 소스는 httpURLConnection
                InputStream inputStream = httpURLConnection.getInputStream();

                //웹페이지 출력물을 버퍼로 받음 버퍼로 하면 속도가 더 빨라짐
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;

                //문자열 처리를 더 빠르게 하기 위해 StringBuilder클래스를 사용함
                StringBuilder stringBuilder = new StringBuilder();

                //한줄씩 읽어서 stringBuilder에 저장함
                while((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp + "\n");//stringBuilder에 넣어줌
                }

                //사용했던 것도 다 닫아줌
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();//trim은 앞뒤의 공백을 제거함

            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println(result);
            this.result = result;
        }

    }

}


