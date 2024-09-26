package com.lab4.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lab4.Model.Customer;
import com.lab4.Model.SmartPhone;
import com.lab4.service.ShoppingCartService;
import com.lab4.service.ShoppingCartServicePhone;
import com.lab4.util.Phone;

import javax.mail.*;
import javax.mail.internet.*;


@Controller
public class ShoppingCartPhoneController {

	@Autowired
	ShoppingCartServicePhone cart; // tiêm Spring Bean đã tạo
	
	
	@RequestMapping("/phone/view")
	public String view(Model model) {
		model.addAttribute("cart", cart);
		model.addAttribute("Count", cart.getCount());
		model.addAttribute("Amount", cart.getAmount());
		return "cartPhone";
	}

	@RequestMapping("/phone/add/{id}")
	public String add(@PathVariable("id") Integer id) {
		System.out.println();
		cart.add(id);
		return "redirect:/phone/view"; // hiển thị giỏ hàng
	}

	@RequestMapping("/phone/remove/{id}")
	public String remove(@PathVariable("id") Integer id) {
		cart.remove(id);
		return "redirect:/phone/view";
	}

	@RequestMapping("/phone/update/{id}/{pre}")
	public String update(@PathVariable("id") Integer id,@PathVariable("pre") String pre) {
		cart.update(id, pre);
		return "redirect:/phone/view";
	}

	@RequestMapping("/phone/clear")
	public String clear() {
		cart.clear();
		return "redirect:/phone/view";
	}
	
	@RequestMapping("/phone/gotoPay")
	public String pay(Model model) {
		Customer customer=new Customer();
		
		List<SmartPhone> ds=new ArrayList<SmartPhone>();
		for(SmartPhone phone:cart.getPhones())
		{
			ds.add(phone);
		}
		customer.setListPhone(ds);
		model.addAttribute("customer", customer);
		model.addAttribute("Amount", cart.getAmount());
		System.out.println(customer.getListPhone());
		
		return "pay";
	}
	
	@RequestMapping("/phone/{id}")
    public String getProductDetail(@PathVariable("id") Integer id, Model model) {
        SmartPhone phone = Phone.phone.get(id);
        model.addAttribute("item", phone);
        return "productDetail";  // Trả về trang chi tiết sản phẩm
    }
	
	@RequestMapping("/phone/order")
	public String order(@ModelAttribute("customer") Customer customer)
	{
		//customer.setListPhone(cart);
		try {
			 Properties properties = new Properties();
		        properties.put("mail.smtp.host", "smtp.gmail.com");
		        properties.put("mail.smtp.port", "587");
		        properties.put("mail.smtp.auth", "true");
		        properties.put("mail.smtp.starttls.enable", "true");
			
		        final String myEmail = "phongpvps36848@fpt.edu.vn";
		        final String password = "hghm ugqp puja zqpk";
		        
		        Session session = Session.getInstance(properties, new Authenticator() {
		            @Override
		            protected PasswordAuthentication getPasswordAuthentication() {
		                return new PasswordAuthentication(myEmail, password);
		            }
		        });
		        StringBuilder listPhone=new StringBuilder("-----------------\n");
		        List<SmartPhone> ds=new ArrayList<SmartPhone>();
				for(SmartPhone phone:cart.getPhones())
				{
					ds.add(phone);
				}
				customer.setListPhone(ds);
		        int stt=1;
		        for(SmartPhone sp:customer.getListPhone())
		        {
		        	listPhone.append(stt+++"| Tên Sản Phẩm: "+sp.getName()+"| Giá: "+sp.getPrice()+"| Số Lượng: "+sp.getQty()+"\n");
		        	
		        }
		        String messageText=" Họ Và Tên: "+customer.getName()+
		        					"\n Số Điện Thoại: "+customer.getPhone()+
		        					"\n Địa Chỉ: "+customer.getAddress()+
		        					"\nDanh Sách Sản Phẩm Bạn Đã Đặt: \n"+
		        					 listPhone.toString();
		        
		        System.out.println(messageText);
		        Message message = prepareMessage(session, myEmail, customer.getEmail(), "Xác Thực đặt hàng", messageText);
		        
		        
		        Transport.send(message);
		        System.out.println("Email đã được gửi thành công!");
		        
		        return "redirect:/phone/clear";
		        
		} catch (Exception e) {
			System.out.println("Email đã được gửi Thất Bại!"+e.toString());
			return "redirect:/phone/gotoPay";
		}
		
	}
	
	private static Message prepareMessage(Session session, String myEmail, String recipient, String subject, String messageText) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(messageText);
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("Lỗi ở dòng 117 của shoppingcartphonecontroller");
        return null;
    }
	
	
}
