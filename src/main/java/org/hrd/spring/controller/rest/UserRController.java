package org.hrd.spring.controller.rest;

import java.util.List;

import org.hrd.spring.entities.User;
import org.hrd.spring.entities.responses.HttpMessage;
import org.hrd.spring.entities.responses.Response;
import org.hrd.spring.entities.responses.ResponseHttpStatus;
import org.hrd.spring.entities.responses.ResponseList;
import org.hrd.spring.entities.responses.ResponseRecord;
import org.hrd.spring.entities.responses.Table;
import org.hrd.spring.entities.responses.Transaction;
import org.hrd.spring.entities.responses.failure.ResponseFailure;
import org.hrd.spring.entities.responses.failure.ResponseListFailure;
import org.hrd.spring.entities.responses.failure.ResponseRecordFailure;
import org.hrd.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("v1/api/user")
public class UserRController {

	private HttpStatus httpStatus = HttpStatus.OK;
	private ResponseList<User> responseList;
	
	private UserService userService;

	@Autowired
	public UserRController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * TO DO: find all user
	 * @return
	 */
	@GetMapping
	public ResponseEntity<ResponseList<User>> findAll(){
		responseList = new ResponseList<User>();
		
		try{
			List<User> user = userService.findAll();
			if(!user.isEmpty()){
				responseList = new ResponseList<User>(
						HttpMessage.success(Table.USERS, Transaction.Success.RETRIEVE),
						true,
						user,
						null
						);
			}else{
				httpStatus = HttpStatus.NOT_FOUND;
				responseList = new ResponseListFailure<User>(
						HttpMessage.fail(Table.USERS, Transaction.Fail.RETRIEVE),
						false,
						ResponseHttpStatus.RECORD_NOT_FOUND
						);
			}
		}catch(Exception e){
			e.printStackTrace();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			responseList = new ResponseListFailure<User>(
					HttpMessage.fail(Table.USERS, Transaction.Fail.RETRIEVE),
					false,
					ResponseHttpStatus.INTERNAL_SERVER_ERROR
					);
		}
		return new ResponseEntity<ResponseList<User>>(responseList, httpStatus);
	}
	
	
	/**
	 * TO DO: find user by UUID
	 * @param uuid
	 * @return
	 */
	@PostMapping("/{uuid}")
	public ResponseEntity<ResponseRecord<User>> findByUUID(@PathVariable("uuid")String uuid){
		ResponseRecord<User> responseRecord;
		try{
			User user = userService.findUserByUUID(uuid);
			if(user!=null){
				responseRecord = new ResponseRecord<User>(
						HttpMessage.success(Table.USERS, Transaction.Success.RETRIEVE),
						true,
						user
						);
			}else{
				httpStatus = HttpStatus.NOT_FOUND;
				responseRecord = new ResponseRecordFailure<User>(
						HttpMessage.fail(Table.USERS, Transaction.Fail.RETRIEVE),
						false,
						ResponseHttpStatus.RECORD_NOT_FOUND
						);
				
			}
		}catch(Exception e){
			e.printStackTrace();
			responseRecord = new ResponseRecordFailure<User>(
					HttpMessage.fail(Table.USERS, Transaction.Fail.RETRIEVE),
					false,
					ResponseHttpStatus.INTERNAL_SERVER_ERROR
					);
		}
		return new ResponseEntity<ResponseRecord<User>>(responseRecord, httpStatus);
	}
	
	
	/**
	 * TO DO: delete by uuid
	 * @param uuid
	 * @return
	 */
	@DeleteMapping("delete/{uuid}")
	public ResponseEntity<Response<User>> deleteAllByUUID(@PathVariable("uuid")String uuid){
		Response<User> response;
		try{
			boolean user = userService.deleteAllByUUID(uuid);
			if(user==true){
				response = new Response<User>(
						HttpMessage.success(Table.USERS, Transaction.Success.DELETED),
						true
						);
			}else{
				httpStatus = HttpStatus.NOT_FOUND;
				response = new ResponseFailure<User>(
						HttpMessage.fail(Table.USERS, Transaction.Fail.DELETED),
						false,
						ResponseHttpStatus.RECORD_NOT_FOUND
						);
				
			}
		}catch(Exception e){
			e.printStackTrace();
			response = new ResponseFailure<User>(
					HttpMessage.fail(Table.USERS, Transaction.Fail.DELETED),
					false,
					ResponseHttpStatus.INTERNAL_SERVER_ERROR
					);
		}
		return new ResponseEntity<Response<User>>(response, httpStatus);
	}
	
	/**
	 * TO DO: add user and role 
	 * @param user
	 * @param result
	 * @return
	 */
	@PostMapping("/add-user")
	public ResponseEntity<Response<User>> addUserRole(@RequestBody User user){
		Response<User> response = new Response<User>();
		
			try{
				if(userService.addUserRole(user)){
					response = new Response<User>(
							HttpMessage.success(Table.USERS, Transaction.Success.CREATED), 
							true);
				}else{
					httpStatus = HttpStatus.NOT_ACCEPTABLE;
					response = new ResponseFailure<User>(
							HttpMessage.fail(Table.USERS, Transaction.Fail.CREATED),
							false, 
							ResponseHttpStatus.FAIL_CREATED);
				}
			}catch(Exception e){
				e.printStackTrace();
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				response = new ResponseFailure<User>(
						HttpMessage.fail(Table.USERS, Transaction.Fail.CREATED),
						false, 
						ResponseHttpStatus.INTERNAL_SERVER_ERROR);
			}
		return new ResponseEntity<Response<User>>(response , httpStatus);
	}
	
}
