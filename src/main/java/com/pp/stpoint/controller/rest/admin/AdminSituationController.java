package com.pp.stpoint.controller.rest.admin;

import com.pp.stpoint.dto.SituationAsItemDTO;
import com.pp.stpoint.entity.Situation;
import com.pp.stpoint.service.ExpressionService;
import com.pp.stpoint.service.FileService;
import com.pp.stpoint.service.SituationService;
import java.util.ArrayList;
import java.util.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@RestController
@RequestMapping(value = "/rest/admin/situation")
public class AdminSituationController {

    static final String UPLOAD_PATH = "SITUATION_IMAGE";

    @Autowired
    SituationService situationService;

    @Autowired
    ExpressionService expressionService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    FileService fileService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Collection<Situation>> save(
            @RequestBody Situation situation){
        situationService.save(situation);
        return new ResponseEntity<>(situationService.all(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Collection<Situation>> remove(
            @PathVariable("id") Long id){
        
        situationService.remove(id);
        return new ResponseEntity<>(situationService.all(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Situation> find(
            @PathVariable("id") Long id){
        
        return new ResponseEntity<>(situationService.find(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Collection<Situation>> all(){
        return new ResponseEntity<>(situationService.all(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/upload", method = RequestMethod.POST)
    public ResponseEntity<Collection<Situation>> upload(
            @PathVariable("id") Long id,
            MultipartFile file){
        
        Situation situation = situationService.find(id);

        String url = fileService.update(
                situation.getImageURL(),
                Situation.IMAGE_FOLDER,
                file);

        situation.setImageURL(url);
        situationService.save(situation);
        return new ResponseEntity<>(situationService.all(),
                HttpStatus.OK);
    }
    
    @RequestMapping(value="/asItem", method = RequestMethod.GET)
    public ResponseEntity<Collection<SituationAsItemDTO>> asItem(){
    	ArrayList<SituationAsItemDTO> dtoList = new ArrayList<>();
    	for(Object[] params : situationService.asItem()){
    		dtoList.add(new SituationAsItemDTO(params));
    	}
    	return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
    
    
    @RequestMapping(value="/parents", method = RequestMethod.GET)
    public ResponseEntity<Collection<Situation>> parents(){
    	return new ResponseEntity<>(situationService.parents(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/{id}/situations", method = RequestMethod.GET)
    public ResponseEntity<Collection<Situation>> byParent(@PathVariable("id") Long id){
    	return new ResponseEntity<>(situationService.byParent(id), HttpStatus.OK);
    }
    
    

}
