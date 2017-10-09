package br.com.bonbini.mypan.app.controller;

import br.com.bonbini.mypan.app.dao.BuyOrderDao;
import br.com.bonbini.mypan.app.dao.ProductDao;
import br.com.bonbini.mypan.app.entities.BuyOrder;
import br.com.bonbini.mypan.app.entities.Product;
import br.com.bonbini.mypan.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private BuyOrderDao buyOrderDao;

    @RequestMapping(value = "/product/", method = RequestMethod.GET)
    public ModelAndView listProducts() {
        List<Product> all = productDao.findAll();

        User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        ModelAndView mv = new ModelAndView("/products/listAll");
        mv.addObject("products", all);
        mv.addObject("username", username);

        return mv;
    }

    @RequestMapping(value = "/product/new", method = RequestMethod.GET)
    public ModelAndView newProduct(Product product) {
        ModelAndView mv = new ModelAndView("/products/newProduct");
        mv.addObject("produto", product);

        return mv;
    }


    @RequestMapping(value = "/addProduct", method = RequestMethod.POST, consumes = javax.ws.rs.core.MediaType.MULTIPART_FORM_DATA)
    public RedirectView addProduct(@RequestParam("uploadFile") MultipartFile uploadFile, @ModelAttribute Product produto, RedirectAttributes redirectAttributes){
        try {
            File basePath = new File(Constantes.UPLOAD_ROOT);
            if (!basePath.exists()) {
                basePath.mkdirs();
            }
            String imageName = uploadFile.getOriginalFilename();
            Files.copy(uploadFile.getInputStream(), Paths.get(Constantes.UPLOAD_ROOT + imageName));
            produto.setFile(imageName);

            productDao.saveNewProduct(produto);
            RedirectView rv = new RedirectView("product/");
            return rv;
        }
        catch (Exception e) {
            e.printStackTrace();
            RedirectView rv = new RedirectView("product/new");
            return rv;
        }
    }

    @RequestMapping(value = "/orders/", method = RequestMethod.GET)
    public ModelAndView getAllOrders(@ModelAttribute("update") String update, RedirectAttributes att) {
        User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        ModelAndView mv = new ModelAndView("/orders/listAll");
        mv.addObject("orders", buyOrderDao.findAll());
        mv.addObject("username", username);
        return mv;
    }

    @RequestMapping(value = "/update-status/{id}", method = RequestMethod.PUT)
    public RedirectView updateOrderStatus(@PathVariable("id") String orderId) {
        BuyOrder orderById = buyOrderDao.findOrderById(orderId);
        String status;
        String orderStatus = orderById.getOrderStatus();
        if (orderStatus.equals(Constantes.AGUARDANDO_APROVACAO)) {
            status = Constantes.PRODUZINDO;
        } else if (orderStatus.equals(Constantes.PRODUZINDO)) {
            status = Constantes.PRONTO;
        } else {
            status = orderStatus;
        }

        buyOrderDao.updateStatus(orderById, status);
        return new RedirectView("/mypan/admin/orders/");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public RedirectView deleteOrder(@PathVariable("id") String orderId) {
        BuyOrder orderById = buyOrderDao.findOrderById(orderId);
        buyOrderDao.delete(orderById);

        RedirectView redirectView = new RedirectView("/mypan/admin/orders/");
        redirectView.addStaticAttribute("update", "update");
        return redirectView;
    }
}
