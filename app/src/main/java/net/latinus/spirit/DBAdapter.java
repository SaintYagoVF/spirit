package net.latinus.spirit;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import net.latinus.spirit.Entidades.Stock;
import net.latinus.spirit.Utilidades.Utilidades;

import java.util.ArrayList;

public class DBAdapter {



    Context c;
    SQLiteDatabase db;
    ConexionSQLiteHelper helper;

    public DBAdapter(Context c) {
        this.c = c;
        helper = new ConexionSQLiteHelper(c);
    }

    /*
     1. RETRIEVE SPACECRAFTS FROM DB AND POPULATE ARRAYLIST
     2. RETURN THE LIST
     */

    public ArrayList<Stock> retrieveSpacecrafts()
    {
        ArrayList<Stock> spacecrafts=new ArrayList<>();

        String[] columns={Utilidades.CAMPO_NOMBRE_STOCK,Utilidades.CAMPO_ID_LOTE_STOCK,Utilidades.CAMPO_CANTIDAD_STOCK};

        try
        {
            db = helper.getWritableDatabase();
            Cursor c=db.query(Utilidades.TABLA_STOCK,columns,null,null,null,null,null);

            Stock s;

            if(c != null)
            {
                while (c.moveToNext())
                {


                    String s_lote=c.getString(1);


                    String s_cantidad=c.getString(2);




                    String s_nombre=c.getString(0);





                    s=new Stock();

                    s.setIdLoteStock(Integer.parseInt(s_lote));


                    s.setNombreStock(s_nombre);




                    s.setCantidadStock(Double.parseDouble(s_cantidad));




                    spacecrafts.add(s);
                }
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }


        return spacecrafts;
    }
}
