package com.vishwapatel.vishwa_store.service;

import com.vishwapatel.vishwa_store.model.Payment;
import com.vishwapatel.vishwa_store.repository.PaymentRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private XMLTransformationService xmlTransformationService;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    // XML transformation method for all payments
    public String getAllPaymentsInXML() throws JAXBException {
        List<Payment> payments = paymentRepository.findAll();

        JAXBContext context = JAXBContext.newInstance(PaymentWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter writer = new StringWriter();
        marshaller.marshal(new PaymentWrapper(payments), writer);

        return writer.toString();
    }

    // Wrapper class for JAXB to handle lists
    public static class PaymentWrapper {
        private List<Payment> payments;

        public PaymentWrapper() {}

        public PaymentWrapper(List<Payment> payments) {
            this.payments = payments;
        }

        public List<Payment> getPayments() {
            return payments;
        }

        public void setPayments(List<Payment> payments) {
            this.payments = payments;
        }
    }
}