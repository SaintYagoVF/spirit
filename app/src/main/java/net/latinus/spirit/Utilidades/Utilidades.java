package net.latinus.spirit.Utilidades;

public class Utilidades {


    //Constantes campos tabla empleado
    public static final String TABLA_EMPLEADO="empleado";
    public static final String CAMPO_ID_TABLA_EMPLEADO="idtab_empleado";
    public static final String CAMPO_ID_EMPLEADO="id_empleado";
    public static final String CAMPO_CODIGO_EMPLEADO="codigo_empleado";
    public static final String CAMPO_NOMBRES_EMPLEADO="nombres_empleado";
    public static final String CAMPO_APELLIDOS_EMPLEADO="apellidos_empleado";
    public static final String CAMPO_IDENTIFICACION_EMPLEADO="identificacion_empleado";
    public static final String CAMPO_PROFESION_EMPLEADO="profesion_empleado";
    public static final String CAMPO_DOMICILIO_EMPLEADO="domicilio_empleado";
    public static final String CAMPO_TELFDOMICILIO_EMPLEADO="telfdomicilio_empleado";
    public static final String CAMPO_CELULAR_EMPLEADO="celular_empleado";
    public static final String CAMPO_EMAIL_EMPLEADO="email_empleado";
    public static final String CAMPO_EXTOFICINA_EMPLEADO="extoficina_empleado";
    public static final String CAMPO_NIVEL_EMPLEADO="nivel_empleado";
    public static final String CAMPO_ESTADO_EMPLEADO="estado_empleado";
    public static final String CAMPO_IDOFICINA_EMPLEADO="idoficina_empleado";
    public static final String CAMPO_IDBANCO_EMPLEADO="idbanco_empleado";
    public static final String CAMPO_TIPOCUENTA_EMPLEADO="tipocuenta_empleado";
    public static final String CAMPO_NUMCUENTA_EMPLEADO="numcuenta_empleado";
    public static final String CAMPO_ENVIARCORREO_EMPLEADO="enviarcorreo_empleado";
    public static final String CAMPO_USUARIO_EMPLEADO="usuario_empleado";
    public static final String CAMPO_CLAVE_EMPLEADO="clave_empleado";

    public static final String CREAR_TABLA_EMPLEADO="CREATE TABLE " +
            ""+TABLA_EMPLEADO+" ("+CAMPO_ID_TABLA_EMPLEADO+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_ID_EMPLEADO+" INTEGER, "+CAMPO_CODIGO_EMPLEADO+" TEXT,"+CAMPO_NOMBRES_EMPLEADO+" TEXT,"+CAMPO_APELLIDOS_EMPLEADO+" TEXT,"+CAMPO_IDENTIFICACION_EMPLEADO+" TEXT,"+CAMPO_PROFESION_EMPLEADO+" TEXT,"+CAMPO_DOMICILIO_EMPLEADO+" TEXT,"+CAMPO_TELFDOMICILIO_EMPLEADO+" TEXT,"+CAMPO_CELULAR_EMPLEADO+" TEXT,"+CAMPO_EMAIL_EMPLEADO+" TEXT,"+CAMPO_EXTOFICINA_EMPLEADO+" TEXT,"+CAMPO_NIVEL_EMPLEADO+" INTEGER,"+CAMPO_ESTADO_EMPLEADO+" TEXT,"+CAMPO_IDOFICINA_EMPLEADO+" INTEGER,"+CAMPO_IDBANCO_EMPLEADO+" INTEGER,"+CAMPO_TIPOCUENTA_EMPLEADO+" TEXT,"+CAMPO_NUMCUENTA_EMPLEADO+" TEXT,"+CAMPO_ENVIARCORREO_EMPLEADO+" TEXT,"+CAMPO_USUARIO_EMPLEADO+" TEXT,"+CAMPO_CLAVE_EMPLEADO+" TEXT)";


