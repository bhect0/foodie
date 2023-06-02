package tfg.hector.foodie;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tfg.hector.foodie.apirest.model.Receta;
import tfg.hector.foodie.apirest.utils.ApiService;
import tfg.hector.foodie.apirest.utils.Apis;

public class Recetas extends Fragment {

    private LinearLayout scrollView;
    TextView text;
    LinearLayout layoutRecetas;
    List<Receta> recetas = new ArrayList<>();
    Map<String, Receta> recetaris;

    SearchView sv;
    Button btn_buscar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment TODO: documentacion de para que sirve inflate
        View view = inflater.inflate(R.layout.recetas, container, false);

        layoutRecetas = view.findViewById(R.id.layoutRecetas);
        sv = view.findViewById(R.id.search_view);
        btn_buscar = view.findViewById(R.id.btn_buscar);

        ApiService as = Apis.getApiRecetas();
        TextView tc_debug = view.findViewById(R.id.debug);

        //tc_debug.setText(recetas.get(2).getNombre());

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {
                // Verificar si el SearchView está vacío
                if (newText.length() > 0) {
                    // El SearchView contiene texto, habilitar el botón
                    btn_buscar.setEnabled(true);
                } else {

                    btn_buscar.setEnabled(false);
                }
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });

        btn_buscar.setOnClickListener(v -> {
            getRecetas(tc_debug, as);
            //pintaRecetas(buscarRecetaPorNombre("macarrones",recetaris));



        });






        return view;
    }

    private void agregarReceta(Receta receta, Map<String, Receta> recetas) {
        recetaris.put(receta.getNombre(), receta);
    }

    private static Map<String, Receta> buscarRecetaPorNombre(String nombre, Map<String, Receta> recetas) {
        String nombreBuscado = nombre.toLowerCase();
        Map<String, Receta> recetasResult = null;

        for (Map.Entry<String, Receta> receta : recetas.entrySet()) {
            String nombreReceta = receta.getKey().toLowerCase();
            if (nombreReceta.contains(nombreBuscado)) {
                recetasResult.put(receta.getValue().getNombre(), (Receta) receta);
            }
        }
        return recetasResult;
    }

    private static List<Receta> buscarRecetasPorIngrediente(String ingrediente, Map<String, Receta> recetas) {
        List<Receta> recetasEncontradas = new ArrayList<>();

        for (Receta receta : recetas.values()) {
            //if (receta.getIngredientes().contains(ingrediente)) {
             //   recetasEncontradas.add(receta);
            //}
        }

        return recetasEncontradas;
    }


    public Map<String,Receta> getRecetas(TextView tc_debug, ApiService as) {
        Call<JsonArray> call = as.getData();
        //recetaris = new ArrayList<>();
        recetaris = new HashMap<>();
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    JsonArray data = response.body().getAsJsonArray();
                    Type recetaListType = new TypeToken<List<Receta>>() {}.getType();
                    recetas.addAll(gson.fromJson(data, recetaListType));

                    for (Receta receta : recetas) {
                        recetaris.put(receta.getNombre(), receta);
                    }

                    //actualizaMapa(recetaris);
                    pintaRecetas(recetaris);

                } else {
                    tc_debug.setText("!isSuccessful");
                    // error
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                tc_debug.setText(t.getMessage());
            }
        });

        //devuelve_recetas(response);

        return recetaris;
    }

    private void actualizaMapa(Map<String,Receta> recetaris) {
        //this.recetaris = recetaris;
        //pintaRecetas(recetaris);
    }

    private void pintaRecetas(Map<String,Receta> recetas) {
        recetaris = recetas;
        for (Receta receta : recetas.values()) {
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
                    .load(receta.getUrlImagen())
                    .error(R.drawable.login_image)
                    .into(imageView);

            TextView textView = new TextView(requireContext());
            LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textLayoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
            textLayoutParams.setMargins(getResources().getDimensionPixelSize(R.dimen.text_margin_left), getResources().getDimensionPixelSize(R.dimen.text_margin_top), getResources().getDimensionPixelSize(R.dimen.text_margin_right), getResources().getDimensionPixelSize(R.dimen.text_margin_bottom));
            textView.setLayoutParams(textLayoutParams);
            textView.setText(getString(R.string.app_name));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimensionPixelSize(R.dimen.text_recipe_title_size));
            textView.setTypeface(null, Typeface.BOLD);

            textView.setText(receta.getNombre());

            linearLayout.addView(imageView);
            linearLayout.addView(textView);
            cardView.addView(linearLayout);

            layoutRecetas.addView(cardView);
        }
    }

}