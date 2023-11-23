	package app;
	
	import static spark.Spark.*;
	import com.google.gson.*;

	import com.microsoft.azure.cognitiveservices.vision.contentmoderator.*;
	import com.microsoft.azure.cognitiveservices.vision.contentmoderator.models.*;

	import java.io.*;
	import java.util.*;
	import java.util.concurrent.*;
	import org.apache.http.HttpEntity;
	import org.apache.http.HttpResponse;
	import org.apache.http.client.HttpClient;
	import org.apache.http.client.methods.HttpPost;
	import org.apache.http.client.utils.URIBuilder;
	import org.apache.http.entity.StringEntity;
	import org.apache.http.impl.client.HttpClients;
	import org.apache.http.util.EntityUtils;
	import service.RespostaService;
	import service.CircuitoService;
	import service.EquipeService;
	import service.PilotoService;
	import service.PostService;
	import service.UserService; // Importe o UserService para corrigir a referência ausente
	import spark.Request;
	
	
	public class Aplicacao {
	    
	    private static PostService postService = new PostService();
	    
	    private static UserService userService = new UserService(1);
	    
	    private static RespostaService respostaService = new RespostaService();
	    
	    private static  CircuitoService circuitoService = new CircuitoService();
	    
	    private static EquipeService equipeService = new EquipeService();
	    
	    private static PilotoService pilotoService = new PilotoService();
	    
	    private static final String subscriptionKey = "270d010b411140d68c67f46ac838e902";
	    private static final String endpoint = "https://moderadorforum.cognitiveservices.azure.com/contentmoderator/moderate/v1.0/ProcessText/Screen";
	    private static File textFile = new File("src/main/resources/TextModeration.txt");
	    
	    private static ContentModeratorClient client = ContentModeratorManager.authenticate(AzureRegionBaseUrl.fromString(endpoint),
	            subscriptionKey);
	      
	    private static boolean usuarioEstaAutenticado(Request request) {
	        
	        
	        Object usuarioAutenticado = request.session().attribute("usuarioAutenticado");
	        System.out.println(usuarioAutenticado);
	
	        return usuarioAutenticado != null;
	    }
	   

	    public static void main(String[] args) {
	        port(6789);
	        
	        staticFiles.location("/public");
	        
	        
	        post("/piloto/insert", (request, response) -> pilotoService.insert(request, response)); 
	
	        get("/piloto/:id", (request, response) -> pilotoService.get(request, response)); 
	        
	        get("/piloto/list/:pagina", (request, response) -> pilotoService.getAll(request, response)); 
	
	        get("/piloto/update/:id", (request, response) -> pilotoService.getToUpdate(request, response)); 
	        
	        post("/piloto/update/:id", (request, response) -> pilotoService.update(request, response));
	           
	        get("/piloto/delete/:id", (request, response) -> pilotoService.delete(request, response)); 
	    
			post("/equipe/insert", (request, response) -> equipeService.insert(request, response));
	
	        get("/equipe/:id", (request, response) -> equipeService.get(request, response));
	        
	        get("/equipe/list/:orderby", (request, response) -> equipeService.getAll	(request, response));
	
	        get("/equipe/update/:id", (request, response) -> equipeService.getToUpdate(request, response));
	        
	        post("/equipe/update/:id", (request, response) -> equipeService.update(request, response));
	           
	        get("/equipe/delete/:id", (request, response) -> equipeService.delete(request, response));
	        
	        
	
	        
	        
	        
	        before("/post/insert", (request, response) -> {
	            if (!usuarioEstaAutenticado(request)) {
	                response.redirect("/user/pagina/1"); // Redireciona para a página de login se o usuário não estiver autenticado
	            }
	        });
	     /*   before("/resposta/insert", (request, response) -> {
	            if (!usuarioEstaAutenticado(request)) {
	                response.redirect("/user/pagina/1"); // Redireciona para a página de login se o usuário não estiver autenticado
	            }
	        });*/
	        before("/post/list:orderby", (request, response) -> {
	            if (!usuarioEstaAutenticado(request)) {
	                response.redirect("/user/pagina/1"); // Redireciona para a página de login se o usuário não estiver autenticado
	            }
	        });
	
	        post("/post/insert", (request, response) -> {
	            // Esta rota agora está protegida e só será acessível para usuários autenticados
	            return postService.insert(request, response);
	        });	
	        get("/post/:id", (request, response) ->
	        {
	        String a =(String) postService.getChain(request, response);
	        a = (String) respostaService.getRespostas(request,response, a);
	        return a;
	        });
	        get("/post/list/:orderby", (request, response) -> postService.getAll(request, response));
	        get("/post/update/:id", (request, response) -> postService.getToUpdate(request, response));
	        post("/post/update/:id", (request, response) -> postService.update(request, response));
	        get("/post/delete/:id", (request, response) -> postService.delete(request, response));
	        get("/forum", (request,response)-> postService.getHomePage(request, response));
	        
	        
	        
	        // Corrigir o mapeamento das rotas para UserService
	        post("/user/register", (request, response) -> userService.insert(request, response));
	        get("/user/pagina/:orderby", (request, response) -> userService.getAll(request, response));
	        post("/user/login", (request, response) -> userService.login(request,response));
	        get("/user/edit", (request, response) -> userService.getToEdit(request, response));
	        before("/perfil", (request, response) -> {
	            if (!usuarioEstaAutenticado(request)) {
	                response.redirect("/user/pagina/1"); // Redireciona para a página de login se o usuário não estiver autenticado
	            }
	        });
	        get("/perfil", (request, response) -> userService.getToUpdate(request, response));
	       // post("/user/update/:id", (request, response) -> userService.update(request,response));
	        post("/perfil/update", (request, response) -> userService.update(request, response)); 
	        post("/forum/1", (request, response) -> userService.updateNota(request, response)); 
	        post("/resposta/insert", (req, res) -> respostaService.insert(req, res));
	      
	   /*         String textoResposta = req.body();
	            System.out.println(textoResposta);
	
	            // Verifica se o texto é válido usando o Azure Content Moderator
	            boolean textoAprovado = verificarTextoModerador(textoResposta);
	
	            // Se o texto for aprovado, insere a resposta no serviço
	            if (textoAprovado) {
	            	System.out.println(req.body());
	                respostaService.insert(req, res);
	                res.redirect("/forum");  // Redireciona para uma página de sucesso
	            } else {
	                res.redirect("/forum");  // Redireciona para uma página de erro
	            }
	
	            return null;  // Indica que a resposta já foi tratada
	        });*/
	        
	        get("/post/:id", (request, response) -> respostaService.getAll(request, response));
			get("/circuitos/list/:pagina", (request,response) -> circuitoService.getAll(request,response));
			
	    }
	}