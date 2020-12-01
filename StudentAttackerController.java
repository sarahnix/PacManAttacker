package edu.ufl.cise.cs1.controllers;
import game.controllers.AttackerController;
import game.models.*;
import java.awt.*;
import java.util.List;

public final class StudentAttackerController implements AttackerController
{
	//optional
	public void init(Game game) { }

	//optional
	public void shutdown(Game game) { }

	//this is where the majority of the code will be
	public int update(Game game,long timeDue)
	{
		int action;

		//An example (which should not be in your final submission) of some syntax that randomly chooses a direction for the attacker to move
//		List<Integer> possibleDirs = game.getAttacker().getPossibleDirs(true);
//		if (possibleDirs.size() != 0)
//			action = possibleDirs.get(Game.rng.nextInt(possibleDirs.size()));
//		else
//			action = -1;
//
//		//An example (which should not be in your final submission) of some syntax to use the visual debugging method, addPathTo, to the top left power pill.
//		List<Node> powerPills = game.getPowerPillList();
//		if (powerPills.size() != 0) {
//			game.getAttacker().addPathTo(game, Color.BLUE, powerPills.get(0));


		Attacker goGata = game.getAttacker();
		List<Node> powerPills = game.getPowerPillList();
		Node targetPill = goGata.getTargetNode(game.getPillList(), true);
		Maze currentMaze = game.getCurMaze();

		if (powerPills.size() > 0) {
			Node closestPowerPill = powerPills.get(0);
			for (int i = 1; i < powerPills.size(); i++) {
				if (powerPills.get(i).getPathDistance(goGata.getLocation()) < closestPowerPill.getPathDistance(goGata.getLocation())) {
					closestPowerPill = powerPills.get(i);
				}
			}

			if (closestPowerPill.getPathDistance(goGata.getLocation()) < 10) {
				goGata.getPathTo(closestPowerPill);
				action = goGata.getNextDir(closestPowerPill, true);
			}

			else {
				action = goGata.getNextDir(targetPill, true);
			}
		}
		else {
			action = goGata.getNextDir(targetPill, true);
		}
//		for (int i = 1; i < powerPills.size(); i++) {
//			if (powerPills.get(i).getPathDistance(goGata.getLocation()) < closestPowerPill.getPathDistance(goGata.getLocation())) {
//				closestPowerPill = powerPills.get(i);
//			}
//		}
//
//		if (closestPowerPill.getPathDistance(goGata.getLocation()) < 10) {
//			goGata.getPathTo(closestPowerPill);
//			action = goGata.getNextDir(closestPowerPill, true);
//		}
//
//		else {
//			action = goGata.getNextDir(targetPill, true);
//		}
		return action;
	}
}