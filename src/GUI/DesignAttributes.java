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
 *
 * @author lyleb and khoap
 */
public class DesignAttributes
{

    public Color primaryColor, secondaryColor, tertiaryColor;
    public Border raisedBevel, loweredBevel, lineBorder, emptyBorder, marginBorder;

    /**
     * Default Design
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
     *
     *
     * @param primaryColor
     * @param secondaryColor
     * @param tertiaryColor
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
     *
     *
     * @param top
     * @param left
     * @param bottom
     * @param right
     * @return
     */
    public Border createMarginBorder(int top, int left, int bottom, int right)
    {
        Border createdMarginBorder
                = BorderFactory.createEmptyBorder(top, left, bottom, right);
        return createdMarginBorder;
    }
}
