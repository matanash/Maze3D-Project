package mvc.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;
import java.util.Map.Entry;

import mvc.controller.Command;
/**
 * This class represents CLI Object 
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public class CLI implements Runnable {
	private BufferedReader in;
	private PrintWriter out;
	private HashMap<String, Command> commands;

	/**
	 * CLI C'tor
	 * @param in - the BufferedReader to read from
	 * @param out - the PrintWriter to write to
	 * @param commands - the CLI commands HashMap
	 */
	public CLI(BufferedReader in, PrintWriter out, HashMap<String, Command> commands) {
		this.in = in;
		this.out = out;
		this.commands = commands;

	}

	/**
	 * This method set the CLI Commands HasMap
	 * @param commands- the commands HashMap to set
	 */
	public void setCommands(HashMap<String, Command> commands) {
		this.commands = commands;
	}

	/**
	 * launch run method and start the CLI View 
	 */
	public void run() {
		try {
			this.out.println("---------------Welcome to the MAZE 3D Game---------------");
			start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method launch the CLI and wait for user command
	 */
	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					out.write("Enter command (for more information you can use the \"help\" command) : ");
					out.flush();
					String commandName;
					while (true) {
						commandName = in.readLine().toLowerCase();
						if (commandName.equals("help"))
							printAllCommands();
						else 
						{
							int n;
							// split command by white spaces
							String[] splitedCommand = commandName.split("\\s+");
							for (n = splitedCommand.length - 1; n >= 0; n--) {
								StringBuilder connectedCommand = new StringBuilder();
								for (int i = 0; i <= n; i++) {
									if (i != n)
										connectedCommand.append(splitedCommand[i] + " ");
									else
										connectedCommand.append(splitedCommand[i]);
								}
								if (commands.containsKey(connectedCommand.toString())) {
									String[] args = new String[splitedCommand.length - n - 1];
									for (int j = 0; j < args.length; j++) {
										args[j] = splitedCommand[n + 1 + j];
									}
									commands.get(connectedCommand.toString()).doCommand(args);
									break;
								}
							}
							if (n < 0)
								System.out.println(
										"Invalid command, you can use the \"help\" command to see the command-name list.");
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "io loop").start();

	}
	/**
	 * This method print all commands help methods
	 */
	private void printAllCommands() {
		for (Entry<String, Command> entry : commands.entrySet()) {
			if (entry.getValue() != null)
				entry.getValue().help();
		}
	}

	/**
	 * This method get the PrintWriter data member of the CLI object
	 * @return - the Printer Writer of the CLI object
	 */
	public PrintWriter getOut() {
		return this.out;
	}
}
