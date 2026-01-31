package com.LambdaExpressions.smarthome;
import java.util.function.Consumer;

public class LightController {
    
    // Lambda for motion detected
    public Consumer<Light> motionDetected = light -> {
        light.setBrightness(100);
        System.out.println("Motion detected!");
    };
    
    // Lambda for motion at night
    public Consumer<Light> motionAtNight = light -> {
        light.setBrightness(30);
        System.out.println("Night motion detected!");
    };
    
    // Lambda for morning routine
    public Consumer<Light> morning = light -> {
        light.setBrightness(50);
        System.out.println("Good morning!");
    };
    
    // Lambda for evening routine
    public Consumer<Light> evening = light -> {
        light.setBrightness(70);
        System.out.println("Evening mode activated!");
    };
    
    // Lambda for bedtime
    public Consumer<Light> bedtime = light -> {
        light.setBrightness(0);
        System.out.println("Good night!");
    };
    
    // Lambda for voice command: turn on
    public Consumer<Light> voiceTurnOn = light -> {
        light.setBrightness(80);
        System.out.println("Voice: Light turned on");
    };
    
    // Lambda for voice command: turn off
    public Consumer<Light> voiceTurnOff = light -> {
        light.setBrightness(0);
        System.out.println("Voice: Light turned off");
    };
    
    // Lambda for voice command: dim
    public Consumer<Light> voiceDim = light -> {
        int newLevel = Math.max(0, light.getBrightness() - 20);
        light.setBrightness(newLevel);
        System.out.println("Voice: Dimming light");
    };
    
    // Lambda for voice command: brighten
    public Consumer<Light> voiceBrighten = light -> {
        int newLevel = Math.min(100, light.getBrightness() + 20);
        light.setBrightness(newLevel);
        System.out.println("Voice: Brightening light");
    };
}