    //Constantes campos tabla Stock
    public static final String TABLA_STOCK="stock";
    public static final String CAMPO_ID_TABLA_STOCK="idtab_stock";
    public static final String CAMPO_ID_STOCK="id_stock";
    public static final String CAMPO_ID_BODEGA_STOCK="id_bodega_stock";
    public static final String CAMPO_ID_PRODUCTO_STOCK="id_prod_stock";
    public static final String CAMPO_ID_LOTE_STOCK="id_lote_stock";
    public static final String CAMPO_ANIO_STOCK="anio_stock";
    public static final String CAMPO_MES_STOCK="mes_stock";
    public static final String CAMPO_CANTIDAD_STOCK="cantidad_stock";
    public static final String CAMPO_RESERVA_STOCK="reserva_stock";
    public static final String CAMPO_TRANSITO_STOCK="transito_stock";
    public static final String CAMPO_FECHA_STOCK="fecha_stock";
    public static final String CAMPO_NOMBRE_STOCK="nombre_stock";

    public static final String CREAR_TABLA_STOCK="CREATE TABLE " +
            ""+TABLA_STOCK+" ("+CAMPO_ID_TABLA_STOCK+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_ID_STOCK+" INTEGER, "+CAMPO_ID_BODEGA_STOCK+" INTEGER,"+CAMPO_ID_PRODUCTO_STOCK+" INTEGER,"+CAMPO_ID_LOTE_STOCK+" INTEGER,"+CAMPO_ANIO_STOCK+" TEXT,"+CAMPO_MES_STOCK+" TEXT,"+CAMPO_CANTIDAD_STOCK+" REAL,"+CAMPO_RESERVA_STOCK+" REAL,"+CAMPO_TRANSITO_STOCK+" REAL,"+CAMPO_FECHA_STOCK+" TEXT,"+CAMPO_NOMBRE_STOCK+" TEXT)";



    //Constantes campos tabla Cliente
    public static final String TABLA_CLIENTE="cliente";
    public static final String CAMPO_ID_TABLA_CLIENTE="idtab_cliente";
    public static final String CAMPO_ID_CLIENTE="id_cliente";
    public static final String CAMPO_IDENTIFICACION_CLIENTE="identificacion_cliente";
    public static final String CAMPO_NOMBRE_CLIENTE="nombre_cliente";
    public static final String CAMPO_RAZON_CLIENTE="razon_cliente";
    public static final String CAMPO_FECHA_CLIENTE="fecha_cliente";
    public static final String CAMPO_LATITUD_CLIENTE="latitud_cliente";
    public static final String CAMPO_LONGITUD_CLIENTE="longitud_cliente";
    public static final String CAMPO_DIRECCION_CLIENTE="direccion_cliente";
    public static final String CAMPO_TELEFONO_CLIENTE="telefono_cliente";
    public static final String CAMPO_EMAIL_CLIENTE="email_cliente";
    public static final String CAMPO_DESCRIPCION_CLIENTE="descripcion_cliente";




    public static final String CREAR_TABLA_CLIENTE="CREATE TABLE " +
            ""+TABLA_CLIENTE+" ("+CAMPO_ID_TABLA_CLIENTE+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_ID_CLIENTE+" INTEGER, "+CAMPO_IDENTIFICACION_CLIENTE+" TEXT,"+CAMPO_NOMBRE_CLIENTE+" TEXT,"+CAMPO_RAZON_CLIENTE+" TEXT,"+CAMPO_FECHA_CLIENTE+" TEXT,"+CAMPO_LATITUD_CLIENTE+" REAL,"+CAMPO_LONGITUD_CLIENTE+" REAL,"+CAMPO_DIRECCION_CLIENTE+" TEXT,"+CAMPO_TELEFONO_CLIENTE+" TEXT,"+CAMPO_EMAIL_CLIENTE+" TEXT,"+CAMPO_DESCRIPCION_CLIENTE+" TEXT)";


