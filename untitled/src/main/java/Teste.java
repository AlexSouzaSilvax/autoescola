import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Teste {

    public static void main(String[] args) throws IOException {
       List<Disciplina> disciplinas = convertRetornoResumo(requestResumoAulas("RJ332806847"));
       for(int i = 0; i < disciplinas.size(); i++) {
          System.out.println("\nNome: " + disciplinas.get(i).getNome() + "\nQuantidade: " + disciplinas.get(i).getQuantidade());
       }
    }

    public static String requestResumoAulas(String renach) {
        try {
            Process process = Runtime.getRuntime().exec("curl -k -d \"renach="+renach+"&tipo=resumo\" -H \"Content-Type: application/x-www-form-urlencoded\" -X POST https://www2.detran.rj.gov.br/portal/habilitacao/biometriaValid");
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while((line = reader.readLine()) != null) {
                output.append(line+"\n");
            }
            int exitVal = process.waitFor();
            if(exitVal == 0) {
                return output.toString();
            } else {
                return "error";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static List<Disciplina> convertRetornoResumo(String retorno) throws IOException {

        List<Disciplina> disciplinas = new ArrayList<>();

        List<String> lista1 = new ArrayList<>();
        List<String> lista2 = new ArrayList<>();

        Document doc = Jsoup.parseBodyFragment(retorno);

        for (Element trTd : doc.select("tr td")) {
            if(validNumber(trTd.ownText())) {
                lista1.add(trTd.ownText()); // quantidade
            }
        }

        for (Element a : doc.select("a")) {
            lista2.add(a.ownText()); // disciplina
        }

        for(int i = 0; i < 5; i++) {
            Disciplina disciplina = new Disciplina();
            disciplina.setNome(lista2.get(i));
            disciplina.setQuantidade(lista1.get(i));
            disciplinas.add(disciplina);
        }

        return disciplinas;
    }

    public static boolean validNumber(String valor) {
        if(!valor.isEmpty() && !valor.equalsIgnoreCase(null) && valor.matches("[0-9]*")) {
            return true;
        }
        return false;
    }

}
