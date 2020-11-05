package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Level.Player;
import Level.PlayerListener;
import Maps.LevelThree;
import Maps.LevelTwo;
import Maps.TestMap;
import Players.Cat;
import SpriteFont.SpriteFont;
import Utils.Stopwatch;

import java.awt.*;

// This class is for when the platformer game is actually being played
public class PlayLevelScreen extends Screen implements PlayerListener {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected Stopwatch screenTimer = new Stopwatch();
    protected LevelClearedScreen levelClearedScreen;
    protected LevelLoseScreen levelLoseScreen;
    protected int currentLevel = 0;

    private boolean isGamePaused = false;
    private SpriteFont pauseLabel;
    private KeyLocker keyLocker = new KeyLocker();
    private final Key pauseKey = Key.P;

    //Pause Screen Info and Buttons
    protected int currentMenuItemHovered = 1; // current menu item being "hovered" over
    protected int menuItemSelected = -1;
    protected SpriteFont pauseHeader;
    protected SpriteFont settingsButton;
    protected SpriteFont mainMenuButton;
    protected Stopwatch keyTimer = new Stopwatch();
    protected int pointerLocationX, pointerLocationY;
    //settings menu
    protected boolean settingsActive = false;
    protected SpriteFont volumeLevel;
    protected SpriteFont aspectRatioLevel;


    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;

