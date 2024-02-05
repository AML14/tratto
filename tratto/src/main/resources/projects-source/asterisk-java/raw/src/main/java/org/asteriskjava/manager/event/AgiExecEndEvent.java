package org.asteriskjava.manager.event;

/**
 * Created by plhk on 1/15/15.
 */
public class AgiExecEndEvent extends AgiExecEvent {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String linkedid;

    private String language;

    public AgiExecEndEvent(Object source) {
        super(source);
        setSubEvent(SUB_EVENT_END);
    }

    public String getLinkedid() {
        return linkedid;
    }

    public void setLinkedid(String linkedid) {
        this.linkedid = linkedid;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
