package utilz;

import java.awt.geom.Rectangle2D;

import main.Game;

public class HelpMethods {

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {// collison method
        if (!IsSolid(x, y, lvlData))//by4of top left , hena yfdl mkml l7d ma y4of awl solid
            if (!IsSolid(x + width, y + height, lvlData))//bottom right
                if (!IsSolid(x + width, y, lvlData))// top right
                    if (!IsSolid(x, y + height, lvlData))//bottom left
                        return true;//lw mafe4 mo4kla fe el condition m3nha t2dr tt7rk f rg3na true
        return false;
    }

    private static boolean IsSolid(float x, float y, int[][] lvlData) {
        int maxWidth = lvlData[0].length * Game.TILES_SIZE;//gbna el actual width 34n mkona4 3rfen
        if (x < 0 || x >= maxWidth)
            //means it is solid
            return true;
        if (y < 0 || y >= Game.GAME_HEIGHT)
            return true;
        // dlwty 3rfna en fe tile ow object 3yez b2a n3rf mkano
        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        return IsTileSolid((int) xIndex, (int) yIndex, lvlData);
    }

    public static boolean IsTileSolid(int xTile, int yTile, int[][] lvlData) {
        int value = lvlData[yTile][xTile];

        if (value >= 48 || value < 0 || value != 11)
            return true;
        return false;
    }

    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.x / Game.TILES_SIZE);//dlwty e7na 3rfna el tile el w2f feh el player
        //howa lma hy3ml collision hy3ml ya m3a left tile ow right tile 34n 5las dlwty mafe4 up and down e7na bn3ml gravity
        //lw feh up htb2a 3obra 3n jumping wa lw feh down htb2 b4kl gravity
        if (xSpeed > 0) {//moving right
            int tileXPos = currentTile * Game.TILES_SIZE;//dah 3la 4kl pixels el tany kan pos y3ny rkm kam el tile dah
            //34n ngebo ymen lazem n3rf el offset ben el player wa el n7ya el tanya el heya htb2a left
            int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - 1;//3mlna -1 34n nmn3 overlap ben el hitbox wla tile
        } else
            // Left
            return currentTile * Game.TILES_SIZE;
    }

    public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
        if (airSpeed > 0) {
            // Falling - touching floor
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - 1;
        } else
            // Jumping
            return currentTile * Game.TILES_SIZE;

    }

    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
        // Check the pixel below bottomleft and bottomright
        if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData))
            if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData))
                return false; //dah m3na en e7na fe el hwa

        return true;

    }
    public static boolean IsFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData) {
        return IsSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
    }

    public static boolean IsAllTilesWalkable(int xStart, int xEnd, int y, int[][] lvlData) {
        for (int i = 0; i < xEnd - xStart; i++) {
            if (IsTileSolid(xStart + i, y, lvlData))
                return false;
            if (!IsTileSolid(xStart + i, y + 1, lvlData))
                return false;
        }

        return true;
    }

    public static boolean IsSightClear(int[][] lvlData, Rectangle2D.Float firstHitbox, Rectangle2D.Float secondHitbox, int yTile) {
        int firstXTile = (int) (firstHitbox.x / Game.TILES_SIZE);
        int secondXTile = (int) (secondHitbox.x / Game.TILES_SIZE);

        if (firstXTile > secondXTile)
            return IsAllTilesWalkable(secondXTile, firstXTile, yTile, lvlData);
        else
            return IsAllTilesWalkable(firstXTile, secondXTile, yTile, lvlData);

    }

}