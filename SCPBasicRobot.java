
package scpbasicteam;

import java.io.IOException;
import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;

public class SCPBasicRobot extends TeamRobot {

	/**
	 * run:  Droid's default behavior
	 */
	public void run() {
		while (true) {
			ahead(100);
			back(100);
		}
	}

	/**
	 * onMessageReceived:  What to do when our leader sends a message
	 */
	public void onMessageReceived(MessageEvent e) {
		if (e.getMessage() instanceof RobotColors) {
			RobotColors c = (RobotColors) e.getMessage();

			setBodyColor(c.bodyColor);
			setGunColor(c.gunColor);
			setRadarColor(c.radarColor);
			setScanColor(c.scanColor);
			setBulletColor(c.bulletColor);
		}
		else if (e.getMessage() instanceof Enemy){
			Enemy target = (Enemy) e.getMessage();
			double x = target.getX() - this.getX();
			double y = target.getY() - this.getY();
			double angle = Math.toDegrees(Math.atan2(x, y));

			turnGunRight(normalRelativeAngleDegrees(angle - getGunHeading()));
			fire(3);
		}
	}
	
	/**
	 * onHitByBullet:  Turn perpendicular to bullet path
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		turnLeft(90 - e.getBearing());
	}

	/**
	 * onScannedRobot:  What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Don't fire on teammates
		if (isTeammate(e.getName())) {
			return;
		}
		// Calculate enemy bearing
		double enemyBearing = this.getHeading() + e.getBearing();
		// Calculate enemy's position
		double enemyX = getX() + e.getDistance() * Math.sin(Math.toRadians(enemyBearing));
		double enemyY = getY() + e.getDistance() * Math.cos(Math.toRadians(enemyBearing));
		double theta = Math.toDegrees(Math.atan2(enemyX, enemyY));
		// Turn gun to target
		turnGunRight(normalRelativeAngleDegrees(theta - getGunHeading()));
		// Fire hard!
		fire(3);
		// Turn wheels to target
		turnRight(normalRelativeAngleDegrees(theta - getGunHeading()));
	}
}