package org.milad.example.event;

import java.util.*;
import java.util.function.BiConsumer;

// STEP 1: Event and Entity
class MyEntity {
    int id;
    String name;

    public MyEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return "[MyEntity id=" + id + ", name=" + name + "]";
    }
}

class PersistEvent {
    private final Object entity;

    public PersistEvent(Object entity) {
        this.entity = entity;
    }

    public Object getEntity() {
        return entity;
    }
}

// STEP 2: Listener Interface
interface OrmEventListener<E> {
    void onEvent(E event);
}

// STEP 3: A listener implementation
class DefaultPersistListener implements OrmEventListener<PersistEvent> {
    @Override
    public void onEvent(PersistEvent event) {
        Object entity = event.getEntity();
        System.out.println("🎯 [Listener] Persisting entity: " + entity);
    }
}

// STEP 4: Event broadcaster
class EventBroadcaster<T> {
    private final List<T> listeners = new ArrayList<>();

    public void register(T listener) {
        System.out.println("✅ Registered listener: " + listener.getClass().getSimpleName());
        listeners.add(listener);
    }

    public <E> void fireEvent(E event, BiConsumer<T, E> method) {
        System.out.println("🚀 Broadcasting event to " + listeners.size() + " listener(s)");
        for (T listener : listeners) {
            System.out.println("🔄 Invoking method on: " + listener.getClass().getSimpleName());
            method.accept(listener, event); // equivalent to listener.onEvent(event)
        }
    }
}

// STEP 5: Main method to see everything in action
public class EventSystemDemo {
    public static void main(String[] args) {
        // Create broadcaster
        EventBroadcaster<OrmEventListener<PersistEvent>> persistBroadcaster = new EventBroadcaster<>();

        // Register a listener
        persistBroadcaster.register(new DefaultPersistListener());

        // Create an entity and event
        MyEntity entity = new MyEntity(1, "Milad");
        PersistEvent event = new PersistEvent(entity);

        System.out.println("1) Using method reference:");
        persistBroadcaster.fireEvent(event, OrmEventListener::onEvent);

        System.out.println("\n2) Using lambda expression:");
        persistBroadcaster.fireEvent(event, (l, e) -> l.onEvent(e));

        System.out.println("\n3) Using anonymous inner class:");
        persistBroadcaster.fireEvent(event, new BiConsumer<OrmEventListener<PersistEvent>, PersistEvent>() {
            @Override
            public void accept(OrmEventListener<PersistEvent> l, PersistEvent e) {
                l.onEvent(e);
            }
        });
    }
}
