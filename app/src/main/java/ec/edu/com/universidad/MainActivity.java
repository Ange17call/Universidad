package ec.edu.com.universidad;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DataBaseManager manager;
    private Cursor cursor;
    private ListView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new DataBaseManager(this);
        lista = (ListView) findViewById(R.id.lv01);

        cursor = manager.cargarCursorAlumnos();
        ArrayList lista1 = new ArrayList();


        if (cursor.moveToFirst()) {

            do {
                lista1.add("codigo:  " + cursor.getString(0));
                lista1.add("Alumno:   " + cursor.getString(1));
                lista1.add("Cedula:   " + cursor.getString(2));
                lista1.add("\n");
            } while (cursor.moveToNext());
        }

        ArrayAdapter adaptadorlista = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista1);
        lista.setAdapter(adaptadorlista);




    }
}