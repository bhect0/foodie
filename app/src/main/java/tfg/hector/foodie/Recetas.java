package tfg.hector.foodie;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tfg.hector.foodie.apirest.model.Receta;
import tfg.hector.foodie.apirest.utils.ApiService;
import tfg.hector.foodie.apirest.utils.Apis;

public class Recetas extends Fragment {

    private LinearLayout scrollView;
    TextView text;
    GridLayout gridLayout;
    List<Receta> recetas = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment TODO: documentacion de para que sirve inflate
        View view = inflater.inflate(R.layout.recetas, container, false);

        //this.scrollView = view.findViewById(R.id.recetas_scroll);
        this.text = new TextView(getContext());
        gridLayout = view.findViewById(R.id.gridLayout);

        ApiService as = Apis.getApiRecetas();
        TextView tc_debug = view.findViewById(R.id.debug);
        recetas = getRecetas(tc_debug, as);

        // Llamar al método para agregar las recetas al GridLayout
        //agregarRecetas(recetas);
        /*TextView textView = new TextView(requireContext());
        textView.setText("ddddd");
        gridLayout.addView(textView);*/

        //tc_debug.setText(recetas.get(0).getNombre());







        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.hectorsnb.com/foodie/recetas.json/") // ip de mi pc
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService as = retrofit.create(ApiService.class);*/



        //pintaReceta();

        return view;
    }




    private List<Receta> getRecetas(TextView tc_debug, ApiService as) {
        Call<JsonArray> call = as.getData();
        List<Receta> recetas = new ArrayList<>();
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful()) {
                   // String data = response.toString();
                    //JsonArray data = response.body().getAsJsonArray();
                    //tc_debug.setText("data: " + data);
                    //Gson gson = new Gson();
                    //Receta listaRecetas = gson.fromJson(data.toString(), Receta.class);
                    //Type listaTipo = new TypeToken<List<Receta>>() {};
                    //List<Receta> listaRecetas = gson.fromJson(data.toString(), listaTipo);

                    //TypeToken<Collection<Receta>> collectionType = new TypeToken<Collection<Receta>>(){};
// Note: For older Gson versions it is necessary to use `collectionType.getType()` as argument below,
// this is however not type-safe and care must be taken to specify the correct type for the local variable
                    //Collection<Integer> ints2 = gson.fromJson((JsonElement) data, (Type) collectionType);

                    //Type r = new TypeToken<Receta>() {}.getType();
                    //Receta receta = gson.fromJson(data.toString(), r);


                    Gson gson = new Gson();
                    JsonArray data = response.body().getAsJsonArray();
                    Type recetaListType = new TypeToken<List<Receta>>() {}.getType();
                    recetas.addAll(gson.fromJson(data, recetaListType));


                    pintaRecetas(recetas);


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

        return recetas;
    }

    private void pintaRecetas(List<Receta> recetas) {
        for (Receta receta : recetas) {
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
            LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.image_width), getResources().getDimensionPixelSize(R.dimen.image_height));
            imageLayoutParams.gravity = Gravity.CENTER;
            imageView.setLayoutParams(imageLayoutParams);
            //imageView.setImageResource(R.drawable.google);
            imageView.setContentDescription(getString(R.string.app_name));

            Picasso.get()
                    .load(receta.getUrlImagen()) // Aquí debes usar la URL de la imagen de la receta
                    .placeholder(R.drawable.google) // Opcional: Imagen de relleno mientras se carga la imagen
                    .error(R.drawable.login_image) // Opcional: Imagen de error si no se puede cargar la imagen
                    .into(imageView);

            TextView textView = new TextView(requireContext());
            LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textLayoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
            textLayoutParams.setMargins(0, getResources().getDimensionPixelSize(R.dimen.text_margin_top), 0, getResources().getDimensionPixelSize(R.dimen.text_margin_bottom));
            textView.setLayoutParams(textLayoutParams);
            textView.setText(getString(R.string.app_name));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimensionPixelSize(R.dimen.text_size));
            textView.setTypeface(null, Typeface.NORMAL);

            textView.setText(receta.getNombre());

            linearLayout.addView(imageView);
            linearLayout.addView(textView);
            cardView.addView(linearLayout);
            gridLayout.addView(cardView);
        }
    }

}