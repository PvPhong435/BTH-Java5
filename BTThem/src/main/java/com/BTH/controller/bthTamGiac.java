package com.BTH.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class bthTamGiac {

	
	@GetMapping("/index")
	public String TamGiac(Model model)
	{
		
		return "index";
	}
	
	@PostMapping("/upload")
	public String requestMethodName(
			@RequestParam("A") float A,
			@RequestParam("B") float B,
			@RequestParam("C") float C,Model model) {
			String ketqua;
			String loaiTamGiac;
		if(A+B>C&&A+B>C&&B+C>A)
		{
			ketqua="Là 3 Cạnh Của 1 tam giác";
			
			if(A*A==B*B+C*C || B*B==A*A+C*C || C*C==A*A+B*B)
			{
				loaiTamGiac="Tam giác vuông";
			}
			else if(A==B && B==C)
			{
				loaiTamGiac="Tam giác Đều";
			}
			else if(A==B || A==C || B==C)
			{
				loaiTamGiac="Tam giác Cân";
			}
			else if(A*A > B*B+C*C || B*B > A*A+C*C || C*C > A*A+B*B)
			{
				loaiTamGiac="Tam giác Tù";
			}
			else
			{
				loaiTamGiac="Tam giác thường";
			}
		}
		else
		{
			ketqua=A+", "+B+", "+C+", "+"không phải Là 3 Cạnh Của 1 tam giác";
			loaiTamGiac="Không phải tam giác";
		}
		
		model.addAttribute("ketqua",ketqua);
		model.addAttribute("loaiTamGiac", loaiTamGiac);
		model.addAttribute("A", A);
		model.addAttribute("B", B);
		model.addAttribute("C", C);
		return "result";
	}
	
}
