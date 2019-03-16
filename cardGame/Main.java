import java.util.ArrayList;
import java.util.Collections;
import java.lang.*;

public class Main {

    public static void main(String[] args) {

        // Check correct arguments
        if(args.length != 1) {
            System.err.print("Incorrect number of input arguments.\n");
            return;
        }

        // Parse input
        int input = Integer.parseInt(args[0], 2);
        ArrayList<Character> state = new ArrayList<>();

        // Initialize state array
        int mask = 1;
        do {
            state.add(((input & mask) != 0) ? '1' : '0');
            mask = mask << 1;
        } while(mask < input);

        // Reverse ArrayList order
        Collections.reverse(state);

        // Call recursive function
        int solutions = recursiveGame(state, "Trace:\n");
        System.out.print("Found " + solutions + " total solutions.\n");
    }

    private static int recursiveGame(ArrayList<Character> state, String trace) {

        // Compare current game state to dead and win states
        if(isWin(state)) {
            System.out.print("Found solution!\n" + trace);
            return 1;
        }
        if(isDead(state))
            return 0;

        // Do appropriate flips
        int solutions = 0;
        ArrayList<Character> temp = new ArrayList<>(state);
        for(int i = 0; i < state.size(); i++) {
            if(state.get(i) == '1') {
                flip(temp, i);
                solutions += recursiveGame(temp, trace + "Removed card at position " + (i + 1) + ".\n");
                temp = new ArrayList<>(state);
            }
        }

        return solutions;
    }

    private static boolean isWin(ArrayList<Character> state) {
        return (state.contains('.') && !state.contains('0') && !state.contains('1'));
    }

    // isDead will also detect win state, but isn't a problem in this implementation
    private static boolean isDead(ArrayList<Character> state) {
        return (!state.contains('1'));
    }

    private static void flip(ArrayList<Character> state, int i) {

        state.set(i, '.');

        char cur = 'E';
        if(i != 0) {
            cur = state.get(i - 1);
            if(cur == '0')
                state.set(i - 1, '1');
            else if(cur == '1')
                state.set(i - 1, '0');
        }
        if(i != (state.size() - 1)) {
            cur = state.get(i + 1);
            if(cur == '0')
                state.set(i + 1, '1');
            else if(cur == '1')
                state.set(i + 1, '0');
        }
    }
}
