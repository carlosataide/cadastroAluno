
package bancodados.cadastroaluno;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/bancodados/CadastroAluno")
public class CadastroAlunoController extends HttpServlet {

  protected void service(
      HttpServletRequest req,
      HttpServletResponse resp)
      throws ServletException, IOException {

    String paramMatricula = req.getParameter("matricula");
    String matricula = paramMatricula == null ? "" : paramMatricula;
    
    String paramNome = req.getParameter("nome");
    String nome = paramNome == null ? "" : paramNome;

    String paramFone = req.getParameter("fone");
    String fone = paramFone == null ? "" : paramFone;
    
    String paramCpf = req.getParameter("cpf");
    String cpf = paramCpf == null ? "" : paramCpf;
    
    String paramEvento = req.getParameter("opcao");
    String opcao = paramEvento == null ? "" : paramEvento;


    Aluno aluno = new Aluno();
    aluno.setMatricula(matricula);
    aluno.setNome(nome);
    aluno.setFone(fone);
    aluno.setCpf(cpf);
    
    if (opcao.equals("Incluir")) {
			if (!matricula.equals("")) {
				aluno.incluir();
			}
		} else if (opcao.equals("Alterar")) {
			if (!matricula.equals("")) {
				aluno.alterar(matricula, nome, fone, cpf);
			}
		} else if (opcao.equals("Excluir")) {
			if (!matricula.equals("")) {
				aluno.excluir(matricula);
			}
		}

    if (!matricula.equals("")) {
      aluno.incluir();
    }
    
    req.setAttribute("aluno", aluno); //Passando um objeto para o JSP.
    
    List<Aluno> alunos = Aluno.listar();
    req.setAttribute("alunos", alunos); //Passando um objeto para o JSP.

    
    //Chamar o JSP apenas para mostrar o resultado.
    req.getRequestDispatcher("CadastroAluno.jsp").forward(req, resp);
  }

}

