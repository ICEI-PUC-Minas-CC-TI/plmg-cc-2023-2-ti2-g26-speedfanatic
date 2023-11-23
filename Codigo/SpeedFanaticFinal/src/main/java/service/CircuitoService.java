package service;

import java.util.List;
import java.util.Scanner;
import java.io.File;
import dao.CircuitoDAO;
import model.Circuito;
import model.Post;
import spark.Request;
import spark.Response;

public class CircuitoService {

    private CircuitoDAO circuitoDAO = new CircuitoDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_LIST = 4;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_DATA = 2;
	private final int FORM_ORDERBY_CATEGORIA = 3;
	
	
	public CircuitoService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Circuito(), FORM_ORDERBY_DATA, 0);
	}

	
	public void makeForm(int orderBy, int pagina) {
		makeForm(FORM_LIST, new Circuito(), orderBy, pagina);
	}

	
	public void makeForm(int tipo, Circuito circuito, int orderBy, int pagina) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umCircuito = "";
		if(tipo != FORM_INSERT && tipo != FORM_LIST) {
			umCircuito += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umCircuito += "\t\t<tr>";
			umCircuito += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/post/list/1\">Novo Post</a></b></font></td>";
			umCircuito += "\t\t</tr>";
			umCircuito += "\t</table>";
			umCircuito += "\t<br>";			
		}
		
		if (tipo == FORM_INSERT || tipo == FORM_UPDATE) {
		    String action = "/circuito/";
		    String name, nome, cidade, pais, latitude, longitude, altitude;
		    nome = "";
		    if (tipo == FORM_INSERT) {
		        action += "insert";
		        name = "Inserir Circuito";
		        nome = "Nome do Circuito";
		        cidade = "Nome da Cidade";
		        pais = "Nome do País";
		        latitude = "Latitude";
		        longitude = "Longitude";
		        altitude = "Altitude";
		    } else {
		        action += "update/" + circuito.getId();
		        name = "Atualizar Circuito (ID " + circuito.getId() + ")";
		        nome = circuito.getNome();
		        cidade = circuito.getCidade();
		        pais = circuito.getPais();
		        latitude = String.valueOf(circuito.getLatitude());
		        longitude = String.valueOf(circuito.getLongitude());
		        altitude = String.valueOf(circuito.getAltitude());
		    }
		    umCircuito += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
		    umCircuito += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
		    umCircuito += "\t\t<tr>";
		    umCircuito += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
		    umCircuito += "\t\t</tr>";
		    umCircuito += "\t\t<tr>";
		    umCircuito += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
		    umCircuito += "\t\t</tr>";
		    umCircuito += "\t\t<tr>";
		    umCircuito += "\t\t\t<td>&nbsp;" + nome + ": <input class=\"input--register\" type=\"text\" name=\"nome\" value=\"" + nome + "\"></td>";
		    umCircuito += "\t\t\t<td>" + cidade + ": <input class=\"input--register\" name=\"cidade\" value=\"" + cidade + "\"></td>";
		    umCircuito += "\t\t</tr>";
		    umCircuito += "\t\t<tr>";
		    umCircuito += "\t\t\t<td>" + pais + ": <input class=\"input--register\" type=\"text\" name=\"pais\" value=\"" + pais + "\"></td>";
		    umCircuito += "\t\t\t<td>" + latitude + ": <input class=\"input--register\" type=\"text\" name=\"latitude\" value=\"" + latitude + "\"></td>";
		    umCircuito += "\t\t</tr>";
		    umCircuito += "\t\t<tr>";
		    umCircuito += "\t\t\t<td>" + longitude + ": <input class=\"input--register\" type=\"text\" name=\"longitude\" value=\"" + longitude + "\"></td>";
		    umCircuito += "\t\t\t<td>" + altitude + ": <input class=\"input--register\" type=\"text\" name=\"altitude\" value=\"" + altitude + "\"></td>";
		    umCircuito += "\t\t</tr>";
		    umCircuito += "\t\t<tr>";
		    umCircuito += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\"a\" class=\"input--main__style input--button\"></td>";
		    umCircuito += "\t\t</tr>";
		    umCircuito += "\t</table>";
		    umCircuito += "\t</form>";
		} else if (tipo == FORM_DETAIL) {
			String name, nome, cidade, pais, latitude, longitude, altitude;
			 name = "Inserir Circuito";
		        nome = "Nome do Circuito";
		        cidade = "Nome da Cidade";
		        pais = "Nome do País";
		        latitude = "Latitude";
		        longitude = "Longitude";
		        altitude = "Altitude";
		    umCircuito += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
		    umCircuito += "\t\t<tr>";
		    umCircuito += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Circuito (ID " + circuito.getId() + ")</b></font></td>";
		    umCircuito += "\t\t</tr>";
		    umCircuito += "\t\t<tr>";
		    umCircuito += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
		    umCircuito += "\t\t</tr>";
		    umCircuito += "\t\t<tr>";
			umCircuito += "\t\t\t<td>&nbsp;" + nome + ": " + circuito.getNome() + "</td>";
		    umCircuito += "\t\t\t<td>" + cidade + ": " + circuito.getCidade() + "</td>";
		    umCircuito += "\t\t\t<td>" + pais + ": " + circuito.getPais() + "</td>";
		    umCircuito += "\t\t</tr>";
		    umCircuito += "\t\t<tr>";
		    umCircuito += "\t\t\t<td>&nbsp;" + latitude + ": " + circuito.getLatitude() + "</td>";
		    umCircuito += "\t\t\t<td>" + longitude + ": " + circuito.getLongitude() + "</td>";
		    umCircuito += "\t\t\t<td>" + altitude + ": " + circuito.getAltitude() + "</td>";
		    umCircuito += "\t\t</tr>";
		    umCircuito += "\t</table>";
		}
		    else if(tipo == FORM_LIST)
		    {
		    	String list = new String();
		    	list+=		        	"<div class=\"content\">\n"
								+ "        <section>\n"
								+ "            <div class=\"container-fluid my-5 w-75 p-3 rounded bg-dark\">\n"
								+ "                <div class=\"row\">\n"
								+ "                    <div class=\"col\">\n"
								+ "                        <div class=\"text-light text-center h1\">Circuitos</div>\n"
								+ "                    </div>\n"
								+ "                </div>\n"
								+ "                <div id=\"tela\">\n"
								+ "                    <form>\n";
		    	List<Circuito> circuitos;
				circuitos = circuitoDAO.getOrderByID(pagina);

				int i = 0;
				String bgcolor = "";
				for (Circuito c : circuitos) {
					
					list += "   <div class=\"row py-4 border-top border-light\">\n"+
							"  <div class=\"col\">\n"                           
							+ " <div class=\"text-light h4\">" +c.getNome() +"</div>\n"
							+ "                            </div>\n"
							+ "                            <div class=\"col\">\n"
							+ "                                <div class=\"text-light h4\">"+c.getCidade()+"</div>\n"
							+ "                            </div>\n"
							+ "                            <div class=\"col\">\n"
							+ "                                <div class=\"text-light h4\">"+c.getPais()+"</div>\n"
							+ "                            </div>\n"
							+ "                            <div class=\"col\">\n"
							+ "                                <div class=\"text-light h4\">Latitude: "+c.getLatitude()+"</div>\n"
							+ "                            </div>\n"
							+ "                            <div class=\"col\">\n"
							+ "                                <div class=\"text-light h4\">Longitude: "+c.getLongitude()+"</div>\n"
							+ "                            </div>\n"
							+ "                            <div class=\"col\">\n"
							+ "                                <div class=\"text-light h4\">Altitude: "+c.getAltitude()+"</div>\n"
							+ "                            </div>\n"
					        + "  </div>\n";
				}
				list +=  "                    </form>\n"
						+ "<div class=\"row justify-content-center\">\n"
						+ "                        <form class=\"row py-4 w-25\">\n"
						+ "                            <div class=\"col\">\n"
						+ "                                <a class=\"text-center\" href=\"/circuitos/list/"+(pagina-1)+"\"<button class=\"btn btn-outline-light\"><-- </button></a>\n"
						+ "                            </div>\n"
						+ "                            <div class=\"col\">\n" 	
						+ "                               <a class=\"text-center\" href=\"/circuitos/list/"+(pagina+1)+"\"<button class=\"btn btn-outline-light\">--></button></a>\n"
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
	    String nome = request.queryParams("nome");
	    String cidade = request.queryParams("cidade");
	    String pais = request.queryParams("pais");
	    double latitude = Double.parseDouble(request.queryParams("latitude"));
	    double longitude = Double.parseDouble(request.queryParams("longitude"));
	    double altitude = Double.parseDouble(request.queryParams("altitude"));

	    String resp = "";
	    if (nome == null) {
	        resp = "Nome vazio";
	        response.status(204);
	    } else {
	        Circuito circuito = new Circuito();
	        circuito.setNome(nome);
	        circuito.setCidade(cidade);
	        circuito.setPais(pais);
	        circuito.setLatitude(latitude);
	        circuito.setLongitude(longitude);
	        circuito.setAltitude(altitude);

	        if (circuitoDAO.insert(circuito)) {
	            resp = "Circuito (" + nome + ") inserido!";
	            response.status(201); // 201 Created
	        } else {
	            resp = "Circuito (" + nome + ") não inserido!";
	            response.status(404); // 404 Not Found
	        }
	    }
	    makeForm();
	    return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
	}

	
	public Object get(Request request, Response response) {
	    int id = Integer.parseInt(request.params(":id"));
	    Circuito circuito = circuitoDAO.get(id);

	    if (circuito != null) {
	        response.status(200); // success
	        makeForm(FORM_DETAIL, circuito, FORM_ORDERBY_DATA, 0);
	    } else {
	        response.status(404); // 404 Not Found
	        String resp = "Circuito " + id + " não encontrado.";
	        makeForm();
	        form = form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
	    }

	    return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
	    int id = Integer.parseInt(request.params(":id"));
	    Circuito circuito = circuitoDAO.get(id);

	    if (circuito != null) {
	        response.status(200); // success
	        makeForm(FORM_UPDATE, circuito, FORM_ORDERBY_DATA, 0);
	    } else {
	        response.status(404); // 404 Not Found
	        String resp = "Circuito " + id + " não encontrado.";
	        makeForm();
	        form = form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
	    }

	    return form;
	}

	
	
	public Object getAll(Request request, Response response) {
		int pagina = Integer.parseInt(request.params(":pagina"));
		makeForm(1, pagina);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
	    int id = Integer.parseInt(request.params(":id"));
	    Circuito circuito = circuitoDAO.get(id);
	    String resp = "";

	    if (circuito != null) {
	        circuito.setNome(request.queryParams("nome"));
	        circuito.setCidade(request.queryParams("cidade"));
	        circuito.setPais(request.queryParams("pais"));
	        circuito.setLatitude(Double.parseDouble(request.queryParams("latitude")));
	        circuito.setLongitude(Double.parseDouble(request.queryParams("longitude")));
	        circuito.setAltitude(Double.parseDouble(request.queryParams("altitude")));
	        circuitoDAO.update(circuito);
	        response.status(200); // success
	        resp = "Circuito (ID " + circuito.getId() + ") atualizado!";
	    } else {
	        response.status(404); // 404 Not Found
	        resp = "Circuito (ID " + circuito.getId() + ") não encontrado!";
	    }
	    makeForm();
	    return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
	}


	
	public Object delete(Request request, Response response) {
	    int id = Integer.parseInt(request.params(":id"));
	    Circuito circuito = circuitoDAO.get(id);
	    String resp = "";

	    if (circuito != null) {
	        circuitoDAO.delete(id);
	        response.status(200); // success
	        resp = "Circuito (ID " + circuito.getId() + ") excluído!";
	    } else {
	        response.status(404); // 404 Not Found
	        resp = "Circuito (ID " + id + ") não encontrado!";
	    }
	    makeForm();
	    return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
	}
}