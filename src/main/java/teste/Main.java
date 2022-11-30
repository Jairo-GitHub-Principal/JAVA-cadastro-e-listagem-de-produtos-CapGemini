/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import model.Project;
import controller.ProjectController;
import controller.TaskController;
import java.util.ArrayList;
import java.util.List;
import model.Task;

/**
 *
 * @author jairo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("é ô ão");
        
        
        
        ProjectController projectController = new  ProjectController();
        Project project = new Project();
        

        project.setName(" projeto inicial");
        project.setDescription("descrição inicial");
        projectController.save(project);
        

        Project projectUpdate = new Project();
      
       projectUpdate.setId(4);
       projectUpdate.setName("projeto atualizado");
       projectUpdate.setDescription("descricão atualizada");
       projectController.update(projectUpdate);
       
       List<Project> projectList = projectController.getAll();
        System.out.println(projectList.size());
        
        int projectId = 9;
        projectController.removeById(projectId);

        
        
        
         System.out.println("\n TAREFAS");
         
        
        
         TaskController taskController = new TaskController();
         
         List<Task> listaDeTarefas = taskController.getAll();
        Task task = new  Task();
        
         
        // cadastrar
         task.setNome("criar tarefa1");
         task.setDescricao("tafefas de rotinas");
         task.setIdProject(6);
         task.setCompletada(false);
         
         taskController.save(task);
         
        // atualizar 
        
        int idTasks = 25 ;
        int idProj = 50;
        
         task.setId(idTasks); // refere-se ao ID de tarefas
         task.setIdProject(idProj);  // refere-se ao idProjeto, chave estrangeira na tabela task
         task.setNome("Atualizar tarefas");
         task.setDescricao("descrição atualizada");
         task.setCompletada(true);
         taskController.update(task);
         
         
         // remover
         
         int idTask = 1;
         
         taskController.removeById(idTask);


         // listar
         
         System.out.println("quantidade de tarefas cadastradas: "+ listaDeTarefas.size() );
         
         
         // listar por id
        
         int  idProjeto = 6;
        List<Task> listarPeloId = taskController.getByProjectId(idProjeto);
        
         System.out.println(listarPeloId.size());
         
         
         
         
         
         
         
         
        
        //System.out.println(project.getDescription());
        
    }
    
}
