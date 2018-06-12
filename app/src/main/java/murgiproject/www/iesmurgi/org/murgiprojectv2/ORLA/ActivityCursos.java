package murgiproject.www.iesmurgi.org.murgiprojectv2.ORLA;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import murgiproject.www.iesmurgi.org.murgiprojectv2.ORLA.curso.FragmentoCurso;
import murgiproject.www.iesmurgi.org.murgiprojectv2.R;

public class ActivityCursos extends AppCompatActivity {

    private FragmentoCurso fragmentoCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);

        fragmentoCurso = new FragmentoCurso();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        fragmentoCurso.cursos.clear();
    }
}
