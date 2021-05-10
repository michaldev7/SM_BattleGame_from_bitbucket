# Battle game backend api

## Dependencies
### Other projects
- none

### Prerequisites if you are backend developer
- OpenJDK 11
- maven

###
####After script will be executed , backend will be running on http://localhost:8080/game
###
1. ###Are there any assumptions in your solution that we should be aware of during the code review?
Answer: I know that packages are organized more like services/dao layer and should be domain layer. But usually applications are connected with database
   so probably there will be request soon to add database then this type of packages will suit to solution.
   
b) Now game board (and ships) are generated using permutation , placed in method:  GameBoard.GenerateAllPositionsInGame
Then , player ships positions are taken. And if game position generated with permutation is free (not used by Player as ship) then this field is marked as sea (place without ship).
   
###2. What technologies have you chosen to implement the backend? Why?
-I used orika mapper for easy separation logic and have dedicated place for mapping.
   -Another tools are coming from javax.validation - this allow me to easy validate incoming request position for given pattern: @Pattern(regexp = "^(([A-z][0-9]+)|([0-9]+))$", message = "Position must match of format: one letter, then numbers").
   -Thanks to that I can do validation as fast I can and thanks to that invalid request are rejected as soon as possible, which will produce less logs if we will add any logging.
   -Another tool is spring boot, maven and java 11. 
   -I used also lombok which provide make POJO classes more readable and with less code (looking from IDE as developer, because during build, lombok will add required additional code).
   -I decided to use also junit 5 for testing.
   -And AtomicInteger because of multi thread context and in multiple games at the same time, AtomicInteger is advantage in performance.
   -I used also postman for testing verification of forcing multiple request
   -I used also kdiff3 to compare changes before commit changes
   -In bigger team, I would like to use upsource for code review
   -I used dedicated feature branches and pull request to keep good practice of development

###3. How would you modify your application if the next feature to implement would be to allow players
   to place their ships on the game board? What new things need to be implemented to make it work?
   
This next feature will be easy to implement because I created abstract class.
So implementing new feature we need to only add new class and do injection in below line (because Board is any abstract class)
new BattleBoard(new GameBoard().drawBattleBoard(type));

Additional, we need to only create one more controller and we can reuse components (for example for validation of positions).
So we can use the same DTO for placing their ships with json:
Request:PUT
Type:json
Content: (one position or all positions at once), for example:
{"position" : "A3"}

###4. Assume that we have to fetch game ID from the external application. What challenges and
potential problems do you see and how would you prepare your application to handle them
properly?
   Answer: I will focus to setup good architecture including timeout, validation of response.
   If this is possible, I will take 10 free game id to have some cache of data for performance reasons.
   Additionally I will setup monitoring to be sure that game is able to connect external serve.
   Another tool will be logging information and package them to tar at midnight, so logs of each day will be separated.
   Additionally I will implement in each error response for player API new message like "ErrorID" which consist of timestamp + unique ID. 
   Thanks to that I will be able to easy find in logs what is the issue if any player will contact me when any error will throw without explanation message.
   I will also create 4 application properties for few profile like "dev,test,UAT,prod" to mock testing data on test and dev environments.
   

###
####Thank you for review this application and for your time.

####Please contact me if you would like to add any new feature or in case of any questions. I am happy to help
Contact: michalsklich@gmail.com