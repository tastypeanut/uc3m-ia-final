package practica.busqueda.basico;

import java.awt.event.ContainerAdapter;
import java.util.ArrayList;
import java.util.List;

import practica.busqueda.basico.Node;
import practica.busqueda.basico.OpenList;
import practica.objetos.Areas;
import practica.objetos.Herramienta;
import practica.objetos.Tarea;
import practica.objetos.Trabajador;

/**
 * Clase creada como base para la parte 2 de la pr�ctica 2019-2020 de Inteligencia Artificial, UC3M, Colmenarejo
 *
 * @author Daniel Amigo Herrero
 * @author David S�nchez Pedroche
 *
 */

public class AStar {

	int printDebug; // 0: nada, 1: informaci�n b�sica, 2: informaci�n completa

	private OpenList openList = new OpenList();						// Lista de nodos por explorar
	private ArrayList<Node> closedList = new ArrayList<Node>();		// Lista de nodos explorados
	private Node initialNode;										// Nodo inicial del problema
	private Node goalNode;											// Nodo meta del problema
	private boolean findGoal;										// Se ha encontrado la meta

	/**
	 * Insertamos en la lista de nodos abiertos los nodos seg�n las acciones que se pueden realizar en este instante
	 * MODIFICAR
	 * @param currentNode - el nodo actual
	 */
	/*private void addAdjacentNodes(Node currentNode) {
		ArrayList<Trabajador> trabajadores  = currentNode.getTrabajadores();
		ArrayList<Herramienta> herramientas = currentNode.getHerramientas();
		ArrayList<Tarea> tareas             = currentNode.getTareas();
		System.out.println("Entra en addAdjacentNodes");
		int indexTrabajador = 0;
		for (Trabajador trabajador : trabajadores){
			if (trabajador.getNombre().equals("Antonio")){
				//Salida del almacén
				if(trabajador.getArea() == Areas.A && trabajador.getHerramienta() == null){
					habilidadisponible:
					for(String habilidad : trabajador.getHabMax()){
						for (Tarea tarea : tareas){
							if(tarea.getTipo().equals(habilidad) && tarea.getUnidades() > 0){
								Node NodoHijo = new Node(currentNode);
								for(Herramienta herramienta : NodoHijo.getHerramientas()){
									if(herramienta.getTrabajo().equals(habilidad) && herramienta.getMejora() == 0) {
										NodoHijo.getTrabajadores().get(indexTrabajador).setHerramienta(herramienta);
										if(checkNode(NodoHijo)){
											NodoHijo.computeHeuristic(goalNode);
											NodoHijo.computeEvaluation();
											NodoHijo.setParent(currentNode);
											openList.insertAtEvaluation(NodoHijo);
										}
									}
								}
								break habilidadisponible;
							}
						}
					}
				}
				//Movimiento entre celdas
				boolean encontrado = false;
				if(trabajador.getHerramienta() != null){
					int indexTarea = 0;
					for (Tarea tarea : tareas){
						if (tarea.getTipo().equals(trabajador.getHerramienta().getTrabajo()) && tarea.getUnidades() > 0){
							Node NodoHijo = new Node(currentNode);
							NodoHijo.getTareas().get(indexTarea).setUnidades(0);
							NodoHijo.getTrabajadores().get(indexTrabajador).setMinutosTrabajados(tarea.getArea(), tarea.getUnidades(), 0);
							NodoHijo.getTrabajadores().get(indexTrabajador).setArea(tarea.getArea());
							NodoHijo.setCoste(NodoHijo.getCost() + (NodoHijo.getTrabajadores().get(indexTrabajador).getMinutosTrabajados() - trabajador.getMinutosTrabajados()));
							if(checkNode(NodoHijo)){
								NodoHijo.computeHeuristic(goalNode);
								NodoHijo.computeEvaluation();
								NodoHijo.setParent(currentNode);
								openList.insertAtEvaluation(NodoHijo);
							}
							encontrado = true;
						}
						indexTarea++;
					}
				}
				if(!encontrado && trabajador.getHerramienta() != null){
					Node NodoHijo = new Node(currentNode);
					NodoHijo.getTrabajadores().get(indexTrabajador).setMinutosTrabajados("A");
					NodoHijo.getTrabajadores().get(indexTrabajador).setHerramienta();
					NodoHijo.getTrabajadores().get(indexTrabajador).setArea(Areas.A);
					NodoHijo.setCoste(NodoHijo.getCost() + (NodoHijo.getTrabajadores().get(indexTrabajador).getMinutosTrabajados() - trabajador.getMinutosTrabajados()));
					if(checkNode(NodoHijo)){
						NodoHijo.computeHeuristic(goalNode);
						NodoHijo.computeEvaluation();
						NodoHijo.setParent(currentNode);
						openList.insertAtEvaluation(NodoHijo);
					}
				}
			}
			indexTrabajador++;
		}
	}
*/
	private void addAdjacentNodes(Node currentNode) {
		ArrayList<Trabajador> trabajadores  = currentNode.getTrabajadores();
		ArrayList<Herramienta> herramientas = currentNode.getHerramientas();
		ArrayList<Tarea> tareas             = currentNode.getTareas();
		System.out.println("Entra en addAdjacentNodes");
		int indexTrabajador = 0;
		for (Trabajador trabajador : trabajadores){
			if (trabajador.getNombre().equals("Antonio")){
				//Salida del almacén
				if(trabajador.getArea() == Areas.A && trabajador.getHerramienta() == null){
					habilidadisponible:
					for(String habilidad : trabajador.getHabMax()){
						for (Tarea tarea : tareas){
							if(tarea.getTipo().equals(habilidad) && tarea.getUnidades() > 0){
								Node NodoHijo = new Node(currentNode);
								for(Herramienta herramienta : NodoHijo.getHerramientas()){
									if(herramienta.getTrabajo().equals(habilidad) && herramienta.getMejora() == 0) {
										NodoHijo.getTrabajadores().get(indexTrabajador).setHerramienta(herramienta);
										if(checkNode(NodoHijo)){
											NodoHijo.computeHeuristic(goalNode);
											NodoHijo.computeEvaluation();
											NodoHijo.setParent(currentNode);
											NodoHijo.setNextNode(null);
											openList.insertAtEvaluation(NodoHijo);
										}
									}
								}
								break habilidadisponible;
							}
						}
					}
				}
				//Movimiento entre celdas
				boolean encontrado = false;
				if(trabajador.getHerramienta() != null){
					int indexTarea = 0;
					for (Tarea tarea : tareas){
						if (tarea.getTipo().equals(trabajador.getHerramienta().getTrabajo()) && tarea.getUnidades() > 0){
							Node NodoHijo = new Node(currentNode);
							NodoHijo.getTareas().get(indexTarea).setUnidades(0);
							NodoHijo.getTrabajadores().get(indexTrabajador).setMinutosTrabajados(tarea.getArea(), tarea.getUnidades(), 0);
							NodoHijo.getTrabajadores().get(indexTrabajador).setArea(tarea.getArea());
							NodoHijo.setCoste(NodoHijo.getCost() + (NodoHijo.getTrabajadores().get(indexTrabajador).getMinutosTrabajados() - trabajador.getMinutosTrabajados()));
							if(checkNode(NodoHijo)){
								NodoHijo.computeHeuristic(goalNode);
								NodoHijo.computeEvaluation();
								NodoHijo.setParent(currentNode);
								NodoHijo.setNextNode(null);
								openList.insertAtEvaluation(NodoHijo);
							}
							encontrado = true;
						}
						indexTarea++;
					}
				}
				if(!encontrado && trabajador.getHerramienta() != null){
					Node NodoHijo = new Node(currentNode);
					NodoHijo.getTrabajadores().get(indexTrabajador).setMinutosTrabajados("A");
					NodoHijo.getTrabajadores().get(indexTrabajador).setHerramienta();
					NodoHijo.getTrabajadores().get(indexTrabajador).setArea(Areas.A);
					NodoHijo.setCoste(NodoHijo.getCost() + (NodoHijo.getTrabajadores().get(indexTrabajador).getMinutosTrabajados() - trabajador.getMinutosTrabajados()));
					if(checkNode(NodoHijo)){
						NodoHijo.computeHeuristic(goalNode);
						NodoHijo.computeEvaluation();
						NodoHijo.setParent(currentNode);
						NodoHijo.setNextNode(null);
						openList.insertAtEvaluation(NodoHijo);
					}
				}
			}
			indexTrabajador++;
		}
	}
	/**
	 * Implementaci�n de A estrella
	 */
	public double Algorithm() {
		double initialTime = Double.parseDouble(""+System.currentTimeMillis()); // Para contar el tiempo de ejecuci�n

		Node currentNode = null;

		while(!this.openList.isEmpty()) { 				// Recorremos la lista de nodos sin explorar
			currentNode = this.openList.pullFirst(); 	// Extraemos el primero (la lista esta ordenada segun la funcion de evaluaci�n)
			System.out.println("Entra en el bucle");
			if(checkNode(currentNode)) {				// Si el nodo ya se ha visitado con un coste menor (esta en la lista de explorados) lo ignoramos
				currentNode.printNodeData(printDebug);
				closedList.add(currentNode); 			// A�adimos dicho nodo a la lista de explorados

				if(this.getGoalNode().equals(currentNode)) {	// Si es el nodo meta hemos acabado y no hace falta continuar
					this.setGoalNode(currentNode);
					this.setFindGoal(true);
					System.out.println("Es nodo final");
					break;
				}
				this.addAdjacentNodes(currentNode); 	// Expandimos el nodo segun las acciones posibles
			}
			printOpenList(); // @TODO eliminar

		}

		// Para contar el tiempo de ejecuci�n
		double fin    = Double.parseDouble(""+System.currentTimeMillis());
		double tiempo = (fin - initialTime) / 1000;
		return tiempo;
	}


