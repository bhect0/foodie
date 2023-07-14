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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    String textoBusqueda;
    LinearLayout layoutRecetas;
    LinearLayout layoutIngredientes;
    static List<Receta> recetas = new ArrayList<>();
    static Map<String, Receta> recetaris;
    LinearLayout layoutSelec;
    LinearLayout layoutBTitulo;
    LinearLayout layoutBIngredientes;
    SearchView sv_titulo;
    SearchView sv_ingredientes;
    Button btn_nueva_consulta;
    Button btn_anade_ingrediente;
    TextView i_seleccion;
    TextView feedback;
    List<String> i_buscados = new ArrayList<>();

    public Recetas() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recetas, container, false);

        layoutRecetas = view.findViewById(R.id.layoutRecetas);
        layoutIngredientes = view.findViewById(R.id.layoutIngredientes);
        layoutSelec = view.findViewById(R.id.layoutSelec);
        layoutBTitulo = view.findViewById(R.id.layoutBTitulo);
        layoutBIngredientes = view.findViewById(R.id.layoutBIngredientes);
        sv_titulo = view.findViewById(R.id.search_titulo);
        sv_ingredientes = view.findViewById(R.id.search_ingredientes);
        btn_anade_ingrediente = view.findViewById(R.id.btn_anadir);
        btn_nueva_consulta = view.findViewById(R.id.btn_nueva_consulta);
        i_seleccion = view.findViewById(R.id.alimentos_seleccionados);
        feedback = view.findViewById(R.id.feedback);

        Button b1 = view.findViewById(R.id.boton_titulo);
        Button b2 = view.findViewById(R.id.boton_ingredientes);

        b1.setOnClickListener(v -> {
            b1.setTextColor(getResources().getColor(R.color.colorPrimario));
            b2.setTextColor(getResources().getColor(R.color.black));
            vistaTitulo();
        });
        b2.setOnClickListener(v -> {
            b2.setTextColor(getResources().getColor(R.color.colorPrimario));
            b1.setTextColor(getResources().getColor(R.color.black));
            vistaIngredientes();
        });

        b1.setBackgroundResource(R.drawable.boton_borde_inferior);
        b2.setBackgroundResource(R.drawable.boton_borde_inferior);

        btn_anade_ingrediente.setOnClickListener(v -> anade_ingrediente(sv_ingredientes.getQuery().toString().toLowerCase()));
        btn_nueva_consulta.setOnClickListener(v -> vistaIngredientes());
        ApiService as = Apis.getApiRecetas();
        getRecetas(as);


        sv_titulo.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String searchText) {
                if (searchText.length() > 3) {
                    textoBusqueda = searchText;
                    buscarPorTitulo();
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
                }
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });

        return view;
    }

    private void anade_ingrediente(String ingrediente) {
        feedback.setVisibility(View.GONE);
        if (ingrediente.length() > 2) {
            if (ingredienteValido(ingrediente)) {
                i_buscados.add(ingrediente.trim());
                buscarPorIngredientes(i_buscados);
                i_seleccion.setVisibility(View.VISIBLE);
                i_seleccion.append(" " + ingrediente);
                sv_ingredientes.clearFocus();
            } else {
                feedback.setVisibility(View.VISIBLE);
                feedback.setText(R.string.no_recetas);
            }

        } else {
            feedback.setVisibility(View.VISIBLE);
            feedback.setText(R.string.introduce_ingrediente);
        }
        sv_ingredientes.setQuery("", false);
    }

    private boolean ingredienteValido(String ingrediente) {
        for (Receta r : recetaris.values()) {
            List<String> iReceta = r.getIngredientesSeparados();
            if (iReceta.contains(ingrediente)) {
                return true;
            }
        }
        return false;
    }

    private void vistaIngredientes() {
        sv_ingredientes.onActionViewExpanded();
        i_seleccion.setVisibility(View.INVISIBLE);
        i_seleccion.setText("");
        sv_ingredientes.setQuery("", false);
        sv_ingredientes.clearFocus();
        layoutBIngredientes.setVisibility(View.VISIBLE);
        layoutBTitulo.setVisibility(View.GONE);
        layoutSelec.setVisibility(View.GONE);
        i_buscados.clear();
        layoutIngredientes.removeAllViews();
        feedback.setVisibility(View.GONE);
    }

    private void vistaTitulo() {
        sv_titulo.onActionViewExpanded();
        layoutBTitulo.setVisibility(View.VISIBLE);
        layoutBIngredientes.setVisibility(View.GONE);
        layoutSelec.setVisibility(View.GONE);
        //layoutSelec.setVisibility();
    }

    private void buscarPorTitulo() {
        String textoBusqueda = sv_titulo.getQuery().toString();
        layoutRecetas.removeAllViews();
        layoutIngredientes.removeAllViews();
        for (String clave : recetaris.keySet()) {
            if (clave.toLowerCase().contains(textoBusqueda.toLowerCase())) {
                pintaReceta(recetaris.get(clave), layoutRecetas);
            }
        }
    }

    private void buscarPorIngredientes(List<String> i_buscados) {
        boolean hay_recetas = false;
        layoutRecetas.removeAllViews();
        layoutIngredientes.removeAllViews();
        for (Receta r : recetaris.values()) {
            List<String> iReceta = r.getIngredientesSeparados();
            if (iReceta.containsAll(i_buscados)) {
                pintaReceta(r, layoutIngredientes);
                hay_recetas = true;
            }
        }
        if (!hay_recetas) {
            feedback.setVisibility(View.VISIBLE);
            feedback.setText(R.string.no_recetas);
        }
    }

    public void getRecetas(ApiService as) {
        Call<JsonArray> call = as.getData();
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
                        Receta r = new Receta(receta.getTitulo(),
                                receta.getDescripcion(),
                                receta.getFoto(),
                                receta.getPasos(),
                                receta.getIngredientes(),
                                receta.getTiempoEstimado());

                        recetaris.put(r.getTitulo(), r);

                    }
                    pintaRecetas(recetaris, layoutSelec);
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(getContext(), "No se ha podido establecer contacto con el servidor." +
                        " Inténtelo de nuevo más tarde", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void pintaRecetas(Map<String, Receta> recetas, LinearLayout layout) {
        for (Receta receta : recetas.values()) {
            pintaReceta(receta, layout);
        }
    }

    public void pintaReceta(Receta r, LinearLayout layout) {
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
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Picasso.get()
                .load(r.getFoto())
                .error(R.drawable.login_image)
                .into(imageView);

        TextView tvTitulo = new TextView(requireContext());
        LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams.gravity = Gravity.BOTTOM | Gravity.LEFT;
        textLayoutParams.setMargins(getResources().getDimensionPixelSize(R.dimen.text_margin_left), getResources().getDimensionPixelSize(R.dimen.text_margin_top), getResources().getDimensionPixelSize(R.dimen.text_margin_right), 0);
        tvTitulo.setLayoutParams(textLayoutParams);
        tvTitulo.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimensionPixelSize(R.dimen.text_recipe_title_size));
        tvTitulo.setTypeface(null, Typeface.BOLD);
        tvTitulo.setTextAppearance(requireContext(), R.style.EstiloPrincipalBold);
        tvTitulo.setText(r.getTitulo());

        TextView tvDesc = new TextView(requireContext());
        LinearLayout.LayoutParams textLayoutParams_d = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams_d.gravity = Gravity.BOTTOM | Gravity.LEFT;
        textLayoutParams_d.setMargins(getResources().getDimensionPixelSize(R.dimen.text_margin_left), getResources().getDimensionPixelSize(R.dimen.text_margin_top), getResources().getDimensionPixelSize(R.dimen.text_margin_right), 0);
        tvDesc.setLayoutParams(textLayoutParams_d);
        tvDesc.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimensionPixelSize(R.dimen.text_recipe_title_size));
        tvDesc.setTypeface(null, Typeface.NORMAL);
        tvDesc.setTextAppearance(requireContext(), R.style.EstiloPrincipal);
        tvDesc.setText(r.getDescripcion());

        TextView tvTE= new TextView(requireContext());
        LinearLayout.LayoutParams textLayoutParams_t = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams_t.gravity = Gravity.BOTTOM | Gravity.LEFT;
        textLayoutParams_t.setMargins(getResources().getDimensionPixelSize(R.dimen.text_margin_left), getResources().getDimensionPixelSize(R.dimen.text_margin_top), getResources().getDimensionPixelSize(R.dimen.text_margin_right), getResources().getDimensionPixelSize(R.dimen.text_margin_bottom));
        tvTE.setLayoutParams(textLayoutParams_t);
        tvTE.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimensionPixelSize(R.dimen.text_recipe_title_size));
        tvTE.setTypeface(null, Typeface.ITALIC);
        tvTE.setTextAppearance(requireContext(), R.style.EstiloPrincipalItalic);
        tvTE.setText(r.getTiempoEstimado());

        linearLayout.addView(imageView);
        linearLayout.addView(tvTitulo);
        linearLayout.addView(tvDesc);
        linearLayout.addView(tvTE);
        cardView.addView(linearLayout);

        layout.addView(cardView);
    }

}