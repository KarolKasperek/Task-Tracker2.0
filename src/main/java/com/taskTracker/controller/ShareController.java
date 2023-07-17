package com.taskTracker.controller;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import com.taskTracker.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.Properties;

@Controller
@AllArgsConstructor
public class ShareController {
    private EmailService emailService;

    @GetMapping("/share")
    public String getSharePage(Model model) {
        model.addAttribute("address", "");
        return "share";
    }

    @PostMapping("/share")
    public String sendEmail(@RequestParam("address") String address, Model model) throws Exception {

        model.addAttribute("address", address);

//        convertHtmlToPdf("src/main/resources/templates/index.html", "workbench.pdf");
        emailService.sendEmailWithAttachment(address, "workbench.pdf");
        emailService.sendEmail(address, "title", "somethingwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");

        return "share";
    }

    public static void convertHtmlToPdf(String htmlFilePath, String outputPdfFilePath) {

        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputPdfFilePath));
            document.open();

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(htmlFilePath);
            renderer.layout();
            renderer.createPDF(writer.getDirectContent().getInternalBuffer(), true);
            document.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create PDF: " + e.getMessage());
        }
    }
}