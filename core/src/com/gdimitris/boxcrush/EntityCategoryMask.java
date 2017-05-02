package com.gdimitris.boxcrush;

public enum EntityCategoryMask {
    BOUNDARY((short) 0x01),
    PROJECTILE((short) 0x0002),
    BOX((short) 0x0003),
    SENSOR((short) 0x0004);


    private short hexValue;

    EntityCategoryMask(short hexValue){
        this.hexValue = hexValue;
    }

    public short getValue(){
        return hexValue;
    }
}
