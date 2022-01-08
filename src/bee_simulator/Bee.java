package bee_simulator;

public abstract class Bee extends Organism {

    //---------------- ATTRIBUTES ----------------\\

    protected int moveDistance;
    protected int targetX;
    protected int targetY;

    //---------------- METHODS ----------------\\

    //-------- CONSTRUCTORS

    public Bee(int startX, int startY, int collisionRadius, int maxEnergy, int energy, String imgUrl, String desc, int moveDistance) {
        super(startX, startY, collisionRadius, maxEnergy, energy, imgUrl, desc);
        this.moveDistance = moveDistance;
    }

    public void move() {

    }

    public abstract void draw();

    public void update() {

    }

    public void die() {

    }

    public void visitFlower(Flower flower) {

    }

    public abstract void collide(Organism otherOrganism);
}
