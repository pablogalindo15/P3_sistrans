package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.modelo.Obc;
import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.repositorio.ObcRepository;
import uniandes.edu.co.proyecto.servicios.obcServicio;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ObcController {

    @Autowired
    private ObcRepository obcRepository;

    @Autowired
    private obcServicio obcServicio;

    @Autowired
    private CuentaRepository cuentaRepository;

    @GetMapping("/obcs")
    public String listarObcs(Model model, String id) {
    
        List<Obc>  obcs= obcRepository.findAll();

        if(id == null || id == "" ){
            model.addAttribute("obcs", obcs);
        }
        else{
            model.addAttribute("obcs", obcRepository.darObcsIdCuenta(Integer.parseInt(id)));
        }



        return "obcs";
    }

    @GetMapping("/obcs/new")
    public String formularioNuevoObc(Model model) {
        List<Cuenta> todasLasCuentas = cuentaRepository.findAll();
        // Filtrar solo las cuentas activas usando Java Streams
        List<Cuenta> cuentasActivas = todasLasCuentas.stream()
            .filter(cuenta -> "Activa".equals(cuenta.getEstado()))
            .collect(Collectors.toList());
    
        model.addAttribute("obc", new Obc());
        model.addAttribute("cuentas", cuentasActivas);
        return "obcNueva";
    }
    
    @Transactional
    @PostMapping("/obcs/new/save")
    public String guardarObc(@ModelAttribute Obc obc, RedirectAttributes redirectAttributes, Model model) {
        try {
            Cuenta cuenta = cuentaRepository.findById(obc.getId_cuenta().getId()).orElse(null);

            if (cuenta != null) {
                if ("Retiro".equals(obc.getTipo())) {
                    if (cuenta.getSaldo() >= obc.getValor()) {
                        cuenta.setSaldo(cuenta.getSaldo() - obc.getValor()); // Disminuir el saldo de la cuenta
                        // Actualizar la fecha de última transacción si es necesario
                        if (cuenta.getFechaUltimaTransaccion() == null || obc.getFecha().after(cuenta.getFechaUltimaTransaccion())) {
                            cuenta.setFechaUltimaTransaccion(obc.getFecha());
                        }
                        cuentaRepository.save(cuenta); // Guardar los cambios en la cuenta
                        obcRepository.save(obc); // Guardar la OBC
                        return "redirect:/obcs";
                    } else {
                        redirectAttributes.addFlashAttribute("error", "Saldo insuficiente para realizar el retiro.");
                        redirectAttributes.addFlashAttribute("obc", obc); // Opcional: Volver a enviar datos de OBC
                        return "redirect:/error.html";
                    }
                } else if ("Consignación".equals(obc.getTipo())) {
                    cuenta.setSaldo(cuenta.getSaldo() + obc.getValor()); // Aumentar el saldo de la cuenta
                    // Actualizar la fecha de última transacción si es necesario
                    if (cuenta.getFechaUltimaTransaccion() == null || obc.getFecha().after(cuenta.getFechaUltimaTransaccion())) {
                        cuenta.setFechaUltimaTransaccion(obc.getFecha());
                    }
                    cuentaRepository.save(cuenta); // Guardar los cambios en la cuenta
                    obcRepository.save(obc); // Guardar la OBC
                    return "redirect:/obcs";
                }
            }

            return "redirect:/error.html"; // En caso de que no exista la cuenta o haya algún otro error
        } catch (Exception e) {
            // Manejar la excepción y realizar rollback si es necesario
            return "redirect:/error.html";
            
        }
    }
    

    @GetMapping("/obcs/{id}/edit")
    public String formularioEditarObc(@PathVariable("id") Integer id, Model model) {
        Obc obc = obcRepository.findById(id).orElse(null);
        List<Cuenta> cuentas = cuentaRepository.findAll();
        if (obc != null) {
            model.addAttribute("obc", obc);
            model.addAttribute("cuentas", cuentas);
            return "editar-obc";
        } else {
            return "redirect:/obcs";
        }
    }

    @PostMapping("/obcs/{id}/update")
    public String actualizarObc(@PathVariable("id") Integer id, @ModelAttribute Obc obc) {
        obc.setId(id);
        obcRepository.save(obc);
        return "redirect:/obcs";
    }

    @GetMapping("/obcs/{id}/delete")
    public String eliminarObc(@PathVariable("id") Integer id) {
        obcRepository.deleteById(id);
        return "redirect:/obcs";
    }

    @GetMapping("/obcs/iso")
    public String listarObcsIso(Model model, String id) throws NumberFormatException, InterruptedException {
    
        List<Obc>  obcs= obcRepository.findAll();

        if(id == null || id == "" ){
            model.addAttribute("obcs", obcs);
        }
        else{
            model.addAttribute("obcs", obcServicio.darObcsIdCuenta(Integer.parseInt(id)));
        }



        return "obcsIso";
    }

    @GetMapping("/obcs/iso")
    public String listarObcsIsoRC(Model model, String id) throws NumberFormatException, InterruptedException {
    
        List<Obc>  obcs= obcRepository.findAll();

        if(id == null || id == "" ){
            model.addAttribute("obcs", obcs);
        }
        else{
            model.addAttribute("obcs", obcServicio.darObcsIdCuenta(Integer.parseInt(id)));
        }



        return "obcsIso";
    }
}
