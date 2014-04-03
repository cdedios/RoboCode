RoboCode
========

### Estrategy:
##Leader Robot:
This Robot follows a moviment pattern that looks for the walls and keep moving following them.
When he meet another robot either enmy or friend, rotates 90 degrees and keeps going on with the pattern. In the meanwhile has activated the radar and spining, to make a search for all the map, when detects a robot, if is enemy creates a new object Enemy Class and sends a broadcast message to all the team mates.

## Basic Robot:

This Robot is really simple, only goes backwards and towards. Has a Enemy message handler that rotates the cannon to the given enemy and shoots, also rotate the wheels towards him (to get closer to him)


