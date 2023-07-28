package com.taskTracker.service;

import com.taskTracker.dto.TaskDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final TaskService taskService;

    public void sendEmailWithAttachment(String to, String filePath)
            throws IOException, MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        System.out.println(javaMailSender);
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

    public void sendEmail(String toEmail, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setFrom("x90063232@gmail.com");
        javaMailSender.send(mailMessage);
    }

    public void sendEmailWithWorkbench(String address) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(address);
        mailMessage.setSubject("Workbench");
        mailMessage.setText(String.valueOf(taskService.getAllTasks()));
        mailMessage.setFrom("x90063232@gmail.com");
        javaMailSender.send(mailMessage);

        log.info("Workbench shared!");
    }
}
