package net.latinus.spirit;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import net.latinus.spirit.Entidades.DetallePedido;
import net.latinus.spirit.Entidades.Pedido;
import net.latinus.spirit.Entidades.RegistroCliente;
import net.latinus.spirit.Entidades.ValoresPedido;
import net.latinus.spirit.Utilidades.Utilidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetallePedidoActivity extends AppCompatActivity {




    Button btnSincronizar, btnBorrar, btnCancelar;

    Integer idTabla;

    String valorPasado;
    String nombrePasado;
    String apellidoPasado;
    String emailPasado;
    Integer idPasado;

    ProgressDialog pDialog;

    ConexionSQLiteHelper conn;

    Integer pedidoIdTabla;



    TextView tipoDocumentoPedidoTex;

        Integer statusPedido;

    TextView nombreClientePedidoTex;

    TextView formaPagoPedidoTex;



    TextView listaPrecioPedidoTex;

    TextView fechaPedidoTex;



    TextView observacionPedidoTex;

    TextView sriIvaPedidoTex;


    TextView subtotalPedidoTex;

    TextView descuentoGlobalTex;

    TextView ivaglobalTex;

    TextView totalGlobalTex;


    ListView listViewRegPedidos;
    ArrayList<String> listaInformacionPedidos;
    ArrayList<DetallePedido> listaRegPedidos;


    Integer contadorPedido;

    Integer idOficinaPedido;

     Integer tipoDocumentoPedido;

     String identificacionClientePedido;

     String nombreClientePedido;

    Integer formaPagoPedido;

     Integer monedaPedido;

     Integer origendocPedido;


     Integer vendedorPedido;

     Integer bodegaPedido;

     Integer listaPrecioPedido;

     String fechaPedido;

     Double latitudPedido;

     Double longitudPedido;

     Integer usuarioPedido;

     String observacionPedido;

     Integer sriIvaPedido;

     Integer documentoPedido;

     String nombreProductoPedido;

     Integer lotePedido;

     String descripcionProductoPedido;

     Double cantidadProductoPedido;

     Double precioProductoPedido;

     Double descuentoProductoPedido;

     Double valorPedido;

     Double ivaValorPedido;

     Double subtotalGlobalPedido;

     Double descuentoGlobalPedido;

     Double ivaGlobalPedido;

     Double totalGlobalPedido;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido);


        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        btnSincronizar = (Button)findViewById(R.id.btnSincPedido);

        listViewRegPedidos=(ListView)findViewById(R.id.listViewDetallePedido);

        btnBorrar = (Button)findViewById(R.id.btnBorrarPedido);

        btnCancelar = (Button)findViewById(R.id.btnCancelarPedido);


        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        Bundle objetoEnviado=getIntent().getExtras();
        Pedido user=null;



        tipoDocumentoPedidoTex=(TextView)findViewById(R.id.sim22);



         nombreClientePedidoTex=(TextView)findViewById(R.id.sim42);
      formaPagoPedidoTex=(TextView)findViewById(R.id.sim52);


         listaPrecioPedidoTex=(TextView)findViewById(R.id.sim112);

         fechaPedidoTex=(TextView)findViewById(R.id.sim102);



         observacionPedidoTex=(TextView)findViewById(R.id.sim152);

        sriIvaPedidoTex=(TextView)findViewById(R.id.sim162);

        subtotalPedidoTex=(TextView)findViewById(R.id.txtSubtotalGlobal);

        descuentoGlobalTex=(TextView)findViewById(R.id.txtDescuentoGlobal);

        ivaglobalTex=(TextView)findViewById(R.id.txtIvaGlobal);

        totalGlobalTex=(TextView)findViewById(R.id.txtTotalGlobal);



        if(objetoEnviado!=null){
           user=(Pedido) objetoEnviado.getSerializable("regpedido");



            contadorPedido=user.getContadorPedido();





            pedidoIdTabla=user.getIdOficinaPedido();




            tipoDocumentoPedidoTex.setText(user.getTipoDocumentoPedido().toString());
            tipoDocumentoPedido=user.getTipoDocumentoPedido();


            identificacionClientePedido=user.getIdentificacionClientePedido();

           nombreClientePedidoTex.setText(user.getNombreClientePedido());
           nombreClientePedido=user.getNombreClientePedido();

            formaPagoPedidoTex.setText(user.getFormaPagoPedido().toString());
            formaPagoPedido=user.getFormaPagoPedido();


            monedaPedido=user.getMonedaPedido();


            origendocPedido=user.getOrigendocPedido();



            vendedorPedido=user.getVendedorPedido();


            bodegaPedido=user.getBodegaPedido();

            listaPrecioPedidoTex.setText(user.getListaPrecioPedido().toString());
            listaPrecioPedido=user.getListaPrecioPedido();

            fechaPedidoTex.setText(user.getFechaPedido().toString());
            fechaPedido=user.getFechaPedido();


            latitudPedido=user.getLatitudPedido();


            longitudPedido=user.getLongitudPedido();


            usuarioPedido=user.getUsuarioPedido();

            observacionPedidoTex.setText(user.getObservacionPedido().toString());
            observacionPedido=user.getObservacionPedido();

            sriIvaPedidoTex.setText(user.getSriIvaPedido().toString());
            sriIvaPedido=user.getSriIvaPedido();


            subtotalPedidoTex.setText(user.getSubtotalglobalPedido().toString());
            subtotalGlobalPedido=user.getSubtotalglobalPedido();

try {
    descuentoGlobalTex.setText(user.getDescuentoglobalPedido().toString());
    descuentoGlobalPedido = user.getDescuentoGlobalPedido();
}
catch (Exception ex)
{
    descuentoGlobalTex.setText("0.0");
    descuentoGlobalPedido = 0.0;

}

            ivaglobalTex.setText(user.getIvaglobalPedido().toString());
            ivaGlobalPedido=user.getIvaglobalPedido();

            totalGlobalTex.setText(user.getValoglobalPedido().toString());
            totalGlobalPedido=user.getValoglobalPedido();





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

            Log.d("idtabla",idTabla.toString());
        }




        llenarCamposListViewPedidos();


        btnSincronizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {








                pDialog.setMessage("Por favor, espere....");
                pDialog.setTitle("Sincronizando");

                // pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                pDialog.setCancelable(false);
                showDialog();





                final String TAG = DetalleRegistroClienteActivity.class.getName();



               // String url = "http://192.168.0.253:8888/spirit-mobile/api/pedido/guardar";

                String url ="http://192.168.0.93:8989/api/pedido/guardar";

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());





                Map<String, Object> postParam= new HashMap<String, Object>();
                postParam.put("numeroPedido",1);
                postParam.put("oficinaId",1);
                postParam.put("tipoDocumentoId",tipoDocumentoPedido);
                postParam.put("identificacion",identificacionClientePedido);
                postParam.put("formaPago",formaPagoPedido);
                postParam.put("moneda",1);
                postParam.put("origenDocumento",2);
                postParam.put("empleadoId",vendedorPedido);
                postParam.put("bodegaId",40);
                postParam.put("listaPrecio",listaPrecioPedido);
                postParam.put("fechaPedido",fechaPedido);
                postParam.put("fechaCreacion",fechaPedido);
                postParam.put("usuarioId",usuarioPedido);
                postParam.put("observacion",observacionPedido);
                postParam.put("idSriIva",sriIvaPedido);
                postParam.put("latitud",latitudPedido);
                postParam.put("longitud",longitudPedido);

              /*  postParam.put("documento",documentoPedido);
                postParam.put("producto",nombreProductoPedido);
                postParam.put("lote",lotePedido);
                postParam.put("descripcion",descripcionProductoPedido);
                postParam.put("cantidadPedida",cantidadProductoPedido);
                postParam.put("cantidad",cantidadProductoPedido);
                postParam.put("precio",precioProductoPedido);
                postParam.put("descuento",descuentoProductoPedido);
                postParam.put("valor",valorPedido);
                postParam.put("iva",ivaValorPedido);

                */






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

                                    statusPedido=jObjstatus.getInt("object");

                                    if(status==true) {

                                        // mTextViewResultado.setText("USUARIO CORRECTO \n\n"+String.valueOf(id) + " : " + nombre + " \n\n " + edad + " años \n\n " + foto + " \n\n " + error + "\n\n");


                                        //  Toast.makeText(getApplicationContext(),
                                        //         "Bienvenido: "+idEmpleado+" "+codigoEmpleado+" "+nombresEmpleado+" "+apellidosEmpleado+" "+identificacionEmpleado+" "+profesionEmpleado+" "+domicilioEmpleado+" "+telfdomicilioEmpleado+" "+celularEmpleado+" "+emailEmpleado+" "+extoficinaEmpleado+" "+nivelEmpleado+" "+estadoEmpleado+" "+idoficinaEmpleado+" "+idbancoEMpleado+" "+tipocuentaEmpleado+" "+numcuentaEmpleado+" "+enviarcorreoEmpleado,  Toast.LENGTH_LONG).show();




                                            almacenardetallespedido(statusPedido);




/*
                                        Toast.makeText(getApplicationContext(),
                                                "Sincronización Correcta",  Toast.LENGTH_LONG).show();



                                        SQLiteDatabase db=conn.getWritableDatabase();

                                        // db.execSQL("delete from "+ Utilidades.TABLA_REG_CLIENTE+" where "+Utilidades.CAMPO_ID_TABLA_REG_CLIENTE+" in "+idTabla);

                                        db.execSQL("delete from "+Utilidades.TABLA_PEDIDO+" where "+Utilidades.CAMPO_ID_TABLA_PEDIDO+"='"+idTabla+"'");

                                        db.close();


                                        Bundle bundle=new Bundle();




                                        bundle.putString("identificacion", valorPasado);
                                        bundle.putString("nombres", nombrePasado);
                                        bundle.putString("apellidos", apellidoPasado);
                                        bundle.putString("correo", emailPasado);
                                        bundle.putInt("id",idPasado);



                                        Intent intent5 = new Intent(DetallePedidoActivity.this, CarritoActivity.class);
                                        intent5.putExtras(bundle);
                                        startActivity(intent5);


*/




                                    }

                                    else {
                                        hideDialog();
                                        Toast.makeText(getApplicationContext(), "Sincronización Fallida", Toast.LENGTH_LONG).show();

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
        });


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Bundle bundle=new Bundle();




                bundle.putString("identificacion", valorPasado);
                bundle.putString("nombres", nombrePasado);
                bundle.putString("apellidos", apellidoPasado);
                bundle.putString("correo", emailPasado);
                bundle.putInt("id",idPasado);



                Intent intent5 = new Intent(DetallePedidoActivity.this, CarritoActivity.class);
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

                                    SQLiteDatabase db6=conn.getWritableDatabase();

                                    // db.execSQL("delete from "+ Utilidades.TABLA_REG_CLIENTE+" where "+Utilidades.CAMPO_ID_TABLA_REG_CLIENTE+" in "+idTabla);

                                    String sql="DELETE FROM "+Utilidades.TABLA_PEDIDO+" WHERE "+Utilidades.CAMPO_ID_TABLA_PEDIDO+"="+pedidoIdTabla+"";

                                    db6.execSQL(sql);


                                    db6.close();




                                    for (int i=0; i<listaRegPedidos.size();i++){


                                    try{

                                        SQLiteDatabase db7=conn.getWritableDatabase();


                                        try {
                                            //select nombre,telefono from usuario where codigo=?
                                            String sql2="UPDATE "+Utilidades.TABLA_STOCK+
                                                    " SET "+Utilidades.CAMPO_CANTIDAD_STOCK+" = "+Utilidades.CAMPO_CANTIDAD_STOCK+" + "+listaRegPedidos.get(i).getCantidadProductoPedidoDetalle()+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = '"+listaRegPedidos.get(i).getNombreProductoPedidoDetalle()+"' AND "+Utilidades.CAMPO_ID_LOTE_STOCK+" = "+listaRegPedidos.get(i).getLotePedidoDetalle();


                                            Log.d("cadena",sql2);

                                            db7.execSQL(sql2);




                                        }catch (Exception e){

                                            Log.d("elerrorupdate",e.toString());




                                        }


                                        db7.close();


                                    }
                                    catch (Exception ex){

                                        Log.d("elerrorupdate2",ex.toString());
                                    }

                                    }

                                    Log.d("consulta",sql);

                                    Bundle bundle=new Bundle();




                                    bundle.putString("identificacion", valorPasado);
                                    bundle.putString("nombres", nombrePasado);
                                    bundle.putString("apellidos", apellidoPasado);
                                    bundle.putString("correo", emailPasado);
                                    bundle.putInt("id",idPasado);



                                    Intent intent5 = new Intent(DetallePedidoActivity.this, CarritoActivity.class);
                                    intent5.putExtras(bundle);
                                    startActivity(intent5);
                                    finish();

                                }

                                catch (Exception ex){
                                    Toast.makeText(DetallePedidoActivity.this, "No se pudo eliminar el pedido" , Toast.LENGTH_SHORT).show();


                                }


                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(DetallePedidoActivity.this);

                builder.setMessage("¿SEGURO DESEA BORRAR EL PEDIDO?").setPositiveButton("SI", dialogClickListener)
                        .setNegativeButton("NO", dialogClickListener).setTitle("BORRAR PEDIDO").setIcon(R.drawable.icon_password).show();



            }
        });
    }


    private void llenarCamposListViewPedidos() {

        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={idTabla.toString()};

        DetallePedido usuario=null;
        listaRegPedidos=new ArrayList<DetallePedido>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_PEDIDO_DETALLE+" WHERE "+Utilidades.CAMPO_PEDIDOIDDETALLE+"=? ",parametros);

        while (cursor.moveToNext()){
            usuario=new DetallePedido();

            idTabla=cursor.getInt(0);

            usuario.setPedidoIdPedidoDetalle(cursor.getInt(1));
            usuario.setDocumentoPedidoDetalle(cursor.getInt(2));
            usuario.setNombreProductoPedidoDetalle(cursor.getString(3));
            usuario.setLotePedidoDetalle(cursor.getInt(4));
            usuario.setDescripcionProductoPedidoDetalle(cursor.getString(5));
            usuario.setIdentificacionPedidoDetalle(cursor.getString(6));
            usuario.setCantidadProductoPedidoDetalle(cursor.getDouble(7));
            usuario.setPrecioProductoPedidoDetalle(cursor.getDouble(8));
            usuario.setDescuentoProductoPedidoDetalle(cursor.getDouble(9));
            usuario.setValorPedidoDetalle(cursor.getDouble(10));
            usuario.setIvaValorPedidoDetalle(cursor.getDouble(11));




            listaRegPedidos.add(usuario);
        }
        obtenerListaPedidos();
        db.close();
    }

    private void obtenerListaPedidos() {
        listaInformacionPedidos=new ArrayList<String>();

        for (int i=0; i<listaRegPedidos.size();i++){
            listaInformacionPedidos.add(listaRegPedidos.get(i).getNombreProductoPedidoDetalle()+" |      "
                    +listaRegPedidos.get(i).getLotePedidoDetalle()+"         | "
                    +listaRegPedidos.get(i).getCantidadProductoPedidoDetalle()+" | $"+listaRegPedidos.get(i).getPrecioProductoPedidoDetalle()+" |   $"+listaRegPedidos.get(i).getValorPedidoDetalle());


        }


        ArrayAdapter adaptador2=new ArrayAdapter(this,R.layout.milistview,listaInformacionPedidos);
        listViewRegPedidos.setAdapter(adaptador2);

    }


    private void almacenardetallespedido(Integer idPedido){



        for (int i=0; i<listaRegPedidos.size();i++){


            final Integer j=i;








            final String TAG = DetalleRegistroClienteActivity.class.getName();



            // String url = "http://192.168.0.253:8888/spirit-mobile/api/pedido/guardar";

            String url ="http://192.168.0.93:8989/api/pedido/guardarDetalle";

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());





            Map<String, Object> postParam= new HashMap<String, Object>();
            postParam.put("pedidoId",idPedido);
            postParam.put("documento",44);
            postParam.put("producto",listaRegPedidos.get(i).getNombreProductoPedidoDetalle());
            postParam.put("lote",listaRegPedidos.get(i).getLotePedidoDetalle());
            postParam.put("descripcion",listaRegPedidos.get(i).getDescripcionProductoPedidoDetalle());
            postParam.put("cantidadPedida",listaRegPedidos.get(i).getCantidadProductoPedidoDetalle());
            postParam.put("cantidad",listaRegPedidos.get(i).getCantidadProductoPedidoDetalle());
            postParam.put("precio",listaRegPedidos.get(i).getPrecioProductoPedidoDetalle());
            postParam.put("descuento",listaRegPedidos.get(i).getDescuentoProductoPedidoDetalle());
            postParam.put("valor",listaRegPedidos.get(i).getValorPedidoDetalle());
            postParam.put("iva",listaRegPedidos.get(i).getIvaValorPedidoDetalle());
            postParam.put("descuentoGlobal",descuentoGlobalPedido);
            postParam.put("identificacion",identificacionClientePedido);




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









/*




                                    SQLiteDatabase db=conn.getWritableDatabase();

                                    // db.execSQL("delete from "+ Utilidades.TABLA_REG_CLIENTE+" where "+Utilidades.CAMPO_ID_TABLA_REG_CLIENTE+" in "+idTabla);

                                    db.execSQL("delete from "+Utilidades.TABLA_PEDIDO+" where "+Utilidades.CAMPO_ID_TABLA_PEDIDO+"='"+idTabla+"'");

                                    db.close();


                                    Bundle bundle=new Bundle();




                                    bundle.putString("identificacion", valorPasado);
                                    bundle.putString("nombres", nombrePasado);
                                    bundle.putString("apellidos", apellidoPasado);
                                    bundle.putString("correo", emailPasado);
                                    bundle.putInt("id",idPasado);



                                    Intent intent5 = new Intent(DetallePedidoActivity.this, CarritoActivity.class);
                                    intent5.putExtras(bundle);
                                    startActivity(intent5);



                                    finish();

*/





                                    Toast.makeText(getApplicationContext(),
                                            "Sincronización Correcta",  Toast.LENGTH_SHORT).show();

                                    hideDialog();


                                }

                                else {
                                    hideDialog();
                                    Toast.makeText(getApplicationContext(), "Sincronización Fallida", Toast.LENGTH_LONG).show();

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


            try {

                //sleep 5 seconds
                Thread.sleep(100);



            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }

        SQLiteDatabase db6=conn.getWritableDatabase();


        String sql="DELETE FROM "+Utilidades.TABLA_PEDIDO+" WHERE "+Utilidades.CAMPO_ID_TABLA_PEDIDO+"="+pedidoIdTabla+"";

        db6.execSQL(sql);


        db6.close();

        Log.d("consulta",sql);

        Bundle bundle=new Bundle();




        bundle.putString("identificacion", valorPasado);
        bundle.putString("nombres", nombrePasado);
        bundle.putString("apellidos", apellidoPasado);
        bundle.putString("correo", emailPasado);
        bundle.putInt("id",idPasado);



        Intent intent5 = new Intent(DetallePedidoActivity.this, CarritoActivity.class);
        intent5.putExtras(bundle);
        startActivity(intent5);
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
