package service;

import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.time.LocalDate;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import dao.PostDAO;
import model.Post;
import model.Resposta;
import model.User;
import spark.Request;
import spark.Response;
import dao.UserDAO;


public class PostService {

	private PostDAO postDAO = new PostDAO();
	private UserDAO userDAO = new UserDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_FORUM = 4;
	private final int FORM_LIST = 5;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_DATA = 2;
	private final int FORM_ORDERBY_CATEGORIA = 3;
	
	
	public PostService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Post(), FORM_ORDERBY_DATA);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_LIST, new Post(), orderBy);
	}
    public void makeFormHp(int FORM_FORUM)
    {
    	makeForm(FORM_FORUM, new Post(), 1);
    }
	
	public void makeForm(int tipo, Post post, int orderBy) {
		String nomeArquivo = "Forum.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umPost = "";
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/post/";
			String name, conteudo, buttonLabel, categoria;
			categoria = "";
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Post";
				conteudo = "leite, pão, ...";
				buttonLabel = "Inserir";
				if(post.getCategoria() == 1)
				{
				  categoria = "Corrida";
				}
				else if(post.getCategoria() == 2)
				{
				 categoria = "Piloto";
				}
			} else {
				action += "update/" + post.getID();
				name = "Atualizar Post (ID " + post.getID() + ")";
				conteudo = post.getConteudo();
				buttonLabel = "Atualizar";
			}
			umPost += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umPost += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umPost += "\t\t<tr>";
			umPost += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umPost += "\t\t</tr>";
			umPost += "\t\t<tr>";
			umPost += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umPost += "\t\t</tr>";
			umPost += "\t\t<tr>";
			umPost += "\t\t\t<td>&nbsp;Conteudo: <input class=\"input--register\" type=\"text\" name=\"conteudo\" value=\""+ conteudo +"\"></td>";
			umPost += "\t\t\t<td>Categoria: <input class=\"input--register\" name=\"categoria\" value=\""+ categoria +"\"></td>";
			umPost += "\t\t</tr>";
			umPost += "\t\t<tr>";
			umPost += "\t\t\t<td>Data de validade: <input class=\"input--register\" type=\"text\" name=\"data\" value=\""+ post.getData().toString() + "\"></td>";
			umPost += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umPost += "\t\t</tr>";
			umPost += "\t</table>";
			umPost += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umPost += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umPost += "\t\t<tr>";
			umPost += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Post (ID " + post.getID() + ")</b></font></td>";
			umPost += "\t\t</tr>";
			umPost += "\t\t<tr>";
			umPost += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umPost += "\t\t</tr>";
			umPost += "\t\t<tr>";
			umPost += "\t\t\t<td>&nbsp;Conteudo: "+ post.getConteudo() +"</td>";
			umPost += "\t\t\t<td>Categoria: "+ post.getCategoria() +"</td>";
			umPost += "\t\t\t<td>Usuario: "+ post.getUsuario() +"</td>";
			umPost += "\t\t</tr>";
			umPost += "\t\t<tr>";
			umPost += "\t\t\t<td>Data de validade: "+ post.getData().toString() + "</td>";
			umPost += "\t\t\t<td>&nbsp;</td>";
			umPost += "\t\t</tr>";
			umPost += "\t</table>";		
		}else if( tipo == FORM_FORUM)
			{
			String tmp[] = new String[10];
			Post p[] = new Post[10];
			for(int i =0; i<10; i++)
			{
			  tmp[i]= Integer.toString(postDAO.getInicial(i+2));	
			  p[i] = postDAO.getLastPost(i+2);
			}
       			  umPost+= "  <div class=\"forum-container\">\n"
       			  		+ "                <div class=\"criar-assunto\">\n"
       			  		+ "                    <button  class=\"btn btn-outline-light w-50 mb-3 \" id=\"new-topic-button\">Inserir Novo Tópico</button>\n"
       			  		+ "                    <div id=\"topic-input\" style=\"display: none\">\n"
       			  	    +"                       <form action=\"/post/insert\" method=\"post\" class=\"row justify-content-center\">\n"
       			  	    + "			            <input type=\"text\"class=\"form-control w-75 mb-3\" id=\"conteudo\" name=\"conteudo\" required>\n"
       			  	    + "					           <br>\n"
       			  		+ "                        <select class=\"form-control w-75 mb-3\" id=\"categoria\" name=\"categoria\">\n"
       			  		+ "                            <option value=\"2\">Assuntos diversos</option>\n"
       			  		+ "                            <option value=\"3\">Estreantes Promissores</option>\n"
       			  		+ "                            <option value=\"4\">Pilotos Lendários</option>\n"
       			  		+ "                            <option value=\"5\">Técnicas de Pilotagem</option>\n"
       			  		+ "                            <option value=\"6\">Rivalidades Memoráveis</option>\n"
       			  		+ "                            <option value=\"7\">Pilotos Contemporâneos</option>\n"
       			  		+ "                            <option value=\"8\">Circuitos Favoritos</option>\n"
       			  		+ "                            <option value=\"9\">Cisrcuitos Históricos</option>\n"
       			  		+ "                            <option value=\"10\">Futuro dos Circuitos</option>\n"
       			  		+ "                            <option value=\"11\">Novos Cirtuitos</option>\n"
       			  		+ "                        </select>\n"
       			  		+ "                        <button class=\"btn btn-outline-light w-50 mb-3\" id=\"submit-topic\">Enviar</button>\n"
       			  		+ "                    </div>\n"
       			  		+ "                </div>\n"
       			  		+ "            </div>"
					+  " <div class=\"container\">\n" 
			  		+ "        <div class=\"subforum\">\n"
			  		+ "            <div class=\"subforum-title\">\n"
			  		+ "                <h1>Geral</h1>\n"
			  		+ "            </div>\n"
			  		+ "            <div class=\"subforum-row\">\n"
			  		+ "                <div class=\"subforum-icon subforum-column center\">\n"
			  		+ "                    <i class=\"fa fa-globe center\"></i>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-description subforum-column\">\n"
			  		+ "                    <h4><a href=\"post/list/2\">Assuntos diversos</a></h4>\n"
			  		+ "                    <p>Descrição Conteúdo: Local para bate papo sobre assuntos diversos, fora do mundo da F1.</p>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-stats subforum-column center\">\n"
			  		+ "                    <span>"+tmp[0]+" Posts</span>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-info subforum-column\">\n"
			  		+ "                    <b><a href=\"/post/"+p[0].getID()+"\">Último post</a></b> by "+p[0].getUsuario()+" \n"
			  		+ "                    <br>em <small>"+p[0].getData().toString()+"</small>\n"
			  		+ "                </div>\n"
			  		+ "            </div>\n"
			  		+ "        </div>\n"
			  		+ "        <!--Mais-->\n"
			  		+ "        \n"
			  		+ "        <div class=\"subforum\">\n"
			  		+ "            <div class=\"subforum-title\">\n"
			  		+ "                <h1>Pilotos</h1>\n"
			  		+ "            </div>\n"
			  		+ "            <div class=\"subforum-row\">\n"
			  		+ "                <div class=\"subforum-icon subforum-column center\">\n"
			  		+ "                    <i class=\"fa fa-user \"></i>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-description subforum-column\">\n"
			  		+ "                    <h4><a href=\"post/list/3\">Estreantes promissores</a></h4>\n"
			  		+ "                    <p>Descrição do Conteúdo: Discuta sobre os pilotos mais jovens e talentosos que estão fazendo suas estreias na Fórmula 1 e as expectativas que cercam suas carreiras.</p>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-stats subforum-column center\">\n"
			  		+ "                    <span>"+tmp[1]+" Posts</span>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-info subforum-column\">\n"
			  		+ "                    <b><a href=\"/post/"+p[1].getID()+"\">Último post </a></b> by "+p[1].getUsuario()+" \n"
			  		+ "                    <br>em <small>"+p[1].getData().toString()+"</small>\n"
			  		+ "                </div>\n"
			  		+ "            </div>\n"
			  		+ "            <hr class=\"subforum-devider\">\n"
			  		+ "            <div class=\"subforum-row\">\n"
			  		+ "                <div class=\"subforum-icon subforum-column center\">\n"
			  		+ "                    <i class=\"fa fa-user \"></i>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-description subforum-column\">\n"
			  		+ "                    <h4><a href=\"/post/list/3\">Pilotos Lendários</a></h4>\n"
			  		+ "                    <p>Descrição do Conteúdo:  Discuta pilotos lendários da Fórmula 1, como Michael Schumacher, Ayrton Senna, Juan Manuel Fangio e outros que deixaram uma marca duradoura no esporte.</p>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-stats subforum-column center\">\n"
			  		+ "                    <span>"+tmp[2]+" Posts</span>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-info subforum-column\">\n"
			  		+ "                    <b><a href=\"/post/"+p[2].getID()+"\">Último post</a></b> by "+p[2].getUsuario()+" \n"
			  		+ "                    <br>em <small>"+p[2].getData().toString()+"</small>\n"
			  		+ "                </div>\n"
			  		+ "            </div>\n"
			  		+ "            <hr class=\"subforum-devider\">\n"
			  		+ "            <div class=\"subforum-row\">\n"
			  		+ "                <div class=\"subforum-icon subforum-column center\">\n"
			  		+ "                    <i class=\"fa fa-user \"></i>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-description subforum-column\">\n"
			  		+ "                    <h4><a href=\"/post/list/4\">Técnica de pilotagem</a></h4>\n"
			  		+ "                    <p>Discuta as diferentes técnicas de pilotagem usadas por pilotos de F1, como frenagem, curvas, estratégias de ultrapassagem e como elas evoluíram ao longo dos anos.</p>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-stats subforum-column center\">\n"
			  		+ "                    <span>"+tmp[3]+" Posts</span>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-info subforum-column\">\n"
			  		+ "                    <b><a href=\"/post/"+p[3].getID()+"\">Último post</a></b> by "+p[3].getUsuario()+" \n"
			  		+ "                    <br>em <small>"+p[3].getData().toString()+"</small>\n"
			  		+ "                </div>\n"
			  		+ "            </div>\n"
			  		+ "            <hr class=\"subforum-devider\">\n"
			  		+ "            <div class=\"subforum-row\">\n"
			  		+ "                <div class=\"subforum-icon subforum-column center\">\n"
			  		+ "                    <i class=\"fa fa-user \"></i>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-description subforum-column\">\n"
			  		+ "                    <h4><a href=\"/post/list/5\">Rivalidades memoráveis</a></h4>\n"
			  		+ "                    <p>Descrição do Conteúdo: Analise rivalidades históricas entre pilotos, como a rivalidade entre Senna e Prost ou entre Hamilton e Rosberg, e como essas rivalidades moldaram o esporte.</p>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-stats subforum-column center\">\n"
			  		+ "                    <span>"+tmp[4]+" Posts</span>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-info subforum-column\">\n"
			  		+ "                    <b><a href=\"/post/"+p[4].getID()+"\">Último post</a></b> by "+p[4].getUsuario()+" \n"
			  		+ "                    <br>em <small>"+p[4].getData().toString()+"</small>\n"
			  		+ "                </div>\n"
			  		+ "            </div>\n"
			  		+ "            <hr class=\"subforum-devider\">\n"
			  		+ "            <div class=\"subforum-row\">\n"
			  		+ "                <div class=\"subforum-icon subforum-column center\">\n"
			  		+ "                    <i class=\"fa fa-user \"></i>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-description subforum-column\">\n"
			  		+ "                    <h4><a href=\"/post/list/6\">Pilotos contemporâneos</a></h4>\n"
			  		+ "                    <p>Descrição do Conteúdo: Aborde os pilotos atuais da Fórmula 1, como Lewis Hamilton, Max Verstappen, Charles Leclerc e outros, e como eles estão moldando o cenário competitivo atual da categoria.</p>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-stats subforum-column center\">\n"
			  		+ "                    <span>"+tmp[5]+" Posts</span>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-info subforum-column\">\n"
			  		+ "                    <b><a href=\"/post/"+p[5].getID()+"\">Último post</a></b> by "+p[5].getUsuario()+" \n"
			  		+ "                    <br>em <small>"+p[5].getData().toString()+"</small>\n"
			  		+ "                </div>\n"
			  		+ "            </div>\n"
			  		+ "        </div>\n"
			  		+ "        \n"
			  		+ "        <div class=\"subforum\">\n"
			  		+ "            <div class=\"subforum-title\">\n"
			  		+ "                <h1>Circuitos</h1>\n"
			  		+ "            </div>\n"
			  		+ "            <div class=\"subforum-row\">\n"
			  		+ "                <div class=\"subforum-icon subforum-column center\">\n"
			  		+ "                    <i class=\"fa fa-road center\"></i>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-description subforum-column\">\n"
			  		+ "                    <h4><a href=\"/post/list/7\">Circuitos Favoritos</a></h4>\n"
			  		+ "                    <p>Descrição do conteúdo: Discuta qual são os melhores cirtcuitos da F1.</p>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-stats subforum-column center\">\n"
			  		+ "                    <span>"+tmp[6]+" Posts</span>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-info subforum-column\">\n"
			  		+ "                    <b><a href=\"/post/"+p[6].getID()+"\">Último post</a></b> by "+p[6].getUsuario()+" \n"
			  		+ "                    <br>em <small>"+p[6].getData().toString()+"</small>\n"
			  		+ "                </div>\n"
			  		+ "            </div>\n"
			  		+ "            <hr class=\"subforum-devider\">\n"
			  		+ "            <div class=\"subforum-row\">\n"
			  		+ "                <div class=\"subforum-icon subforum-column center\">\n"
			  		+ "                    <i class=\"fa fa-road center\"></i>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-description subforum-column\">\n"
			  		+ "                    <h4><a href=\"post/list/8\">Circuitos icônicos</a></h4>\n"
			  		+ "                    <p>Descrição do conteúdo: Discuta os circuitos mais icônicos da Fórmula 1, como Monza, Spa-Francorchamps, Silverstone e Mônaco, destacando sua história, desafios únicos e momentos memoráveis.</p>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-stats subforum-column center\">\n"
			  		+ "                    <span>"+tmp[7]+" Posts</span>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-info subforum-column\">\n"
			  		+ "                    <b><a href=\"/post/"+p[7].getID()+"\">Último post</a></b> by "+p[7].getUsuario()+" \n"
			  		+ "                    <br>em <small>"+p[7].getData().toString()+"</small>\n"
			  		+ "                </div>\n"
			  		+ "            </div>\n"
			  		+ "            <hr class=\"subforum-devider\">\n"
			  		+ "            <div class=\"subforum-row\">\n"
			  		+ "                <div class=\"subforum-icon subforum-column center\">\n"
			  		+ "                    <i class=\"fa fa-road center\"></i>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-description subforum-column\">\n"
			  		+ "                    <h4><a href=\"post/list/9\">Futuro dos circuitos</a></h4>\n"
			  		+ "                    <p>Descrição do conteúdo: Discuta as mudanças e inovações planejadas para os circuitos de Fórmula 1, como a introdução de novos circuitos, reformas em circuitos existentes, questões ambientais e segurança nas pistas.</p>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-stats subforum-column center\">\n"
			  		+ "                    <span>"+tmp[8]+" Posts</span>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-info subforum-column\">\n"
			  		+ "                    <b><a href=\"/post/"+p[8].getID()+"\">Último post</a></b> by "+p[8].getUsuario()+" \n"
			  		+ "                    <br>em <small>"+p[8].getData().toString()+"</small>\n"
			  		+ "                </div>\n"
			  		+ "            </div>\n"
			  		+ "            <hr class=\"subforum-devider\">\n"
			  		+ "            <div class=\"subforum-row\">\n"
			  		+ "                <div class=\"subforum-icon subforum-column center\">\n"
			  		+ "                    <i class=\"fa fa-road center\"></i>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-description subforum-column\">\n"
			  		+ "                    <h4><a href=\"post/list/10\">Novos circuitos</a></h4>\n"
			  		+ "                    <p>Descrição do conteúdo: Explore os novos circuitos que foram introduzidos na Fórmula 1 nos últimos anos, como o Circuito das Américas em Austin, Texas, ou o Circuito de Yas Marina em Abu Dhabi, e como eles se comparam aos circuitos mais tradicionais.</p>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-stats subforum-column center\">\n"
			  		+ "                    <span>"+tmp[9]+" Posts </span>\n"
			  		+ "                </div>\n"
			  		+ "                <div class=\"subforum-info subforum-column\">\n"
			  		+ "                    <b><a href=\"/post/"+p[9].getID()+"\">Último post</a></b> by "+p[9].getUsuario()+" \n"
			  		+ "                    <br>em <small>"+p[9].getData().toString()+"</small>\n"
			  		+ "                </div>\n"
			  		+ "            </div>\n"
			  		+ "\n"
			  		+ "           \n"
			  		+ "        \n"
			  		+ "\n"
			  		+ "";
			}
		else if( tipo == FORM_LIST)
		{
			String list = new String();
			umPost+= "   <div class=\"container\">\n"
					+ "        \n"
					+ "        <!--tabela-->\n"
					+ "        <div class=\"posts-table\">\n"
					+ "            <div class=\"table-head\">\n"
					+ "                <div class=\"status\">Status</div>\n"
					+ "                <div class=\"subjects\">Tema</div>\n"
					+ "            </div>\n"
					+ "            </div>";
			List<Post> posts;
				posts = postDAO.getArray(orderBy);
			int i = 0;
			String bgcolor = "";
			for (Post p : posts) {
				String categoria = "";
				String inicio = "";
				if(p.getConteudo() == null)
				{
					
				}
				else if(p.getConteudo().length() < 30)
				{
				  inicio = p.getConteudo().substring(0, p.getConteudo().length());
				}
				else
				{
				inicio = p.getConteudo().substring(0,30);
				}
				bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
				if(p.getCategoria()== 1)
				{
				  categoria = "Corrida";
				}
				else if(p.getCategoria() == 2)
				{
					categoria = "Piloto";
				}
				
				list+="<div class=\"table-row\">\n"
						+ "                <div class=\"status\"><i class=\"fa fa-fire\"></i></div>\n"
						+ "                <div class=\"subjects\">\n"
						+ "                    <a href=\"/post/"+p.getID()+"\">//"+ p.getConteudo() +"</a>\n"
						+ "                    <br>\n"
						+ "                    <span>Iniciado por: <b>"+ p.getUsuario()+"</b> .</span>\n"
						+ "                </div>\n"
						+ "            </div>\n";
				
			}
           list+= " </div>" +
        		   "      <footer>\n"
		+ "        <span>&copy;  SpeedFanatic</span>\n"
		+ "    </footer>";
			form = form.replaceFirst("<LISTAR-POST>", list);
		}
		else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-POST>", umPost);
		if(tipo != FORM_FORUM && tipo != FORM_LIST)
		{
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Posts</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/post/list/" + FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
        		"\t<td><a href=\"/post/list/" + FORM_ORDERBY_DATA + "\"><b>Conteudo</b></a></td>\n" +
        		"\t<td><a href=\"/post/list/" + FORM_ORDERBY_CATEGORIA + "\"><b>Preço</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Post> posts;
		if (orderBy == FORM_ORDERBY_ID) {                 	posts = postDAO.getOrderByID();
		} else if (orderBy == FORM_ORDERBY_DATA) {		posts = postDAO.getOrderByData();
		} else if (orderBy == FORM_ORDERBY_CATEGORIA) {			posts = postDAO.getOrderByCategoria();
		} else {											posts = postDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Post p : posts) {
			String categoria = "";
			String inicio = "";
			if(p.getConteudo() == null)
			{
				
			}
			else if(p.getConteudo().length() < 30)
			{
			  inicio = p.getConteudo().substring(0, p.getConteudo().length());
			}
			else
			{
			inicio = p.getConteudo().substring(0,30);
			}
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			if(p.getCategoria()== 1)
			{
			  categoria = "Corrida";
			}
			else if(p.getCategoria() == 2)
			{
				categoria = "Piloto";
			}
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + categoria + "</td>\n" +
            		  "\t<td><a href=\"/resposta/list/" + p.getID() + "\">" + inicio + "</a></td>\n" +
            		  "\t<td>" + p.getData().toString() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/post/" + p.getID() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/post/update/" + p.getID() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeletePost('" + p.getID() + "', '" + p.getConteudo() + "', '" + p.getCategoria() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-POST>", list);				
	}
	}
	public Object makeFormChain(Post post)	
	{
		String nomeArquivo = "Forum.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umPost = "";
		umPost += "\t<table width=\"80%\" bgcolor=\"#000000	\" align=\"center\">";
		umPost += "\t\t<tr>";
		umPost += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Post: " + post.getConteudo() + "</b></font></td>";
		umPost += "\t\t</tr>";
		umPost += "\t\t<tr>";
		umPost += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
		umPost += "\t\t</tr>";
		umPost += "\t\t<tr>";
		umPost += "\t\t\t<td>Feito por: "+ post.getUsuario() +"</td>";
		umPost += "\t\t</tr>";
		umPost += "\t\t<tr>";
		umPost += "\t\t\t<td>Data: "+ post.getData().toString() + "</td>";
		umPost += "\t\t\t<td>&nbsp;</td>";
		umPost += "\t\t</tr>";
		umPost += "\t</table>";
		umPost += "<div class=\"comment\">\n"
	             + "                    <button data-comment-area-id=\"comment-area-1\">Comentar</button>\n"
	             + "                </div>\n"
	             + "            </div>\n"
	             + "        </div>\n"
	             + "    </div>"
	      		+ "         <form action=\"/resposta/insert\" method=\"post\" class=\"row justify-content-center\">\n"
	      		+ "        <div class=\"comment-area hide\" id=\"comment-area-1\">\n"
	      		+ "            <input type=\"text\" class=\"form-control w-75 mb-3\" id=\"conteudo\" name=\"conteudo\" required>\n"
	      		+ "            <br>\n"
	      		+ "            <input type=\"hidden\" id=\"categoria\" name=\"categoria\" value=\""+post.getCategoria()+"\">\n"
	      		+ "            <input type=\"hidden\" id=\"resp_pai\" name=\"resp_pai\" value=\"0\">\n"
	      		+ "            <input type=\"hidden\" id=\"post\" name=\"post\" value=\""+post.getID()+"\">\n"
	      		+ "            <button id=\"submit\">Enviar</button>\n"
	      		+ "        </div>\n"
	      		+ "    </form>\n";
	
	form = form.replaceFirst("<UM-POST>", umPost);
	return form;
	}
	
	private static boolean verificarTextoModerador(String texto) {
        // Configuração da API da Microsoft Content Moderator
        String subscriptionKey = "270d010b411140d68c67f46ac838e902";
        String endpoint = "https://moderadorforum.cognitiveservices.azure.com/contentmoderator/moderate/v1.0/ProcessText/Screen";

        try {
            HttpClient httpclient = HttpClients.createDefault();

            URIBuilder builder = new URIBuilder(endpoint);
            builder.setParameter("autocorrect", "false");
            builder.setParameter("PII", "false");
            builder.setParameter("listId", "");
            builder.setParameter("classify", "True");
            builder.setParameter("language", "");

            HttpPost request = new HttpPost(builder.build());
            request.setHeader("Content-Type", "text/plain");
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

            StringEntity reqEntity = new StringEntity(texto);
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String responseText = EntityUtils.toString(entity);
                System.out.println(responseText);
                
                // Verifique se a resposta contém um termo inadequado
                if (responseText.contains("\"Terms\":null")) {
                    // A resposta indica que o texto é aprovado
                    return true;
                } else {
                    // A resposta indica que o texto não é aprovado
                    System.out.println("Texto não aprovado pelo Content Moderator.");
                    return false;
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Em caso de erro, assume que o texto não é aprovado
        return false;
    }
	
	public Object insert(Request request, Response response) {
		String conteudo = request.queryParams("conteudo");
		int categoria = Integer.parseInt(request.queryParams("categoria"));
			String usuario = request.session().attribute("user_username");
			LocalDate data = LocalDate.now();
		
		String resp = "";
		int tmp = request.session().attribute("user_nota");
        if(tmp >= 0)
        {
        boolean textoAprovado = verificarTextoModerador(conteudo);
        Post post = new Post(1, conteudo, categoria, usuario, data);
        // Se o texto for aprovado, insere a resposta no serviço
        if (textoAprovado) {

        	if(postDAO.insert(post) == true) {
                resp = "Post (" + conteudo + ") inserido!";
                response.status(201); // 201 Created
                response.redirect("/forum");
    		} else {
    			resp = "Post (" + conteudo + ") não inserido!";
    			response.status(404); // 404 Not found
    		}
        }
        
        else
        {
        	System.out.println("Texto nao aprovado");
    
        User user = userDAO.get(request.session().attribute("user_id"));
        int temp = request.session().attribute("user_nota");
        user.setNota(temp-1);
        System.out.println("NOTA DEPOIS: " + user.getNota());
        userDAO.update(user);
        response.redirect("/forum");
        }
        }
		makeFormHp(4);
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	
	}
	public Object getChain(Request request, Response response) {
			int id = Integer.parseInt(request.params(":id"));		
			Post post = (Post) postDAO.get(id);
			
			if (post != null) {
				response.status(200); // success
				request.session().attribute("conteudo_p", post.getConteudo());
				request.session().attribute("data_p", post.getData().toString());
				request.session().attribute("usuario_p", post.getUsuario());
				makeFormChain(post);
	        } else {
	            response.status(404); // 404 Not found
	            String resp = "Post " + id + " não encontrado.";
	    		makeForm();
	    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
	        }

			return form;
		}

	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Post post = (Post) postDAO.get(id);
		
		if (post != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, post, FORM_ORDERBY_DATA);
        } else {
            response.status(404); // 404 Not found
            String resp = "Post " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Post post = (Post) postDAO.get(id);
		
		if (post != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, post, FORM_ORDERBY_DATA);
        } else {
            response.status(404); // 404 Not found
            String resp = "Post " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		
		int orderBy = Integer.parseInt(request.params(":orderby"));
		if(orderBy == 1)
		{
			response.redirect("/forum");
		}
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}
	public Object getHomePage(Request request, Response response)
	{
	  makeFormHp(4);
	  return form;
	}
	
	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
		Post post = postDAO.get(id);
        String resp = "";       

        if (post != null) {
        	post.setConteudo(request.queryParams("conteudo"));
        	post.setCategoria(Integer.parseInt(request.queryParams("categoria")));
        	post.setUsuario((request.queryParams("usuario")));
        	post.setData(LocalDate.parse(request.queryParams("data")));
        	postDAO.update(post);
        	response.status(200); // success
            resp = "Post (ID " + post.getID() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Post (ID \" + post.getId() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Post post = postDAO.get(id);
        String resp = "";       

        if (post != null) {
            postDAO.delete(id);
            response.status(200); // success
            resp = "Post (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Post (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}