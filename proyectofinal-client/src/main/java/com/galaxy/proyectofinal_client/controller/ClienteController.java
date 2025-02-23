package com.galaxy.proyectofinal_client.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.galaxy.proyectofinal_client.dtos.ClienteDTO;
import com.galaxy.proyectofinal_client.dtos.PageDTO;

import jakarta.validation.Valid;

@Controller
public class ClienteController {
	
	@Value("${backend.url}")
	private String apiUrl;
	private RestTemplate restTemplate;
	
	public ClienteController(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.rootUri("").build();
	}
	
	/*@GetMapping
	public String findAllOrFilterByNombre(@RequestParam(required = false) String nombre, Model model) {
		Optional<String> optionalName = Optional.ofNullable(nombre);
		ResponseEntity<ClienteDTO[]> clientes;
		if (optionalName.isPresent()) {
			clientes = this.restTemplate.getForEntity(this.apiUrl + "?nombre=" + nombre, ClienteDTO[].class);
		} else {
			clientes = this.restTemplate.getForEntity(this.apiUrl, ClienteDTO[].class);
		}
		model.addAttribute("clientes", clientes.getBody());
		return "clientes";
	}*/
	@GetMapping
	public String findAllOrFilterByNombre(
	        @RequestParam(required = false) String nombre,
	        @RequestParam(defaultValue = "1") int pageNumber,
	        @RequestParam(defaultValue = "3") int pageSize,
	        Model model) {

	    String url = this.apiUrl + "/byPage?pageNumber=" + pageNumber + "&pageSize=" + pageSize;

	    if (nombre != null && !nombre.isEmpty()) {
	        url += "&nombre=" + nombre;
	    }

	    ResponseEntity<PageDTO> response = this.restTemplate.getForEntity(url, PageDTO.class);
	    
	    PageDTO clientesPage = response.getBody();

	    model.addAttribute("clientes", clientesPage.getContent());
	    model.addAttribute("currentPage", clientesPage.getNumber() + 1);
	    model.addAttribute("totalPages", clientesPage.getTotalPages());
	    model.addAttribute("pageSize", pageSize);

	    return "clientes";
	}
	
	@GetMapping("/{id}")
	public String findById(@PathVariable Long id,Model model) {
		ResponseEntity<ClienteDTO> cliente = this.restTemplate.getForEntity(this.apiUrl+"/"+id, ClienteDTO.class);
		model.addAttribute("cliente",cliente.getBody());
		return "detalle-cliente";
	}
	
	@GetMapping("/nuevo")
	public String showFormToRegister(Model model) {
		model.addAttribute("cliente", new ClienteDTO()); // Objeto vacío para el formulario
        model.addAttribute("titulo", "Registrar Cliente");
        model.addAttribute("action", "/guardar");
		return "formulario-nuevo";
	}
	
	@PostMapping("/guardar")
	public String save(@Valid @ModelAttribute("cliente") ClienteDTO clienteDTO, BindingResult result, Model model) {
		if (result.hasErrors()) {
	        model.addAttribute("titulo", "Registrar Cliente");
	        model.addAttribute("action", "/guardar");
	        return "formulario-nuevo"; // Retorna el formulario con errores
	    }
		this.restTemplate.postForEntity(this.apiUrl, clienteDTO, ClienteDTO.class);
        return "redirect:/"; // Redirige a la lista de clientes
	}
	
	// Mostrar el formulario de edición
    @GetMapping("/editar/{id}")
    public String showFormToEdit(@PathVariable Long id, Model model) {
    	ResponseEntity<ClienteDTO> cliente = this.restTemplate.getForEntity(this.apiUrl + '/' + id, ClienteDTO.class);
        model.addAttribute("cliente", cliente.getBody());
        model.addAttribute("titulo", "Editar Cliente");
        model.addAttribute("action", "/actualizar");
        return "formulario-nuevo";
    }

    // Procesar la actualización del cliente
    @PostMapping("/actualizar")
    public String update(@Valid @ModelAttribute("cliente") ClienteDTO clienteDTO, BindingResult result, Model model) {
    	if (result.hasErrors()) {
    		model.addAttribute("titulo", "Editar Cliente");
            model.addAttribute("action", "/actualizar");
            return "formulario-nuevo";
    	}
    	this.restTemplate.put(this.apiUrl + '/' + clienteDTO.getClienteNo(), clienteDTO);
        return "redirect:/"; // Redirige a la lista de clientes
    }
    
    // Mostrar el formulario de eliminación
    @GetMapping("/eliminar/{id}")
    public String showFormToDelete(@PathVariable Long id, Model model) {
    	ResponseEntity<ClienteDTO> cliente = this.restTemplate.getForEntity(this.apiUrl + '/' + id, ClienteDTO.class);
        model.addAttribute("cliente", cliente.getBody());
        model.addAttribute("titulo", "Eliminar Cliente");
        model.addAttribute("action", "/borrar?id=" + cliente.getBody().getClienteNo());
        return "formulario-nuevo";
    }

    // Procesar la actualización del cliente
    @PostMapping("/borrar")
    public String delete(@RequestParam Long id) {
    	this.restTemplate.delete(this.apiUrl + '/' + id);
        return "redirect:/"; // Redirige a la lista de clientes
    }
}