    //Constantes campos tabla Registro Cliente
    public static final String TABLA_REG_CLIENTE="registro_cliente";
    public static final String CAMPO_ID_TABLA_REG_CLIENTE="idtab_reg_cliente";
    public static final String CAMPO_TIPOIDENTIF_REG_CLIENTE="tipoid_reg_cliente";
    public static final String CAMPO_IDENTIFICACION_REG_CLIENTE="identificacion_reg_cliente";
    public static final String CAMPO_NOMBRE_REG_CLIENTE="nombre_reg_cliente";
    public static final String CAMPO_RAZON_REG_CLIENTE="razon_reg_cliente";
    public static final String CAMPO_ID_CORP_REG_CLIENTE="corpid_reg_cliente";
    public static final String CAMPO_ID_TIPOCLIENTE_REG_CLIENTE="tipoclienteid_reg_cliente";
    public static final String CAMPO_ID_TIPOPROVEED_REG_CLIENTE="tipoproveedid_reg_cliente";
    public static final String CAMPO_CODIGO_REG_CLIENTE="codigo_reg_cliente";
    public static final String CAMPO_ID_CIUDAD_REG_CLIENTE="ciudadid_reg_cliente";
    public static final String CAMPO_DIRECCION_REG_CLIENTE="direccion_reg_cliente";
    public static final String CAMPO_LATITUD_REG_CLIENTE="latitud_reg_cliente";
    public static final String CAMPO_LONGITUD_REG_CLIENTE="longitud_reg_cliente";
    public static final String CAMPO_EMAIL_REG_CLIENTE="email_reg_cliente";
    public static final String CAMPO_DESCRIPCION_REG_CLIENTE="desc_reg_cliente";
    public static final String CAMPO_VENDEDOR_REG_CLIENTE="vendedor_reg_cliente";




    public static final String CREAR_TABLA_REG_CLIENTE="CREATE TABLE " +
            ""+TABLA_REG_CLIENTE+" ("+CAMPO_ID_TABLA_REG_CLIENTE+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_TIPOIDENTIF_REG_CLIENTE+" INTEGER, "+CAMPO_IDENTIFICACION_REG_CLIENTE+" TEXT,"+CAMPO_NOMBRE_REG_CLIENTE+" TEXT,"+CAMPO_RAZON_REG_CLIENTE+" TEXT,"+CAMPO_ID_CORP_REG_CLIENTE+" INTEGER,"+CAMPO_ID_TIPOCLIENTE_REG_CLIENTE+" INTEGER,"+CAMPO_ID_TIPOPROVEED_REG_CLIENTE+" INTEGER,"+CAMPO_CODIGO_REG_CLIENTE+" TEXT,"+CAMPO_ID_CIUDAD_REG_CLIENTE+" INTEGER,"+CAMPO_DIRECCION_REG_CLIENTE+" TEXT,"+CAMPO_LATITUD_REG_CLIENTE+" REAL,"+CAMPO_LONGITUD_REG_CLIENTE+" REAL,"+CAMPO_EMAIL_REG_CLIENTE+" TEXT,"+CAMPO_DESCRIPCION_REG_CLIENTE+" TEXT,"+CAMPO_VENDEDOR_REG_CLIENTE+" TEXT)";



    //Constantes campos tabla Spin Corporacion
    public static final String TABLA_SPIN_CORPORACION="spin_corporacion";
    public static final String CAMPO_ID_TABLA_SPIN_CORP="idtab_spin_corp";
    public static final String CAMPO_ID_SPIN_CORP="id_spin_corp";
    public static final String CAMPO_NOMBRE_SPIN_CORP="nombre_spin_corp";




    public static final String CREAR_TABLA_SPIN_CORP="CREATE TABLE " +
            ""+TABLA_SPIN_CORPORACION+" ("+CAMPO_ID_TABLA_SPIN_CORP+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_ID_SPIN_CORP+" INTEGER, "+CAMPO_NOMBRE_SPIN_CORP+" TEXT)";



    //Constantes campos tabla Spin Ciudad
    public static final String TABLA_SPIN_CIUDAD="spin_ciudad";
    public static final String CAMPO_ID_TABLA_SPIN_CIUDAD="idtab_spin_ciudad";
    public static final String CAMPO_ID_SPIN_CIUDAD="id_spin_ciudad";
    public static final String CAMPO_NOMBRE_SPIN_CIUDAD="nombre_spin_ciudad";




