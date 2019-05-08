/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 * Design Attributes of a JPanel.
 * 
 * @author lyleb and khoap
 */
public class DesignAttributes
{

    public Color primaryColor, secondaryColor, tertiaryColor;
    public Border raisedBevel, loweredBevel, lineBorder, emptyBorder, marginBorder;

    /**
     * Default Design of the Design Attributes Class.
     */
    public DesignAttributes()
    {

        // Setting up the Colours
        this.primaryColor = new Color(35, 31, 32); // Black
        this.secondaryColor = new Color(120, 120, 120); // Gray
        this.tertiaryColor = new Color(245, 245, 245); // WhiteSmoke

        // Setting up the Border Styles
        this.raisedBevel = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        this.loweredBevel = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        this.lineBorder = BorderFactory.createLineBorder(this.primaryColor, 1);
        this.emptyBorder = BorderFactory.createEmptyBorder();
        this.marginBorder = BorderFactory.createEmptyBorder(16, 16, 16, 16);
    }

    /**
     * Set the color scheme of the design attributes.
     *
     * @param primaryColor of the design theme.
     * @param secondaryColor of the design theme.
     * @param tertiaryColor of the design theme.
     */
    public void setColorScheme(Color primaryColor, Color secondaryColor, Color tertiaryColor)
    {
        if (primaryColor != null)
        {
            this.primaryColor = primaryColor;
        }
        if (primaryColor != null)
        {
            this.secondaryColor = secondaryColor;
        }
        if (tertiaryColor != null)
        {
            this.tertiaryColor = tertiaryColor;
        }
    }

    /**
     * Generates a margin border based on the margins specified.
     *
     * @param top margin from the top.
     * @param left margin from the left.
     * @param bottom margin from the bottom.
     * @param right margin from the right.
     * @return the generated border.
     */
    public Border createMarginBorder(int top, int left, int bottom, int right)
    {
        Border createdMarginBorder
                = BorderFactory.createEmptyBorder(top, left, bottom, right);
        return createdMarginBorder;
    }
}
