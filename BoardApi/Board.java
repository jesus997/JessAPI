package me.JessHilario.BoardApi;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

/**
 * BoardApi v2.0
 * @author JessHilario
 *
 */

public class Board {
	public static List<Board> listOfBoard = new ArrayList<>();
	private String name;
	private Objective objective;
	private Scoreboard board;
	private int color = 0;
	
	public Board(String name){
		setName(name);
		setBoard(Bukkit.getScoreboardManager().getNewScoreboard());
		listOfBoard.add(this);
	}
	
	public Board(String name, String criteria, DisplaySlot slot){
		setName(name);
		setBoard(Bukkit.getScoreboardManager().getNewScoreboard());
		setObjective(getBoard().registerNewObjective(name, criteria));
		getObjective().setDisplaySlot(slot);
		listOfBoard.add(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Objective getObjective() {
		return objective;
	}

	public void setObjective(Objective objective) {
		this.objective = objective;
	}

	public Scoreboard getBoard() {
		return board;
	}

	public void setBoard(Scoreboard board) {
		this.board = board;
	}
	
	public void setTitle(String title){
		getObjective().setDisplayName(title.replace("&", "§"));
	}
	public void getTitle(){
		getObjective().getDisplayName();
	}
	
	public Score getScore(String score){
		return getObjective().getScore(score);
	}
	
	public void addLine(String line, int i){
		line = line.replace("&", "§");
		if(line.length() > 16)
			line = line.substring(0, 16);
		if(getObjective().getScore(line) != null){
			removeLine(line);
		}
		Score s = getObjective().getScore(line);
		s.setScore(i);
	}
	
	public void addSpace(int i){
		if(color > 9)
			color = 0;
		Score s = getObjective().getScore("§" + color);
		s.setScore(i);
		color += 1;
	}
	
	public void removeLine(String line){
		line = line.replace("&", "§");
		getBoard().resetScores(line);
		if(getBoard().getTeam(line) != null)
			getBoard().getTeam(line).unregister();
	}
	
	public void updateLine(String oldLine, String newLine, int i){
		oldLine = oldLine.replace("&", "§");
		if(getObjective().getScore(oldLine).getScore() == i){
			removeLine(oldLine);
		}
		addLine(newLine, i);
	}
	
	public void update(Arena a){
		String criteria = getObjective().getCriteria();
		DisplaySlot slot = getObjective().getDisplaySlot();
		try{
			getObjective().unregister();
			setObjective(getBoard().registerNewObjective(getName(), criteria));
			getObjective().setDisplaySlot(slot);
			for(Player p : Bukkit.getOnlinePlayers()){
				p.getPlayer().setScoreboard(getBoard());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
