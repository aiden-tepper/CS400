import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

/**
 * Filename:   PackageManager.java
 * Project:    p4
 * Authors:    Aiden Tepper
 * 
 * PackageManager is used to process json package dependency files
 * and provide function that make that information available to other users.
 * 
 * Each package that depends upon other packages has its own
 * entry in the json file.  
 * 
 * Package dependencies are important when building software, 
 * as you must install packages in an order such that each package 
 * is installed after all of the packages that it depends on 
 * have been installed.
 * 
 * For example: package A depends upon package B,
 * then package B must be installed before package A.
 * 
 * This program will read package information and 
 * provide information about the packages that must be 
 * installed before any given package can be installed.
 * all of the packages in
 * 
 * You may add a main method, but we will test all methods with
 * our own Test classes.
 */

public class PackageManager {

	private Graph graph;

	/*
	 * Package Manager default no-argument constructor.
	 */
	public PackageManager() {
		graph = new Graph();
	}

	/**
	 * Takes in a file path for a json file and builds the
	 * package dependency graph from it. 
	 * 
	 * @param jsonFilepath the name of json data file with package dependency information
	 * @throws FileNotFoundException if file path is incorrect
	 * @throws IOException if the give file cannot be read
	 * @throws ParseException if the given json cannot be parsed 
	 */
	public void constructGraph(String jsonFilepath) throws FileNotFoundException, IOException, ParseException {

		Object obj = new JSONParser().parse(new FileReader(jsonFilepath));
		JSONObject jo = (JSONObject) obj;
		JSONArray array = (JSONArray) jo.get("packages");

		for(int i = 0; i < array.size(); i++) {

			JSONObject jsonPackage = (JSONObject) array.get(i);
			String name = (String) jsonPackage.get("name");
			graph.addVertex(name);			

			JSONArray dependencies = (JSONArray) jsonPackage.get("dependencies");
			for(int j = 0; j < dependencies.size(); j++) {
				String dependency = (String) dependencies.get(j);
				graph.addEdge(name, dependency);
			}

		}

	}

	/**
	 * Helper method to get all packages in the graph.
	 * 
	 * @return Set<String> of all the packages
	 */
	public Set<String> getAllPackages() {

		return graph.getAllVertices();

	}

	/**
	 * Given a package name, returns a list of packages in a
	 * valid installation order.  
	 * 
	 * Valid installation order means that each package is listed 
	 * before any packages that depend upon that package.
	 * 
	 * @return List<String>, order in which the packages have to be installed
	 * 
	 * @throws CycleException if you encounter a cycle in the graph while finding
	 * the installation order for a particular package. Tip: Cycles in some other
	 * part of the graph that do not affect the installation order for the 
	 * specified package, should not throw this exception.
	 * 
	 * @throws PackageNotFoundException if the package passed does not exist in the 
	 * dependency graph.
	 */
	public List<String> getInstallationOrder(String pkg) throws CycleException, PackageNotFoundException {

		List<String> order = new ArrayList<>();
		List<String> visitedList = new ArrayList<>();

		if(!graph.getAllVertices().contains(pkg)) {
			throw new PackageNotFoundException();
		}

		order = installationOrderHelper(pkg, order, visitedList);
		order.remove(pkg);

		return order;

	}

	/**
	 * Helper method for getInstallationOrder
	 * 
	 * @param pkg
	 * @param order
	 * @param visited
	 * @return order to return
	 * @throws CycleException
	 */
	private List<String> installationOrderHelper(String pkg, List<String> order, List<String> visitedList)
			throws CycleException {
		
		for(String visited : visitedList) {
			if(pkg.equals(visited)) {
				throw new CycleException();
			}
		}
		
		visitedList.add(pkg);

		if(!graph.getAdjacentVerticesOf(pkg).isEmpty()) {
			for(String dependency : graph.getAdjacentVerticesOf(pkg)) {
				installationOrderHelper(dependency, order, visitedList);
			}
		}

		order.add(pkg);
		return order;

	}

