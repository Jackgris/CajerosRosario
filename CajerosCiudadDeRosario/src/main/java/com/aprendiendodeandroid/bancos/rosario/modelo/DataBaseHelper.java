package com.aprendiendodeandroid.bancos.rosario.modelo;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase se va a encargar de la creacion de la base de datos
 * @author gabriel
 *
 */
public class DataBaseHelper extends SQLiteOpenHelper implements ConstantesCajeros {

    /*
     * Estas van a ser las consultas con las que se van a crear las tablas e insertar los datos por
     * defecto, que van a estar dentro de nuestra bd
     */
	
	// FIXME crear la tabla
	final static private String tableCajeros = "CREATE TABLE " + TABLE_CAJEROS +
	        " (" + COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, "
			+ COLUMN_BANCO + " TEXT,"
	        + COLUMN_RED + " TEXT,"
			+ COLUMN_CALLE + " TEXT," 
	        + COLUMN_ALTURA + " INTEGER," 
			+ COLUMN_LATITUD + " REAL,"
	        + COLUMN_LONGITUD + " REAL,"
			+ COLUMN_TELEFONO + " TEXT)";
    	
	// Elimnar la tabla
	final static private String eliminarTablaCajeros = "DROP TABLE IF EXISTS " + TABLE_CAJEROS;
	
	// Estos van a ser todos los cajeros que encuentre de rosario
	final static private String insert1 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'1','Banca Nazionale del Lavoro S.A.','Link','San Martín', '902', " +
			"'-32.9479424','-60.6370975','')";
	
	final static private String insert2 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'2','Banca Nazionale del Lavoro S.A.','Link','Av. Córdoba', '1772', " +
			"'-32.944754','-60.6475068','')";
	
	final static private String insert3 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'3','Banco Columbia S.A.','Link','San Martín', '866', " +
			"'-32.9475094','-60.6369908','')";
	
	final static private String insert4 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'4','Banco Credicoop Coop. Ltdo.','Link','Ovidio Lagos', '132', " +
			"'-32.9344492','-60.6596142','')";
	
	final static private String insert5 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'5','Banco Credicoop Coop. Ltdo.','Link','27 de Febrero', '1501', " +
			"'-32.9598419','-60.7288666','')";
	
	final static private String insert6 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'6','Banco Credicoop Coop. Ltdo.','Link','Bvrd. Rondeau', '3422', " +
			"'-32.8835564','-60.6956369','')";
	
	final static private String insert7 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'7','Banco Credicoop Coop. Ltdo.','Link','Ovidio Lagos', '3605', " +
			"'-32.974913','-60.6694327','')";
	
	final static private String insert8 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'8','Banco Credicoop Coop. Ltdo.','Link','Pte. Roca', '946', " +
			"'-32.9469975','-60.6456596','')";
	
	final static private String insert9 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'9','Banco Credicoop Coop. Ltdo.','Link','Santa Fé', '1056', " +
			"'-32.9453481','-60.6373005','')";
	
	final static private String insert10 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'10','Banco Credicoop Coop. Ltdo.','Link','Av. Arijon', '320', " +
			"'-33.0023778','-60.6363287','')";
	
	final static private String insert11 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'11','Banco Credicoop Coop. Ltdo.','Link','Belgrano', '31', " +
			"'-32.8638683','-60.7022191','')";

	final static private String insert12 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'12','Banco Credicoop Coop. Ltdo.','Link','Ovidio Lagos', '132', " +
			"'-32.9344492','-60.6596142','')";

	final static private String insert13 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'13','Banco Credicoop Coop. Ltdo.','Link','Pellegrini', '3205', " +
			"'-32.9520441','-60.6698025','')";
	
	final static private String insert14 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'14','Banco Credicoop Coop. Ltdo.','Link','Tucuman', '1685', " +
			"'-32.9404387','-60.6445965','')";
	
	final static private String insert15 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'15','Banco Credicoop Coop. Ltdo.','Link','San Martin', '1371', " +
			"'-33.0181127','-60.6326719','')";
	
	final static private String insert16 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'16','Banco de la Nación Argentina','Link','Av. Córdoba', '1026', " +
			"'-32.9466243','-60.6371644','')";

	final static private String insert17 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'17','Banco de la Nación Argentina','Link','San Luis', '1549', " +
			"'-32.9478918','-60.6451161','')";
	
	final static private String insert18 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'18','Banco de la Nación Argentina','Link','Alberdi', '701', " +
			"'-32.9192898','-60.682573','')";
	
