package service;

import dao.UserDAO;
import model.Piloto;
import model.Post;
import model.User;
import spark.Request;
import spark.Response;
import dao.PilotoDAO;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import dao.EquipeDAO;
import model.Equipe;

public class UserService {

    private UserDAO userDAO = new UserDAO();
    private PilotoDAO pilotoDAO = new PilotoDAO();
    private EquipeDAO equipeDAO = new EquipeDAO();
    private String form;
    private final int FORM_INSERT = 3;
    private final int FORM_UPDATE = 2;
    private final int FORM_REGISTER = 1;
    private final int FORM_EDIT = 4;
    private final int FORM_ORDERBY_ID = 1;
    private final int FORM_ORDERBY_DATA = 2;
    private final int FORM_ORDERBY_CATEGORIA = 3;

    public UserService(int i) {
        makeForm(i);
    }

    public void makeForm(int i) {
        makeForm(i, new User(), FORM_ORDERBY_DATA);
    }

    public void makeForm(int orderBy, int i) {
        makeForm(i, new User(), orderBy);
    }

    public void makeForm(int tipo, User user, int orderBy) {
    	String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umPost = "";
    	if(tipo == FORM_REGISTER) {
			String action = "/user/";
			String name, conteudo, buttonLabel, categoria;
			categoria = "";

				action += "insert";
				name = "Inserir Post";
				conteudo = "leite, pão, ...";
				
			buttonLabel = "Inserir";

			umPost += "<div class=\"content\">\n"
					+ "        <section>\n"
					+ "            <div class=\"container-fluid my-5 w-75 p-3 rounded bg-dark\">\n"
					+ "<h1>Formulário de Login</h1>\n"
					+ "        <form action=\"/user/login\" method=\"post\" class=\"row justify-content-center\">\n"
					+ "            <label color= \"white\" for=\"email\">Email:</label>\n"
					+ "            <input type=\"text\"class=\"form-control w-75 mb-3\" id=\"email\" name=\"email\" placeholder=\"email\" required>\n"
					+ "            <br>\n"
					+ "            <label for=\"senha\">Senha:</label>\n"
					+ "            <input type=\"password\" id=\"senha\" class=\"form-control w-75 mb-3\"name=\"senha\" placeholder=\"senha\" required>\n"
					+ "            <br>\n"
					+ "            <input  class=\"btn btn-outline-light w-50 mb-3\" type=\"submit\" value=\"Entrar\">\n"
                    + "            <div class=\"text-center text-light\">Não tem conta?</div>\n"
                    + "            <a class=\"text-center\" href=\"/user/pagina/3\">Cadastre-se</a>";
					     
		} 	
    	else if(tipo == FORM_INSERT)
    	{
    		String action = "/user/";
			String name, conteudo, buttonLabel, categoria;
			categoria = "";

				action += "insert";
				name = "Inserir Post";
				conteudo = "leite, pão, ...";
				
			buttonLabel = "Inserir";
			umPost += "<div class=\"content\">\n"
					+ "        <section>\n"
					+ "            <div class=\"container-fluid my-5 w-75 p-3 rounded bg-dark\">\n"
					+ "<h1>Formulário de Login</h1>\n"
					+ "        <form action=\"/user/register\" method=\"post\" class=\"row justify-content-center\">\n"
					+ "            <label color= \"white\" for=\"email\">Email:</label>\n"
					+ "            <input type=\"text\"class=\"form-control w-75 mb-3\" id=\"email\" name=\"email\" placeholder=\"email\" required>\n"
					+ "            <br>\n"
					+ " <label for=\"nome\">Nome:</label>\n"
								+ "            <input type=\"text\" id=\"nome\" class=\"form-control w-75 mb-3\"name=\"nome\" placeholder=\"nome\" required>\n"
								+ "            <br>\n"
					+ "            <label for=\"senha\">Senha:</label>\n"
					+ "            <input type=\"password\" id=\"senha\" class=\"form-control w-75 mb-3\"name=\"senha\" placeholder=\"senha\" required>\n"
					+ "            <br>\n"
					+ "            <input  class=\"btn btn-outline-light w-50 mb-3\" type=\"submit\" value=\"Entrar\">\n"
                    + "            <div class=\"text-center text-light\">Ja possui uma conta?</div>\n"
                    + "            <a class=\"text-center\" href=\"/user/pagina/1\">Sign-in</a>";
					     
    	}
    	else if(tipo == FORM_UPDATE)
    	{
    		String buttonLabel = "atualizar";
    		String action = "/perfil/";
    		String name = "Atualizar";
            action+= "update";
            umPost+= " <div class=\"content\">\n"
            		+ "        <div class=\"container-fluid my-5 w-75 p-3 rounded bg-dark\">\n"
            		+ "            <div class=\"row py-1\">\n"
            		+ "                <div class=\"col\">\n"
            		+ "                    <div class=\"text-light text-center h1\">Perfil</div>\n"
            		+ "                </div>\n"
            		+ "            </div>\n"
            		+ "            <div id=\"tela\">\n"
            		+ "                <div class=\"row py-4 border-top border-light\">\n"
            		+ "                    <div class=\"col\">\n"
            		+ "                        <div class=\"row\">\n"
            		+ "                            <div class=\"text-light h3\">Nome: "+ user.getUsername() + "</div>\n"
            		+ "                        </div>\n"
            		+ "                        <div class=\"row\">\n"
            		+ "                            <div class=\"text-light h4\">Email: "+ user.getEmail()+ "</div>\n"
            		+ "                        </div>\n"
            		+ "                    </div>\n"
            		+ "                    <div class=\"col\">\n"
            		+ "                        <div class=\"text-light\">Data de Criação: "+ user.getDataCriacao()+"</div>\n"
            		+ "                    </div>\n"
            		+ "                    <form class=\"row justify-content-center\">\n"
            		+ "                     <a href=\"/user/edit\" <button class=\"btn btn-outline-light w-25\" id=\"btn-cadastrar\">Editar</button></a>\n"
            		+ "                    </form>\n"
            		+ "                </div>\n"
            		+ "                <div class=\"row py-4 border-top border-light\">\n"
            		+ "                    <div class=\"col\">\n"
            		+ "                        <div class=\"text-light text-center h2\">Favoritos</div>\n"
            		+ "                    </div>\n"
            		+ "                </div>\n"
            		+ "                <div class=\"row py-4 border-top border-light\">\n"
            		+ "                    <div class=\"row\">\n"
            		+ "                        <div class=\"text-light h4\">Piloto: "+ pilotoDAO.get(user.getPiloto())+"</div>\n"
            		+ "                    </div>\n"
            		+ "                    <div class=\"row\">\n"
            		+ "                        <div class=\"text-light h4\">Equipe "+ equipeDAO.get(user.getEquipe())+"</div>\n"
            		+ "                    </div>\n"
            		+ "                </div>\n"
            		+ "            </div>\n"
            		+ "        </div>\n"
            		+ "    </div>\n"
            		+ "\n";
    	}
    	else if(tipo == FORM_EDIT)
    	{
    	  umPost += "<div class=\"content\">\n"
    	  		+ "        <section>\n"
    	  		+ "            <div class=\"container-fluid my-5 w-75 p-3 rounded bg-dark\">\n"
    	  		+ "                <form action=\"/perfil/update\" method=\"post\" class=\"row justify-content-center\">\n"
    	  		+ "                    <div class=\"text-center text-light h1\">Editar Perfil</div>\n"
    	  		+ "                    <input type=\"email\" class=\"form-control w-75 mb-3\" id=\"email\" name=\"email\" placeholder=\"Email\">\n"
    	  		+ "                    <input type=\"password\" class=\"form-control w-75 mb-3\" id=\"senha\" name=\"senha\" placeholder=\"Senha\">\n"
    	  		+ "                    <input type=\"submit\" value=\"Confirmar\" <button class=\"btn btn-outline-light w-50 mb-3\" id=\"btn-cadastrar\">Confirmar</button>n\">"
    	  		+ "                </form>\n"
    	  		+ "            </div>\n"
    	  		+ "        </section>\n"
    	  		+ "    </div>";
    	}
    	
		 else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-POST>", umPost);		
	}
    

