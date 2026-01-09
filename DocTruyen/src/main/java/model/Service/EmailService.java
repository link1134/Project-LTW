package model.Service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {
    // Thông tin cấu hình (Nên đưa vào file properties)
    private final String HOST = "smtp.gmail.com";
    private final String PORT = "587";
    private final String FROM_EMAIL = "hongochoansona1.c3hn2020@gmail.com";
    private final String APP_PASSWORD = "ahqouctzwhfzoiho"; 
    

    public boolean sendOTP(String toEmail, String otp) {
        // 1. Thiết lập Properties
    	Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // 2. Tạo Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
            }
        };

        // 3. Tạo Session
        Session session = Session.getInstance(props, auth);

        try {
            MimeMessage msg = new MimeMessage(session);
            // Thiết lập kiểu nội dung và mã hóa tiếng Việt
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.setFrom(new InternetAddress(FROM_EMAIL, "Hỗ trợ Website"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            msg.setSubject("Mã xác thực OTP đặt lại mật khẩu", "UTF-8");

            // Nội dung Email dạng HTML
            String content = "<h3>Mã xác thực của bạn là: <b style='color:red;'>" + otp + "</b></h3>"
                           + "<p>Mã này sẽ hết hạn sau 5 phút. Vui lòng không chia sẻ cho bất kỳ ai.</p>";
            
            msg.setContent(content, "text/html; charset=UTF-8");

            // 4. Gửi mail
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}