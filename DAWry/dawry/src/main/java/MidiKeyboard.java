//package dawry;
import javax.sound.midi.*;
import javax.swing.*;
import java.awt.event.*;

public class MidiKeyboard extends JFrame {
    private Synthesizer synth;
    private MidiChannel channel;

    public MidiKeyboard() throws Exception {
        setTitle("Java MIDI Keyboard");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Setup MIDI synthesizer
        synth = MidiSystem.getSynthesizer();
        synth.open();
        channel = synth.getChannels()[0];  // Use first MIDI channel

        JLabel label = new JLabel("Press A S D F G H J to play notes!", SwingConstants.CENTER);
        add(label);

        // Map keys to MIDI notes (C major scale, starting from middle C = 60)
        int[] notes = {60, 62, 64, 65, 67, 69, 71}; // C D E F G A B

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A -> channel.noteOn(notes[0], 600);
                    case KeyEvent.VK_S -> channel.noteOn(notes[1], 600);
                    case KeyEvent.VK_D -> channel.noteOn(notes[2], 600);
                    case KeyEvent.VK_F -> channel.noteOn(notes[3], 600);
                    case KeyEvent.VK_G -> channel.noteOn(notes[4], 600);
                    case KeyEvent.VK_H -> channel.noteOn(notes[5], 600);
                    case KeyEvent.VK_J -> channel.noteOn(notes[6], 600);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A -> channel.noteOff(notes[0]);
                    case KeyEvent.VK_S -> channel.noteOff(notes[1]);
                    case KeyEvent.VK_D -> channel.noteOff(notes[2]);
                    case KeyEvent.VK_F -> channel.noteOff(notes[3]);
                    case KeyEvent.VK_G -> channel.noteOff(notes[4]);
                    case KeyEvent.VK_H -> channel.noteOff(notes[5]);
                    case KeyEvent.VK_J -> channel.noteOff(notes[6]);
                }
            }
        });
    }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            try {
                new MidiKeyboard().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
