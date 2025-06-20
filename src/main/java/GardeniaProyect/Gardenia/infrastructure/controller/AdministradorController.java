package GardeniaProyect.Gardenia.infrastructure.controller;

import GardeniaProyect.Gardenia.app.service.IOrdenService;
import GardeniaProyect.Gardenia.infrastructure.entity.Producto;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import GardeniaProyect.Gardenia.app.service.IProductoService;
import GardeniaProyect.Gardenia.app.service.IUsuarioService;
import GardeniaProyect.Gardenia.infrastructure.entity.Orden;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IOrdenService ordensService;

    @Autowired
    private IProductoService productoService;

    private Logger logg = LoggerFactory.getLogger(AdministradorController.class);

    @GetMapping("")
    public String home(Model model) {

        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);

        return "administrador/home";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "administrador/usuarios";
    }

    @GetMapping("/ordenes")
    public String ordenes(Model model) {
        model.addAttribute("ordenes", ordensService.findAll());
        return "administrador/ordenes";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(Model model, @PathVariable Integer id) {
        logg.info("Id de la orden {}", id);
        Orden orden = ordensService.findById(id).get();

        model.addAttribute("detalles", orden.getDetalle());

        return "administrador/detalleorden";
    }

}
