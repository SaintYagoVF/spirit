package net.latinus.spirit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import net.latinus.spirit.Entidades.Cliente;
import net.latinus.spirit.Entidades.Productos;
import net.latinus.spirit.Entidades.RegistroCliente;
import net.latinus.spirit.Entidades.SpinCorporacion;
import net.latinus.spirit.Entidades.SpinListaPrecio;
import net.latinus.spirit.Entidades.UnionCR;
import net.latinus.spirit.Entidades.ValoresPedido;
import net.latinus.spirit.Utilidades.Utilidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.nio.BufferUnderflowException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClienteActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mToggle;

    RecyclerView recyclerView;

    ArrayList<FoodModel> foodsList;

    ArrayList<FoodModel> foodsList2;

    ElegantNumberButton botonAumenta;


    ArrayList<ValoresPedido> valoresList;

    EditText filtroClientes2;

   // ArrayList<ProductoList> ProdList = new ArrayList<ProductoList>();

   Button botonCarrito;

   Dialog myDialog;

    TextView Pedido;

    String valorPasado;
    String nombrePasado;
    String apellidoPasado;
    String emailPasado;
    TextView nombrePas;
    TextView apellPas;
    TextView correoPas;

    Integer idPasado;

    ConnectivityDetector connectivityDetector;


    Integer listaprecioPedido;
    Spinner comboListaPrecio;
    ArrayList<String> listaListaPrecio;
    ArrayList<SpinListaPrecio> listaprecioList;


    ArrayList<String> listaValoresProducto;
    ArrayList<ValoresPedido> valoresproductoList;

    private ProgressDialog pDialog;

    private RequestQueue mQueue;

    ArrayAdapter adaptador;

    ConexionSQLiteHelper conn;

    Dialog myDialog2;

    ListView listViewClientes;
    ArrayList<String> listaInformacion;
    ArrayList<Cliente> listaClientes;

    ArrayList<UnionCR> listaUnion;

    ListView listViewRegClientes;

    ArrayList<RegistroCliente> listaRegClientes;


    EditText filtroProductos;

    FoodDeliverAdapter adapter;

    FoodDeliverAdapter adapter2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        recyclerView = findViewById(R.id.rv);
        botonCarrito=(Button)findViewById(R.id.btnCarrito);

        comboListaPrecio=(Spinner)findViewById(R.id.spinfiltroProductos);

        filtroProductos = (EditText)findViewById(R.id.filtroProductos);

        mQueue = Volley.newRequestQueue(this);

        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.abrir,R.string.cerrar);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDialog = new Dialog(this);

        myDialog2 = new Dialog(this);

        Bundle bundle = this.getIntent().getExtras();

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);




       /* nombrePas=(TextView)findViewById(R.id.menuNombre);
        apellPas=(TextView)findViewById(R.id.menuApellidos);
        correoPas=(TextView)findViewById(R.id.menuEmail);


*/

        consultarListaPrecio();


        if(bundle !=null)
        {
            //ObtainBundleData in the object
            String strdata = bundle.getString("identificacion");

            valorPasado=strdata;

            String strdata1 = bundle.getString("nombres");

            nombrePasado=strdata1;

            String strdata2 = bundle.getString("apellidos");

            apellidoPasado=strdata2;

            String strdata3 = bundle.getString("correo");

            emailPasado=strdata3;

            int strdata4 = bundle.getInt("id");

            idPasado = strdata4;

            Log.d("Valor Identif ",valorPasado);
            Log.d("Valor nombres ",nombrePasado);
            Log.d("Valor apellidos ",apellidoPasado);
            Log.d("Valor correo ",emailPasado);

            Log.d("Valor id ",idPasado.toString());

/*            nombrePas.setText(nombrePasado);
            apellPas.setText(apellidoPasado);
            correoPas.setText(emailPasado);

       */

            //Do something here if data  received
        }
        else
        {
            //Do something here if data not received
        }



        String[] names = {"Chifle Dulce Tira", "Chifle Sal Tira", "Cuerito Grande", "Cuerito Mediano", "Cuerito Pequeño Tira", "Maní Dulce Paq. 20", "Maní Dulce Paq. 10", "Maní Sal", "Mixto Grande", "Mixto Mediano", "Mixto Pequeño", "Papa Hoja Gigante", "Papa Hoja Grande", "Papa Hoja Mediana", "Papa Pequeña", "Papa Picada Gigante", "Papa Picada Grande", "Papa Picada Mediana", "Papa Picada Pequeña", "Tostado Tiras"};
        final String[] places = {"Chifle Dulce Tiras x 12", "Chifle Sal Tiras x 12", "Cuerito Grande", "Cuerito Mediano", "Cuerito Pequeño Tira x 12", "Maní Dulce Paquete x 20", "Maní Dulce Paquete x 10", "Maní Sal Paquete x 10", "Mixto Grande", "Mixto Mediano", "Mixto Pequeño", "Papa Hoja Gigante", "Papa Hoja Grande", "Papa Hoja Mediana", "Papa Pequeña x 12", "Papa Picada Gigante", "Papa Picada Grande", "Papa Picada Mediana", "Papa Picada Pequeña", "Tostado Tiras x 12"};
        final String[] prices = {"$", "$", "$", "$", "$", "$", "$", "$", "$", "$", "$", "$", "$", "$", "$", "$", "$", "$", "$", "$"};
        int[] images = {R.drawable.chifle_dulce_tira_x_12, R.drawable.chifle_sal_tira_x_12, R.drawable.cuerito_grande, R.drawable.cuerito_mediano, R.drawable.cuerito_pequeno_tira_x_12, R.drawable.mani_dulce_paq_20_y_10, R.drawable.mani_dulce_paq_20_y_10, R.drawable.mani_sal_paq_x_10, R.drawable.mixto_grande, R.drawable.mixto_mediano, R.drawable.mixto_pequeno, R.drawable.papa_hoja_gigante, R.drawable.papa_hoja_grande, R.drawable.papa_hoja_mediana, R.drawable.papa_pequena_x12, R.drawable.papa_picada_gigante, R.drawable.papa_picada_grande, R.drawable.papa_picada_mediana, R.drawable.papa_picada_pequena, R.drawable.tostado_tiras_x_10};

        foodsList = new ArrayList<>();


        for (int i = 0; i < names.length; i++) {

            foodsList.add(new FoodModel(names[i], places[i], prices[i], images[i]));

        }



         adapter = new FoodDeliverAdapter(this, foodsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        RecyclerView.LayoutManager mLayoutManager = linearLayoutManager;

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);



        filtroProductos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());
            }
        });


        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);


        View headerView = navigationView.getHeaderView(0);
        nombrePas = (TextView) headerView.findViewById(R.id.menuNombre);
        nombrePas.setText(nombrePasado);

        apellPas = (TextView)headerView.findViewById(R.id.menuApellidos);
        apellPas.setText(apellidoPasado);

        correoPas = (TextView)headerView.findViewById(R.id.menuEmail);
        correoPas.setText(emailPasado);
       final DecimalFormat twoDForm = new DecimalFormat("#.##");

        valoresproductoList =new ArrayList<ValoresPedido>();
        /*if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }
*/
        setupNavigationDrawerContent(navigationView);


        botonCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Button btnAceptar;
                Button btnCancelar;
                myDialog.setContentView(R.layout.popup_pedido);

                String Chifle_Dulce_Tira="";
                String Chifle_Sal_Tira="";
                String Cuerito_Grande="";
                String Cuerito_Mediano="";
                String Cuerito_Pequeno_Tira ="";
                String  Mani_Dulce_Paq_20="";
                String  Mani_Dulce_Paq_10="";
                String  Mani_Sal="";
                String  Mixto_Grande="";
                String  Mixto_Mediano="";
                String  Mixto_Pequeno="";
                String  Papa_Hoja_Gigante="";
                String  Papa_Hoja_Grande="";
                String  Papa_Hoja_Mediana="";
                String  Papa_Pequena="";
                String  Papa_Picada_Gigante="";
                String  Papa_Picada_Grande="";
                String  Papa_Picada_Mediana="";
                String  Papa_Picada_Pequena="";
                String  Tostado_Tiras="";


                Double PChifle_Dulce_Tira=0.0;
                Double PChifle_Sal_Tira=0.0;
                Double PCuerito_Grande=0.0;
                Double PCuerito_Mediano=0.0;
                Double PCuerito_Pequeno_Tira=0.0;
                Double  PMani_Dulce_Paq_20=0.0;
                Double  PMani_Dulce_Paq_10=0.0;
                Double  PMani_Sal=0.0;
                Double  PMixto_Grande=0.0;
                Double  PMixto_Mediano=0.0;
                Double  PMixto_Pequeno=0.0;
                Double  PPapa_Hoja_Gigante=0.0;
                Double  PPapa_Hoja_Grande=0.0;
                Double  PPapa_Hoja_Mediana=0.0;
                Double  PPapa_Pequena=0.0;
                Double  PPapa_Picada_Gigante=0.0;
                Double  PPPapa_Picada_Grande=0.0;
                Double  PPapa_Picada_Mediana=0.0;
                Double  PPapa_Picada_Pequena=0.0;
                Double  PTostado_Tiras=0.0;

                btnAceptar = (Button)myDialog.findViewById(R.id.btnPopupAceptar);
                btnCancelar = (Button)myDialog.findViewById(R.id.btnPopupCancelar);
                Pedido=(TextView)myDialog.findViewById(R.id.txtPedido);


                ArrayList<ProductoList>ProdList = adapter.getmProdList();

                valoresproductoList = new ArrayList<>();


                final Productos clien=new Productos();


               /* for (int i = 0 ; i < ProdList.size() ; i++) {
                    Log.d("Valores son", ProdList.get(i).getNombreProd());
                    Log.d("Valores cant", ProdList.get(i).getCantProd());
                }
                */

                for(int i=0 ; i < ProdList.size() ; i++){

                    if(ProdList.get(i).getNombreProd()=="Chifle Dulce Tira") {
                        Chifle_Dulce_Tira = ProdList.get(i).getCantProd();
                        try {
                            PChifle_Dulce_Tira = Double.valueOf(twoDForm.format(Double.parseDouble(ProdList.get(i).getPrecioProd())));
                        }
                        catch (Exception ex){

                        }
                    }
                    if(ProdList.get(i).getNombreProd()=="Chifle Sal Tira"){
                        Chifle_Sal_Tira=ProdList.get(i).getCantProd();
                        try {
                            PChifle_Sal_Tira = Double.valueOf(twoDForm.format(Double.parseDouble(ProdList.get(i).getPrecioProd())));
                        }
                        catch (Exception ex){

                        }
                    }

                    if(ProdList.get(i).getNombreProd()=="Cuerito Grande"){
                        Cuerito_Grande=ProdList.get(i).getCantProd();
                        try {
                            PCuerito_Grande = Double.valueOf(twoDForm.format(Double.parseDouble(ProdList.get(i).getPrecioProd())));
                        }
                        catch (Exception ex){

                        }
                    }

                    if(ProdList.get(i).getNombreProd()=="Cuerito Mediano"){
                        Cuerito_Mediano=ProdList.get(i).getCantProd();
                        try {
                            PCuerito_Mediano = Double.valueOf(twoDForm.format(Double.parseDouble(ProdList.get(i).getPrecioProd())));
                        }
                        catch (Exception ex){

                        }
                    }
                    if(ProdList.get(i).getNombreProd()=="Cuerito Pequeño Tira"){
                        Cuerito_Pequeno_Tira=ProdList.get(i).getCantProd();

                        try {
                            PCuerito_Pequeno_Tira = Double.valueOf(twoDForm.format(Double.parseDouble(ProdList.get(i).getPrecioProd())));
                        }
                        catch (Exception ex){

                        }
                    }
                    if(ProdList.get(i).getNombreProd()=="Maní Dulce Paq. 20"){
                        Mani_Dulce_Paq_20=ProdList.get(i).getCantProd();
                        try {
                            PMani_Dulce_Paq_20 = Double.valueOf(twoDForm.format(Double.parseDouble(ProdList.get(i).getPrecioProd())));
                        }
                        catch (Exception ex){

                        }
                    }
                    if(ProdList.get(i).getNombreProd()=="Maní Dulce Paq. 10"){
                        Mani_Dulce_Paq_10=ProdList.get(i).getCantProd();
                        try {
                            PMani_Dulce_Paq_10 = Double.valueOf(twoDForm.format(Double.parseDouble(ProdList.get(i).getPrecioProd())));
                        }
                        catch (Exception ex){

                        }
                    }
                    if(ProdList.get(i).getNombreProd()=="Maní Sal"){
                        Mani_Sal=ProdList.get(i).getCantProd();
                        try {
                            PMani_Sal = Double.valueOf(twoDForm.format(Double.parseDouble(ProdList.get(i).getPrecioProd())));
                        }
                        catch (Exception ex){

                        }
                    }
                    if(ProdList.get(i).getNombreProd()=="Mixto Grande"){
                        Mixto_Grande=ProdList.get(i).getCantProd();
                        try {
                            PMixto_Grande = Double.valueOf(twoDForm.format(Double.parseDouble(ProdList.get(i).getPrecioProd())));
                        }
                        catch (Exception ex){

                        }
                    }
                    if(ProdList.get(i).getNombreProd()=="Mixto Mediano"){
                        Mixto_Mediano=ProdList.get(i).getCantProd();
                        try {
                            PMixto_Mediano = Double.valueOf(twoDForm.format(Double.parseDouble(ProdList.get(i).getPrecioProd())));
                        }
                        catch (Exception ex){

                        }
                    }
                    if(ProdList.get(i).getNombreProd()=="Mixto Pequeño"){
                        Mixto_Pequeno=ProdList.get(i).getCantProd();
                        try {
                            PMixto_Pequeno = Double.valueOf(twoDForm.format(Double.parseDouble(ProdList.get(i).getPrecioProd())));
                        }
                        catch (Exception ex){

                        }
                    }
                    if(ProdList.get(i).getNombreProd()=="Papa Hoja Gigante"){
                        Papa_Hoja_Gigante=ProdList.get(i).getCantProd();
                        try {
                            PPapa_Hoja_Gigante = Double.valueOf(twoDForm.format(Double.parseDouble(ProdList.get(i).getPrecioProd())));
                        }
                        catch (Exception ex){

                        }
                    }

                    if(ProdList.get(i).getNombreProd()=="Papa Hoja Grande"){
                        Papa_Hoja_Grande=ProdList.get(i).getCantProd();
                        try {
                            PPapa_Hoja_Grande = Double.valueOf(twoDForm.format(Double.parseDouble(ProdList.get(i).getPrecioProd())));
                        }
                        catch (Exception ex){

                        }
                    }
                    if(ProdList.get(i).getNombreProd()=="Papa Hoja Mediana"){
                        Papa_Hoja_Mediana=ProdList.get(i).getCantProd();
                        try {
                            PPapa_Hoja_Mediana = Double.valueOf(twoDForm.format(Double.parseDouble(ProdList.get(i).getPrecioProd())));
                        }
                        catch (Exception ex){

                        }
                    }
                    if(ProdList.get(i).getNombreProd()=="Papa Pequeña"){
                        Papa_Pequena=ProdList.get(i).getCantProd();
                        try {
                            PPapa_Pequena = Double.valueOf(twoDForm.format(Double.parseDouble(ProdList.get(i).getPrecioProd())));
                        }
                        catch (Exception ex){

                        }
                    }
                    if(ProdList.get(i).getNombreProd()=="Papa Picada Gigante"){
                        Papa_Picada_Gigante=ProdList.get(i).getCantProd();
                        try {
                            PPapa_Picada_Gigante = Double.valueOf(twoDForm.format(Double.parseDouble(ProdList.get(i).getPrecioProd())));
                        }
                        catch (Exception ex){


                        }
                    }
                    if(ProdList.get(i).getNombreProd()=="Papa Picada Grande"){
                        Papa_Picada_Grande=ProdList.get(i).getCantProd();
                        try {
                            PPPapa_Picada_Grande = Double.valueOf(twoDForm.format(Double.parseDouble(ProdList.get(i).getPrecioProd())));
                        }
                        catch (Exception ex){

                        }
                    }
                    if(ProdList.get(i).getNombreProd()=="Papa Picada Mediana"){
                        Papa_Picada_Mediana=ProdList.get(i).getCantProd();
                        try {
                            PPapa_Picada_Mediana = Double.valueOf(twoDForm.format(Double.parseDouble(ProdList.get(i).getPrecioProd())));
                        }
                        catch (Exception ex){

                        }
                    }
                    if(ProdList.get(i).getNombreProd()=="Papa Picada Pequeña"){
                        Papa_Picada_Pequena=ProdList.get(i).getCantProd();
                        try {
                            PPapa_Picada_Pequena = Double.valueOf(twoDForm.format(Double.parseDouble(ProdList.get(i).getPrecioProd())));
                        }
                        catch (Exception ex){

                        }
                    }
                    if(ProdList.get(i).getNombreProd()=="Tostado Tiras"){
                        Tostado_Tiras=ProdList.get(i).getCantProd();

                        try {
                            PTostado_Tiras = Double.valueOf(twoDForm.format(Double.parseDouble(ProdList.get(i).getPrecioProd())));
                        }
                        catch (Exception ex){

                        }
                    }




                }
               /* Log.d("canti Papas:", papas);
                Log.d("canti Cachitos:", cachitos);
                Log.d("canti Tostitos:", tostitos);
                Log.d("canti Sodas:", sodas);

                */

                // Pedido.setText("Papas: "+papas+"\n\nCachitos: "+cachitos+"\n\nTostitos: "+tostitos+"\n\nSodas: "+sodas);




                if(Chifle_Dulce_Tira!="" && Chifle_Dulce_Tira!="0") {

                    SQLiteDatabase db = conn.getReadableDatabase();

                    try{




                        //select * from usuarios
                        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_CANTIDAD_STOCK+","+Utilidades.CAMPO_ID_LOTE_STOCK+" FROM " + Utilidades.TABLA_STOCK+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = 'CHIFLE DULCE TIRA'", null);

                        if(cursor.getCount() <= 0){

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                    alert.setTitle("Stock");
                                    alert.setMessage("No existe Chifle Dulce en Tira en Stock");
                                    alert.setPositiveButton("OK",null);
                                    alert.show();
                                }
                            }, 500);



                        }
                        else{

                            Double cantidad=0.0;
                            Integer lote=0;

                            while (cursor.moveToNext()) {

                               cantidad=cursor.getDouble(0);
                               lote=cursor.getInt(1);

                            }
                            if(Double.parseDouble(Chifle_Dulce_Tira)>cantidad){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                        alert.setTitle("Stock");
                                        alert.setMessage("No hay suficienes Chifles Dulce en Tira en Stock");
                                        alert.setPositiveButton("OK",null);
                                        alert.show();
                                    }
                                }, 500);


                            }
                            else{



                                clien.setChifle_Dulce_Tira(Integer.parseInt(Chifle_Dulce_Tira));

                                Double valor=Double.valueOf(twoDForm.format(Double.parseDouble(Chifle_Dulce_Tira)*PChifle_Dulce_Tira));

                                ValoresPedido persona=null;

                                persona=new ValoresPedido();

                                Pedido.append("Chifle Dulce Tira: " + Chifle_Dulce_Tira + " X $"+PChifle_Dulce_Tira+" = $"+valor+"\n\n");

                                persona.setNombreValorPedido("CHIFLE DULCE TIRA");
                                persona.setLoteValorPedido(lote);
                                persona.setCantidadValorPedido(Integer.parseInt(Chifle_Dulce_Tira));
                                persona.setPrecioValorPedido(PChifle_Dulce_Tira);
                                persona.setTotalValorPedido(valor);

                                valoresproductoList.add(persona);



                            }



                        }






                    }
                    catch (Exception ex){


                    }
                    db.close();



                }
                if(Chifle_Sal_Tira!="" && Chifle_Sal_Tira!="0"){

                    SQLiteDatabase db = conn.getReadableDatabase();

                    try{




                        //select * from usuarios
                        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_CANTIDAD_STOCK+","+Utilidades.CAMPO_ID_LOTE_STOCK+" FROM " + Utilidades.TABLA_STOCK+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = 'CHIFLE SAL TIRA'", null);

                        if(cursor.getCount() <= 0){

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                    alert.setTitle("Stock");
                                    alert.setMessage("No existe Chifle Sal en Tira en Stock");
                                    alert.setPositiveButton("OK",null);
                                    alert.show();
                                }
                            }, 500);



                        }
                        else{

                            Double cantidad=0.0;

                            Integer lote=0;

                            while (cursor.moveToNext()) {

                                cantidad=cursor.getDouble(0);
                                lote=cursor.getInt(1);
                            }
                            if(Double.parseDouble(Chifle_Sal_Tira)>cantidad){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                        alert.setTitle("Stock");
                                        alert.setMessage("No hay suficienes Chifles Sal en Tira en Stock");
                                        alert.setPositiveButton("OK",null);
                                        alert.show();
                                    }
                                }, 500);


                            }
                            else{

                                clien.setChifle_Sal_Tira(Integer.parseInt(Chifle_Sal_Tira));


                                ValoresPedido persona=null;

                                persona=new ValoresPedido();
                                Double valor=Double.valueOf(twoDForm.format(Double.parseDouble(Chifle_Sal_Tira)*PChifle_Sal_Tira));

                                Pedido.append("Chifle Sal Tira: " + Chifle_Sal_Tira + " X $"+PChifle_Sal_Tira+" = $"+valor+"\n\n");

                                persona.setNombreValorPedido("CHIFLE SAL TIRA");
                                persona.setLoteValorPedido(lote);
                                persona.setCantidadValorPedido(Integer.parseInt(Chifle_Sal_Tira));
                                persona.setPrecioValorPedido(PChifle_Sal_Tira);
                                persona.setTotalValorPedido(valor);

                                valoresproductoList.add(persona);

                            }



                        }






                    }
                    catch (Exception ex){


                    }
                    db.close();

                }
                if(Cuerito_Grande!="" && Cuerito_Grande!="0"){

                    SQLiteDatabase db = conn.getReadableDatabase();

                    try{




                        //select * from usuarios
                        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_CANTIDAD_STOCK+","+Utilidades.CAMPO_ID_LOTE_STOCK+" FROM " + Utilidades.TABLA_STOCK+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = 'CUERITO GRANDE'", null);

                        if(cursor.getCount() <= 0){

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                    alert.setTitle("Stock");
                                    alert.setMessage("No existe Cuerito Grande en Stock");
                                    alert.setPositiveButton("OK",null);
                                    alert.show();
                                }
                            }, 500);



                        }
                        else{

                            Double cantidad=0.0;

                            Integer lote=0;

                            while (cursor.moveToNext()) {

                                cantidad=cursor.getDouble(0);
                                lote=cursor.getInt(1);

                            }
                            if(Double.parseDouble(Cuerito_Grande)>cantidad){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                        alert.setTitle("Stock");
                                        alert.setMessage("No hay suficienes Cueritos Grandes en Stock");
                                        alert.setPositiveButton("OK",null);
                                        alert.show();
                                    }
                                }, 500);


                            }
                            else{

                                clien.setCuerito_Grande(Integer.parseInt(Cuerito_Grande));

                                ValoresPedido persona=null;

                                persona=new ValoresPedido();
                                Double valor=Double.valueOf(twoDForm.format(Double.parseDouble(Cuerito_Grande)*PCuerito_Grande));

                                Pedido.append("Cuerito Grande: " + Cuerito_Grande + " X $"+PCuerito_Grande+" = $"+valor+"\n\n");

                                persona.setNombreValorPedido("CUERITO GRANDE");
                                persona.setLoteValorPedido(lote);
                                persona.setCantidadValorPedido(Integer.parseInt(Cuerito_Grande));
                                persona.setPrecioValorPedido(PCuerito_Grande);
                                persona.setTotalValorPedido(valor);

                                valoresproductoList.add(persona);
                            }



                        }






                    }
                    catch (Exception ex){


                    }
                    db.close();

                }
                if(Cuerito_Mediano!="" && Cuerito_Mediano!="0"){



                    SQLiteDatabase db = conn.getReadableDatabase();

                    try{




                        //select * from usuarios
                        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_CANTIDAD_STOCK+","+Utilidades.CAMPO_ID_LOTE_STOCK+" FROM " + Utilidades.TABLA_STOCK+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = 'CUERITO MEDIANO'", null);

                        if(cursor.getCount() <= 0){

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                    alert.setTitle("Stock");
                                    alert.setMessage("No existe Cueritos Medianos en Stock");
                                    alert.setPositiveButton("OK",null);
                                    alert.show();
                                }
                            }, 500);



                        }
                        else{

                            Double cantidad=0.0;

                            Integer lote=0;

                            while (cursor.moveToNext()) {

                                cantidad=cursor.getDouble(0);
                                lote=cursor.getInt(1);

                            }
                            if(Double.parseDouble(Cuerito_Mediano)>cantidad){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                        alert.setTitle("Stock");
                                        alert.setMessage("No hay suficienes Cueritos Medianos en Stock");
                                        alert.setPositiveButton("OK",null);
                                        alert.show();
                                    }
                                }, 500);


                            }
                            else{

                                clien.setCuerito_Mediano(Integer.parseInt(Cuerito_Mediano));


                                ValoresPedido persona=null;

                                persona=new ValoresPedido();
                                Double valor=Double.valueOf(twoDForm.format(Double.parseDouble(Cuerito_Mediano)*PCuerito_Mediano));

                                Pedido.append("Cuerito Mediano: " + Cuerito_Mediano+ " X $"+PCuerito_Mediano+" = $"+valor+"\n\n");

                                persona.setNombreValorPedido("CUERITO MEDIANO");
                                persona.setLoteValorPedido(lote);
                                persona.setCantidadValorPedido(Integer.parseInt(Cuerito_Mediano));
                                persona.setPrecioValorPedido(PCuerito_Mediano);
                                persona.setTotalValorPedido(valor);

                                valoresproductoList.add(persona);
                            }



                        }






                    }
                    catch (Exception ex){


                    }
                    db.close();

                }
                if(Cuerito_Pequeno_Tira!="" && Cuerito_Pequeno_Tira!="0"){

                    SQLiteDatabase db = conn.getReadableDatabase();

                    try{




                        //select * from usuarios
                        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_CANTIDAD_STOCK+","+Utilidades.CAMPO_ID_LOTE_STOCK+" FROM " + Utilidades.TABLA_STOCK+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = 'CUERITO PEQUEÑO TIRA'", null);

                        if(cursor.getCount() <= 0){

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                    alert.setTitle("Stock");
                                    alert.setMessage("No existe Cueritos Pequeños en Stock");
                                    alert.setPositiveButton("OK",null);
                                    alert.show();
                                }
                            }, 500);



                        }
                        else{

                            Double cantidad=0.0;

                            Integer lote=0;

                            while (cursor.moveToNext()) {

                                cantidad=cursor.getDouble(0);
                                lote=cursor.getInt(1);

                            }
                            if(Double.parseDouble(Cuerito_Pequeno_Tira)>cantidad){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                        alert.setTitle("Stock");
                                        alert.setMessage("No hay suficienes Cueritos Pequeños en Stock");
                                        alert.setPositiveButton("OK",null);
                                        alert.show();
                                    }
                                }, 500);


                            }
                            else{

                                clien.setCuerito_Pequeno_Tira(Integer.parseInt(Cuerito_Pequeno_Tira));

                                ValoresPedido persona=null;

                                persona=new ValoresPedido();
                                Double valor=Double.valueOf(twoDForm.format(Double.parseDouble(Cuerito_Pequeno_Tira)*PCuerito_Pequeno_Tira));

                                Pedido.append("Cuerito Pequeño: " + Cuerito_Pequeno_Tira+ " X $"+PCuerito_Pequeno_Tira+" = $"+valor+"\n\n");

                                persona.setNombreValorPedido("CUERITO PEQUEÑO TIRA");
                                persona.setLoteValorPedido(lote);
                                persona.setCantidadValorPedido(Integer.parseInt(Cuerito_Pequeno_Tira));
                                persona.setPrecioValorPedido(PCuerito_Pequeno_Tira);
                                persona.setTotalValorPedido(valor);

                                valoresproductoList.add(persona);
                            }



                        }






                    }
                    catch (Exception ex){


                    }
                    db.close();



                }
                if(Mani_Dulce_Paq_20!="" && Mani_Dulce_Paq_20!="0"){

                    SQLiteDatabase db = conn.getReadableDatabase();

                    try{




                        //select * from usuarios
                        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_CANTIDAD_STOCK+","+Utilidades.CAMPO_ID_LOTE_STOCK+" FROM " + Utilidades.TABLA_STOCK+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = 'MANI DULCE PAQ. 20'", null);

                        if(cursor.getCount() <= 0){

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                    alert.setTitle("Stock");
                                    alert.setMessage("No existe Maní Dulce Paq. 20 en Stock");
                                    alert.setPositiveButton("OK",null);
                                    alert.show();
                                }
                            }, 500);



                        }
                        else{

                            Double cantidad=0.0;

                            Integer lote=0;

                            while (cursor.moveToNext()) {

                                cantidad=cursor.getDouble(0);
                                lote=cursor.getInt(1);

                            }
                            if(Double.parseDouble(Mani_Dulce_Paq_20)>cantidad){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                        alert.setTitle("Stock");
                                        alert.setMessage("No hay suficienes Maní Dulce Paq. 20 en Stock");
                                        alert.setPositiveButton("OK",null);
                                        alert.show();
                                    }
                                }, 500);


                            }
                            else{

                                clien.setMani_Dulce_Paq_20(Integer.parseInt(Mani_Dulce_Paq_20));

                                ValoresPedido persona=null;

                                persona=new ValoresPedido();
                                Double valor=Double.valueOf(twoDForm.format(Double.parseDouble(Mani_Dulce_Paq_20)*PMani_Dulce_Paq_20));

                                Pedido.append("Maní Dulce Paq.20: " + Mani_Dulce_Paq_20+ " X $"+PMani_Dulce_Paq_20+" = $"+valor+"\n\n");

                                persona.setNombreValorPedido("MANI DULCE PAQ. 20");
                                persona.setLoteValorPedido(lote);
                                persona.setCantidadValorPedido(Integer.parseInt(Mani_Dulce_Paq_20));
                                persona.setPrecioValorPedido(PMani_Dulce_Paq_20);
                                persona.setTotalValorPedido(valor);

                                valoresproductoList.add(persona);
                            }



                        }






                    }
                    catch (Exception ex){


                    }
                    db.close();

                }
                if(Mani_Dulce_Paq_10!="" && Mani_Dulce_Paq_10!="0"){

                    SQLiteDatabase db = conn.getReadableDatabase();

                    try{




                        //select * from usuarios
                        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_CANTIDAD_STOCK+","+Utilidades.CAMPO_ID_LOTE_STOCK+" FROM " + Utilidades.TABLA_STOCK+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = 'MANI DULCE PAQ. 10'", null);

                        if(cursor.getCount() <= 0){

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                    alert.setTitle("Stock");
                                    alert.setMessage("No existe Maní Dulce Paq. 10 en Stock");
                                    alert.setPositiveButton("OK",null);
                                    alert.show();
                                }
                            }, 500);



                        }
                        else{

                            Double cantidad=0.0;

                            Integer lote=0;

                            while (cursor.moveToNext()) {

                                cantidad=cursor.getDouble(0);
                                lote=cursor.getInt(1);

                            }
                            if(Double.parseDouble(Mani_Dulce_Paq_10)>cantidad){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                        alert.setTitle("Stock");
                                        alert.setMessage("No hay suficienes Maní Dulce Paq. 10 en Stock");
                                        alert.setPositiveButton("OK",null);
                                        alert.show();
                                    }
                                }, 500);


                            }
                            else{

                                clien.setMani_Dulce_Paq_10(Integer.parseInt(Mani_Dulce_Paq_10));

                                ValoresPedido persona=null;

                                persona=new ValoresPedido();
                                Double valor=Double.valueOf(twoDForm.format(Double.parseDouble(Mani_Dulce_Paq_10)*PMani_Dulce_Paq_10));

                                Pedido.append("Maní Dulce Paq.10: " + Mani_Dulce_Paq_10+ " X $"+PMani_Dulce_Paq_10+" = $"+valor+"\n\n");

                                persona.setNombreValorPedido("MANI DULCE PAQ. 10");
                                persona.setLoteValorPedido(lote);
                                persona.setCantidadValorPedido(Integer.parseInt(Mani_Dulce_Paq_10));
                                persona.setPrecioValorPedido(PMani_Dulce_Paq_10);
                                persona.setTotalValorPedido(valor);

                                valoresproductoList.add(persona);
                            }



                        }






                    }
                    catch (Exception ex){


                    }
                    db.close();

                }
                if(Mani_Sal!="" && Mani_Sal!="0"){
                    SQLiteDatabase db = conn.getReadableDatabase();

                    try{




                        //select * from usuarios
                        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_CANTIDAD_STOCK+","+Utilidades.CAMPO_ID_LOTE_STOCK+" FROM " + Utilidades.TABLA_STOCK+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = 'MANI SAL'", null);

                        if(cursor.getCount() <= 0){

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                    alert.setTitle("Stock");
                                    alert.setMessage("No existe Maní Sal en Stock");
                                    alert.setPositiveButton("OK",null);
                                    alert.show();
                                }
                            }, 500);



                        }
                        else{

                            Double cantidad=0.0;

                            Integer lote=0;

                            while (cursor.moveToNext()) {

                                cantidad=cursor.getDouble(0);
                                lote=cursor.getInt(1);

                            }
                            if(Double.parseDouble(Mani_Sal)>cantidad){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                        alert.setTitle("Stock");
                                        alert.setMessage("No hay suficiene Maní Sal en Stock");
                                        alert.setPositiveButton("OK",null);
                                        alert.show();
                                    }
                                }, 500);


                            }
                            else{

                                clien.setMani_Sal(Integer.parseInt(Mani_Sal));

                                ValoresPedido persona=null;

                                persona=new ValoresPedido();
                                Double valor=Double.valueOf(twoDForm.format(Double.parseDouble(Mani_Sal)*PMani_Sal));

                                Pedido.append("Maní Sal: " + Mani_Sal+ " X $"+PMani_Sal+" = $"+valor+"\n\n");

                                persona.setNombreValorPedido("MANI SAL");
                                persona.setLoteValorPedido(lote);
                                persona.setCantidadValorPedido(Integer.parseInt(Mani_Sal));
                                persona.setPrecioValorPedido(PMani_Sal);
                                persona.setTotalValorPedido(valor);

                                valoresproductoList.add(persona);
                            }



                        }






                    }
                    catch (Exception ex){


                    }
                    db.close();

                }
                if(Mixto_Grande!="" && Mixto_Grande!="0"){

                    SQLiteDatabase db = conn.getReadableDatabase();

                    try{




                        //select * from usuarios
                        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_CANTIDAD_STOCK+","+Utilidades.CAMPO_ID_LOTE_STOCK+" FROM " + Utilidades.TABLA_STOCK+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = 'MIXTO GRANDE'", null);

                        if(cursor.getCount() <= 0){

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                    alert.setTitle("Stock");
                                    alert.setMessage("No existe Mixto Grande en Stock");
                                    alert.setPositiveButton("OK",null);
                                    alert.show();
                                }
                            }, 500);



                        }
                        else{

                            Double cantidad=0.0;

                            Integer lote=0;

                            while (cursor.moveToNext()) {

                                cantidad=cursor.getDouble(0);
                                lote=cursor.getInt(1);

                            }
                            if(Double.parseDouble(Mixto_Grande)>cantidad){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                        alert.setTitle("Stock");
                                        alert.setMessage("No hay suficiente Mixto Grande en Stock");
                                        alert.setPositiveButton("OK",null);
                                        alert.show();
                                    }
                                }, 500);


                            }
                            else{

                                clien.setMixto_Grande(Integer.parseInt(Mixto_Grande));

                                ValoresPedido persona=null;

                                persona=new ValoresPedido();
                                Double valor=Double.valueOf(twoDForm.format(Double.parseDouble(Mixto_Grande)*PMixto_Grande));

                                Pedido.append("Mixto Grande: " + Mixto_Grande+ " X $"+PMixto_Grande+" = $"+valor+"\n\n");

                                persona.setNombreValorPedido("MIXTO GRANDE");
                                persona.setLoteValorPedido(lote);
                                persona.setCantidadValorPedido(Integer.parseInt(Mixto_Grande));
                                persona.setPrecioValorPedido(PMixto_Grande);
                                persona.setTotalValorPedido(valor);

                                valoresproductoList.add(persona);
                            }



                        }






                    }
                    catch (Exception ex){


                    }
                    db.close();

                }
                if(Mixto_Mediano!="" && Mixto_Mediano!="0"){

                    SQLiteDatabase db = conn.getReadableDatabase();

                    try{




                        //select * from usuarios
                        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_CANTIDAD_STOCK+","+Utilidades.CAMPO_ID_LOTE_STOCK+" FROM " + Utilidades.TABLA_STOCK+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = 'MIXTO MEDIANO'", null);

                        if(cursor.getCount() <= 0){

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                    alert.setTitle("Stock");
                                    alert.setMessage("No existe Mixto Mediano en Stock");
                                    alert.setPositiveButton("OK",null);
                                    alert.show();
                                }
                            }, 500);



                        }
                        else{

                            Double cantidad=0.0;

                            Integer lote=0;

                            while (cursor.moveToNext()) {

                                cantidad=cursor.getDouble(0);
                                lote=cursor.getInt(1);

                            }
                            if(Double.parseDouble(Mixto_Mediano)>cantidad){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                        alert.setTitle("Stock");
                                        alert.setMessage("No hay suficiente Mixto Mediano en Stock");
                                        alert.setPositiveButton("OK",null);
                                        alert.show();
                                    }
                                }, 500);


                            }
                            else{

                                clien.setMixto_Mediano(Integer.parseInt(Mixto_Mediano));


                                ValoresPedido persona=null;

                                persona=new ValoresPedido();
                                Double valor=Double.valueOf(twoDForm.format(Double.parseDouble(Mixto_Mediano)*PMixto_Mediano));

                                Pedido.append("Mixto Mediano: " + Mixto_Mediano+ " X $"+PMixto_Mediano+" = $"+valor+"\n\n");

                                persona.setNombreValorPedido("MIXTO MEDIANO");
                                persona.setLoteValorPedido(lote);
                                persona.setCantidadValorPedido(Integer.parseInt(Mixto_Mediano));
                                persona.setPrecioValorPedido(PMixto_Mediano);
                                persona.setTotalValorPedido(valor);

                                valoresproductoList.add(persona);
                            }



                        }






                    }
                    catch (Exception ex){


                    }
                    db.close();

                }
                if(Mixto_Pequeno!="" && Mixto_Pequeno!="0"){

                    SQLiteDatabase db = conn.getReadableDatabase();

                    try{




                        //select * from usuarios
                        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_CANTIDAD_STOCK+","+Utilidades.CAMPO_ID_LOTE_STOCK+" FROM " + Utilidades.TABLA_STOCK+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = 'MIXTO PEQUEÑO'", null);

                        if(cursor.getCount() <= 0){

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                    alert.setTitle("Stock");
                                    alert.setMessage("No existe Mixto Pequeño en Stock");
                                    alert.setPositiveButton("OK",null);
                                    alert.show();
                                }
                            }, 500);



                        }
                        else{

                            Double cantidad=0.0;

                            Integer lote=0;

                            while (cursor.moveToNext()) {

                                cantidad=cursor.getDouble(0);
                                lote=cursor.getInt(1);

                            }
                            if(Double.parseDouble(Mixto_Pequeno)>cantidad){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                        alert.setTitle("Stock");
                                        alert.setMessage("No hay suficiente Mixto Pequeño en Stock");
                                        alert.setPositiveButton("OK",null);
                                        alert.show();
                                    }
                                }, 500);


                            }
                            else{

                                clien.setMixto_Pequeno(Integer.parseInt(Mixto_Pequeno));


                                ValoresPedido persona=null;

                                persona=new ValoresPedido();
                                Double valor=Double.valueOf(twoDForm.format(Double.parseDouble(Mixto_Pequeno)*PMixto_Pequeno));

                                Pedido.append("Mixto Pequeño: " + Mixto_Pequeno+ " X $"+PMixto_Pequeno+" = $"+valor+"\n\n");

                                persona.setNombreValorPedido("MIXTO PEQUEÑO");
                                persona.setLoteValorPedido(lote);
                                persona.setCantidadValorPedido(Integer.parseInt(Mixto_Pequeno));
                                persona.setPrecioValorPedido(PMixto_Pequeno);
                                persona.setTotalValorPedido(valor);

                                valoresproductoList.add(persona);
                            }



                        }






                    }
                    catch (Exception ex){


                    }
                    db.close();

                }
                if(Papa_Hoja_Gigante!="" && Papa_Hoja_Gigante!="0"){

                    SQLiteDatabase db = conn.getReadableDatabase();

                    try{




                        //select * from usuarios
                        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_CANTIDAD_STOCK+","+Utilidades.CAMPO_ID_LOTE_STOCK+" FROM " + Utilidades.TABLA_STOCK+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = 'PAPA HOJA GIGANTE'", null);

                        if(cursor.getCount() <= 0){

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                    alert.setTitle("Stock");
                                    alert.setMessage("No existe Papa Hoja Gigante en Stock");
                                    alert.setPositiveButton("OK",null);
                                    alert.show();
                                }
                            }, 500);



                        }
                        else{

                            Double cantidad=0.0;

                            Integer lote=0;

                            while (cursor.moveToNext()) {

                                cantidad=cursor.getDouble(0);
                                lote=cursor.getInt(1);

                            }
                            if(Double.parseDouble(Papa_Hoja_Gigante)>cantidad){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                        alert.setTitle("Stock");
                                        alert.setMessage("No hay suficiente Papa Hoja Gigante en Stock");
                                        alert.setPositiveButton("OK",null);
                                        alert.show();
                                    }
                                }, 500);


                            }
                            else{

                                clien.setPapa_Hoja_Gigante(Integer.parseInt(Papa_Hoja_Gigante));

                                ValoresPedido persona=null;

                                persona=new ValoresPedido();
                                Double valor=Double.valueOf(twoDForm.format(Double.parseDouble(Papa_Hoja_Gigante)*PPapa_Hoja_Gigante));

                                Pedido.append("Papa Hoja Gigante: " + Papa_Hoja_Gigante+ " X $"+PPapa_Hoja_Gigante+" = $"+valor+"\n\n");

                                persona.setNombreValorPedido("PAPA HOJA GIGANTE");
                                persona.setLoteValorPedido(lote);
                                persona.setCantidadValorPedido(Integer.parseInt(Papa_Hoja_Gigante));
                                persona.setPrecioValorPedido(PPapa_Hoja_Gigante);
                                persona.setTotalValorPedido(valor);

                                valoresproductoList.add(persona);
                            }



                        }






                    }
                    catch (Exception ex){


                    }
                    db.close();

                }
                if(Papa_Hoja_Grande!="" && Papa_Hoja_Grande!="0"){

                    SQLiteDatabase db = conn.getReadableDatabase();

                    try{




                        //select * from usuarios
                        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_CANTIDAD_STOCK+","+Utilidades.CAMPO_ID_LOTE_STOCK+" FROM " + Utilidades.TABLA_STOCK+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = 'PAPA HOJA GRANDE'", null);

                        if(cursor.getCount() <= 0){

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                    alert.setTitle("Stock");
                                    alert.setMessage("No existe Papa Hoja Grande en Stock");
                                    alert.setPositiveButton("OK",null);
                                    alert.show();
                                }
                            }, 500);



                        }
                        else{

                            Double cantidad=0.0;

                            Integer lote=0;

                            while (cursor.moveToNext()) {

                                cantidad=cursor.getDouble(0);
                                lote=cursor.getInt(1);

                            }
                            if(Double.parseDouble(Papa_Hoja_Grande)>cantidad){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                        alert.setTitle("Stock");
                                        alert.setMessage("No hay suficiente Papa Hoja Grande en Stock");
                                        alert.setPositiveButton("OK",null);
                                        alert.show();
                                    }
                                }, 500);


                            }
                            else{

                                clien.setPapa_Hoja_Grande(Integer.parseInt(Papa_Hoja_Grande));

                                ValoresPedido persona=null;

                                persona=new ValoresPedido();
                                Double valor=Double.valueOf(twoDForm.format(Double.parseDouble(Papa_Hoja_Grande)*PPapa_Hoja_Grande));

                                Pedido.append("Papa Hoja Grande: " + Papa_Hoja_Grande+ " X $"+PPapa_Hoja_Grande+" = $"+valor+"\n\n");

                                persona.setNombreValorPedido("PAPA HOJA GRANDE");
                                persona.setLoteValorPedido(lote);
                                persona.setCantidadValorPedido(Integer.parseInt(Papa_Hoja_Grande));
                                persona.setPrecioValorPedido(PPapa_Hoja_Grande);
                                persona.setTotalValorPedido(valor);

                                valoresproductoList.add(persona);
                            }



                        }






                    }
                    catch (Exception ex){


                    }
                    db.close();

                }
                if(Papa_Hoja_Mediana!="" && Papa_Hoja_Mediana!="0"){
                    SQLiteDatabase db = conn.getReadableDatabase();

                    try{




                        //select * from usuarios
                        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_CANTIDAD_STOCK+","+Utilidades.CAMPO_ID_LOTE_STOCK+" FROM " + Utilidades.TABLA_STOCK+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = 'PAPA HOJA MEDIANA'", null);

                        if(cursor.getCount() <= 0){

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                    alert.setTitle("Stock");
                                    alert.setMessage("No existe Papa Hoja Mediana en Stock");
                                    alert.setPositiveButton("OK",null);
                                    alert.show();
                                }
                            }, 500);



                        }
                        else{

                            Double cantidad=0.0;

                            Integer lote=0;

                            while (cursor.moveToNext()) {

                                cantidad=cursor.getDouble(0);
                                lote=cursor.getInt(1);

                            }
                            if(Double.parseDouble(Papa_Hoja_Mediana)>cantidad){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                        alert.setTitle("Stock");
                                        alert.setMessage("No hay suficiente Papa Hoja Mediana en Stock");
                                        alert.setPositiveButton("OK",null);
                                        alert.show();
                                    }
                                }, 500);


                            }
                            else{

                                clien.setPapa_Hoja_Mediana(Integer.parseInt(Papa_Hoja_Mediana));

                                ValoresPedido persona=null;

                                persona=new ValoresPedido();
                                Double valor=Double.valueOf(twoDForm.format(Double.parseDouble(Papa_Hoja_Mediana)*PPapa_Hoja_Mediana));

                                Pedido.append("Papa Hoja Mediana: " + Papa_Hoja_Mediana+ " X $"+PPapa_Hoja_Mediana+" = $"+valor+"\n\n");

                                persona.setNombreValorPedido("PAPA HOJA MEDIANA");
                                persona.setLoteValorPedido(lote);
                                persona.setCantidadValorPedido(Integer.parseInt(Papa_Hoja_Mediana));
                                persona.setPrecioValorPedido(PPapa_Hoja_Mediana);
                                persona.setTotalValorPedido(valor);

                                valoresproductoList.add(persona);
                            }



                        }






                    }
                    catch (Exception ex){


                    }
                    db.close();



                }
                if(Papa_Pequena!="" && Papa_Pequena!="0"){

                    SQLiteDatabase db = conn.getReadableDatabase();

                    try{




                        //select * from usuarios
                        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_CANTIDAD_STOCK+","+Utilidades.CAMPO_ID_LOTE_STOCK+" FROM " + Utilidades.TABLA_STOCK+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = 'PAPA PEQUEÑA'", null);

                        if(cursor.getCount() <= 0){

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                    alert.setTitle("Stock");
                                    alert.setMessage("No existe Papa Pequeña en Stock");
                                    alert.setPositiveButton("OK",null);
                                    alert.show();
                                }
                            }, 500);



                        }
                        else{

                            Double cantidad=0.0;

                            Integer lote=0;

                            while (cursor.moveToNext()) {

                                cantidad=cursor.getDouble(0);
                                lote=cursor.getInt(1);

                            }
                            if(Double.parseDouble(Papa_Pequena)>cantidad){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                        alert.setTitle("Stock");
                                        alert.setMessage("No hay suficiente Papa Pequeña en Stock");
                                        alert.setPositiveButton("OK",null);
                                        alert.show();
                                    }
                                }, 500);


                            }
                            else{

                                clien.setPapa_Pequena(Integer.parseInt(Papa_Pequena));


                                ValoresPedido persona=null;

                                persona=new ValoresPedido();
                                Double valor=Double.valueOf(twoDForm.format(Double.parseDouble(Papa_Pequena)*PPapa_Pequena));

                                Pedido.append("Papa Pequeña: " + Papa_Pequena+ " X $"+PPapa_Pequena+" = $"+valor+"\n\n");

                                persona.setNombreValorPedido("PAPA PEQUEÑA");
                                persona.setLoteValorPedido(lote);
                                persona.setCantidadValorPedido(Integer.parseInt(Papa_Pequena));
                                persona.setPrecioValorPedido(PPapa_Pequena);
                                persona.setTotalValorPedido(valor);

                                valoresproductoList.add(persona);
                            }



                        }






                    }
                    catch (Exception ex){


                    }
                    db.close();




                }
                if(Papa_Picada_Gigante!="" && Papa_Picada_Gigante!="0"){

                    SQLiteDatabase db = conn.getReadableDatabase();

                    try{




                        //select * from usuarios
                        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_CANTIDAD_STOCK+","+Utilidades.CAMPO_ID_LOTE_STOCK+" FROM " + Utilidades.TABLA_STOCK+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = 'PAPA PICADA GIGANTE'", null);

                        if(cursor.getCount() <= 0){

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                    alert.setTitle("Stock");
                                    alert.setMessage("No existen Papas Picadas Gigantes en Stock");
                                    alert.setPositiveButton("OK",null);
                                    alert.show();
                                }
                            }, 500);



                        }
                        else{

                            Double cantidad=0.0;
                            Integer lote=0;

                            while (cursor.moveToNext()) {

                                cantidad=cursor.getDouble(0);
                                lote=cursor.getInt(1);

                            }
                            if(Double.parseDouble(Papa_Picada_Gigante)>cantidad){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                        alert.setTitle("Stock");
                                        alert.setMessage("No hay suficienes Papas Picadas Gigantes en Stock");
                                        alert.setPositiveButton("OK",null);
                                        alert.show();
                                    }
                                }, 500);


                            }
                            else{







                                clien.setPapa_Picada_Gigante(Integer.parseInt(Papa_Picada_Gigante));

                                Double valor=Double.valueOf(twoDForm.format(Double.parseDouble(Papa_Picada_Gigante)*PPapa_Picada_Gigante));

                                Pedido.append("Papa Picada Gigante: " + Papa_Picada_Gigante + " X $"+PPapa_Picada_Gigante+" = $"+valor+"\n\n");

                                ValoresPedido persona=null;

                                persona=new ValoresPedido();

                                persona.setNombreValorPedido("PAPA PICADA GIGANTE");
                                persona.setLoteValorPedido(lote);

                                persona.setCantidadValorPedido(Integer.parseInt(Papa_Picada_Gigante));

                                persona.setPrecioValorPedido(PPapa_Picada_Gigante);

                                persona.setTotalValorPedido(valor);


                                valoresproductoList.add(persona);
                            }



                        }






                    }
                    catch (Exception ex){


                    }
                    db.close();



                }
                if(Papa_Picada_Grande!="" && Papa_Picada_Grande!="0"){

                    SQLiteDatabase db = conn.getReadableDatabase();

                    try{




                        //select * from usuarios
                        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_CANTIDAD_STOCK+","+Utilidades.CAMPO_ID_LOTE_STOCK+" FROM " + Utilidades.TABLA_STOCK+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = 'PAPA PICADA GRANDE'", null);

                        if(cursor.getCount() <= 0){

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                    alert.setTitle("Stock");
                                    alert.setMessage("No existe Papa Picada Grande en Stock");
                                    alert.setPositiveButton("OK",null);
                                    alert.show();
                                }
                            }, 500);



                        }
                        else{

                            Double cantidad=0.0;

                            Integer lote=0;

                            while (cursor.moveToNext()) {

                                cantidad=cursor.getDouble(0);
                                lote=cursor.getInt(1);

                            }
                            if(Double.parseDouble(Papa_Picada_Grande)>cantidad){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                        alert.setTitle("Stock");
                                        alert.setMessage("No hay suficiente Papa Picada Grande en Stock");
                                        alert.setPositiveButton("OK",null);
                                        alert.show();
                                    }
                                }, 500);


                            }
                            else{

                                clien.setPapa_Picada_Grande(Integer.parseInt(Papa_Picada_Grande));

                                ValoresPedido persona=null;

                                persona=new ValoresPedido();
                                Double valor=Double.valueOf(twoDForm.format(Double.parseDouble(Papa_Picada_Grande)*PPPapa_Picada_Grande));

                                Pedido.append("Papa Picada Grande: " + Papa_Picada_Grande +" X $"+PPPapa_Picada_Grande+" = $"+valor+"\n\n");

                                persona.setNombreValorPedido("PAPA PICADA GRANDE");
                                persona.setLoteValorPedido(lote);
                                persona.setCantidadValorPedido(Integer.parseInt(Papa_Picada_Grande));
                                persona.setPrecioValorPedido(PPPapa_Picada_Grande);
                                persona.setTotalValorPedido(valor);

                                valoresproductoList.add(persona);
                            }



                        }






                    }
                    catch (Exception ex){


                    }
                    db.close();

                }
                if(Papa_Picada_Mediana!="" && Papa_Picada_Mediana!="0"){
                    SQLiteDatabase db = conn.getReadableDatabase();

                    try{




                        //select * from usuarios
                        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_CANTIDAD_STOCK+","+Utilidades.CAMPO_ID_LOTE_STOCK+" FROM " + Utilidades.TABLA_STOCK+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = 'PAPA PICADA MEDIANA'", null);

                        if(cursor.getCount() <= 0){

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                    alert.setTitle("Stock");
                                    alert.setMessage("No existe Papa Picada Mediana en Stock");
                                    alert.setPositiveButton("OK",null);
                                    alert.show();
                                }
                            }, 500);



                        }
                        else{

                            Double cantidad=0.0;

                            Integer lote=0;

                            while (cursor.moveToNext()) {

                                cantidad=cursor.getDouble(0);
                                lote=cursor.getInt(1);

                            }
                            if(Double.parseDouble(Papa_Picada_Mediana)>cantidad){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                        alert.setTitle("Stock");
                                        alert.setMessage("No hay suficiente Papa Picada Mediana en Stock");
                                        alert.setPositiveButton("OK",null);
                                        alert.show();
                                    }
                                }, 500);


                            }
                            else{

                                clien.setPapa_Picada_Mediana(Integer.parseInt(Papa_Picada_Mediana));

                                ValoresPedido persona=null;

                                persona=new ValoresPedido();
                                Double valor=Double.valueOf(twoDForm.format(Double.parseDouble(Papa_Picada_Mediana)*PPapa_Picada_Mediana));

                                Pedido.append("Papa Picada Mediana: " + Papa_Picada_Mediana +" X $"+PPapa_Picada_Mediana+" = $"+valor+"\n\n");

                                persona.setNombreValorPedido("PAPA PICADA MEDIANA");
                                persona.setLoteValorPedido(lote);
                                persona.setCantidadValorPedido(Integer.parseInt(Papa_Picada_Mediana));
                                persona.setPrecioValorPedido(PPapa_Picada_Mediana);
                                persona.setTotalValorPedido(valor);

                                valoresproductoList.add(persona);
                            }



                        }






                    }
                    catch (Exception ex){


                    }
                    db.close();

                }
                if(Papa_Picada_Pequena!="" && Papa_Picada_Pequena!="0"){
                    SQLiteDatabase db = conn.getReadableDatabase();

                    try{




                        //select * from usuarios
                        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_CANTIDAD_STOCK+","+Utilidades.CAMPO_ID_LOTE_STOCK+" FROM " + Utilidades.TABLA_STOCK+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = 'PAPA PICADA PEQUEÑA'", null);

                        if(cursor.getCount() <= 0){

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                    alert.setTitle("Stock");
                                    alert.setMessage("No existe Papa Picada Pequeña en Stock");
                                    alert.setPositiveButton("OK",null);
                                    alert.show();
                                }
                            }, 500);



                        }
                        else{

                            Double cantidad=0.0;

                            Integer lote=0;

                            while (cursor.moveToNext()) {

                                cantidad=cursor.getDouble(0);
                                lote=cursor.getInt(1);

                            }
                            if(Double.parseDouble(Papa_Picada_Pequena)>cantidad){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                        alert.setTitle("Stock");
                                        alert.setMessage("No hay suficiente Papa Picada Pequeña en Stock");
                                        alert.setPositiveButton("OK",null);
                                        alert.show();
                                    }
                                }, 500);


                            }
                            else{


                                clien.setPapa_Picada_Pequena(Integer.parseInt(Papa_Picada_Pequena));

                                ValoresPedido persona=null;

                                persona=new ValoresPedido();
                                Double valor=Double.valueOf(twoDForm.format(Double.parseDouble(Papa_Picada_Pequena)*PPapa_Picada_Pequena));

                                Pedido.append("Papa Picada Pequeña: " + Papa_Picada_Pequena +" X $"+PPapa_Picada_Pequena+" = $"+valor+"\n\n");

                                persona.setNombreValorPedido("PAPA PICADA PEQUEÑA");
                                persona.setLoteValorPedido(lote);
                                persona.setCantidadValorPedido(Integer.parseInt(Papa_Picada_Pequena));
                                persona.setPrecioValorPedido(PPapa_Picada_Pequena);
                                persona.setTotalValorPedido(valor);

                                valoresproductoList.add(persona);
                            }



                        }






                    }
                    catch (Exception ex){


                    }
                    db.close();
                }
                if(Tostado_Tiras!="" && Tostado_Tiras!="0"){
                    SQLiteDatabase db = conn.getReadableDatabase();

                    try{




                        //select * from usuarios
                        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_CANTIDAD_STOCK+","+Utilidades.CAMPO_ID_LOTE_STOCK+" FROM " + Utilidades.TABLA_STOCK+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = 'TOSTADO TIRAS'", null);

                        if(cursor.getCount() <= 0){

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                    alert.setTitle("Stock");
                                    alert.setMessage("No existe Tostado en Tiras en Stock");
                                    alert.setPositiveButton("OK",null);
                                    alert.show();
                                }
                            }, 500);



                        }
                        else{

                            Double cantidad=0.0;

                            Integer lote=0;

                            while (cursor.moveToNext()) {

                                cantidad=cursor.getDouble(0);
                                lote=cursor.getInt(1);

                            }
                            if(Double.parseDouble(Tostado_Tiras)>cantidad){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(ClienteActivity.this);
                                        alert.setTitle("Stock");
                                        alert.setMessage("No hay suficiente Tostado en Tiras en Stock");
                                        alert.setPositiveButton("OK",null);
                                        alert.show();
                                    }
                                }, 500);


                            }
                            else{


                                clien.setTostado_Tiras(Integer.parseInt(Tostado_Tiras));

                                ValoresPedido persona=null;

                                persona=new ValoresPedido();
                                Double valor=Double.valueOf(twoDForm.format(Double.parseDouble(Tostado_Tiras)*PTostado_Tiras));

                                Pedido.append("Tostado Tiras: " + Tostado_Tiras +" X $"+PTostado_Tiras+" = $"+valor+"\n\n");

                                persona.setNombreValorPedido("TOSTADO TIRAS");
                                persona.setLoteValorPedido(lote);
                                persona.setCantidadValorPedido(Integer.parseInt(Tostado_Tiras));
                                persona.setPrecioValorPedido(PTostado_Tiras);
                                persona.setTotalValorPedido(valor);

                                valoresproductoList.add(persona);
                            }



                        }






                    }
                    catch (Exception ex){


                    }
                    db.close();

                }



                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                btnAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();

                        //Intent reg4 = new Intent(ClienteActivity.this,RegistroPedidosActivity.class);
                        //startActivity(reg4);



                        Button btnCancelar2;
                        myDialog2.setContentView(R.layout.popup_clientes);


                        filtroClientes2 = (EditText)myDialog2.findViewById(R.id.filtroClientes2);


                        btnCancelar2 = (Button)myDialog2.findViewById(R.id.btnPopupCliCancelar);

                        listViewClientes= (ListView)myDialog2.findViewById(R.id.listViewTodosClientes);

                        btnCancelar2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myDialog2.dismiss();
                            }
                        });



                        btnCancelar2.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(event.getAction() == MotionEvent.ACTION_DOWN){
                                    v.getBackground().setAlpha(150);
                                }else if(event.getAction() == MotionEvent.ACTION_UP){
                                    v.getBackground().setAlpha(255);
                                }
                                return false;
                            }
                        });



                        try {

                            SQLiteDatabase db = conn.getReadableDatabase();

                            Cliente usuario = null;
                            listaClientes = new ArrayList<Cliente>();




                            RegistroCliente usuario2=null;
                            listaRegClientes=new ArrayList<RegistroCliente>();


                            UnionCR usuario3=null;

                            listaUnion=new ArrayList<UnionCR>();




                            //select * from usuarios
                            Cursor cursor2=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_REG_CLIENTE,null);



                            while (cursor2.moveToNext()){
                                usuario2=new RegistroCliente();
                                usuario3= new UnionCR();


                                usuario2.setTipoidRegCliente(cursor2.getInt(1));
                                usuario2.setIdentificacionRegCliente(cursor2.getString(2));
                                usuario3.setIdentificacionClienteUnion(cursor2.getString(2));
                                usuario2.setNombreRegCliente(cursor2.getString(3));
                                usuario3.setNombreClienteUnion(cursor2.getString(3));
                                usuario2.setRazonRegCliente(cursor2.getString(4));
                                usuario2.setCorporacionidRegCliente(cursor2.getInt(5));
                                usuario2.setTipoclienteidRegCliente(cursor2.getInt(6));
                                usuario2.setTipoproveedoridRegCliente(cursor2.getInt(7));
                                usuario2.setCodigoRegCliente(cursor2.getString(8));
                                usuario2.setCiudadidRegCliente(cursor2.getInt(9));
                                usuario2.setDireccionRegCliente(cursor2.getString(10));
                                usuario2.setLatitudRegCliente(cursor2.getDouble(11));
                                usuario2.setLongitudRegCliente(cursor2.getDouble(12));
                                usuario2.setEmailRegCliente(cursor2.getString(13));
                                usuario2.setDescripcionRegCliente(cursor2.getString(14));
                                usuario2.setVendedoridRegCliente(cursor2.getInt(15));

          /*  Log.d("CliensId:",usuario.getIdCliente().toString());

            Log.d("CliensIdentif:",usuario.getIdentificacionCliente());

            Log.d("CliensNom:",usuario.getNombreCliente());
            Log.d("CliensRaz:",usuario.getRazonCliente());

            Log.d("CliensFec:",usuario.getFechaCliente());
            Log.d("CliensLati:",usuario.getLatitudCliente().toString());
            Log.d("CliensLong:",usuario.getLongitudCliente().toString());

            Log.d("CliensDirec:",usuario.getDireccionCliente());
            Log.d("CliensTelf:",usuario.getTelefonoCliente());
            Log.d("CliensEmai:",usuario.getEmailCliente());

            Log.d("CliensDesc:",usuario.getDescripcionCliente());

            */

                                listaRegClientes.add(usuario2);
                                listaUnion.add(usuario3);
                            }


                            listaInformacion=new ArrayList<String>();


                            for (int i=0; i<listaRegClientes.size();i++){
                                listaInformacion.add(listaRegClientes.get(i).getNombreRegCliente());

                            }

                            //select * from usuarios
                            Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_CLIENTE, null);

                            while (cursor.moveToNext()) {
                                usuario = new Cliente();
                                usuario3= new UnionCR();
                                usuario.setIdCliente(cursor.getInt(1));
                                usuario.setIdentificacionCliente(cursor.getString(2));
                                usuario3.setIdentificacionClienteUnion(cursor.getString(2));
                                usuario.setNombreCliente(cursor.getString(3));
                                usuario3.setNombreClienteUnion(cursor.getString(3));
                                usuario.setRazonCliente(cursor.getString(4));
                                usuario.setFechaCliente(cursor.getString(5));
                                usuario.setLatitudCliente(cursor.getDouble(6));
                                usuario.setLongitudCliente(cursor.getDouble(7));
                                usuario.setDireccionCliente(cursor.getString(8));
                                usuario.setTelefonoCliente(cursor.getString(9));
                                usuario.setEmailCliente(cursor.getString(10));
                                usuario.setDescripcionCliente(cursor.getString(11));

                                Log.d("CliensId:", usuario.getIdCliente().toString());

                                Log.d("CliensIdentif:", usuario.getIdentificacionCliente());

                                Log.d("CliensNom:", usuario.getNombreCliente());
                                Log.d("CliensRaz:", usuario.getRazonCliente());

                                Log.d("CliensFec:", usuario.getFechaCliente());
                                Log.d("CliensLati:", usuario.getLatitudCliente().toString());
                                Log.d("CliensLong:", usuario.getLongitudCliente().toString());

                                Log.d("CliensDirec:", usuario.getDireccionCliente());
                                Log.d("CliensTelf:", usuario.getTelefonoCliente());
                                Log.d("CliensEmai:", usuario.getEmailCliente());

                                Log.d("CliensDesc:", usuario.getDescripcionCliente());

                                listaClientes.add(usuario);
                                listaUnion.add(usuario3);
                            }




                            for (int i=0; i<listaClientes.size();i++){
                                listaInformacion.add(listaClientes.get(i).getNombreCliente());

                            }



                            listViewClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {





                                    Object o = listViewClientes.getItemAtPosition(pos);

                                    String nombr = (String)o;

                                    UnionCR user=listaUnion.get(pos);
                                    //  String nom=user.getIdentificacionCliente();

                                    // String keyword = user.toString();
                                    Integer index = -1;
                                    for (int i = 0; i < listaUnion.size(); i++) {
                                        if (listaUnion.get(i).getNombreClienteUnion().equals(nombr)) {
                                            index = i;



                                        }

                                        Log.d("valinde",index.toString());
                                    }

                                    UnionCR user34=listaUnion.get(index);


                                    String valCliente=user34.getIdentificacionClienteUnion();

                                    String nomCLiente=user34.getNombreClienteUnion();



                                    Bundle baske = new Bundle();
                                    baske.putString("idCliente", valCliente);
                                    baske.putString("nomCliente", nomCLiente);
                                    baske.putInt("idEmpleado",idPasado);
                                    baske.putSerializable("pedido",(Serializable)valoresproductoList);
                                    baske.putSerializable("producto",clien);





                                    Toast.makeText(ClienteActivity.this, "Cliente: " +nomCLiente, Toast.LENGTH_SHORT).show();
                                    mDrawerLayout.closeDrawer(GravityCompat.START);


                                    Intent intent5 = new Intent(ClienteActivity.this, RegistroPedidosActivity.class);
                                   intent5.putExtras(baske);
                                    startActivity(intent5);


                                        myDialog2.dismiss();


                                }
                            });



                            adaptador=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,listaInformacion);
                            listViewClientes.setAdapter(adaptador);


                            filtroClientes2.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {

                                    (ClienteActivity.this).adaptador.getFilter().filter(s);


                                }

                                @Override
                                public void afterTextChanged(Editable s) {

                                }
                            });




                        }
                        catch (SQLiteException e){
                            if (e.getMessage().contains("no such table")){

                                Toast.makeText(getApplicationContext(), "No existen Datos de Clientes", Toast.LENGTH_LONG).show();
                            }
                        }



                        myDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        myDialog2.show();

                    }
                });

                btnCancelar.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if(event.getAction() == MotionEvent.ACTION_DOWN){
                            v.getBackground().setAlpha(150);
                        }else if(event.getAction() == MotionEvent.ACTION_UP){
                            v.getBackground().setAlpha(255);
                        }
                        return false;
                    }
                });

                btnAceptar.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if(event.getAction() == MotionEvent.ACTION_DOWN){
                            v.getBackground().setAlpha(150);
                        }else if(event.getAction() == MotionEvent.ACTION_UP){
                            v.getBackground().setAlpha(255);
                        }
                        return false;
                    }
                });





                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();


            }




        });




        ArrayAdapter<CharSequence> adaptador6=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaListaPrecio);

        comboListaPrecio.setAdapter(adaptador6);

        comboListaPrecio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                if (position!=0){
                   /* txtDocumento.setText(personasList.get(position-1).getId().toString());
                    txtNombre.setText(personasList.get(position-1).getNombre());
                    txtTelefono.setText(personasList.get(position-1).getClave());

                    */

                    listaprecioPedido = listaprecioList.get(position-1).getIdSpinListaPrecio();


                    SQLiteDatabase db=conn.getReadableDatabase();
                    String[] parametros={listaprecioPedido.toString()};

                    String[] names2 = {"Chifle Dulce Tira", "Chifle Sal Tira", "Cuerito Grande", "Cuerito Mediano", "Cuerito Pequeño Tira", "Maní Dulce Paq. 20", "Maní Dulce Paq. 10", "Maní Sal", "Mixto Grande", "Mixto Mediano", "Mixto Pequeño", "Papa Hoja Gigante", "Papa Hoja Grande", "Papa Hoja Mediana", "Papa Pequeña", "Papa Picada Gigante", "Papa Picada Grande", "Papa Picada Mediana", "Papa Picada Pequeña", "Tostado Tiras"};
                    String[] places2 = {"Chifle Dulce Tiras x 12", "Chifle Sal Tiras x 12", "Cuerito Grande", "Cuerito Mediano", "Cuerito Pequeño Tira x 12", "Maní Dulce Paquete x 20", "Maní Dulce Paquete x 10", "Maní Sal Paquete x 10", "Mixto Grande", "Mixto Mediano", "Mixto Pequeño", "Papa Hoja Gigante", "Papa Hoja Grande", "Papa Hoja Mediana", "Papa Pequeña x 12", "Papa Picada Gigante", "Papa Picada Grande", "Papa Picada Mediana", "Papa Picada Pequeña", "Tostado Tiras x 12"};
                    final String[] prices2 = {"$", "$", "$", "$", "$", "$", "$", "$", "$", "$", "$", "$", "$", "$", "$", "$", "$", "$", "$", "$"};
                    int[] images2 = {R.drawable.chifle_dulce_tira_x_12, R.drawable.chifle_sal_tira_x_12, R.drawable.cuerito_grande, R.drawable.cuerito_mediano, R.drawable.cuerito_pequeno_tira_x_12, R.drawable.mani_dulce_paq_20_y_10, R.drawable.mani_dulce_paq_20_y_10, R.drawable.mani_sal_paq_x_10, R.drawable.mixto_grande, R.drawable.mixto_mediano, R.drawable.mixto_pequeno, R.drawable.papa_hoja_gigante, R.drawable.papa_hoja_grande, R.drawable.papa_hoja_mediana, R.drawable.papa_pequena_x12, R.drawable.papa_picada_gigante, R.drawable.papa_picada_grande, R.drawable.papa_picada_mediana, R.drawable.papa_picada_pequena, R.drawable.tostado_tiras_x_10};



                    for (int i = 0; i<prices2.length; i++){
                        Log.i("PRECIOS: ", prices2[i]);
                    }




                    try {
                        //select nombre,telefono from usuario where codigo=?
                        Cursor cursor=db.rawQuery("SELECT "+Utilidades.CAMPO_NOMBREPROD_PRECIO+","+Utilidades.CAMPO_VALOR_PRECIO+
                                " FROM "+Utilidades.TABLA_PRECIO+" WHERE "+Utilidades.CAMPO_LISTA_PRECIO+"=? ",parametros);

                        while (cursor.moveToNext()){

                            String nombr=cursor.getString(0);
                            Double valorp;


                            switch(nombr){

                                case "CHIFLE DULCE TIRA":
                                    valorp=cursor.getDouble(1);
                                    prices2[0]+=valorp.toString();

                                    break;

                                case "CHIFLE SAL TIRA":
                                    valorp=cursor.getDouble(1);
                                    prices2[1]+=valorp.toString();

                                    break;

                                case "CUERITO GRANDE":
                                    valorp=cursor.getDouble(1);
                                    prices2[2]+=valorp.toString();

                                    break;

                                case "CUERITO MEDIANO":
                                    valorp=cursor.getDouble(1);
                                    prices2[3]+=valorp.toString();

                                    break;

                                case "CUERITO PEQUEÑO TIRA":
                                    valorp=cursor.getDouble(1);
                                    prices2[4]+=valorp.toString();

                                    break;

                                case "MANI DULCE PAQ. 20":
                                    valorp=cursor.getDouble(1);
                                    prices2[5]+=valorp.toString();

                                    break;

                                case "MANI DULCE PAQ. 10":
                                    valorp=cursor.getDouble(1);
                                    prices2[6]+=valorp.toString();

                                    break;

                                case "MANI SAL":
                                    valorp=cursor.getDouble(1);
                                    prices2[7]+=valorp.toString();

                                    break;

                                case "MIXTO GRANDE":
                                    valorp=cursor.getDouble(1);
                                    prices2[8]+=valorp.toString();

                                    break;

                                case "MIXTO MEDIANO":
                                    valorp=cursor.getDouble(1);
                                    prices2[9]+=valorp.toString();

                                    break;

                                case "MIXTO PEQUEÑO":
                                    valorp=cursor.getDouble(1);
                                    prices2[10]+=valorp.toString();

                                    break;

                                case "PAPA HOJA GIGANTE":
                                    valorp=cursor.getDouble(1);
                                    prices2[11]+=valorp.toString();

                                    break;

                                case "PAPA HOJA GRANDE":
                                    valorp=cursor.getDouble(1);
                                    prices2[12]+=valorp.toString();

                                    break;

                                case "PAPA HOJA MEDIANA":
                                    valorp=cursor.getDouble(1);
                                    prices2[13]+=valorp.toString();
                                    break;

                                case "PAPA PEQUEÑA":
                                    valorp=cursor.getDouble(1);
                                    prices2[14]+=valorp.toString();

                                    break;

                                case "PAPA PICADA GIGANTE":
                                    valorp=cursor.getDouble(1);
                                    prices2[15]+=valorp.toString();

                                    break;

                                case "PAPA PICADA GRANDE":
                                    valorp=cursor.getDouble(1);
                                    prices2[16]+=valorp.toString();

                                    break;

                                case "PAPA PICADA MEDIANA":
                                    valorp=cursor.getDouble(1);
                                    prices2[17]+=valorp.toString();

                                    break;

                                case "PAPA PICADA PEQUEÑA":
                                    valorp=cursor.getDouble(1);
                                    prices2[18]+=valorp.toString();

                                    break;

                                case "TOSTADO TIRAS":
                                    valorp=cursor.getDouble(1);
                                    prices2[19]+=valorp.toString();

                                    break;


                            }







                        }




                    }catch (Exception e){

                        Log.d("elerror",e.toString());




                    }


                    db.close();


                    for (int i = 0; i<prices2.length; i++){
                        Log.i("PRECIOS2: ", prices2[i]);
                    }

                    foodsList = new ArrayList<>();


                    for (int i = 0; i < names2.length; i++) {

                        foodsList.add(new FoodModel(names2[i], places2[i], prices2[i], images2[i]));

                    }


                    adapter = new FoodDeliverAdapter(getApplicationContext(), foodsList);
                    LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplicationContext());

                    RecyclerView.LayoutManager mLayoutManager2 = linearLayoutManager2;

                    recyclerView.setLayoutManager(mLayoutManager2);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);


                }else{
                    listaprecioPedido = 0;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                listaprecioPedido = 0;
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {
                            case R.id.productos:
                                menuItem.setChecked(true);
                                Bundle basket = new Bundle();
                                basket.putString("identificacion", valorPasado);
                                basket.putString("nombres", nombrePasado);
                                basket.putString("apellidos", apellidoPasado);
                                basket.putString("correo", emailPasado);
                                basket.putInt("id",idPasado);

                                Toast.makeText(ClienteActivity.this, "Pantalla: " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                mDrawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent = new Intent(ClienteActivity.this, ClienteActivity.class);
                                intent.putExtras(basket);
                                startActivity(intent);
                                finish();
                                return true;
                            case R.id.carrito:
                                menuItem.setChecked(true);

                                Bundle basket2 = new Bundle();
                                basket2.putString("identificacion", valorPasado);
                                basket2.putString("nombres", nombrePasado);
                                basket2.putString("apellidos", apellidoPasado);
                                basket2.putString("correo", emailPasado);
                                basket2.putInt("id",idPasado);

                                Toast.makeText(ClienteActivity.this, "Pantalla: " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                mDrawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent2 = new Intent(ClienteActivity.this, CarritoActivity.class);
                                intent2.putExtras(basket2);
                                startActivity(intent2);
                                return true;
                            case R.id.stock:
                                menuItem.setChecked(true);

                                Bundle basket3 = new Bundle();
                                basket3.putString("identificacion", valorPasado);
                                basket3.putString("nombres", nombrePasado);
                                basket3.putString("apellidos", apellidoPasado);
                                basket3.putString("correo", emailPasado);
                                basket3.putInt("id",idPasado);

                                Toast.makeText(ClienteActivity.this, "Pantalla: " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                mDrawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent3 = new Intent(ClienteActivity.this, StockActivity.class);
                                intent3.putExtras(basket3);
                                startActivity(intent3);
                                return true;
                            case R.id.perfil:
                                menuItem.setChecked(true);
                                Bundle basket4 = new Bundle();
                                basket4.putString("identificacion", valorPasado);
                                basket4.putString("nombres", nombrePasado);
                                basket4.putString("apellidos", apellidoPasado);
                                basket4.putString("correo", emailPasado);
                                basket4.putInt("id",idPasado);

                                Toast.makeText(ClienteActivity.this, "Pantalla: " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                mDrawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent4 = new Intent(ClienteActivity.this, PerfilActivity.class);
                                intent4.putExtras(basket4);
                                startActivity(intent4);
                                return true;
                            case R.id.micuenta:
                                menuItem.setChecked(true);

                                Bundle basket6 = new Bundle();
                                basket6.putString("identificacion", valorPasado);
                                basket6.putString("nombres", nombrePasado);
                                basket6.putString("apellidos", apellidoPasado);
                                basket6.putString("correo", emailPasado);
                                basket6.putInt("id",idPasado);


                                Toast.makeText(ClienteActivity.this, "Pantalla: " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                mDrawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent6 = new Intent(ClienteActivity.this, CuentaActivity.class);
                                intent6.putExtras(basket6);
                                startActivity(intent6);
                                return true;

                            case R.id.logout:
                                menuItem.setChecked(true);

                                Bundle basket5 = new Bundle();
                                basket5.putString("identificacion", valorPasado);
                                basket5.putString("nombres", nombrePasado);
                                basket5.putString("apellidos", apellidoPasado);
                                basket5.putString("correo", emailPasado);
                                basket5.putInt("id",idPasado);

                                Toast.makeText(ClienteActivity.this, "Pantalla: " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                mDrawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent5 = new Intent(ClienteActivity.this, LoginActivity.class);
                                intent5.putExtras(basket5);
                                startActivity(intent5);
                                finish();
                                return true;

                            case R.id.servidor:

                                menuItem.setChecked(true);

                                mDrawerLayout.closeDrawer(GravityCompat.START);

                                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which){
                                            case DialogInterface.BUTTON_POSITIVE:
                                                //Yes button clicked



                                                connectivityDetector = new ConnectivityDetector(getBaseContext());


                                                if(connectivityDetector.checkConnectivityStatus()){





                                                    sincronizarBaseDatosStock(valorPasado);





                                                }else{
                                                    connectivityDetector.showAlertDialog(ClienteActivity.this, "ACTIVE EL WI-FI","No hay conexión a Internet");
                                                }

                                                break;

                                            case DialogInterface.BUTTON_NEGATIVE:
                                                //No button clicked
                                                break;
                                        }
                                    }
                                };
                                AlertDialog.Builder builder = new AlertDialog.Builder(ClienteActivity.this);

                                builder.setMessage("¿DESEA SINCRONIZAR CON EL SERVIDOR?").setPositiveButton("SI", dialogClickListener)
                                        .setNegativeButton("NO", dialogClickListener).setTitle("SINCRONIZACIÓN").setIcon(R.drawable.icon_password).show();



                                return true;

                        }
                        return true;
                    }
                });
    }


    public void sincronizarBaseDatosStock(final String valorEmpleado){



        pDialog.setMessage("Por favor, espere....");
        pDialog.setTitle("Sincronizando");

        // pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        pDialog.setCancelable(false);
        showDialog();






        final String TAG = ClienteActivity.class.getName();

        String valNombre = valorEmpleado;

        String url = "http://192.168.0.93:8989/api/stock/empleado";

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());


        Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("identificacion",valNombre);


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.PUT,
                url, new JSONObject(postParam),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        // hideDialog();

                        Log.d(TAG, response.toString());

                        try {

                            JSONObject jObjstatus = response;

                            Boolean status=jObjstatus.getBoolean("status");

                            if(status==true) {


                                SQLiteDatabase db=conn.getWritableDatabase();

                                try {

                                    db.execSQL("delete from " + Utilidades.TABLA_STOCK);
                                }
                                catch (SQLiteException e){
                                    if (e.getMessage().contains("no such table")){

                                       // Toast.makeText(getApplicationContext(), "No existen Datos de Stock", Toast.LENGTH_LONG).show();
                                    }
                                }

                                JSONArray jsonArray = response.getJSONArray("object");

                                for (int i = 0; i < jsonArray.length() ; i++) {

                                    int idStock;

                                    int idBodegaStock;

                                    int idProductoStock;

                                    int idLoteStock;

                                    String anioStock;

                                    String mesStock;

                                    double cantidadStock;

                                    double reservaStock;

                                    double transitoStock;

                                    String fechaStock;

                                    String nombreStock;


                                    JSONObject stock = jsonArray.getJSONObject(i);

                                    idStock = stock.getInt("id");

                                    try {
                                        idBodegaStock = stock.getInt("bodega");
                                    } catch (Exception ex) {
                                        idBodegaStock = 0;
                                    }

                                    try {
                                        idProductoStock = stock.getInt("id");
                                    } catch (Exception ex) {
                                        idProductoStock = 0;
                                    }

                                    try {

                                        idLoteStock = stock.getInt("lote");
                                    } catch (Exception ex) {
                                        idLoteStock = 0;
                                    }

                                    try {
                                        anioStock = stock.getString("anio");
                                    } catch (Exception ex) {
                                        anioStock = "0000";
                                    }

                                    try {
                                        mesStock = stock.getString("mes");
                                    } catch (Exception ex) {
                                        mesStock = "00";
                                    }

                                    try {
                                        cantidadStock = stock.getDouble("cantidad");
                                    } catch (Exception ex) {
                                        cantidadStock = 0;
                                    }

                                    try {
                                        reservaStock = stock.getDouble("reserva");
                                    } catch (Exception ex) {
                                        reservaStock = 0;
                                    }

                                    try {
                                        transitoStock = stock.getDouble("transito");
                                    } catch (Exception ex) {
                                        transitoStock = 0;
                                    }

                                    try{
                                        fechaStock = stock.getString("fhUtlModificacion");
                                    }

                                    catch(Exception ex){
                                        fechaStock = "No hay Fecha";
                                    }


                                    try{
                                        nombreStock = stock.getString("producto");
                                    }

                                    catch(Exception ex){
                                        nombreStock = "No hay Producto";
                                    }

                                    ContentValues values=new ContentValues();
                                    values.put(Utilidades.CAMPO_ID_STOCK,idStock);
                                    values.put(Utilidades.CAMPO_ID_BODEGA_STOCK,idBodegaStock);
                                    values.put(Utilidades.CAMPO_ID_PRODUCTO_STOCK,idProductoStock);
                                    values.put(Utilidades.CAMPO_ID_LOTE_STOCK,idLoteStock);
                                    values.put(Utilidades.CAMPO_ANIO_STOCK,anioStock);
                                    values.put(Utilidades.CAMPO_MES_STOCK,mesStock);
                                    values.put(Utilidades.CAMPO_CANTIDAD_STOCK,cantidadStock);
                                    values.put(Utilidades.CAMPO_RESERVA_STOCK,reservaStock);
                                    values.put(Utilidades.CAMPO_TRANSITO_STOCK,transitoStock);
                                    values.put(Utilidades.CAMPO_FECHA_STOCK,fechaStock);
                                    values.put(Utilidades.CAMPO_NOMBRE_STOCK,nombreStock);



                                    db.insert(Utilidades.TABLA_STOCK,Utilidades.CAMPO_ID_TABLA_STOCK,values);


                                }

                                // mTextViewResultado.setText("USUARIO CORRECTO \n\n"+String.valueOf(id) + " : " + nombre + " \n\n " + edad + " años \n\n " + foto + " \n\n " + error + "\n\n");


                                //  Toast.makeText(getApplicationContext(),
                                //         "Bienvenido: "+idEmpleado+" "+codigoEmpleado+" "+nombresEmpleado+" "+apellidosEmpleado+" "+identificacionEmpleado+" "+profesionEmpleado+" "+domicilioEmpleado+" "+telfdomicilioEmpleado+" "+celularEmpleado+" "+emailEmpleado+" "+extoficinaEmpleado+" "+nivelEmpleado+" "+estadoEmpleado+" "+idoficinaEmpleado+" "+idbancoEMpleado+" "+tipocuentaEmpleado+" "+numcuentaEmpleado+" "+enviarcorreoEmpleado,  Toast.LENGTH_LONG).show();







                                db.close();



                              /*  Toast.makeText(getApplicationContext(),
                                        "Sincronización Correcta",  Toast.LENGTH_LONG).show();


                              llenarCamposTableView();
                               */

                              sincronizarBaseDatosCliente(valorEmpleado);





                            }

                            else {
                                hideDialog();
                                Toast.makeText(getApplicationContext(), "Status falso de Stocks", Toast.LENGTH_LONG).show();

                            }






                        } catch (JSONException e) {


                            e.printStackTrace();
                            hideDialog();

                            Toast.makeText(getApplicationContext(),
                                    "Sincronización Fallida"+e.toString(),  Toast.LENGTH_LONG).show();
                        }

                        //   mTextViewResultado.append(response.toString());
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();
                VolleyLog.d(TAG, "Error: " + error.getMessage());


                Toast.makeText(getApplicationContext(),
                        "Error : "+error.getMessage(),  Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        jsonObjReq.setTag(TAG);
        queue.add(jsonObjReq);

    }



    public void sincronizarBaseDatosCliente(String valorEmpleado) {


       /* pDialog.setMessage("Por favor, espere....");
        pDialog.setTitle("Sincronizando");

        // pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        pDialog.setCancelable(false);
        showDialog();

*/


        final String TAG = LoginActivity.class.getName();

        String valNombre = valorEmpleado;

        String url = "http://192.168.0.93:8989/api/cliente/empleado";

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());


        Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("identificacion",valNombre);


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.PUT,
                url, new JSONObject(postParam),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        // hideDialog();

                        Log.d(TAG, response.toString());

                        try {

                            JSONObject jObjstatus = response;

                            Boolean status=jObjstatus.getBoolean("status");

                            if(status==true) {


                                SQLiteDatabase db=conn.getWritableDatabase();

                                try {

                                    db.execSQL("delete from " + Utilidades.TABLA_CLIENTE);
                                }
                                catch (SQLiteException e){
                                    if (e.getMessage().contains("no such table")){

                                       // Toast.makeText(getApplicationContext(), "No existen Datos de Clientes", Toast.LENGTH_LONG).show();
                                    }
                                }

                                JSONArray jsonArray = response.getJSONArray("object");

                                for (int i = 0; i < jsonArray.length() ; i++) {

                                    int idCliente;

                                    String identificacionCliente;


                                    String nombreCliente;

                                    String razonCliente;

                                    String fechaCliente;

                                    double latitudCliente;

                                    double longitudCliente;



                                    String direccionCliente;

                                    String telefonoCliente;

                                    String emailCliente;

                                    String descripcionCliente;


                                    JSONObject stock = jsonArray.getJSONObject(i);

                                    idCliente = stock.getInt("id");

                                    try {
                                        identificacionCliente = stock.getString("identificacion");
                                    } catch (Exception ex) {
                                        identificacionCliente = "";
                                    }

                                    try {
                                        nombreCliente = stock.getString("nombreLegal");
                                    } catch (Exception ex) {
                                        nombreCliente = "N/A";
                                    }

                                    try {
                                        razonCliente = stock.getString("razonSocial");
                                    } catch (Exception ex) {
                                        razonCliente = "N/A";
                                    }

                                    try {
                                        fechaCliente = stock.getString("fechaCreacion");
                                    } catch (Exception ex) {
                                        fechaCliente = "N/A";
                                    }

                                    try {
                                        latitudCliente= stock.getDouble("latitud");
                                    } catch (Exception ex) {
                                        latitudCliente = 0;
                                    }

                                    try {
                                        longitudCliente = stock.getDouble("longitud");
                                    } catch (Exception ex) {
                                        longitudCliente = 0;
                                    }



                                    try{
                                        direccionCliente = stock.getString("direccion");
                                    }

                                    catch(Exception ex){
                                        direccionCliente= "N/A";
                                    }

                                    try{
                                        telefonoCliente = stock.getString("telefono");
                                    }

                                    catch(Exception ex){
                                        telefonoCliente= "N/A";
                                    }


                                    try{
                                        emailCliente = stock.getString("email");
                                    }

                                    catch(Exception ex){
                                        emailCliente= "N/A";
                                    }


                                    try{
                                        descripcionCliente = stock.getString("descripcion");
                                    }

                                    catch(Exception ex){
                                        descripcionCliente= "N/A";
                                    }





                                    ContentValues values=new ContentValues();
                                    values.put(Utilidades.CAMPO_ID_CLIENTE,idCliente);
                                    values.put(Utilidades.CAMPO_IDENTIFICACION_CLIENTE,identificacionCliente);
                                    values.put(Utilidades.CAMPO_NOMBRE_CLIENTE,nombreCliente);
                                    values.put(Utilidades.CAMPO_RAZON_CLIENTE,razonCliente);
                                    values.put(Utilidades.CAMPO_FECHA_CLIENTE,fechaCliente);
                                    values.put(Utilidades.CAMPO_LATITUD_CLIENTE,latitudCliente);
                                    values.put(Utilidades.CAMPO_LONGITUD_CLIENTE,longitudCliente);
                                    values.put(Utilidades.CAMPO_DIRECCION_CLIENTE,direccionCliente);
                                    values.put(Utilidades.CAMPO_TELEFONO_CLIENTE,telefonoCliente);
                                    values.put(Utilidades.CAMPO_EMAIL_CLIENTE,emailCliente);

                                    values.put(Utilidades.CAMPO_DESCRIPCION_CLIENTE,descripcionCliente);




                                    db.insert(Utilidades.TABLA_CLIENTE,Utilidades.CAMPO_ID_TABLA_CLIENTE,values);


                                }

                                // mTextViewResultado.setText("USUARIO CORRECTO \n\n"+String.valueOf(id) + " : " + nombre + " \n\n " + edad + " años \n\n " + foto + " \n\n " + error + "\n\n");


                                //  Toast.makeText(getApplicationContext(),
                                //         "Bienvenido: "+idEmpleado+" "+codigoEmpleado+" "+nombresEmpleado+" "+apellidosEmpleado+" "+identificacionEmpleado+" "+profesionEmpleado+" "+domicilioEmpleado+" "+telfdomicilioEmpleado+" "+celularEmpleado+" "+emailEmpleado+" "+extoficinaEmpleado+" "+nivelEmpleado+" "+estadoEmpleado+" "+idoficinaEmpleado+" "+idbancoEMpleado+" "+tipocuentaEmpleado+" "+numcuentaEmpleado+" "+enviarcorreoEmpleado,  Toast.LENGTH_LONG).show();







                                db.close();






                                sincronizarBaseSpinCorporacion();




                               // llenarCamposListView();

                               // hideDialog();




                            }

                            else {
                                hideDialog();
                                Toast.makeText(getApplicationContext(), "Status falso de Clientes", Toast.LENGTH_LONG).show();

                            }






                        } catch (JSONException e) {


                            e.printStackTrace();
                            hideDialog();

                            Toast.makeText(getApplicationContext(),
                                    "Sincronización Fallida"+e.toString(),  Toast.LENGTH_LONG).show();
                        }

                        //   mTextViewResultado.append(response.toString());
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();
                VolleyLog.d(TAG, "Error: " + error.getMessage());


                Toast.makeText(getApplicationContext(),
                        "Error : "+error.getMessage(),  Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        jsonObjReq.setTag(TAG);
        queue.add(jsonObjReq);



    }

    public void sincronizarBaseSpinCorporacion(){




        String url = "http://192.168.0.93:8989/api/corporacion";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {

                    JSONObject jObjstatus = response;

                    Boolean status=jObjstatus.getBoolean("status");

                    if(status==true) {


                        SQLiteDatabase db=conn.getWritableDatabase();

                      try {
                          db.execSQL("delete from " + Utilidades.TABLA_SPIN_CORPORACION);
                      }
                      catch (SQLiteException e){
                          if (e.getMessage().contains("no such table")){

                             // Toast.makeText(getApplicationContext(), "No existen Datos de Clientes", Toast.LENGTH_LONG).show();
                          }
                      }

                        JSONArray jsonArray = response.getJSONArray("object");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject general = jsonArray.getJSONObject(i);

                            int idSpin = general.getInt("id");

                            String nombreSpin = general.getString("nombre");




                            //  mTextViewResultado.append(String.valueOf(id)+" : "+nombre+" , "+edad+" años, "+foto+" , "+error+"\n\n");


                            ContentValues values = new ContentValues();
                            values.put(Utilidades.CAMPO_ID_SPIN_CORP, idSpin);
                            values.put(Utilidades.CAMPO_NOMBRE_SPIN_CORP, nombreSpin);



                            db.insert(Utilidades.TABLA_SPIN_CORPORACION, Utilidades.CAMPO_ID_TABLA_SPIN_CORP, values);
                        }

                        db.close();




                        sincronizarBaseSpinCiudad();


                    }

                    else {
                        hideDialog();
                        Toast.makeText(getApplicationContext(), "Status falso de Corporaciones", Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }


    public void sincronizarBaseSpinCiudad(){




        String url = "http://192.168.0.93:8989/api/ciudad";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {

                    JSONObject jObjstatus = response;

                    Boolean status=jObjstatus.getBoolean("status");

                    if(status==true) {


                        SQLiteDatabase db=conn.getWritableDatabase();

                       try {
                           db.execSQL("delete from " + Utilidades.TABLA_SPIN_CIUDAD);
                       }
                       catch (SQLiteException e){
                           if (e.getMessage().contains("no such table")){

                              // Toast.makeText(getApplicationContext(), "No existen Datos de Clientes", Toast.LENGTH_LONG).show();
                           }
                       }

                        JSONArray jsonArray = response.getJSONArray("object");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject general = jsonArray.getJSONObject(i);

                            int idSpin = general.getInt("id");

                            String nombreSpin = general.getString("nombre");




                            //  mTextViewResultado.append(String.valueOf(id)+" : "+nombre+" , "+edad+" años, "+foto+" , "+error+"\n\n");


                            ContentValues values = new ContentValues();
                            values.put(Utilidades.CAMPO_ID_SPIN_CIUDAD, idSpin);
                            values.put(Utilidades.CAMPO_NOMBRE_SPIN_CIUDAD, nombreSpin);



                            db.insert(Utilidades.TABLA_SPIN_CIUDAD, Utilidades.CAMPO_ID_TABLA_SPIN_CIUDAD, values);
                        }

                        db.close();





                        sincronizarBaseSpinTipoProveedor();

                    }

                    else {
                        hideDialog();
                        Toast.makeText(getApplicationContext(), "Status falso de Ciudades", Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

    public void sincronizarBaseSpinTipoProveedor(){




        String url = "http://192.168.0.93:8989/api/tipoProveedor";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {

                    JSONObject jObjstatus = response;

                    Boolean status=jObjstatus.getBoolean("status");

                    if(status==true) {


                        SQLiteDatabase db=conn.getWritableDatabase();

                       try {
                           db.execSQL("delete from " + Utilidades.TABLA_SPIN_TIPOPROV);
                       }
                       catch (SQLiteException e){
                           if (e.getMessage().contains("no such table")){

                               //Toast.makeText(getApplicationContext(), "No existen Datos de Clientes", Toast.LENGTH_LONG).show();
                           }
                       }

                        JSONArray jsonArray = response.getJSONArray("object");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject general = jsonArray.getJSONObject(i);

                            int idSpin = general.getInt("id");

                            String nombreSpin = general.getString("nombre");




                            //  mTextViewResultado.append(String.valueOf(id)+" : "+nombre+" , "+edad+" años, "+foto+" , "+error+"\n\n");


                            ContentValues values = new ContentValues();
                            values.put(Utilidades.CAMPO_ID_SPIN_TIPOPROV, idSpin);
                            values.put(Utilidades.CAMPO_NOMBRE_SPIN_TIPOPROV, nombreSpin);



                            db.insert(Utilidades.TABLA_SPIN_TIPOPROV, Utilidades.CAMPO_ID_TABLA_SPIN_TIPOPROV, values);
                        }

                        db.close();




                        sincronizarBaseSpinOficina();

                    }

                    else {
                        hideDialog();
                        Toast.makeText(getApplicationContext(), "Status falso de Tipo Proveedor", Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }


    public void sincronizarBaseSpinOficina(){




        String url = "http://192.168.0.93:8989/api/oficina";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {

                    JSONObject jObjstatus = response;

                    Boolean status=jObjstatus.getBoolean("status");

                    if(status==true) {


                        SQLiteDatabase db=conn.getWritableDatabase();

                        try {
                            db.execSQL("delete from " + Utilidades.TABLA_SPIN_OFICINA);
                        }
                        catch (SQLiteException e){
                            if (e.getMessage().contains("no such table")){

                                //Toast.makeText(getApplicationContext(), "No existen Datos de Clientes", Toast.LENGTH_LONG).show();
                            }
                        }

                        JSONArray jsonArray = response.getJSONArray("object");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject general = jsonArray.getJSONObject(i);

                            int idSpin = general.getInt("id");

                            String nombreSpin = general.getString("nombre");




                            //  mTextViewResultado.append(String.valueOf(id)+" : "+nombre+" , "+edad+" años, "+foto+" , "+error+"\n\n");


                            ContentValues values = new ContentValues();
                            values.put(Utilidades.CAMPO_ID_SPIN_OFICINA, idSpin);
                            values.put(Utilidades.CAMPO_NOMBRE_SPIN_OFICINA, nombreSpin);



                            db.insert(Utilidades.TABLA_SPIN_OFICINA, Utilidades.CAMPO_ID_TABLA_SPIN_OFICINA, values);
                        }

                        db.close();




                        sincronizarBaseSpinTipoDoc();


                    }

                    else {
                        hideDialog();
                        Toast.makeText(getApplicationContext(), "Status falso de Oficinas", Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }


    public void sincronizarBaseSpinTipoDoc(){




        String url = "http://192.168.0.93:8989/api/tipoDocumento";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {

                    JSONObject jObjstatus = response;

                    Boolean status=jObjstatus.getBoolean("status");

                    if(status==true) {


                        SQLiteDatabase db=conn.getWritableDatabase();

                        try {
                            db.execSQL("delete from " + Utilidades.TABLA_SPIN_TIPODOC);
                        }
                        catch (SQLiteException e){
                            if (e.getMessage().contains("no such table")){

                                //Toast.makeText(getApplicationContext(), "No existen Datos de Clientes", Toast.LENGTH_LONG).show();
                            }
                        }

                        JSONArray jsonArray = response.getJSONArray("object");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject general = jsonArray.getJSONObject(i);

                            int idSpin = general.getInt("id");

                            String nombreSpin = general.getString("nombre");




                            //  mTextViewResultado.append(String.valueOf(id)+" : "+nombre+" , "+edad+" años, "+foto+" , "+error+"\n\n");


                            ContentValues values = new ContentValues();
                            values.put(Utilidades.CAMPO_ID_SPIN_TIPODOC, idSpin);
                            values.put(Utilidades.CAMPO_NOMBRE_SPIN_TIPODOC, nombreSpin);



                            db.insert(Utilidades.TABLA_SPIN_TIPODOC, Utilidades.CAMPO_ID_TABLA_SPIN_TIPODOC, values);
                        }

                        db.close();


                        sincronizarBaseSpinFormaPago();


                    }

                    else {
                        hideDialog();
                        Toast.makeText(getApplicationContext(), "Status falso de Tipo Documento", Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }


    public void sincronizarBaseSpinFormaPago(){




        String url = "http://192.168.0.93:8989/api/formaPago";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {

                    JSONObject jObjstatus = response;

                    Boolean status=jObjstatus.getBoolean("status");

                    if(status==true) {


                        SQLiteDatabase db=conn.getWritableDatabase();

                        try {
                            db.execSQL("delete from " + Utilidades.TABLA_SPIN_FORMAPAGO);
                        }
                        catch (SQLiteException e){
                            if (e.getMessage().contains("no such table")){

                                //Toast.makeText(getApplicationContext(), "No existen Datos de Clientes", Toast.LENGTH_LONG).show();
                            }
                        }

                        JSONArray jsonArray = response.getJSONArray("object");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject general = jsonArray.getJSONObject(i);

                            int idSpin = general.getInt("id");

                            String nombreSpin = general.getString("nombre");




                            //  mTextViewResultado.append(String.valueOf(id)+" : "+nombre+" , "+edad+" años, "+foto+" , "+error+"\n\n");


                            ContentValues values = new ContentValues();
                            values.put(Utilidades.CAMPO_ID_SPIN_FORMAPAGO, idSpin);
                            values.put(Utilidades.CAMPO_NOMBRE_SPIN_FORMAPAGO, nombreSpin);



                            db.insert(Utilidades.TABLA_SPIN_FORMAPAGO, Utilidades.CAMPO_ID_TABLA_SPIN_FORMAPAGO, values);
                        }

                        db.close();


                        sincronizarBaseSpinMoneda();


                    }

                    else {
                        hideDialog();
                        Toast.makeText(getApplicationContext(), "Status falso de Forma de Pago", Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }


    public void sincronizarBaseSpinMoneda(){




        String url = "http://192.168.0.93:8989/api/moneda";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {

                    JSONObject jObjstatus = response;

                    Boolean status=jObjstatus.getBoolean("status");

                    if(status==true) {


                        SQLiteDatabase db=conn.getWritableDatabase();

                        try {
                            db.execSQL("delete from " + Utilidades.TABLA_SPIN_MONEDA);
                        }
                        catch (SQLiteException e){
                            if (e.getMessage().contains("no such table")){

                                //Toast.makeText(getApplicationContext(), "No existen Datos de Clientes", Toast.LENGTH_LONG).show();
                            }
                        }

                        JSONArray jsonArray = response.getJSONArray("object");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject general = jsonArray.getJSONObject(i);

                            int idSpin = general.getInt("id");

                            String nombreSpin = general.getString("nombre");




                            //  mTextViewResultado.append(String.valueOf(id)+" : "+nombre+" , "+edad+" años, "+foto+" , "+error+"\n\n");


                            ContentValues values = new ContentValues();
                            values.put(Utilidades.CAMPO_ID_SPIN_MONEDA, idSpin);
                            values.put(Utilidades.CAMPO_NOMBRE_SPIN_MONEDA, nombreSpin);



                            db.insert(Utilidades.TABLA_SPIN_MONEDA, Utilidades.CAMPO_ID_TABLA_SPIN_MONEDA, values);
                        }

                        db.close();




                        sincronizarBaseSpinOrigenDoc();


                    }

                    else {
                        hideDialog();
                        Toast.makeText(getApplicationContext(), "Status falso de Moneda", Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }


    public void sincronizarBaseSpinOrigenDoc(){




        String url = "http://192.168.0.93:8989/api/origenDocumento";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {

                    JSONObject jObjstatus = response;

                    Boolean status=jObjstatus.getBoolean("status");

                    if(status==true) {


                        SQLiteDatabase db=conn.getWritableDatabase();

                        try {
                            db.execSQL("delete from " + Utilidades.TABLA_SPIN_ORIGENDOC);
                        }
                        catch (SQLiteException e){
                            if (e.getMessage().contains("no such table")){

                                //Toast.makeText(getApplicationContext(), "No existen Datos de Clientes", Toast.LENGTH_LONG).show();
                            }
                        }



                       // JSONArray jsonArray = response.getJSONArray("object");

                        JSONObject jObj = new JSONObject(response.getString("object"));





                            int idSpin = jObj.getInt("id");

                            String nombreSpin = jObj.getString("nombre");




                            //  mTextViewResultado.append(String.valueOf(id)+" : "+nombre+" , "+edad+" años, "+foto+" , "+error+"\n\n");


                            ContentValues values = new ContentValues();
                            values.put(Utilidades.CAMPO_ID_SPIN_ORIGENDOC, idSpin);
                            values.put(Utilidades.CAMPO_NOMBRE_SPIN_ORIGENDOC, nombreSpin);



                            db.insert(Utilidades.TABLA_SPIN_ORIGENDOC, Utilidades.CAMPO_ID_TABLA_SPIN_ORIGENDOC, values);


                        db.close();


                        sincronizarBaseSpinBodega();


                    }

                    else {
                        hideDialog();
                        Toast.makeText(getApplicationContext(), "Status falso de Origen Moneda", Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

    public void sincronizarBaseSpinBodega(){




        String url = "http://192.168.0.93:8989/api/bodega";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {

                    JSONObject jObjstatus = response;

                    Boolean status=jObjstatus.getBoolean("status");

                    if(status==true) {


                        SQLiteDatabase db=conn.getWritableDatabase();

                        try {
                            db.execSQL("delete from " + Utilidades.TABLA_SPIN_BODEGA);
                        }
                        catch (SQLiteException e){
                            if (e.getMessage().contains("no such table")){

                                //Toast.makeText(getApplicationContext(), "No existen Datos de Clientes", Toast.LENGTH_LONG).show();
                            }
                        }

                        JSONArray jsonArray = response.getJSONArray("object");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject general = jsonArray.getJSONObject(i);

                            int idSpin = general.getInt("id");

                            String nombreSpin = general.getString("nombre");




                            //  mTextViewResultado.append(String.valueOf(id)+" : "+nombre+" , "+edad+" años, "+foto+" , "+error+"\n\n");


                            ContentValues values = new ContentValues();
                            values.put(Utilidades.CAMPO_ID_SPIN_BODEGA, idSpin);
                            values.put(Utilidades.CAMPO_NOMBRE_SPIN_BODEGA, nombreSpin);



                            db.insert(Utilidades.TABLA_SPIN_BODEGA, Utilidades.CAMPO_ID_TABLA_SPIN_BODEGA, values);
                        }

                        db.close();


                        sincronizarBaseSpinListaPrecio();


                    }

                    else {
                        hideDialog();
                        Toast.makeText(getApplicationContext(), "Status falso de Bodega", Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

    public void sincronizarBaseSpinListaPrecio(){




        String url = "http://192.168.0.93:8989/api/listaPrecio";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {

                    JSONObject jObjstatus = response;

                    Boolean status=jObjstatus.getBoolean("status");

                    if(status==true) {


                        SQLiteDatabase db=conn.getWritableDatabase();

                        try {
                            db.execSQL("delete from " + Utilidades.TABLA_SPIN_LISTAPRECIO);
                        }
                        catch (SQLiteException e){
                            if (e.getMessage().contains("no such table")){

                                //Toast.makeText(getApplicationContext(), "No existen Datos de Clientes", Toast.LENGTH_LONG).show();
                            }
                        }

                        JSONArray jsonArray = response.getJSONArray("object");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject general = jsonArray.getJSONObject(i);

                            int idSpin = general.getInt("id");

                            String nombreSpin = general.getString("nombre");




                            //  mTextViewResultado.append(String.valueOf(id)+" : "+nombre+" , "+edad+" años, "+foto+" , "+error+"\n\n");


                            ContentValues values = new ContentValues();
                            values.put(Utilidades.CAMPO_ID_SPIN_LISTAPRECIO, idSpin);
                            values.put(Utilidades.CAMPO_NOMBRE_SPIN_LISTAPRECIO, nombreSpin);



                            db.insert(Utilidades.TABLA_SPIN_LISTAPRECIO, Utilidades.CAMPO_ID_TABLA_SPIN_LISTAPRECIO, values);
                        }

                        db.close();


                        sincronizarBaseSpinSri();


                    }

                    else {
                        hideDialog();
                        Toast.makeText(getApplicationContext(), "Status falso de Lista Precio", Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }



    public void sincronizarBaseSpinSri(){




        String url = "http://192.168.0.93:8989/api/sriIva";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {

                    JSONObject jObjstatus = response;

                    Boolean status=jObjstatus.getBoolean("status");

                    if(status==true) {


                        SQLiteDatabase db=conn.getWritableDatabase();

                        try {
                            db.execSQL("delete from " + Utilidades.TABLA_SPIN_SRI);
                        }
                        catch (SQLiteException e){
                            if (e.getMessage().contains("no such table")){

                                //Toast.makeText(getApplicationContext(), "No existen Datos de Clientes", Toast.LENGTH_LONG).show();
                            }
                        }

                        JSONArray jsonArray = response.getJSONArray("object");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject general = jsonArray.getJSONObject(i);

                            int idSpin = general.getInt("id");

                            int nombreSpin = general.getInt("entero");




                            //  mTextViewResultado.append(String.valueOf(id)+" : "+nombre+" , "+edad+" años, "+foto+" , "+error+"\n\n");


                            ContentValues values = new ContentValues();
                            values.put(Utilidades.CAMPO_ID_SPIN_SRI, idSpin);
                            values.put(Utilidades.CAMPO_NOMBRE_SPIN_SRI, nombreSpin);



                            db.insert(Utilidades.TABLA_SPIN_SRI, Utilidades.CAMPO_ID_TABLA_SPIN_SRI, values);
                        }

                        db.close();



                        sincronizarBaseSpinDocumento();


                    }

                    else {
                        hideDialog();
                        Toast.makeText(getApplicationContext(), "Status falso de SRI IVA", Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }


    public void sincronizarBaseSpinDocumento(){




        String url = "http://192.168.0.93:8989/api/documento";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {

                    JSONObject jObjstatus = response;

                    Boolean status=jObjstatus.getBoolean("status");

                    if(status==true) {


                        SQLiteDatabase db=conn.getWritableDatabase();

                        try {
                            db.execSQL("delete from " + Utilidades.TABLA_SPIN_DOCUMENTO);
                        }
                        catch (SQLiteException e){
                            if (e.getMessage().contains("no such table")){

                                //Toast.makeText(getApplicationContext(), "No existen Datos de Clientes", Toast.LENGTH_LONG).show();
                            }
                        }

                        JSONArray jsonArray = response.getJSONArray("object");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject general = jsonArray.getJSONObject(i);

                            int idSpin = general.getInt("id");

                            String nombreSpin = general.getString("nombre");




                            //  mTextViewResultado.append(String.valueOf(id)+" : "+nombre+" , "+edad+" años, "+foto+" , "+error+"\n\n");


                            ContentValues values = new ContentValues();
                            values.put(Utilidades.CAMPO_ID_SPIN_DOCUMENTO, idSpin);
                            values.put(Utilidades.CAMPO_NOMBRE_SPIN_DOCUMENTO, nombreSpin);



                            db.insert(Utilidades.TABLA_SPIN_DOCUMENTO, Utilidades.CAMPO_ID_TABLA_SPIN_DOCUMENTO, values);
                        }

                        db.close();


                        sincronizarPrecios();
                    }

                    else {
                        hideDialog();
                        Toast.makeText(getApplicationContext(), "Status falso de SRI IVA", Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

    public void sincronizarPrecios(){




        String url = "http://192.168.0.93:8989/api/producto/precio";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {

                    JSONObject jObjstatus = response;

                    Boolean status=jObjstatus.getBoolean("status");

                    if(status==true) {


                        SQLiteDatabase db=conn.getWritableDatabase();

                        try {
                            db.execSQL("delete from " + Utilidades.TABLA_PRECIO);
                        }
                        catch (SQLiteException e){
                            if (e.getMessage().contains("no such table")){

                                //Toast.makeText(getApplicationContext(), "No existen Datos de Clientes", Toast.LENGTH_LONG).show();
                            }
                        }

                        JSONArray jsonArray = response.getJSONArray("object");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject general = jsonArray.getJSONObject(i);

                            Integer idSpin = general.getInt("id");

                            Log.d("preciosid",idSpin.toString());

                            String nombreSpin = general.getString("nombre");

                            Log.d("preciosnom",nombreSpin);

                            Double valorSpin = general.getDouble("precio");

                            Log.d("preciosval",valorSpin.toString());

                            Integer listaSpin = general.getInt("listaPrecio");

                            Log.d("preciosid",listaSpin.toString());




                            //  mTextViewResultado.append(String.valueOf(id)+" : "+nombre+" , "+edad+" años, "+foto+" , "+error+"\n\n");


                            ContentValues values = new ContentValues();
                            values.put(Utilidades.CAMPO_IDPROD_PRECIO, idSpin);
                            values.put(Utilidades.CAMPO_NOMBREPROD_PRECIO, nombreSpin);
                            values.put(Utilidades.CAMPO_VALOR_PRECIO, valorSpin);
                            values.put(Utilidades.CAMPO_LISTA_PRECIO, listaSpin);



                            db.insert(Utilidades.TABLA_PRECIO, Utilidades.CAMPO_ID_TABLA_PRECIO, values);
                        }

                        db.close();

                        sincronizarBaseSpinLote();


                    }

                    else {
                        hideDialog();
                        Toast.makeText(getApplicationContext(), "Status falso de Precio", Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }



    public void sincronizarBaseSpinLote(){




        String url = "http://192.168.0.93:8989/api/lote";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {

                    JSONObject jObjstatus = response;

                    Boolean status=jObjstatus.getBoolean("status");

                    if(status==true) {


                        SQLiteDatabase db=conn.getWritableDatabase();

                        try {
                            db.execSQL("delete from " + Utilidades.TABLA_SPIN_LOTE);
                        }
                        catch (SQLiteException e){
                            if (e.getMessage().contains("no such table")){

                                //Toast.makeText(getApplicationContext(), "No existen Datos de Clientes", Toast.LENGTH_LONG).show();
                            }
                        }

                        JSONArray jsonArray = response.getJSONArray("object");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject general = jsonArray.getJSONObject(i);

                            int idSpin = general.getInt("id");

                            String nombreSpin = general.getString("codigo");




                            //  mTextViewResultado.append(String.valueOf(id)+" : "+nombre+" , "+edad+" años, "+foto+" , "+error+"\n\n");


                            ContentValues values = new ContentValues();
                            values.put(Utilidades.CAMPO_ID_SPIN_LOTE, idSpin);
                            values.put(Utilidades.CAMPO_NOMBRE_LOTE, nombreSpin);



                            db.insert(Utilidades.TABLA_SPIN_LOTE, Utilidades.CAMPO_ID_TABLA_SPIN_LOTE, values);
                        }

                        db.close();


                        Toast.makeText(getApplicationContext(), "Sincronización Correcta", Toast.LENGTH_SHORT).show();

                        hideDialog();


                    }

                    else {
                        hideDialog();
                        Toast.makeText(getApplicationContext(), "Status falso de Lote", Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

    //Show Dialog
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    //Hide Dialog
    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public void filter(String text){

        ArrayList<FoodModel> filteredList= new ArrayList<>();

        for (FoodModel item: foodsList){

            if(item.getItem_name().toLowerCase().contains(text.toLowerCase())){


                filteredList.add(item);
            }


        }

        adapter.filterList(filteredList);

    }

    private void consultarListaPrecio() {

        SQLiteDatabase db=conn.getReadableDatabase();





        SpinListaPrecio persona=null;

        listaprecioList =new ArrayList<SpinListaPrecio>();

        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_SPIN_LISTAPRECIO,null);

        while (cursor.moveToNext()){
            persona=new SpinListaPrecio();
            persona.setIdSpinListaPrecio(cursor.getInt(1));
            persona.setNombreSpinListaPrecio(cursor.getString(2));


           /* Log.i("idOficina",persona.getIdSpinOficina().toString());
            Log.i("NombreOficina",persona.getNombreSpinOficina());

            */


            listaprecioList.add(persona);

        }
        obtenerListaPrecio();
    }

    private void obtenerListaPrecio() {

        listaListaPrecio=new ArrayList<String>();

        listaListaPrecio.add("Lista de Precios");

        for(int i=0;i<listaprecioList.size();i++){
            listaListaPrecio.add(listaprecioList.get(i).getNombreSpinListaPrecio());
        }


        ArrayAdapter<CharSequence> adaptador6=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaListaPrecio);

        comboListaPrecio.setAdapter(adaptador6);



    }


}
