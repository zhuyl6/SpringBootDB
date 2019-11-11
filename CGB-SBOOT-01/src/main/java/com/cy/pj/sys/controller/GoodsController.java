package com.cy.pj.sys.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/goods/")
public class GoodsController {

	@RequestMapping("doGoodsUI")
	public String doGoodsUI() {
		return "goods";
	}
	
	
	@RequestMapping("deleteObject")
	@ResponseBody
	public String deleteObject(Integer...ids) {
		return "delete Object ids="+Arrays.toString(ids);
	}
	
	
}
