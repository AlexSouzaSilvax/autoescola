package ResumoAulasTeoricas;

import java.io.IOException;
import java.util.List;

public class Teste {

    public static void main(String[] args) throws IOException {

       DisciplinaController disciplinaController = new DisciplinaController();

        String renach = "RJ332806847"; //meu
        //String renach = "RJ931486076"; //junior
        //String renach = "RJ931739390"; //tias

       List<DisciplinaBean> disciplinaBeans = disciplinaController.convertRetornoResumo(disciplinaController.requestResumoAulas(renach));

       for(int i = 0; i < disciplinaBeans.size(); i++) {
          System.out.println(disciplinaBeans.get(i).getQuantidade() + " - " + disciplinaBeans.get(i).getNome());
       }

    }


}
