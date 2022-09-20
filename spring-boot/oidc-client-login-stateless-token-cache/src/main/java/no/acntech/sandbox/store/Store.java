package no.acntech.sandbox.store;

public interface Store<S, T> {

    T load(S key);

    void save(S key, T value);

    void remove(S key);

    boolean contains(S key);
}
