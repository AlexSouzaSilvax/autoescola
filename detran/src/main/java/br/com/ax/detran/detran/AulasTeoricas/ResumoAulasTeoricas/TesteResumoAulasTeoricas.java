package br.com.ax.detran.detran.AulasTeoricas.ResumoAulasTeoricas;

import java.io.IOException;
import java.util.List;

public class TesteResumoAulasTeoricas {

    public static void main(String[] args) throws IOException {

    	ResumoAulasTeoricasService disciplinaController = new ResumoAulasTeoricasService();

        String renach = "RJ332806847"; //meu
        //String renach = "RJ931486076"; //junior
        //String renach = "RJ931739390"; //tias

       List<ResumoAulasTeoricasBean> disciplinaBeans = disciplinaController.convertRetornoResumo(disciplinaController.requestResumoAulas(renach));

       for(int i = 0; i < disciplinaBeans.size(); i++) {
          System.out.println(disciplinaBeans.get(i).getQuantidade() + " - " + disciplinaBeans.get(i).getNome());
       }

    }


}
