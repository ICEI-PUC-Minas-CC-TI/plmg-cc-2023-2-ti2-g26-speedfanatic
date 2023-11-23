package service;

import java.util.List;
import java.util.Scanner;
import java.io.File;
import dao.PilotoDAO;
import model.Piloto;
import model.Post;
import spark.Request;
import spark.Response;

public class PilotoService {

    private PilotoDAO pilotoDAO = new PilotoDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_LIST = 4;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_DATA = 2;
	private final int FORM_ORDERBY_CATEGORIA = 3;
	
	
	public PilotoService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Piloto(), FORM_ORDERBY_DATA, 0);
	}

	
	public void makeForm(int orderBy, int pagina) {
		makeForm(FORM_LIST, new Piloto(), orderBy, pagina);
	}

	
	public void makeForm(int tipo, Piloto piloto, int orderBy, int pagina) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umPiloto = "";
		if(tipo != FORM_INSERT && tipo != FORM_LIST) {
			umPiloto += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umPiloto += "\t\t<tr>";
			umPiloto += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/post/list/1\">Novo Post</a></b></font></td>";
			umPiloto += "\t\t</tr>";
			umPiloto += "\t</table>";
			umPiloto += "\t<br>";			
		}
		
		if (tipo == FORM_INSERT || tipo == FORM_UPDATE) {
		    String action = "/piloto/";
		    String name, nome, pais, abreviacao, sobrenome;
			int numero;
		    nome = "";
	
		 
		        action += "update/" + piloto.getId();
		        name = "Atualizar Piloto (ID " + piloto.getId() + ")";
		        nome = piloto.getNome();
		        numero = piloto.getNumero();
		        pais = piloto.getPais();
		        abreviacao= String.valueOf(piloto.getAbreviacao());
		        sobrenome = String.valueOf(piloto.getSobrenome());
		}  	 

		    else if(tipo == FORM_LIST)
		    {
		    	String list = new String();
		    	list+=		        	"<div class=\"content\">\n"
								+ "        <section>\n"
								+ "            <div class=\"container-fluid my-5 w-75 p-3 rounded bg-dark\">\n"
								+ "                <div class=\"row\">\n"
								+ "                    <div class=\"col\">\n"
								+ "                        <div class=\"text-light text-center h1\">Pilotos</div>\n"
								+ "                    </div>\n"
								+ "                </div>\n"
								+ "                <div id=\"tela\">\n"
								+ "                    <form>\n";
		    	List<Piloto> pilotos;
				pilotos = pilotoDAO.getList(pagina);

				int i = 0;
				String bgcolor = "";
				for (Piloto c : pilotos) {
					String abreviacao= c.getAbreviacao();
					if(abreviacao==null)
					{
						c.setAbreviacao("nao possui");
					}
			       
					list += "     <form>\n"
							+ "                        <div class=\"row py-4 border-top border-light\">\n"
							+ "                            <div class=\"col\">\n"
							+ "                                <div class=\"text-light h4\">"+c.getNumero()+"</div>\n"
							+ "                            </div>\n"
							+ "                            <div class=\"col\">\n"
							+ "                                <div class=\"text-light h4\">"+abreviacao+"</div>\n"
							+ "                            </div>\n"
							+ "                            <div class=\"col\">\n"
							+ "                                <div class=\"text-light h4\">"+c.getNome()+" "+c.getSobrenome()+"</div>\n"
							+ "                            </div>\n"
							+ "                            <div class=\"col\">\n"
							+ "                                <div class=\"text-light h4\">"+c.getPais()+"</div>\n"
							+ "                            </div>\n"
							+ "                            <div class=\"col\">\n"
							+ "                                <button class=\"btn btn-outline-light\">Favoritar</button>\n"
							+ "                            </div>\n"
							+ "                        </div>\n";
				}
				list +=  "                    </form>\n"
						+ "<div class=\"row justify-content-center\">\n"
						+ "                        <form class=\"row py-4 w-25\">\n"
						+ "                            <div class=\"col\">\n"
						+ "                                <a class=\"text-center\" href=\"/piloto/list/"+(pagina-1)+"\"<button class=\"btn btn-outline-light\"><-- </button></a>\n"
						+ "                            </div>\n"
						+ "                            <div class=\"col\">\n" 	
						+ "                               <a class=\"text-center\" href=\"/piloto/list/"+(pagina+1)+"\"<button class=\"btn btn-outline-light\">--></button></a>\n"
						+ "                            </div>\n"
						+ "                        </form>\n"
						+ "                    </div>\n"
						+ "                </div>\n"
						+ "            </div>\n"
						+ "        </section>\n"
						+ "    </div>";
				form = form.replaceFirst("<LISTAR-POST>", list);				
			}
		    	
		 else {
		    System.out.println("ERRO! Tipo não identificado " + tipo);
		}

	}

	
	public Object insert(Request request, Response response) {
	    String nome = request.queryParams("piloto_nome");
	    String sobrenome = request.queryParams("piloto_sobrenome");
	    String pais = request.queryParams("piloto_pais");
	    int numero = Integer.parseInt(request.queryParams("piloto_numero"));
	    String abreviacao = request.queryParams("piloto_abreviacao");

	    String resp = "";
	    if (nome == null) {
	        resp = "Nome vazio";
	        response.status(204);
	    } else {
	        Piloto piloto = new Piloto();
	        piloto.setNome(nome);
	        piloto.setSobrenome(sobrenome);
	        piloto.setPais(pais);
	        piloto.setNumero(numero);
	        piloto.setAbreviacao(abreviacao);

	        if (pilotoDAO.insert(piloto)) {
	            resp = "Piloto (" + nome + ") inserido!";
	            response.status(201); // 201 Created
	        } else {
	            resp = "Piloto (" + nome + ") não inserido!";
	            response.status(404); // 404 Not Found
	        }
	    }
	    makeForm();
	    return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
	}

	public Object get(Request request, Response response) {
	    int id = Integer.parseInt(request.params(":id"));
	    Piloto piloto = pilotoDAO.get(id);

	    if (piloto != null) {
	        response.status(200); // success
	        makeForm(FORM_DETAIL, piloto, FORM_ORDERBY_DATA, 0);
	    } else {
	        response.status(404); // 404 Not Found
	        String resp = "Piloto " + id + " não encontrado.";
	        makeForm();
	        form = form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
	    }

	    return form;
	}

	public Object getToUpdate(Request request, Response response) {
	    int id = Integer.parseInt(request.params(":id"));
	    Piloto piloto = pilotoDAO.get(id);

	    if (piloto != null) {
	        response.status(200); // success
	        makeForm(FORM_UPDATE, piloto, FORM_ORDERBY_DATA, 0);
	    } else {
	        response.status(404); // 404 Not Found
	        String resp = "Piloto " + id + " não encontrado.";
	        makeForm();
	        form = form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
	    }

	    return form;
	}

	public Object getAll(Request request, Response response) {
	    int pagina = Integer.parseInt(request.params(":pagina"));
	    makeForm(FORM_LIST, pagina);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
	    return form;
	}

	public Object update(Request request, Response response) {
	    int id = Integer.parseInt(request.params(":id"));
	    Piloto piloto = pilotoDAO.get(id);
	    String resp = "";

	    if (piloto != null) {
	        piloto.setNome(request.queryParams("piloto_nome"));
	        piloto.setSobrenome(request.queryParams("piloto_sobrenome"));
	        piloto.setPais(request.queryParams("piloto_pais"));
	        piloto.setNumero(Integer.parseInt(request.queryParams("piloto_numero")));
	        piloto.setAbreviacao(request.queryParams("piloto_abreviacao"));
	        pilotoDAO.update(piloto);
	        response.status(200); // success
	        resp = "Piloto (ID " + piloto.getId() + ") atualizado!";
	    } else {
	        response.status(404); // 404 Not Found
	        resp = "Piloto (ID " + piloto.getId() + ") não encontrado!";
	    }
	    makeForm();
	    return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
	}

	public Object delete(Request request, Response response) {
	    int id = Integer.parseInt(request.params(":id"));
	    Piloto piloto = pilotoDAO.get(id);
	    String resp = "";

	    if (piloto != null) {
	        pilotoDAO.delete(id);
	        response.status(200); // success
	        resp = "Piloto (ID " + piloto.getId() + ") excluído!";
	    } else {
	        response.status(404); // 404 Not Found
	        resp = "Piloto (ID " + id + ") não encontrado!";
	    }
	    makeForm();
	    return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
	}
}
