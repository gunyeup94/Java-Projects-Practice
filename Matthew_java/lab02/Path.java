/** A class that represents a path via pursuit curves. */
public class Path {

    // TODO
<<<<<<< HEAD
 	public Point curr;
    public Point next;

    public Path(double x, double y){
    	this.next = new Point(x,y);
    	this.curr = new Point(0,0);
    }

    public void iterate(double dx, double dy){
    	this.curr.x = this.next.x;
    	this.curr.y = this.next.y;
    	this.next.x = this.curr.x + dx;
    	this.next.y = this.curr.y + dy;
    }
=======

>>>>>>> 82d49a8f86de44bd346ef431816340221a8f46ff
}
