package service;

import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import dao.EquipeDAO;
import model.Equipe;
import spark.Request;
import spark.Response;


public class EquipeService {

	private EquipeDAO equipeDAO= new EquipeDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_NOME = 2;
	private final int FORM_LIST = 4;
	
	
	
	public EquipeService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Equipe(), FORM_ORDERBY_NOME);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_LIST,new Equipe(), orderBy);
	}

	
	public void makeForm(int tipo, Equipe equipe, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umEquipe = "";
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/produto/";
			String name, nacionalidade, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Produto";
				nacionalidade = "leite, pão, ...";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + equipe.getID();
				name = "Atualizar Produto (ID " + equipe.getID() + ")";
				nacionalidade = equipe.getNacionalidade();
				buttonLabel = "Atualizar";
			}
			umEquipe += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umEquipe += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umEquipe += "\t\t<tr>";
			umEquipe += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umEquipe += "\t\t</tr>";
			umEquipe += "\t\t<tr>";
			umEquipe += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umEquipe += "\t\t</tr>";
			umEquipe += "\t\t<tr>";
			umEquipe += "\t\t\t<td>&nbsp;Descrição: <input class=\"input--register\" type=\"text\" name=\"nacionalidade\" value=\""+ nacionalidade +"\"></td>";
			
			umEquipe += "\t\t</tr>";
			umEquipe += "\t\t<tr>";
			
			umEquipe += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umEquipe += "\t\t</tr>";
			umEquipe += "\t</table>";
			umEquipe += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umEquipe += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umEquipe += "\t\t<tr>";
			umEquipe += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Produto (ID " + equipe.getID() + ")</b></font></td>";
			umEquipe += "\t\t</tr>";
			umEquipe += "\t\t<tr>";
			umEquipe += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umEquipe += "\t\t</tr>";
			umEquipe += "\t\t<tr>";
			umEquipe += "\t\t\t<td>&nbsp;Descrição: "+ equipe.getNacionalidade() +"</td>";
			
			umEquipe += "\t\t</tr>";
			umEquipe += "\t\t<tr>";
			
			umEquipe += "\t\t\t<td>&nbsp;</td>";
			umEquipe += "\t\t</tr>";
			umEquipe += "\t</table>";
		}
			else if(tipo == FORM_LIST)
			{
				
			
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-POST>", umEquipe);
		
		String list = new String("<div class=\"content\">\n"
				+ "        <section>\n"
				+ "            <div class=\"container-fluid my-5 w-75 p-3 rounded bg-dark\">\n"
				+ "                <div class=\"row\">\n"
				+ "                    <div class=\"col\">\n"
				+ "                        <div class=\"text-light text-center h1\">Equipes</div>\n"
				+ "                    </div>\n"
				+ "                </div>\n"
				+ "                <div id=\"tela\">\n");
	if(tipo == FORM_LIST)
	{
		List<Equipe> equipes;
		int pagina = orderBy;
		    equipes = equipeDAO.getList(pagina);
		   


		int i = 0;
		String bgcolor = "";
		for (Equipe p : equipes) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list +=   "                    <form>\n"
					+ "                        <div class=\"row py-4 border-top border-light\">\n"
					+ "                            <div class=\"col\">\n"
					+ "                                <div class=\"text-light h4\">"+p.getNome()+"</div>\n"
					+ "                            </div>\n"
					+ "                            <div class=\"col\">\n"
					+ "                                <div class=\"text-light h4\">"+p.getNacionalidade()+"</div>\n"
					+ "                            </div>\n"
					+ "                            <div class=\"col\">\n"
					+ "                                <button class=\"btn btn-outline-light\">Favoritar</button>\n"
					+ "                            </div>\n"
					+ "                        </div>\n"
					+ "                    </form>\n";
		
		}		
		list+= 
				 "                    <div class=\"row justify-content-center\">\n"
						+ "                        <form class=\"row py-4 w-25\">\n"
						+ "                            <div class=\"col\">\n"
						+                              " <a class=\"text-center\" href=\"/equipe/list/"+(pagina-1)+"\"<button class=\"btn btn-outline-light\"><-- </button></a>\n"
						+ "                            </div>\n"
						+ "                            <div class=\"col\">\n"
						+                               " <a class=\"text-center\" href=\"/equipe/list/"+(pagina+1)+"\"<button class=\"btn btn-outline-light\">--></button></a>\n" 
						+ "                            </div>\n"
						+ "                        </form>\n"
							+ "                    </div>\n"+
						"                </div>\n"
				+ "            </div>\n"
				+ "        </section>\n"
				+ "    </div>";
		form = form.replaceFirst("<LISTAR-POST>", list);
	}
	}
	
	
	public Object insert(Request request, Response response) {
		String nome = request.queryParams("nome");
		String nacionalidade = request.queryParams("nacionalidade");
		String resp = "";
		
		Equipe equipe = new Equipe(-1, nome, nacionalidade);
		
		if(equipeDAO.insert(equipe) == true) {
            resp = "Equipe (" + nome + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Equipe (" + nome + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Equipe equipe = (Equipe) equipeDAO.get(id);
		
		if (equipe != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, equipe, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Produto " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Equipe equipe = (Equipe) equipeDAO.get(id);
		
		if (equipe != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, equipe, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Equipe " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
		Equipe equipe = equipeDAO.get(id);
        String resp = "";       

        if (equipe != null) {
        	equipe.setNacionalidade(request.queryParams("nacionalidade"));
        	equipe.setNome(request.queryParams("nome"));

        	equipeDAO.update(equipe);
        	response.status(200); // success
            resp = "Produto (ID " + equipe.getID() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Produto (ID \" + produto.getId() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Equipe equipe = equipeDAO.get(id);
        String resp = "";       

        if (equipe != null) {
            equipeDAO.delete(id);
            response.status(200); // success
            resp = "Equipe (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Equipe (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}