    public static final String CREAR_TABLA_SPIN_CIUDAD="CREATE TABLE " +
            ""+TABLA_SPIN_CIUDAD+" ("+CAMPO_ID_TABLA_SPIN_CIUDAD+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_ID_SPIN_CIUDAD+" INTEGER, "+CAMPO_NOMBRE_SPIN_CIUDAD+" TEXT)";



    //Constantes campos tabla Spin Tipo Proveedor
    public static final String TABLA_SPIN_TIPOPROV="spin_tipoprov";
    public static final String CAMPO_ID_TABLA_SPIN_TIPOPROV="idtab_spin_tipoprov";
    public static final String CAMPO_ID_SPIN_TIPOPROV="id_spin_tipoprov";
    public static final String CAMPO_NOMBRE_SPIN_TIPOPROV="nombre_spin_tipoprov";




    public static final String CREAR_TABLA_SPIN_TIPOPROV="CREATE TABLE " +
            ""+TABLA_SPIN_TIPOPROV+" ("+CAMPO_ID_TABLA_SPIN_TIPOPROV+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_ID_SPIN_TIPOPROV+" INTEGER, "+CAMPO_NOMBRE_SPIN_TIPOPROV+" TEXT)";




    //Constantes campos tabla Spin Oficina
    public static final String TABLA_SPIN_OFICINA="spin_oficina";
    public static final String CAMPO_ID_TABLA_SPIN_OFICINA="idtab_spin_oficina";
    public static final String CAMPO_ID_SPIN_OFICINA="id_spin_oficina";
    public static final String CAMPO_NOMBRE_SPIN_OFICINA="nombre_spin_oficina";




    public static final String CREAR_TABLA_SPIN_OFICINA="CREATE TABLE " +
            ""+TABLA_SPIN_OFICINA+" ("+CAMPO_ID_TABLA_SPIN_OFICINA+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_ID_SPIN_OFICINA+" INTEGER, "+CAMPO_NOMBRE_SPIN_OFICINA+" TEXT)";


    //Constantes campos tabla Spin Tipo Documento
    public static final String TABLA_SPIN_TIPODOC="spin_tipodoc";
    public static final String CAMPO_ID_TABLA_SPIN_TIPODOC="idtab_spin_tipodoc";
    public static final String CAMPO_ID_SPIN_TIPODOC="id_spin_tipodoc";
    public static final String CAMPO_NOMBRE_SPIN_TIPODOC="nombre_spin_tipodoc";




    public static final String CREAR_TABLA_SPIN_TIPODOC="CREATE TABLE " +
            ""+TABLA_SPIN_TIPODOC+" ("+CAMPO_ID_TABLA_SPIN_TIPODOC+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_ID_SPIN_TIPODOC+" INTEGER, "+CAMPO_NOMBRE_SPIN_TIPODOC+" TEXT)";



    //Constantes campos tabla Spin Forma Pago
    public static final String TABLA_SPIN_FORMAPAGO="spin_formapago";
    public static final String CAMPO_ID_TABLA_SPIN_FORMAPAGO="idtab_spin_formapago";
    public static final String CAMPO_ID_SPIN_FORMAPAGO="id_spin_formapago";
    public static final String CAMPO_NOMBRE_SPIN_FORMAPAGO="nombre_spin_formapago";




    public static final String CREAR_TABLA_SPIN_FORMAPAGO="CREATE TABLE " +
            ""+TABLA_SPIN_FORMAPAGO+" ("+CAMPO_ID_TABLA_SPIN_FORMAPAGO+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_ID_SPIN_FORMAPAGO+" INTEGER, "+CAMPO_NOMBRE_SPIN_FORMAPAGO+" TEXT)";



    //Constantes campos tabla Spin Moneda
    public static final String TABLA_SPIN_MONEDA="spin_moneda";
    public static final String CAMPO_ID_TABLA_SPIN_MONEDA="idtab_spin_moneda";
    public static final String CAMPO_ID_SPIN_MONEDA="id_spin_moneda";
    public static final String CAMPO_NOMBRE_SPIN_MONEDA="nombre_spin_moneda";




