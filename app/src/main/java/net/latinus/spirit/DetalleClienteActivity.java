package net.latinus.spirit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import net.latinus.spirit.Entidades.Cliente;

public class DetalleClienteActivity extends AppCompatActivity {

    TextView idCliente,identificacionCliente,nombreCliente,razonCliente,fechaCliente,latitudCliente,longitudCliente,direccionCliente,telefonoCliente,emailCliente,DescripcionCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cliente);


        idCliente=(TextView)findViewById(R.id.simpleT12);
        identificacionCliente=(TextView)findViewById(R.id.simpleT22);
        nombreCliente=(TextView)findViewById(R.id.simpleT32);
        razonCliente=(TextView)findViewById(R.id.simpleT42);
        fechaCliente=(TextView)findViewById(R.id.simpleT52);
        latitudCliente=(TextView)findViewById(R.id.simpleT62);
        longitudCliente=(TextView)findViewById(R.id.simpleT72);
        direccionCliente=(TextView)findViewById(R.id.simpleT82);
        telefonoCliente=(TextView)findViewById(R.id.simpleT92);
        emailCliente=(TextView)findViewById(R.id.simpleT102);
        DescripcionCliente=(TextView)findViewById(R.id.simpleT112);


        Bundle objetoEnviado=getIntent().getExtras();
        Cliente user=null;

        if(objetoEnviado!=null){
            user= (Cliente) objetoEnviado.getSerializable("cliente");
            idCliente.setText(user.getIdCliente().toString());
            identificacionCliente.setText(user.getIdentificacionCliente());
            nombreCliente.setText(user.getNombreCliente());
            razonCliente.setText(user.getRazonCliente());
            fechaCliente.setText(user.getFechaCliente());
            latitudCliente.setText(user.getLatitudCliente().toString());
            longitudCliente.setText(user.getLongitudCliente().toString());
            direccionCliente.setText(user.getDireccionCliente());
            telefonoCliente.setText(user.getTelefonoCliente());
            emailCliente.setText(user.getEmailCliente());
            DescripcionCliente.setText(user.getDescripcionCliente());



        }

    }
}
