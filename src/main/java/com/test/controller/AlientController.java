package com.test.controller;

import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import com.example.register.RegisterRepo;
import com.test.entity.Alien;
import com.test.entity.Register;
import com.test.exception.ProductNotfoundException;
import com.test.repository.AlienRepo;
import com.test.service.AlienService;
import com.test.service.StorageService;
import com.test.service.impl.*;

import ch.qos.logback.classic.Logger;
import io.swagger.annotations.ApiOperation;

//@Controller
@RestController
//@RibbonClient(name="custribbon")
public class AlientController

{

	// private final Service storageService;

	private static String PLAN_URL = "http://localhost:9393/microservicetest/test/{mainOutput}";

	@Autowired(required = true)
	AlienRepo repo;

	@Autowired(required = true)
	AlienService service;

	@Autowired
	@Qualifier("restTemplate")
	RestTemplate restTemplate;

//	@Autowired
//	@Qualifier("restTemplate2")
//	RestTemplate restTemplate2;

	// @Autowired(required=true)
	// RegisterRepo registerRepo;

	@Autowired
	OTPService otpService;

	/*
	 * @Autowired public AlientController(StorageService storageService) {
	 * this.storageService = storageService; }
	 */

	private static final Logger logger = (Logger) LoggerFactory.getLogger(AlientController.class);

