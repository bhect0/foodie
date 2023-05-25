package tfg.hector.foodie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tfg.hector.foodie.apirest.model.Receta;
import tfg.hector.foodie.apirest.utils.ApiService;
import tfg.hector.foodie.apirest.utils.Apis;

public class Recetas extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment TODO: documentacion de para que sirve inflate
        View view = inflater.inflate(R.layout.recetas, container, false);

        TextView tc_debug = view.findViewById(R.id.debug);

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.hectorsnb.com/foodie/recetas.json/") // ip de mi pc
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService as = retrofit.create(ApiService.class);*/

        ApiService as = Apis.getApiRecetas();
        getRecetas(tc_debug, as);


        return view;
    }

    private void getRecetas(TextView tc_debug, ApiService as) {
        Call<JsonArray> call = as.getData();
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful()) {
                   // String data = response.toString();
                    JsonArray data = response.body().getAsJsonArray();
                    tc_debug.setText("data: " + data);
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

                    //String json = "[{ \"titulo\": \"Pasta con salsa de tomate\", \"descripcion\": \"Un plato clásico de pasta con una deliciosa salsa de tomate fresco.\", \"foto\": \"https://example.com/pasta.jpg\", \"pasos\": [ \"Cocine la pasta en agua hirviendo con sal según las instrucciones del paquete.\", \"Mientras tanto, en una sartén, caliente aceite de oliva y saltee ajo y cebolla picados.\", \"Agregue tomates picados, sal y pimienta negra. Cocine a fuego medio-bajo durante unos 20 minutos, revolviendo ocasionalmente.\", \"Agregue la pasta cocida y mezcle bien con la salsa.\", \"Sirva caliente con queso parmesano rallado y hojas de albahaca fresca.\" ], \"ingredientes\": [ \"500 gramos de pasta seca\", \"2 cucharadas de aceite de oliva\", \"3 dientes de ajo picados\", \"1 cebolla picada\", \"4 tomates maduros picados\", \"Sal y pimienta negra al gusto\", \"Queso parmesano rallado y hojas de albahaca fresca para servir\" ], \"tiempo_estimado\": \"30 minutos\" }, { \"titulo\": \"Omelette de champiñones\", \"descripcion\": \"Un delicioso omelette hecho con champiñones frescos.\", \"foto\": \"https://example.com/omelette.jpg\", \"pasos\": [ \"En una sartén, caliente aceite de oliva y saltee los champiñones hasta que estén dorados.\", \"En un tazón, bata los huevos y agregue sal y pimienta al gusto.\", \"Vierta los huevos batidos sobre los champiñones en la sartén caliente.\", \"Cocine a fuego medio hasta que los huevos estén firmes, volteando una vez.\", \"Sirva caliente con una pizca de perejil fresco.\" ], \"ingredientes\": [ \"4 huevos\", \"100 gramos de champiñones\", \"Sal y pimienta al gusto\", \"Aceite de oliva para cocinar\", \"Perejil fresco para decorar\" ], \"tiempo_estimado\": \"15 minutos\" }]";
                    String json = "[\n" +
                            "    {\n" +
                            "    \"titulo\": \"Pasta con salsa de tomate\",\n" +
                            "    \"descripcion\": \"Un plato clásico de pasta con una deliciosa salsa de tomate fresco.\",\n" +
                            "      \"foto\": \"https://example.com/pasta.jpg\",\n" +
                            "      \"pasos\": [\n" +
                            "        \"Cocine la pasta en agua hirviendo con sal según las instrucciones del paquete.\",\n" +
                            "        \"Mientras tanto, en una sartén, caliente aceite de oliva y saltee ajo y cebolla picados.\",\n" +
                            "        \"Agregue tomates picados, sal y pimienta negra. Cocine a fuego medio-bajo durante unos 20 minutos, revolviendo ocasionalmente.\",\n" +
                            "        \"Agregue la pasta cocida y mezcle bien con la salsa.\",\n" +
                            "        \"Sirva caliente con queso parmesano rallado y hojas de albahaca fresca.\"\n" +
                            "      ],\n" +
                            "      \"ingredientes\": [\n" +
                            "        \"500 gramos de pasta seca\",\n" +
                            "        \"2 cucharadas de aceite de oliva\",\n" +
                            "        \"3 dientes de ajo picados\",\n" +
                            "        \"1 cebolla picada\",\n" +
                            "        \"4 tomates maduros picados\",\n" +
                            "        \"Sal y pimienta negra al gusto\",\n" +
                            "        \"Queso parmesano rallado y hojas de albahaca fresca para servir\"\n" +
                            "      ],\n" +
                            "      \"tiempo_estimado\": \"30 minutos\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "    \"titulo\": \"Ensalada César de Pollo\",\n" +
                            "    \"descripcion\": \"Una ensalada clásica de pollo con aderezo César casero.\",\n" +
                            "    \"foto\": \"https://example.com/ensalada-cesar-pollo.jpg\",\n" +
                            "    \"pasos\": [\n" +
                            "        \"Cocina las pechugas de pollo a la parrilla o en una sartén hasta que estén completamente cocidas.\",\n" +
                            "        \"Corta las pechugas de pollo en tiras.\",\n" +
                            "        \"Coloca la lechuga en un tazón grande para ensalada.\",\n" +
                            "        \"Agrega el pollo, el aderezo César casero, el queso parmesano rallado y los croutones.\",\n" +
                            "        \"Mezcla bien.\",\n" +
                            "        \"Sirve y disfruta.\"\n" +
                            "    ],\n" +
                            "    \"ingredientes\": [\n" +
                            "        \"2 pechugas de pollo sin hueso y sin piel\",\n" +
                            "        \"4 tazas de lechuga romana picada\",\n" +
                            "        \"1/2 taza de aderezo César casero\",\n" +
                            "        \"1/4 taza de queso parmesano rallado\",\n" +
                            "        \"1 taza de croutones\"\n" +
                            "    ],\n" +
                            "    \"tiempo_estimado\": \"30 minutos\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "      \"titulo\": \"Ensalada de quinoa\",\n" +
                            "      \"descripcion\": \"Una ensalada vegetariana y saludable hecha con quinoa, vegetales frescos y una vinagreta de limón.\",\n" +
                            "      \"foto\": \"https://example.com/ensalada.jpg\",\n" +
                            "      \"pasos\": [\n" +
                            "        \"Enjuague 1 taza de quinoa en agua fría y colóquela en una olla con 2 tazas de agua. Lleve a ebullición, luego reduzca el fuego a bajo y cocine tapado durante unos 15 minutos hasta que el agua se haya absorbido y la quinoa esté tierna.\",\n" +
                            "        \"Mientras tanto, pique 1 pepino, 1 pimiento rojo y 1 cebolla roja en cubos pequeños.\",\n" +
                            "        \"En un tazón pequeño, mezcle 1/4 de taza de aceite de oliva, 2 cucharadas de jugo de limón, 1 cucharadita de miel, sal y pimienta al gusto.\",\n" +
                            "        \"Combine la quinoa cocida con los vegetales picados y la vinagreta de limón.\",\n" +
                            "        \"Refrigere durante al menos 30 minutos antes de servir.\"\n" +
                            "      ],\n" +
                            "      \"ingredientes\": [\n" +
                            "        \"1 taza de quinoa\",\n" +
                            "        \"2 tazas de agua\",\n" +
                            "        \"1 pepino\",\n" +
                            "        \"1 pimiento rojo\",\n" +
                            "        \"1 cebolla roja\",\n" +
                            "        \"1/4 de taza de aceite de oliva\",\n" +
                            "        \"2 cucharadas de jugo de limón\",\n" +
                            "        \"1 cucharadita de miel\",\n" +
                            "        \"Sal y pimienta al gusto\"\n" +
                            "      ],\n" +
                            "      \"tiempo_estimado\": \"45 minutos\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "    \"titulo\": \"Spaghetti Carbonara\",\n" +
                            "    \"descripcion\": \"Un plato de pasta clásico y cremoso con tocino, huevo y queso.\",\n" +
                            "    \"foto\": \"https://example.com/spaghetti-carbonara.jpg\",\n" +
                            "    \"pasos\": [\n" +
                            "        \"Cocina el spaghetti en una olla grande de agua con sal hasta que esté al dente.\",\n" +
                            "        \"Mientras tanto, cocina el tocino en una sartén grande hasta que esté crujiente.\",\n" +
                            "        \"Retira el tocino de la sartén y déjalo escurrir sobre papel toalla.\",\n" +
                            "        \"En un tazón pequeño, bate las yemas de huevo, el queso parmesano rallado, la crema espesa, la sal y la pimienta.\",\n" +
                            "        \"Cuando el spaghetti esté cocido, escúrrelo y vuelve a colocarlo en la olla.\",\n" +
                            "        \"Agrega el tocino a la olla y mezcla bien.\",\n" +
                            "        \"Agrega la mezcla de huevo y queso a la olla y mezcla bien.\",\n" +
                            "        \"Cocina a fuego medio durante unos 2-3 minutos o hasta que la salsa esté caliente y ligeramente espesa.\",\n" +
                            "        \"Sirve caliente y disfruta.\"\n" +
                            "    ],\n" +
                            "    \"ingredientes\": [\n" +
                            "        \"1 libra de spaghetti\",\n" +
                            "        \"4 rebanadas de tocino\",\n" +
                            "        \"4 yemas de huevo\",\n" +
                            "        \"1 taza de queso parmesano rallado\",\n" +
                            "        \"1/2 taza de crema espesa\",\n" +
                            "        \"1/2 cucharadita de sal\",\n" +
                            "        \"1/2 cucharadita de pimienta negra molida\"\n" +
                            "    ],\n" +
                            "    \"tiempo_estimado\": \"25 minutos\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "    \"titulo\": \"Tacos de Pescado\",\n" +
                            "    \"descripcion\": \"Unos deliciosos tacos de pescado con una mezcla de especias picantes y un aderezo de cilantro.\",\n" +
                            "    \"foto\": \"https://example.com/tacos-pescado.jpg\",\n" +
                            "    \"pasos\": [\n" +
                            "        \"En un plato poco profundo, mezcla la harina, el comino molido, el chile en polvo, el ajo en polvo, la sal y la pimienta negra molida.\",\n" +
                            "        \"Pasa los filetes de pescado por la mezcla de harina, asegurándote de que estén bien cubiertos por ambos lados.\",\n" +
                            "        \"En una sartén grande, calienta el aceite de oliva a fuego medio-alto.\",\n" +
                            "        \"Agrega los filetes de pescado y cocina durante 3-4 minutos por cada lado o hasta que estén dorados y completamente cocidos.\",\n" +
                            "        \"Retira el pescado de la sartén y déjalo escurrir sobre papel toalla.\",\n" +
                            "        \"Calienta las tortillas de maíz en una sartén o en el microondas.\",\n" +
                            "        \"Para armar los tacos, coloca un poco de repollo rallado en cada tortilla.\",\n" +
                            "        \"Coloca un filete de pescado en la parte superior del repollo.\",\n" +
                            "        \"Agrega una cucharada de salsa de tomate, una cucharada de crema agria y un poco de cilantro fresco.\",\n" +
                            "        \"Exprime un poco de jugo de lima sobre cada taco.\",\n" +
                            "        \"Sirve y disfruta.\"\n" +
                            "    ],\n" +
                            "    \"ingredientes\": [\n" +
                            "        \"1 libra de filetes de pescado blanco (como la tilapia o el mahi-mahi)\",\n" +
                            "        \"1/4 taza de harina para todo uso\",\n" +
                            "        \"1 cucharadita de comino molido\",\n" +
                            "        \"1 cucharadita de chile en polvo\",\n" +
                            "        \"1/2 cucharadita de ajo en polvo\",\n" +
                            "        \"1/2 cucharadita de sal\",\n" +
                            "        \"1/4 cucharadita de pimienta negra molida\",\n" +
                            "        \"2 cucharadas de aceite de oliva\",\n" +
                            "        \"12 tortillas de maíz\",\n" +
                            "        \"1 taza de repollo rallado\",\n" +
                            "        \"1/2 taza de cilantro fresco picado\",\n" +
                            "        \"1/2 taza de salsa de tomate\",\n" +
                            "        \"1/2 taza de crema agria\",\n" +
                            "        \"1 lima cortada en gajos\"\n" +
                            "    ],\n" +
                            "    \"tiempo_estimado\": \"30 minutos\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "    \"titulo\": \"Lasaña de Carne\",\n" +
                            "    \"descripcion\": \"Un plato italiano clásico con carne molida, salsa de tomate y queso.\",\n" +
                            "    \"foto\": \"https://example.com/lasana-carne.jpg\",\n" +
                            "    \"pasos\": [\n" +
                            "        \"Precalienta el horno a 375 grados F (190 grados C).\",\n" +
                            "        \"En una sartén grande, cocina la carne molida, la cebolla y el ajo a fuego medio-alto hasta que la carne esté dorada y la cebolla esté transparente.\",\n" +
                            "        \"Agrega los tomates triturados, la salsa de tomate, la sal, la pimienta y el orégano a la sartén y mezcla bien.\",\n" +
                            "        \"Reduce el fuego a medio-bajo y deja que la mezcla de carne y salsa hierva a fuego lento durante 10-15 minutos.\",\n" +
                            "        \"En una fuente para horno de 9x13 pulgadas, extiende una capa de la mezcla de carne y salsa.\",\n" +
                            "        \"Cubre la capa de carne y salsa con una capa de láminas de lasaña, asegurándote de que las láminas estén ligeramente superpuestas.\",\n" +
                            "        \"Cubre la capa de lasaña con una capa de queso mozzarella rallado.\",\n" +
                            "        \"Repite las capas de carne y salsa, lasaña y queso mozzarella rallado hasta que todos los ingredientes se hayan utilizado.\",\n" +
                            "        \"Cubre la capa superior de lasaña con queso parmesano rallado.\",\n" +
                            "        \"Cubre la fuente para horno con papel aluminio y hornea durante 25 minutos.\",\n" +
                            "        \"Quita el papel aluminio y hornea durante otros 25 minutos o hasta que la lasaña esté caliente y dorada por encima.\",\n" +
                            "        \"Deja reposar la lasaña durante 5-10 minutos antes de cortar y servir.\",\n" +
                            "        \"Sirve y disfruta.\"\n" +
                            "    ],\n" +
                            "    \"ingredientes\": [\n" +
                            "        \"1 libra de carne molida\",\n" +
                            "        \"1 cebolla picada\",\n" +
                            "        \"2 dientes de ajo picados\",\n" +
                            "        \"1 lata de tomates triturados\",\n" +
                            "        \"1 lata de salsa de tomate\",\n" +
                            "        \"1 cucharadita de sal\",\n" +
                            "        \"1/2 cucharadita de pimienta negra molida\",\n" +
                            "        \"1/2 cucharadita de orégano seco\",\n" +
                            "        \"12 láminas de lasaña\",\n" +
                            "        \"3 tazas de queso mozzarella rallado\",\n" +
                            "        \"1/2 taza de queso parmesano rallado\"\n" +
                            "    ],\n" +
                            "    \"tiempo_estimado\": \"1 hora y 30 minutos\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "    \"titulo\": \"Sopa de Fideos con Pollo\",\n" +
                            "    \"descripcion\": \"Una sopa reconfortante y fácil de hacer con fideos y pollo tierno.\",\n" +
                            "    \"foto\": \"https://example.com/sopa-fideos-pollo.jpg\",\n" +
                            "    \"pasos\": [\n" +
                            "        \"En una olla grande, agrega el caldo de pollo, las pechugas de pollo, las zanahorias, el apio, la cebolla, el ajo, el tomillo, el orégano y la pimienta.\",\n" +
                            "        \"Lleva la mezcla a ebullición a fuego alto.\",\n" +
                            "        \"Reduce el fuego a medio-bajo y cocina a fuego lento durante 20-25 minutos o hasta que el pollo esté cocido y tierno.\",\n" +
                            "        \"Retira el pollo de la olla y deja que se enfríe.\",\n" +
                            "        \"Agrega los fideos de huevo a la olla y cocina a fuego medio durante 8-10 minutos o hasta que los fideos estén cocidos.\",\n" +
                            "        \"Mientras tanto, corta el pollo en cubos o deshebra con un tenedor.\",\n" +
                            "        \"Agrega el pollo de vuelta a la olla y calienta durante unos minutos.\",\n" +
                            "        \"Prueba la sopa y agrega sal al gusto.\",\n" +
                            "        \"Sirve caliente y disfruta.\"\n" +
                            "    ],\n" +
                            "    \"ingredientes\": [\n" +
                            "        \"1 libra de pechugas de pollo sin piel y sin hueso\",\n" +
                            "        \"8 tazas de caldo de pollo\",\n" +
                            "        \"2 zanahorias peladas y picadas\",\n" +
                            "        \"2 tallos de apio picados\",\n" +
                            "        \"1 cebolla picada\",\n" +
                            "        \"2 dientes de ajo picados\",\n" +
                            "        \"1/2 cucharadita de tomillo seco\",\n" +
                            "        \"1/2 cucharadita de orégano seco\",\n" +
                            "        \"1/4 cucharadita de pimienta negra molida\",\n" +
                            "        \"8 onzas de fideos de huevo\",\n" +
                            "        \"Sal al gusto\"\n" +
                            "    ],\n" +
                            "    \"tiempo_estimado\": \"45 minutos\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "    \"titulo\": \"Ensalada César\",\n" +
                            "    \"descripcion\": \"Una ensalada clásica con lechuga romana, croutones y aderezo César casero.\",\n" +
                            "    \"foto\": \"https://example.com/ensalada-cesar.jpg\",\n" +
                            "    \"pasos\": [\n" +
                            "        \"Lava y seca la lechuga romana y córtala en trozos.\",\n" +
                            "        \"En una sartén pequeña, tuesta los croutones a fuego medio-alto hasta que estén dorados y crujientes.\",\n" +
                            "        \"Para hacer el aderezo, mezcla la mayonesa, el jugo de limón, los filetes de anchoa, el ajo, la mostaza Dijon y el queso parmesano en un tazón.\",\n" +
                            "        \"Mientras mezclas los ingredientes, agrega el aceite de oliva poco a poco y sigue mezclando hasta que se haya incorporado todo el aceite.\",\n" +
                            "        \"Agrega sal y pimienta al gusto.\",\n" +
                            "        \"En un tazón grande, combina la lechuga romana, los croutones y el queso parmesano rallado.\",\n" +
                            "        \"Agrega el aderezo y mezcla bien.\",\n" +
                            "        \"Sirve y disfruta.\"\n" +
                            "    ],\n" +
                            "    \"ingredientes\": [\n" +
                            "        \"1 cabeza de lechuga romana\",\n" +
                            "        \"1/2 taza de croutones\",\n" +
                            "        \"1/4 taza de queso parmesano rallado\",\n" +
                            "        \"Para el aderezo:\",\n" +
                            "        \"1/4 taza de mayonesa\",\n" +
                            "        \"2 cucharadas de jugo de limón fresco\",\n" +
                            "        \"2 filetes de anchoa enlatados, picados\",\n" +
                            "        \"1 diente de ajo picado\",\n" +
                            "        \"1/4 cucharadita de mostaza Dijon\",\n" +
                            "        \"1/4 taza de queso parmesano rallado\",\n" +
                            "        \"1/4 taza de aceite de oliva\",\n" +
                            "        \"Sal y pimienta al gusto\"\n" +
                            "    ],\n" +
                            "    \"tiempo_estimado\": \"20 minutos\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "    \"titulo\": \"Pollo Teriyaki\",\n" +
                            "    \"descripcion\": \"Pollo tierno y jugoso con una deliciosa salsa teriyaki casera.\",\n" +
                            "    \"foto\": \"https://example.com/pollo-teriyaki.jpg\",\n" +
                            "    \"pasos\": [\n" +
                            "        \"Precalienta el horno a 200°C.\",\n" +
                            "        \"Sazona las pechugas de pollo con sal y pimienta al gusto.\",\n" +
                            "        \"Coloca las pechugas de pollo en una bandeja para hornear y hornea durante 20-25 minutos o hasta que estén cocidas.\",\n" +
                            "        \"Mientras tanto, prepara la salsa teriyaki.\",\n" +
                            "        \"En una sartén pequeña, mezcla la salsa de soja, el agua, el vinagre de arroz, la miel, el jengibre rallado y el ajo picado.\",\n" +
                            "        \"Calienta a fuego medio-alto y deja que la mezcla hierva.\",\n" +
                            "        \"Reduce el fuego y deja cocinar a fuego lento durante 5 minutos.\",\n" +
                            "        \"Agrega la maicena disuelta y sigue cocinando hasta que la salsa espese.\",\n" +
                            "        \"Saca las pechugas de pollo del horno y córtalas en rebanadas.\",\n" +
                            "        \"Cubre las rebanadas de pollo con la salsa teriyaki y espolvorea con semillas de sésamo.\",\n" +
                            "        \"Sirve caliente y disfruta.\"\n" +
                            "    ],\n" +
                            "    \"ingredientes\": [\n" +
                            "        \"4 pechugas de pollo sin piel y sin hueso\",\n" +
                            "        \"Sal y pimienta al gusto\",\n" +
                            "        \"Para la salsa teriyaki:\",\n" +
                            "        \"1/2 taza de salsa de soja\",\n" +
                            "        \"1/4 taza de agua\",\n" +
                            "        \"2 cucharadas de vinagre de arroz\",\n" +
                            "        \"2 cucharadas de miel\",\n" +
                            "        \"1 cucharada de jengibre rallado\",\n" +
                            "        \"1 diente de ajo picado\",\"1 cucharada de maicena disuelta en 2 cucharadas de agua\",\n" +
                            "        \"Semillas de sésamo para decorar\"\n" +
                            "    ],\n" +
                            "    \"tiempo_estimado\": \"45 minutos\"\n" +
                            "    }\n" +
                            "       \n" +
                            "]\n";


                    """;

                    Gson gson = new Gson();
                    Type recetaListType = new TypeToken<List<Receta>>() {}.getType();
                    List<Receta> recetas = gson.fromJson(data, recetaListType);

                    // Ahora tienes una lista de objetos Receta con los datos deserializados del JSON
                    // Puedes acceder a las recetas individuales utilizando los métodos de la lista
                    List<String> descripciones = new LinkedList<>();
                    for (Receta receta : recetas) {
                        descripciones.add(receta.getDescripcion() + "\n");
                    }

                    tc_debug.setText(descripciones.toString());
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
    }

}