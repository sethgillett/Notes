import org.jfugue.player.Player;

public class SoundPitch {
	static void playSound(String notes) {
		Player player = new Player();
		player.play(notes);
	}
}
