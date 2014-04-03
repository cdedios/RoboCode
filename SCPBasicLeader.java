package scpbasicteam;

import robocode.*;

import java.awt.*;
import java.io.IOException;

public class SCPBasicLeader extends TeamRobot {

	boolean peek; // Don't turn if there's a robot there
	double moveAmount; // How much to move
	//Enemy tar = null;
	/**
	 * run:  Leader's default behavior
	 */
	public void run() {
		// Prepare RobotColors object
		RobotColors c = new RobotColors();

		c.bodyColor = Color.yellow;
		c.gunColor = Color.yellow;
		c.radarColor = Color.yellow;
		c.scanColor = Color.yellow;
		c.bulletColor = Color.yellow;

		// Set the color of this robot containing the RobotColors
		setBodyColor(c.bodyColor);
		setGunColor(c.gunColor);
		setRadarColor(c.radarColor);
		setScanColor(c.scanColor);
		setBulletColor(c.bulletColor);
		try {
			// Send RobotColors object to our entire team
			broadcastMessage(c);
		} catch (IOException ignored) {}
		// Normal behavior
		
		// Initialize moveAmount to the maximum possible for this battlefield.
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		// Initialize peek to false
		peek = false;

		// turnLeft to face a wall.
		// getHeading() % 90 means the remainder of
		// getHeading() divided by 90.
		turnLeft(getHeading() % 90);
		ahead(moveAmount);
		// Turn the gun to turn right 90 degrees.
		peek = true;
		turnGunRight(90);
		turnRight(90);
		
		while (true) {
			setTurnRadarRight(10000);
			// Look before we turn when ahead() completes.
			peek = true;
			// Move up the wall
			ahead(moveAmount);
			// Don't look now
			peek = false;
			// Turn to the next wall
			turnRight(90);
		}
	}

	/**
	 * onScannedRobot:  What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Don't fire on teammates
		if (isTeammate(e.getName())) {
			return;
		}
		else{
			double enemyBearing = this.getHeading() + e.getBearing();
			// Calculate enemy's position
			double enemyX = getX() + e.getDistance() * Math.sin(Math.toRadians(enemyBearing));
			double enemyY = getY() + e.getDistance() * Math.cos(Math.toRadians(enemyBearing));
	
			try {
				// Send enemy position to teammates
				Enemy targt = new Enemy(e.getName(),enemyX,enemyY);
				broadcastMessage(targt);
			} catch (IOException ex) {
				//cathc exception 
				out.println("Unable to send order: ");
				ex.printStackTrace(out);
			}
		}
	}
	/**
	 * onMessageReceived:  What to do when recive a message
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
	}
	/**
	 * onHitRobot:  What to do when some robot hit us: Move away a bit.
	 */
	public void onHitRobot(HitRobotEvent e) {
		// If he's in front of us, set back up a bit.
		if (e.getBearing() > -90 && e.getBearing() < 90) {
			back(100);
		} // else he's in back of us, so set ahead a bit.
		else {
			ahead(100);
		}
	}
	public void onHitByBullet(HitByBulletEvent e) {
		turnLeft(90 - e.getBearing());
	}
	//public boolean isTarget(String name){
	//	return tar.getName()==name;
	//}
}																	
