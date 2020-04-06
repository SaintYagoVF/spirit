package net.latinus.spirit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.latinus.spirit.Utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(Context context){
        super(context, "bd_usuarios", null, 1);
    }

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_EMPLEADO);
        db.execSQL(Utilidades.CREAR_TABLA_STOCK);
        db.execSQL(Utilidades.CREAR_TABLA_CLIENTE);
        db.execSQL(Utilidades.CREAR_TABLA_REG_CLIENTE);
        db.execSQL(Utilidades.CREAR_TABLA_SPIN_CORP);
        db.execSQL(Utilidades.CREAR_TABLA_SPIN_CIUDAD);
        db.execSQL(Utilidades.CREAR_TABLA_SPIN_TIPOPROV);
        db.execSQL(Utilidades.CREAR_TABLA_SPIN_OFICINA);
        db.execSQL(Utilidades.CREAR_TABLA_SPIN_TIPODOC);
        db.execSQL(Utilidades.CREAR_TABLA_SPIN_FORMAPAGO);
        db.execSQL(Utilidades.CREAR_TABLA_SPIN_MONEDA);
        db.execSQL(Utilidades.CREAR_TABLA_SPIN_ORIGENDOC);
        db.execSQL(Utilidades.CREAR_TABLA_SPIN_BODEGA);
        db.execSQL(Utilidades.CREAR_TABLA_SPIN_LISTAPRECIO);
        db.execSQL(Utilidades.CREAR_TABLA_SPIN_SRI);
        db.execSQL(Utilidades.CREAR_TABLA_SPIN_DOCUMENTO);
        db.execSQL(Utilidades.CREAR_TABLA_PRECIO);
        db.execSQL(Utilidades.CREAR_TABLA_PEDIDO);
        db.execSQL(Utilidades.CREAR_TABLA_PEDIDODETALLE);
        db.execSQL(Utilidades.CREAR_TABLA_SPIN_LOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_EMPLEADO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_STOCK);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_CLIENTE);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_REG_CLIENTE);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_SPIN_CORPORACION);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_SPIN_CIUDAD);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_SPIN_TIPOPROV);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_SPIN_OFICINA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_SPIN_TIPODOC);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_SPIN_FORMAPAGO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_SPIN_MONEDA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_SPIN_ORIGENDOC);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_SPIN_BODEGA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_SPIN_LISTAPRECIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_SPIN_SRI);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_SPIN_DOCUMENTO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_PRECIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_PEDIDO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_PEDIDO_DETALLE);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_SPIN_LOTE);


        onCreate(db);
    }
}
