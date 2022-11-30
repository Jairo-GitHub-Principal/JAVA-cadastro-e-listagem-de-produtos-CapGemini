package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import model.Project;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author Marcio Michelluzzi
 */
public class TaskController {
     public void save(Task task) {
         String sql = "INSERT INTO tasks(idProjeto, nome, descricao,completada, observacao, prazo, dataCriacao, dataAtualizacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
           
            conn = ConnectionFactory.getConnection();
            //Cria um PreparedStatment, classe usada para executar a query
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, task.getIdProject());
            stmt.setString(2,task.getNome());
            stmt.setString(3, task.getDescricao());
            stmt.setBoolean(4, task.isCompletada());
            stmt.setString(5, task.getObservacao());
            stmt.setDate(6, new Date(task.getPrazo().getTime()));
            stmt.setDate(7, new Date(task.getDataCriacao().getTime()));
            
            stmt.setDate(8, new Date(task.getDataAtualizacao().getTime()));
            
            
            
            
           

            //Executa a sql para inser��o dos dados
            stmt.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar a tarefa", ex);
        } finally {
            //Fecha as conex�es
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }
        }

    

     public void update(Task task) {

        String sql = "UPDATE tasks SET idProjeto = ?, nome = ?, descricao = ?,completada  = ?,observacao = ?, prazo = ?, dataCriacao = ?, dataAtualizacao = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            //Cria uma conex�o com o banco
            conn = ConnectionFactory.getConnection();
            //Cria um PreparedStatment, classe usada para executar a query
            stmt = conn.prepareStatement(sql);
            
           
            stmt.setInt(1, task.getIdProject());
            stmt.setString(2, task.getNome());
            stmt.setString(3, task.getDescricao());
             stmt.setBoolean(4, task.isCompletada());
            stmt.setString(5, task.getObservacao());
            stmt.setDate(6, new Date(task.getPrazo().getTime()));
            stmt.setDate(7, new Date(task.getDataCriacao().getTime()));
            stmt.setDate(8, new Date(task.getDataAtualizacao().getTime()));
            stmt.setInt(9, task.getId()); /* quando eu receber o ID nessa linha, significa que 
            o numero que eu receber nessa propriedade, vai apontar para o id que identificara uma linha na tabela task
            e nessa linha todos os campos serão mudados conforme os novos valores recebidos*/

            //Executa a sql para inser��o dos dados
            stmt.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro em atualizar a tarefa", ex);
        } finally {
          
                 ConnectionFactory.closeConnection(conn,stmt);
           
           
        }
    }
     
     
     
     public List<Task> getAll() {
        String sql = "SELECT * FROM tasks";

        List<Task> tasks = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            rset = stmt.executeQuery();

            //Enquanto existir dados no banco de dados, fa�a
            while (rset.next()) {

                Task task = new Task();

                task.setId(rset.getInt("id"));
                task.setIdProject(rset.getInt("idProjeto"));
                task.setNome(rset.getString("nome"));
                task.setDescricao(rset.getString("descricao"));
               
                task.setObservacao(rset.getString("observacao"));
                task.setPrazo(rset.getDate("prazo"));
                task.setCompletada(rset.getBoolean("completada"));
                task.setDataCriacao(rset.getDate("dataCriacao"));
                task.setDataAtualizacao(rset.getDate("dataAtualizacao"));

                //Adiciono o contato recuperado, a lista de contatos
                tasks.add(task);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar as tarefas", ex);
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }
        return tasks;
    }
     
     
      public void removeById(int id) {

        String sql = "DELETE FROM tasks WHERE id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar o tarefa", ex);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }

    }
       
   
    

    

    // busca um objeto atravez do id do mesmo
    public List<Task> getByProjectId(int id) {
        String sql = "SELECT * FROM tasks where idProjeto = ?";

        List<Task> tasks = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            rset = stmt.executeQuery();

            //Enquanto existir dados no banco de dados, fa�a
            while (rset.next()) {

                Task task = new Task();

                task.setId(rset.getInt("id"));
                task.setIdProject(rset.getInt("idProjeto"));
                task.setNome(rset.getString("nome"));
                task.setDescricao(rset.getString("descricao"));
               
                task.setObservacao(rset.getString("observacao"));
                task.setPrazo(rset.getDate("prazo"));
                task.setCompletada(rset.getBoolean("completada"));
                task.setDataCriacao(rset.getDate("dataCriacao"));
                task.setDataAtualizacao(rset.getDate("dataAtualizacao"));

                //Adiciono o contato recuperado, a lista de contatos
                tasks.add(task);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar as tarefas", ex);
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }
        return tasks;
    }

   

    

}