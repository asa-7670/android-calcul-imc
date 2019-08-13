package com.asa.calculimc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button calculImcBtnView;
    private Button razBtnView;

    private EditText poidsEdtView;
    private EditText tailleEdtView;

    private RadioGroup typeRdgView;

    private TextView resultatTxView;

    private CheckBox megaCbView;

    private final String megaString = "Vous faites un poids parfait ! Wahou ! Trop fort ! On dirait Brad Pitt (si vous êtes un homme)/Angelina Jolie (si vous êtes une femme)/Willy (si vous êtes un orque) !";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        
        init ();


    }

    private void init() {
        calculImcBtnView =(Button) findViewById ( R.id.calcul_btn_view );
        calculImcBtnView.setOnClickListener ( calculImcListener );

        razBtnView = (Button) findViewById ( R.id.raz_btn_view );
        razBtnView.setOnClickListener ( razListener );

        poidsEdtView = (EditText) findViewById ( R.id.poids_edt_view );
        poidsEdtView.addTextChangedListener ( textWatcher );

        tailleEdtView = (EditText) findViewById ( R.id.taille_edt_view );
        tailleEdtView.addTextChangedListener ( textWatcher );

        typeRdgView = (RadioGroup) findViewById ( R.id.type_rg_view );

        megaCbView = (CheckBox) findViewById ( R.id.mega_function_cb_view );
        megaCbView.setOnClickListener ( checkedListener );

        resultatTxView = (TextView) findViewById ( R.id.info_label_view );

    }

    private View.OnClickListener calculImcListener = new View.OnClickListener () {
        @Override
        public void onClick(View view) {
            if(!megaCbView.isChecked ()){
                String t = tailleEdtView.getText ().toString ();

                float tValue = t.isEmpty ()? Float.valueOf ( 0 ) : Float.valueOf ( t );
                if(tValue == 0) {
                    Toast.makeText ( MainActivity.this, "Hého, tu est Minus ou quoi", Toast.LENGTH_SHORT ).show ();
                }else{
                    String p = poidsEdtView.getText ().toString ();
                    float pValue = p.isEmpty ()? 0: Float.valueOf ( p );
                    if(typeRdgView.getCheckedRadioButtonId () == R.id.centimetre_rb_view){
                        tValue = tValue /100;
                    }
                    tValue = (float) Math.pow (tValue,2);
                    float imc = pValue /tValue;
                    resultatTxView.setText ( "Votre IMC est :" + String.valueOf ( imc ) );
                }
            }else {
                resultatTxView.setText ( megaString );
            }
        }
    };

    private View.OnClickListener razListener = new View.OnClickListener () {
        @Override
        public void onClick(View view) {
            poidsEdtView.getText ().clear ();
            tailleEdtView.getText ().clear ();
            resultatTxView.setText ( getString ( R.string.info_label ) );
        }
    };

    private View.OnClickListener checkedListener = new View.OnClickListener () {
        @Override
        public void onClick(View view) {
           poidsEdtView.getText ().clear ();
           tailleEdtView.getText ().clear ();
           resultatTxView.setText ( getString ( R.string.info_label ));
        }
    };

    private TextWatcher textWatcher = new TextWatcher (){


        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            resultatTxView.setText ( getString ( R.string.info_label ) );
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
