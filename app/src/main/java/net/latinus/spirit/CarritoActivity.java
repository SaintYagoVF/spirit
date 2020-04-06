package net.latinus.spirit;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import net.latinus.spirit.Entidades.Cliente;
import net.latinus.spirit.Entidades.Pedido;
import net.latinus.spirit.Entidades.RegistroCliente;
import net.latinus.spirit.Utilidades.Utilidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CarritoActivity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mToggle;

    String valorPasado;
    String nombrePasado;
    String apellidoPasado;
    String emailPasado;
    TextView nombrePas;
    TextView apellPas;
    TextView correoPas;

    Integer idPasado;


    ConexionSQLiteHelper conn;



    Integer idTabla2;

    private ProgressDialog pDialog;

    private RequestQueue mQueue;


    ConnectivityDetector connectivityDetector;

    ListView listViewRegClientes;
    ArrayList<String> listaInformacion;
    ArrayList<RegistroCliente> listaRegClientes;


    ListView listViewRegPedidos;
    ArrayList<String> listaInformacionPedidos;
    ArrayList<Pedido> listaRegPedidos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);


        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.abrir,R.string.cerrar);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
       /* if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }
        */

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        listViewRegClientes= (ListView) findViewById(R.id.listViewRegClientes);

        listViewRegPedidos=(ListView)findViewById(R.id.listViewRegPedidos);




        Bundle bundle = this.getIntent().getExtras();

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
            //Do something here if data  received
        }
        else
        {
            //Do something here if data not received
        }

        View headerView = navigationView.getHeaderView(0);
        nombrePas = (TextView) headerView.findViewById(R.id.menuNombre);
        nombrePas.setText(nombrePasado);

        apellPas = (TextView)headerView.findViewById(R.id.menuApellidos);
        apellPas.setText(apellidoPasado);

        correoPas = (TextView)headerView.findViewById(R.id.menuEmail);
        correoPas.setText(emailPasado);

        setupNavigationDrawerContent(navigationView);


        llenarCamposListView();

        llenarCamposListViewPedidos();


        listViewRegClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion="CLIENTE: "+listaRegClientes.get(pos).getNombreRegCliente()+"\n";



                Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_LONG).show();

                RegistroCliente user=listaRegClientes.get(pos);



                Bundle bundle=new Bundle();
                bundle.putSerializable("regcliente",user);
                bundle.putInt("idtabla",2);


                bundle.putString("identificacion", valorPasado);
                bundle.putString("nombres", nombrePasado);
                bundle.putString("apellidos", apellidoPasado);
                bundle.putString("correo", emailPasado);
                bundle.putInt("id",idPasado);


                mDrawerLayout.closeDrawer(GravityCompat.START);
                Intent intent5 = new Intent(CarritoActivity.this, DetalleRegistroClienteActivity.class);
                intent5.putExtras(bundle);
                startActivity(intent5);
                finish();



            }
        });


        listViewRegPedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion="Pedido: "+listaRegPedidos.get(pos).getNombreClientePedido()+"\n";



                Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_SHORT).show();

                Pedido user2=listaRegPedidos.get(pos);



                Bundle bundle2=new Bundle();
                bundle2.putSerializable("regpedido",user2);
                bundle2.putInt("idtabla",idTabla2);


                bundle2.putString("identificacion", valorPasado);
                bundle2.putString("nombres", nombrePasado);
                bundle2.putString("apellidos", apellidoPasado);
                bundle2.putString("correo", emailPasado);
                bundle2.putInt("id",idPasado);


                mDrawerLayout.closeDrawer(GravityCompat.START);
                Intent intent5 = new Intent(CarritoActivity.this, DetallePedidoActivity.class);
                intent5.putExtras(bundle2);
                startActivity(intent5);
                finish();



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

                                Toast.makeText(CarritoActivity.this, "Pantalla: " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                mDrawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent = new Intent(CarritoActivity.this, ClienteActivity.class);
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

                                Toast.makeText(CarritoActivity.this, "Pantalla: " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                mDrawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent2 = new Intent(CarritoActivity.this, CarritoActivity.class);
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

                                Toast.makeText(CarritoActivity.this, "Pantalla: " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                mDrawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent3 = new Intent(CarritoActivity.this, StockActivity.class);
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

                                Toast.makeText(CarritoActivity.this, "Pantalla: " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                mDrawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent4 = new Intent(CarritoActivity.this, PerfilActivity.class);
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


                                Toast.makeText(CarritoActivity.this, "Pantalla: " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                mDrawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent6 = new Intent(CarritoActivity.this, CuentaActivity.class);
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

                                Toast.makeText(CarritoActivity.this, "Pantalla: " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                mDrawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent5 = new Intent(CarritoActivity.this, LoginActivity.class);
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
                                                    connectivityDetector.showAlertDialog(CarritoActivity.this, "ACTIVE EL WI-FI","No hay conexión a Internet");
                                                }

                                                break;

                                            case DialogInterface.BUTTON_NEGATIVE:
                                                //No button clicked
                                                break;
                                        }
                                    }
                                };
                                AlertDialog.Builder builder = new AlertDialog.Builder(CarritoActivity.this);

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

        String url = "http://192.168.0.253:8888/spirit-mobile/api/stock/empleado";

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

        String url = "http://192.168.0.253:8888/spirit-mobile/api/cliente/empleado";

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




        String url = "http://192.168.0.253:8888/spirit-mobile/api/corporacion";

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




        String url = "http://192.168.0.253:8888/spirit-mobile/api/ciudad";

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




        String url = "http://192.168.0.253:8888/spirit-mobile/api/tipoProveedor";

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




        String url = "http://192.168.0.253:8888/spirit-mobile/api/oficina";

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




        String url = "http://192.168.0.253:8888/spirit-mobile/api/tipoDocumento";

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




        String url = "http://192.168.0.253:8888/spirit-mobile/api/formaPago";

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




        String url = "http://192.168.0.253:8888/spirit-mobile/api/moneda";

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




        String url = "http://192.168.0.253:8888/spirit-mobile/api/origenDocumento";

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




        String url = "http://192.168.0.253:8888/spirit-mobile/api/bodega";

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




        String url = "http://192.168.0.253:8888/spirit-mobile/api/listaPrecio";

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




        String url = "http://192.168.0.253:8888/spirit-mobile/api/sriIva";

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




        String url = "http://192.168.0.253:8888/spirit-mobile/api/documento";

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




        String url = "http://192.168.0.253:8888/spirit-mobile/api/producto/precio";

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




        String url = "http://192.168.0.253:8888/spirit-mobile/api/lote";

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


    private void llenarCamposListView() {

        Integer i=0;

        SQLiteDatabase db=conn.getReadableDatabase();

        RegistroCliente usuario=null;
        listaRegClientes=new ArrayList<RegistroCliente>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_REG_CLIENTE,null);

        while (cursor.moveToNext()){
            usuario=new RegistroCliente();

            usuario.setIdborrar(cursor.getInt(0));
            usuario.setTipoidRegCliente(cursor.getInt(1));
            usuario.setIdentificacionRegCliente(cursor.getString(2));
            usuario.setNombreRegCliente(cursor.getString(3));
            usuario.setRazonRegCliente(cursor.getString(4));
            usuario.setCorporacionidRegCliente(cursor.getInt(5));
            usuario.setTipoclienteidRegCliente(cursor.getInt(6));
            usuario.setTipoproveedoridRegCliente(cursor.getInt(7));
            usuario.setCodigoRegCliente(cursor.getString(8));
            usuario.setCiudadidRegCliente(cursor.getInt(9));
            usuario.setDireccionRegCliente(cursor.getString(10));
            usuario.setLatitudRegCliente(cursor.getDouble(11));
            usuario.setLongitudRegCliente(cursor.getDouble(12));
            usuario.setEmailRegCliente(cursor.getString(13));
            usuario.setDescripcionRegCliente(cursor.getString(14));
            usuario.setVendedoridRegCliente(cursor.getInt(15));

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
          i++;

            listaRegClientes.add(usuario);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();

        for (int i=0; i<listaRegClientes.size();i++){
            listaInformacion.add(listaRegClientes.get(i).getIdentificacionRegCliente()+" - "
                    +listaRegClientes.get(i).getNombreRegCliente());

        }

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewRegClientes.setAdapter(adaptador);

    }




    private void llenarCamposListViewPedidos() {

        SQLiteDatabase db=conn.getReadableDatabase();

        Pedido usuario=null;
        listaRegPedidos=new ArrayList<Pedido>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_PEDIDO,null);

        while (cursor.moveToNext()){
            usuario=new Pedido();

            idTabla2=cursor.getInt(0);
            usuario.setContadorPedido(cursor.getInt(1));
            usuario.setIdOficinaPedido(cursor.getInt(0));
            usuario.setTipoDocumentoPedido(cursor.getInt(3));
            usuario.setIdentificacionClientePedido(cursor.getString(4));
            usuario.setNombreClientePedido(cursor.getString(5));
            usuario.setFormaPagoPedido(cursor.getInt(6));
            usuario.setMonedaPedido(cursor.getInt(7));
            usuario.setOrigendocPedido(cursor.getInt(8));
            usuario.setVendedorPedido(cursor.getInt(9));
            usuario.setBodegaPedido(cursor.getInt(10));
            usuario.setListaPrecioPedido(cursor.getInt(11));
            usuario.setFechaPedido(cursor.getString(12));
            usuario.setLatitudPedido(cursor.getDouble(13));
            usuario.setLongitudPedido(cursor.getDouble(14));
            usuario.setUsuarioPedido(cursor.getInt(15));
            usuario.setObservacionPedido(cursor.getString(16));
            usuario.setSriIvaPedido(cursor.getInt(17));
            usuario.setSubtotalglobalPedido(cursor.getDouble(18));
            usuario.setDescuentoGlobalPedido(cursor.getDouble(19));
            usuario.setIvaglobalPedido(cursor.getDouble(20));
            usuario.setValoglobalPedido(cursor.getDouble(21));

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

            listaRegPedidos.add(usuario);
        }
        obtenerListaPedidos();
    }

    private void obtenerListaPedidos() {
        listaInformacionPedidos=new ArrayList<String>();

        for (int i=0; i<listaRegPedidos.size();i++){
            listaInformacionPedidos.add(listaRegPedidos.get(i).getFechaPedido()+" - "
                    +listaRegPedidos.get(i).getNombreClientePedido());

        }

        ArrayAdapter adaptador2=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacionPedidos);
        listViewRegPedidos.setAdapter(adaptador2);

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
}
