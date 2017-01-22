package ec.edu.com.universidad;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DataBaseManager manager;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private TextView textView;
    private Button listar;
    private Button insertar;
    EditText nombre;
    EditText ci;
    String nom;
    String cedi;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.resultado);
        manager = new DataBaseManager(this);
        listar = (Button)findViewById(R.id.button);
        insertar =(Button)findViewById(R.id.button2);
        nombre = (EditText)findViewById(R.id.nombre);
        ci=(EditText)findViewById(R.id.ci) ;

        insertar.setOnClickListener(this);
        listar.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                nom= String.valueOf(nombre.getText());
                cedi=String.valueOf(ci.getText());
                manager.insertar(nom,cedi);
                break;
            case R.id.button2:
                cursor = manager.cargarCursorAlumnos();
                if (cursor.moveToFirst()) {
                    textView.setText(""); // Vacio el textview
                    //Recorremos el cursor hasta que no haya más registros
                    textView.append("Codigo: \t \t " + " Nombre: \t \t " + " CI: " + "\n");
                    do {
                        Integer codigo = cursor.getInt(0);
                        String nombre = cursor.getString(1);
                        String direccion = cursor.getString(2);
                        textView.append(codigo + "\t \t \t" + nombre + " \t \t \t \t" + direccion + "\n");
                    } while (cursor.moveToNext());
                }
                break;


        }
    }
}
