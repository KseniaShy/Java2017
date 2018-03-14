package ru.shok.javacore2017;

import java.io.File;

class HelloJava {
	public static void main(String[] args) {
		String osName = System.getProperty("os.name");
		String osVersion = System.getProperty("os.version");
		String javaVersion = System.getProperty("java.version");

		System.out.printf("Hello, Java from %s %s %s %s %s\n",
			convertCliArgumentsToString(args),
			osName,
			osVersion,
			javaVersion,
			getJdkPath()
		);
	}

	private static String convertCliArgumentsToString(String[] arguments) {
		StringBuilder result = new StringBuilder();
		for (String argument : arguments) {
			result.append(argument);
			result.append(" ");
		}
		if (result.length() > 0) {
			result.deleteCharAt(result.length() - 1);
		}
		return result.toString();
	}

	private static String getJdkPath() {
		String jrePath = System.getProperty("java.home");
		return (new File(jrePath)).getParentFile().getAbsolutePath() + "\\jdk-9.0.4";
	}
}
