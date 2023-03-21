package me.whiteship.practice.optional;

import java.util.Optional;

public class OnlineClass {

    private Integer id;

    private String title;

    private boolean closed;

    private Progress progress;

    // ofNullable(V) : V가 null을 포함할 수 있음
    // of(V) : V가 null을 포함하면 그대로 NPE 발생
    public Optional<Progress> getProgress() {
        return Optional.ofNullable(progress);
    }

    public void setProgress(Optional<Progress> progress) {
        if (progress != null) {
            progress.ifPresent(p -> this.progress = p);
        }
    }

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public OnlineClass(Integer id, String title, boolean closed, Optional<Progress> progress)  {
        this.id = id;
        this.title = title;
        this.closed = closed;
        progress.orElseThrow();
        this.progress = progress.get();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
