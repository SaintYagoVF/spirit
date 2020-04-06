package net.latinus.spirit;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import net.latinus.spirit.Entidades.Cliente;
import net.latinus.spirit.Entidades.Productos;
import net.latinus.spirit.Entidades.RegistroCliente;
import net.latinus.spirit.Entidades.SpinBodega;
import net.latinus.spirit.Entidades.SpinCorporacion;
import net.latinus.spirit.Entidades.SpinDocumento;
import net.latinus.spirit.Entidades.SpinFormaPago;
import net.latinus.spirit.Entidades.SpinListaPrecio;
import net.latinus.spirit.Entidades.SpinLote;
import net.latinus.spirit.Entidades.SpinMoneda;
import net.latinus.spirit.Entidades.SpinOficina;
import net.latinus.spirit.Entidades.SpinSri;
import net.latinus.spirit.Entidades.SpinTipoDocumento;
import net.latinus.spirit.Entidades.UnionCR;
import net.latinus.spirit.Entidades.ValoresPedido;
import net.latinus.spirit.Utilidades.Utilidades;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class RegistroPedidosActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener{


    TextView  txtFechaPed,  txtObservacion, txtProducto, txtDescGlobal, txtCantidad, txtPrecio, txtDescuento, txtValor, txtIva, txtSubtotal,txtValorTotal;



    Dialog myDialog;

    ListView listViewClientes;
    ArrayList<String> listaInformacion;
    ArrayList<Cliente> listaClientes;


    ListView listViewRegClientes;

    ArrayList<RegistroCliente> listaRegClientes;

    private ArrayAdapter adapta;

    private Double ivaporProducto;

    ConexionSQLiteHelper conn;

    TextView txtClientes2;

    String nomClientePasado;
    String idClientePasado;

    Integer oficinaidPedido;
    Spinner comboOficina;
    ArrayList<String> listaOficina;
    ArrayList<SpinOficina> oficinaList;

    Integer tipodocPedido;
    Spinner comboTipoDoc;
    ArrayList<String> listaTipoDoc;
    ArrayList<SpinTipoDocumento> tipodocList;


    Integer formapagoPedido;
    Spinner comboFormaPago;
    ArrayList<String> listaFormaPago;
    ArrayList<SpinFormaPago> formapagoList;


    Integer monedaPedido;
    Spinner comboMoneda;
    ArrayList<String> listaMoneda;
    ArrayList<SpinMoneda> monedaList;


    Integer origendocPedido;

    Integer vendedorPedido;

    Integer bodegaPedido;
    Spinner comboBodega;
    ArrayList<String> listaBodega;
    ArrayList<SpinBodega> bodegaList;

    Integer listaprecioPedido;
    Spinner comboListaPrecio;
    ArrayList<String> listaListaPrecio;
    ArrayList<SpinListaPrecio> listaprecioList;

    String fechaPedidoCreacion;

    Double latitudPedido;

    Double longitudPedido;


    Double subtotal=0.0;
    Double iva;

    Double total;



    Integer sriPedido;
    Spinner comboSri;
    ArrayList<String> listaSri;
    ArrayList<SpinSri> sriList;

    Integer documentoPedido;
    Spinner comboDocumento;
    ArrayList<String> listaDocumento;
    ArrayList<SpinDocumento> documentoList;



    Integer lotePedido;
    Spinner comboLote;
    ArrayList<String> listaLote;
    ArrayList<SpinLote> loteList;


    Double precioPedido;


    Double descuentoPedido;

    Double valorPedido;

    Double ivaPedido;

    Integer contadorPedido;




    ListView listViewProductos;
    ArrayList<String> listaProductos;


    ArrayList<ValoresPedido> pedidoPasado;
    ArrayList<String> listainformacionPedidos;



    Integer Chifle_Dulce_Tira=0;
    Integer Chifle_Sal_Tira=0;
    Integer Cuerito_Grande=0;
    Integer Cuerito_Mediano=0;
    Integer Cuerito_Pequeno_Tira =0;
    Integer  Mani_Dulce_Paq_20=0;
    Integer  Mani_Dulce_Paq_10=0;
    Integer  Mani_Sal=0;
    Integer  Mixto_Grande=0;
    Integer  Mixto_Mediano=0;
    Integer  Mixto_Pequeno=0;
    Integer  Papa_Hoja_Gigante=0;
    Integer  Papa_Hoja_Grande=0;
    Integer  Papa_Hoja_Mediana=0;
    Integer  Papa_Pequena=0;
    Integer  Papa_Picada_Gigante=0;
    Integer Papa_Picada_Grande=0;
    Integer  Papa_Picada_Mediana=0;
    Integer  Papa_Picada_Pequena=0;
    Integer  Tostado_Tiras=0;


    Button btnCancelarPedido;

    Button btnCancelarPopup;
    Button btnAceptarPedido;


    //GPS

    private static final String TAG = "MainActivity";
    private TextView mLatitudeTextView;
    private TextView mLongitudeTextView;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private LocationManager mLocationManager;

    private LocationRequest mLocationRequest;
    private com.google.android.gms.location.LocationListener listener;
    private long UPDATE_INTERVAL = 2 * 500;  /* 5 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_pedidos);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        final DecimalFormat twoDForm = new DecimalFormat("#.##");

        comboTipoDoc= (Spinner) findViewById(R.id.spinPedTipoDoc);
        comboFormaPago= (Spinner) findViewById(R.id.spinPedFormaPago);

        txtIva=(TextView)findViewById(R.id.simpleTex302);

        txtDescGlobal=(TextView)findViewById(R.id.simpleTex202);

        txtSubtotal=(TextView)findViewById(R.id.simpleTex102);

        txtValorTotal=(TextView)findViewById(R.id.simpleTex402);




        comboListaPrecio=(Spinner)findViewById(R.id.spinPedListaPrecio);
        comboSri=(Spinner)findViewById(R.id.spinPedSri);

        pedidoPasado = new ArrayList<ValoresPedido>();


        listViewProductos= (ListView) findViewById(R.id.listViewDetalle);

        btnCancelarPedido=(Button)findViewById(R.id.btnPedCancelar);

        btnAceptarPedido=(Button)findViewById(R.id.btnPedAceptar);

        contadorPedido=1;

        try {

            SQLiteDatabase db = conn.getReadableDatabase();


            //select * from usuarios
            Cursor cursor = db.rawQuery("SELECT seq FROM sqlite_sequence WHERE name='" + Utilidades.TABLA_PEDIDO + "'", null);

            while (cursor.moveToNext()) {



                contadorPedido = cursor.getInt(0)+1;


                Log.i("contadorPedido", contadorPedido.toString());


            }

            db.close();
        }
        catch (Exception ex){

            Log.i("contadorPedido", ex.toString());

        }


        Log.i("contadorPedido", contadorPedido.toString());


        //GPS




        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);

        checkLocation();




        myDialog = new Dialog(this);


        txtClientes2=(TextView)findViewById(R.id.siT23);

        txtFechaPed=(TextView)findViewById(R.id.siT102);

        txtObservacion=(TextView)findViewById(R.id.siT142);








        Bundle bundle = this.getIntent().getExtras();





        Productos user=null;




        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());


        txtFechaPed.setText(currentDateandTime);

        fechaPedidoCreacion=currentDateandTime;


        if(bundle !=null)
        {

            user=(Productos)bundle.getSerializable("producto");

            //ObtainBundleData in the object
            String strdata = bundle.getString("idCliente");

            idClientePasado=strdata;

            String strdata1 = bundle.getString("nomCliente");

            nomClientePasado=strdata1;

            Integer strdata2 = bundle.getInt("idEmpleado");

            vendedorPedido=strdata2;





            txtClientes2.setText(nomClientePasado);


            Log.d("Vendedor ",vendedorPedido.toString());

            Log.d("Valor Identif ",idClientePasado);

            Log.d("Valor Identif2 ",nomClientePasado);

            Chifle_Dulce_Tira=user.getChifle_Dulce_Tira();
            if(Chifle_Dulce_Tira==null)
                Chifle_Dulce_Tira=0;

            Log.d("comid Chifl D",Chifle_Dulce_Tira.toString());


            Chifle_Sal_Tira=user.getChifle_Sal_Tira();
            if(Chifle_Sal_Tira==null)
                Chifle_Sal_Tira=0;

            Log.d("comid Chifl S",Chifle_Sal_Tira.toString());

            Cuerito_Grande=user.getCuerito_Grande();
            if(Cuerito_Grande==null)
                Cuerito_Grande=0;

            Log.d("comid Cuer G",Cuerito_Grande.toString());

            Cuerito_Mediano=user.getCuerito_Mediano();
            if(Cuerito_Mediano==null)
                Cuerito_Mediano=0;

            Log.d("comid Cuer M",Cuerito_Mediano.toString());

            Cuerito_Pequeno_Tira=user.getCuerito_Pequeno_Tira();
            if(Cuerito_Pequeno_Tira==null)
                Cuerito_Pequeno_Tira=0;

            Log.d("comid Cuer P",Cuerito_Pequeno_Tira.toString());

            Mani_Dulce_Paq_20=user.getMani_Dulce_Paq_20();
            if(Mani_Dulce_Paq_20==null)
                Mani_Dulce_Paq_20=0;

            Log.d("comid Mani 20",Mani_Dulce_Paq_20.toString());

            Mani_Dulce_Paq_10=user.getMani_Dulce_Paq_10();
            if(Mani_Dulce_Paq_10==null)
                Mani_Dulce_Paq_10=0;

            Log.d("comid Mani 10",Mani_Dulce_Paq_10.toString());

            Mani_Sal=user.getMani_Sal();
            if(Mani_Sal==null)
                Mani_Sal=0;

            Log.d("comid Mani S",Mani_Sal.toString());

            Mixto_Grande=user.getMixto_Grande();
            if(Mixto_Grande==null)
                Mixto_Grande=0;

            Log.d("comid Mix G",Mixto_Grande.toString());

            Mixto_Mediano=user.getMixto_Mediano();
            if(Mixto_Mediano==null)
                Mixto_Mediano=0;

            Log.d("comid Mix M",Mixto_Mediano.toString());

            Mixto_Pequeno=user.getMixto_Pequeno();
            if(Mixto_Pequeno==null)
                Mixto_Pequeno=0;

            Log.d("comid Mix P",Mixto_Pequeno.toString());

            Papa_Hoja_Gigante=user.getPapa_Hoja_Gigante();
            if(Papa_Hoja_Gigante==null)
                Papa_Hoja_Gigante=0;

            Log.d("comid Papa G",Papa_Hoja_Gigante.toString());

            Papa_Hoja_Grande=user.getPapa_Hoja_Grande();
            if(Papa_Hoja_Grande==null)
                Papa_Hoja_Grande=0;

            Log.d("comid Papa G",Papa_Hoja_Grande.toString());

            Papa_Hoja_Mediana=user.getPapa_Hoja_Mediana();
            if(Papa_Hoja_Mediana==null)
                Papa_Hoja_Mediana=0;

            Log.d("comid Papa M",Papa_Hoja_Mediana.toString());

            Papa_Pequena=user.getPapa_Pequena();
            if(Papa_Pequena==null)
                Papa_Pequena=0;

            Log.d("comid Papa P",Papa_Pequena.toString());

            Papa_Picada_Gigante=user.getPapa_Picada_Gigante();
            if(Papa_Picada_Gigante==null)
                Papa_Picada_Gigante=0;

            Log.d("comid Papap Gi",Papa_Picada_Gigante.toString());

            Papa_Picada_Grande=user.getPapa_Picada_Grande();
            if(Papa_Picada_Grande==null)
                Papa_Picada_Grande=0;

            Log.d("comid Papap GR",Papa_Picada_Grande.toString());

            Papa_Picada_Mediana=user.getPapa_Picada_Mediana();
            if(Papa_Picada_Mediana==null)
                Papa_Picada_Mediana=0;

            Log.d("comid Papap M",Papa_Picada_Mediana.toString());

            Papa_Picada_Pequena=user.getPapa_Picada_Pequena();
            if(Papa_Picada_Pequena==null)
                Papa_Picada_Pequena=0;

            Log.d("comid PapaP P",Papa_Picada_Pequena.toString());

            Tostado_Tiras=user.getTostado_Tiras();
            if(Tostado_Tiras==null)
                Tostado_Tiras=0;

            Log.d("comid Tost",Tostado_Tiras.toString());


            pedidoPasado=(ArrayList)bundle.getSerializable("pedido");








            //Do something here if data  received
        }
        else
        {
            //Do something here if data not received
        }


       // llenarTabla();

        llenarTabla2();

        consultarListaOficina();
        consultarListaTipoDoc();
        consultarFormaPago();
        consultarMoneda();
        obtenerOrigenDoc();
        consultarBodega();
        consultarListaPrecio();
        consultarSri();
        consultarDocumento();
        consultarLote();











        ArrayAdapter<CharSequence> adaptador2=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaTipoDoc);

        comboTipoDoc.setAdapter(adaptador2);

        comboTipoDoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                if (position!=0){
                   /* txtDocumento.setText(personasList.get(position-1).getId().toString());
                    txtNombre.setText(personasList.get(position-1).getNombre());
                    txtTelefono.setText(personasList.get(position-1).getClave());

                    */

                    tipodocPedido = tipodocList.get(position-1).getIdSpinTipoDocumento();


                }else{
                    tipodocPedido = 0;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tipodocPedido = 0;
            }
        });




        ArrayAdapter<CharSequence> adaptador3=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaFormaPago);

        comboFormaPago.setAdapter(adaptador3);

        comboFormaPago.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                if (position!=0){
                   /* txtDocumento.setText(personasList.get(position-1).getId().toString());
                    txtNombre.setText(personasList.get(position-1).getNombre());
                    txtTelefono.setText(personasList.get(position-1).getClave());

                    */

                    formapagoPedido = formapagoList.get(position-1).getIdSpinFormaPago();





                }else{
                    formapagoPedido = 0;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                formapagoPedido = 0;
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


                }else{
                    listaprecioPedido = 0;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                listaprecioPedido = 0;
            }
        });


        ArrayAdapter<CharSequence> adaptador7=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaSri);

        comboSri.setAdapter(adaptador7);

        comboSri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                if (position!=0){
                   /* txtDocumento.setText(personasList.get(position-1).getId().toString());
                    txtNombre.setText(personasList.get(position-1).getNombre());
                    txtTelefono.setText(personasList.get(position-1).getClave());

                    */

                    sriPedido = sriList.get(position-1).getIdSpinSri();


                    iva=Double.valueOf(twoDForm.format(((subtotal-Double.parseDouble(txtDescGlobal.getText().toString()))*getValorCombo())/100));

                    txtIva.setText(iva.toString());


                    total=Double.valueOf(twoDForm.format(subtotal-Double.parseDouble(txtDescGlobal.getText().toString())+iva));

                    txtValorTotal.setText(total.toString());


                }else{
                    sriPedido = 0;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sriPedido = 0;
            }
        });




        listViewProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {



            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Button btnCancelar2;
                myDialog.setContentView(R.layout.popup_detalle_pedido);




                Object o = listViewProductos.getItemAtPosition(position);

                final String nombr = (String)o;

               // String[] parts=nombr.split("|");

                StringTokenizer st = new StringTokenizer(nombr, "|");
               final String nombr2 = st.nextToken().trim();

             //   final String nombr2=parts[0];



                btnCancelar2 = (Button)myDialog.findViewById(R.id.btnPopupCancelarPed);


                btnAceptarPedido=(Button)myDialog.findViewById(R.id.btnPopupAceptarPed);




                txtProducto=(TextView)myDialog.findViewById(R.id.simpleTex12);

                txtCantidad=(TextView)myDialog.findViewById(R.id.simpleTex22);

                txtPrecio=(TextView)myDialog.findViewById(R.id.simpleTex62);

                txtDescuento=(TextView)myDialog.findViewById(R.id.simpleTex42);

                txtValor=(TextView)myDialog.findViewById(R.id.simpleTex52);




                txtProducto.setText(nombr2);

                Log.d("cadenasin",nombr);
                Log.d("cadena",nombr2);



                switch(nombr2){

                    case "CHIFLE DULCE TIRA":
                        for (int i=0; i<pedidoPasado.size();i++){

                            if(txtProducto.getText().toString().equals(pedidoPasado.get(i).getNombreValorPedido()))
                            {

                                txtCantidad.setText(pedidoPasado.get(i).getCantidadValorPedido().toString());
                                txtPrecio.setText(pedidoPasado.get(i).getPrecioValorPedido().toString());

                                txtValor.setText(pedidoPasado.get(i).getTotalValorPedido().toString());


                            }
                        }

                    Chifle_Dulce_Tira=0;


                        break;

                    case "CHIFLE SAL TIRA":

                        for (int i=0; i<pedidoPasado.size();i++){

                            if(txtProducto.getText().toString().equals(pedidoPasado.get(i).getNombreValorPedido()))
                            {

                                txtCantidad.setText(pedidoPasado.get(i).getCantidadValorPedido().toString());
                                txtPrecio.setText(pedidoPasado.get(i).getPrecioValorPedido().toString());

                                txtValor.setText(pedidoPasado.get(i).getTotalValorPedido().toString());


                            }
                        }

                        Chifle_Sal_Tira=0;

                        break;

                    case "CUERITO GRANDE":
                        for (int i=0; i<pedidoPasado.size();i++){

                            if(txtProducto.getText().toString().equals(pedidoPasado.get(i).getNombreValorPedido()))
                            {

                                txtCantidad.setText(pedidoPasado.get(i).getCantidadValorPedido().toString());
                                txtPrecio.setText(pedidoPasado.get(i).getPrecioValorPedido().toString());

                                txtValor.setText(pedidoPasado.get(i).getTotalValorPedido().toString());


                            }
                        }
                        Cuerito_Grande=0;

                        break;

                    case "CUERITO MEDIANO":
                        for (int i=0; i<pedidoPasado.size();i++){

                            if(txtProducto.getText().toString().equals(pedidoPasado.get(i).getNombreValorPedido()))
                            {

                                txtCantidad.setText(pedidoPasado.get(i).getCantidadValorPedido().toString());
                                txtPrecio.setText(pedidoPasado.get(i).getPrecioValorPedido().toString());

                                txtValor.setText(pedidoPasado.get(i).getTotalValorPedido().toString());


                            }
                        }
                        Cuerito_Mediano=0;

                        break;

                    case "CUERITO PEQUEÑO TIRA":
                        for (int i=0; i<pedidoPasado.size();i++){

                            if(txtProducto.getText().toString().equals(pedidoPasado.get(i).getNombreValorPedido()))
                            {

                                txtCantidad.setText(pedidoPasado.get(i).getCantidadValorPedido().toString());
                                txtPrecio.setText(pedidoPasado.get(i).getPrecioValorPedido().toString());

                                txtValor.setText(pedidoPasado.get(i).getTotalValorPedido().toString());


                            }
                        }
                        Cuerito_Pequeno_Tira=0;

                        break;

                    case "MANI DULCE PAQ. 20":
                        for (int i=0; i<pedidoPasado.size();i++){

                            if(txtProducto.getText().toString().equals(pedidoPasado.get(i).getNombreValorPedido()))
                            {

                                txtCantidad.setText(pedidoPasado.get(i).getCantidadValorPedido().toString());
                                txtPrecio.setText(pedidoPasado.get(i).getPrecioValorPedido().toString());

                                txtValor.setText(pedidoPasado.get(i).getTotalValorPedido().toString());


                            }
                        }
                        Mani_Dulce_Paq_20=0;

                        break;

                    case "MANI DULCE PAQ. 10":
                        for (int i=0; i<pedidoPasado.size();i++){

                            if(txtProducto.getText().toString().equals(pedidoPasado.get(i).getNombreValorPedido()))
                            {

                                txtCantidad.setText(pedidoPasado.get(i).getCantidadValorPedido().toString());
                                txtPrecio.setText(pedidoPasado.get(i).getPrecioValorPedido().toString());

                                txtValor.setText(pedidoPasado.get(i).getTotalValorPedido().toString());


                            }
                        }
                        Mani_Dulce_Paq_10=0;

                        break;

                    case "MANI SAL":
                        for (int i=0; i<pedidoPasado.size();i++){

                            if(txtProducto.getText().toString().equals(pedidoPasado.get(i).getNombreValorPedido()))
                            {

                                txtCantidad.setText(pedidoPasado.get(i).getCantidadValorPedido().toString());
                                txtPrecio.setText(pedidoPasado.get(i).getPrecioValorPedido().toString());

                                txtValor.setText(pedidoPasado.get(i).getTotalValorPedido().toString());


                            }
                        }
                        Mani_Sal=0;

                        break;

                    case "MIXTO GRANDE":
                        for (int i=0; i<pedidoPasado.size();i++){

                            if(txtProducto.getText().toString().equals(pedidoPasado.get(i).getNombreValorPedido()))
                            {

                                txtCantidad.setText(pedidoPasado.get(i).getCantidadValorPedido().toString());
                                txtPrecio.setText(pedidoPasado.get(i).getPrecioValorPedido().toString());

                                txtValor.setText(pedidoPasado.get(i).getTotalValorPedido().toString());


                            }
                        }
                        Mixto_Grande=0;

                        break;

                    case "MIXTO MEDIANO":
                        for (int i=0; i<pedidoPasado.size();i++){

                            if(txtProducto.getText().toString().equals(pedidoPasado.get(i).getNombreValorPedido()))
                            {

                                txtCantidad.setText(pedidoPasado.get(i).getCantidadValorPedido().toString());
                                txtPrecio.setText(pedidoPasado.get(i).getPrecioValorPedido().toString());

                                txtValor.setText(pedidoPasado.get(i).getTotalValorPedido().toString());


                            }
                        }
                        Mixto_Mediano=0;

                        break;

                    case "MIXTO PEQUEÑO":
                        for (int i=0; i<pedidoPasado.size();i++){

                            if(txtProducto.getText().toString().equals(pedidoPasado.get(i).getNombreValorPedido()))
                            {

                                txtCantidad.setText(pedidoPasado.get(i).getCantidadValorPedido().toString());
                                txtPrecio.setText(pedidoPasado.get(i).getPrecioValorPedido().toString());

                                txtValor.setText(pedidoPasado.get(i).getTotalValorPedido().toString());


                            }
                        }
                        Mixto_Pequeno=0;

                        break;

                    case "PAPA HOJA GIGANTE":
                        for (int i=0; i<pedidoPasado.size();i++){

                            if(txtProducto.getText().toString().equals(pedidoPasado.get(i).getNombreValorPedido()))
                            {

                                txtCantidad.setText(pedidoPasado.get(i).getCantidadValorPedido().toString());
                                txtPrecio.setText(pedidoPasado.get(i).getPrecioValorPedido().toString());

                                txtValor.setText(pedidoPasado.get(i).getTotalValorPedido().toString());


                            }
                        }
                        Papa_Hoja_Gigante=0;

                        break;

                    case "PAPA HOJA GRANDE":
                        for (int i=0; i<pedidoPasado.size();i++){

                            if(txtProducto.getText().toString().equals(pedidoPasado.get(i).getNombreValorPedido()))
                            {

                                txtCantidad.setText(pedidoPasado.get(i).getCantidadValorPedido().toString());
                                txtPrecio.setText(pedidoPasado.get(i).getPrecioValorPedido().toString());

                                txtValor.setText(pedidoPasado.get(i).getTotalValorPedido().toString());


                            }
                        }
                        Papa_Hoja_Grande=0;

                        break;

                    case "PAPA HOJA MEDIANA":
                        for (int i=0; i<pedidoPasado.size();i++){

                            if(txtProducto.getText().toString().equals(pedidoPasado.get(i).getNombreValorPedido()))
                            {

                                txtCantidad.setText(pedidoPasado.get(i).getCantidadValorPedido().toString());
                                txtPrecio.setText(pedidoPasado.get(i).getPrecioValorPedido().toString());

                                txtValor.setText(pedidoPasado.get(i).getTotalValorPedido().toString());


                            }
                        }

                        Papa_Hoja_Mediana=0;
                        break;

                    case "PAPA PEQUEÑA":
                        for (int i=0; i<pedidoPasado.size();i++){

                            if(txtProducto.getText().toString().equals(pedidoPasado.get(i).getNombreValorPedido()))
                            {

                                txtCantidad.setText(pedidoPasado.get(i).getCantidadValorPedido().toString());
                                txtPrecio.setText(pedidoPasado.get(i).getPrecioValorPedido().toString());

                                txtValor.setText(pedidoPasado.get(i).getTotalValorPedido().toString());


                            }
                        }
                        Papa_Pequena=0;

                        break;

                    case "PAPA PICADA GIGANTE":

                        for (int i=0; i<pedidoPasado.size();i++){

                            if(txtProducto.getText().toString().equals(pedidoPasado.get(i).getNombreValorPedido()))
                            {

                                txtCantidad.setText(pedidoPasado.get(i).getCantidadValorPedido().toString());
                                txtPrecio.setText(pedidoPasado.get(i).getPrecioValorPedido().toString());

                                txtValor.setText(pedidoPasado.get(i).getTotalValorPedido().toString());


                            }
                        }

                       // txtCantidad.setText(Papa_Picada_Gigante.toString());
                        Papa_Picada_Gigante=0;

                        break;

                    case "PAPA PICADA GRANDE":
                        for (int i=0; i<pedidoPasado.size();i++){

                            if(txtProducto.getText().toString().equals(pedidoPasado.get(i).getNombreValorPedido()))
                            {

                                txtCantidad.setText(pedidoPasado.get(i).getCantidadValorPedido().toString());
                                txtPrecio.setText(pedidoPasado.get(i).getPrecioValorPedido().toString());

                                txtValor.setText(pedidoPasado.get(i).getTotalValorPedido().toString());


                            }
                        }
                        Papa_Picada_Grande=0;

                        break;

                    case "PAPA PICADA MEDIANA":
                        for (int i=0; i<pedidoPasado.size();i++){

                            if(txtProducto.getText().toString().equals(pedidoPasado.get(i).getNombreValorPedido()))
                            {

                                txtCantidad.setText(pedidoPasado.get(i).getCantidadValorPedido().toString());
                                txtPrecio.setText(pedidoPasado.get(i).getPrecioValorPedido().toString());

                                txtValor.setText(pedidoPasado.get(i).getTotalValorPedido().toString());


                            }
                        }
                        Papa_Picada_Mediana=0;

                        break;

                    case "PAPA PICADA PEQUEÑA":
                        for (int i=0; i<pedidoPasado.size();i++){

                            if(txtProducto.getText().toString().equals(pedidoPasado.get(i).getNombreValorPedido()))
                            {

                                txtCantidad.setText(pedidoPasado.get(i).getCantidadValorPedido().toString());
                                txtPrecio.setText(pedidoPasado.get(i).getPrecioValorPedido().toString());

                                txtValor.setText(pedidoPasado.get(i).getTotalValorPedido().toString());


                            }
                        }
                        Papa_Picada_Pequena=0;

                        break;

                    case "TOSTADO TIRAS":
                        for (int i=0; i<pedidoPasado.size();i++){

                            if(txtProducto.getText().toString().equals(pedidoPasado.get(i).getNombreValorPedido()))
                            {

                                txtCantidad.setText(pedidoPasado.get(i).getCantidadValorPedido().toString());
                                txtPrecio.setText(pedidoPasado.get(i).getPrecioValorPedido().toString());

                                txtValor.setText(pedidoPasado.get(i).getTotalValorPedido().toString());


                            }
                        }
                        Tostado_Tiras=0;

                        break;


                }


                SQLiteDatabase db=conn.getReadableDatabase();
                String[] parametros={listaprecioPedido.toString(),nombr2};

                try {
                    //select nombre,telefono from usuario where codigo=?
                    Cursor cursor=db.rawQuery("SELECT "+Utilidades.CAMPO_VALOR_PRECIO+
                            " FROM "+Utilidades.TABLA_PRECIO+" WHERE "+Utilidades.CAMPO_LISTA_PRECIO+"=? AND "+Utilidades.CAMPO_NOMBREPROD_PRECIO+"=? ",parametros);

                    cursor.moveToFirst();
                    precioPedido=cursor.getDouble(0);


                    txtPrecio.setText(precioPedido.toString());
                    Log.d("precio",precioPedido.toString());



                }catch (Exception e){

                    Log.d("elerror",e.toString());




                }


                db.close();



               final Double valorPedido;

               final Integer valorCantidad=Integer.parseInt(txtCantidad.getText().toString());

              final  Double descPedido=Double.parseDouble(txtDescuento.getText().toString());



txtPrecio.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        try{
                            Double valor=Double.valueOf(twoDForm.format((Double.parseDouble(s.toString())-Double.parseDouble(txtDescuento.getText().toString()))*valorCantidad));
                            txtValor.setText(valor.toString());


                        }
                        catch (Exception ex){

                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                    txtCantidad.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                            try{
                                Double valor=Double.valueOf(twoDForm.format((Double.parseDouble(txtPrecio.getText().toString())-Double.parseDouble(txtDescuento.getText().toString()))*(Double.parseDouble(s.toString()))));
                                txtValor.setText(valor.toString());
                            }
                            catch (Exception ex){

                            }

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });


                        txtDescuento.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {


                                try {

                                    if (Double.parseDouble(s.toString()) > Double.parseDouble(txtPrecio.getText().toString())) {
                                        Toast.makeText(getApplicationContext(), "El Descuento no puede ser mayor al precio del Producto", Toast.LENGTH_LONG).show();
                                        txtDescuento.setText("0.0");
                                    } else {

                                        try {
                                            Double valor = Double.valueOf(twoDForm.format((Double.parseDouble(txtPrecio.getText().toString()) - Double.parseDouble(s.toString())) * Double.parseDouble(txtCantidad.getText().toString())));
                                            txtValor.setText(valor.toString());
                                        } catch (Exception ex) {

                                        }

                                    }
                                }
                                catch (Exception ex){

                                }

                            }


                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });

                Double valorprod;
    try {
    valorprod = Double.parseDouble(txtValor.getText().toString());
    }
    catch (Exception ex){

    valorprod=0.0;
    }


                       final Double srival = getValorCombo();

                        Log.d("SRIVal",srival.toString());

                        ivaporProducto=valorprod*srival/100;

                      //  txtIva.setText(valorIva.toString());


                        txtValor.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                                Double valorIva2=Double.parseDouble(s.toString())*srival/100;

                                ivaporProducto=valorIva2;


                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });





                btnCancelar2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        switch(nombr2){

                            case "CHIFLE DULCE TIRA":


                                Chifle_Dulce_Tira=Integer.parseInt(txtCantidad.getText().toString());


                                break;

                            case "CHIFLE SAL TIRA":


                                Chifle_Sal_Tira=Integer.parseInt(txtCantidad.getText().toString());

                                break;

                            case "CUERITO GRANDE":

                                Cuerito_Grande=Integer.parseInt(txtCantidad.getText().toString());

                                break;

                            case "CUERITO MEDIANO":

                                Cuerito_Mediano=Integer.parseInt(txtCantidad.getText().toString());

                                break;

                            case "CUERITO PEQUEÑO TIRA":

                                Cuerito_Pequeno_Tira=Integer.parseInt(txtCantidad.getText().toString());

                                break;

                            case "MANI DULCE PAQ. 20":

                                Mani_Dulce_Paq_20=Integer.parseInt(txtCantidad.getText().toString());

                                break;

                            case "MANI DULCE PAQ. 10":

                                Mani_Dulce_Paq_10=Integer.parseInt(txtCantidad.getText().toString());

                                break;

                            case "MANI SAL":

                                Mani_Sal=Integer.parseInt(txtCantidad.getText().toString());

                                break;

                            case "MIXTO GRANDE":

                                Mixto_Grande=Integer.parseInt(txtCantidad.getText().toString());

                                break;

                            case "MIXTO MEDIANO":

                                Mixto_Mediano=Integer.parseInt(txtCantidad.getText().toString());

                                break;

                            case "MIXTO PEQUEÑO":

                                Mixto_Pequeno=Integer.parseInt(txtCantidad.getText().toString());

                                break;

                            case "PAPA HOJA GIGANTE":

                                Papa_Hoja_Gigante=Integer.parseInt(txtCantidad.getText().toString());

                                break;

                            case "PAPA HOJA GRANDE":

                                Papa_Hoja_Grande=Integer.parseInt(txtCantidad.getText().toString());

                                break;

                            case "PAPA HOJA MEDIANA":


                                Papa_Hoja_Mediana=Integer.parseInt(txtCantidad.getText().toString());
                                break;

                            case "PAPA PEQUEÑA":

                                Papa_Pequena=Integer.parseInt(txtCantidad.getText().toString());

                                break;

                            case "PAPA PICADA GIGANTE":

                                Papa_Picada_Gigante=Integer.parseInt(txtCantidad.getText().toString());

                                break;

                            case "PAPA PICADA GRANDE":

                                Papa_Picada_Grande=Integer.parseInt(txtCantidad.getText().toString());

                                break;

                            case "PAPA PICADA MEDIANA":

                                Papa_Picada_Mediana=Integer.parseInt(txtCantidad.getText().toString());

                                break;

                            case "PAPA PICADA PEQUEÑA":

                                Papa_Picada_Pequena=Integer.parseInt(txtCantidad.getText().toString());

                                break;

                            case "TOSTADO TIRAS":

                                Tostado_Tiras=Integer.parseInt(txtCantidad.getText().toString());

                                break;


                        }
                        myDialog.dismiss();
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

                btnAceptarPedido.setOnTouchListener(new View.OnTouchListener() {
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


                btnAceptarPedido.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //botonAceptar Pedido


                        /*
                        try {










                            SQLiteDatabase db = conn.getWritableDatabase();


                            ContentValues values = new ContentValues();
                            values.put(Utilidades.CAMPO_CONTADOR_PEDIDO, contadorPedido);
                            values.put(Utilidades.CAMPO_OFICINA_PEDIDO, oficinaidPedido);
                            values.put(Utilidades.CAMPO_TIPODOC_PEDIDO, tipodocPedido);
                            values.put(Utilidades.CAMPO_IDENTIFICACIONCLI_PEDIDO, idClientePasado);
                            values.put(Utilidades.CAMPO_NOMBRECLI_PEDIDO, nomClientePasado);
                            values.put(Utilidades.CAMPO_FORMAPAGO_PEDIDO, formapagoPedido);
                            values.put(Utilidades.CAMPO_MONEDA_PEDIDO, monedaPedido);
                            values.put(Utilidades.CAMPO_ORIGENDOC_PEDIDO, origendocPedido);
                            values.put(Utilidades.CAMPO_VENDEDOR_PEDIDO, vendedorPedido);
                            values.put(Utilidades.CAMPO_BODEGA_PEDIDO, bodegaPedido);
                            values.put(Utilidades.CAMPO_LISTAPRECIO_PEDIDO, listaprecioPedido);
                            values.put(Utilidades.CAMPO_FECHA_PEDIDO, fechaPedidoCreacion);
                            values.put(Utilidades.CAMPO_LATITUD_PEDIDO, latitudPedido);
                            values.put(Utilidades.CAMPO_LONGITUD_PEDIDO, longitudPedido);
                            values.put(Utilidades.CAMPO_USUARIO_PEDIDO, vendedorPedido);
                            values.put(Utilidades.CAMPO_OBSERVACION_PEDIDO, txtObservacion.getText().toString());
                            values.put(Utilidades.CAMPO_SRIIVA_PEDIDO, sriPedido);
                            values.put(Utilidades.CAMPO_DOCUMENTO_PEDIDO, documentoPedido);
                            values.put(Utilidades.CAMPO_NOMBREPROD_PEDIDO, nombr2);
                            values.put(Utilidades.CAMPO_LOTE_PEDIDO,lotePedido);
                            values.put(Utilidades.CAMPO_DESCRIPCION_PEDIDO, nombr2);
                            values.put(Utilidades.CAMPO_CANTIDADPROD_PEDIDO, Double.parseDouble(txtCantidad.getText().toString()));
                            values.put(Utilidades.CAMPO_PRECIOPROD_PEDIDO, Double.parseDouble(txtPrecio.getText().toString()));
                            values.put(Utilidades.CAMPO_DESCUENTOPROD_PEDIDO, Double.parseDouble(txtDescuento.getText().toString()));
                            values.put(Utilidades.CAMPO_VALOR_PEDIDO, Double.parseDouble(txtValor.getText().toString()));
                            values.put(Utilidades.CAMPO_IVA_PEDIDO, Double.parseDouble(txtIva.getText().toString()));
                            values.put(Utilidades.CAMPO_DESCUENTOGLOBAL_PEDIDO, Double.parseDouble(txtDescGlobal.getText().toString()));




                            db.insert(Utilidades.TABLA_PEDIDO, Utilidades.CAMPO_ID_TABLA_PEDIDO, values);

                            db.close();






                            try{

                                SQLiteDatabase db2=conn.getReadableDatabase();


                                try {
                                    //select nombre,telefono from usuario where codigo=?
                                    String sql="UPDATE "+Utilidades.TABLA_STOCK+
                                            " SET "+Utilidades.CAMPO_CANTIDAD_STOCK+" = "+Utilidades.CAMPO_CANTIDAD_STOCK+" - "+Double.parseDouble(txtCantidad.getText().toString())+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = '"+nombr2+"'";


                                    Log.d("cadena",sql);

                                    db2.execSQL(sql);



                                }catch (Exception e){

                                    Log.d("elerrorupdate",e.toString());




                                }


                                db2.close();


                            }
                            catch (Exception ex){

                                Log.d("elerrorupdate2",ex.toString());
                            }



                            //llenarTabla();




                            myDialog.dismiss();


                            Toast.makeText(getApplicationContext(), "Pedido Creado", Toast.LENGTH_LONG).show();



                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error al crear Pedido", Toast.LENGTH_LONG).show();



                        }


                        */


                        for (int i=0; i<pedidoPasado.size();i++){

                            if(txtProducto.getText().toString().equals(pedidoPasado.get(i).getNombreValorPedido()))
                            {
                                pedidoPasado.get(i).setCantidadValorPedido(Integer.parseInt(txtCantidad.getText().toString()));
                                pedidoPasado.get(i).setPrecioValorPedido(Double.parseDouble(txtPrecio.getText().toString()));
                                pedidoPasado.get(i).setTotalValorPedido(Double.parseDouble(txtValor.getText().toString()));

                            }
                        }
                subtotal=0.0;

                        llenarTabla2();


                        myDialog.dismiss();

                    }
                });







                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



                myDialog.show();
            }
        });


        btnCancelarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCancelarPedido.setOnTouchListener(new View.OnTouchListener() {
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

        btnAceptarPedido.setOnTouchListener(new View.OnTouchListener() {
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



        btnAceptarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //enviarpedido




                try {










                    SQLiteDatabase db4 = conn.getWritableDatabase();


                    ContentValues values4 = new ContentValues();
                    values4.put(Utilidades.CAMPO_CONTADOR_PEDIDO, contadorPedido);
                    values4.put(Utilidades.CAMPO_OFICINA_PEDIDO, 1);
                    values4.put(Utilidades.CAMPO_TIPODOC_PEDIDO, tipodocPedido);
                    values4.put(Utilidades.CAMPO_IDENTIFICACIONCLI_PEDIDO, idClientePasado);
                    values4.put(Utilidades.CAMPO_NOMBRECLI_PEDIDO, nomClientePasado);
                    values4.put(Utilidades.CAMPO_FORMAPAGO_PEDIDO, formapagoPedido);
                    values4.put(Utilidades.CAMPO_MONEDA_PEDIDO, 1);
                    values4.put(Utilidades.CAMPO_ORIGENDOC_PEDIDO, 1);
                    values4.put(Utilidades.CAMPO_VENDEDOR_PEDIDO, vendedorPedido);
                    values4.put(Utilidades.CAMPO_BODEGA_PEDIDO, 1);
                    values4.put(Utilidades.CAMPO_LISTAPRECIO_PEDIDO, listaprecioPedido);
                    values4.put(Utilidades.CAMPO_FECHA_PEDIDO, fechaPedidoCreacion);
                    values4.put(Utilidades.CAMPO_LATITUD_PEDIDO, latitudPedido);
                    values4.put(Utilidades.CAMPO_LONGITUD_PEDIDO, longitudPedido);
                    values4.put(Utilidades.CAMPO_USUARIO_PEDIDO, vendedorPedido);
                    values4.put(Utilidades.CAMPO_OBSERVACION_PEDIDO, txtObservacion.getText().toString());
                    values4.put(Utilidades.CAMPO_SRIIVA_PEDIDO, sriPedido);
                    values4.put(Utilidades.CAMPO_SUBTOTALGLO_PEDIDO, txtSubtotal.getText().toString());
                    values4.put(Utilidades.CAMPO_DESCUENTOGLOBAL_PEDIDO, Double.parseDouble(txtDescGlobal.getText().toString()));
                    values4.put(Utilidades.CAMPO_IVAGLOB_PEDIDO, txtIva.getText().toString());
                    values4.put(Utilidades.CAMPO_VALORGLOB, txtValorTotal.getText().toString());




                    db4.insert(Utilidades.TABLA_PEDIDO, Utilidades.CAMPO_ID_TABLA_PEDIDO, values4);

                    db4.close();

                    Integer idPedidoUltimo;

                    SQLiteDatabase db3=conn.getReadableDatabase();

                    Cursor cursor=db3.rawQuery("SELECT "+Utilidades.CAMPO_ID_TABLA_PEDIDO+
                            " FROM "+Utilidades.TABLA_PEDIDO,null);



                    cursor.moveToLast();
                    idPedidoUltimo=cursor.getInt(0);



                    db3.close();




                    Log.d("idUltimoPed",idPedidoUltimo.toString());


                /*
                    try{

                        SQLiteDatabase db2=conn.getReadableDatabase();


                        try {
                            //select nombre,telefono from usuario where codigo=?
                            String sql="UPDATE "+Utilidades.TABLA_STOCK+
                                    " SET "+Utilidades.CAMPO_CANTIDAD_STOCK+" = "+Utilidades.CAMPO_CANTIDAD_STOCK+" - "+Double.parseDouble(txtCantidad.getText().toString())+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = '"+nombr2+"'";


                            Log.d("cadena",sql);

                            db2.execSQL(sql);



                        }catch (Exception e){

                            Log.d("elerrorupdate",e.toString());




                        }


                        db2.close();


                    }
                    catch (Exception ex){

                        Log.d("elerrorupdate2",ex.toString());
                    }



                    //llenarTabla();

        */






                    for (int i=0; i<pedidoPasado.size();i++){





                try{



                    SQLiteDatabase db2 = conn.getWritableDatabase();


                    ContentValues values2 = new ContentValues();
                    values2.put(Utilidades.CAMPO_PEDIDOIDDETALLE, idPedidoUltimo);
                    values2.put(Utilidades.CAMPO_DOCUMENTO_PEDIDODETALLE, 1);
                    values2.put(Utilidades.CAMPO_NOMBREPROD_PEDIDODETALLE, pedidoPasado.get(i).getNombreValorPedido());
                    values2.put(Utilidades.CAMPO_LOTE_PEDIDODETALLE,pedidoPasado.get(i).getLoteValorPedido());
                    values2.put(Utilidades.CAMPO_DESCRIPCION_PEDIDODETALLE,  pedidoPasado.get(i).getNombreValorPedido());
                    values2.put(Utilidades.CAMPO_IDENTIFICACION_PEDIDODETALLE,  idClientePasado);
                    values2.put(Utilidades.CAMPO_CANTIDADPROD_PEDIDODETALLE, pedidoPasado.get(i).getCantidadValorPedido());
                    values2.put(Utilidades.CAMPO_PRECIOPROD_PEDIDODETALLE, pedidoPasado.get(i).getPrecioValorPedido());
                    values2.put(Utilidades.CAMPO_DESCUENTOPROD_PEDIDODETALLE, Double.parseDouble(txtDescGlobal.getText().toString()));
                    values2.put(Utilidades.CAMPO_VALOR_PEDIDODETALLE, pedidoPasado.get(i).getTotalValorPedido());
                    values2.put(Utilidades.CAMPO_IVA_PEDIDODETALLE, Double.parseDouble(txtIva.getText().toString()));




                    db2.insert(Utilidades.TABLA_PEDIDO_DETALLE, Utilidades.CAMPO_ID_TABLA_PEDIDODETALLE, values2);

                    db2.close();



                    try{

                        SQLiteDatabase db6=conn.getReadableDatabase();


                        try {
                            //select nombre,telefono from usuario where codigo=?
                            String sql="UPDATE "+Utilidades.TABLA_STOCK+
                                    " SET "+Utilidades.CAMPO_CANTIDAD_STOCK+" = "+Utilidades.CAMPO_CANTIDAD_STOCK+" - "+pedidoPasado.get(i).getCantidadValorPedido()+" WHERE "+Utilidades.CAMPO_NOMBRE_STOCK+" = '"+pedidoPasado.get(i).getNombreValorPedido()+"' AND "+Utilidades.CAMPO_ID_LOTE_STOCK+" = "+pedidoPasado.get(i).getLoteValorPedido();


                            Log.d("cadena",sql);

                            db6.execSQL(sql);



                        }catch (Exception e){

                            Log.d("elerrorupdate",e.toString());




                        }


                        db6.close();


                    }
                    catch (Exception ex){

                        Log.d("elerrorupdate2",ex.toString());
                    }



                }

                catch (Exception ex){
                    Toast.makeText(getApplicationContext(), "Error al crear Detalle Pedido"+ex.toString(), Toast.LENGTH_LONG).show();



                }


                    }



                    Toast.makeText(getApplicationContext(), "Pedido Creado", Toast.LENGTH_LONG).show();

                    finish();

                }
                catch (Exception ex){
                    Toast.makeText(getApplicationContext(), "Error al crear Pedido"+ex.toString(), Toast.LENGTH_LONG).show();



                }



            }
        });



        txtDescGlobal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {



            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try{

                if(Double.parseDouble(s.toString())>subtotal){
                    Toast.makeText(getApplicationContext(), "El Descuento no puede ser mayor al Subtotal", Toast.LENGTH_LONG).show();
                    txtDescGlobal.setText("0.0");
                }
                else {

                    try {


                        iva = Double.valueOf(twoDForm.format(((subtotal - Double.parseDouble(s.toString())) * getValorCombo()) / 100));

                        txtIva.setText(iva.toString());

                        total = Double.valueOf(twoDForm.format(subtotal - Double.parseDouble(s.toString()) + iva));

                        txtValorTotal.setText(total.toString());

                    } catch (Exception ex) {

                    }
                }}
                catch (Exception ex){

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }







    //GPS

    private double getValorCombo() {
        try {
           return Double.parseDouble(comboSri.getSelectedItem().toString());
        }
        catch(Exception e) {
            return 0.0;
        }
    }


    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        startLocationUpdates();

        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if(mLocation == null){
            startLocationUpdates();
        }
        if (mLocation != null) {

            // mLatitudeTextView.setText(String.valueOf(mLocation.getLatitude()));
            //mLongitudeTextView.setText(String.valueOf(mLocation.getLongitude()));
        } else {
            Toast.makeText(this, "Location not Detected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection Suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed. Error: " + connectionResult.getErrorCode());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    protected void startLocationUpdates() {
        // Create the location request
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);
        // Request location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                mLocationRequest, this);
        Log.d("reque", "--->>>>");
    }

    @Override
    public void onLocationChanged(Location location) {

        String msg = "Updated Location: " +
                Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude());
        latitudPedido=location.getLatitude();
        longitudPedido=location.getLongitude();
        //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        // You can now create a LatLng Object for use with maps
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
    }

    private boolean checkLocation() {
        if(!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private void showAlert() {
       /* final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                    }
                });
        dialog.show();

        */

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked


                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);






                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked

                        showAlert();
                        break;
                }
            }
        };
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RegistroPedidosActivity.this);

        builder.setMessage("POR FAVOR ACTIVE EL GPS EN EL CELULAR").setPositiveButton("HERRAMIENTAS", dialogClickListener)
                .setNegativeButton("NO", dialogClickListener).setTitle("GPS").setIcon(R.drawable.icon_password).show();


    }

    private boolean isLocationEnabled() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    //Consultas

    private void consultarListaOficina() {

        SQLiteDatabase db=conn.getReadableDatabase();





        SpinOficina persona=null;

        oficinaList =new ArrayList<SpinOficina>();

        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_SPIN_OFICINA,null);

        while (cursor.moveToNext()){
            persona=new SpinOficina();
            persona.setIdSpinOficina(cursor.getInt(1));
            persona.setNombreSpinOficina(cursor.getString(2));


            Log.i("idOficina",persona.getIdSpinOficina().toString());
            Log.i("NombreOficina",persona.getNombreSpinOficina());


            oficinaList.add(persona);

        }
        obtenerListaOficina();
    }

    private void obtenerListaOficina() {

        listaOficina=new ArrayList<String>();

        listaOficina.add("Seleccione");

        for(int i=0;i<oficinaList.size();i++){
            listaOficina.add(oficinaList.get(i).getNombreSpinOficina());
        }

    }


    private void consultarListaTipoDoc() {

        SQLiteDatabase db=conn.getReadableDatabase();





        SpinTipoDocumento persona=null;

        tipodocList =new ArrayList<SpinTipoDocumento>();

        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_SPIN_TIPODOC,null);

        while (cursor.moveToNext()){
            persona=new SpinTipoDocumento();
            persona.setIdSpinTipoDocumento(cursor.getInt(1));
            persona.setNombreSpinTipoDocumento(cursor.getString(2));


           /* Log.i("idOficina",persona.getIdSpinOficina().toString());
            Log.i("NombreOficina",persona.getNombreSpinOficina());

            */


            tipodocList.add(persona);

        }
        obtenerListaTipoDoc();
    }

    private void obtenerListaTipoDoc() {

        listaTipoDoc=new ArrayList<String>();

        listaTipoDoc.add("Seleccione");

        for(int i=0;i<tipodocList.size();i++){
            listaTipoDoc.add(tipodocList.get(i).getNombreSpinTipoDocumento());
        }

    }


    private void consultarFormaPago() {

        SQLiteDatabase db=conn.getReadableDatabase();





        SpinFormaPago persona=null;

        formapagoList =new ArrayList<SpinFormaPago>();

        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_SPIN_FORMAPAGO,null);

        while (cursor.moveToNext()){
            persona=new SpinFormaPago();
            persona.setIdSpinFormaPago(cursor.getInt(1));
            persona.setNombreSpinFormaPago(cursor.getString(2));


           /* Log.i("idOficina",persona.getIdSpinOficina().toString());
            Log.i("NombreOficina",persona.getNombreSpinOficina());

            */


            formapagoList.add(persona);

        }
        obtenerListaFormaPago();
    }

    private void obtenerListaFormaPago() {

        listaFormaPago=new ArrayList<String>();

        listaFormaPago.add("Seleccione");

        for(int i=0;i<formapagoList.size();i++){
            listaFormaPago.add(formapagoList.get(i).getNombreSpinFormaPago());
        }

    }


    private void consultarMoneda() {

        SQLiteDatabase db=conn.getReadableDatabase();





        SpinMoneda persona=null;

        monedaList =new ArrayList<SpinMoneda>();

        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_SPIN_MONEDA,null);

        while (cursor.moveToNext()){
            persona=new SpinMoneda();
            persona.setIdSpinMoneda(cursor.getInt(1));
            persona.setNombreSpinMoneda(cursor.getString(2));


           /* Log.i("idOficina",persona.getIdSpinOficina().toString());
            Log.i("NombreOficina",persona.getNombreSpinOficina());

            */


            monedaList.add(persona);

        }
        obtenerListaMoneda();
    }

    private void obtenerListaMoneda() {

        listaMoneda=new ArrayList<String>();

        listaMoneda.add("Seleccione");

        for(int i=0;i<monedaList.size();i++){
            listaMoneda.add(monedaList.get(i).getNombreSpinMoneda());
        }

    }

    private void obtenerOrigenDoc(){





        String nomOrig;
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={"APP"};

        try {
            //select nombre,telefono from usuario where codigo=?
            Cursor cursor=db.rawQuery("SELECT "+Utilidades.CAMPO_ID_SPIN_ORIGENDOC+","+Utilidades.CAMPO_NOMBRE_SPIN_ORIGENDOC+
                    " FROM "+Utilidades.TABLA_SPIN_ORIGENDOC+" WHERE "+Utilidades.CAMPO_NOMBRE_SPIN_ORIGENDOC+"=? ",parametros);

            cursor.moveToFirst();
            origendocPedido=cursor.getInt(0);
            nomOrig=cursor.getString(1);



            Log.d("OrigenDocid",origendocPedido.toString());
            Log.d("OrigenDocNom",nomOrig);



        }catch (Exception e){

            Log.d("Error",e.toString());


        }
    }

    private void consultarBodega() {

        SQLiteDatabase db=conn.getReadableDatabase();





        SpinBodega persona=null;

        bodegaList =new ArrayList<SpinBodega>();

        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_SPIN_BODEGA,null);

        while (cursor.moveToNext()){
            persona=new SpinBodega();
            persona.setIdSpinBodega(cursor.getInt(1));
            persona.setNombreSpinBodega(cursor.getString(2));


           /* Log.i("idOficina",persona.getIdSpinOficina().toString());
            Log.i("NombreOficina",persona.getNombreSpinOficina());

            */


            bodegaList.add(persona);

        }
        obtenerListaBodega();
    }

    private void obtenerListaBodega() {

        listaBodega=new ArrayList<String>();

        listaBodega.add("Seleccione");

        for(int i=0;i<bodegaList.size();i++){
            listaBodega.add(bodegaList.get(i).getNombreSpinBodega());
        }

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

        listaListaPrecio.add("Seleccione");

        for(int i=0;i<listaprecioList.size();i++){
            listaListaPrecio.add(listaprecioList.get(i).getNombreSpinListaPrecio());
        }

    }


    private void consultarSri() {

        SQLiteDatabase db=conn.getReadableDatabase();





        SpinSri persona=null;

        sriList =new ArrayList<SpinSri>();

        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_SPIN_SRI,null);

        while (cursor.moveToNext()){
            persona=new SpinSri();
            persona.setIdSpinSri(cursor.getInt(1));
            persona.setNombreSpinSri(cursor.getInt(2));


           /* Log.i("idOficina",persona.getIdSpinOficina().toString());
            Log.i("NombreOficina",persona.getNombreSpinOficina());

            */


            sriList.add(persona);

        }
        obtenerListaSri();
    }

    private void obtenerListaSri() {

        listaSri=new ArrayList<String>();

        listaSri.add("Seleccione");

        for(int i=0;i<sriList.size();i++){
            listaSri.add(sriList.get(i).getNombreSpinSri().toString());
        }

    }



    private void consultarDocumento() {

        SQLiteDatabase db=conn.getReadableDatabase();





        SpinDocumento persona=null;

        documentoList =new ArrayList<SpinDocumento>();

        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_SPIN_DOCUMENTO,null);

        while (cursor.moveToNext()){
            persona=new SpinDocumento();
            persona.setIdSpinDocumento(cursor.getInt(1));
            persona.setNombreSpinDocumento(cursor.getString(2));


           /* Log.i("idOficina",persona.getIdSpinOficina().toString());
            Log.i("NombreOficina",persona.getNombreSpinOficina());

            */


            documentoList.add(persona);

        }
        obtenerListaDocumento();
    }

    private void obtenerListaDocumento() {

        listaDocumento=new ArrayList<String>();

        listaDocumento.add("Seleccione");

        for(int i=0;i<documentoList.size();i++){
            listaDocumento.add(documentoList.get(i).getNombreSpinDocumento());
        }

    }



    private void consultarLote() {

        SQLiteDatabase db=conn.getReadableDatabase();





        SpinLote persona=null;

        loteList =new ArrayList<SpinLote>();

        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_SPIN_LOTE,null);

        while (cursor.moveToNext()){
            persona=new SpinLote();
            persona.setIdSpinLote(cursor.getInt(1));
            persona.setNombreSpinLote(cursor.getString(2));


           /* Log.i("idOficina",persona.getIdSpinOficina().toString());
            Log.i("NombreOficina",persona.getNombreSpinOficina());

            */


            loteList.add(persona);

        }
        obtenerListaLote();
    }

    private void obtenerListaLote() {

        listaLote=new ArrayList<String>();

        listaLote.add("Seleccione");

        for(int i=0;i<loteList.size();i++){
            listaLote.add(loteList.get(i).getNombreSpinLote());
        }

    }


    public void llenarTabla(){


        listaProductos=new ArrayList<String>();



        if(Chifle_Dulce_Tira>0)
            listaProductos.add("CHIFLE DULCE TIRA");


        if(Chifle_Sal_Tira>0)
            listaProductos.add("CHIFLE SAL TIRA");




        if(Cuerito_Grande>0)
            listaProductos.add("CUERITO GRANDE");


        if(Cuerito_Mediano>0)
            listaProductos.add("CUERITO MEDIANO");


        if(Cuerito_Pequeno_Tira>0)
            listaProductos.add("CUERITO PEQUEÑO TIRA");


        if(Mani_Dulce_Paq_20>0)
            listaProductos.add("MANI DULCE PAQ. 20");


        if(Mani_Dulce_Paq_10>0)
            listaProductos.add("MANI DULCE PAQ. 10");


        if(Mani_Sal>0)
            listaProductos.add("MANI SAL");


        if(Mixto_Grande>0)
            listaProductos.add("MIXTO GRANDE");


        if(Mixto_Mediano>0)
            listaProductos.add("MIXTO MEDIANO");


        if(Mixto_Pequeno>0)
            listaProductos.add("MIXTO PEQUEÑO");


        if(Papa_Hoja_Gigante>0)
            listaProductos.add("PAPA HOJA GIGANTE");


        if(Papa_Hoja_Grande>0)
            listaProductos.add("PAPA HOJA GRANDE");


        if(Papa_Hoja_Mediana>0)
            listaProductos.add("PAPA HOJA MEDIANA");


        if(Papa_Pequena>0)
            listaProductos.add("PAPA PEQUEÑA");


        if(Papa_Picada_Gigante>0)
            listaProductos.add("PAPA PICADA GIGANTE");


        if(Papa_Picada_Grande>0)
            listaProductos.add("PAPA PICADA GRANDE");


        if(Papa_Picada_Mediana>0)
            listaProductos.add("PAPA PICADA MEDIANA");


        if(Papa_Picada_Pequena>0)
            listaProductos.add("PAPA PICADA PEQUEÑA");


        if(Tostado_Tiras>0)
            listaProductos.add("TOSTADO TIRAS");






    }

    public void llenarTabla2(){

        listainformacionPedidos=new ArrayList<String>();

        final DecimalFormat twoDForm = new DecimalFormat("#.##");


        for (int i=0; i<pedidoPasado.size();i++){
            listainformacionPedidos.add(pedidoPasado.get(i).getNombreValorPedido()+" |      "
                    +pedidoPasado.get(i).getLoteValorPedido()+"         | "
                    +pedidoPasado.get(i).getCantidadValorPedido()+" | $"+pedidoPasado.get(i).getPrecioValorPedido()+" |   $"+pedidoPasado.get(i).getTotalValorPedido());

            if(pedidoPasado.get(i).getTotalValorPedido()==null)
                subtotal+=0.0;
            else
            subtotal+=Double.valueOf(twoDForm.format(pedidoPasado.get(i).getTotalValorPedido()));
        }

        txtSubtotal.setText(subtotal.toString());


        iva=Double.valueOf(twoDForm.format(((subtotal-Double.parseDouble(txtDescGlobal.getText().toString()))*getValorCombo())/100));

        txtIva.setText(iva.toString());

        total=Double.valueOf(twoDForm.format(subtotal-Double.parseDouble(txtDescGlobal.getText().toString())+iva));

        txtValorTotal.setText(total.toString());



        adapta=new ArrayAdapter(this,R.layout.milistview,listainformacionPedidos);
        listViewProductos.setAdapter(adapta);
    }

}
