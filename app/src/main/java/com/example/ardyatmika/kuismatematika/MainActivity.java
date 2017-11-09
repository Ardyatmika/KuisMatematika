package com.example.ardyatmika.kuismatematika;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText angka1, angka2, operator, inputJawaban;
    TextView jawabanBenar, jawabanSalah, hasilJawab;
    Button cekJawaban;
    int value1, value2, hasil, jawabBenar, jawabSalah;
    String getJawab, randOpr;

    SharedPref classShardPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        classShardPref = new SharedPref(MainActivity.this.getApplicationContext());

        angka1 = (EditText) findViewById(R.id.angka1);
        angka2 = (EditText) findViewById(R.id.angka2);
        operator = (EditText) findViewById(R.id.operator);
        inputJawaban = (EditText) findViewById(R.id.inputJawaban);
        jawabanBenar = (TextView) findViewById(R.id.jawabanBenar);
        jawabanSalah = (TextView) findViewById(R.id.jawabanSalah);
        hasilJawab = (TextView) findViewById(R.id.hasilJawaban);
        cekJawaban = (Button) findViewById(R.id.cekJawaban);

        this.generateSoal(findViewById(R.id.buatSoal));

        //GetFromPreferences
        jawabBenar = classShardPref.getNilaiBenar();
        jawabSalah = classShardPref.getNilaiSalah();
        jawabanBenar.setText(String.valueOf(classShardPref.getNilaiBenar()) + " Jawaban Benar");
        jawabanSalah.setText(String.valueOf(classShardPref.getNilaiSalah()) + " Jawaban Salah");

    }

    public void generateSoal(View view) {
        cekJawaban.setEnabled(true);
        inputJawaban.setText("");
        randOpr = randOprtr();
        operator.setText(randOpr);
        value1 = (int) (Math.random() * 100 + 1);
        value2 = (int) (Math.random() * 100 + 1);
        angka1.setText(String.valueOf(value1));
        angka2.setText(String.valueOf(value2));
    }

    public void cekJawaban(View view) {
        String cekIfKosong = inputJawaban.getText().toString();

        if (cekIfKosong.isEmpty()) {
            hasilJawab.setText("Jawaban Belum Diisi");
            hasilJawab.setBackgroundColor(0xFFFF99FF);
        } else {

            cekJawaban.setEnabled(false);

            //Random Operator
            if (randOpr == "+") {
                hasil = value1 + value2;
            } else if (randOpr == "-") {
                hasil = value1 - value2;
            } else if (randOpr == "*") {
                hasil = value1 * value2;
            } else if (randOpr == "/") {
                hasil = value1 / value2;
            }
            //Random Operator

            getJawab = inputJawaban.getText().toString();
            if (getJawab.equals(String.valueOf(hasil))) {
                jawabBenar = jawabBenar + 1;
                jawabanBenar.setText(String.valueOf(jawabBenar) + " Jawaban Benar");
                hasilJawab.setText("Jawaban: " + String.valueOf(hasil) + "\nJawaban Anda Benar");
                classShardPref.setNilaiBenar(jawabBenar);
                hasilJawab.setBackgroundColor(0xFF00FF00);
            } else {
                jawabSalah = jawabSalah + 1;
                jawabanSalah.setText(String.valueOf(jawabSalah) + " Jawaban Salah");
                hasilJawab.setText("Jawaban: " + String.valueOf(hasil) + "\nJawaban Anda Salah");
                classShardPref.setNilaiSalah(jawabSalah);
                hasilJawab.setBackgroundColor(0xFFFF0000);
            }
        }
    }


    public String randOprtr() {
        randOpr = "x";
        int rand = (int) (Math.random() * 4 + 1);
        switch (rand) {
            case 1:
                randOpr = "+";
                break;
            case 2:
                randOpr = "-";
                break;
            case 3:
                randOpr = "*";
                break;
            case 4:
                randOpr = "/";
                break;
        }
        return randOpr;
    }

}
