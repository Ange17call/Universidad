package ec.edu.com.universidad;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Dragon on 21/01/2017.
 */

public class DataBaseManager {

    public static final String sqlCreateTabla = "CREATE TABLE Alumno (idAlumno INTEGER PRIMARY KEY AUTOINCREMENT, nombreAlumno TEXT, ciAlumno TEXT)";

    //public static final String CREATE_TABLE="CREATE TABLE "+ TABLE_NAME+ "("+CN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT"+","+ CN_NAME+ " TEXT NOT NULL,"+CN_CI+" INTEGER)";

    private DBHelper helper;
    private SQLiteDatabase db;
    public DataBaseManager(Context context){

        helper = new DBHelper(context);
        db = helper.getWritableDatabase();//aqui se crea la base de datos modo escritura


    }


       public void insertar(String nombreAlumno, String ciAlumno){

           db.execSQL("INSERT INTO Alumno (nombreAlumno,ciAlumno) VALUES ('" +nombreAlumno + "','"+ciAlumno+"')");

           //db.insert(TABLE_NAME,null,generarContentValues(nombreAlumno,ciAlumno));

       }
    /*
       private ContentValues generarContentValues(String nombreAlumno, Integer ciAlumno){
           ContentValues valores = new ContentValues();
           valores.put(CN_NAME,nombreAlumno);
           valores.put(CN_CI,ciAlumno);

           return valores;

       }


       //la misma insercion pero con otro metodo
       public void  insertar2(String nombreAlumno, Integer ciAlumno){

           db.execSQL("insert into Alumno"+TABLE_NAME+" values (null, '"+nombreAlumno+"',"+ciAlumno+")");

       }

       public void eliminar(String nombre){
           //bd.delete(TAbla,clausula where, Argumentos where)
           db.delete(TABLE_NAME,CN_NAME+"=?",new String[] {nombre});

       }
       public void eliminarMultiple(String nombreAlumno, String nombreAlumno2){
           db.delete(TABLE_NAME,CN_NAME+"IN(?,?)",new String[] {nombreAlumno,nombreAlumno2});


       }
       public void modificarciAlumno(String nombreAlumno, Integer nuevociAlumno){

           //bd.update(TABLA, ContentValues, Clausula where, Argumentos where)
           db.update(TABLE_NAME,generarContentValues(nombreAlumno,nuevociAlumno),CN_NAME+"=?",new String[] {nombreAlumno});
       }*/
    public Cursor cargarCursorAlumnos(){

    //   String[] columnas = new String[] {CN_ID,CN_NAME,CN_CI};
        return db.query("Alumno", new String[]{"idAlumno", "nombreAlumno", "ciAlumno"},null,null,null,null,null);

    }
}
