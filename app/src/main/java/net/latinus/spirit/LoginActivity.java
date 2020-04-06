package net.latinus.spirit;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import net.latinus.spirit.Entidades.Empleado;
import net.latinus.spirit.Utilidades.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



import dmax.dialog.SpotsDialog;


public class LoginActivity extends AppCompatActivity {


    EditText etUserEmail;
    EditText etPassword;
    RadioGroup rbgUserType;
    Button btnLoginSubmit;


    ConexionSQLiteHelper conn;


   private ProgressDialog pDialog;



    String userEmail, userPassword;

    ConnectivityDetector connectivityDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        etUserEmail = (EditText) findViewById(R.id.etLoginUserEmail);
        etPassword = (EditText) findViewById(R.id.etLoginPassword);
        btnLoginSubmit = (Button) findViewById(R.id.btnLoginSubmit);

       // rbgUserType = (RadioGroup) findViewById(R.id.rbgLoginUserType);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btnLoginSubmit.setOnTouchListener(new View.OnTouchListener() {
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



    }





    public void botonOnClickLogin(View v){





        userEmail = etUserEmail.getText().toString().trim();
        userPassword = etPassword.getText().toString().trim();

        //Validación del Login
        if(userEmail.isEmpty() || userPassword.isEmpty()){
            Toast.makeText(LoginActivity.this, "Ingrese Usuario y la Contraseña", Toast.LENGTH_SHORT).show();
        }else{

            /*


            connectivityDetector = new ConnectivityDetector(getBaseContext());


            if(connectivityDetector.checkConnectivityStatus()){
                //verificarClienteLogin(userEmail, userPassword);

                verificarUsuarioSpirit(userEmail, userPassword);


                // Toast.makeText(LoginActivity.this, "Client", Toast.LENGTH_SHORT).show();

            }else{
                connectivityDetector.showAlertDialog(LoginActivity.this, "Error","No hay conexión a Internet");
            }
            */


            verificarUsuarioOffline(userEmail, userPassword);

        }

    }

    private void verificarUsuarioOffline(final String email, final String password) {


        pDialog.setMessage("Por favor, espere....");
        pDialog.setTitle("Iniciando Sesión");

        // pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        pDialog.setCancelable(false);
        showDialog();

        String usuarioEmpleado;

        String claveEmpleado;

        String nombreEmpleado;

        String apellidoEmpleado;

        String claveIngresada=password;

        String identificacionEmpleado;

        String emailEmpleado;

        Integer idEmpleado;



        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={email};

        try {
            //select nombre,telefono from usuario where codigo=?
            Cursor cursor=db.rawQuery("SELECT "+Utilidades.CAMPO_USUARIO_EMPLEADO+","+Utilidades.CAMPO_IDENTIFICACION_EMPLEADO+","+Utilidades.CAMPO_NOMBRES_EMPLEADO+","+Utilidades.CAMPO_APELLIDOS_EMPLEADO+","+Utilidades.CAMPO_EMAIL_EMPLEADO+","+Utilidades.CAMPO_CLAVE_EMPLEADO+","+Utilidades.CAMPO_ID_EMPLEADO+
                    " FROM "+Utilidades.TABLA_EMPLEADO+" WHERE "+Utilidades.CAMPO_USUARIO_EMPLEADO+"=? ",parametros);

            cursor.moveToFirst();
            usuarioEmpleado=cursor.getString(0);
            identificacionEmpleado=cursor.getString(1);
            nombreEmpleado=cursor.getString(2);
            apellidoEmpleado=cursor.getString(3);
            emailEmpleado=cursor.getString(4);
            claveEmpleado=cursor.getString(5);
            idEmpleado=cursor.getInt(6);


            Log.d("Usuario",usuarioEmpleado);
            Log.d("NomUsuario",nombreEmpleado);
            Log.d("ApellUsuario",apellidoEmpleado);
            Log.d("ClaveUsuario",claveEmpleado);
            Log.d("EmailUsuario",emailEmpleado);
            Log.d("PassUsuario",claveIngresada);
            Log.d("IdUsuario",idEmpleado.toString());

            if(claveIngresada.equals(claveEmpleado)){

                Toast.makeText(getApplicationContext(),
                        "Bienvenido: "+nombreEmpleado+" "+apellidoEmpleado,  Toast.LENGTH_LONG).show();




                hideDialog();

                goClientDashboardActivity(identificacionEmpleado,nombreEmpleado,apellidoEmpleado,emailEmpleado,idEmpleado);

            }
            else{

                hideDialog();
                Toast.makeText(getApplicationContext(),
                        "CONTRASEÑA INCORRECTA",  Toast.LENGTH_LONG).show();



            }

        }catch (Exception e){

            hideDialog();
            Toast.makeText(getApplicationContext(),"Login Offline Fallido",Toast.LENGTH_SHORT).show();

            connectivityDetector = new ConnectivityDetector(getBaseContext());


            if(connectivityDetector.checkConnectivityStatus()){
                //verificarClienteLogin(userEmail, userPassword);

                verificarUsuarioSpirit(email,password);


                // Toast.makeText(LoginActivity.this, "Client", Toast.LENGTH_SHORT).show();

            }else{
                connectivityDetector.showAlertDialog(LoginActivity.this, "Active el Wi-fi","No hay conexión al servidor");
            }


        }




    }

    private void verificarUsuarioSpirit(final String email, final String password) {




        pDialog.setMessage("Por favor, espere....");
        pDialog.setTitle("Iniciando Sesión");

        // pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        pDialog.setCancelable(false);
        showDialog();

        String eEmail = null;
        String ePassword = null;
        try {
            eEmail = URLEncoder.encode(email, "UTF-8");
            ePassword = URLEncoder.encode(password, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }




        final String TAG = LoginActivity.class.getName();

        String valNombre = etUserEmail.getText().toString();

        String url = "http://192.168.0.93:8989/spirit-mobile/api/empleado/usuario";

        RequestQueue queue = Volley.newRequestQueue(this);


        Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("user",valNombre);


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

                                JSONObject jObj = new JSONObject(response.getString("object"));

                               int idEmpleado = jObj.getInt("id");

                                 String codigoEmpleado = jObj.getString("codigo");

                                String nombresEmpleado = jObj.getString("nombres");

                                 String apellidosEmpleado = jObj.getString("apellidos");

                                 String identificacionEmpleado = jObj.getString("identificacion");

                                 String profesionEmpleado = jObj.getString("profesion");

                                String domicilioEmpleado = jObj.getString("direccionDomicilio");

                                 String telfdomicilioEmpleado = jObj.getString("telefonoDomicilio");

                                 String celularEmpleado = jObj.getString("celular");

                                 String emailEmpleado = jObj.getString("emailOficina");

                                 String extoficinaEmpleado = jObj.getString("extensionOficina");

                                 int nivelEmpleado = jObj.getInt("nivel");

                                 String estadoEmpleado = jObj.getString("estado") ;

                                 int idoficinaEmpleado = jObj.getInt("oficinaId");

                                 int idbancoEMpleado = jObj.getInt("bancoId");

                                 String tipocuentaEmpleado = jObj.getString("tipoCuenta");

                                 String numcuentaEmpleado = jObj.getString("numeroCuenta");

                                 String enviarcorreoEmpleado = jObj.getString("enviarCorreoOt");

                                // mTextViewResultado.setText("USUARIO CORRECTO \n\n"+String.valueOf(id) + " : " + nombre + " \n\n " + edad + " años \n\n " + foto + " \n\n " + error + "\n\n");


                              //  Toast.makeText(getApplicationContext(),
                               //         "Bienvenido: "+idEmpleado+" "+codigoEmpleado+" "+nombresEmpleado+" "+apellidosEmpleado+" "+identificacionEmpleado+" "+profesionEmpleado+" "+domicilioEmpleado+" "+telfdomicilioEmpleado+" "+celularEmpleado+" "+emailEmpleado+" "+extoficinaEmpleado+" "+nivelEmpleado+" "+estadoEmpleado+" "+idoficinaEmpleado+" "+idbancoEMpleado+" "+tipocuentaEmpleado+" "+numcuentaEmpleado+" "+enviarcorreoEmpleado,  Toast.LENGTH_LONG).show();


                                SQLiteDatabase db=conn.getWritableDatabase();

                                ContentValues values=new ContentValues();
                                values.put(Utilidades.CAMPO_ID_EMPLEADO,idEmpleado);
                                values.put(Utilidades.CAMPO_CODIGO_EMPLEADO,codigoEmpleado);
                                values.put(Utilidades.CAMPO_NOMBRES_EMPLEADO,nombresEmpleado);
                                values.put(Utilidades.CAMPO_APELLIDOS_EMPLEADO,apellidosEmpleado);
                                values.put(Utilidades.CAMPO_IDENTIFICACION_EMPLEADO,identificacionEmpleado);
                                values.put(Utilidades.CAMPO_PROFESION_EMPLEADO,profesionEmpleado);
                                values.put(Utilidades.CAMPO_DOMICILIO_EMPLEADO,domicilioEmpleado);
                                values.put(Utilidades.CAMPO_TELFDOMICILIO_EMPLEADO,telfdomicilioEmpleado);
                                values.put(Utilidades.CAMPO_CELULAR_EMPLEADO,celularEmpleado);
                                values.put(Utilidades.CAMPO_EMAIL_EMPLEADO,emailEmpleado);
                                values.put(Utilidades.CAMPO_EXTOFICINA_EMPLEADO,extoficinaEmpleado);
                                values.put(Utilidades.CAMPO_NIVEL_EMPLEADO,nivelEmpleado);
                                values.put(Utilidades.CAMPO_ESTADO_EMPLEADO,estadoEmpleado);
                                values.put(Utilidades.CAMPO_IDOFICINA_EMPLEADO,idoficinaEmpleado);
                                values.put(Utilidades.CAMPO_IDBANCO_EMPLEADO,idbancoEMpleado);
                                values.put(Utilidades.CAMPO_TIPOCUENTA_EMPLEADO,tipocuentaEmpleado);
                                values.put(Utilidades.CAMPO_NUMCUENTA_EMPLEADO,numcuentaEmpleado);
                                values.put(Utilidades.CAMPO_ENVIARCORREO_EMPLEADO,enviarcorreoEmpleado);
                                values.put(Utilidades.CAMPO_USUARIO_EMPLEADO,email);
                                values.put(Utilidades.CAMPO_CLAVE_EMPLEADO,password);


                                db.insert(Utilidades.TABLA_EMPLEADO,Utilidades.CAMPO_ID_TABLA_EMPLEADO,values);


                                db.close();



                                Toast.makeText(getApplicationContext(),
                                        "Bienvenido: "+nombresEmpleado+" "+apellidosEmpleado,  Toast.LENGTH_LONG).show();




                                hideDialog();

                                goClientDashboardActivity(identificacionEmpleado,nombresEmpleado,apellidosEmpleado,emailEmpleado,idEmpleado);

                            }

                            else {
                                hideDialog();
                                Toast.makeText(getApplicationContext(), " USUARIO O CONTRASEÑA ERRÓNEA", Toast.LENGTH_LONG).show();

                            }


                            /*


                            JSONObject jObj = new JSONObject(response.getString("object"));

                            String nombre = jObj.getString("nombreUsuario");



                            int id = jObj.getInt("id");


                            String edad = jObj.getString("edad");

                            String foto = jObj.getString("foto");

                            boolean error = jObj.getBoolean("error");

                            // mTextViewResultado.setText("USUARIO CORRECTO \n\n"+String.valueOf(id) + " : " + nombre + " \n\n " + edad + " años \n\n " + foto + " \n\n " + error + "\n\n");


                            Toast.makeText(getApplicationContext(),
                                    "Bienvenido: "+nombre,  Toast.LENGTH_LONG).show();



                            hideDialog();

                            goClientDashboardActivity();

                            */



                        } catch (JSONException e) {


                            e.printStackTrace();
                            hideDialog();

                            Toast.makeText(getApplicationContext(),
                                    "LOGIN ONLINE FALLIDO.",  Toast.LENGTH_LONG).show();
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


    private void verificarClienteLogin(final String email, final String password) {

        pDialog.setMessage("Por favor, espere....");
        pDialog.setTitle("Iniciando Sesión");

       // pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        pDialog.setCancelable(false);
        showDialog();

        String eEmail = null;
        String ePassword = null;
        try {
            eEmail = URLEncoder.encode(email, "UTF-8");
            ePassword = URLEncoder.encode(password, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }




        final String TAG = LoginActivity.class.getName();

        String valNombre = etUserEmail.getText().toString();

        String url = "http://192.168.0.93:8989/api/general/verificar";

        RequestQueue queue = Volley.newRequestQueue(this);


        Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("nombre",valNombre);


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.PUT,
                url, new JSONObject(postParam),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                       // hideDialog();

                        Log.d(TAG, response.toString());

                        try {
                            JSONObject jObj = new JSONObject(response.getString("general"));

                            String nombre = jObj.getString("nombreUsuario");



                            int id = jObj.getInt("id");


                            String edad = jObj.getString("edad");

                            String foto = jObj.getString("foto");

                            boolean error = jObj.getBoolean("error");

                            // mTextViewResultado.setText("USUARIO CORRECTO \n\n"+String.valueOf(id) + " : " + nombre + " \n\n " + edad + " años \n\n " + foto + " \n\n " + error + "\n\n");


                            Toast.makeText(getApplicationContext(),
                                    "Bienvenido: "+nombre,  Toast.LENGTH_LONG).show();

                            hideDialog();

                     //       goClientDashboardActivity(edad);





                        } catch (JSONException e) {


                            e.printStackTrace();
                            hideDialog();

                            Toast.makeText(getApplicationContext(),
                                    " Login Online fallido. USUARIO O CONTRASEÑA ERRÓNEA",  Toast.LENGTH_LONG).show();
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




    public void goClientDashboardActivity(String identificacion,String nombres, String apellidos, String correo, Integer idEmpleado){

        Bundle basket = new Bundle();
        basket.putString("identificacion", identificacion);
        basket.putString("nombres", nombres);
        basket.putString("apellidos", apellidos);
        basket.putString("correo", correo);
        basket.putInt("id",idEmpleado);


        Intent intent = new Intent(LoginActivity.this,
                ClienteActivity.class);
        intent.putExtras(basket);

        startActivity(intent);
        finish();
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