        //Pause Screen Initialization
        pauseHeader = new SpriteFont("PAUSED", 300, 100, "Comic Sans", 50, new Color(255, 255, 255));
        pauseHeader.setOutlineColor(Color.black);
        pauseHeader.setOutlineThickness(3);
        settingsButton = new SpriteFont("SETTINGS", 130, 250, "Comic Sans", 30, new Color (49, 207,240));
        settingsButton.setOutlineColor(Color.black);
        settingsButton.setOutlineThickness(3);
        mainMenuButton = new SpriteFont("MAIN MENU", 120, 330, "Comic Sans", 30, new Color(49, 207, 240));
        mainMenuButton.setOutlineColor(Color.black);
        mainMenuButton.setOutlineThickness(3);
        //Settings Buttons
        volumeLevel = new SpriteFont("Volume: ", 410, 250, "Comic Sans", 30, new Color (49, 207,240));
        volumeLevel.setOutlineColor(Color.black);
        volumeLevel.setOutlineThickness(3);
        aspectRatioLevel = new SpriteFont("Aspect Ratio: ", 410, 330, "Comic Sans", 30, new Color(49, 207, 240));
        aspectRatioLevel.setOutlineColor(Color.black);
        aspectRatioLevel.setOutlineThickness(3);
    }

    public void initialize() {
        // define/setup map

        switch (currentLevel) {
            case 0:
                //TODO: Change this to desired map to start on that map
                this.map = new LevelThree();
                map.reset();
                break;
            case 1:
                this.map = new LevelTwo();
                map.reset();
                break;
//            case 2:
//                this.map = new LevelThree();
//                map.reset();
//                break;
        }

        // setup player
        this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        if (currentLevel > 0) {
            player.unlockPowerUpOne();
        }
        this.player.setMap(map);
        this.player.addListener(this);
        this.player.setLocation(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.playLevelScreenState = PlayLevelScreenState.RUNNING;
    }

    public void update() {
        if (Keyboard.isKeyDown(pauseKey) && !keyLocker.isKeyLocked(pauseKey)) {
            isGamePaused = !isGamePaused;
            keyLocker.lockKey(pauseKey);
        }

        if (Keyboard.isKeyUp(pauseKey)) {
            keyLocker.unlockKey(pauseKey);
        }

        if (!isGamePaused) {
            // based on screen state, perform specific actions
            switch (playLevelScreenState) {
                // if level is "running" update player and map to keep game logic for the platformer level going
                case RUNNING:
                    player.update();
                    map.update(player);
                    break;
                // if level has been completed, bring up level cleared screen
                case LEVEL_COMPLETED:
                    levelClearedScreen = new LevelClearedScreen();
                    levelClearedScreen.initialize();
                    screenTimer.setWaitTime(2500);
                    playLevelScreenState = PlayLevelScreenState.LEVEL_WIN_MESSAGE;
                    currentLevel++;
                    break;
                // if level cleared screen is up and the timer is up for how long it should stay out, go back to main menu
                case LEVEL_WIN_MESSAGE:
                    if (screenTimer.isTimeUp()) {
                        levelClearedScreen = null;
                        if (currentLevel > 1) {
                            goBackToMenu();
                        } else {
                            playLevelScreenState = PlayLevelScreenState.RUNNING;
                            this.initialize();
                        }
                    }
                    break;
                // if player died in level, bring up level lost screen
                case PLAYER_DEAD:
                    levelLoseScreen = new LevelLoseScreen(this);
                    levelLoseScreen.initialize();
                    playLevelScreenState = PlayLevelScreenState.LEVEL_LOSE_MESSAGE;
                    break;
                // wait on level lose screen to make a decision (either resets level or sends player back to main menu)
                case LEVEL_LOSE_MESSAGE:
                    levelLoseScreen.update();
                    break;
            }
        }
        if(isGamePaused == true)
        {
        	if (Keyboard.isKeyDown(Key.DOWN) && keyTimer.isTimeUp())
        	{
        		keyTimer.reset();
        		currentMenuItemHovered++;
        	}
        	else if (Keyboard.isKeyDown(Key.UP) && keyTimer.isTimeUp())
        	{
        		keyTimer.reset();
        		currentMenuItemHovered--;
        	}

        // if down is pressed on last menu item or up is pressed on first menu item, "loop" the selection back around to the beginning/end
        if (currentMenuItemHovered > 1)
        {
        	currentMenuItemHovered = 2;
        }
        else if (currentMenuItemHovered < 0)
        {
            currentMenuItemHovered = 0;
        }

        // sets location for blue square in front of text (pointerLocation) and also sets color of spritefont text based on which menu item is being hovered
        if (currentMenuItemHovered == 1)
        {
        	pauseHeader.setColor(new Color(255, 255, 255));
        	settingsButton.setColor(new Color(255, 215, 0));
            mainMenuButton.setColor(new Color(49, 207, 240));
            pointerLocationX = 170;
            pointerLocationY = 205;
        }
        else if (currentMenuItemHovered == 2)
        {
        	pauseHeader.setColor(new Color(255, 255, 255));
        	settingsButton.setColor(new Color(49, 207, 240));
        	mainMenuButton.setColor(new Color(255, 215, 0));
            pointerLocationX = 170;
            pointerLocationY = 280;
        }
        if (Keyboard.isKeyUp(Key.SPACE))
        {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE))
        {
        	keyLocker.lockKey(Key.SPACE);
            menuItemSelected = currentMenuItemHovered;
            if (menuItemSelected == 1)
            {
            	if(settingsActive == false)
            	{
            		settingsActive = true;
            	}
            	else if(settingsActive == true)
            	{
            		settingsActive = false;
            	}
            }
            else if (menuItemSelected == 2)
            {
                screenCoordinator.setGameState(GameState.MENU);
            }
        }
      }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (playLevelScreenState) {
            case RUNNING:
            case LEVEL_COMPLETED:
            case PLAYER_DEAD:
                map.draw(graphicsHandler);
                player.draw(graphicsHandler);
                break;
            case LEVEL_WIN_MESSAGE:
                levelClearedScreen.draw(graphicsHandler);
                break;
            case LEVEL_LOSE_MESSAGE:
                levelLoseScreen.draw(graphicsHandler);
                break;
        }
        if (isGamePaused)
        {
            graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), new Color(0, 0, 0, 100));
            pauseHeader.draw(graphicsHandler);
            settingsButton.draw(graphicsHandler);
            mainMenuButton.draw(graphicsHandler);
            if(settingsActive == false)
        	{
            	pauseHeader.draw(graphicsHandler);
                settingsButton.draw(graphicsHandler);
                mainMenuButton.draw(graphicsHandler);
        	}
        	else if(settingsActive == true)
        	{
        		volumeLevel.draw(graphicsHandler);
        		aspectRatioLevel.draw(graphicsHandler);
        	}
        }
    }
    public int getMenuItemSelected() {
        return menuItemSelected;
    }

    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }

    @Override
    public void onLevelCompleted() {
        playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
    }

    @Override
    public void onDeath() {
        playLevelScreenState = PlayLevelScreenState.PLAYER_DEAD;
    }

    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }


    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED, PLAYER_DEAD, LEVEL_WIN_MESSAGE, LEVEL_LOSE_MESSAGE,
    }
}
