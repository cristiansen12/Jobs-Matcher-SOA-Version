package com.databaseRestApi.springboot.controller;

import java.util.List;

import com.databaseRestApi.springboot.model.Profile;
import com.databaseRestApi.springboot.model.User;
import com.databaseRestApi.springboot.service.JobService;
import com.databaseRestApi.springboot.service.ProfileService;
import com.databaseRestApi.springboot.service.UserService;
import com.databaseRestApi.springboot.util.CustomErrorType;
import com.databaseRestApi.springboot.model.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class DatabaseRestApiController {

	public static final Logger logger = LoggerFactory.getLogger(DatabaseRestApiController.class);

	@Autowired
	UserService userService; //Service which will do all data retrieval/manipulation work

	@Autowired
	JobService jobService; //Service which will do all data retrieval/manipulation work

	@Autowired
	ProfileService profileService;

	// -------------------Retrieve All Users---------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// -------------------Retrieve Single User------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		User user = userService.findById(id);
		if (user == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("User with id " + id
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// -------------------Create a User-------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", user);

		if (userService.isUserExist(user)) {
			logger.error("Unable to create. A User with name {} already exist", user.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " +
			user.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a User ------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		logger.info("Updating User with id {}", id);

		User currentUser = userService.findById(id);

		if (currentUser == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());
		currentUser.setAge(user.getAge());
		currentUser.setSalary(user.getSalary());

		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	// ------------------- Delete a User-----------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id {}", id);

		User user = userService.findById(id);
		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Users-----------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		logger.info("Deleting All Users");

		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}


	////////// Jobs

	@RequestMapping(value = "/job/", method = RequestMethod.GET)
	public ResponseEntity<List<Job>> listAllJobs() {
		List<Job> jobs = jobService.findAllJobs();
		if (jobs.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
	}

	// -------------------Retrieve Single Job------------------------------------------

	@RequestMapping(value = "/job/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getJob(@PathVariable("id") long id) {
		logger.info("Fetching Job with id {}", id);
		Job job = jobService.findById(id);
		if (job == null) {
			logger.error("Job with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Job with id " + id
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}

	// -------------------Create a Job-------------------------------------------

	@RequestMapping(value = "/job/", method = RequestMethod.POST)
	public ResponseEntity<?> createJob(@RequestBody Job job, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Job : {}", job);

		if (jobService.isJobExist(job)) {
			logger.error("Unable to create. A Job with name {} already exist", job.getTitle());
			return new ResponseEntity(new CustomErrorType("Unable to create. A Job with name " +
					job.getTitle() + " already exist."),HttpStatus.CONFLICT);
		}
		jobService.saveJob(job);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/job/{id}").buildAndExpand(job.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}


	// ------------------- Update a Job ------------------------------------------------

	@RequestMapping(value = "/job/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateJob(@PathVariable("id") long id, @RequestBody Job job) {
		logger.info("Updating Job with id {}", id);

		Job currentJob = jobService.findById(id);

		if (currentJob == null) {
			logger.error("Unable to update. Job with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Job with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentJob.setCompany(currentJob.getCompany());
		currentJob.setTitle(currentJob.getTitle());
		currentJob.setDescription(currentJob.getDescription());
		currentJob.setUser_id(currentJob.getUser_id());

		jobService.updateJob(currentJob);
		return new ResponseEntity<Job>(currentJob, HttpStatus.OK);
	}

	// ------------------- Delete a Job-----------------------------------------

	@RequestMapping(value = "/job/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteJob(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Job with id {}", id);

		Job job = jobService.findById(id);
		if (job == null) {
			logger.error("Unable to delete. Job with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Job with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		jobService.deleteJobById(id);
		return new ResponseEntity<Job>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Jobs-----------------------------

	@RequestMapping(value = "/job/", method = RequestMethod.DELETE)
	public ResponseEntity<Job> deleteAllJobs() {
		logger.info("Deleting All Jobs");

		jobService.deleteAllJobs();
		return new ResponseEntity<Job>(HttpStatus.NO_CONTENT);
	}


	////////// Profiles

	@RequestMapping(value = "/profile/", method = RequestMethod.GET)
	public ResponseEntity<List<Profile>> listAllProfiles() {
		List<Profile> profiles = profileService.findAllProfiles();
		if (profiles.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Profile>>(profiles, HttpStatus.OK);
	}

	// -------------------Retrieve Single Profile------------------------------------------

	@RequestMapping(value = "/profile/{userId}", method = RequestMethod.GET)
	public ResponseEntity<?> getProfile(@PathVariable("userId") long userId) {
		logger.info("Fetching Profile with userId {}", userId);
		Profile profile = profileService.findByUserId(userId);
		if (profile == null) {
			logger.error("Profile with userId {} not found.", userId);
			return new ResponseEntity(new CustomErrorType("Profile with userId " + userId
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Profile>(profile, HttpStatus.OK);
	}

	// -------------------Create a Profile-------------------------------------------

	@RequestMapping(value = "/profile/", method = RequestMethod.POST)
	public ResponseEntity<?> createProfile(@RequestBody Profile profile, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Profile : {}", profile);

		if (profileService.isProfileExist(profile)) {
			logger.error("Unable to create. A Profile with userId {} already exist", profile.getUserId());
			return new ResponseEntity(new CustomErrorType("Unable to create. A Profile with userId " +
					profile.getUserId() + " already exist."),HttpStatus.CONFLICT);
		}
		profileService.saveProfile(profile);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/profile/{userId}").buildAndExpand(profile.getUserId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}


	// ------------------- Update a Profile ------------------------------------------------

	@RequestMapping(value = "/profile/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateProfile(@PathVariable("userId") long userId, @RequestBody Profile profile) {
		logger.info("Updating Profile with userId {}", userId);

		Profile currentProfile = profileService.findByUserId(userId);

		if (currentProfile == null) {
			logger.error("Unable to update. Profile with userId {} not found.", userId);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Profile with userId " + userId + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentProfile.setContent(currentProfile.getContent());

		profileService.updateProfile(currentProfile);
		return new ResponseEntity<Profile>(currentProfile, HttpStatus.OK);
	}

	// ------------------- Delete a Profile-----------------------------------------

	@RequestMapping(value = "/profile/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProfile(@PathVariable("userId") long userId) {
		logger.info("Fetching & Deleting Profile with id {}", userId);

		Profile profile = profileService.findByUserId(userId);
		if (profile == null) {
			logger.error("Unable to delete. Profile with id {} not found.", userId);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Profile with userId " + userId + " not found."),
					HttpStatus.NOT_FOUND);
		}
		profileService.deleteProfileById(userId);
		return new ResponseEntity<Job>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Profile-----------------------------

	@RequestMapping(value = "/profile/", method = RequestMethod.DELETE)
	public ResponseEntity<Profile> deleteAllProfiles() {
		logger.info("Deleting All Profiles");

		profileService.deleteAllProfiles();
		return new ResponseEntity<Profile>(HttpStatus.NO_CONTENT);
	}


}