	final static private String insert19 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'19','Banco de la Nación Argentina','Link','Santa Fé', '1274', " +
			"'-32.9446081','-60.6403044','')";
	

	final static private String insert20 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'20','Banco de la Nación Argentina','Link','Av. San Martín', '2599', " +
			"'-32.9677309','-60.6419241','')";
	
	final static private String insert21 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'21','Banco de la Nación Argentina','Link','Entre Ríos', '451', " +
			"'-32.9418888','-60.6399881','')";
	
	final static private String insert22 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'22','Banco de la Nación Argentina','Link','Zeballos', '1341', " +
			"'-32.9543368','-60.6436677','')";
	
	final static private String insert23 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'23','Banco de la Nación Argentina','Link','Alvear', '151', " +
			"'-32.9358671','-60.6529293','')";

	final static private String insert24 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'24','Banco de la Nación Argentina','Link','Alvear', '304', " +
			"'-32.9376731','-60.6533977','')";
	
	final static private String insert25 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'25','Banco de la Nación Argentina','Link','Av. Córdoba', '317', " +
			"'-32.948236','-60.6290913','')";
	
	final static private String insert26 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'26','Banco de la Nación Argentina','Link','Mendoza', '3801', " +
			"'-32.9449246','-60.6764323','')";
	
	final static private String insert27 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'27','Banco de la Provincia de Córdoba S.A.','Link','Santa Fé', '1240', " +
			"'-32.9447183','-60.6398284','')";
	
	final static private String insert28 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'28','Banco de la Provincia de Córdoba S.A.','Link','Bvrd. Oroño 6000', '1240', " +
			"'-33.0062641','-60.6650493','')";
	
	final static private String insert29 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'29','Banco Hipotecario S.A.','Link','Santa Fe', '1157', " +
			"'-32.9450222','-60.638689','')";
	
	final static private String insert30 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'30','Banco Municipal de Rosario','Link','Córdoba', '8032', " +
			"'-32.9330431','-60.7129201','')";
	
	final static private String insert31 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'31','Banco Municipal de Rosario','Link','Av. Belgrano', '860', " +
			"'-32.9485574','-60.6285611','')";
	
	final static private String insert32 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'32','Banco Municipal de Rosario','Link','Córdoba piso 2do.', '1643', " +
			"'-32.9450989','-60.6457681','')";
	
	final static private String insert33 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'33','Banco Municipal de Rosario','Link','Uriburu', '637', " +
			"'-32.9450989','-60.6457681','')";
	
	final static private String insert34 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'34','Banco Municipal de Rosario','Link','San Martín', '730', " +
			"'-32.9459539','-60.6366071','')";
	
	final static private String insert35 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'35','Banco Municipal de Rosario','Link','Pellegrini', '250', " +
			"'-32.960325','-60.6234284','')";
	
	final static private String insert36 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'36','Banco Municipal de Rosario','Link','Riobamba bis', '250', " +
			"'-32.9507408','-60.6665001','')";
	
	final static private String insert37 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'37','Banco Municipal de Rosario','Link','Bvrd. Oroño', '1261', " +
			"'-32.9495139','-60.6548571','')";
	
	final static private String insert38 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'38','Banco Municipal de Rosario','Link','Maipu', '1065', " +
			"'-32.9502317','-60.6361675','')";
	
	final static private String insert39 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'39','Banco Municipal de Rosario','Link','Wheelwrigth', '1484', " +
			"'-32.93644142719205','-60.641913414001465','')";
	
	final static private String insert40 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'40','Banco Municipal de Rosario','Link','Mendoza', '4554', " +
			"'-32.9430904','-60.6866864','')";
	
	final static private String insert41 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'41','Banco Municipal de Rosario','Link','Alberdi bis', '718', " +
			"'-32.8952379','-60.6944521','')";
	
	final static private String insert42 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'42','Banco Municipal de Rosario','Link','Córdoba', '8032', " +
			"'-32.9330431','-60.7129201','')";
	
	final static private String insert43 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'43','Banco Municipal de Rosario','Link','Montevideo', '2076', " +
			"'-32.9536452','-60.6540171','')";
	
	final static private String insert44 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'44','Banco Municipal de Rosario','Link','Cafferata', '702', " +
			"'-32.9392194','-60.6707847','')";
	
	final static private String insert45 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'45','Banco Municipal de Rosario','Link','Necochea', '1225', " +
			"'-32.954173','-60.6255731','')";
	
