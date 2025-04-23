package com.vishwapatel.vishwa_store.service;

import com.vishwapatel.vishwa_store.model.Product;
import com.vishwapatel.vishwa_store.repository.ProductRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // ✅ New method to return all products in XML
    public String getAllProductsInXML() throws JAXBException {
        List<Product> products = productRepository.findAll();

        // Check if the list is empty
        if (products.isEmpty()) {
            return "<products></products>";  // Return empty XML
        }

        // Convert list to XML
        JAXBContext context = JAXBContext.newInstance(ProductsWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        ProductsWrapper wrapper = new ProductsWrapper(products);
        StringWriter writer = new StringWriter();
        marshaller.marshal(wrapper, writer);

        return writer.toString();
    }

    // ✅ Helper class for wrapping the product list (required for XML transformation)
    private static class ProductsWrapper {
        private List<Product> products;

        public ProductsWrapper() {}  // Default constructor required for JAXB

        public ProductsWrapper(List<Product> products) {
            this.products = products;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }
    }
}
