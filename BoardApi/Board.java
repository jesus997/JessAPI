package me.JessHilario.Util.Scoreboard;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class Board {
	public static List<Board> listOfBoard = new ArrayList<>();
	private String name;
	private Objective objective;
	private Scoreboard board;
	private int color = 0;
	private List<String> entries = new ArrayList<>();
	private static Plugin plugin;
	
	public Board(String name, Plugin p){
		setName(name);
		plugin = p;
		setBoard(Bukkit.getScoreboardManager().getNewScoreboard());
		listOfBoard.add(this);
	}
	
	public Board(String name, String criteria, DisplaySlot slot, Plugin p){
		setName(name);
		setBoard(Bukkit.getScoreboardManager().getNewScoreboard());
		setObjective(getBoard().registerNewObjective(name, criteria));
		getObjective().setDisplaySlot(slot);
		plugin = p;
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
	public String getTitle(){
		return getObjective().getDisplayName();
	}
	
	public Score getScore(String score){
		return getObjective().getScore(score);
	}
	
	public void addLine(String line, int i){
		line = line.replace("&", "§");
		if(color > 9) color = 0;
		line = line + "§" +  color;
		if(line.length() > 16){
			if(line.length() <= 32)
				addTeam(line.substring(0, 16), line.substring(16, line.length()), null, i);
			if(line.length() >  32 && line.length() <= 48)
				addTeam(line.substring(0, 16), line.substring(16, 32), line.substring(33, line.length()), i);
			color++;
		}else {
			if(getObjective().getScore(line) != null){
				removeLine(line);
			}
			Score s = getObjective().getScore(line);
			s.setScore(i);
			entries.add(line);
		}
	}
	
	public void addTeam(String prefix, String name, String sufix, int i){
		org.bukkit.scoreboard.Team t = board.registerNewTeam(name);
		t.setPrefix(prefix);
		if(sufix != null)
			t.setSuffix(sufix);
		t.addEntry(name);
		Score s = getObjective().getScore(name);
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
		if(oldLine.length() > 16){
			if(oldLine.length() <= 32)
				board.getTeam(oldLine.substring(16, oldLine.length())).unregister();; 
			if(oldLine.length() >  32 && oldLine.length() <= 48)
				board.getTeam(oldLine.substring(16, 32)).unregister();;
		}
		if(getObjective().getScore(oldLine).getScore() == i){
			removeLine(oldLine);
		}
		addLine(newLine, i);
	}
	
	public List<String> getEntries() {
		return entries;
	}

	public void setEntries(List<String> entries) {
		this.entries = entries;
	}
	
	public void update(){
		for(org.bukkit.scoreboard.Team t : getObjective().getScoreboard().getTeams()){
			t.unregister();
		}
		
		for(String s : board.getEntries()){
			board.resetScores(s);
		}
	}
	
	public static void startCaroucel(final List<Board> boards, final int time, final Collection<? extends Player> p){
		new BukkitRunnable(){
			int i = 0;
			public void run(){
				if(i > (boards.size() - 1)){
					i = 0;
				}
				
				for(Player p2 : p){
					p2.setScoreboard(boards.get(i).getBoard()); 
				}
				i++;
			}
		}.runTaskTimer(plugin, 0, time*20);
	}
}