	final static private String insert46 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'46','Banco Municipal de Rosario','Link','Santa Fé', '3160', " +
			"'-32.9399749','-60.6661726','')";
	
	final static private String insert47 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'47','Banco Municipal de Rosario','Link','Buenos Aires', '701', " +
			"'-32.9467881','-60.6326058','')";
	
	final static private String insert48 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'48','Banco Municipal de Rosario','Link','San Martín', '2884', " +
			"'-32.9710291','-60.6427751','')";
	
	final static private String insert49 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'49','Banco Municipal de Rosario','Link','San Martín', '730', " +
			"'-32.9459539','-60.6366071','')";
	
	final static private String insert50 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'50','Banco Municipal de Rosario','Link','San Lorenzo', '1055', " +
			"'-32.9444246','-60.6370573','')";
	
	final static private String insert51 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
            + COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
            + COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
            "'51','Banco Saenz S.A.','Link','Rioja', '1040', " +
            "'-32.9477772','-60.6376584','')";
	
	final static private String insert52 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
            + COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
            + COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
            "'52','Banex','Banelco','Av. Cordoba', '960', " +
            "'-32.946809','-60.6362197','')";
	
	final static private String insert53 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
            + COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
            + COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
            "'53','Bank Boston','Banelco','Paraguay', '927', " +
            "'-32.9469832','-60.644138','')";
	
	final static private String insert54 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
            + COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
            + COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
            "'54','Bank Boston','Banelco','25 De Mayo Bis', '1340', " +
            "'-32.8532907','-60.7831037','')";
	
	final static private String insert55 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
            + COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
            + COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
            "'55','Bank Boston','Banelco','Av. Cordoba', '1201', " +
            "'-32.9462101','-60.6396129','')";
	
	final static private String insert56 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
            + COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
            + COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
            "'56','Bank Boston','Banelco','Av. Carlos Pellegrini', '1125', " +
            "'-32.9571723','-60.6412048','')";
	
	final static private String insert57 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
            + COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
            + COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
            "'57','Bank Boston','Banelco','Nansen', '255', " +
            "'-32.9091438','-60.6825585','')";
	
	final static private String insert58 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
            + COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
            + COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
            "'58','Bank Boston','Banelco','Cordoba', '1201', " +
            "'-32.9462101','-60.6396129','')";
	
	final static private String insert59 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
            + COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
            + COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
            "'59','Bank Boston','Banelco','Nansen', '323', " +
            "'-32.9091628','-60.6834603','')";
	
	final static private String insert60 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
            + COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
            + COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
            "'60','Bank Boston','Banelco','J. M. De Rosas', '957', " +
            "'-32.9398285','-60.6865085','')";
	
