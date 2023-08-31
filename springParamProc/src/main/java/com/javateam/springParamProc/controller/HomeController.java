package com.javateam.springParamProc.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javateam.springParamProc.vo.MemberVO;

/**
 * main action
 */
@Controller
public class HomeController {
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class); 
	
	@RequestMapping("/")
	// @RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		log.info("home !");
		
		return "home";
	} //
	
	@RequestMapping(value="action", 
					method = {RequestMethod.POST})
	public void action(Model model,
				       HttpServletRequest request,
				       @RequestParam("name") String name,
				       @RequestParam Map<String, String> map,
				       @RequestBody String str,
				       @ModelAttribute("member") MemberVO member) throws UnsupportedEncodingException {
		
		log.info("action");
		
		model.addAttribute("param1", name);
		request.setAttribute("param2", name);				
		model.addAttribute("param3", map.get("name"));
		
		// @RequestBody는 아래와 같이 URLDecoder.decode()로 한글 변환 처리해야 됨.
		model.addAttribute("param4", URLDecoder.decode(str.split("=")[1], "UTF-8"));
		// model.addAttribute("param4", str.split("=")[1]);
		model.addAttribute("param5", member.getName());
		
	} //
	
	@RequestMapping(value="action2", method=RequestMethod.POST) 
	public ModelAndView action2(@RequestParam("name") String name) {
		
		log.info("ModelAndView action");

		// ModelAndView modelAndView = new ModelAndView("action2");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("action2");

		modelAndView.addObject("param1", name);
		
		return modelAndView;
	} //
	
	@RequestMapping(value = "/formREST", method = RequestMethod.GET)
	public String formREST() {
		
		log.info("formREST !");
		
		return "formREST";
	} //
	
	// since Spring 4.3 
	@GetMapping("/formREST2")
	public String formREST2() {
		
		log.info("formREST2 !");
		
		return "formREST2";
	} //
	
	@RequestMapping(value="action3/name/{name}/grade/{grade}", 
					method= {RequestMethod.GET, RequestMethod.POST})
	public String action3(@PathVariable("name") String name,
						@PathVariable("grade") int grade,
						Model model) {
		
		log.info("PathVariable action");
		log.info("name : " + name);
		log.info("grade : " + grade);
		
		model.addAttribute("msg", "성공");
		return "action3";
	} //
	
}