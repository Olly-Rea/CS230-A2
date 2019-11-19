/**
* Teleporter.java
* @version 1.0.0
* @author Daniel Clenaghan
*/

/**
* The class for the cell type Teleporter.
* It will store a Vector for both itself and a linked Teleporter
*/

public class Teleporter extends Cell {

 private Vector position; // Vector location of this teleporter
 private Vector linkedTele; // Vector location of linked teleporter

 /**
 * Create a cell of type teleporter with a given position in the map
 */
 public Teleporter (Vector position){

 }

 /**
 * Method to link two teleporters together
 * @param tele The teleporter to be linked
 */
 public void linkTele (Vector tele){

 }

 /**
 * Method to get the teleporter's location
 * @return position This teleporter's location
 */
 public Vector getPos(){
   return position;
 }

 /**
 * Method to get the linked teleporter's location
 * @return linkedTele The vector of the linked teleporter
 */
 public Vector getLinkedPos(){
   return linkedTele;
 }
}
