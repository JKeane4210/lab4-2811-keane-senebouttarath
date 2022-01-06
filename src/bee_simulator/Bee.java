package bee_simulator;

public abstract class Bee {

    protected int moveDistance;
    protected int targetX;
    protected int targetY;

    public void move() {

    }

    public abstract void drawBee();

    public void die() {

    }

    public void visitFlower(Flower flower) {

    }

    public abstract void collide(Organism otherOrganism);
}
