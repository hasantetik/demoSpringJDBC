//package com.example.demoSpringJDBC.service;
//
//import lombok.AllArgsConstructor;
//import org.springframework.context.MessageSource;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//
//@Service @AllArgsConstructor public class MyMailSenderService
//{
//    private JavaMailSender emailSender;
//
//    public void sendMail(String to, String subject)
//    {
//        new Thread(new Runnable()
//        {
//            @Override public void run()
//            {
//
//                try
//                {
//                    SimpleMailMessage message = new SimpleMailMessage();
//                    message.setTo(to);
//                    message.setSubject(subject);
//                    message.setText("merhaba spring mail");
//                    emailSender.send(message);
//                }
//                catch (Exception e)
//                {
//                    System.err.println(e.getMessage());
//                }
//            }
//        });
//    }
//}