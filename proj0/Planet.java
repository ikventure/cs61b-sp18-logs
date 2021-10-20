public class Planet {
    private static final double gravConstant = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double distanceX = p.xxPos - xxPos;
        double distanceY = p.yyPos - yyPos;
        double r = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        return r;
    }

    public double calcForceExertedBy(Planet p) {
        double distance = calcDistance(p);
        double force = gravConstant * mass * p.mass / (distance * distance);
        return force;
    }

    public double calcForceExertedByX(Planet p) {
        double forceX = calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
        return forceX;
    }

    public double calcForceExertedByY(Planet p) {
        double forceY = calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
        return forceY;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netForceX = 0.0;
        for (Planet planet : allPlanets) {
            if (!(this.equals(planet))) {
                netForceX += this.calcForceExertedByX(planet);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netForceY = 0.0;
        for (Planet planet : allPlanets) {
            if (!(this.equals(planet))) {
                netForceY += this.calcForceExertedByY(planet);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
        double accX = fX / mass;
        double accY = fY / mass;
        xxVel += dt * accX;
        yyVel += dt * accY;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
