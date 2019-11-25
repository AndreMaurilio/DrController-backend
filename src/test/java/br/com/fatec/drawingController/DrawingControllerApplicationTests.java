package br.com.fatec.drawingController;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.fatec.drawingController.linha.BodyLinha;
import br.com.fatec.drawingController.linha.Linha;
import br.com.fatec.drawingController.linha.LinhaService;
import br.com.fatec.drawingController.maquete.Maquete;
import br.com.fatec.drawingController.security.JwtUtils;
import br.com.fatec.drawingController.security.Login;
import br.com.fatec.drawingController.usuario.Usuario;
import br.com.fatec.drawingController.usuario.UsuarioService;
import javax.servlet.http.HttpServletResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrawingControllerApplicationTests {
	@Autowired
	LinhaService linhaService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	private AuthenticationManager auth;

	public void setAuth(AuthenticationManager auth) {
		this.auth = auth;
	}

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

	@Test
	public void authorityUser() {
		Login login = new Login();
		login.setUsername("user@ig.com");
		login.setPassword("senha");
		Authentication credentials = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
		Usuario usuario = (Usuario) auth.authenticate(credentials).getPrincipal();
		String str = "ROLE_ADMIN";
		assertEquals(str, usuario.getAutorizacoes().get(0).getAuthority());

	}

}
