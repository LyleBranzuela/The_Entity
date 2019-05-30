/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sun.audio.AudioData;
import sun.audio.AudioStream;
import sun.audio.AudioPlayer;
import sun.audio.ContinuousAudioDataStream;

/**
 *
 * @author lyleb and khoap
 */
public class UtilityMethods extends JFrame
{

    /**
     * Generates a random number for a variety of purposes
     *
     * @param range for random number to be created in
     * @return a random number within range.
     */
    public static int randNum(int range)
    {
        Random rand = new Random();
        int x = rand.nextInt(range);

        return x;
    }

    /**
     * Generates a button based on the parameters set.
     *
     * @param fontName
     * @param fontSize
     * @param fontColor
     * @param bgColor
     * @param opaque
     * @return
     */
    public static JButton generateButton(String fontName, int fontSize,
            Color fontColor, Color bgColor, Boolean opaque)
    {
        JButton generatedButton = new JButton(fontName);
        generatedButton.setFont(new Font("Tahoma", Font.BOLD, fontSize));
        generatedButton.setForeground(fontColor);
        generatedButton.setFocusPainted(false);

        // If the caller wants to have a background 
        if (bgColor != null)
        {
            generatedButton.setBackground(bgColor);
        }

        // If the caller wants the button to be opaque
        if (opaque)
        {
            generatedButton.setOpaque(false);
            generatedButton.setContentAreaFilled(false);
            generatedButton.setBorderPainted(false);
        }

        generatedButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent me)
            {
                generatedButton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent me)
            {
                generatedButton.setForeground(fontColor);
            }
        });

        return generatedButton;
    }

    /**
     *
     */
    public static void exitConfirmation()
    {
        int confirmDialog = JOptionPane.showConfirmDialog(null, "Would You Like To Exit?", "Warning", JOptionPane.OK_CANCEL_OPTION);
        if (confirmDialog == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }
    
    
    /**
     *
     * @param fileLocation
     * @param duration
     */
    public void playSoundtrack(String fileLocation, int duration)
    {
        int trackDuration = duration * 1000;
        
        try
        {
            AudioData data = new AudioStream(new FileInputStream(fileLocation)).getData();
            ContinuousAudioDataStream soundtrack = new ContinuousAudioDataStream(data);
            AudioPlayer.player.start(soundtrack);
            
            Thread.sleep(trackDuration);
            
            AudioPlayer.player.stop(soundtrack);
        }
        
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "Error");
        } 
        
        catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
    
}
