package net.latinus.spirit;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
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
import net.latinus.spirit.Entidades.RegistroCliente;
import net.latinus.spirit.Utilidades.Utilidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetalleRegistroClienteActivity extends AppCompatActivity {


    TextView tipoidRegCliente,identificacionRegCliente,nombreRegCliente,razonRegCliente,corporacionidRegCliente,tipoclienteidRegCliente, tipoproveedRegCliente, codigoRegCliente, ciudadidRegCliente, direccionRegCliente, latitudRegCliente,longitudRegCliente, emailRegCliente, descripcionRegCliente, vendedorRegCliente;




    Button btnSincronizar, btnBorrar, btnCancelar;

Integer idTabla;

    String valorPasado;
    String nombrePasado;
    String apellidoPasado;
    String emailPasado;
    Integer idPasado;

    Integer pedidoborrar;

    ProgressDialog pDialog;

    ConexionSQLiteHelper conn;

   Integer tipoidRegClientes;

     String identificacionRegClientes;

     String nombreRegClientes;

 String razonRegClientes;

   Integer corporacionidRegClientes;

     Integer tipoclienteidRegClientes;

     Integer tipoproveedoridRegClientes;

     String codigoRegClientes;

     Integer ciudadidRegClientes;

     String direccionRegClientes;

     Double latitudRegClientes;

    Double longitudRegClientes;


    String emailRegClientes;

     String descripcionRegClientes;

     Integer vendedoridRegClientes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_registro_cliente);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        btnSincronizar = (Button)findViewById(R.id.btnSincRegCliente);

        btnBorrar = (Button)findViewById(R.id.btnBorrarRegCliente);

        btnCancelar = (Button)findViewById(R.id.btnCancelarRegCliente);


        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        tipoidRegCliente = (TextView)findViewById(R.id.simT12);
        identificacionRegCliente = (TextView)findViewById(R.id.simT22);
        nombreRegCliente = (TextView)findViewById(R.id.simT32);

        razonRegCliente = (TextView)findViewById(R.id.simT42);
        corporacionidRegCliente = (TextView)findViewById(R.id.simT52);
        tipoclienteidRegCliente = (TextView)findViewById(R.id.simT62);
        tipoproveedRegCliente = (TextView)findViewById(R.id.simT72);
        codigoRegCliente = (TextView)findViewById(R.id.simT82);
        ciudadidRegCliente = (TextView)findViewById(R.id.simT92);
        direccionRegCliente = (TextView)findViewById(R.id.simT102);
        latitudRegCliente = (TextView)findViewById(R.id.simT112);
        longitudRegCliente = (TextView)findViewById(R.id.simT122);
        emailRegCliente = (TextView)findViewById(R.id.simT132);
        descripcionRegCliente = (TextView)findViewById(R.id.simT142);
        vendedorRegCliente = (TextView)findViewById(R.id.simT152);


        Bundle objetoEnviado=getIntent().getExtras();
        RegistroCliente user=null;

        if(objetoEnviado!=null){
            user= (RegistroCliente) objetoEnviado.getSerializable("regcliente");
            tipoidRegCliente.setText(user.getTipoidRegCliente().toString());

           tipoidRegClientes=user.getTipoidRegCliente();

            identificacionRegCliente.setText(user.getIdentificacionRegCliente());
            identificacionRegClientes=user.getIdentificacionRegCliente();

            nombreRegCliente.setText(user.getNombreRegCliente());
            nombreRegClientes=user.getNombreRegCliente();

            razonRegCliente.setText(user.getRazonRegCliente());
            razonRegClientes=user.getRazonRegCliente();

            corporacionidRegCliente.setText(user.getCorporacionidRegCliente().toString());
            corporacionidRegClientes=user.getCorporacionidRegCliente();

            tipoclienteidRegCliente.setText(user.getTipoclienteidRegCliente().toString());
            tipoclienteidRegClientes=user.getTipoclienteidRegCliente();

            tipoproveedRegCliente.setText(user.getTipoproveedoridRegCliente().toString());
            tipoproveedoridRegClientes=user.getTipoproveedoridRegCliente();

            codigoRegCliente.setText(user.getCodigoRegCliente());
            codigoRegClientes=user.getCodigoRegCliente();

            ciudadidRegCliente.setText(user.getCiudadidRegCliente().toString());
            ciudadidRegClientes=user.getCiudadidRegCliente();

            direccionRegCliente.setText(user.getDireccionRegCliente());
            direccionRegClientes=user.getDireccionRegCliente();

            latitudRegCliente.setText(user.getLatitudRegCliente().toString());
            latitudRegClientes=user.getLatitudRegCliente();

            longitudRegCliente.setText(user.getLongitudRegCliente().toString());
            longitudRegClientes=user.getLongitudRegCliente();

            emailRegCliente.setText(user.getEmailRegCliente());
            emailRegClientes=user.getEmailRegCliente();

            descripcionRegCliente.setText(user.getDescripcionRegCliente());
            descripcionRegClientes=user.getDescripcionRegCliente();

            vendedorRegCliente.setText(user.getVendedoridRegCliente().toString());
            vendedoridRegClientes=user.getVendedoridRegCliente();

            pedidoborrar=user.getIdborrar();



            //ObtainBundleData in the object
            String strdata = objetoEnviado.getString("identificacion");

            valorPasado=strdata;
            String strdata1 = objetoEnviado.getString("nombres");

            nombrePasado=strdata1;

            String strdata2 = objetoEnviado.getString("apellidos");

            apellidoPasado=strdata2;

            String strdata3 = objetoEnviado.getString("correo");

            emailPasado=strdata3;

            int strdata4 = objetoEnviado.getInt("id");

            idPasado = strdata4;


            int strdata5 = objetoEnviado.getInt("idtabla");

            idTabla = strdata5;

            Log.d("valorpasado",idTabla.toString());
        }



        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Bundle bundle=new Bundle();




                bundle.putString("identificacion", valorPasado);
                bundle.putString("nombres", nombrePasado);
                bundle.putString("apellidos", apellidoPasado);
                bundle.putString("correo", emailPasado);
                bundle.putInt("id",idPasado);



                Intent intent5 = new Intent(DetalleRegistroClienteActivity.this, CarritoActivity.class);
                intent5.putExtras(bundle);
                startActivity(intent5);
                finish();

            }
        });


        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked


                                try{

                                    SQLiteDatabase db=conn.getWritableDatabase();

                                    // db.execSQL("delete from "+ Utilidades.TABLA_REG_CLIENTE+" where "+Utilidades.CAMPO_ID_TABLA_REG_CLIENTE+" in "+idTabla);
                                    String sql="delete from "+Utilidades.TABLA_REG_CLIENTE+" where "+Utilidades.CAMPO_ID_TABLA_REG_CLIENTE+"="+pedidoborrar+"";

                                    db.execSQL(sql);

                                    db.close();

                                    Log.d("borrado",sql);


                                    Bundle bundle=new Bundle();




                                    bundle.putString("identificacion", valorPasado);
                                    bundle.putString("nombres", nombrePasado);
                                    bundle.putString("apellidos", apellidoPasado);
                                    bundle.putString("correo", emailPasado);
                                    bundle.putInt("id",idPasado);



                                    Intent intent5 = new Intent(DetalleRegistroClienteActivity.this, CarritoActivity.class);
                                    intent5.putExtras(bundle);
                                    startActivity(intent5);
                                    finish();

                                }

                                catch (Exception ex){
                                    Toast.makeText(DetalleRegistroClienteActivity.this, "No se pudo eliminar el cliente" , Toast.LENGTH_SHORT).show();


                                }


                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(DetalleRegistroClienteActivity.this);

                builder.setMessage("¿SEGURO DESEA BORRAR EL CLIENTE?").setPositiveButton("SI", dialogClickListener)
                        .setNegativeButton("NO", dialogClickListener).setTitle("BORRAR CLIENTE").setIcon(R.drawable.icon_password).show();





            }
        });


        btnSincronizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                pDialog.setMessage("Por favor, espere....");
                pDialog.setTitle("Sincronizando");

                // pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                pDialog.setCancelable(false);
                showDialog();





                final String TAG = DetalleRegistroClienteActivity.class.getName();



                String url = "http://192.168.0.131:8989/spirit-mobile/api/cliente/guardar";



                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());


                Map<String, Object> postParam= new HashMap<String, Object>();
                postParam.put("tipoIdentificacionId",tipoidRegClientes);
                postParam.put("identificacion",identificacionRegClientes);
                postParam.put("nombreLegal",nombreRegClientes);
                postParam.put("razonSocial",razonRegClientes);
                postParam.put("corporacionId",corporacionidRegClientes);
                postParam.put("tipoClienteId",tipoclienteidRegClientes);
                postParam.put("tipoProveedorId",tipoproveedoridRegClientes);
                postParam.put("codigo",codigoRegClientes);
                postParam.put("ciudadId",ciudadidRegClientes);
                postParam.put("direccion",direccionRegClientes);
                postParam.put("latitud",latitudRegClientes);
                postParam.put("longitud",longitudRegClientes);
                postParam.put("email",emailRegClientes);
                postParam.put("descripcion",descripcionRegClientes);
                postParam.put("vendedorId",vendedoridRegClientes);


                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
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

                                        // mTextViewResultado.setText("USUARIO CORRECTO \n\n"+String.valueOf(id) + " : " + nombre + " \n\n " + edad + " años \n\n " + foto + " \n\n " + error + "\n\n");


                                        //  Toast.makeText(getApplicationContext(),
                                        //         "Bienvenido: "+idEmpleado+" "+codigoEmpleado+" "+nombresEmpleado+" "+apellidosEmpleado+" "+identificacionEmpleado+" "+profesionEmpleado+" "+domicilioEmpleado+" "+telfdomicilioEmpleado+" "+celularEmpleado+" "+emailEmpleado+" "+extoficinaEmpleado+" "+nivelEmpleado+" "+estadoEmpleado+" "+idoficinaEmpleado+" "+idbancoEMpleado+" "+tipocuentaEmpleado+" "+numcuentaEmpleado+" "+enviarcorreoEmpleado,  Toast.LENGTH_LONG).show();










                               Toast.makeText(getApplicationContext(),
                                        "Sincronización Correcta",  Toast.LENGTH_LONG).show();



                                        SQLiteDatabase db=conn.getWritableDatabase();

                                        // db.execSQL("delete from "+ Utilidades.TABLA_REG_CLIENTE+" where "+Utilidades.CAMPO_ID_TABLA_REG_CLIENTE+" in "+idTabla);

                                        db.execSQL("delete from "+Utilidades.TABLA_REG_CLIENTE+" where "+Utilidades.CAMPO_ID_TABLA_REG_CLIENTE+"="+pedidoborrar);

                                        db.close();


                                        Bundle bundle=new Bundle();




                                        bundle.putString("identificacion", valorPasado);
                                        bundle.putString("nombres", nombrePasado);
                                        bundle.putString("apellidos", apellidoPasado);
                                        bundle.putString("correo", emailPasado);
                                        bundle.putInt("id",idPasado);



                                        Intent intent5 = new Intent(DetalleRegistroClienteActivity.this, CarritoActivity.class);
                                        intent5.putExtras(bundle);
                                        startActivity(intent5);
                                        finish();



                                    }

                                    else {
                                        hideDialog();
                                        Toast.makeText(getApplicationContext(), "Sincronización Fallida", Toast.LENGTH_LONG).show();

                                    }






                                } catch (JSONException e) {


                                    e.printStackTrace();
                                    hideDialog();

                                    Toast.makeText(getApplicationContext(),
                                            "Sincronización Fallida",  Toast.LENGTH_LONG).show();
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
        });

        btnSincronizar.setOnTouchListener(new View.OnTouchListener() {
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

        btnBorrar.setOnTouchListener(new View.OnTouchListener() {
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
