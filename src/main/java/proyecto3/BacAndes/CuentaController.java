package proyecto3.BacAndes;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.stream.Stream;

import org.springframework.ui.Model;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController

public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private CuentaRepository cuentaRepository;


    @Autowired
    private ObcsRepository obcsRepository;

    @Autowired
    private ClienteService clienteService;

    @RequestMapping("/cuentas")
    public ModelAndView listarCuentas(
        @RequestParam(required = false) String tipo,
        @RequestParam(required = false) Integer minSaldo,
        @RequestParam(required = false) Integer maxSaldo,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date minFechaUltimaTransaccion,
        @RequestParam(required = false) String idCliente,
        Model model) {
    List<Cuenta> cuentas = cuentaService.allCuentas();

    Stream<Cuenta> filteredCuentas = cuentas.stream();

    if (tipo != null && !tipo.isEmpty()) {
        filteredCuentas = filteredCuentas.filter(c -> tipo.equals(c.getTipo()));
    }
    if (minSaldo != null) {
        filteredCuentas = filteredCuentas.filter(c -> c.getSaldo() >= minSaldo);
    }
    if (maxSaldo != null) {
        filteredCuentas = filteredCuentas.filter(c -> c.getSaldo() <= maxSaldo);
    }
    if (minFechaUltimaTransaccion != null) {
        filteredCuentas = filteredCuentas.filter(c -> c.getFecha_ultima_transaccion() != null && !c.getFecha_ultima_transaccion().before(minFechaUltimaTransaccion));
    }
    if (idCliente != null && ObjectId.isValid(idCliente)) {
        ObjectId clienteId = new ObjectId(idCliente);
        filteredCuentas = filteredCuentas.filter(c -> c.getId_cliente() != null && clienteId.equals(c.getId_cliente()));
    }

    List<Cuenta> sortedCuentas = filteredCuentas
            .sorted(Comparator.comparing(Cuenta::getEstado, Comparator.comparingInt(s -> "Activa".equals(s) ? 1 : "Desactivada".equals(s) ? 2 : 3)))
            .collect(Collectors.toList());

    model.addAttribute("cuentas", sortedCuentas);

    return new ModelAndView("cuentas").addObject("cuentas", sortedCuentas);
}

    @GetMapping("cuentas/nuevo")
    public ModelAndView mostrarFormularioNuevaCuenta() {
        List<Cliente> clientes = clienteService.allClientes();
        return new ModelAndView("cuentaNueva").addObject("cuenta", new Cuenta()).addObject("clientes", clientes);
    }

    @PostMapping("cuentas/new/save")
    public ModelAndView crearCuenta(@ModelAttribute Cuenta cuenta) {
        cuentaService.saveCuenta(cuenta);
        List<Cuenta> cuentas = cuentaService.allCuentas();
        return new ModelAndView("cuentas").addObject("cuentas", cuentas);
    }

    @GetMapping("cuentas/desactivar/{id}")
    public ModelAndView desactivarCuenta(@PathVariable("id") ObjectId id) {
        cuentaService.desactivarCuenta(id);
        return new ModelAndView("redirect:/cuentas");
    }

    @GetMapping("cuentas/cerrar/{id}")
    public ModelAndView cerrarCuenta(@PathVariable("id") ObjectId id) {
        cuentaService.cerrarCuenta(id);
        return new ModelAndView("redirect:/cuentas");
    }

    @GetMapping("cuentas/extractos")
    public ModelAndView formularioExtractos( @RequestParam(required = false) String id_cuenta) {
   
        List<Obcs> listaObcs = obcsRepository.findAll();
        Stream<Obcs> filtereObcs = listaObcs.stream();


        if (id_cuenta != null && ObjectId.isValid(id_cuenta)) {
            ObjectId id_cuenta2 = new ObjectId(id_cuenta);
            filtereObcs = filtereObcs .filter(o -> o.getId_cuenta() != null && id_cuenta2.equals(o.getId_cuenta()));
            
        }
         
        ObjectId idcuentaId= new ObjectId(id_cuenta);
        int saldo = cuentaRepository.findById(idcuentaId).get().getSaldo();


        
        
        return new ModelAndView("extractoNuevo").addObject("obcs", filtereObcs).addObject("saldo", saldo);
    }
}

