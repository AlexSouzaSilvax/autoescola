package br.com.ax.detran.detran.AulasTeoricas.DetalheAulasTeoricas;

import br.com.ax.detran.detran.Util.Helper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DetalheAulasTeoricasService {

	public String requestDetalheAulas(String renach) {
		try {
			Process process = Runtime.getRuntime().exec("curl -k -d \"renach=RJ" + renach
					+ "&disciplina=TEORICAS&tipo=detalhesTeorico\" -H \"Content-Type: application/x-www-form-urlencoded\" -X POST https://www2.detran.rj.gov.br/portal/habilitacao/biometriaValid");
			StringBuilder output = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			int exitVal = process.waitFor();
			if (exitVal == 0) {
				return output.toString();
			} else {
				return "";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "";
	}

	public List<DetalheAulasTeoricasBean> convertRetornoDetalhe(String retorno) {

		List<DetalheAulasTeoricasBean> detalheAulasTeoricasBeans = new ArrayList<DetalheAulasTeoricasBean>();
		List<String> lista1 = new ArrayList<>();

		Document doc = Jsoup.parseBodyFragment(retorno);

		for (Element trTd : doc.select("tr td")) {
			lista1.add(Helper.formatText(trTd.ownText()));
		}

		for (int i = 0; i < lista1.size(); i++) {
			DetalheAulasTeoricasBean detalheAulasTeoricasBean = new DetalheAulasTeoricasBean();

			detalheAulasTeoricasBean.setData(lista1.get(0));
			lista1.remove(0);
			detalheAulasTeoricasBean.setInicio(lista1.get(0));
			lista1.remove(0);
			detalheAulasTeoricasBean.setFim(lista1.get(0));
			lista1.remove(0);
			detalheAulasTeoricasBean.setDisciplina(lista1.get(0));
			lista1.remove(0);
			detalheAulasTeoricasBean.setDataEnvio(lista1.get(0));
			lista1.remove(0);
			detalheAulasTeoricasBean.setStatus(lista1.get(0));
			lista1.remove(0);
			detalheAulasTeoricasBeans.add(detalheAulasTeoricasBean);
		}
		//Collections.sort(detalheAulasTeoricasBeans);
		return detalheAulasTeoricasBeans;
	}

}
