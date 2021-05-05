package com.michal.battleship.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "invitationUrl"
})
@AllArgsConstructor
public class InvitationURLDTO {

    public static final String REGEX = "{idVar}";

    @JsonProperty("invitationUrl")
    private String invitationUrl;

    @JsonProperty("invitationUrl")
    public String getInvitationUrl() {
        return invitationUrl;
    }

    @JsonProperty("invitationUrl")
    public void setInvitationUrl(String url) {
        this.invitationUrl = url;
    }

    public InvitationURLDTO(String INVITATION_URL, long id) {
        this.invitationUrl = INVITATION_URL.replace(REGEX, String.valueOf(id));
    }
}