    public static final String CREAR_TABLA_SPIN_MONEDA="CREATE TABLE " +
            ""+TABLA_SPIN_MONEDA+" ("+CAMPO_ID_TABLA_SPIN_MONEDA+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_ID_SPIN_MONEDA+" INTEGER, "+CAMPO_NOMBRE_SPIN_MONEDA+" TEXT)";



    //Constantes campos tabla Origen Documento
    public static final String TABLA_SPIN_ORIGENDOC="spin_origendoc";
    public static final String CAMPO_ID_TABLA_SPIN_ORIGENDOC="idtab_spin_origendoc";
    public static final String CAMPO_ID_SPIN_ORIGENDOC="id_spin_origendoc";
    public static final String CAMPO_NOMBRE_SPIN_ORIGENDOC="nombre_spin_origendoc";




    public static final String CREAR_TABLA_SPIN_ORIGENDOC="CREATE TABLE " +
            ""+TABLA_SPIN_ORIGENDOC+" ("+CAMPO_ID_TABLA_SPIN_ORIGENDOC+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_ID_SPIN_ORIGENDOC+" INTEGER, "+CAMPO_NOMBRE_SPIN_ORIGENDOC+" TEXT)";



    //Constantes campos tabla Bodega
    public static final String TABLA_SPIN_BODEGA="spin_bodega";
    public static final String CAMPO_ID_TABLA_SPIN_BODEGA="idtab_spin_bodega";
    public static final String CAMPO_ID_SPIN_BODEGA="id_spin_bodega";
    public static final String CAMPO_NOMBRE_SPIN_BODEGA="nombre_spin_bodega";




    public static final String CREAR_TABLA_SPIN_BODEGA="CREATE TABLE " +
            ""+TABLA_SPIN_BODEGA+" ("+CAMPO_ID_TABLA_SPIN_BODEGA+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_ID_SPIN_BODEGA+" INTEGER, "+CAMPO_NOMBRE_SPIN_BODEGA+" TEXT)";



    //Constantes campos tabla ListaPrecio
    public static final String TABLA_SPIN_LISTAPRECIO="spin_listaprecio";
    public static final String CAMPO_ID_TABLA_SPIN_LISTAPRECIO="idtab_spin_listaprecio";
    public static final String CAMPO_ID_SPIN_LISTAPRECIO="id_spin_listaprecio";
    public static final String CAMPO_NOMBRE_SPIN_LISTAPRECIO="nombre_spin_listaprecio";




    public static final String CREAR_TABLA_SPIN_LISTAPRECIO="CREATE TABLE " +
            ""+TABLA_SPIN_LISTAPRECIO+" ("+CAMPO_ID_TABLA_SPIN_LISTAPRECIO+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_ID_SPIN_LISTAPRECIO+" INTEGER, "+CAMPO_NOMBRE_SPIN_LISTAPRECIO+" TEXT)";




    //Constantes campos tabla Sri IVA
    public static final String TABLA_SPIN_SRI="spin_sri";
    public static final String CAMPO_ID_TABLA_SPIN_SRI="idtab_spin_sri";
    public static final String CAMPO_ID_SPIN_SRI="id_spin_sri";
    public static final String CAMPO_NOMBRE_SPIN_SRI="nombre_spin_sri";




    public static final String CREAR_TABLA_SPIN_SRI="CREATE TABLE " +
            ""+TABLA_SPIN_SRI+" ("+CAMPO_ID_TABLA_SPIN_SRI+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_ID_SPIN_SRI+" INTEGER, "+CAMPO_NOMBRE_SPIN_SRI+" INTEGER)";





    //Constantes campos tabla Documento
    public static final String TABLA_SPIN_DOCUMENTO="spin_documento";
    public static final String CAMPO_ID_TABLA_SPIN_DOCUMENTO="idtab_spin_documento";
    public static final String CAMPO_ID_SPIN_DOCUMENTO="id_spin_sri";
    public static final String CAMPO_NOMBRE_SPIN_DOCUMENTO="nombre_spin_documento";