	@RequestMapping("/test")
	public String home() {
		System.out.println("Hi in home Test");
		return "home";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadFile(@RequestParam("file") MultipartFile file) {

		 // Setting up the path of the file
        String filePath = System.getProperty("user.dir") + "/Uploads" + File.separator + file.getOriginalFilename();
		//String filePath = "G:\\FileUpload";
        String fileUploadStatus;
         
        // Try block to check exceptions
        try {
             
            // Creating an object of FileOutputStream class  
            FileOutputStream fout = new FileOutputStream(filePath);
            fout.write(file.getBytes());
             
            // Closing the connection 
            fout.close();
            fileUploadStatus = "File Uploaded Successfully";
             
        } 
       
        // Catch block to handle exceptions
        catch (Exception e) {
            e.printStackTrace();
            fileUploadStatus =  "Error in uploading file: " + e;
        }
        return fileUploadStatus;
    }

	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> updateProduct(@RequestParam int id) {
		System.out.println("ID value " + id);
		throw new ProductNotfoundException();
	}

	@RequestMapping("/addAlien")
	public String addAlien(Alien alien) {
		System.out.println("In Add Alient");

		repo.save(alien);
		return "home";
	}

	@RequestMapping("/getAlien")
	public ModelAndView getAlien(@RequestParam int id, String name) {
		System.out.println("In get Alient");
		Optional<Alien> alien = repo.findById(id);
		// System.out.println(alien);

		ModelAndView mv = new ModelAndView();
		// mv.addObject("alien", alien);
		// mv.setViewName("output.jsp");

		List<Alien> alien1 = repo.findByLang("java");
		// System.out.println(alien1);
		mv.addObject("alien1", alien1);
		// mv.setViewName("output.jsp");

		List<Alien> alien2 = repo.findByName(name);
		ArrayList al = new ArrayList();

		for (int i = 0; i < alien2.size(); i++) {
			System.out.println("In for loop");
			Alien it = alien2.get(i);
			int id1 = it.getId();
			String name1 = it.getName();
			String lang1 = it.getLang();

			al.add(id1);
			al.add(name1);
			al.add(lang1);
		}

		// alien2.get(id);

		// Alien a= alien2.get(1);
		/*
		 * Stream<Alien> alien3 = alien2.stream(); Stream alien4 = alien3.map(n ->
		 * n.getId());
		 */

		// System.out.println("Print " + a.getId());
		// System.out.println("Print " + a.getName());

		mv.addObject("alien2", alien2);
		mv.setViewName("output");

		// System.out.println(repo.findByNameSorted(name));

		return mv;
	}

	// retrieve user details
	@RequestMapping(path = "/getId")
	public ModelAndView getName(@RequestParam int id, String name) {

		String name1 = service.existsByID(id);

		System.out.println(name1);

		ModelAndView mv = new ModelAndView();
		mv.addObject("name", name1);
		mv.addObject("id", id);
		
		mv.setViewName("name");

		return mv;

	}

	@RequestMapping(path = "/restAlient/{id}")
	@ResponseBody
	public Optional<Alien> getRestAlien(@PathVariable("id") int id, String name) {
		System.out.println("In rest get Alient");
		Optional<Alien> alien = repo.findById(id);
		// System.out.println(alien);
		System.out.println(alien);
		return repo.findById(id);

	}

	@RequestMapping("/allRestAlient")
	@ResponseBody
	public Iterable<Alien> getRestAlien() {

		return repo.findAll();
	}

	@ApiOperation("save the data")
	@PostMapping("/addRestAlien")
	@ResponseBody
	public Alien addRestAlien(Alien alien) {

		logger.info("In addRestAlien Method");
		System.out.println("In AddRest Alient");

		repo.save(alien);
		// return "home.jsp";

		return alien;
	}

	@ApiOperation("save the raw data")
	@PostMapping(path = "/addRestAlienRawData", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Alien addRestAlienRawData(@RequestBody Alien alien) throws Exception {

		// int i =1/0;
		System.out.println("In Add Alient");
		logger.warn("In addRestAlienRawData");
		repo.save(alien);
		// return "home.jsp";

		Optional opt12 = repo.findById(67);
		if (opt12.isPresent()) {
			alien.setId(0);
			return alien;
		} else {
			alien.setId(0);
			return alien;
		}

		// return alien;
	}
	/*
	 * @ExceptionHandler(ArithmeticException.class) public ModelAndView
	 * handleException(ArithmeticException ex) { ModelAndView modelAndView = new
	 * ModelAndView(); modelAndView.setViewName("error");
	 * modelAndView.addObject("message", ex.getMessage()); return modelAndView; }
	 */

	@ApiOperation("Delete the data of selected id")
	@DeleteMapping("/deleteRestAlien/{id}")
	@ResponseBody
	public String deleteRestAlien(Alien alien) {
		// public String deleteRestAlien(@PathVariable int id,@PathVariable String
		// name,@PathVariable String name)
		System.out.println("In delete Alient");

		logger.info("Name value");

		// repo.deleteAll();
		repo.delete(alien);
		// repo.deleteById(id);
		// repo.deleteByName(name);

		// return "home.jsp";

		return "deleted";
	}

	@PutMapping(path = "/updateRestAlien", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Alien saveOrupdateRestAlien(@RequestBody Alien alien) {

		System.out.println("In Update Alient");

		repo.save(alien);
		// return "home.jsp";

		return alien;
	}

	@PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Register onetooneregisterUser(@RequestBody Register register) {
		logger.info("In register method ");
		System.out.println("In register method");

		// List<String> list = registerRepo.findByLastName("test");

		// registerRepo.save(register);

		return register;
	}

	@GetMapping("/viewProfile/{id}")
	public List<Integer> customerProfile(@PathVariable int id) {
		System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
		// CustomerDto customerDto=service.readCustomer(phoneNumber);
		int mainOutput = service.existsByIds(id);

		System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
		// calling friend-micros ervice

		ParameterizedTypeReference<List<Integer>> typeRef = new ParameterizedTypeReference<List<Integer>>() {
		};
		ResponseEntity<List<Integer>> re = restTemplate.exchange(PLAN_URL, HttpMethod.GET, null, typeRef, mainOutput);

		List<Integer> plan_id = re.getBody();

		plan_id.add(mainOutput);

		// List<Integer> friendsContactList=new ArrayList<Integer>();

		// ModelAndView mv = new ModelAndView();
		// mv.addObject("mainOutput",mainOutput);
		// mv.addObject("plan_id",plan_id);

		// friendsContactList.add(plan_id)
		// friendsContactList.add(mainOutput);

		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		// customerDto.setFriendsContactNumbers(friendsContactList);

		System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" + plan_id);

		// calling plan-microservice
		// PlanDTO planDto = restTemplate.getForObject(PLAN_URL, PlanDTO.class,
		// customerDto.getPlanId());

		System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
		// customerDto.setCurrentPlan(planDto);

		System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");

		return plan_id;
	}

	@GetMapping(path = "/getIds")
	public ModelAndView getId(@RequestParam int id, String name) {

		int ids = service.existsByIds(id);

		System.out.println(ids);

		ModelAndView mv = new ModelAndView();
		mv.addObject("name", ids);
		mv.addObject("id", id);
		mv.setViewName("name");

		return mv;

	}

	@RequestMapping("/otp")
	public String getFohpDetails() {
		/*
		 * System.out.println("In Controller");
		 * 
		 * Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"),
		 * System.getenv("TWILIO_AUTH_TOKEN"));
		 * 
		 * Message.creator(new PhoneNumber("+918886131729"), new
		 * PhoneNumber("<FROM number - ie your Twilio number"),
		 * "Hello from Twilio 📞").create();
		 * 
		 * return new ResponseEntity<String>("Message sent successfully",
		 * HttpStatus.OK);
		 */
		return otpService.generateOTP();
	}

	/*
	 * @GetMapping("/upload") public String listUploadedFiles(Model model) throws
	 * IOException {
	 * 
	 * model.addAttribute("files", storageService.loadAll().map( path ->
	 * MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
	 * "serveFile", path.getFileName().toString()).build().toUri().toString())
	 * .collect(Collectors.toList()));
	 * 
	 * return "uploadForm"; }
	 */

	/*
	 * @GetMapping("/files/{filename:.+}")
	 * 
	 * @ResponseBody public ResponseEntity<Resource> serveFile(@PathVariable String
	 * filename) {
	 * 
	 * Resource file = storageService.loadAsResource(filename); return
	 * ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
	 * "attachment; filename=\"" + file.getFilename() + "\"").body(file); }
	 */

	/*
	 * @PostMapping("/uploadFile") public String
	 * handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes
	 * redirectAttributes) {
	 * 
	 * storageService.store(file); redirectAttributes.addFlashAttribute("message",
	 * "You successfully uploaded " + file.getOriginalFilename() + "!");
	 * 
	 * return "redirect:/"; }
	 */

	/*
	 * @ExceptionHandler(StorageFileNotFoundException.class) public
	 * ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc)
	 * { return ResponseEntity.notFound().build(); }
	 */

}
