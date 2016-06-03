package MVC.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;
import java.util.Map.Entry;

import MVC.controller.Command;

public class CLI implements Runnable {
	private BufferedReader in;
	private PrintWriter out;
	private HashMap<String, Command> commands;

	/**
	 * CLI Constructor
	 * 
	 * @param in
	 * @param out
	 * @param commands
	 * @param se
	 * @param error
	 */
	public CLI(BufferedReader in, PrintWriter out, HashMap<String, Command> commands) {
		this.in = in;
		this.out = out;
		this.commands = commands;

	}

	/**
	 * @param commands
	 *            the commands to set
	 */
	public void setCommands(HashMap<String, Command> commands) {
		this.commands = commands;
	}

	/**
	 * launch run method
	 * 
	 * @throws Exception
	 */
	public void run() {
		try {
			start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author Matan Ashkenazi and Noee Cohen
	 * @return
	 */
	public void start() throws Exception {
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
						else {
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

	private void printAllCommands() {
		for (Entry<String, Command> entry : commands.entrySet()) {
			if (entry.getValue() != null)
				entry.getValue().help();
		}
	}

}
