import java.util.ArrayList;
import java.util.List;
import interfaces.PhoneObserver;

/**
 * Store a phone number, digit-by-digit
 */
public class PhoneModel {
    private List<Integer> digits = new ArrayList<>();
    private List<PhoneObserver> observers = new ArrayList<>();

    public void addDigit(int newDigit) {
        digits.add(newDigit);
        notifyObservers();
    }

    public List<Integer> getDigits() {
        return digits;
    }

    public void addObserver(PhoneObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (PhoneObserver observer : observers) {
            observer.update();
        }
    }
}