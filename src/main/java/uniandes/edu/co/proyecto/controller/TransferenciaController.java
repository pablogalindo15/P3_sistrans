package uniandes.edu.co.proyecto.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.modelo.Transferencia;
import uniandes.edu.co.proyecto.modelo.TransferenciaPK;
import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.repositorio.TransferenciaRepository;

@Controller
public class TransferenciaController {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @GetMapping("/transferencia/new")
    public String transferenciaForm(Model model) {
        model.addAttribute("cuentas", cuentaRepository.findAll());
        return "transferenciaNuevo";
    }

    @PostMapping("/transferencia/new/save")
    public String transferenciaGuardar(@ModelAttribute("id_cuenta_1") Integer idCuenta1,
                                       @ModelAttribute("id_cuenta_2") Integer idCuenta2,
                                       @ModelAttribute("fecha") Date fecha,
                                       @ModelAttribute("valor") Integer valor,
                                       @ModelAttribute("tipo") String tipo) {

        Cuenta cuenta1 = cuentaRepository.findById(idCuenta1).orElse(null);
        Cuenta cuenta2 = cuentaRepository.findById(idCuenta2).orElse(null);
        
        if (cuenta1 != null && cuenta2 != null) {
            TransferenciaPK pk = new TransferenciaPK(cuenta1, cuenta2, fecha, valor, tipo);
            Transferencia transferencia = new Transferencia();
            transferencia.setPk(pk);
            transferenciaRepository.save(transferencia);
        }
        
        return "redirect:/transferencias";
    }
}
