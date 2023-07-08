package com.taskTracker.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmailWithAttachment(String to, String filePath)
            throws MessagingException, IOException, jakarta.mail.MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("My workbench");

        // Read the PDF file into a byte array
        Path path = Paths.get(filePath);
        byte[] pdfBytes = Files.readAllBytes(path);

        // Attach the PDF file to the email
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);
        helper.addAttachment("attachment.pdf", resource);

        javaMailSender.send(message);
    }
}

