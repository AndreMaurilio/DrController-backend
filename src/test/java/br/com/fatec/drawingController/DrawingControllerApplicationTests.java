package br.com.fatec.drawingController;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.fatec.drawingController.linha.BodyLinha;
import br.com.fatec.drawingController.linha.Linha;
import br.com.fatec.drawingController.linha.LinhaService;
import br.com.fatec.drawingController.maquete.Maquete;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrawingControllerApplicationTests {
	@Autowired
	LinhaService linhaService;
	private static final String NOME = "Elton";
	private static final Maquete maquete1 = new Maquete();

	@Test
	public void contextLoads() {

	}

	@Test
	public void savelinha() {
		maquete1.setProjetoNumero(202019L);
		Linha linha1 = new Linha();
		BodyLinha bd = new BodyLinha();

		assertEquals(linha1, linhaService.saveLinha(bd));

	}

}
