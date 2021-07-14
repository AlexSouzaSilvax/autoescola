package br.com.ax.detran.detran.AulasTeoricas.ResumoAulasTeoricas;

import br.com.ax.detran.detran.Util.Helper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ResumoAulasTeoricasService {

	public String requestResumoAulas(String renach) {
		try {
			Process process = Runtime.getRuntime().exec("curl -k -d \"renach=RJ" + renach
					+ "&tipo=resumo\" -H \"Content-Type: application/x-www-form-urlencoded\" -X POST https://www2.detran.rj.gov.br/portal/habilitacao/biometriaValid");
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

	public List<ResumoAulasTeoricasBean> convertRetornoResumo(String retorno) throws IOException {

		List<ResumoAulasTeoricasBean> resumoAulasTeoricasBeans = new ArrayList<>();

		List<String> lista1 = new ArrayList<>();
		List<String> lista2 = new ArrayList<>();

		Document doc = Jsoup.parseBodyFragment(retorno);

		for (Element trTd : doc.select("tr td")) {
			if (Helper.validNumber(trTd.ownText())) {
				lista1.add(trTd.ownText()); // quantidade
			}
		}

		for (Element a : doc.select("a")) {
			lista2.add(a.ownText()); // disciplina
		}

		for (int i = 0; i < 5; i++) {
			ResumoAulasTeoricasBean ResumoAulasTeoricasBean = new ResumoAulasTeoricasBean();
			ResumoAulasTeoricasBean.setNome(Helper.formatText(lista2.get(i)));
			ResumoAulasTeoricasBean.setQuantidade(lista1.get(i));
			resumoAulasTeoricasBeans.add(ResumoAulasTeoricasBean);
		}

		return resumoAulasTeoricasBeans;
	}

}
