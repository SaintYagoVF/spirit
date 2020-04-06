package net.latinus.spirit;

import android.content.Context;

import net.latinus.spirit.Entidades.Stock;

import java.util.ArrayList;

public class TableHelper {





    //DECLARATIONS
    Context c;
    private String[] spaceProbeHeaders={"Producto","Lote","Cantidad"};
    private String[][] spaceProbes;

    //CONSTRUCTOR
    public TableHelper(Context c) {
        this.c = c;
    }

    //RETURN TABLE HEADERS
    public String[] getSpaceProbeHeaders()
    {
        return spaceProbeHeaders;
    }

    //RETURN TABLE ROWS
    public  String[][] getSpaceProbes()
    {
        ArrayList<Stock> spacecrafts=new DBAdapter(c).retrieveSpacecrafts();
        Stock s;

        spaceProbes= new String[spacecrafts.size()][3];

        for (int i=0;i<spacecrafts.size();i++) {

            s=spacecrafts.get(i);



            spaceProbes[i][0]=s.getNombreStock();

            spaceProbes[i][1]=s.getIdLoteStock().toString();

            spaceProbes[i][2]=s.getCantidadStock().toString();







        }

        return spaceProbes;





    }
}
