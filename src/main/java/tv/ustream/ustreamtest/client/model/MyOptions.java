/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tv.ustream.ustreamtest.client.model;

/**
 *
 * @author pfeiferlaszlo
 */
public enum MyOptions {
    ADD("a"),
    DELETE("d"),
    LIST("l"),
    HELP("h"),
    GET("g");
    
    private final String character;
    
    MyOptions(String character) {
        this.character = character;
    }
    
    public String getCharacter() {
        return character;
    }
}
