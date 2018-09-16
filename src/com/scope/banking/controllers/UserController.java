package com.scope.banking.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.scope.banking.models.Scope_Asset;
import com.scope.banking.models.Scope_user;
import com.scope.banking.models.Transaction;
import com.scope.banking.services.UserService;
import com.scope.banking.services.assetService;
import com.scope.banking.services.transactionService;

@RestController
public class UserController {
	
	@Autowired
	private MessageSource msgSrc;	
	
	@Autowired
	private UserService UService;
	
	@Autowired
	private assetService aService;
	
	@Autowired
	private transactionService tService;
	
	@GetMapping(path="/messageIntnl")
	public String msgInternationalized( @RequestHeader(name="Accept-Language", required=false) Locale locale){
		return msgSrc.getMessage("good.morning.message", null, locale);
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(path="/", method=RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public String home(){
		return "Asset Management Rest API is ready to rock.";
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(path="/addUser", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean addUserInfo(@RequestBody Scope_user user){
		System.out.println(user.getUserName());
		return UService.addUser(user);
		
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(path="/addAsset", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean addNewAsset(@RequestBody Scope_Asset asset){
		
		return aService.addAsset(asset);
		
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(path="/getAssetByUser/{uName}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Scope_Asset findAssetByUser(@PathVariable String uName){
		System.out.println(uName);
		return aService.findAsset(uName);
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(path="/addTransaction/{bName}/{sName}/{aId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Transaction addTransaction(@PathVariable String bName, @PathVariable String sName, @PathVariable Integer aId){
		System.out.println(bName+" "+sName+" "+aId);
		return tService.addTransaction(bName, sName, aId);
	}	
	
}
