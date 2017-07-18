package com.pp.stpoint.controller.rest.admin;

import com.pp.stpoint.entity.ArticleMedia;
import com.pp.stpoint.service.FileService;
import com.pp.stpoint.service.ArticleMediaService;
import com.pp.stpoint.service.ArticleService;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@RestController
@RequestMapping(value = "/rest/admin/situation/article")
public class AdminArticleMediaController {

    @Autowired
    ArticleService articleService;

	@Autowired
    ArticleMediaService articleMediaService;
	
    @Autowired
    FileService fileService;

    @RequestMapping(value = "/{articleId}/media/{articleMediaId}/upload", method = RequestMethod.POST)
    public ResponseEntity<Collection<ArticleMedia>> upload(
            @PathVariable("articleId") Long articleId,
            @PathVariable("articleMediaId") Long articleMediaId,
            MultipartFile file){
				
			ArticleMedia articleMedia = articleMediaService.find(articleMediaId);
			return upload(articleId, articleMedia, file);
    }
	
	
	 @RequestMapping(value = "/{articleId}/media/upload", method = RequestMethod.POST)
    public ResponseEntity<Collection<ArticleMedia>> upload(
            @PathVariable("articleId") Long articleId,
            MultipartFile file){
				
		ArticleMedia articleMedia = new ArticleMedia(articleService.find(articleId));
		return upload(articleId, articleMedia, file);
        
    }
	
	@RequestMapping(value = "/{id}/media", method = RequestMethod.GET)
    public ResponseEntity<Collection<ArticleMedia>> all(
            @PathVariable("id") Long id){
        return new ResponseEntity<>(articleService.find(id).getMedia(), HttpStatus.OK);
    }
	
	private ResponseEntity<Collection<ArticleMedia>> upload(Long id, ArticleMedia articleMedia, MultipartFile file){
		String url = fileService.update(
                articleMedia.getURL(),
                ArticleMedia.FOLDER,
                file);
        articleMedia.setURL(url);
        articleMediaService.save(articleMedia);
        return all(id);
		
	}
}