    public static final String CREAR_TABLA_SPIN_DOCUMENTO="CREATE TABLE " +
            ""+TABLA_SPIN_DOCUMENTO+" ("+CAMPO_ID_TABLA_SPIN_DOCUMENTO+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_ID_SPIN_DOCUMENTO+" INTEGER, "+CAMPO_NOMBRE_SPIN_DOCUMENTO+" TEXT)";



    //Constantes campos tabla Precios
    public static final String TABLA_PRECIO="spin_precio";
    public static final String CAMPO_ID_TABLA_PRECIO="idtab_precio";
    public static final String CAMPO_IDPROD_PRECIO="id_idprod_precio";
    public static final String CAMPO_NOMBREPROD_PRECIO="id_nomprod_precio";
    public static final String CAMPO_VALOR_PRECIO="valor_precio";
    public static final String CAMPO_LISTA_PRECIO="lista_precio";




    public static final String CREAR_TABLA_PRECIO="CREATE TABLE " +
            ""+TABLA_PRECIO+" ("+CAMPO_ID_TABLA_PRECIO+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_IDPROD_PRECIO+" INTEGER, "+CAMPO_NOMBREPROD_PRECIO+" TEXT, "+CAMPO_VALOR_PRECIO+" REAL, "+CAMPO_LISTA_PRECIO+" INTEGER)";



    //Constantes campos tabla Pedidos
    public static final String TABLA_PEDIDO="spin_pedido";
    public static final String CAMPO_ID_TABLA_PEDIDO="idtab_pedido";
    public static final String CAMPO_CONTADOR_PEDIDO="contador_pedido";
    public static final String CAMPO_OFICINA_PEDIDO="oficina_pedido";
    public static final String CAMPO_TIPODOC_PEDIDO="tipodoc_pedido";
    public static final String CAMPO_IDENTIFICACIONCLI_PEDIDO="identificacion_pedido";
    public static final String CAMPO_NOMBRECLI_PEDIDO="nombrecli_pedido";
    public static final String CAMPO_FORMAPAGO_PEDIDO="formapago_pedido";
    public static final String CAMPO_MONEDA_PEDIDO="moneda_pedido";
    public static final String CAMPO_ORIGENDOC_PEDIDO="origendoc_pedido";
    public static final String CAMPO_VENDEDOR_PEDIDO="vendedor_pedido";
    public static final String CAMPO_BODEGA_PEDIDO="bodega_pedido";
    public static final String CAMPO_LISTAPRECIO_PEDIDO="listaprecio_pedido";
    public static final String CAMPO_FECHA_PEDIDO="fecha_pedido";
    public static final String CAMPO_LATITUD_PEDIDO="latitud_pedido";
    public static final String CAMPO_LONGITUD_PEDIDO="longitud_pedido";
    public static final String CAMPO_USUARIO_PEDIDO="usuario_pedido";
    public static final String CAMPO_OBSERVACION_PEDIDO="observacion_pedido";
    public static final String CAMPO_SRIIVA_PEDIDO="sriiva_pedido";
    public static final String CAMPO_SUBTOTALGLO_PEDIDO="subtotal_pedido";
    public static final String CAMPO_DESCUENTOGLOBAL_PEDIDO="descuentoglobal_pedido";
    public static final String CAMPO_IVAGLOB_PEDIDO="ivaglob_pedido";
    public static final String CAMPO_VALORGLOB="valglob_pedido";







    public static final String CREAR_TABLA_PEDIDO="CREATE TABLE " +
            ""+TABLA_PEDIDO+" ("+CAMPO_ID_TABLA_PEDIDO+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_CONTADOR_PEDIDO+" INTEGER, "+CAMPO_OFICINA_PEDIDO+" INTEGER, "+CAMPO_TIPODOC_PEDIDO+" INTEGER, "+CAMPO_IDENTIFICACIONCLI_PEDIDO+" TEXT, "+CAMPO_NOMBRECLI_PEDIDO+" TEXT, "+CAMPO_FORMAPAGO_PEDIDO+" INTEGER, "+CAMPO_MONEDA_PEDIDO+" INTEGER, "+CAMPO_ORIGENDOC_PEDIDO+" INTEGER, "+CAMPO_VENDEDOR_PEDIDO+" INTEGER, "+CAMPO_BODEGA_PEDIDO+" INTEGER, "+CAMPO_LISTAPRECIO_PEDIDO+" INTEGER, "+CAMPO_FECHA_PEDIDO+" TEXT, "+CAMPO_LATITUD_PEDIDO+" REAL, "+CAMPO_LONGITUD_PEDIDO+" REAL, "+CAMPO_USUARIO_PEDIDO+" INTEGER, "+CAMPO_OBSERVACION_PEDIDO+" TEXT, "+CAMPO_SRIIVA_PEDIDO+" INTEGER, "+CAMPO_SUBTOTALGLO_PEDIDO+" REAL, "+CAMPO_DESCUENTOGLOBAL_PEDIDO+" REAL, "+CAMPO_IVAGLOB_PEDIDO+" REAL, "+CAMPO_VALORGLOB+" REAL)";



