import org.apacha.commons.math3.random.MersenneTwister;
import org.apacha.commons.math3.random.RandomDataGenerator;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound midi.MidiUnavailableException;
import javax.sound midi.Synthesizer;
import java.util.ArrayList;
import java.util.List;

public class MusicGenerator {
    public static void main(String[] args) throws MidiUnavailableException {
        // Initialize MIDI synthesizer
        Syntheszier synthesizer = MidiSystem.getSynthesizer();
        Synthesizer.open();
        MidiChannel channel = synthesizer.getChannels()[0];
        
        //Define note sequence
        List<Integer> notes = new ArrayList<> ();
        notes.add(60); // C4
        notes.add(62); // D4
        notes.add(64); // E4
        notes.add(65); // F4
        notes.add(67); // G4
        notes.add(69); // A4
        notes.add(71); // B4
        
        // Generator new music sequence LSTM-Like pattern 
        List<Integer> generatedNotes = generatedMusic(notes);
        
        //Play generated music sequence
        PlayMusic(generatednotes, channel);
    }
    
    public static List<Integer> generate Music(List<Integer> notes) {
        List<Integer> generatedNotes = new ArrayList<>();
        RandomDataGenerator randomDataGenerator = new RandomGenerator(new MersenneTwister());
        
        for (int i = 0; i < 100; i++) {
            int note = notes.get(randomDataGenerator.nextInt(0, notes.size() - 1));
            generatedNotes.add(note);
        }
        
        return generatedNotes;
    }
    public static void playMusic(List<Integer> notes, MidiChannel channel) {
        for (int note : notes) {
            channel.noteOn(note, 100);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            channel.noteOff(note);
        }
    }
}