    public Object insert(Request request, Response response) {
        String username = request.queryParams("nome");
       String email = request.queryParams("email");
        String senha = request.queryParams("senha");
       // LocalDate dataCriacao = LocalDate.parse(request.queryParams("dataCriacao"));
       // int piloto = Integer.parseInt(request.queryParams("piloto"));
       // int equipe = Integer.parseInt(request.queryParams("equipe"));

        String resp = "";

        //User user = new User(1, username, email, senha, dataCriacao, piloto, equipe);
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        senha = criptografarSenhaMD5(senha);
        System.out.println(senha);
        user.setSenha(senha);
        User tmp = userDAO.authenticate(user.getUsername(), user.getSenha());
        if(tmp != null)
        {
            resp = "Nome de usuario ja existente";
            response.status(404);
        }
        else
        {
        if (userDAO.insert(user)) {
            resp = "Usuário (" + email + ") inserido!";
            response.status(201); // 201 Created
            response.redirect("/forum");
        } else {
            resp = "Usuário (" + email + ") não inserido!";
            response.status(404); // 404 Not found
        }
        }
        makeForm(3);
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
    }
    private static String criptografarSenhaMD5(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(senha.getBytes());
            byte[] digest = md.digest();

            // Converte o hash MD5 para uma representação hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                hexString.append(String.format("%02x", b & 0xff));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao criar hash MD5", e);
        }
    }
    public Object updatePiloto(Request request, Response response) {
        User user = userDAO.get(request.session().attribute("user_id"));
        int id = Integer.parseInt(request.queryParams("id"));
        System.out.println("id: "+ id+ "Piloto: "+ user.getPiloto());
        String resp = "";
        
        if (user != null) {
        	user.setPiloto(id);
        	
            userDAO.update(user);
            System.out.println("Nota: "+ user.getPiloto());
            response.status(200); // 200 OK
            resp = "Usuário (ID " + user.getID() + ") atualizado!";
            response.redirect("/forum");
        } else {
            response.status(404); // 404 Not Found
            resp = "Usuário (ID " + user.getID() + ") não encontrado!";
            response.redirect("/perfil");
        }

        makeForm(FORM_UPDATE);
        return 	form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
    }
    public Object login(Request request, Response response) {
        String username = request.queryParams("email");
        String senha = request.queryParams("senha");
       System.out.println(username);
       System.out.println(senha);
        String resp = "";
        senha = criptografarSenhaMD5(senha);
        System.out.println(senha);
        // Verifique se o usuário com as credenciais fornecidas existe no banco de dados
        User user = userDAO.authenticate(username, senha, request);

        if (user != null) {
            resp = "Login bem-sucedido para o usuário " + username;
            response.status(200); // 200 OK
            response.redirect("/perfil");
        } else {
            resp = "Falha no login. Verifique suas credenciais.";
            response.status(401); // 401 Unauthorized
         
        }

        makeForm(1);
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
    }

    public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}	
    public Object updateNota(Request request, Response response) {
        User user = userDAO.get(request.session().attribute("user_id"));
        String resp = "";

        if (user != null) {
            user.setNota(request.session().attribute("user_nota"));
            userDAO.update(user);
            System.out.println("Nota: "+ user.getNota());
            response.status(200); // 200 OK
            resp = "Usuário (ID " + user.getID() + ") atualizado!";
            response.redirect("/post/list/1");
        } else {
            response.status(404); // 404 Not Found
            resp = "Usuário (ID " + user.getID() + ") não encontrado!";
        }

        makeForm(FORM_UPDATE);
        return 	form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
    }
    public Object update(Request request, Response response) {
        User user = userDAO.get(request.session().attribute("user_id"));
        String resp = "";

        if (user != null) {
            user.setEmail(request.queryParams("email"));
            user.setSenha(criptografarSenhaMD5(request.queryParams("senha")));
            user.setNota(request.session().attribute("user_nota"));
          //  user.setPiloto(Integer.parseInt(request.queryParams("piloto")));
          //  user.setEquipe(Integer.parseInt(request.queryParams("equipe")));
            userDAO.update(user);
            response.status(200); // 200 OK
            resp = "Usuário (ID " + user.getID() + ") atualizado!";
            response.redirect("/post/list/1");
        } else {
            response.status(404); // 404 Not Found
            resp = "Usuário (ID " + user.getID() + ") não encontrado!";
        }

        makeForm(FORM_UPDATE);
        return 	form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
    }
    public Object getToUpdate(Request request, Response response) {
        
        User user = userDAO.get(request.session().attribute("user_id"));

        if (user != null) {
            response.status(200); // success
            makeForm(FORM_UPDATE, user, FORM_ORDERBY_DATA);
        } else {
            response.status(404); // 404 Not found
            response.redirect("/user/pagina/1");
            String resp = "Usuário " + request.session().attribute("user_id") + " não encontrado.";
            makeForm(FORM_UPDATE);
            form = form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
        }

        return form;
    }
public Object getToEdit(Request request, Response response) {
        
        User user = userDAO.get(request.session().attribute("user_id"));

        if (user != null) {
            response.status(200); // success
            makeForm(FORM_EDIT, user, FORM_ORDERBY_DATA);
        } else {
            response.status(404); // 404 Not found
            response.redirect("/user/pagina/1");
            String resp = "Usuário " + request.session().attribute("user_id") + " não encontrado.";
            makeForm(FORM_UPDATE);
            form = form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
        }

        return form;
    }

}