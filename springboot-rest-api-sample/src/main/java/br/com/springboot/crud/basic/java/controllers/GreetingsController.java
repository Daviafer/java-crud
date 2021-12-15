package br.com.springboot.crud.basic.java.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.crud.basic.java.model.Usuario;
import br.com.springboot.crud.basic.java.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 * Intercepta todas as requisições do mapeamento(celular, pc etc)
 */
@RestController
public class GreetingsController {
	
	@Autowired		//IC/CD ou CDI - Injeção de dependência
	private UsuarioRepository usuarioRepository;

    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Curso SpringBoot API " + name + "!";
    }
    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);

    	usuarioRepository.save(usuario);    	 // gravar no banco de dados
        return "Olá mundo " + nome;
    }
    @GetMapping(value = "listatodos")
    @ResponseBody	// Retorna os dados para o corpo da resposta
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	List<Usuario> usuarios = usuarioRepository.findAll(); // executa a consulta no banco de dados
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); // retorna a lista em JSON
    	
    }
}
