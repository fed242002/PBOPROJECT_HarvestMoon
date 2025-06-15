package Main;

public class MasterMusic extends Sound {
    public MasterMusic() {
        soundURL[0] = getClass().getResource("/assets/sound/home.wav");   
        soundURL[1] = getClass().getResource("/assets/sound/home.wav");  //<- nanti ganti dengan sound yang lain  
        soundURL[2] = getClass().getResource("/assets/sound/city.wav");   

    }
    
}