	/**
	 * Constructor del algoritmo, obtiene el nodo de inicio y el nodo meta
	 * NO MODIFICAR
	 * @param initialNode
	 * @param goalNode
	 */
	public AStar(int printDebug, Node initialNode, Node goalNode) {
		this.printDebug = printDebug;
		this.setInitialNode(initialNode);
		this.setGoalNode(goalNode);
		this.setFindGoal(false); 					// No se ha llegado al nodo meta

		// Introducir heur�sticas y costes para el nodo inicial. El nodo meta solo tiene heur�stica
		initialNode.computeHeuristic(goalNode);	// Coste esperado por la heur�stica para llegar al nodo final desde el inicial
		initialNode.setCoste(0);					// el nodo inicial tiene coste cero
		initialNode.computeEvaluation();			// coste + heur�stica
		goalNode.computeHeuristic(goalNode);		// Debe ser 0, ya es el nodo final

		// Genera la lista de nodos explorados y sin explorar
		this.closedList = new ArrayList<Node>();
		this.openList   = new OpenList();
		this.openList.insertAtEvaluation(initialNode); // A�adimos a la lista de nodos sin explorar el nodo inicial
	}


	/**
	 * Comprobaci�n de si el nodo ya se ha explorado
	 * NO MODIFICAR
	 * @param currentNode
	 * @return
	 */
	private boolean checkNode(Node currentNode) {
		boolean expandirNodo = true;
		for (Node node : this.closedList) { // Se observa si el nodo est� en la lista de cerrados
			if(currentNode.equals(node)) {	// Comprueba si la informaci�n del nodo es igual
				expandirNodo = false;
				break;
			}
		}
		return expandirNodo;				// false en el caso de que el nodo se haya visitado, indicando que no hay que expandirlo
	}
	public void printOpenList(){
		System.out.println(openList.getSize());
	}

