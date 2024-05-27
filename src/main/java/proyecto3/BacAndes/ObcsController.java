package proyecto3.BacAndes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.model.Model;

@RestController
public class ObcsController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @Autowired
    private ObcsRepository obcsRepository;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteService clienteService;

    @RequestMapping("/obcs")
    public ModelAndView listarObcs() {
        List<Obcs> ListaObcs = obcsRepository.findAll();

        return new ModelAndView("obcs").addObject("obcs", ListaObcs);
    }
    
    @RequestMapping("/obcs2")
    public ModelAndView listarObcs2(@RequestParam(required = true) ObjectId idCliente) {
        clienteService.singleCliente(idCliente);
        String idClienteString = idCliente.toString();    
        List<Obcs> obcs = obcsRepository.findAll();
        
        List<Cuenta> cuentasUsuario = cuentaService.darCuentasIdUsuario(idClienteString);    
        return new ModelAndView("obcs").addObject("obcs", cuentasUsuario);
    }

    @GetMapping("obcs/nuevo")
    public ModelAndView mostrarFormularioNuevoObcs() {
        List<Cuenta> cuentas = cuentaService.allCuentas();
        return new ModelAndView("ObcsNuevo").addObject("obcs", new Obcs()).addObject("cuentas", cuentas);
    }

    @PostMapping("obcs/nuevo/save")
    public ModelAndView crearObcs(@ModelAttribute Obcs obcs) {
        obcsRepository.save(obcs);
        ObjectId id_cuenta =  obcs.getId_cuenta(); // Convert ObjectId to String
        
        
        if(obcs.getTipo().equals("consignacion")){
            Cuenta cuentaNueva = cuentaRepository.findById(id_cuenta).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
            cuentaService.deleteCuenta(id_cuenta);
            cuentaNueva.setSaldo(cuentaNueva.getSaldo() + obcs.getValor());
            cuentaNueva.setFecha_ultima_transaccion(new Date());
            cuentaService.saveCuenta(cuentaNueva);
        }
        else if(obcs.getTipo().equals("retiro")){
            Cuenta cuentaNueva2 = cuentaRepository.findById(id_cuenta).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
            cuentaService.deleteCuenta(id_cuenta);
            cuentaNueva2.setSaldo(cuentaNueva2.getSaldo() + obcs.getValor());
            cuentaNueva2.setFecha_ultima_transaccion(new Date());
            cuentaService.saveCuenta(cuentaNueva2);
        }
      
        List<Obcs> obcss = obcsRepository.findAll();
        
        return new ModelAndView("obcs").addObject("obcs", obcss);
    }




    
}
