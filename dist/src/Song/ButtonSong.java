
package Song;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*@author Abel123*/
public class ButtonSong {
    public enum song{DISCORD_1, DISCORD_2, AMON_US}
    private File dir_song;
    private Clip clip;
            
    public ButtonSong(File dir_song) {
        this.dir_song = dir_song;
        InitComponents();
    }
    public ButtonSong(song s) {
        switch (s) {
            case AMON_US:
                dir_song=new File("src/Song/us_kill.wav");
                break;
            case DISCORD_1:
                dir_song=new File("src/Song/discord1.wav");
                break;
            case DISCORD_2:
                dir_song=new File("src/Song/discord2.wav");
                break;
            default:
                dir_song=null;
                break;
        }
        InitComponents();
    }
    public ButtonSong() {
        this.dir_song=new File("src/Song/discord1.wav");
        InitComponents();
    }
    
    private void InitComponents(){
        this.clip=null;
    }
    public void StartSong(){
        try {
            clip=AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(dir_song));
            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {System.out.println("ButtonSong->StartSong "+ex);}
    }
    public void StopSong(){
        clip.stop();
    }
    public File getDir_song() {
        return dir_song;
    }
    public void setDir_song(File dir_song) {
        this.dir_song = dir_song;
    }
    public void setSong(song s){
        switch (s) {
            case AMON_US:
                dir_song=new File("src/Song/us_kill.wav");
                break;
            case DISCORD_1:
                dir_song=new File("src/Song/discord1.wav");
                break;
            case DISCORD_2:
                dir_song=new File("src/Song/discord2.wav");
                break;
            default:
                dir_song=null;
                break;
        }
    }
}
