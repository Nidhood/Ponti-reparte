package javeriana.pontireparte.project.dto;

import java.util.UUID;

public class OrderIdSendDTO {
    private UUID id;

    // Constructors:
    public OrderIdSendDTO() {
    }

    public OrderIdSendDTO(UUID id) {
        this.id = id;
    }

    // Getters & setters:

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    // toString:
    @Override
    public String toString() {
        return "OrderIdSendDTO{" +
                "id=" + id +
                '}';
    }
}
