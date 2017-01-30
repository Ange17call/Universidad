package ec.edu.com.universidad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Dragon on 21/01/2017.
 */

public class DataBaseManager {

    public static final String TABLE_NAMEALUMNO = "Alumno";

    public static final String CN_IDALUMNO = "idAlumno";
    public static final String CN_NAMEALUMNO = "nombreAlumno";
    public static final String CN_CIALUMNO = "ciAlumno";


    public static final String CREATE_TABLEALUMNO = "CREATE TABLE " + TABLE_NAMEALUMNO + "( "
            + CN_IDALUMNO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CN_NAMEALUMNO + " TEXT, "
            + CN_CIALUMNO + " TEXT)";

    private DBHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {

        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    private ContentValues generarContentValues(String nombreAlumno, String ciAlumno) {
        ContentValues valores = new ContentValues();
        valores.put(CN_NAMEALUMNO, nombreAlumno);
        valores.put(CN_CIALUMNO, ciAlumno);

        return valores;
    }

    public void insertar(String nombreAlumno, String ciAlumno) {

        //bd.insert(TABLA, NullColumnHack CUALES PUEDEN SER NULOS, ContentValues--contenedordevalores)
        db.insert(TABLE_NAMEALUMNO, null, generarContentValues(nombreAlumno, ciAlumno));//devuelve -1 hay problemas en la insercion
    }

    public void insertar2(String nombreAlumno, String ciAlumno) {
        //INSERT INTO Alumno VALUES (null,'paco','999')//no necesitamos contenedor de valores
        db.execSQL("INSER INTO " + TABLE_NAMEALUMNO + " VALUES (null, '" + nombreAlumno + "', " + ciAlumno + ")");
    }

    public void eliminar(String nombreAlumno) {
        //bd.delete(Tabla,Clausula Where,Argumentos where)
        db.delete(TABLE_NAMEALUMNO, CN_NAMEALUMNO + "=?", new String[]{nombreAlumno});

    }

    //para eliminar a dos personas
    public void eliminarMultiple(String nombreAlumno1, String nombreAlumno2) {

        db.delete(TABLE_NAMEALUMNO, CN_NAMEALUMNO + "IN (?,?)", new String[]{nombreAlumno1, nombreAlumno2});
    }

    public void modificarciAlumno(String nombreAlumno, String nuevociAlumno) {

        //bd.update(TABLA, cONTENTVALUES,CLAUSULA WHERE, ARGUMENTOS WHERE)
        db.update(TABLE_NAMEALUMNO, generarContentValues(nombreAlumno, nuevociAlumno), CN_NAMEALUMNO + "=?", new String[]{nombreAlumno});
    }


    public Cursor cargarCursorAlumnos(){

        String[] columnas = new String[]{CN_IDALUMNO, CN_NAMEALUMNO, CN_CIALUMNO};
        //query(String tabla, String[] columnas, String selecciobn,string selection args,String groupby,string habing, string order by)
        return db.query(TABLE_NAMEALUMNO, columnas, null, null, null, null, null);
    }


}
