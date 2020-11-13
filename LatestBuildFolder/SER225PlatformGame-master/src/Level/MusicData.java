package Level;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Engine.Config;

public class MusicData {
	
	public static File equalizer;
	public static float dB;
	public void playMusic(String musicLocation, double vol) 
	{
		try 
		{
			File musicPath = new File(musicLocation);
			//equalizer = musicPath;
			if(musicPath.exists()) 
			{
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				setVol(vol,clip);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				clip.getLevel();
			}
			else 
			{
				System.out.println("Can't find file");
			}
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		
	}
	public static void setVol(double vol, Clip clip) 
	{
		FloatControl gain = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		dB = (float)(Math.log(vol)/(Math.log(10)) * 20);
		gain.setValue(dB);
	} 

}
