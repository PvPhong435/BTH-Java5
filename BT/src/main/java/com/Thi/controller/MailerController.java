package com.Thi.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.Thi.entity.MailInfo;
import com.Thi.helper.MailerHelper;


@Controller
@RequestMapping("mailer")
public class MailerController {
	@Autowired
	com.Thi.service.MailerService mailer;

	@PostMapping("/send")
	public String send(Model model, 
			@RequestParam String txtTo, 
			@RequestParam String txtCC,
			@RequestParam String txtBCC,
			@RequestParam String txtSubject,
			@RequestParam String txtContent,
			@RequestParam(value = "file[]", required = false) List<MultipartFile> listFiles
			) throws IOException {
		MailerHelper helper=new MailerHelper();
	
		List<File> files=new ArrayList<>();
		String []toEmails = helper.parseStringEmailToArray(txtTo);
		String[] emailCC=helper.parseStringEmailToArray(txtCC);
		String[] emailBCC=helper.parseStringEmailToArray(txtBCC);
		MailInfo mail = new MailInfo();
		mail.setTo(toEmails);
		mail.setCc(emailCC);
		mail.setBcc(emailBCC);
		mail.setSubject(txtSubject);
		mail.setBody(txtContent);
		
		if(listFiles!=null&&!listFiles.isEmpty())
		{
			for(MultipartFile filemult : listFiles)
			{
				if(!filemult.isEmpty())
				{
					File file=helper.convertMultipartFileToFile(filemult);
					files.add(file);
					mail.setFiles(files);
				}
			}
		}
		else
		{
			model.addAttribute("error", "lỗi vì không có file đính kèm");
			System.out.println( "lỗi vì không có file đính kèm");
			return "redirect:/sendMail";
		}
		
		
		
//		//covert MultipartFile to File
//		if(!multipartFile.isEmpty()) {
//			File file=helper.convertMultipartFileToFile(multipartFile);
//			files.add(file);
//			//Set cho MailInfo
//			mail.setFiles(files);			
//		}
//		if(!multipartFile2.isEmpty()) {
//			File file=helper.convertMultipartFileToFile(multipartFile2);
//			files.add(file);
//			//Set cho MailInfo
//			mail.setFiles(files);			
//		}
//		if(!multipartFile1.isEmpty()) {
//			File file=helper.convertMultipartFileToFile(multipartFile1);
//			files.add(file);
//			//Set cho MailInfo
//			mail.setFiles(files);			
//		}
		//Gửi mail vào queue
		
//		mailer.queue(mail);
		//gửi mail trực tiếp
		//mailer.send(mail);
		
		try {
			mailer.send(mail);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		 return "success";
	}

}
