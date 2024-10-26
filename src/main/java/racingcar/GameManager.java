package racingcar;

import constant.Constants;
import inspector.InspectRaceResult;
import utils.ContentParser;

public class GameManager {
    private String[] playerGroup;
    private RacingCar[] racingCarGroup;
    private int playerTurn;
    private int currentTurn = 0;

    public void execute() {
        playerSetting();
        raceSetting();
    }

    private void playerSetting(){
        playerGroup = playerGroupSetting();
        playerTurn = playerTurnSetting();
        racingCarGroup = RacingCarFactory.createRacingCarGroup(playerGroup);
    }

    private String[] playerGroupSetting(){
        String inputContent = InputManager.inputPlayerGroup();
        return ContentParser.parsingContentToGroup(inputContent);
    }

    private int playerTurnSetting(){
        return InputManager.inputTurn();
    }

    private void raceSetting(){
        OutputManager.printBlankLine();
        System.out.println(Constants.RACING_START_MESSAGE);
        while(currentTurn < playerTurn){
            racing();
            OutputManager.printCurrentResult(racingCarGroup);
            currentTurn++;
        }
        InspectRaceResult inspectRaceResult = new InspectRaceResult();
        inspectRaceResult.inspecting(racingCarGroup);
    }

    private void racing(){
        for(RacingCar racingCar : racingCarGroup){
            racingCar.addDistance();
        }
    }
}
