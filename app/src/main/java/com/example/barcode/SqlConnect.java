package com.example.barcode;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlConnect extends AppCompatActivity {

    private static String ip = "40.121.158.30";;
    private static String port = "11000";
    private static String classes ="net.sourceforge.jtds.jdbc.Driver";
    private static String database = "aceproject";
    private static String username = "ikjun";
    private static String password = "qwe123qwe12#";
    private static String url = "jdbc:jtds:sqlserver://"+ip+"/"+database;
    TextView result_view;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_connect);
        Intent intent = getIntent(); //

        button = findViewById((R.id.button2));
        result_view = (TextView) findViewById(R.id.textView2) ;
        Toast.makeText(this, "연결중...",Toast.LENGTH_LONG).show();

        button.setOnClickListener((View) -> {
            connect();
        });
    }

    public void connect(){
        ActivityCompat.requestPermissions(this, new String []{Manifest.permission.INTERNET},PackageManager.PERMISSION_GRANTED );
        Connection connection = null;

        try{ //mssql서버와 연결을 설정
            Class.forName(classes);
            connection = DriverManager.getConnection(url, username, password);
            Statement stmt = connection.createStatement();
            Toast.makeText(this, "연결 성공.", Toast.LENGTH_LONG).show();//연결 성공 메시지
            result_view.setText("연결 성공");
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            Toast.makeText(this, "연결 실패.", Toast.LENGTH_LONG).show();//연결 실패 메시지
            result_view.setText("연결 실패");
        }
    }
}