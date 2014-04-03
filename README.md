RoboCode
========
http://robocode.sourceforge.net/

## WHAT IS ROBOCODE?

Robocode is a programming game where the goal is to code a robot battle tank to compete against other robots in a battle arena. So the name Robocode is a short for "Robot code". The player is the programmer of the robot, who will have no direct influence on the game. Instead, the player must write the AI of the robot telling it how to behave and react on events occurring in the battle arena. Battles are running in real-time and on-screen.
# Estrategy:
##Leader Robot:
This Robot follows a moviment pattern that looks for the walls and keep moving following them.
When he meet another robot either enmy or friend, rotates 90 degrees and keeps going on with the pattern. In the meanwhile has activated the radar and spining, to make a search for all the map, when detects a robot, if is enemy creates a new object Enemy Class and sends a broadcast message to all the team mates.

## Basic Robot:

This Robot is really simple, only goes backwards and towards. Has a Enemy message handler that rotates the cannon to the given enemy and shoots, also rotate the wheels towards him (to get closer to him)


