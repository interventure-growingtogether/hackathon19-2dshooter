/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels;

/**
 *
 * @author Marko
 */
public abstract class LevelElements {

    public static final byte HOLE           = 0x00;     //xxxx xxx0 b
    public static final byte PLATFORM       = 0x01;     //xxxx xxx1 b
    public static final byte TYPE_MASK      = 0x01;
    
    public static final byte SMALL          = 0x00;     //xxxx x00x b
    public static final byte MEDIUM         = 0x02;     //xxxx x01x b
    public static final byte LARGE          = 0x04;     //xxxx x10x b
    public static final byte XLARGE         = 0x06;     //xxxx x11x b
    public static final byte SIZE_MASK      = 0x06;
    
    public static final byte ENEMY_NO       = 0x00;     //xxx0 0xxx b
    public static final byte ENEMY1         = 0x08;     //xxx0 1xxx b
    public static final byte ENEMY2         = 0x10;     //xxx1 0xxx b
    public static final byte ENEMY3         = 0x18;     //xxx1 1xxx b
    public static final byte ENEMY_MASK     = 0x18;
    
    public static final byte MUNITION_NO    = 0x00;     //xx0x xxxx b
    public static final byte MUNITION       = 0x20;     //xx1x xxxx b
    public static final byte MUNITION_MASK  = 0x20;
    
    public static final byte COIN_NO        = 0x00;     //x0xx xxxx b
    public static final byte COIN           = 0x40;     //x1xx xxxx b
    public static final byte COIN_MASK      = 0x40;
    
    public static final byte END_NO         = 0x00;     //0xxx xxxx b
    public static final byte END            = (byte) 0x80;     //1xxx xxxx b
    public static final byte END_MASK       = (byte) 0x80;     
    
    
    /**
     * Helper constants
     */
    
    public static final byte SH     = SMALL ^ HOLE;
    public static final byte MH     = MEDIUM ^ HOLE;
    public static final byte LH     = LARGE ^ HOLE;
    public static final byte XH     = XLARGE ^ HOLE;
    
    public static final byte SP     = SMALL ^ PLATFORM;
    public static final byte MP     = MEDIUM ^ PLATFORM;
    public static final byte LP     = LARGE ^ PLATFORM;
    public static final byte XP     = XLARGE ^ PLATFORM;
    
    public static final byte E1SP   = ENEMY1 ^ SP;
    public static final byte E1MP   = ENEMY1 ^ MP;
    public static final byte E1LP   = ENEMY1 ^ LP;
    public static final byte E1XP   = ENEMY1 ^ XP;
    
    public static final byte E2SP   = ENEMY2 ^ SP;
    public static final byte E2MP   = ENEMY2 ^ MP;
    public static final byte E2LP   = ENEMY2 ^ LP;
    public static final byte E2XP   = ENEMY2 ^ XP;
    
    public static final byte MSP    = MUNITION ^ SP;
    public static final byte MMP    = MUNITION ^ MP;
    public static final byte MLP    = MUNITION ^ LP;
    public static final byte MXP    = MUNITION ^ XP;
    
    public static final byte CSP    = COIN ^ SP;
    public static final byte CMP    = COIN ^ MP;
    public static final byte CLP    = COIN ^ LP;
    public static final byte CXP    = COIN ^ XP;
    
    
    
    
    
   
    
}
