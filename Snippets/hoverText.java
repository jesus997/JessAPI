// Using Spigot patch 1.8

public static void sendTextHoverable(String playerName, String chatMessage, String clickableMessage, String hoverMessage) {
  BaseComponent[] hoverText = new ComponentBuilder(hoverMessage).create();
  HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverText);
  BaseComponent[] message = new ComponentBuilder(chatMessage).append(clickableMessage).event(hoverEvent).create();
  playerName.spigot().sendMessage(message);
}
