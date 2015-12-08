package org.esiea.dabo_forsain.cookingmamamia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.DialogInterface;
import android.view.View.OnKeyListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.RadioGroup;
import android.widget.Button;
import android.view.MotionEvent;
import android.view.KeyEvent;


public class TroimsActivity extends AppCompatActivity  {
    //la chaine de caractères par défaut
    private final String defaut="Vous devez cliquer sur le bouton 'Calculer l'IMC' pour obtenir un résultat.";
    //la chaine de caractères de la megafonction
    private final String megaString = "Vous faites un poids parfait ! Wahou ! Trop fort ! On dirait Brad Pitt " +
            "(si vous êtes un homme)/Angelina Jolie (si vous êtes une femme)/Willy (si vous êtes un orque) !";

    Button envoyer = null;
    Button raz = null;

    EditText poids = null;
    EditText taille = null;

    RadioGroup group = null;

    TextView result = null;

    CheckBox mega = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_troims);

        //On récupère toutes les vues dont on a besoin
        envoyer = (Button)findViewById(R.id.calcul);

        raz = (Button)findViewById(R.id.raz);

        taille = (EditText)findViewById(R.id.taille);
        poids = (EditText)findViewById(R.id.poids);

        mega = (CheckBox)findViewById(R.id.mega);

        group = (RadioGroup)findViewById(R.id.group);

        result = (TextView)findViewById(R.id.result);

        //On attribue un listener adapté aux vues qui en ont besoin
        envoyer.setOnClickListener(envoyerListener);
        raz.setOnClickListener(razListener);
        taille.addTextChangedListener(textWatcher);
        poids.addTextChangedListener(textWatcher);


        //Solution avec des onKey
        taille.setOnKeyListener(modificationListener);
        poids.setOnKeyListener(modificationListener);
        mega.setOnClickListener(checkedListener);

    }


  // Se lance à chaque fois qu'on appuie sur une touche en étant sur un EditText
    private OnKeyListener modificationListener = new OnKeyListener() {
      @Override
      public boolean onKey(View v, int keyCode, KeyEvent event) {
          result.setText(defaut);
          return false;
      }
  };
  private TextWatcher textWatcher = new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        result.setText(defaut);
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
  };
    //Uniquement pour le bouton "envoyer"
    private OnClickListener envoyerListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("TAG", "onClickListener");
            if(!mega.isChecked()) {
                //Si la megafonction n'est pas activée
                //on récupére la taille
                String t = taille.getText().toString();
                //On récupère le poids
                String p = poids.getText().toString();

                float tValue = Float.valueOf(t);

                //puis on vérifie que la taille est cohérente
                if(tValue==0) {
                    Toast.makeText(TroimsActivity.this ,"Hého, tu es un minipouce ou quoi?",
                Toast.LENGTH_SHORT).show();
                }
                else {
                    float pValue = Float.valueOf(p);
                    //si l'utilisateur a indiqué que la taille était en centimètre
                    //on vérifie que la Checkbox sélectionnée est la deuxième à l'aide de son identifiant
                    if (group.getCheckedRadioButtonId() == R.id.radio2)
                        tValue = tValue/100;

                    tValue = (float)Math.pow(tValue, 2);
                    float imc = pValue/tValue;
                    result.setText("Votre IMC est " +String.valueOf(imc));
                }
            } else
                result.setText(megaString);
        }
    };

    //Listener du bouton de remise à zéro
    private OnClickListener razListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            poids.getText().clear();
            taille.getText().clear();
            result.setText(defaut);
        }
    };

    //listener du bouton de la megafonction
    private OnClickListener checkedListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            //On remet le texte par défaut si c'était le texte de la megafonction qui était écrit
            if(!((CheckBox)v).isChecked() && result.getText().equals(megaString))
                result.setText(defaut);
        }
    };

}
