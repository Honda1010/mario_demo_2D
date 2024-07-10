package ui;

import gamestates.Gamestate;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.Buttons.*;

public class MenuButton {
    private int xPos,yPos,rowIndex,index;
    private int xOffsetCenter=(B_WIDTH/2);
    private boolean MouseOver,MousePressed;
    private Gamestate state;
    private BufferedImage[] imgs;// dah 34n n5zn el swr wa nst5dmha wat ma e7na 3yezen
    private Rectangle bounds;// dah 3mlna 34n n3ml hitbox,34n check hl ana dost el button wl la
    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state){
        //dah x w y axis bt3t el button wa rowdindex dah 34n t5tr el row bt3t el sora, state 34n e7na 3ndha keza state f tb2 fe el menu
        this.xPos=xPos;
        this.yPos=yPos;
        this.rowIndex=rowIndex;
        this.state=state;
        loadImgs();
        intiBounds();
    }

    private void intiBounds() {
        bounds= new Rectangle(xPos-xOffsetCenter,yPos,B_WIDTH,B_HEIGHT);
    }

    private void loadImgs() {// ms2ol 3n loading el images
        imgs= new BufferedImage[3];
        BufferedImage temp= LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
        for(int i=0;i<imgs.length;i++){
            imgs[i]=temp.getSubimage(i*B_WIDTH_DEFAULT,rowIndex*B_HEIGHT_DEFAULT,B_WIDTH_DEFAULT,B_HEIGHT_DEFAULT);
        }
    }
    public void draw(Graphics g){// ms2ol 3n rsm el images
        g.drawImage(imgs[index],xPos- xOffsetCenter,yPos,B_WIDTH,B_HEIGHT,null);

    }
    public void update(){// ms2ol 3n
        index=0;
        if(MouseOver)
            index=1;
        if(MousePressed)
            index=2;
    }

    public boolean isMouseOver() {
        return MouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        MouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return MousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        MousePressed = mousePressed;
    }
    public Rectangle getBounds(){
        return bounds;
    }
    public void applyGamestate(){// dah method ll action el by7sl b3d ma tdos el button
        Gamestate.state=state;// dah 34n y8yer el state
    }
    public void resetBools(){
        MouseOver=false;
        MousePressed=false;
    }
}
