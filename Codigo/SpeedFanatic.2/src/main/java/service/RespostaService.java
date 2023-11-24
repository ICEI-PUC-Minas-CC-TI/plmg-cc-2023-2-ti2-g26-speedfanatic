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
import dao.RespostaDAO;
import model.Resposta;
import spark.Request;
import spark.Response;
import dao.PostDAO;
import model.Post;
import model.User;
import dao.UserDAO;

public class RespostaService {

    private RespostaDAO respostaDAO = new RespostaDAO();
    private UserDAO userDAO = new UserDAO();
    private String form;
    private final int FORM_INSERT = 1;
    private final int FORM_DETAIL = 2;
    private final int FORM_UPDATE = 3;
    private final int FORM_ORDERBY_ID = 1;
    private final int FORM_ORDERBY_DATA = 2;
    private final int FORM_ORDERBY_CATEGORIA = 3;

    public RespostaService() {
        makeForm();
    }

    public void makeForm() {
        makeForm(FORM_INSERT, new Resposta(), FORM_ORDERBY_DATA);
    }

    public void makeForm(int orderBy) {
        makeForm(FORM_INSERT, new Resposta(), orderBy);
    }
    public void makeFormChain(int post_id, String conteudo, String a)
    {
         form = a;
      
         List<Resposta> respostas;
         respostas = respostaDAO.getChain(post_id);
         Resposta tmp = new Resposta();
     
     int i = 1;

     String list = new String();
     String bgcolor = "";
     for (Resposta r : respostas) {
         String categoria = "";
         String inicio = "";
         if (r.getConteudo() == null) {

         } else if (r.getConteudo().length() < 30) {
             inicio = r.getConteudo().substring(0, r.getConteudo().length());
         } else {
             inicio = r.getConteudo().substring(0, 30);
         }
         bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
         if(r.getRespostaPaiId()==0)
         {
         list += "   <!--Topic Section-->\n"
         		+ "        <div class=\"topic-container\">\n"
         		+ "            <!--Mensagem Inicial-->\n"
         		+ "            <div class=\"head\">\n"
         		+ "                <div class=\"authors\">Autor</div>\n"
         		+ "                <div class=\"content\">Tópico:"+ categoria+"</div>\n"
         		+ "            </div>\n"
         		+ "\n"
         		+ "            <div class=\"body\">\n"
         		+ "                <div class=\"authors\">\n"
         		+ "                    <div class=\"username\"> "+ r.getUsuario()+"</div>\n"
         		+ "                     </div>\n"
         		+ "                \n"
         		+ "                <div class=\"content\">\n"
         		+ "                    Mensagem:\n"
         		+ "                    <br>"+ r.getConteudo()+"\n"
         		+ "                    <br><br>\n"
         		+ "                    \n"
         		+ "                    <br>\n"
         		+ "                    <hr>\n"
         		+ "                    \n"
         		+ "                    <br>\n"
                +"\n"
                + "                <div class=\"comment\">\n"
                + "                    <button data-comment-area-id=\"comment-area-"+i+"\">Comentar</button>\n"
                + "                </div>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>"
         		+ "         <form action=\"/resposta/insert\" method=\"post\" class=\"row justify-content-center\">\n"
         		+ "        <div class=\"comment-area hide\" id=\"comment-area-"+i+"\">\n"
         		+ "            <input type=\"text\" class=\"form-control w-75 my-4\" id=\"conteudo\" name=\"conteudo\" required>\n"
         		+ "            <br>\n"
         		+ "            <input type=\"hidden\" id=\"categoria\" name=\"categoria\" value=\""+r.getCategoria()+"\">\n"
         		+ "            <input type=\"hidden\" id=\"resp_pai\" name=\"resp_pai\" value=\""+r.getID()+"\">\n"
         		+ "            <input type=\"hidden\" id=\"post\" name=\"post\" value=\""+r.getPost()+"\">\n"
         		+ "            <button class=\"btn btn-danger ms-5\" id=\"submit\">Enviar</button>\n"
         		+ "        </div>\n"
         		+ "    </form>\n"
         		+ "";
         }
         else
        	 list+="  \n"
        			 + "         <div class=\"comments-container\" style=\"width: 75%; margin-left: auto; margin-right: 0;\"> \n"
              		+ "        <div class=\"body\">\n"
              		+ "            <div class=\"authors\">\n"
              		+ "                <div class=\"username\"> "+ r.getUsuario()+"</div>\n"
              		+ "            </div>\n"
              		+ "            <div class=\"content\">\n"
              		+ "                Comentário:\n"
              		+ "                <br>"+r.getConteudo()+"\n"
              		+ "                <br><br>\n"
              		+ "                <div class=\"comment\">\n"
              		+ "                    <button data-reply-area-id=\"reply-area-"+i+"\">Responder</button>\n"
              		+ "                </div>\n"
              		+ "            </div>\n"
              		+ "        </div>\n"
              		+ "    </div>\n"
         		+ "\n"
         		+ "    <!-- Área de Resposta 2 -->\n"
         		+ "    <form action=\"/resposta/insert\" method=\"post\" class=\"row justify-content-center\">\n"
         		+ "        <div class=\"comment-area hide\" id=\"reply-area-"+i+"\">\n"
         		+ "            <label for=\"email\">Nome de Usuário:</label>\n"
         		+ "            <input type=\"text\" class=\"form-control w-75 my-4\" id=\"conteudo\" name=\"conteudo\" required>\n"
         		+ "            <br>\n"
         		 + "<input type=\"hidden\" id=\"categoria\" name=\"categoria\" value=\""+ r.getCategoria()+"\" />"
     			 + "<input type=\"hidden\" id=\"resp_pai\" name=\"resp_pai\" value=\""+r.getID()+"\">"
     			  + "<input type=\"hidden\" id=\"post\" name=\"post\" value=\""+ r.getPost()+"\" />"
         		+ "            <button class=\"btn btn-danger ms-5\" id=\"submit\">Enviar</button>\n"
         		+ "        </div>\n"
         		+ "    </form>";
      i++;  		
     }
     
     form = form.replaceFirst("<LISTAR-POST>", list);
     
 
    }
    
    

