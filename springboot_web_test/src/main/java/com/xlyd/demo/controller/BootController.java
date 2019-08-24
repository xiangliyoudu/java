package com.xlyd.demo.controller;

import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xlyd.demo.dao.ProductDao;
import com.xlyd.demo.dao.ProductMapper;
import com.xlyd.demo.entity.Product;


@Controller
@RequestMapping("/product")
public class BootController {

	private static final Logger log = LoggerFactory.getLogger(BootController.class);
	
	@RequestMapping("/index")
	public String indexPage(Model model) {
		model.addAttribute("user", "thymeleaf user");

		List<Product> prods = new ArrayList<Product>();
		prods.add(new Product("shouji", 699, 999));
		prods.add(new Product("diannao", 1288, 500));
		prods.add(new Product("shuiguo", 43, 234));
		model.addAttribute("prods", prods);
		log.info("prods size: " + prods.size());
		return "hello";
	}

	@Autowired
	ProductDao dao;

	@ResponseBody
	@RequestMapping("/list")
	public List<Product> listPage() {
		List<Product> prods = new ArrayList<>();
		prods = dao.findAll();
		return prods;
	}

	@Autowired
	ProductMapper mapper;

	@ResponseBody
	@RequestMapping("/findall")
	public Object getAll() {
		List<Product> ps = mapper.findAll();
		return ps;
	}

}
