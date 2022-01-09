package bee_simulator;

public abstract class Bee extends Organism {

    //---------------- ATTRIBUTES ----------------\\

    protected int moveDistance;
    protected int targetX = 0;
    protected int targetY = 0;
    protected BeeMovementPattern movementPattern;

    //---------------- METHODS ----------------\\

    //-------- CONSTRUCTORS

    public Bee(int startX, int startY, int collisionRadius, int maxEnergy, int energy, String imgUrl, String desc, int moveDistance) {
        super(startX, startY, collisionRadius, maxEnergy, energy, imgUrl, desc);
        this.moveDistance = moveDistance;
    }

    public void move() {
        movementPattern.moveBee();
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
        if (movementPattern.targetAchieved()) {
            movementPattern.retarget();
        }
    }

    public void die() {

    }

    public void setTarget() {
        movementPattern.retarget();
    }

//    public void visitFlower(Flower flower) {
//        targetX = flower.getCenterX();
//        targetY = flower.getCenterY();
//    }

    public void collide(Organism otherOrganism) {
        if (otherOrganism instanceof Flower) {
            ((Flower)otherOrganism).interactWithBee(this);
        }
    }

   public void setMovementPattern(BeeMovementPattern movementPattern) {
        this.movementPattern = movementPattern;
   }
}
