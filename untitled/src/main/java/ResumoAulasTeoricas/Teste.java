package ResumoAulasTeoricas;

import java.io.IOException;
import java.util.List;

public class Teste {

    public static void main(String[] args) throws IOException {

       DisciplinaController disciplinaController = new DisciplinaController();

       List<DisciplinaBean> disciplinaBeans = disciplinaController.convertRetornoResumo(disciplinaController.requestResumoAulas("RJ332806847"));

       for(int i = 0; i < disciplinaBeans.size(); i++) {
          System.out.println(disciplinaBeans.get(i).getQuantidade() + " - " + disciplinaBeans.get(i).getNome());
       }

    }


}