    //Constantes campos tabla Pedido DETALLE
    public static final String TABLA_PEDIDO_DETALLE="spin_pedido_detalle";
    public static final String CAMPO_ID_TABLA_PEDIDODETALLE="idtab_pedidodetalle";
    public static final String CAMPO_PEDIDOIDDETALLE="pedidoiddetalle";
    public static final String CAMPO_DOCUMENTO_PEDIDODETALLE="documento_pedidodetalle";
    public static final String CAMPO_NOMBREPROD_PEDIDODETALLE="nombreprod_pedidodetalle";
    public static final String CAMPO_LOTE_PEDIDODETALLE="lote_pedidodetalle";
    public static final String CAMPO_DESCRIPCION_PEDIDODETALLE="descripcion_pedidodetalle";
    public static final String CAMPO_IDENTIFICACION_PEDIDODETALLE="identificacion_pedidodetalle";
    public static final String CAMPO_CANTIDADPROD_PEDIDODETALLE="cantidadprod_pedidodetalle";
    public static final String CAMPO_PRECIOPROD_PEDIDODETALLE="precioprod_pedidodetalle";
    public static final String CAMPO_DESCUENTOPROD_PEDIDODETALLE="descuentoprod_pedidodetalle";
    public static final String CAMPO_VALOR_PEDIDODETALLE="valor_pedidodetalle";
    public static final String CAMPO_IVA_PEDIDODETALLE="iva_pedidodetalle";







    public static final String CREAR_TABLA_PEDIDODETALLE="CREATE TABLE " +
            ""+TABLA_PEDIDO_DETALLE+" ("+CAMPO_ID_TABLA_PEDIDODETALLE+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_PEDIDOIDDETALLE+" INTEGER, "+CAMPO_DOCUMENTO_PEDIDODETALLE+" INTEGER, "+CAMPO_NOMBREPROD_PEDIDODETALLE+" TEXT, "+CAMPO_LOTE_PEDIDODETALLE+" INTEGER, "+CAMPO_DESCRIPCION_PEDIDODETALLE+" TEXT, "+CAMPO_IDENTIFICACION_PEDIDODETALLE+" TEXT, "+CAMPO_CANTIDADPROD_PEDIDODETALLE+" REAL, "+CAMPO_PRECIOPROD_PEDIDODETALLE+" REAL, "+CAMPO_DESCUENTOPROD_PEDIDODETALLE+" REAL, "+CAMPO_VALOR_PEDIDODETALLE+" REAL, "+CAMPO_IVA_PEDIDODETALLE+" REAL)";



    //Constantes campos tabla Lotes
    public static final String TABLA_SPIN_LOTE="spin_lote";
    public static final String CAMPO_ID_TABLA_SPIN_LOTE="idtab_spin_lote";
    public static final String CAMPO_ID_SPIN_LOTE="id_spin_lote";
    public static final String CAMPO_NOMBRE_LOTE="nombre_spin_lote";




    public static final String CREAR_TABLA_SPIN_LOTE="CREATE TABLE " +
            ""+TABLA_SPIN_LOTE+" ("+CAMPO_ID_TABLA_SPIN_LOTE+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_ID_SPIN_LOTE+" INTEGER, "+CAMPO_NOMBRE_LOTE+" TEXT)";




}
