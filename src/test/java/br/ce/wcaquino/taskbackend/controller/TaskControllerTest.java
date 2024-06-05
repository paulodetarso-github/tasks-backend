package br.ce.wcaquino.taskbackend.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {

	@Mock
	TaskRepo taskrepo;
	
	@InjectMocks
	TaskController controller = new TaskController();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void deveDarErroTarefaSemDescricao() {
		
		Task tarefa = new Task();
		
		try {
			controller.save(tarefa);
			fail("Deveria ter lançado exceção.");
		} catch (ValidationException e) {
			assertEquals("Fill the task description", e.getMessage());
		}
	}
	
	@Test
	public void deveDarErroTarefaSemData() {
		
		Task tarefa = new Task();
		tarefa.setTask("Carece de preencher a data.");
		
		try {
			controller.save(tarefa);
			fail("Deveria ter lançado exceção.");
		} catch (ValidationException e) {
			assertEquals("Fill the due date", e.getMessage());
		}
	}
	
	@Test
	public void deveDarErroTarefaVencida() {

		Task tarefa = new Task();
		tarefa.setTask("Perdeu o prazo, mane");
		tarefa.setDueDate(LocalDate.of(2024, 6, 4));
		
		try {
			controller.save(tarefa);
			fail("Deveria ter lançado exceção.");
		} catch (ValidationException e) {
			assertEquals("Due date must not be in past", e.getMessage());
		}
	}
	
	@Test
	public void deveDarCerto() throws ValidationException {
		
		Task tarefa = new Task();
		tarefa.setTask("Curso de Jenkins.");
		tarefa.setDueDate(LocalDate.of(2024, 6, 30));
		
		controller.save(tarefa);
		
		Mockito.verify(taskrepo).save(tarefa);
	}
	
}
