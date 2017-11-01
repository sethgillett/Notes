import java.util.HashMap;

public class StateMachine {
	Runnable curState;
	SketchWindow p;
	HashMap<String, Runnable> runnables;
	HashMap<String, Output> stateOutput;
	
	public static class Output <T> {
		T output;
		Output(String statename, T output) {
			this.output = output;
		}
	}
	
	StateMachine(SketchWindow p) {
		this.p = p;
		runnables = new HashMap<String, Runnable>();
		stateOutput = new HashMap<String, Output>();
		state("default", () -> {});
		setState("default");
	}
	void state (String statename, Runnable state) {
		runnables.put(statename, state);
	}
//	void transition(String statename, Runnable switcher) {
//		runnables.put(statename+"Switch", switcher);
//	}
	void setState(String statename) {
		switchState(statename, false);
	}
	private void switchState(String statename, boolean transitionFirst) {
		if (transitionFirst && runnables.containsKey(statename+"Switch")) {
			curState = runnables.get(statename + "Switch");
			return;
		}
		curState = runnables.get(statename);
	}
	void runState() {
		// Clears state drawing screen
		p.displaySheet();
		curState.run();
	}
	<T> void output(String statename, T output) {
		stateOutput.put(statename, new Output<T>(statename, output));
	}
	<T> T getOutput(String statename) {
		System.out.println("Getting: " + statename);
		return (T) stateOutput.get(statename).output;
	}
	boolean hasOutput(String statename) {
		return stateOutput.containsKey(statename);
	}
}