package Main;

public class SFX extends Sound {
    public SFX() {
        soundURL[0] = getClass().getResource("/assets/sound/jalan.wav");   
        soundURL[1] = getClass().getResource("/assets/sound/cursor_move.wav");   
        soundURL[2] = getClass().getResource("/assets/sound/decide.wav");   
        soundURL[3] = getClass().getResource("/assets/sound/pause.wav");   
        soundURL[4] = getClass().getResource("/assets/sound/window_open.wav");   
    }
    
}
