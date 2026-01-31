package com.LambdaExpressions.smarthome;
public class SmartHomeDemo {
    public static void main(String[] args) {
        
        System.out.println("====== SMART HOME LIGHTING ======\n");
        
        // Create lights
        Light livingRoom = new Light("Living Room");
        Light bedroom = new Light("Bedroom");
        
        // Create controller with all lambda triggers
        LightController controller = new LightController();
        
        // MOTION TRIGGERS
        System.out.println("--- Motion Triggers ---");
        controller.motionDetected.accept(livingRoom);
        controller.motionAtNight.accept(bedroom);
        
        // TIME TRIGGERS
        System.out.println("\n--- Time Triggers ---");
        controller.morning.accept(livingRoom);
        controller.evening.accept(livingRoom);
        controller.bedtime.accept(bedroom);
        
        // VOICE COMMAND TRIGGERS
        System.out.println("\n--- Voice Commands ---");
        controller.voiceTurnOn.accept(bedroom);
        controller.voiceBrighten.accept(bedroom);
        controller.voiceDim.accept(bedroom);
        controller.voiceTurnOff.accept(bedroom);
        
        // CUSTOM LAMBDA (created on the fly)
        System.out.println("\n--- Custom Lambda ---");
        java.util.function.Consumer<Light> readingMode = light -> {
            light.setBrightness(90);
            System.out.println("Reading mode: Perfect for books!");
        };
        readingMode.accept(livingRoom);
        
        System.out.println("\n====== DEMO COMPLETE ======");
    }
}