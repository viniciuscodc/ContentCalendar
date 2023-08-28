package dev.mrs.contentcalendar.controller;

import dev.mrs.contentcalendar.model.Content;
import dev.mrs.contentcalendar.repository.ContentCollectionRepository;
import dev.mrs.contentcalendar.repository.ContentJdbcTemplateRepository;
import dev.mrs.contentcalendar.repository.ContentRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {
    private final ContentCollectionRepository collectionRepository;
    private final ContentJdbcTemplateRepository contentJdbcTemplateRepository;
    private final ContentRepository contentRepository;

    public ContentController(ContentCollectionRepository collectionRepository, ContentJdbcTemplateRepository contentJdbcTemplateRepository, ContentRepository contentRepository) {
        this.collectionRepository = collectionRepository;
        this.contentJdbcTemplateRepository = contentJdbcTemplateRepository;
        this.contentRepository = contentRepository;
    }

    @GetMapping
    public List<Content> findAll(){
        return contentJdbcTemplateRepository.getAllContent();
    }

    @GetMapping("/filter/{keyword}")
    public List<Content> findByTitle(@PathVariable String keyword){
        return contentRepository.findAllByTitleContains(keyword);
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id) {
        return collectionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found."));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@Valid @RequestBody Content content) {
        collectionRepository.save(content);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable Integer id) {
        if(!collectionRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found.");
        }
        collectionRepository.save(content);
    }
}
