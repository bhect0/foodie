package tfg.hector.foodie;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.Random;
import java.util.Set;

import tfg.hector.foodie.apirest.model.Receta;

public class Match extends Fragment {

    LinearLayout layoutMatch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match, container, false);

        layoutMatch = view.findViewById(R.id.layoutMatch);

        pintaReceta(Recetas.recetaris.get("Macarrones con salsa de tomate"));



        return view;
    }

    private Receta siguienteReceta() {
        layoutMatch.removeAllViews();
        Set<String> keys = Recetas.recetaris.keySet();
        String[] keysArray = keys.toArray(new String[0]);
        Random random = new Random();
        int randomIndex = random.nextInt(keysArray.length);
        return Recetas.recetaris.get(keysArray[randomIndex]);
    }


    private void pintaReceta(Receta r) {
        CardView cardView = new CardView(requireContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cardView.setLayoutParams(layoutParams);

        cardView.setCardElevation(getResources().getDimension(R.dimen.cardview_elevation));
        cardView.setRadius(getResources().getDimension(R.dimen.cardview_corner_radius));
        cardView.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white));
        cardView.setMaxCardElevation(getResources().getDimension(R.dimen.cardview_max_elevation));
        cardView.setPreventCornerOverlap(true);
        cardView.setUseCompatPadding(true);

        LinearLayout linearLayout = new LinearLayout(requireContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        ImageView imageView = new ImageView(requireContext());
        LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.image_height));
        imageLayoutParams.gravity = Gravity.CENTER;
        imageView.setLayoutParams(imageLayoutParams);
        //imageView.setImageResource(R.drawable.google);
        imageView.setContentDescription(getString(R.string.app_name));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Picasso.get()
                .load(r.getFoto())
                .error(R.drawable.login_image)
                .into(imageView);

        TextView tvTitulo = new TextView(requireContext());
        LinearLayout.LayoutParams tlpTitulo = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tlpTitulo.gravity = Gravity.BOTTOM | Gravity.LEFT;
        tlpTitulo.setMargins(getResources().getDimensionPixelSize(R.dimen.text_margin_left), getResources().getDimensionPixelSize(R.dimen.text_margin_top), getResources().getDimensionPixelSize(R.dimen.text_margin_right), getResources().getDimensionPixelSize(R.dimen.text_margin_bottom));
        tvTitulo.setLayoutParams(tlpTitulo);
        tvTitulo.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimensionPixelSize(R.dimen.text_recipe_title_size));
        tvTitulo.setTypeface(null, Typeface.BOLD);
        tvTitulo.setText(r.getTitulo());

        TextView tvDescripcion = new TextView(requireContext());
        LinearLayout.LayoutParams tlpDescripcion = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tlpDescripcion.gravity = Gravity.BOTTOM | Gravity.LEFT;
        tlpDescripcion.setMargins(getResources().getDimensionPixelSize(R.dimen.text_margin_left), getResources().getDimensionPixelSize(R.dimen.text_margin_top), getResources().getDimensionPixelSize(R.dimen.text_margin_right), getResources().getDimensionPixelSize(R.dimen.text_margin_none));
        tvDescripcion.setLayoutParams(tlpDescripcion);
        tvDescripcion.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimensionPixelSize(R.dimen.text_recipe_title_size));
        tvDescripcion.setTypeface(null, Typeface.NORMAL);
        tvDescripcion.setText(r.getDescripcion());

        TextView tvTiempo = new TextView(requireContext());
        LinearLayout.LayoutParams tlpTiempo = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tlpTiempo.gravity = Gravity.BOTTOM | Gravity.LEFT;
        tlpTiempo.setMargins(getResources().getDimensionPixelSize(R.dimen.text_margin_left), getResources().getDimensionPixelSize(R.dimen.text_margin_top), getResources().getDimensionPixelSize(R.dimen.text_margin_right), getResources().getDimensionPixelSize(R.dimen.text_margin_none));
        tvTiempo.setLayoutParams(tlpTiempo);
        tvTiempo.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimensionPixelSize(R.dimen.text_recipe_title_size));
        tvTiempo.setTypeface(null, Typeface.ITALIC);
        tvTiempo.setText(r.getTiempoEstimado());

        LinearLayout linearBtn = new LinearLayout(requireContext());
        linearBtn.setOrientation(LinearLayout.HORIZONTAL);
        linearBtn.setGravity(1);
        //linearBtn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.image_height)));

        ImageButton cancelarBtn = new ImageButton(requireContext());
        //cancelarBtn.setLayoutParams(new ViewGroup.LayoutParams(getResources().getDimensionPixelSize(R.dimen.image_width), getResources().getDimensionPixelSize(R.dimen.image_height)));
        cancelarBtn.setImageResource(R.drawable.no);
        cancelarBtn.setBackgroundColor(Color.WHITE);
        cancelarBtn.setOnClickListener(v -> pintaReceta(siguienteReceta()));

        ImageButton aceptarBtn = new ImageButton(requireContext());
        //aceptarBtn.setLayoutParams(new ViewGroup.LayoutParams(getResources().getDimensionPixelSize(R.dimen.image_width), getResources().getDimensionPixelSize(R.dimen.image_height)));
        aceptarBtn.setImageResource(R.drawable.si);
        aceptarBtn.setBackgroundColor(Color.WHITE);
        aceptarBtn.setOnClickListener(v -> {
            Intent i = new Intent(requireContext(), VerReceta.class);
            startActivity(i);
        }); // Aplicacion.boton4.performClick()

        linearLayout.addView(imageView);
        linearLayout.addView(tvTitulo);
        linearLayout.addView(tvDescripcion);
        linearLayout.addView(tvTiempo);
        linearLayout.addView(linearBtn);

        linearBtn.addView(cancelarBtn);
        linearBtn.addView(aceptarBtn);

        cardView.addView(linearLayout);

        layoutMatch.addView(cardView);
    }




}