package dev.mrs.contentcalendar.repository;

import dev.mrs.contentcalendar.model.Content;
import dev.mrs.contentcalendar.model.Status;
import dev.mrs.contentcalendar.model.Type;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {
    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository() {
    }

    public List<Content> findAll(){
        return contentList;
    }

    public Optional<Content> findById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    public boolean existsById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).count() == 1;
    }

    public void save(Content content) {
        contentList.add(content);
    }

    private void init(){
        Content content = new Content(
                1,
                "My First Blog Post",
                "My First Blog Post",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                "http://localhost:8080/blog-post"
        );

        contentList.add(content);
    }
}
