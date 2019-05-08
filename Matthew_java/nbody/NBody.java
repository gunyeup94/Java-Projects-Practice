public class NBody{
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planetsarray = readPlanets(filename);
        String imageToDraw = "images/starfield.jpg";
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, imageToDraw);
        for(int i = 0; i < planetsarray.length; i++)
            planetsarray[i].draw();
        StdDraw.show();
        StdDraw.enableDoubleBuffering();

        for(double time = 0; time<T; time += dt){
            double[] xForces = new double[planetsarray.length];
            double[] yForces = new double[planetsarray.length];
            for(int i = 0; i < planetsarray.length; i++){
                xForces[i] = planetsarray[i].calcNetForceExertedByX(planetsarray);
                yForces[i] = planetsarray[i].calcNetForceExertedByY(planetsarray);
                planetsarray[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.clear();
            StdDraw.picture(0, 0, imageToDraw);
            for(int i = 0; i < planetsarray.length; i++)
                planetsarray[i].draw();
            StdDraw.show();
        }
        StdOut.printf("%d\n", planetsarray.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planetsarray.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planetsarray[i].xxPos, planetsarray[i].yyPos, planetsarray[i].xxVel,
                    planetsarray[i].yyVel, planetsarray[i].mass, planetsarray[i].imgFileName);
        }
    }

	public static double readRadius(String file){
		In in = new In(file);
		in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String file){
		In in = new In(file);
		int times = in.readInt();
		in.readDouble();
		Planet[] planetsarray = new Planet[times];
		for (int i = 0; i < times; i++){
			planetsarray[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
			in.readLine();
		}
		return planetsarray;
	}
}