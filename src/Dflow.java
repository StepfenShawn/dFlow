import java.io.*;

import ast.Block;
import execute.Evaluate;
import parser.Parser;


public class Dflow {
	public static void main(String[] args) {
		String fileName = args[0];
		String source = "";
		try (BufferedReader buf = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = buf.readLine()) != null) {
				source += line + '\n';
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Block block = Parser.parse(source, fileName);
		Evaluate.run(block);
	}
}