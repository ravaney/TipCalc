package com.example.lewis.tipcalc;

import android.content.Intent;
import android.content.IntentSender;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText bill;
    private Button submit,email;
    private TextView tip,total,pbt;
    private SeekBar seekBar;
    private ProgressBar progBar;
    private double amount = 0.0;
    private int tipMax = 30;
    private int tipMin = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        total = (TextView)findViewById(R.id.total);
        bill = (EditText) findViewById(R.id.bill);
        tip = (TextView) findViewById(R.id.tip);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        pbt = (TextView) findViewById(R.id.pbt);
        progBar = (ProgressBar) findViewById(R.id.progBar);
        submit = (Button) findViewById(R.id.submit);
        email = (Button) findViewById(R.id.email);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,"Thanks for your having dinner at On the Rocks");
                sendIntent.setType("text/plain");

            }
        });

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                amount = Double.parseDouble(bill.getText().toString());
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progBar.setProgress(progress);
                pbt.setText(progress +"%");
                tip.setText("Tip : $ "+(progress*amount)/100);
                total.setText("Total bill due: $"+(amount+(progress*amount)/100));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });





    }


}
