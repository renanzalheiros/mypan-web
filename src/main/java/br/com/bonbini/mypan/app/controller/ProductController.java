package br.com.bonbini.mypan.app.controller;

import br.com.bonbini.mypan.app.dao.LoginDao;
import br.com.bonbini.mypan.app.dao.ProductDao;
import br.com.bonbini.mypan.app.entities.Login;
import br.com.bonbini.mypan.app.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rest/product")
public class ProductController {

    @Autowired
    private ProductDao dao;

    @Autowired
    private LoginDao loginDao;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts(@RequestHeader("user-auth") String auth) {
        Login user = loginDao.findByToken(auth);
        if(user != null) {

            return dao.findAll();
        }

        return null;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Product saveProduct(@RequestBody Product product) {
        return dao.saveNewProduct(product);
    }

    @RequestMapping(value = "/getimage/{filename}", method = RequestMethod.GET)
    @Produces(org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImage(@PathVariable("filename") String filename) {
        File file = new File("upload-dir/" + filename + ".jpg");
        try {
            FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
            return StreamUtils.copyToByteArray(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
