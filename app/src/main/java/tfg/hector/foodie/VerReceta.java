package tfg.hector.foodie;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import tfg.hector.foodie.apirest.model.Receta;

public class VerReceta extends FragmentActivity { // extends fragment
    private Ingredientes ingredientes;
    private Pasos pasos;

    private LinearLayout layoutPasos;
    private LinearLayout layoutIngredientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_receta);

        Button boton1 = findViewById(R.id.boton1);
        Button boton2 = findViewById(R.id.boton2);

        ingredientes = new Ingredientes();
        pasos = new Pasos();

        layoutPasos = findViewById(R.id.layoutPasos_vr);
        layoutIngredientes = findViewById(R.id.layoutIngredientes_vr);

        //getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_fragments_vr, ingredientes).commit();

        /*boton1.setOnClickListener(v -> cambiarFragment(ingredientes));
        boton2.setOnClickListener(v -> cambiarFragment(pasos));*/

        boton1.setOnClickListener(v -> pintaIngredientes(Recetas.recetaris.get("Macarrones con salsa de tomate")));
        boton2.setOnClickListener(v -> pintaPasos(Recetas.recetaris.get("Macarrones con salsa de tomate")));

        boton1.setBackgroundResource(R.drawable.boton_borde_superior);
        boton2.setBackgroundResource(R.drawable.boton_borde_superior);


    }
    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ver_receta, container, false);



        return view;
    }*/

    public void cambiarFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_fragments_vr, fragment).commit();
    }

    private void pintaIngredientes(Receta r) {
        layoutPasos.removeAllViews();
        for (String i : r.getIngredientes()) {
            CardView cardView = new CardView(getApplicationContext());
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            cardView.setLayoutParams(layoutParams);
            cardView.setCardElevation(getResources().getDimension(R.dimen.cardview_elevation));
            cardView.setRadius(getResources().getDimension(R.dimen.cardview_corner_radius));
            cardView.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            cardView.setMaxCardElevation(getResources().getDimension(R.dimen.cardview_max_elevation));
            cardView.setPreventCornerOverlap(true);
            cardView.setUseCompatPadding(true);

            LinearLayout linearLayout = new LinearLayout(getApplicationContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            /*ImageView imageView = new ImageView(requireContext());
            LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.image_height));
            imageLayoutParams.gravity = Gravity.CENTER;
            imageView.setLayoutParams(imageLayoutParams);
            //imageView.setImageResource(R.drawable.google);
            imageView.setContentDescription(getString(R.string.app_name));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);*/

            /*Picasso.get()
                    .load(i.getFoto())
                    .error(R.drawable.login_image)
                    .into(imageView);*/

            TextView textView = new TextView(getApplicationContext());
            LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textLayoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
            textLayoutParams.setMargins(getResources().getDimensionPixelSize(R.dimen.text_margin_left), getResources().getDimensionPixelSize(R.dimen.text_margin_top), getResources().getDimensionPixelSize(R.dimen.text_margin_right), getResources().getDimensionPixelSize(R.dimen.text_margin_bottom));
            textView.setLayoutParams(textLayoutParams);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimensionPixelSize(R.dimen.text_recipe_title_size));
            textView.setTypeface(null, Typeface.NORMAL);

            textView.setText(i);

            //linearLayout.addView(imageView);
            linearLayout.addView(textView);
            cardView.addView(linearLayout);

            layoutIngredientes.addView(cardView);
        }
    }

    private void pintaPasos(Receta r) {
        layoutIngredientes.removeAllViews();
        for (String p : r.getPasos()) {
            CardView cardView = new CardView(getApplicationContext());
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            cardView.setLayoutParams(layoutParams);
            cardView.setCardElevation(getResources().getDimension(R.dimen.cardview_elevation));
            cardView.setRadius(getResources().getDimension(R.dimen.cardview_corner_radius));
            cardView.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            cardView.setMaxCardElevation(getResources().getDimension(R.dimen.cardview_max_elevation));
            cardView.setPreventCornerOverlap(true);
            cardView.setUseCompatPadding(true);

            LinearLayout linearLayout = new LinearLayout(getApplicationContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            /*ImageView imageView = new ImageView(requireContext());
            LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.image_height));
            imageLayoutParams.gravity = Gravity.CENTER;
            imageView.setLayoutParams(imageLayoutParams);
            //imageView.setImageResource(R.drawable.google);
            imageView.setContentDescription(getString(R.string.app_name));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);*/

            /*Picasso.get()
                    .load(i.getFoto())
                    .error(R.drawable.login_image)
                    .into(imageView);*/

            TextView textView = new TextView(getApplicationContext());
            LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textLayoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
            textLayoutParams.setMargins(getResources().getDimensionPixelSize(R.dimen.text_margin_left), getResources().getDimensionPixelSize(R.dimen.text_margin_top), getResources().getDimensionPixelSize(R.dimen.text_margin_right), getResources().getDimensionPixelSize(R.dimen.text_margin_bottom));
            textView.setLayoutParams(textLayoutParams);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimensionPixelSize(R.dimen.text_recipe_title_size));
            textView.setTypeface(null, Typeface.NORMAL);

            textView.setText(p);

            //linearLayout.addView(imageView);
            linearLayout.addView(textView);
            cardView.addView(linearLayout);

            layoutPasos.addView(cardView);
        }
    }


}