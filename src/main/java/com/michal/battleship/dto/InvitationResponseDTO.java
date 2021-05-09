package com.michal.battleship.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "invitationUrl"
})
public class InvitationResponseDTO {

    public static final String REGEX = "{idVar}";

    /*
    I verified sonar hints, but making this variable final, will destroy correct content of API responses
     */
    @JsonProperty("invitationUrl")
    private String invitationUrl;

    public InvitationResponseDTO(String invitationApiUrl, long id) {
        this.invitationUrl = invitationApiUrl.replace(REGEX, String.valueOf(id));
    }
}