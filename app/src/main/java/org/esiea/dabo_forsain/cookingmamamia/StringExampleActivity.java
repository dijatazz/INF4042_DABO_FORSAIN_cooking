package org.esiea.dabo_forsain.cookingmamamia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.Button;

public class StringExampleActivity extends AppCompatActivity {
    Button button = null;
    String hist = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //on récupère notre ressource au format String
        hist = getResources().getString(R.string.histoire);
        //on convertit en Spanned
        Spanned marked_up = Html.fromHtml(hist);

        button = new Button(this);
        //et on attribue le Spanned au bouton
        button.setText();
        setContentView(button);
    }
}
