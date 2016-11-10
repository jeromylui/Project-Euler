package populate;

import akka.actor.UntypedActor;
import java.util.ArrayList;

public class Counter extends UntypedActor {

    private int sum;
    private Range range;
    private int sumNumbers() {
        if (range == null){
            throw new Exception("There is nothing to sum")
        }

        for (int i = range.getLow; i <= range.getHigh; i++){
            boolean hasFactor = False;
            for (int g: Config.NUMBERS){
                if (i % g == 0){
                    hasFactors = True;
                    break;
                }
            }
            if (hasFactor){
                sum += i;
            }
        }

        return sum;
    }

    @Override
    public void onReceive(Object msg) {
        if (msg != null) {
            this.range = (Range) msg;
            getSender().tell(sumNumbers(), getSelf());
        } else {
            getContext().stop(getSelf());
            unhandled(msg);
        }
    }

    @Override
    public void preStart() {
        sum = 0;
    }
}
