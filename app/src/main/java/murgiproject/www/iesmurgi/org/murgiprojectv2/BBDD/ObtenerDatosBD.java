package murgiproject.www.iesmurgi.org.murgiprojectv2.BBDD;

/**
 * Created by Narka on 17/05/2018.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import murgiproject.www.iesmurgi.org.murgiprojectv2.ORLA.Act2Orla_curso;
import murgiproject.www.iesmurgi.org.murgiprojectv2.ORLA.AdapterDatos_Alumno;
import murgiproject.www.iesmurgi.org.murgiprojectv2.ORLA.AdapterDatos_Curso;

public class ObtenerDatosBD extends AsyncTask<Void, Void , String>{
    //datos para conectar
    private final String dir = "jdbc:mysql://www.iesmurgi.org:3306/bjeff";
    private final String usu = "ujeff";
    private final String pass = "pjeff";

    //variables para mysql consultas
    private Connection connection = null;
    private Statement statementCurso = null;
    private Statement statementAlumno = null;
    private ResultSet resultSetCursos = null;
    private ResultSet resultSetAlumnos = null;

    //Adapters
    public static  ArrayList <AdapterDatos_Curso> adaptarCurso = new ArrayList<>();
    public static   ArrayList <AdapterDatos_Alumno> adaptarAlumno = new ArrayList<>();

    //activit
    private Context con = null;

    public ObtenerDatosBD(Context con) {
        this.con = con.getApplicationContext();
    }

    @Override
    protected String doInBackground(Void... Void) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dir,usu,pass);
            statementCurso = connection.createStatement();
            statementAlumno = connection.createStatement();
            resultSetCursos = statementCurso.executeQuery("select * from curso");
            resultSetAlumnos = statementAlumno.executeQuery("select * from alumno");



        } catch (Exception ae) {
            Log.v("Error", "Error connection db obtenerdatosdb "+ae.getLocalizedMessage());
            ae.printStackTrace();
        }

        return null;
    }



    @Override
    protected void onPostExecute(String x) {
        super.onPostExecute(x);
        int pp=0;
        try {
            while (resultSetCursos.next()) {//

                adaptarCurso.add(new AdapterDatos_Curso(
                        resultSetCursos.getString("id_curso"),
                        resultSetCursos.getString("nombre")
                ));
//////Log
                Log.v("Lista cursos",""+adaptarCurso.get(pp).idCurso()+" "+ adaptarCurso.get(pp).getNombre());
                pp++;

            }
                pp=0;
            while (resultSetAlumnos.next()) {

                adaptarAlumno.add(new AdapterDatos_Alumno(
                        resultSetAlumnos.getString("nombre")+" ",
                        resultSetAlumnos.getString("app"),
                        resultSetAlumnos.getString("app2"),
                        resultSetAlumnos.getString("rutaImg"),
                        resultSetAlumnos.getString("curso"),
                        resultSetAlumnos.getString("id_curso")

                ));
//////Log
                Log.v("Lista Alumnos",
                        ""+adaptarAlumno.get(pp).getNombre()+
                                ""+adaptarAlumno.get(pp).getApp()+
                                ""+adaptarAlumno.get(pp).getApp2()+
                                ""+adaptarAlumno.get(pp).getCurso()+
                                ""+adaptarAlumno.get(pp).getRutaImg()+
                                ""+adaptarAlumno.get(pp).getId_curso()
                );
                pp++;

            }

        } catch (Exception ae) {
            Log.v("Error", "Error connection db obtenerdatosdb onpostExecute "+ae.getLocalizedMessage());
            ae.printStackTrace();
        }
        close();
        con.startActivity(new Intent(con,Act2Orla_curso.class));
    }


    private void close() {
        try {
            if (connection != null) { connection.close();  }
            //cierre statements
            if (statementCurso != null) { statementCurso.close();  }
            if (statementAlumno != null) { statementAlumno.close();  }
            //cierre resultSets
            if (resultSetCursos != null) { resultSetCursos.close();  }
            if (resultSetAlumnos != null) { resultSetAlumnos.close();  }
        } catch (Exception ae) {
            Log.v("Error", "Error connection db obtenerdatosdb method close "+ae.getLocalizedMessage());
            ae.printStackTrace();
        }
    }

}