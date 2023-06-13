package tfg.hector.foodie;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import tfg.hector.foodie.apirest.model.Receta;

public class VerReceta extends FragmentActivity { // extends fragment
    public static Receta receta;

    private LinearLayout layoutPasos;
    private ListView lv_ingredientes;
    private ListView lv_pasos;
    private LinearLayout layoutIngredientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_receta);

        Intent i = getIntent();
        receta = (Receta) i.getSerializableExtra("receta");

        Button boton1 = findViewById(R.id.boton1);
        Button boton2 = findViewById(R.id.boton2);

        boton1.setBackgroundResource(R.drawable.boton_borde_inferior);
        boton2.setBackgroundResource(R.drawable.boton_borde_inferior);

        boton1.performClick();

        layoutPasos = findViewById(R.id.layoutPasos_vr);
        layoutIngredientes = findViewById(R.id.layoutIngredientes_vr);
        lv_ingredientes = findViewById(R.id.lv_ingredientes);
        lv_pasos = findViewById(R.id.lv_pasos);

        ImageView iv_rimagen = findViewById(R.id.iv_rimagen);
        TextView tv_nombre = findViewById(R.id.tv_nombre);
        TextView tv_descripcion = findViewById(R.id.tv_descripcion);
        TextView tv_tiempo_estimado = findViewById(R.id.tv_tiempo_estimado);

        LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.image_height));
        imageLayoutParams.gravity = Gravity.CENTER;
        iv_rimagen.setLayoutParams(imageLayoutParams);
        iv_rimagen.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.get()
            .load(receta.getFoto())
            .error(R.drawable.login_image)
            .into(iv_rimagen);

        LinearLayout.LayoutParams textLayoutParams_1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams_1.gravity = Gravity.BOTTOM | Gravity.LEFT;
        textLayoutParams_1.setMargins(getResources().getDimensionPixelSize(R.dimen.text_margin_left), getResources().getDimensionPixelSize(R.dimen.text_margin_top), getResources().getDimensionPixelSize(R.dimen.text_margin_right), getResources().getDimensionPixelSize(R.dimen.text_margin_right));
        tv_nombre.setLayoutParams(textLayoutParams_1);
        tv_nombre.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimensionPixelSize(R.dimen.text_recipe_title_size));
        tv_nombre.setTypeface(null, Typeface.BOLD);
        tv_nombre.setText(receta.getTitulo());

        LinearLayout.LayoutParams textLayoutParams_2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams_2.gravity = Gravity.BOTTOM | Gravity.LEFT;
        textLayoutParams_2.setMargins(getResources().getDimensionPixelSize(R.dimen.text_margin_left), 0, getResources().getDimensionPixelSize(R.dimen.text_margin_right), 0);
        tv_descripcion.setLayoutParams(textLayoutParams_2);
        tv_descripcion.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimensionPixelSize(R.dimen.text_recipe_title_size));
        tv_descripcion.setTypeface(null, Typeface.NORMAL);
        tv_descripcion.setText(receta.getDescripcion());

        LinearLayout.LayoutParams textLayoutParams_3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams_3.gravity = Gravity.BOTTOM | Gravity.LEFT;
        textLayoutParams_3.setMargins(getResources().getDimensionPixelSize(R.dimen.text_margin_left), getResources().getDimensionPixelSize(R.dimen.text_margin_top), getResources().getDimensionPixelSize(R.dimen.text_margin_right), getResources().getDimensionPixelSize(R.dimen.text_margin_bottom));
        tv_tiempo_estimado.setLayoutParams(textLayoutParams_3);
        tv_tiempo_estimado.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimensionPixelSize(R.dimen.text_recipe_title_size));
        tv_tiempo_estimado.setTypeface(null, Typeface.ITALIC);
        tv_tiempo_estimado.setText(receta.getTiempoEstimado());

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

    }

    public void pintaIngredientes(Receta r) {
        List<String> ingredientes = new ArrayList<>();
        layoutPasos.setVisibility(View.GONE);
        layoutIngredientes.setVisibility(View.VISIBLE);
        for (String i : r.getIngredientes()) {
            ingredientes.add(i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, ingredientes);
        lv_ingredientes.setAdapter(adapter);
    }

    public void pintaPasos(Receta r) {
        int c = 1;
        List<String> pasos = new ArrayList<>();
        layoutPasos.setVisibility(View.VISIBLE);
        layoutIngredientes.setVisibility(View.GONE);
        for (String paso : r.getPasos()) {
            String p = "Paso " + c + " : " + paso;
            pasos.add(p);
            c++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, pasos);
        lv_pasos.setAdapter(adapter);
    }


}