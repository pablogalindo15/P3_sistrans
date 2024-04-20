package uniandes.edu.co.proyecto.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProyectoController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
