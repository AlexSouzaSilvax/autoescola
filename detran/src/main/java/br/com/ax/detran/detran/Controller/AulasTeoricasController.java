package br.com.ax.detran.detran.Controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ax.detran.detran.AulasTeoricas.DetalheAulasTeoricas.DetalheAulasTeoricasService;
import br.com.ax.detran.detran.AulasTeoricas.ResumoAulasTeoricas.ResumoAulasTeoricasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Aulas Teoricas")
@CrossOrigin("*")
@RestController(value = "aulasteoricas")
@RequestMapping(name = "aulasteoricas", value = "aulasteoricas")
public class AulasTeoricasController {

	ResumoAulasTeoricasService resumoAulasTeoricasService = new ResumoAulasTeoricasService();
	DetalheAulasTeoricasService detalheAulasTeoricasService = new DetalheAulasTeoricasService();

	@ApiOperation(value = "Lista de quantidade de aulas por disciplina")
	@GetMapping("resumo")
	public ResponseEntity<Object> getQtdAulasDisciplina(@RequestParam String renach) throws IOException {
		try {
			return ResponseEntity.ok().body(resumoAulasTeoricasService
					.convertRetornoResumo(resumoAulasTeoricasService.requestResumoAulas(renach)));
		} catch (Exception e) {
			return ResponseEntity.ok().body("Nenhuma informação encontrada");
		}
	}
	
	@ApiOperation(value = "Retorna detalhe das aulas teoricas")
	@GetMapping("detalhe")
	public ResponseEntity<Object> getDetalheAulas(@RequestParam String renach) throws IOException {
		try {
			return ResponseEntity.ok().body(detalheAulasTeoricasService
					.convertRetornoDetalhe(detalheAulasTeoricasService.requestDetalheAulas(renach)));
		} catch (Exception e) {
			return ResponseEntity.ok().body("Nenhuma informação encontrada");
		}
	}

}