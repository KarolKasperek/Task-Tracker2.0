package com.taskTracker.controller;

import com.taskTracker.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;

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

        // Load the HTML content from index.html

//        ClassPathResource resource = new ClassPathResource("/");
//        File htmlFile = resource.getFile();
//        String htmlString = new String(Files.readAllBytes(htmlFile.toPath()));
//        String outputPath = "output.pdf";
//
//        // Generate pdf file with workbench
//
//        try (OutputStream outputStream = new FileOutputStream(outputPath)) {
//            ITextRenderer renderer = new ITextRenderer();
//            renderer.setDocumentFromString(htmlString);
//            renderer.layout();
//            renderer.createPDF(outputStream);
//            renderer.finishPDF();
//        } catch (Exception e) {
//            throw new Exception("Failed to create PDF: " + e.getMessage());
//        }
//
//        // Send email on submitted address
//
//        emailService.sendEmailWithAttachment(address, "output.pdf");

        // Load the HTML content from index.html
        String htmlString = "<html><body><h1>Hello, World!</h1></body></html>";
        String outputPath = "output.pdf";

        // Generate pdf file with workbench
        try (OutputStream outputStream = new FileOutputStream(outputPath)) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlString);
            renderer.layout();
            renderer.createPDF(outputStream);
            renderer.finishPDF();

            System.out.println("PDF created successfully!");
        } catch (Exception e) {
            System.err.println("Failed to create PDF: " + e.getMessage());
        }

        // Send email on submitted address
        emailService.sendEmailWithAttachment(address, "output.pdf");

        return "share";
    }
}
