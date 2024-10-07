package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import wordgenerator.utils.ConsolePrinter;

public class PackageClassGetter {

	public static void main(String[] args) {
		List<String> classNames = getClasses("wordgenerator.words.impl");
		for (String className : classNames) {
			ConsolePrinter.println(className);
		}
	}

	public static List<String> getClasses(String packageName) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = packageName.replace('.', '/');
		ArrayList<String> classNames = new ArrayList<>();

		try {
			Enumeration<java.net.URL> resources = classLoader.getResources(path);
			List<java.io.File> dirs = new ArrayList<>();

			while (resources.hasMoreElements()) {
				java.net.URL resource = resources.nextElement();
				dirs.add(new java.io.File(resource.getFile()));
			}

			for (java.io.File directory : dirs) {
				classNames.addAll(findClasses(directory, packageName));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classNames;
	}

	private static List<String> findClasses(java.io.File directory, String packageName) {
		List<String> classNames = new ArrayList<>();
		if (!directory.exists()) {
			return classNames;
		}

		java.io.File[] files = directory.listFiles();
		if (files == null) {
			return classNames;
		}

		for (java.io.File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classNames.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classNames.add(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
			}
		}

		return classNames;
	}
}
