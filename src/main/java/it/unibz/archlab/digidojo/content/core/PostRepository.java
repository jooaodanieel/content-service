package it.unibz.archlab.digidojo.content.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
    Optional<Post> findByTitle(String title);

    @Query(
            value = "SELECT id, title FROM post",
            nativeQuery = true
    )
    List<PostArchiveItem> getArchive();
}
