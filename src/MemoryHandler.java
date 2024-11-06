public class MemoryHandler {
    private double memoryValue = 0;

    public void addToMemory(double value) {
        memoryValue += value;
    }

    public void subtractFromMemory(double value) {
        memoryValue -= value;
    }

    public double recallMemory() {
        return memoryValue;
    }

    public void clearMemory() {
        memoryValue = 0;
    }
}
