package bee_simulator;

public abstract class Bee extends Organism {

    //---------------- ATTRIBUTES ----------------\\

    protected int moveDistance;
    protected int targetX = 0;
    protected int targetY = 0;

    //---------------- METHODS ----------------\\

    //-------- CONSTRUCTORS

    public Bee(int startX, int startY, int collisionRadius, int maxEnergy, int energy, String imgUrl, String desc, int moveDistance) {
        super(startX, startY, collisionRadius, maxEnergy, energy, imgUrl, desc);
        this.moveDistance = moveDistance;
    }

    public void move() {

        int xOffset = targetX - centerX;
        int yOffset = targetY - centerY;

        double denom = Math.abs(xOffset) + Math.abs(yOffset);

        int xMoveDist = (int) ((xOffset / denom) * moveDistance);
        int yMoveDist = (int) ((yOffset / denom) * moveDistance);

        centerX+=xMoveDist;
        centerY+=yMoveDist;
    }

    public void draw() {
        super.draw();
    }

    public void update() {
        decreaseEnergy(1);

        if (energy > 0) {
            move();
        } else {
            die();
        }
    }

    public void die() {

    }

    public void visitFlower(Flower flower) {
        targetX = flower.getCenterX();
        targetY = flower.getCenterY();
    }

    public void collide(Organism otherOrganism) {
        if (otherOrganism instanceof Flower) {
            increaseEnergy(10);
        }
    };
}
