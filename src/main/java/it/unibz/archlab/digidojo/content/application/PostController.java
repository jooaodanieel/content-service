package it.unibz.archlab.digidojo.content.application;

import it.unibz.archlab.digidojo.content.application.dto.CreatePost;
import it.unibz.archlab.digidojo.content.application.dto.PostPreview;
import it.unibz.archlab.digidojo.content.core.Post;
import it.unibz.archlab.digidojo.content.core.PostArchiveItem;
import it.unibz.archlab.digidojo.content.core.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public Post createPost(@RequestBody CreatePost dto) {
        return postService.writePost(dto.getTitle(), dto.getContent(), dto.getSlug());
    }

    @GetMapping
    public List<PostArchiveItem> getArchive() {
        return postService.listArchive();
    }

    @DeleteMapping(path = "/{id}")
    public Post deletePost(@PathVariable("id") String id) {
        return postService.erasePost(id);
    }

    @GetMapping(path = "/{id}/preview")
    public PostPreview previewPost(@PathVariable("id") String id) {
        Post post = postService.readPost(id);

        return PostPreview.of(post);
    }

    @GetMapping(path = "/{id}")
    public Post readPost(@PathVariable("id") String id) {
        return postService.readPost(id);
    }
}
