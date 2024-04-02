package com.taskTracker.service;

import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailService {
    void sendEmailWithAttachment(String to, String filePath) throws MessagingException, IOException;
    void sendEmail(String toEmail, String subject, String message);
    void sendEmailWithWorkbench(String address);
}