	/**
	 * Given two packages - one to be installed and the other installed, 
	 * return a List of the packages that need to be newly installed. 
	 * 
	 * For example, refer to shared_dependecies.json - toInstall("A","B") 
	 * If package A needs to be installed and packageB is already installed, 
	 * return the list ["A", "C"] since D will have been installed when 
	 * B was previously installed.
	 * 
	 * @return List<String>, packages that need to be newly installed.
	 * 
	 * @throws CycleException if you encounter a cycle in the graph while finding
	 * the dependencies of the given packages. If there is a cycle in some other
	 * part of the graph that doesn't affect the parsing of these dependencies, 
	 * cycle exception should not be thrown.
	 * 
	 * @throws PackageNotFoundException if any of the packages passed 
	 * do not exist in the dependency graph.
	 */
	public List<String> toInstall(String newPkg, String installedPkg) throws CycleException, PackageNotFoundException {

		List<String> newInstallationOrder = getInstallationOrder(newPkg);
		List<String> previousInstallationOrder = getInstallationOrder(installedPkg);

		if(newInstallationOrder.contains(installedPkg)) {
			newInstallationOrder.remove(installedPkg);
		}

		for(String pkg : previousInstallationOrder) {
			if(newInstallationOrder.contains(pkg)) {
				newInstallationOrder.remove(pkg);
			}
		}

		return newInstallationOrder;

	}

	/**
	 * Return a valid global installation order of all the packages in the 
	 * dependency graph.
	 * 
	 * assumes: no package has been installed and you are required to install 
	 * all the packages
	 * 
	 * returns a valid installation order that will not violate any dependencies
	 * 
	 * @return List<String>, order in which all the packages have to be installed
	 * @throws CycleException if you encounter a cycle in the graph
	 */
	public List<String> getInstallationOrderForAllPackages() throws CycleException {

		List<String> order = new ArrayList<>();
		int numVertices = graph.getAllVertices().size();
		Set<String> verticesListSet = graph.getAllVertices();
		ArrayList<String> verticesList = new ArrayList<>();

		for(String pkg : verticesListSet) {
			verticesList.add(pkg);
		}

		boolean[] visited = new boolean[numVertices];
		for(int i = 0; i < visited.length; i++) {
			visited[i] = false;
		}

		for(int i = 0; i < numVertices; i++) {
			if(visited[i] == false) {
				Object[] x = orderAllPackagesHelper(i, verticesList.get(i), visited, order);
				visited = (boolean[]) x[0];
				order = (List<String>) x[1];
			}
		}
		
		List<String> reversedOrder = new ArrayList<>();
		
		for(int i = order.size() - 1; i >= 0; i--) {
			reversedOrder.add(order.get(i));
		}

		return reversedOrder;

	}

	/**
	 * Helper method for getInstallationOrderForAllPackages.
	 * 
	 * @param i
	 * @param pkg
	 * @param visited
	 * @param order
	 * @return array that contains both the visited array and the order List
	 */
	private Object[] orderAllPackagesHelper(int i, String pkg, boolean[] visited, List<String> order) {

		visited[i] = true;

		if(!graph.getAdjacentVerticesOf(pkg).isEmpty()) {
			int j = i;
			for(String dependency : graph.getAdjacentVerticesOf(pkg)) {
				if(!visited[j]) {
					orderAllPackagesHelper(j, dependency, visited, order);
				}
			}
		}

		order.add(pkg);

		Object[] x = new Object[2];
		x[0] = visited;
		x[1] = order;
		return x;

	}

	/**
	 * Find and return the name of the package with the maximum number of dependencies.
	 * 
	 * Tip: it's not just the number of dependencies given in the json file.  
	 * The number of dependencies includes the dependencies of its dependencies.  
	 * But, if a package is listed in multiple places, it is only counted once.
	 * 
	 * Example: if A depends on B and C, and B depends on C, and C depends on D.  
	 * Then,  A has 3 dependencies - B,C and D.
	 * 
	 * @return String, name of the package with most dependencies.
	 * @throws CycleException if you encounter a cycle in the graph
	 */
	public String getPackageWithMaxDependencies() throws CycleException {

		String packageWithMaxDependencies = "";
		int dependencies = 0;

		try {
			for(String pkg : getAllPackages()) {
				if(getInstallationOrder(pkg).size() > dependencies) {
					packageWithMaxDependencies = pkg;
					dependencies = getInstallationOrder(pkg).size();
				}
			}
		} catch(Exception e) {
			System.out.println(e);
		}

		return packageWithMaxDependencies;

	}

	public static void main (String [] args) {
		System.out.println("PackageManager.main()");
		PackageManager test = new PackageManager();
		try {
			test.constructGraph("C:\\Users\\Aiden Tepper\\OneDrive - UW-Madison\\CS 400\\p4_project\\valid.json");
			System.out.println(test.getInstallationOrderForAllPackages());
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}
