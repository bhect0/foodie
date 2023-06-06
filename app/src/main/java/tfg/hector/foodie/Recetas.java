package tfg.hector.foodie;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

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
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tfg.hector.foodie.apirest.model.Receta;
import tfg.hector.foodie.apirest.utils.ApiService;
import tfg.hector.foodie.apirest.utils.Apis;

public class Recetas extends Fragment {

    private LinearLayout scrollView;
    TextView searchText;
    String textoBusqueda;
    LinearLayout layoutRecetas;
    static List<Receta> recetas = new ArrayList<>();
    static Map<String, Receta> recetaris;

    LinearLayout layoutBTitulo;
    LinearLayout layoutBIngredientes;

    SearchView sv_titulo;
    SearchView sv_ingredientes;
    Button btn_buscar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment TODO: documentacion de para que sirve inflate
        View view = inflater.inflate(R.layout.recetas, container, false);

        layoutRecetas = view.findViewById(R.id.layoutRecetas);
        layoutBTitulo = view.findViewById(R.id.layoutBTitulo);
        layoutBIngredientes = view.findViewById(R.id.layoutBIngredientes);
        sv_titulo = view.findViewById(R.id.search_titulo);
        sv_ingredientes = view.findViewById(R.id.search_ingredientes);

        Button b1 = view.findViewById(R.id.boton_titulo);
        Button b2 = view.findViewById(R.id.boton_ingredientes);

        b1.setOnClickListener(v -> vistaTitulo());
        b2.setOnClickListener(v -> vistaIngredientes());

        b1.setBackgroundResource(R.drawable.boton_borde_inferior);
        b2.setBackgroundResource(R.drawable.boton_borde_inferior);

        ApiService as = Apis.getApiRecetas();
        getRecetas(as);
        //TextView tc_debug = view.findViewById(R.id.debug);

        List<String> ingredientes = new ArrayList<>();
        String[] ingredienteu = new String[2];
        for (Receta r : recetaris.values()) {
            for (String i : r.getIngredientes()){
                ingredientes.add(i);
            }
        }
        /*Curso<String> suggestionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ingredientes);
        sv_ingredientes.setSuggestionsAdapter(suggestionAdapter);*/
        /*@Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.options_menu, menu);

            // Associate searchable configuration with the SearchView
            SearchManager searchManager =
                    (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView searchView =
                    (SearchView) menu.findItem(R.id.search).getActionView();
            searchView.setSearchableInfo(
                    searchManager.getSearchableInfo(getComponentName()));

            return true;
        }

        sv_ingredientes.createContextMenu(*/

        sv_titulo.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String searchText) {
                if (searchText.length() > 3) {
                    textoBusqueda = searchText;
                    buscarPorTitulo();
                    Log.d("Titulo:", searchText);
                }
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });

        sv_ingredientes.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String searchText) {
                if (searchText.length() > 3) {
                    textoBusqueda = searchText;
                    buscarPorIngredientes();
                    Log.d("Ingrediente:", searchText);
                }
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });

        //btn_buscar.setOnClickListener(v -> {
        //    realizarBusqueda();
        //});



        return view;
    }

    private void vistaIngredientes() {
        layoutBIngredientes.setVisibility(View.VISIBLE);
        layoutBTitulo.setVisibility(View.GONE);
    }

    private void vistaTitulo() {
        layoutBTitulo.setVisibility(View.VISIBLE);
        layoutBIngredientes.setVisibility(View.GONE);
    }

    private void buscarPorTitulo() {
        String textoBusqueda = sv_titulo.getQuery().toString();
        buscaTitulo(textoBusqueda);
    }

    private void buscarPorIngredientes() {
        String textoBusqueda = sv_titulo.getQuery().toString();
        buscaTitulo(textoBusqueda);
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


    public Map<String,Receta> getRecetas(ApiService as) {
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
                        Receta r = new Receta(receta.getTitulo(), receta.getDescripcion(), receta.getFoto(), receta.getPasos(), receta.getIngredientes(), receta.getTiempoEstimado());
                        recetaris.put(r.getTitulo(), r);
                    }


                    //buscaTitulo("r");

                    //actualizaMapa(recetaris);
                    //pintaRecetas(recetaris.containsKey("Macarrones"));

                } else {
                    // TODO
                    // error
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                // TODO
            }
        });

        return recetaris;
    }

    private void buscaTitulo(String titulo) {
        layoutRecetas.removeAllViews();
        Log.d("Keyset:", recetaris.toString());
        for (String clave : recetaris.keySet()) {
            Log.d("Clave:", clave);
            if (clave.toLowerCase().contains(titulo.toLowerCase())) {
                Log.d("Tituloo:", titulo);
                pintaReceta(recetaris.get(clave));
            }
        }
    }

    //List<Receta> recetas;
    private void pintaRecetas(Map<String, Receta> recetas) {
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
                    .load(receta.getFoto())
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

            textView.setText(receta.getTitulo());

            linearLayout.addView(imageView);
            linearLayout.addView(textView);
            cardView.addView(linearLayout);

            layoutRecetas.addView(cardView);
        }
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
        cardView.setOnClickListener(v -> {
            Intent i = new Intent(requireContext(), VerReceta.class);
            i.putExtra("receta", r);
            startActivity(i);
        });

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

        TextView textView = new TextView(requireContext());
        LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        textLayoutParams.setMargins(getResources().getDimensionPixelSize(R.dimen.text_margin_left), getResources().getDimensionPixelSize(R.dimen.text_margin_top), getResources().getDimensionPixelSize(R.dimen.text_margin_right), getResources().getDimensionPixelSize(R.dimen.text_margin_bottom));
        textView.setLayoutParams(textLayoutParams);
        textView.setText(getString(R.string.app_name));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimensionPixelSize(R.dimen.text_recipe_title_size));
        textView.setTypeface(null, Typeface.BOLD);

        textView.setText(r.getTitulo());

        linearLayout.addView(imageView);
        linearLayout.addView(textView);
        cardView.addView(linearLayout);

        layoutRecetas.addView(cardView);
    }

}