package com.michal.battleship.domain;

import com.michal.battleship.domain.type.ShipType;
import com.michal.battleship.domain.type.PlayerType;
import com.michal.battleship.generic.GameConfig;
import com.michal.battleship.service.GameService;
import com.michal.battleship.service.impl.GameServiceImpl;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate
@ActiveProfiles("test")
class GameTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    GameService service;

    @BeforeEach
    public void init() {
        this.service = new GameServiceImpl();
        ReflectionTestUtils.setField(service, "InvitationURL", "/game/{idVar}/join");
    }

    @Test
    void newPlayerShouldHaveCalculatedGameBoard() {
        Player p = new Player(PlayerType.HOST);
        Assertions.assertNotNull(p.getBattleBoard());
        Map<String, ShipType> gameBoard = p.getBattleBoard().getGameBoard();
        Assertions.assertNotNull(gameBoard);
        Assertions.assertEquals(gameBoard.size(), GameConfig.GAME_SIZE * GameConfig.GAME_SIZE);
        Assertions.assertTrue(gameBoard.containsValue(ShipType.ONE_DECKER));
        Assertions.assertTrue(gameBoard.containsValue(ShipType.TWO_DECKER));
        Assertions.assertTrue(gameBoard.containsValue(ShipType.THREE_DECKER));
        Assertions.assertTrue(gameBoard.containsValue(ShipType.FOUR_DECKER));
    }

    @Test
    @DisplayName("When game is created, then http response 200 and headers contain token and Response Entity returned InvitationDTO URL")
    void shouldCreateNewGameWithinInvitationURL2() throws JSONException {
        String expectedJson = "{\"invitationUrl\":\"/game/1/join\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("/game", HttpMethod.POST, entity, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertTrue(response.getHeaders().containsKey(GameConfig.SET_TOKEN_KEY), "When game is created, the header 'Set-Auth-Token' is required");
        JSONAssert.assertEquals(expectedJson, response.getBody(), false);
    }

}