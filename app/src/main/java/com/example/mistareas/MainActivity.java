package com.example.mistareas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mistareas.db.ControladorDB;

public class MainActivity extends AppCompatActivity {
    private ControladorDB controladorDB;
    private ArrayAdapter<String> miAdapter;
    private ListView listViewTareas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controladorDB = new ControladorDB(this);
        listViewTareas = (ListView) findViewById(R.id.listaTareas);
        actualizarUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // carga la barra en nuestra app
        getMenuInflater().inflate(R.menu.menuright, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        EditText cajaTexto = new EditText(this);
        //TO-DO
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("nueva tarea")
                .setMessage("¿Qué quieres hacer a continuación?")
                .setView(cajaTexto)
                .setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tarea = cajaTexto.getText().toString();
                        controladorDB.addTarea(tarea);
                        listViewTareas = (ListView) findViewById(R.id.listaTareas);
                        actualizarUI();
                    }
                })
                .setNegativeButton("cancelar", null)
                .create();
        dialog.show();

        return super.onOptionsItemSelected(item);
    }

    private void actualizarUI(){
        if(controladorDB.numeroRegistros()==0){
            listViewTareas.setAdapter(null);
        }else{
            miAdapter = new ArrayAdapter<>(this, R.layout.item_tarea, R.id.task_title, controladorDB.obtenerTareas());
            listViewTareas.setAdapter(miAdapter);
        }
    }
    public void borrarTarea(View view){
        View parent = (View) view.getParent();
        TextView tareaTextView = (TextView) parent.findViewById(R.id.task_title);
        String tarea = tareaTextView.getText().toString();
        controladorDB.borrarTareas(tarea);
        actualizarUI();
    }

    public void actualizar(View view){

    }

}