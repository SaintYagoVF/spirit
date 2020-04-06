package net.latinus.spirit;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import net.latinus.spirit.Entidades.SpinCiudad;
import net.latinus.spirit.Entidades.SpinCorporacion;
import net.latinus.spirit.Entidades.SpinTipoProv;
import net.latinus.spirit.Utilidades.Utilidades;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RegistrarClienteActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener{

    Button btnCancelarReg;

    Button btnAceptarReg;

    Spinner comboCorporacion;

    Spinner comboCiudad;

    Spinner comboTipoProveedor;

    Spinner comboTipoIdentificacion;

    Spinner comboTipoCliente;

    ArrayList<String> listaCorporacion;
    ArrayList<SpinCorporacion> corporacionList;

    ArrayList<String> listaCiudad;
    ArrayList<SpinCiudad> ciudadList;

    ArrayList<String> listaTipoProveedor;
    ArrayList<SpinTipoProv> tipoproveedorList;


    ConexionSQLiteHelper conn;


    private ProgressDialog pDialog;


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


    //VARIABLES SQLITE

    int tipoidRegCliente=1;

     String identificacionRegCliente;

     String nombreRegCliente;

    String razonRegCliente;

 int corporacionidRegCliente;

    int tipoclienteidRegCliente=1;

     int tipoproveedoridRegCliente;

     String codigoRegCliente;

     int ciudadidRegCliente;

     String direccionRegCliente;

     double latitudRegCliente;

     double longitudRegCliente;


     String emailRegCliente;

    String descripcionRegCliente;

     Integer vendedoridRegCliente;


     TextView identRegCliente;

     TextView nomRegCliente;

     TextView razRegCliente;

     TextView codRgCliente;

     TextView dirRegCLiente;

     TextView emailRegCLien;

     TextView descrRegClien;

    String valorPasado;
    String nombrePasado;
    String apellidoPasado;
    String emailPasado;
    TextView nombrePas;
    TextView apellPas;
    TextView correoPas;

    Integer idPasado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        Bundle bundle = this.getIntent().getExtras();

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

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

            vendedoridRegCliente = strdata4;

            Log.d("Valor Identif ",valorPasado);
            Log.d("Valor nombres ",nombrePasado);
            Log.d("Valor apellidos ",apellidoPasado);
            Log.d("Valor correo ",emailPasado);

            Log.d("Valor id ",vendedoridRegCliente.toString());

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



        btnCancelarReg=(Button)findViewById(R.id.btnRegCliCancelar);

        btnAceptarReg=(Button)findViewById(R.id.btnRegCliAceptar);

        identRegCliente = (TextView)findViewById(R.id.simplT22);

        nomRegCliente = (TextView)findViewById(R.id.simplT32);
        razRegCliente = (TextView)findViewById(R.id.simplT42);
        codRgCliente = (TextView)findViewById(R.id.simplT82);
        dirRegCLiente = (TextView)findViewById(R.id.simplT102);
        emailRegCLien = (TextView)findViewById(R.id.simplT132);
        descrRegClien = (TextView)findViewById(R.id.simplT142);


        btnCancelarReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAceptarReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


             try {
                 pDialog.setMessage("Por favor, espere....");
                 pDialog.setTitle("Sincronizando");

                 // pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                 pDialog.setCancelable(false);
                 showDialog();

                 identificacionRegCliente = identRegCliente.getText().toString();

                 nombreRegCliente = nomRegCliente.getText().toString();

                 razonRegCliente = razRegCliente.getText().toString();

                 codigoRegCliente = codRgCliente.getText().toString();

                 direccionRegCliente = dirRegCLiente.getText().toString();

                 latitudRegCliente = Double.parseDouble(mLatitudeTextView.getText().toString());

                 longitudRegCliente = Double.parseDouble(mLongitudeTextView.getText().toString());

                 emailRegCliente = emailRegCLien.getText().toString();

                 descripcionRegCliente = descrRegClien.getText().toString();


                 SQLiteDatabase db = conn.getWritableDatabase();


                 ContentValues values = new ContentValues();
                 values.put(Utilidades.CAMPO_TIPOIDENTIF_REG_CLIENTE, tipoidRegCliente);
                 values.put(Utilidades.CAMPO_IDENTIFICACION_REG_CLIENTE, identificacionRegCliente);
                 values.put(Utilidades.CAMPO_NOMBRE_REG_CLIENTE, nombreRegCliente);
                 values.put(Utilidades.CAMPO_RAZON_REG_CLIENTE, razonRegCliente);
                 values.put(Utilidades.CAMPO_ID_CORP_REG_CLIENTE, corporacionidRegCliente);
                 values.put(Utilidades.CAMPO_ID_TIPOCLIENTE_REG_CLIENTE, tipoclienteidRegCliente);
                 values.put(Utilidades.CAMPO_ID_TIPOPROVEED_REG_CLIENTE, tipoproveedoridRegCliente);
                 values.put(Utilidades.CAMPO_CODIGO_REG_CLIENTE, codigoRegCliente);
                 values.put(Utilidades.CAMPO_ID_CIUDAD_REG_CLIENTE, ciudadidRegCliente);
                 values.put(Utilidades.CAMPO_DIRECCION_REG_CLIENTE, direccionRegCliente);
                 values.put(Utilidades.CAMPO_LATITUD_REG_CLIENTE, latitudRegCliente);
                 values.put(Utilidades.CAMPO_LONGITUD_REG_CLIENTE, longitudRegCliente);
                 values.put(Utilidades.CAMPO_EMAIL_REG_CLIENTE, emailRegCliente);
                 values.put(Utilidades.CAMPO_DESCRIPCION_REG_CLIENTE, descripcionRegCliente);
                 values.put(Utilidades.CAMPO_VENDEDOR_REG_CLIENTE, vendedoridRegCliente);



                 db.insert(Utilidades.TABLA_REG_CLIENTE, Utilidades.CAMPO_ID_TABLA_REG_CLIENTE, values);

                 db.close();
                 Toast.makeText(getApplicationContext(), "Cliente Creado", Toast.LENGTH_LONG).show();

                 finish();

             }
             catch (Exception ex){
                 Toast.makeText(getApplicationContext(), "Error al crear Cliente", Toast.LENGTH_LONG).show();



             }
            }
        });

        btnCancelarReg.setOnTouchListener(new View.OnTouchListener() {
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

        btnAceptarReg.setOnTouchListener(new View.OnTouchListener() {
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

        consultarListaCorporacion();
        consultarListaTipoProveedor();

        consultarListaCiudad();




        comboCorporacion= (Spinner) findViewById(R.id.spinCorporacion);



        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaCorporacion);

        comboCorporacion.setAdapter(adaptador);

        comboCorporacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                if (position!=0){
                   /* txtDocumento.setText(personasList.get(position-1).getId().toString());
                    txtNombre.setText(personasList.get(position-1).getNombre());
                    txtTelefono.setText(personasList.get(position-1).getClave());

                    */

                   corporacionidRegCliente = corporacionList.get(position-1).getIdSpinCorp();


                }else{
                    corporacionidRegCliente = 0;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                corporacionidRegCliente = 0;
            }
        });


        comboCiudad= (Spinner) findViewById(R.id.spinCiudad);





        ArrayAdapter<CharSequence> adaptador2=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaCiudad);

        comboCiudad.setAdapter(adaptador2);

        comboCiudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                if (position!=0){
                   /* txtDocumento.setText(personasList.get(position-1).getId().toString());
                    txtNombre.setText(personasList.get(position-1).getNombre());
                    txtTelefono.setText(personasList.get(position-1).getClave());

                    */

                    ciudadidRegCliente = ciudadList.get(position-1).getIdSpinCiudad();


                }else{

                    ciudadidRegCliente = 0;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                ciudadidRegCliente = 0;
            }
        });



        comboTipoProveedor= (Spinner) findViewById(R.id.spinTipoProveedor);




        ArrayAdapter<CharSequence> adaptador3=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaTipoProveedor);

        comboTipoProveedor.setAdapter(adaptador3);

        comboTipoProveedor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                if (position!=0){
                   /* txtDocumento.setText(personasList.get(position-1).getId().toString());
                    txtNombre.setText(personasList.get(position-1).getNombre());
                    txtTelefono.setText(personasList.get(position-1).getClave());

                    */

                   tipoproveedoridRegCliente = tipoproveedorList.get(position-1).getIdSpinTipoProv();


                }else{

                    tipoproveedoridRegCliente = 0;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                tipoproveedoridRegCliente = 0;

            }
        });


        comboTipoCliente= (Spinner) findViewById(R.id.spinTipoCliente);

        comboTipoCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                switch (position){

                    case 0:

                        tipoclienteidRegCliente = 1;

                        break;

                    case 1:

                        tipoclienteidRegCliente = 2;
                        break;

                    case 2:

                        tipoclienteidRegCliente = 3;

                        break;





                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                tipoclienteidRegCliente = 1;

            }
        });


        comboTipoIdentificacion= (Spinner) findViewById(R.id.spinTipoIdent);

        comboTipoIdentificacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                switch (position){

                    case 0:

                        tipoidRegCliente = 1;

                        break;

                    case 1:

                        tipoidRegCliente = 2;
                        break;

                    case 2:

                        tipoidRegCliente = 3;

                        break;

                    case 3:

                        tipoidRegCliente = 4;

                        break;


                    case 4:

                        tipoidRegCliente = 40;

                        break;






                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                tipoidRegCliente = 1;

            }
        });


        //GPS


        mLatitudeTextView = (TextView) findViewById((R.id.latitude_textview));
        mLongitudeTextView = (TextView) findViewById((R.id.longitude_textview));

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);

        checkLocation(); //check whether location service is enable or not in your  phone




    }


    private void consultarListaCorporacion() {

        SQLiteDatabase db=conn.getReadableDatabase();

        SpinCorporacion persona=null;
        corporacionList =new ArrayList<SpinCorporacion>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_SPIN_CORPORACION,null);

        while (cursor.moveToNext()){
            persona=new SpinCorporacion();
            persona.setIdSpinCorp(cursor.getInt(1));
            persona.setNombreSpinCorp(cursor.getString(2));


            Log.i("idCorpora",persona.getIdSpinCorp().toString());
            Log.i("NombreCorpora",persona.getNombreSpinCorp());


            corporacionList.add(persona);

        }
        obtenerListaCorporacion();
    }

    private void obtenerListaCorporacion() {
        listaCorporacion=new ArrayList<String>();
        listaCorporacion.add("Seleccione");

        for(int i=0;i<corporacionList.size();i++){
           listaCorporacion.add(corporacionList.get(i).getNombreSpinCorp());
        }

    }



    private void consultarListaCiudad() {

        SQLiteDatabase db=conn.getReadableDatabase();

        SpinCiudad persona=null;
        ciudadList =new ArrayList<SpinCiudad>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_SPIN_CIUDAD,null);

        while (cursor.moveToNext()){
            persona=new SpinCiudad();
            persona.setIdSpinCiudad(cursor.getInt(1));
            persona.setNombreSpinCiudad(cursor.getString(2));


            Log.i("idCorp",persona.getIdSpinCiudad().toString());
            Log.i("NombreCorp",persona.getNombreSpinCiudad());


            ciudadList.add(persona);

        }
        obtenerListaCiudad();
    }

    private void obtenerListaCiudad() {
        listaCiudad=new ArrayList<String>();
        listaCiudad.add("Seleccione");

        for(int i=0;i<ciudadList.size();i++){
            listaCiudad.add(ciudadList.get(i).getNombreSpinCiudad());
        }

    }



    private void consultarListaTipoProveedor() {

        SQLiteDatabase db=conn.getReadableDatabase();

        SpinTipoProv persona=null;
        tipoproveedorList =new ArrayList<SpinTipoProv>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_SPIN_TIPOPROV,null);

        while (cursor.moveToNext()){
            persona=new SpinTipoProv();
            persona.setIdSpinTipoProv(cursor.getInt(1));
            persona.setNombreSpinTipoProv(cursor.getString(2));


            Log.i("idCorp",persona.getIdSpinTipoProv().toString());
            Log.i("NombreCorp",persona.getNombreSpinTipoProv());


            tipoproveedorList.add(persona);

        }
        obtenerListaTipoProveedor();
    }

    private void obtenerListaTipoProveedor() {
        listaTipoProveedor=new ArrayList<String>();
        listaTipoProveedor.add("Seleccione");

        for(int i=0;i<tipoproveedorList.size();i++){
            listaTipoProveedor.add(tipoproveedorList.get(i).getNombreSpinTipoProv());
        }

    }



    //GPS




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
        mLatitudeTextView.setText(String.valueOf(location.getLatitude()));
        mLongitudeTextView.setText(String.valueOf(location.getLongitude() ));
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
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RegistrarClienteActivity.this);

        builder.setMessage("POR FAVOR ACTIVE EL GPS EN EL CELULAR").setPositiveButton("HERRAMIENTAS", dialogClickListener)
                .setNegativeButton("NO", dialogClickListener).setTitle("GPS").setIcon(R.drawable.icon_password).show();


    }

    private boolean isLocationEnabled() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
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
