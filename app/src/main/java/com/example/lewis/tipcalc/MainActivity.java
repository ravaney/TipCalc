package com.example.lewis.tipcalc;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    int def=0;
    //private int div=0;
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
    int radioid;
    public static final String EXTRA_NUM = "com.example.lewis.tipcalc";
    public static final String EXTRA_NUM2 = "com.example.lewis.tipcalc";
    public static final String EXTRA_NUM3 = "com.example.lewis.tipcalc";
    public static final String EXTRA_TEXT = "com.example.lewis.tipcalc";


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.share_menu,menu);
        return true;
    }

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
                checkedId=radioid;
                if(checkedId==0){
                  // finalamnt=amount+tipamnt;

                   // splitID.setText("helloo1");

                    //updater();

                }
                else if(checkedId==1){
                    //total is completed
                    finalamnt=555;
                    //splitID.setText("helloo2");
                    updater();


                }
                else if(checkedId==2){
                    //for tip
                    tipamnt=Math.ceil(tipamnt/100);
                    finalamnt=amount+tipamnt;
                   // splitID.setText("helloo3");

                    updater();

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
                total.setText(String.format("Total bill due: %.2f",finalamnt));
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
    public boolean onOptionsItemSelected(MenuItem item) {
        //final String message = (String.format("The bill was : %.2f $",+amount+", the tip was %.2f :$",+tipamnt+"and the total bill came up to %.2f :$",+finalamnt));

        switch (item.getItemId()){
            case R.id.info:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("About Spinners");
                alert.setMessage("The Spinner is a drop down menu, that is used to display a lit of selectable items.\n" +
                        "In the case of our tipCalc app, it was implemented as a method to split a bill between a group of friends.\n" +
                        "A number is selected from the list and the bill then divided by that number");
                alert.create().show();
                break;
            case R.id.share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,"The bill came up to $"+amount+"\nThe" +
                        " tip was: $"+tipamnt+"\nThe total came up to: $"+finalamnt+"\nEach person will pay: $"+finalamnt/def);

                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void infoDialog() {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

        switch (position){
            case 0: //div=1;
            def=1;
            splitID.setText(String.format("You will pay: %.2f",+finalamnt));
                 break;
            case 1: //div=2;
            def=2;
            splitID.setText(String.format("Each person pays: %.2f",+finalamnt/2));
                break;
            case 2: //div=3;
            def=3;
            splitID.setText(String.format("Each person pays %.2f",+finalamnt/3));
                break;
            case 3: //div=4;
            def=4;
            splitID.setText(String.format("Each person pays %.2f",+finalamnt/4));
                break;
            case 4: //div=5;
            def=5;
            splitID.setText(String.format("Each person pays %.2f",+finalamnt/5));
                break;
        }

    }

    public void updater(){//updates the screen for each person when the slider is drawn
        switch (def){
            case 1: //div=1;
                //def=1;
                splitID.setText(String.format("You will pay: %.2f",+finalamnt));
                break;
            case 2: //div=2;
               // def=2;
                splitID.setText(String.format("Each person pays: %.2f",+finalamnt/2));
                break;
            case 3: //div=3;
               // def=3;
                splitID.setText(String.format("Each person pays %.2f",+finalamnt/3));
                break;
            case 4: //div=4;
               // def=4;
                splitID.setText(String.format("Each person pays %.2f",+finalamnt/4));
                break;
            case 5: //div=5;
               // def=5;
                splitID.setText(String.format("Each person pays %.2f",+finalamnt/5));
                break;
        }
    }

    public void checkID(View v){
        int radioid = radiogroup.getCheckedRadioButtonId();

        No = findViewById(radioid);
        String getname = (String) No.getText();

        switch (getname){
            case "No": if(getname.equalsIgnoreCase("No")){
                radioid=0;
            }
                break;

            case "radioTip": if(getname.equalsIgnoreCase("radioTip")){
                radioid=2;
            }
                break;

            case "radioTotal": if(getname.equalsIgnoreCase("radioTotal")){
                radioid=1;
            }
                break;
        }



    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
