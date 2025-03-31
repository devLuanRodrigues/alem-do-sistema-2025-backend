package br.com.alemdosistema.alemdosistema.controller;

import br.com.alemdosistema.alemdosistema.dto.ClientDTO;
import br.com.alemdosistema.alemdosistema.mapper.ClientMapper;
import br.com.alemdosistema.alemdosistema.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String home(Model model) {
        List<ClientDTO> clients = clientService.listAllClients().stream()
                .map(ClientMapper.INSTANCE::clientToClientDTO)
                .toList();
        model.addAttribute("clients", clients);
        return "index";
    }
}
