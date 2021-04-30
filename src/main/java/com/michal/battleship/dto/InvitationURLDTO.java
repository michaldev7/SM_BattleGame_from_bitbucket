package com.michal.battleship.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "invitationUrl"
})
@AllArgsConstructor
public class InvitationURLDTO {

    private static final String REGEX = "{idVar}";

    @Value("${game.invitation.url}")
    private String INVITATION_URL;

    @JsonProperty("invitationUrl")
    private String invitationUrl;

    @JsonProperty("invitationUrl")
    public String getInvitationUrl() {
        return invitationUrl;
    }

    @JsonProperty("invitationUrl")
    public void setInvitationUrl(String gameId) {
        this.invitationUrl = INVITATION_URL.replace(REGEX, gameId);
    }

    public InvitationURLDTO(String id) {
        this.invitationUrl = INVITATION_URL.replace(REGEX, id);
    }

    //    public final void init() {
//        String endpointJoin = INVITATION_URL.replace(REGEX, String.valueOf(gameId));
//        return new StringBuilder().append(appURL).append(endpointJoin).toString();
//    }

}