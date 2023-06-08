package tfg.hector.foodie;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
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
import com.squareup.picasso.Picasso;

import tfg.hector.foodie.apirest.model.Receta;

public class VerReceta extends FragmentActivity { // extends fragment
    public static Receta receta;
    private Ingredientes ingredientes;
    private Pasos pasos;
    private ImageView iv_rimagen;
    private TextView tv_nombre;
    private TextView tv_descripcion;
    private TextView tv_tiempo_estimado;

    private LinearLayout layoutPasos;
    private static LinearLayout layoutIngredientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_receta);

        Intent i = getIntent();
        receta = (Receta) i.getSerializableExtra("receta");

        Button boton1 = findViewById(R.id.boton1);
        Button boton2 = findViewById(R.id.boton2);

        ingredientes = new Ingredientes();
        pasos = new Pasos();

        layoutPasos = findViewById(R.id.layoutPasos_vr);
        layoutIngredientes = findViewById(R.id.layoutIngredientes_vr);

        iv_rimagen = findViewById(R.id.iv_rimagen);
        tv_nombre = findViewById(R.id.tv_nombre);
        tv_descripcion = findViewById(R.id.tv_descripcion);
        tv_tiempo_estimado = findViewById(R.id.tv_tiempo_estimado);

        LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.image_height));
        imageLayoutParams.gravity = Gravity.CENTER;
        iv_rimagen.setLayoutParams(imageLayoutParams);
        iv_rimagen.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.get()
            .load(receta.getFoto())
            .error(R.drawable.login_image)
            .into(iv_rimagen);

        LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams.gravity = Gravity.BOTTOM | Gravity.LEFT;
        textLayoutParams.setMargins(getResources().getDimensionPixelSize(R.dimen.text_margin_left), getResources().getDimensionPixelSize(R.dimen.text_margin_top), getResources().getDimensionPixelSize(R.dimen.text_margin_right), getResources().getDimensionPixelSize(R.dimen.text_margin_bottom));

        tv_nombre.setLayoutParams(textLayoutParams);
        tv_nombre.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimensionPixelSize(R.dimen.text_recipe_title_size));
        tv_nombre.setTypeface(null, Typeface.BOLD);
        tv_nombre.setText(receta.getTitulo());

        tv_descripcion.setLayoutParams(textLayoutParams);
        tv_descripcion.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimensionPixelSize(R.dimen.text_recipe_title_size));
        tv_descripcion.setTypeface(null, Typeface.NORMAL);
        tv_descripcion.setText(receta.getDescripcion());

        tv_descripcion.setLayoutParams(textLayoutParams);
        tv_descripcion.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimensionPixelSize(R.dimen.text_recipe_title_size));
        tv_descripcion.setTypeface(null, Typeface.ITALIC);
        tv_descripcion.setText(receta.getTiempoEstimado());

        boton1.setOnClickListener(v -> {
            boton1.setTextColor(getResources().getColor(R.color.colorPrimario));
            boton2.setTextColor(getResources().getColor(R.color.black));
            pintaIngredientes(receta);
        });
        boton2.setOnClickListener(v -> {
            boton2.setTextColor(getResources().getColor(R.color.colorPrimario));
            boton1.setTextColor(getResources().getColor(R.color.black));
            pintaPasos(receta);
        });

        boton1.setBackgroundResource(R.drawable.boton_borde_inferior);
        boton2.setBackgroundResource(R.drawable.boton_borde_inferior);

    }

    public void pintaIngredientes(Receta r) {
        layoutPasos.removeAllViews();
        for (String i : r.getIngredientes()) {
            Log.d("i: ", i);
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
            textLayoutParams.gravity = Gravity.BOTTOM | Gravity.LEFT;
            textLayoutParams.setMargins(10, 10, 10, 10);
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

    public void pintaPasos(Receta r) {
        int c = 0;
        layoutIngredientes.removeAllViews();
        for (String p : r.getPasos()) {
            c++;
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

            TextView textView = new TextView(getApplicationContext());
            LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textLayoutParams.gravity = Gravity.BOTTOM | Gravity.LEFT;
            textLayoutParams.setMargins(10, 10, 10, 10);
            textView.setLayoutParams(textLayoutParams);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimensionPixelSize(R.dimen.text_recipe_title_size));
            textView.setTypeface(null, Typeface.NORMAL);
            String pp = "Paso " + c + " : " + p;
            textView.setText(pp);

            linearLayout.addView(textView);
            cardView.addView(linearLayout);

            layoutPasos.addView(cardView);
        }
    }


}