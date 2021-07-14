package br.com.ax.detran.detran.AulasTeoricas.ResumoAulasTeoricas;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ax.detran.detran.AulasTeoricas.ResumoAulasTeoricas.ResumoAulasTeoricasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Api(value = "Aulas Teoricas")
@CrossOrigin("*")
@RestController(value = "aulasteoricas")
@RequestMapping(name = "aulasteoricas", value = "aulasteoricas")
public class ResumoAulasTeoricasController {

	ResumoAulasTeoricasService resumoAulasTeoricasService = new ResumoAulasTeoricasService();

	@ApiOperation(value = "Lista de quantidade de aulas por disciplina")
	@GetMapping("resumo")
	public ResponseEntity<Object> getSolicitacaoReembolso(@RequestParam String renach) throws IOException {
		try {
			return ResponseEntity.ok().body(resumoAulasTeoricasService
					.convertRetornoResumo(resumoAulasTeoricasService.requestResumoAulas(renach)));
		} catch (Exception e) {
			return ResponseEntity.ok().body("Nenhuma informação encontrada");
		}
	}

}
