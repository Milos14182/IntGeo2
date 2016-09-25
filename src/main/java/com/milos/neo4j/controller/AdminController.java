package com.milos.neo4j.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.milos.neo4j.data.AnimalData;
import com.milos.neo4j.data.CityData;
import com.milos.neo4j.data.LakeData;
import com.milos.neo4j.data.MountainData;
import com.milos.neo4j.data.PlantData;
import com.milos.neo4j.data.RiverData;
import com.milos.neo4j.data.StateData;
import com.milos.neo4j.services.AdminService;
import com.milos.neo4j.services.AnimalService;
import com.milos.neo4j.services.CityService;
import com.milos.neo4j.services.LakeService;
import com.milos.neo4j.services.MountainService;
import com.milos.neo4j.services.PlantService;
import com.milos.neo4j.services.RiverService;
import com.milos.neo4j.services.StateService;

@Controller
public class AdminController {
	@Autowired
	AdminService adminService;
	
	@Autowired
	AnimalService animalService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	StateService stateService;
	
	@Autowired
	PlantService plantService;
	
	@Autowired
	RiverService riverService;
	
	@Autowired
	LakeService lakeService;
	
	@Autowired
	MountainService mountainService;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminNodesList(HttpServletRequest request) {
		Set<AnimalData> inactiveAnimalDatas = adminService.getInactiveAnimalDatas();
		Set<CityData> inactiveCityDatas = adminService.getInactiveCityDatas();
		Set<StateData> inactiveStateDatas = adminService.getInactiveStateDatas();
		Set<MountainData> inactiveMountainDatas = adminService.getInactiveMountainDatas();
		Set<RiverData> inactiveRiverDatas = adminService.getInactiveRiverDatas();
		Set<LakeData> inactiveLakeDatas = adminService.getInactiveLakeDatas();
		Set<PlantData> inactivePlantDatas = adminService.getInactivePlantDatas();
		request.setAttribute("animalDatas", inactiveAnimalDatas);
		request.setAttribute("cityDatas", inactiveCityDatas);
		request.setAttribute("stateDatas", inactiveStateDatas);
		request.setAttribute("mountainDatas", inactiveMountainDatas);
		request.setAttribute("riverDatas", inactiveRiverDatas);
		request.setAttribute("lakeDatas", inactiveLakeDatas);
		request.setAttribute("plantDatas", inactivePlantDatas);
		return "admin";
	}

	@RequestMapping(value = "/admin/accept/{model}/{id}", method = RequestMethod.GET)
	public String acceptModelNode(@PathVariable("model") String model, @PathVariable("id") Long id) {
		switch (model) {
		case "city":
			adminService.setCityDataActive(id, true);
			break;
		case "state":
			adminService.setStateDataActive(id, true);
			break;
		case "mountain":
			adminService.setMountainDataActive(id, true);
			break;
		case "lake":
			adminService.setLakeDataActive(id, true);
			break;
		case "plant":
			adminService.setPlantDataActive(id, true);
			break;
		case "river":
			adminService.setRiverDataActive(id, true);
			break;
		case "animal":
			adminService.setAnimalDataActive(id, true);
			break;
		}
		return "redirect:/admin";
	}

	@RequestMapping(value = "/admin/reject/{model}/{id}", method = RequestMethod.GET)
	public String rejectModelNode(@PathVariable("model") String model, @PathVariable("id") Long id) {
		switch (model) {
		case "city":
			adminService.removeCity(id);
			break;
		case "state":
			adminService.removeState(id);
			;
			break;
		case "mountain":
			adminService.removeMountain(id);
			break;
		case "lake":
			adminService.removelake(id);
			break;
		case "plant":
			adminService.removePlant(id);
			break;
		case "river":
			adminService.removeRiver(id);
			break;
		case "animal":
			adminService.removeAnimal(id);
			break;
		}
		return "redirect:/admin";
	}

	@RequestMapping(value = "/admin/edit/{type}/{id}", method = RequestMethod.GET)
	public String editModelNode(@PathVariable("type") String type, @PathVariable("id") Long id, Model model,
			HttpServletRequest request) {
		switch (type) {
		case "city":
			CityData cityData = adminService.getCityById(id);
			request.setAttribute("code", "city");
			model.addAttribute("type", cityData);
			break;
		case "state":
			StateData stateData = adminService.getStateById(id);
			request.setAttribute("code", "state");
			model.addAttribute("type", stateData);
			break;
		case "mountain":
			MountainData mountainData = adminService.getMountainById(id);
			request.setAttribute("code", "mountain");
			model.addAttribute("type", mountainData);
			break;
		case "lake":
			LakeData lakeData = adminService.getLakeById(id);
			request.setAttribute("code", "lake");
			model.addAttribute("type", lakeData);
			break;
		case "plant":
			PlantData plantData = adminService.getPlantById(id);
			request.setAttribute("code", "plant");
			model.addAttribute("type", plantData);
			break;
		case "river":
			RiverData riverData = adminService.getRiverById(id);
			request.setAttribute("code", "river");
			model.addAttribute("type", riverData);
			break;
		case "animal":
			AnimalData animalData = adminService.getAnimalById(id);
			request.setAttribute("code", "animal");
			model.addAttribute("type", animalData);
			break;
		}
		request.setAttribute("states", stateService.getAllStates());
		return "editNode";
	}
	
	@RequestMapping (value = "/admin/edit/city", method = RequestMethod.POST)
	public String saveEditedNode(@ModelAttribute("type") CityData cityData) {
		cityService.saveCity(cityData);
		return "redirect:/admin";
	}
	
	@RequestMapping (value = "/admin/edit/mountain", method = RequestMethod.POST)
	public String saveEditedNode(@ModelAttribute("type") MountainData mountainData) {
		mountainService.saveMountain(mountainData);
		return "redirect:/admin";
	}
	
	@RequestMapping (value = "/admin/edit/lake", method = RequestMethod.POST)
	public String saveEditedNode(@ModelAttribute("type") LakeData lakeData) {
		lakeService.saveLake(lakeData);
		return "redirect:/admin";
	}
	
	@RequestMapping (value = "/admin/edit/plant", method = RequestMethod.POST)
	public String saveEditedNode(@ModelAttribute("type") PlantData plantData) {
		plantService.savePlant(plantData);
		return "redirect:/admin";
	}
	
	@RequestMapping (value = "/admin/edit/animal", method = RequestMethod.POST)
	public String saveEditedNode(@ModelAttribute("type") AnimalData animalData) {
		animalService.saveAnimal(animalData);
		return "redirect:/admin";
	}
	
	@RequestMapping (value = "/admin/edit/state", method = RequestMethod.POST)
	public String saveEditedNode(@ModelAttribute("type") StateData stateData) {
		stateService.saveState(stateData);
		return "redirect:/admin";
	}
	
	@RequestMapping (value = "/admin/edit/river", method = RequestMethod.POST)
	public String saveEditedNode(@ModelAttribute("type") RiverData riverData) {
		riverService.saveRiver(riverData);
		return "redirect:/admin";
	}
}
