package connecthub.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Expirable { // Interface Segregation
    @JsonIgnore
    public boolean isExpired();
}
