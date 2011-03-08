package interpreter;

public class PrimitiveCommandNode implements Node {
	private String name;
	private String text;

	public void parse(Context context) {
		name = context.currentToken();
		context.skipToken(name);
		if (!name.equals("PRINT") && !name.equals("BREAK")
				&& !name.equals("LINEBREAK") && !name.equals("SPACE")) {
			System.err.println("Undefined Command!");
		}
		if (name.equals("PRINT")) {
			text = context.currentToken();
			context.nextToken();
		}
	}

	public void execute() {
		if (name.equals("PRINT"))
			System.out.print(text);
		else if (name.equals("SPACE"))
			System.out.print(" ");
		else if (name.equals("BREAK"))
			System.out.println();
		else if (name.equals("LINEBREAK"))
			System.out.println("\n----------------------------");
	}

	public String toString() {
		return name;
	}
}
