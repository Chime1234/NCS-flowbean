package com.unionsystems.ncscmb.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenResponse {
    private String token;
    private String expiryDate;

    @JsonProperty("refreshToken")
    private RefreshToken refreshToken;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RefreshToken {
        private String token;
        private String created;
        private String expires;
    }
}
