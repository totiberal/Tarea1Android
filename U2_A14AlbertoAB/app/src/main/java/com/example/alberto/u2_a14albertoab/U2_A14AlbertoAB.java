package com.example.alberto.u2_a14albertoab;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class U2_A14AlbertoAB extends Activity {

    private EditText textoArriba;
    private CheckBox checkBox;
    private Button boton;
    private TextView texto;
    private RadioButton rbRojo;
    private RadioButton rbAzul;
    private ImageView imaxe;
    private  Spinner provSpinner;
    private Chronometer contar;
    private Switch onOff;
    long tempoAutodestrucion;
    private RadioGroup rbGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u2__a14_alberto_ab);
        textoArriba= (EditText) findViewById(R.id.idTexto);
        checkBox= (CheckBox) findViewById(R.id.checkBoton);
        boton= (Button) findViewById(R.id.cambiarBoton);
        texto= (TextView) findViewById(R.id.oculto);
        rbRojo= (RadioButton) findViewById(R.id.colorRoxo);
        rbAzul= (RadioButton) findViewById(R.id.colorAzul);
        imaxe= (ImageView) findViewById(R.id.miCasa);
        provSpinner= (Spinner) findViewById(R.id.spinnerProvincias);
        contar= (Chronometer) findViewById(R.id.cronometro);
        onOff = (Switch) findViewById(R.id.idSwitch);
        rbGrupo= (RadioGroup) findViewById(R.id.rgId);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkBox.isChecked()) {
                    texto.setText("");
                    textoArriba.setText("");
                } else {
                    texto.append(" "+textoArriba.getText().toString());
                    textoArriba.setText("");
                }
            }
        });

        provSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            Toast tostada1;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==4) {
                    tostada1 = Toast.makeText(getBaseContext(), R.string.tostada3, Toast.LENGTH_SHORT);
                    tostada1.show();
                } else {
                    tostada1 = Toast.makeText(getBaseContext(), R.string.tostada2, Toast.LENGTH_SHORT);
                    tostada1.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rbGrupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rbRojo.isChecked()) texto.setTextColor(getResources().getColor(R.color.rojo));
                if(rbAzul.isChecked()) texto.setTextColor(getResources().getColor(R.color.azul));
            }
        });

        onOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    tempoAutodestrucion = 15;
                    contar.setBase(SystemClock.elapsedRealtime());
                    contar.start();

                } else {
                    contar.stop();
                    contar.setBase(SystemClock.elapsedRealtime());
                }
            }
        });

        contar.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long tempoPasado = SystemClock.elapsedRealtime()
                        - chronometer.getBase();
                int tempoSeg = (int) tempoPasado / 1000;
                if (tempoSeg == tempoAutodestrucion)
                    finish();
            }
        });

    }

    public void clickImaxe(View view){
        ImageView img=(ImageView) view;
        Toast.makeText(getBaseContext(), img.getTag().toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_u2__a14_alberto_ab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