	/**
	 * Constructor por defecto para la clase con la que vamos a crear nuestra base de datos
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public DataBaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Aca vamos a armar la estructura de la base de datos
	    try{
	        // Creamos la tabla
	        db.execSQL(tableCajeros);
	      
	        // Insertamos los datos
	        db.execSQL(insert1);
	        db.execSQL(insert2);
	        db.execSQL(insert3);
	        db.execSQL(insert4);
	        db.execSQL(insert5);
	        db.execSQL(insert6);
	        db.execSQL(insert7);
	        db.execSQL(insert8);
	        db.execSQL(insert9);
	        db.execSQL(insert10);
	        db.execSQL(insert11);
	        db.execSQL(insert12);
	        db.execSQL(insert13);
	        db.execSQL(insert14);
	        db.execSQL(insert15);
	        db.execSQL(insert16);
	        db.execSQL(insert17);
	        db.execSQL(insert18);
	        db.execSQL(insert19);
	        db.execSQL(insert20);
	        db.execSQL(insert21);
	        db.execSQL(insert22);
	        db.execSQL(insert23);
	        db.execSQL(insert24);
	        db.execSQL(insert25);
	        db.execSQL(insert26);
	        db.execSQL(insert27);
	        db.execSQL(insert28);
	        db.execSQL(insert29);
	        db.execSQL(insert30);
	        db.execSQL(insert31);
	        db.execSQL(insert32);
	        db.execSQL(insert33);
	        db.execSQL(insert34);
	        db.execSQL(insert35);
	        db.execSQL(insert36);
	        db.execSQL(insert37);
	        db.execSQL(insert38);
	        db.execSQL(insert39);
	        db.execSQL(insert40);
	        db.execSQL(insert41);
	        db.execSQL(insert42);
	        db.execSQL(insert43);
	        db.execSQL(insert44);
	        db.execSQL(insert45);
	        db.execSQL(insert46);
	        db.execSQL(insert47);
	        db.execSQL(insert48);
	        db.execSQL(insert49);
	        db.execSQL(insert50);
	        db.execSQL(insert51);
	        db.execSQL(insert52);
	        db.execSQL(insert53);
	        db.execSQL(insert54);
	        db.execSQL(insert55);
	        db.execSQL(insert56);
	        db.execSQL(insert57);
	        db.execSQL(insert58);
	        db.execSQL(insert59);
	        db.execSQL(insert60);

	    }
	    catch(SQLException ex){
	        Log.d("SQLException", "CREATE: " + ex.getMessage());
	    }
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionanterior, int nuevaversion) {
		
		// verificamos si es una version nueva de la base de datos, si es asi creamos la tabla
		// nueva
		if(versionanterior < nuevaversion){
		    try{
		        //Se elimina la version anterior de la tabla
	            db.execSQL(eliminarTablaCajeros);
	     
	            //Se crea la nueva version de la tabla
	            db.execSQL(tableCajeros);
	            
	         // Insertamos los datos
		        db.execSQL(insert1);
		        db.execSQL(insert2);
		        db.execSQL(insert3);
		        db.execSQL(insert4);
		        db.execSQL(insert5);
		        db.execSQL(insert6);
		        db.execSQL(insert7);
		        db.execSQL(insert8);
		        db.execSQL(insert9);
		        db.execSQL(insert10);
		        db.execSQL(insert11);
		        db.execSQL(insert12);
		        db.execSQL(insert13);
		        db.execSQL(insert14);
		        db.execSQL(insert15);
		        db.execSQL(insert16);
		        db.execSQL(insert17);
		        db.execSQL(insert18);
		        db.execSQL(insert19);
		        db.execSQL(insert20);
		        db.execSQL(insert21);
		        db.execSQL(insert22);
		        db.execSQL(insert23);
		        db.execSQL(insert24);
		        db.execSQL(insert25);
		        db.execSQL(insert26);
		        db.execSQL(insert27);
		        db.execSQL(insert28);
		        db.execSQL(insert29);
		        db.execSQL(insert30);
		        db.execSQL(insert31);
		        db.execSQL(insert32);
		        db.execSQL(insert33);
		        db.execSQL(insert34);
		        db.execSQL(insert35);
		        db.execSQL(insert36);
		        db.execSQL(insert37);
		        db.execSQL(insert38);
		        db.execSQL(insert39);
		        db.execSQL(insert40);
		        db.execSQL(insert41);
		        db.execSQL(insert42);
		        db.execSQL(insert43);
		        db.execSQL(insert44);
		        db.execSQL(insert45);
		        db.execSQL(insert46);
		        db.execSQL(insert47);
		        db.execSQL(insert48);
		        db.execSQL(insert49);
		        db.execSQL(insert50);
		        db.execSQL(insert51);
		        db.execSQL(insert52);
		        db.execSQL(insert53);
		        db.execSQL(insert54);
		        db.execSQL(insert55);
		        db.execSQL(insert56);
		        db.execSQL(insert57);
		        db.execSQL(insert58);
		        db.execSQL(insert59);
		        db.execSQL(insert60);

		    }
		    catch(SQLException ex){
	            Log.d("SQLException", "UPGRADE: " + ex.getMessage());
	        }

		}
	}

    /**
     * Con este metodo vamos a realizar toda la carga de los cajeros en nuestra aplicacion
     *
     * @param db necesario para poder ejecutar el SQL
     */
    private void cargarDatosCajeros(SQLiteDatabase db){

        List<Cajero> listaCajeros = new ArrayList<Cajero>();

        // FIXME deberia poder cargar la lista de cajeros

        for (Cajero cajero : listaCajeros){

            final String insert = "INSERT INTO " + TABLE_CAJEROS + "("
                    + COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + ","
                    + COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
                    "'" + cajero.getNombreBanco() + "','" + cajero.getTipoCajero()+ "','" +
                    cajero.getCalle() + "', '" + cajero.getAltura() + "', " +
                    "'" + cajero.getLatitud() + "','" + cajero.getLongitud() + "','" +
                    cajero.getTelefonoBanco() + "')";

            db.execSQL(insert);
        }
    }

}
