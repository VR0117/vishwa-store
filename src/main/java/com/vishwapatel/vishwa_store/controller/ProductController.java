package com.vishwapatel.vishwa_store.controller;

import com.vishwapatel.vishwa_store.model.Product;
import com.vishwapatel.vishwa_store.repository.ProductRepository;
import com.vishwapatel.vishwa_store.service.ProductService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.StringWriter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return null;
    }
//    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
//    public ResponseEntity<String> getProductsInXML() {
//        try {
//            String xmlData = productService.getAllProductsInXML();
//            return ResponseEntity.ok(xmlData);
//        } catch (JAXBException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating XML");
//        }
//    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getProductsInXML() {
        try {
            List<Product> products = productService.getAllProducts();
            String xmlData = transformToXML(products);
            return ResponseEntity.ok(xmlData);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error generating XML");
        }
    }

    // Helper method to convert product list to XML
    private String transformToXML(List<Product> products) {
        try {
            JAXBContext context = JAXBContext.newInstance(Product.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter writer = new StringWriter();
            for (Product product : products) {
                marshaller.marshal(product, writer);
            }
            return writer.toString();
        } catch (Exception e) {
            return "<error>XML Conversion Failed</error>";
        }
    }

    public static String transformToXML(Product product) {
        try {
            JAXBContext context = JAXBContext.newInstance(Product.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter writer = new StringWriter();
            marshaller.marshal(product, writer);
            return writer.toString();  // This returns the XML string
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}



