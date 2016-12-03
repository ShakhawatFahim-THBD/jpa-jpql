package net.therap.command;

import java.util.HashMap;
import java.util.Map;

/**
 * @author safat
 * @since 12/2/16.
 */
@SuppressWarnings("SuspiciousMethodCalls")
public enum Status {

    IN_PREP(1, "In Prep"),
    SUBMITTED(2, "Submitted"),
    APPROVED(3, "Approved");

    private int id;
    private String label;

    Status(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    private static final Map<Integer, Status> ID_TO_STATUS_MAP = new HashMap<>();
    private static final Map<Status, Integer> STATUS_TO_ID_MAP = new HashMap<>();

    static {
        for (Status status : Status.values()) {
            ID_TO_STATUS_MAP.put(status.getId(), status);
            STATUS_TO_ID_MAP.put(status, status.getId());
        }
    }

    public static Status getStatusById(Integer id) {
        return ID_TO_STATUS_MAP.get(id);
    }

    public static Integer getIdByStatus(Status status) {
        return STATUS_TO_ID_MAP.get(status);
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
