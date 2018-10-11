package com.example.lewis.tipcalc;

import android.content.Intent;
import android.content.IntentSender;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText bill;
    int def=1;
    private int div;
    private Button submit,email;
    private Toolbar toolbar;
    private TextView tip,total,pbt,splitID;
    private SeekBar seekBar;
    private ProgressBar progBar;
    private double amount = 0.0;
    private double tipamnt,finalamnt;
    private int tipMax = 30;
    private int tipMin = 0;
    private RadioGroup radiogroup;
    private RadioButton No,radioTip,radioTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        total = (TextView)findViewById(R.id.total);
        splitID = findViewById(R.id.splitID);
        bill = (EditText) findViewById(R.id.bill);
        tip = (TextView) findViewById(R.id.tip);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        pbt = (TextView) findViewById(R.id.pbt);
        progBar = (ProgressBar) findViewById(R.id.progBar);
        submit = (Button) findViewById(R.id.submit);
        email = (Button) findViewById(R.id.email);
        radiogroup = findViewById(R.id.radiogroup);
        radioTip = findViewById(R.id.radioTip);
        radioTotal = findViewById(R.id.radioTotal);
        No = findViewById(R.id.No);

         toolbar = findViewById(R.id.toolbar);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.numbers,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==0){
                    tipamnt=tipamnt;
                    splitID=splitID;
                    finalamnt=amount+tipamnt;
                    //finalamnt=Math.round(finalamnt/10)*10;

                }
                else if(checkedId==1){
                    //total is completed
                    finalamnt=Math.ceil(finalamnt/100);


                }
                else{
                    //for tip
                    tipamnt=Math.ceil(tipamnt/100);
                    finalamnt=amount+tipamnt;



                }
            }
        });

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
                tipamnt = (progress*amount)/100;
                tip.setText("Tip : $ "+(progress*amount)/100);
                finalamnt=(amount+tipamnt);
                total.setText("Total bill due: $"+(amount+(progress*amount)/100));
                //splitID.setText("You will pay "+finalamnt);
                updater();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });





    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

        switch (position){
            case 0: div=1;
            def=1;
           // splitID.setText("You will pay "+finalamnt);
              //  total.setText("Total due $"+finalamnt);
                 break;
            case 1: div=2;
            def=2;
            //splitID.setText("Person 1 pays "+finalamnt/2+"\nPerson 2 pays"+finalamnt/2);
                break;
            case 2: div=3;
            def=3;
           // splitID.setText("Person 1 pays "+finalamnt/3+"\nPerson 2 pays "+finalamnt/3+"\nPerson 3 pays "+finalamnt/3);

                break;

            case 3: div=4;
            case 4: div=5;
            break;
        }

    }

    public void updater(){//updates the screen for each person when the slider is drawn
            if(def==1){
                splitID.setText("You Pay $"+finalamnt);
            }
            else if(def==2){
                //finalamnt=Math.ceil(finalamnt/100)*100;
                splitID.setText("Person 1 pays $"+finalamnt/2+"\nPerson 2 pays $"+finalamnt/2);
            }
            else if(def==3){
                splitID.setText("Person 1 pays $"+finalamnt/3+"\nPerson 2 pays $"+finalamnt/3+"\nPerson 3 pays $"+finalamnt/3);

            }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
