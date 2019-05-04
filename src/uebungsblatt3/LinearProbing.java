package uebungsblatt3;

public class LinearProbing implements Probing {
    int iteration, sign, step, alternation;

    public LinearProbing(int step, int alternation){
        this.step = step;
        this.alternation = alternation;
        iteration = 1;
        sign = 1;
    }

    public LinearProbing() {
        this(1, -1);
    }

    @Override
    public void startProbing() {
        iteration = 1;
        sign = 1;
    }

    @Override
    public int nextNum() {
        int current = step * iteration * sign;

        if (alternation > 0 || sign < 0)
            iteration += 1;
        sign *= alternation;

        return current;
    }
}