    public void makeForm(int tipo, Resposta resposta, int orderBy) {
        String nomeArquivo = "form.html";
        form = "";
        try {
            Scanner entrada = new Scanner(new File(nomeArquivo));
            while (entrada.hasNext()) {
                form += (entrada.nextLine() + "\n");
            }
            entrada.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String umaResposta = "";
        if (tipo != FORM_INSERT) {
            umaResposta += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
            umaResposta += "\t\t<tr>";
            umaResposta += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/resposta/list/1\">Nova Resposta</a></b></font></td>";
            umaResposta += "\t\t</tr>";
            umaResposta += "\t</table>";
            umaResposta += "\t<br>";
        }

        if (tipo == FORM_INSERT || tipo == FORM_UPDATE) {
            String action = "/post/";
            String name, conteudo, buttonLabel, categoria;
            categoria = "";
            if (tipo == FORM_INSERT) {
                action += "insert";
                name = "Inserir Resposta";
                conteudo = "leite, pão, ...";
                buttonLabel = "Inserir";
                if (resposta.getPost() == 1) {
                    categoria = "Corrida";
                } else if (resposta.getPost() == 2) {
                    categoria = "Piloto";
                }
            } else {
                action += "update/" + resposta.getID();
                name = "Atualizar Resposta (ID " + resposta.getID() + ")";
                conteudo = resposta.getConteudo();
                buttonLabel = "Atualizar";
            }
            umaResposta += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
            umaResposta += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
            umaResposta += "\t\t<tr>";
            umaResposta += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
            umaResposta += "\t\t</tr>";
            umaResposta += "\t\t<tr>";
            umaResposta += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
            umaResposta += "\t\t</tr>";
            umaResposta += "\t\t<tr>";
            umaResposta += "\t\t\t<td>&nbsp;Conteudo: <input class=\"input--register\" type=\"text\" name=\"conteudo\" value=\"" + conteudo + "\"></td>";
            umaResposta += "\t\t\t<td>Categoria: <input class=\"input--register\" name=\"categoria\" value=\"" + categoria + "\"></td>";
            umaResposta += "\t\t</tr>";
            umaResposta += "\t\t<tr>";
            umaResposta += "\t\t\t<td>Data de validade: <input class=\"input--register\" type=\"text\" name=\"data\" value=\"" + resposta.getData().toString() + "\"></td>";
            umaResposta += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\"" + buttonLabel + "\" class=\"input--main__style input--button\"></td>";
            umaResposta += "\t\t</tr>";
            umaResposta += "\t</table>";
            umaResposta += "\t</form>";
        } else if (tipo == FORM_DETAIL) {
            umaResposta += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
            umaResposta += "\t\t<tr>";
            umaResposta += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Resposta (ID " + resposta.getID() + ")</b></font></td>";
            umaResposta += "\t\t</tr>";
            umaResposta += "\t\t<tr>";
            umaResposta += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
            umaResposta += "\t\t</tr>";
            umaResposta += "\t\t<tr>";
            umaResposta += "\t\t\t<td>&nbsp;Conteudo: " + resposta.getConteudo() + "</td>";
            umaResposta += "\t\t\t<td>Categoria: " + resposta.getPost() + "</td>";
            umaResposta += "\t\t\t<td>Usuario: " + resposta.getUsuario() + "</td>";
            umaResposta += "\t\t</tr>";
            umaResposta += "\t\t<tr>";
            umaResposta += "\t\t\t<td>Data de validade: " + resposta.getData().toString() + "</td>";
            umaResposta += "\t\t\t<td>&nbsp;</td>";
            umaResposta += "\t\t</tr>";
            umaResposta += "\t</table>";
        } else {
            System.out.println("ERRO! Tipo não identificado " + tipo);
        }
        form = form.replaceFirst("<UM-POST>", umaResposta);
         	
        String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
        list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Respostas</b></font></td></tr>\n" +
                "\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
                "\n<tr>\n" +
                "\t<td><a href=\"/resposta/list/" + FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
                "\t<td><a href=\"/resposta/list/" + FORM_ORDERBY_DATA + "\"><b>Conteudo</b></a></td>\n" +
                "\t<td><a href=\"/resposta/list/" + FORM_ORDERBY_CATEGORIA + "\"><b>Post</b></a></td>\n" +
                "\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
                "\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
                "\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
                "</tr>\n";

        List<Resposta> respostas;
            respostas = respostaDAO.getOrderByData();
        
        int i = 0;
        String bgcolor = "";
        for (Resposta r : respostas) {
            String categoria = "";
            String inicio = "";
            if (r.getConteudo() == null) {

            } else if (r.getConteudo().length() < 30) {
                inicio = r.getConteudo().substring(0, r.getConteudo().length());
            } else {
                inicio = r.getConteudo().substring(0, 30);
            }
            bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
            if (r.getPost() == 1) {
                categoria = "Corrida";
            } else if (r.getPost() == 2) {
                categoria = "Piloto";
            }
            list += "\n<tr bgcolor=\"" + bgcolor + "\">\n" +
                    "\t<td>" + categoria + "</td>\n" +
                    "\t<td><a href=\"teste.html\">" + inicio + "</a></td>\n" +
                    "\t<td>" + r.getData().toString() + "</td>\n" +
                    "\t<td align=\"center\" valign=\"middle\"><a href=\"/resposta/" + r.getID() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
                    "\t<td align=\"center\" valign=\"middle\"><a href=\"/resposta/update/" + r.getID() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
                    "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteResposta('" + r.getID() + "', '" + r.getConteudo() + "', '" + r.getPost() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
                    "</tr>\n";
        }
        list += "</table>";
        form = form.replaceFirst("<LISTAR-POST>", list);
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

        return false;
    }
    public Object insert(Request request, Response response) {
        String conteudo = request.queryParams("conteudo");
        int post = Integer.parseInt(request.queryParams("post"));
        String usuario = request.session().attribute("user_username");
        int resppai = Integer.parseInt(request.queryParams("resp_pai"));
        LocalDate data = LocalDate.now(); 
        int categoria = Integer.parseInt(request.queryParams("categoria"));
        String resp = "";
        int tmp = request.session().attribute("user_nota");
        if(tmp >= 0)
        {
        boolean textoAprovado = verificarTextoModerador(conteudo);
        Resposta resposta = new Resposta(1, conteudo, post, usuario, resppai, data, categoria);
        // Se o texto for aprovado, insere a resposta no serviço
        if (textoAprovado) {

            if (respostaDAO.insert(resposta) == true) {
                resp = "Resposta (" + conteudo + ") inserida!";
                response.status(201); // 201 Created
                response.redirect("/post/"+post);
        } else {
        	 resp = "Resposta (" + conteudo + ") não inserida!";
             response.status(404); // 404 Not found
             response.redirect("/forum");
        }
        }
        else
        {
        	System.out.println("Texto nao aprovado");
        
        User user = userDAO.get(request.session().attribute("user_id"));
        System.out.println("NOTA ANTES: " +user.getNota());
        int temp = user.getNota()-1;
        user.setNota(temp);
        System.out.println("NOTA DEPOIS: " + user.getNota());
        userDAO.update(user);
        request.session().attribute("user_nota", user.getNota());
        
        }
        }
        response.redirect("/forum");
        makeFormChain(post, "", "");
        resp = "Resposta (" + conteudo + ") não aprovada!";
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
  
    }

    public Object get(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Resposta resposta = (Resposta) respostaDAO.get(id);

        if (resposta != null) {
            response.status(200); // success
            makeForm(FORM_DETAIL, resposta, FORM_ORDERBY_DATA);
        } else {
            response.status(404); // 404 Not found
            String resp = "Resposta " + id + " não encontrada.";
            makeForm();
            form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
        }

        return form;
    }
    public Object getRespostas(Request request, Response response, String a) {
    
            response.status(200); // success
            int id = Integer.parseInt(request.params(":id"));
            String conteudo = "";
            makeFormChain(id, conteudo, a);
        return form;
    }

    public Object getToUpdate(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Resposta resposta = (Resposta) respostaDAO.get(id);

        if (resposta != null) {
            response.status(200); // success
            makeForm(FORM_UPDATE, resposta, FORM_ORDERBY_DATA);
        } else {
            response.status(404); // 404 Not found
            String resp = "Resposta " + id + " não encontrada.";
            makeForm();
            form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
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
        Resposta resposta = respostaDAO.get(id);
        String resp = "";

        if (resposta != null) {
            resposta.setConteudo(request.queryParams("conteudo"));
            resposta.setPost(Integer.parseInt(request.queryParams("post")));
            resposta.setUsuario((request.queryParams("usuario")));
            resposta.setData(LocalDate.parse(request.queryParams("data")));
            respostaDAO.update(resposta);
            response.status(200); // success
            resp = "Resposta (ID " + resposta.getID() + ") atualizada!";
        } else {
            response.status(404); // 404 Not found
            resp = "Resposta (ID " + resposta.getID() + ") não encontrada!";
        }
        makeForm();
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
    }

    public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Resposta resposta = respostaDAO.get(id);
        String resp = "";

        if (resposta != null) {
            respostaDAO.delete(id);
            response.status(200); // success
            resp = "Resposta (" + id + ") excluída!";
        } else {
            response.status(404); // 404 Not found
            resp = "Resposta (" + id + ") não encontrada!";
        }
        makeForm();
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
    }
}
