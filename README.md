# GA-A* Pathfinding Algorithm

A hybrid **Genetic Algorithm (GA) + A*** pathfinding system that evolves heuristics to optimize search performance on complex grids.

This project explores how **evolutionary algorithms can learn better heuristics** for classical graph search, reducing node expansions and improving path quality under varying obstacle densities.

---

## Overview

Traditional A* relies on a fixed heuristic (e.g., Euclidean or Manhattan distance). In this project, a **Genetic Algorithm evolves heuristic parameters** that guide A* dynamically.

Each chromosome (gene vector) represents a weighted heuristic function. Over generations, the GA selects and mutates heuristics that:

* Reach the goal successfully
* Expand fewer nodes
* Produce shorter / cheaper paths

The best-performing gene is then used by A* to perform near-optimal or optimal search.

---

## Core Concepts

### 1. A* Search

* Uses `f(n) = g(n) + h(n)`
* Supports **8-directional movement** (including diagonals)
* Can run in:

  * **Pure Dijkstra mode** (no heuristic)
  * **Standard A***
  * **GA-optimized A***

### 2. Genetic Algorithm

* Population-based optimization of heuristic weights
* Each individual represents a heuristic configuration
* Fitness is evaluated by running A* using the individual's genes

---

## Gene Representation

A gene is a `double[]` containing heuristic weights, for example:

* Distance to goal
* Obstacle proximity penalty
* Directional bias
* Diagonal movement preference

These weights are combined inside the heuristic function used by A*.

---

## Fitness Function

The fitness score is computed using:

* **Nodes expanded** (lower is better)
* **Path cost / length**
* **Goal reachability** (hard penalty if goal not reached)

---

## Grid Environment

* Grid size: `100 x 100`
* Random obstacle generation
* Adjustable obstacle density
* Deterministic generation via seed (for reproducibility)

---

## Outputs & Metrics

For each run, the system tracks:

* Nodes expanded
* Path length / cost
* Best gene per generation

Grid and path visualization has been added in terminal output at the end.

---

## Tech Stack

* **Language:** Java
* **Algorithms:** A*, Dijkstra, Genetic Algorithm
* **Data Structures:** PriorityQueue, Arrays, Custom Node classes

---

## Key Learnings

* Heuristic quality dramatically affects A* performance. On a 100×100 grid, GA-A* reduced node expansions by ≈81% compared to standard A*, while preserving optimal path cost.
* GA can discover **non-intuitive heuristic weightings**
* Hybrid algorithms outperform static approaches in complex environments

---

## Author

**Aryan Srivastava**
Information Technology Undergraduate
Exploring Software Engineering, AI, algorithms, and intelligent systems

---

⭐ If you find this project interesting, consider starring the repo!
