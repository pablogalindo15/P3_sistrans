package uniandes.edu.co.proyecto.controller;

import java.sql.Date;
import java.util.stream.Collectors;

import java.util.List;
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

    @GetMapping("/transferencias")
    public String listarTransferencias(Model model) {
        model.addAttribute("transferencias", transferenciaRepository.findAll());
        return "transferencias";
    }
    
    @GetMapping("/transferencia/new")
    public String transferenciaForm(Model model) {
        List<Cuenta> todasLasCuentas = cuentaRepository.findAll();
        // Filtrar solo las cuentas activas usando Java Streams
        List<Cuenta> cuentasActivas = todasLasCuentas.stream()
            .filter(cuenta -> "Activa".equals(cuenta.getEstado()))
            .collect(Collectors.toList());
        
        model.addAttribute("cuentas", cuentasActivas);
        return "transferenciaNueva";
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
