public class Score {
    private int value;

    public Score() {
        this.value = 0;
    }

    public void increment() {
        this.value++;
    }

    public int getValue() {
        return this.value;
    }
}
