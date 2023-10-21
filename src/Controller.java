public class Controller {

    View view;
    private Trial trial;

    public Controller(){
        view = new View(this);
        trial = new Trial(this, view.howManyDice());
    }

    public void newTrial() {
        trial = new Trial(this, view.howManyDice());
    }
}