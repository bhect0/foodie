package tfg.hector.foodie;

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

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import tfg.hector.foodie.apirest.model.Receta;

public class Pasos extends Fragment {

    private LinearLayout scrollView;
    LinearLayout layoutPasos;
    List<String> pasos = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment TODO: documentacion de para que sirve inflate
        View view = inflater.inflate(R.layout.pasos, container, false);

        layoutPasos = view.findViewById(R.id.layoutPasos);

        Button boton1 = view.findViewById(R.id.boton1);
        Button boton2 = view.findViewById(R.id.boton2);
        //boton1.setEnabled(true);
        //boton2.setEnabled(false);

        boton1.setBackgroundResource(R.drawable.boton_borde_superior);
        boton2.setBackgroundResource(R.drawable.boton_borde_superior);

        pintaPasos(Recetas.recetaris.get("Macarrones con salsa de tomate"));




        return view;
    }

    private void pintaPasos(Receta r) {
        for (String p : r.getPasos()) {
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

            TextView textView = new TextView(requireContext());
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