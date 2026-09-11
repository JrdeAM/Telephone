import interfaces.PhoneObserver;

/**
 * Prints things out to the screen, when needed
 * Printing to the screen:
 * System.out.println("hello");
 */
public class Screen implements PhoneObserver {
    private final PhoneModel model;

    public Screen(PhoneModel model) {
        this.model = model;
    }

    @Override
    public void update() {
        System.out.println("Número atual: " + model.getDigits());
    }
}
