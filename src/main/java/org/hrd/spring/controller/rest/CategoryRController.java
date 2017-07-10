package org.hrd.spring.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hrd.spring.entities.Category;
import org.hrd.spring.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Tola Created Date: 2017/07/03
 *
 */

@RestController
@RequestMapping("/v1/api/category")
public class CategoryRController {

	private HttpStatus httpStatus = HttpStatus.OK;
	private Map<String, Object> map = null;

	private CategoryService categoryService;

	@Autowired
	public CategoryRController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public ResponseEntity<Map<String, Object>> findAll() {
		List<Category> categories = null;
		map = new HashMap<String, Object>();
		try {
			categories = categoryService.findAll();
			if (!categories.isEmpty()) {
				map.put("data", categories);
				map.put("message", "Categories have been found.");
				map.put("status", true);
				map.put("code", 200);
			} else {
				map.put("data", categories);
				map.put("message", "Categories have not been found.");
				map.put("status", false);
				httpStatus = HttpStatus.NOT_FOUND;
				map.put("code", 404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", false);
			map.put("message", "Something is broken. Please contact to developer team!");
			map.put("code", 500);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(map, httpStatus);
	}

	@GetMapping("/{uuid}")
	public ResponseEntity<Map<String, Object>> findByUUID(@PathVariable("uuid") String uuid) {
		
		map = new HashMap<String, Object>();
		try {
			Category category = categoryService.findByUUID(uuid);
			if (category != null) {
				map.put("data", category);
				map.put("message", "Categories have been found.");
				map.put("status", true);
				map.put("code", 200);
			} else {
				map.put("data", category);
				map.put("message", "Categories have not been found.");
				map.put("status", false);
				httpStatus = httpStatus.NOT_FOUND;
				map.put("code", 404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", false);
			map.put("message", "Something is broken. Please contact to developer team!");
			map.put("code", 500);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(map, httpStatus);
	}

	@GetMapping("/save")
	public ResponseEntity<Map<String, Object>> save(@RequestBody Category category) {
		
		map = new HashMap<String, Object>();
		try {
			
			if (categoryService.save(category)) {
				map.put("message", "Categories have been found.");
				map.put("status", true);
				map.put("code", 200);
			} else {
				map.put("message", "Categories have not been found.");
				map.put("status", false);
				httpStatus = httpStatus.NOT_FOUND;
				map.put("code", 404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", false);
			map.put("message", "Something is broken. Please contact to developer team!");
			map.put("code", 500);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(map, httpStatus);
	}
	
	@GetMapping("/update/status/{status}/{uuid}")
	public ResponseEntity<Map<String, Object>> update(
			@PathVariable("status") String status,
			@PathVariable("uuid") String uuid
			) {
		
		map = new HashMap<String, Object>();
		try {
			if (categoryService.updateStatusByUUID(status, uuid)) {
				map.put("message", "Categories have been found.");
				map.put("status", true);
				map.put("code", 200);
			} else {
				map.put("message", "Categories have not been found.");
				map.put("status", false);
				httpStatus = httpStatus.NOT_FOUND;
				map.put("code", 404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", false);
			map.put("message", "Something is broken. Please contact to developer team!");
			map.put("code", 500);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(map, httpStatus);
	}
}
