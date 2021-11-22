package petAPI;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ProcessList {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    @Getter
    private String procces;
}
