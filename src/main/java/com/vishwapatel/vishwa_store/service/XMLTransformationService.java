package com.vishwapatel.vishwa_store.service;

import com.vishwapatel.vishwa_store.model.Product;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.stereotype.Service;

import java.io.StringWriter;

@Service
public class XMLTransformationService {

    public static String transformToXML(Product product) {
        try {
            // Create JAXB context and marshaller for the Product class
            JAXBContext jaxbContext = JAXBContext.newInstance(Product.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Create StringWriter to hold the XML output
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(product, sw);

            // Return the XML string
            return sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
