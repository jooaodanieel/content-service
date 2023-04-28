package it.unibz.archlab.digidojo.content.core;

public interface Broadcaster {
    void emitPostWritten(Post post);

    void emitPostErased(Post post);
}
