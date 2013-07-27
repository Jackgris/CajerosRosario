package com.aprendiendodeandroid.bancos.rosario.modelo;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
			+ COLUMN_LATITUD + " INTEGER," 
	        + COLUMN_LONGITUD + " INTEGER," 
			+ COLUMN_TELEFONO + " TEXT)";
    	
	// Elimnar la tabla
	final static private String eliminarTablaCajeros = "DROP TABLE IF EXISTS " + TABLE_CAJEROS;
	
	// Estos van a ser todos los cajeros que encuentre de rosario
	final static private String insert1 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'1','Banca Nazionale del Lavoro S.A.','Link','San Martín', '902', " +
			"'-329479424','-606370975','')";
	
	final static private String insert2 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'2','Banca Nazionale del Lavoro S.A.','Link','Av. Córdoba', '1772', " +
			"'-32944754','-606475068','')";
	
	final static private String insert3 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'3','Banco Columbia S.A.','Link','San Martín', '866', " +
			"'-329475094','-606369908','')";
	
	final static private String insert4 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'4','Banco Credicoop Coop. Ltdo.','Link','Ovidio Lagos', '132', " +
			"'-329344492','-606596142','')";
	
	final static private String insert5 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'5','Banco Credicoop Coop. Ltdo.','Link','27 de Febrero', '1501', " +
			"'-329598419','-607288666','')";
	
	final static private String insert6 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'6','Banco Credicoop Coop. Ltdo.','Link','Bvrd. Rondeau', '3422', " +
			"'-328835564','-606956369','')";
	
	final static private String insert7 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'7','Banco Credicoop Coop. Ltdo.','Link','Ovidio Lagos', '3605', " +
			"'-32974913','-606694327','')";
	
	final static private String insert8 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'8','Banco Credicoop Coop. Ltdo.','Link','Pte. Roca', '946', " +
			"'-329469975','-606456596','')";
	
	final static private String insert9 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'9','Banco Credicoop Coop. Ltdo.','Link','Santa Fé', '1056', " +
			"'-329453481','-606373005','')";
	
	final static private String insert10 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'10','Banco Credicoop Coop. Ltdo.','Link','Av. Arijon', '320', " +
			"'-330023778','-606363287','')";
	
	final static private String insert11 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'11','Banco Credicoop Coop. Ltdo.','Link','Belgrano', '31', " +
			"'-328638683','-607022191','')";

	final static private String insert12 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'12','Banco Credicoop Coop. Ltdo.','Link','Ovidio Lagos', '132', " +
			"'-329344492','-606596142','')";

	final static private String insert13 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'13','Banco Credicoop Coop. Ltdo.','Link','Pellegrini', '3205', " +
			"'-329520441','-606698025','')";
	
	final static private String insert14 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'14','Banco Credicoop Coop. Ltdo.','Link','Tucuman', '1685', " +
			"'-329404387','-606445965','')";
	
	final static private String insert15 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'15','Banco Credicoop Coop. Ltdo.','Link','San Martin', '1371', " +
			"'-330181127','-606326719','')";
	
	final static private String insert16 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'16','Banco de la Nación Argentina','Link','Av. Córdoba', '1026', " +
			"'-329466243','-606371644','')";

	final static private String insert17 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'17','Banco de la Nación Argentina','Link','San Luis', '1549', " +
			"'-329478918','-606451161','')";
	
	final static private String insert18 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'18','Banco de la Nación Argentina','Link','Alberdi', '701', " +
			"'-329192898','-60682573','')";
	
	final static private String insert19 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'19','Banco de la Nación Argentina','Link','Santa Fé', '1274', " +
			"'-329446081','-606403044','')";
	

	final static private String insert20 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'20','Banco de la Nación Argentina','Link','Av. San Martín', '2599', " +
			"'-329677309','-606419241','')";
	
	final static private String insert21 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'21','Banco de la Nación Argentina','Link','Entre Ríos', '451', " +
			"'-329418888','-606399881','')";
	
	final static private String insert22 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'22','Banco de la Nación Argentina','Link','Zeballos', '1341', " +
			"'-329543368','-606436677','')";
	
	final static private String insert23 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'23','Banco de la Nación Argentina','Link','Alvear', '151', " +
			"'-329358671','-606529293','')";

	final static private String insert24 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'24','Banco de la Nación Argentina','Link','Alvear', '304', " +
			"'-329376731','-606533977','')";
	
	final static private String insert25 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'25','Banco de la Nación Argentina','Link','Av. Córdoba', '317', " +
			"'-32948236','-606290913','')";
	
	final static private String insert26 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'26','Banco de la Nación Argentina','Link','Mendoza', '3801', " +
			"'-329449246','-606764323','')";
	
	final static private String insert27 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'27','Banco de la Provincia de Córdoba S.A.','Link','Santa Fé', '1240', " +
			"'-329447183','-606398284','')";
	
	final static private String insert28 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'28','Banco de la Provincia de Córdoba S.A.','Link','Bvrd. Oroño 6000', '1240', " +
			"'-330062641','-606650493','')";
	
	final static private String insert29 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'29','Banco Hipotecario S.A.','Link','Santa Fe', '1157', " +
			"'-329450222','-60638689','')";
	
	final static private String insert30 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'30','Banco Municipal de Rosario','Link','Córdoba', '8032', " +
			"'-329330431','-607129201','')";
	
	final static private String insert31 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'31','Banco Municipal de Rosario','Link','Av. Belgrano', '860', " +
			"'-329485574','-606285611','')";
	
	final static private String insert32 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'32','Banco Municipal de Rosario','Link','Córdoba piso 2do.', '1643', " +
			"'-329450989','-606457681','')";
	
	final static private String insert33 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'33','Banco Municipal de Rosario','Link','Uriburu', '637', " +
			"'-329450989','-606457681','')";
	
	final static private String insert34 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'34','Banco Municipal de Rosario','Link','San Martín', '730', " +
			"'-329459539','-606366071','')";
	
	final static private String insert35 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'35','Banco Municipal de Rosario','Link','Pellegrini', '250', " +
			"'-32960325','-606234284','')";
	
	final static private String insert36 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'36','Banco Municipal de Rosario','Link','Riobamba bis', '250', " +
			"'-329507408','-606665001','')";
	
	final static private String insert37 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'37','Banco Municipal de Rosario','Link','Bvrd. Oroño', '1261', " +
			"'-329495139','-606548571','')";
	
	final static private String insert38 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'38','Banco Municipal de Rosario','Link','Maipu', '1065', " +
			"'-329502317','-606361675','')";
	
	final static private String insert39 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'39','Banco Municipal de Rosario','Link','Wheelwrigth', '1484', " +
			"'-32.93644142719205','-60.641913414001465','')";
	
	final static private String insert40 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'40','Banco Municipal de Rosario','Link','Mendoza', '4554', " +
			"'-329430904','-606866864','')";
	
	final static private String insert41 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'41','Banco Municipal de Rosario','Link','Alberdi bis', '718', " +
			"'-328952379','-606944521','')";
	
	final static private String insert42 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'42','Banco Municipal de Rosario','Link','Córdoba', '8032', " +
			"'-329330431','-607129201','')";
	
	final static private String insert43 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'43','Banco Municipal de Rosario','Link','Montevideo', '2076', " +
			"'-329536452','-606540171','')";
	
	final static private String insert44 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'44','Banco Municipal de Rosario','Link','Cafferata', '702', " +
			"'-329392194','-606707847','')";
	
	final static private String insert45 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'45','Banco Municipal de Rosario','Link','Necochea', '1225', " +
			"'-32954173','-606255731','')";
	
	final static private String insert46 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'46','Banco Municipal de Rosario','Link','Santa Fé', '3160', " +
			"'-329399749','-606661726','')";
	
	final static private String insert47 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'47','Banco Municipal de Rosario','Link','Buenos Aires', '701', " +
			"'-329467881','-606326058','')";
	
	final static private String insert48 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'48','Banco Municipal de Rosario','Link','San Martín', '2884', " +
			"'-329710291','-606427751','')";
	
	final static private String insert49 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'49','Banco Municipal de Rosario','Link','San Martín', '730', " +
			"'-329459539','-606366071','')";
	
	final static private String insert50 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
			+ COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
			+ COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
			"'50','Banco Municipal de Rosario','Link','San Lorenzo', '1055', " +
			"'-329444246','-606370573','')";
	
	final static private String insert51 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
            + COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
            + COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
            "'51','Banco Saenz S.A.','Link','Rioja', '1040', " +
            "'-329477772','-606376584','')";
	
	final static private String insert52 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
            + COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
            + COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
            "'52','Banex','Banelco','Av. Cordoba', '960', " +
            "'-32946809','-606362197','')";
	
	final static private String insert53 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
            + COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
            + COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
            "'53','Bank Boston','Banelco','Paraguay', '927', " +
            "'-329469832','-60644138','')";
	
	final static private String insert54 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
            + COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
            + COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
            "'54','Bank Boston','Banelco','25 De Mayo Bis', '1340', " +
            "'-328532907','-607831037','')";
	
	final static private String insert55 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
            + COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
            + COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
            "'55','Bank Boston','Banelco','Av. Cordoba', '1201', " +
            "'-329462101','-606396129','')";
	
	final static private String insert56 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
            + COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
            + COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
            "'56','Bank Boston','Banelco','Av. Carlos Pellegrini', '1125', " +
            "'-329571723','-606412048','')";
	
	final static private String insert57 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
            + COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
            + COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
            "'57','Bank Boston','Banelco','Nansen', '255', " +
            "'-329091438','-606825585','')";
	
	final static private String insert58 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
            + COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
            + COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
            "'58','Bank Boston','Banelco','Cordoba', '1201', " +
            "'-329462101','-606396129','')";
	
	final static private String insert59 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
            + COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
            + COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
            "'59','Bank Boston','Banelco','Nansen', '323', " +
            "'-329091628','-606834603','')";
	
	final static private String insert60 = "INSERT INTO " + TABLE_CAJEROS + "(" + COLUMN_ID + "," 
            + COLUMN_BANCO + "," + COLUMN_RED + "," + COLUMN_CALLE + "," + COLUMN_ALTURA + "," 
            + COLUMN_LATITUD + "," + COLUMN_LONGITUD + "," + COLUMN_TELEFONO + ") VALUES (" +
            "'60','Bank Boston','Banelco','J. M. De Rosas', '957', " +
            "'-329398285','-606865085','')";
	
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

}