	/**
	 * Metodo para calcular el camino desde el nodo Inicial hasta el nodo actual
	 * NO MODIFICAR
	 * @param currentNode
	 * @return lista de nodos ordenada, desde el primer nodo al ultimo
	 */
	public List<Node> getPath(Node currentNode) {
		List<Node> path = new ArrayList<Node>();
		path.add(currentNode);
		Node parent;
		while ((parent = currentNode.getParent()) != null) {	// Desde el nodo actual, se busca el nodo padre y se insertan
			path.add(0, parent);								//  dentro de la lista de manera inversa
			currentNode = parent;
		}
		return path;
	}


	/**** Getters y Setters ****/
	/**
	 * MODIFICAR y/o A�ADIR si se considera necesario. No es imprescindible, solo si se considera que puede ayudar a la implementaci�n
	 */
	public Node getInitialNode() {
		return initialNode;
	}
	public void setInitialNode(Node initialNode) {
		this.initialNode = initialNode;
	}
	public boolean isFindGoal() {
		return findGoal;
	}
	public void setFindGoal(boolean findGoal) {
		this.findGoal = findGoal;
	}
	public Node getGoalNode() {
		return goalNode;
	}
	public void setGoalNode(Node goalNode) {
		this.goalNode = goalNode;
	}

}