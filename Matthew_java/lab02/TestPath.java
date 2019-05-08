public class TestPath{
	public static void main(String[] args){
		Path p = new Path(2,3);
		for(int i = 0; i < 4; i += 1){
			p.iterate(1,3);
		}
	}
}