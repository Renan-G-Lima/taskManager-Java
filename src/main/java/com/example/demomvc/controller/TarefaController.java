package com.example.demomvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*importando a tarefa*/
import com.example.demomvc.entity.Tarefa;
import com.example.demomvc.service.TarefaService;

import jakarta.validation.Valid;

/*coloquei os @ para que o sprintboot entenda oque é.*/
@Controller
@RequestMapping("/tarefas")
public class TarefaController {
	
	@Autowired
	private TarefaService service;
	
	/* mapeamento para a página de cadastros */
	@GetMapping("/cadastro")
	public String cadastro(Tarefa tarefa) {
		return "/tarefa/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Tarefa tarefa, BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "tarefa/cadastro";
		}
		service.salvar(tarefa);
		attr.addFlashAttribute("success", "Tarefa salva com sucesso");
		return "redirect:/tarefas/lista"; /*ainda não esta direcionando para a lista*/
	}
	
	/*Redireciona para a lista quando aperta em submit*/
	@GetMapping("/lista")
	public String lista(ModelMap model) {
		model.addAttribute("tarefas", service.buscaTodos());
		return "tarefa/lista";
	}

			@PostMapping("/editar")
			public String editar(@Valid Tarefa tarefa, BindingResult result, RedirectAttributes attr) {
				if(result.hasErrors()) {
					return "tarefa/cadastro";
				}
				service.editar(tarefa);
				attr.addFlashAttribute("success", "Tarefa salva com sucesso");
				return "redirect:/tarefas/cadastro";
			}

			@GetMapping("/excluir/{id}")
			public String excluir(@PathVariable("id") Long id, ModelMap model ) {
				
				service.excluir(id);
				model.addAttribute("success", "Tarefa excluída");
				return lista(model);
			}

			@GetMapping("/editar/{id}")
			public String preEditar(@PathVariable("id") Long id, ModelMap model) {
				model.addAttribute("tarefa", service.buscarPorId(id));
				return "/tarefa/cadastro";
			}
	

}
