package br.com.ax.detran.detran.AulasTeoricas.ResumoAulasTeoricas;

import java.io.IOException;
import java.util.List;

public class TesteResumoAulasTeoricas {

    public static void main(String[] args) throws IOException {

    	ResumoAulasTeoricasService disciplinaController = new ResumoAulasTeoricasService();

        String renach = "332806847"; //meu

       List<ResumoAulasTeoricasBean> disciplinaBeans = disciplinaController.convertRetornoResumo(disciplinaController.requestResumoAulas(renach));

       for(int i = 0; i < disciplinaBeans.size(); i++) {
          System.out.println(disciplinaBeans.get(i).getQuantidade() + " - " + disciplinaBeans.get(i).getNome());
       }
/*
Dados retornados dia 02/09/21 02:16  
17 - Legislação De Trânsito
4 - Primeiros Socorros
13 - Direção Defensiva
4 - Meio Ambiente Cidadania
3 - Noções De Mecânica Veicular

*/
    }


}
