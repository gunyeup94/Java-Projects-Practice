public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}


	public double calcDistance(Planet p){
		double distance = Math.sqrt(Math.pow((this.xxPos-p.xxPos), 2) + Math.pow((this.yyPos-p.yyPos),2));
		return distance;
	}

	public double calcForceExertedBy(Planet p){
		double gravity = 6.67 * Math.pow(10, -11);
		double force = (gravity * p.mass * this.mass) / (Math.pow(calcDistance(p), 2));
		return force;
	}

	public double calcForceExertedByX(Planet p){
		double force = calcForceExertedBy(p);
		double force_x = force * (p.xxPos - this.xxPos)/(calcDistance(p));
		return force_x;
	}

	public double calcForceExertedByY(Planet p){
		double force = calcForceExertedBy(p);
		double force_y = force * (p.yyPos - this.yyPos) / (calcDistance(p));
		return force_y;
	}

	public double calcNetForceExertedByX(Planet[] planets){
		double netForceByX = 0;
		for(int i = 0; i < planets.length; i++){
			if(planets[i].equals(this)){
				continue;
			} else {
				netForceByX += calcForceExertedByX(planets[i]);
			}
		}
		return netForceByX;
	}

	public double calcNetForceExertedByY(Planet[] planets){
		double netForceByY = 0;
		for(int i = 0; i < planets.length; i++){
			if(planets[i].equals(this)){
				continue;
			} else {
				netForceByY += calcForceExertedByY(planets[i]);
			}
		}
		return netForceByY;
	}

	public void update(double dt, double fX, double fY) {
	    double accelerateX = fX / this.mass;
	    double accelerateY = fY / this.mass;
	    this.xxVel += accelerateX * dt;
	    this.xxPos += this.xxVel * dt;
	    this.yyVel += accelerateY * dt;
	    this.yyPos += this.yyVel * dt;
    }

    public void draw(){
    	StdDraw.picture(xxPos, yyPos, imgFileName);
    	StdDraw.show();
    }
}