package it.unibz.archlab.digidojo.content.core;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private Broadcaster broadcaster;

    public Post writePost(String title, String content, String slug) {
        if (postTitleExists(title)) {
            throw new IllegalArgumentException("Post title already exists - " + title);
        }

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setSlug(slug);

        post = postRepository.save(post);

        broadcaster.emitPostWritten(post);

        return post;
    }

    public Post readPost(String uuid) {
        return retrievePost(uuid);
    }

    public Post erasePost(String uuid) {
        Post post = retrievePost(uuid);

        postRepository.delete(post);

        broadcaster.emitPostErased(post);

        return post;
    }

    public List<PostArchiveItem> listArchive() {
        return postRepository.getArchive();
    }

    private Post retrievePost(String uuid) {
        Optional<Post> maybePost = postRepository.findById(uuid);

        if (maybePost.isEmpty()) {
            throw new IllegalArgumentException("Post not found - " + uuid);
        }
        return maybePost.get();
    }

    private boolean postTitleExists(String title) {
        Optional<Post> maybePost = postRepository.findByTitle(title);

        return maybePost.isPresent();
    }
}
