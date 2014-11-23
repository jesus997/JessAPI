package me.JessHilario.UUIDApi;

import java.util.HashMap;
import java.util.UUID;

/**
 * 
 * With this API you can convert any text to UUID and vice versa.
 * How to use:
 * UUIDApi UUIDname = new UUIDApi("Text here!");
 * 
 * @author JessHilario
 *
 * Visit my channel: www.youtube.com/MrJesus997 (Spanish*)
 */
public class UUIDApi {
  public static HashMap<String, UUID> UUIDs = new HashMap<>();
  private String name;
  private UUID uuid;

  public UUIDApi(String name) {
    UUID uuid = UUID.randomUUID();
    this.name = name;
    this.uuid = uuid;
    UUIDs.put(name, uuid);
  }
  
  /**
   * Gets the value of the stored String.
   * @return 
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the value of the String stored.
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the value of the stored UUID.
   * @return
   */
  public UUID getUUID() {
    return this.uuid;
  }

  /**
   * Sets the value of the stored UUID.
   * @param uuid
   */
  public void setUUID(UUID uuid) {
    this.uuid = uuid;
  }

  /**
   * Gets the String stored from a given UUID.
   * <p>If there is no String returns null.</p>
   * @param uuid
   * @return
   */
  public static String getNameFromUUID(UUID uuid) {
    for (UUID uuids : UUIDs.values()) {
      if (uuids.equals(uuid)) {
        for (String key : UUIDs.keySet()) {
          if (((UUID)UUIDs.get(key)).equals(uuid)) {
            return key;
          }
        }
      }
    }
    return null;
  }

  /**
   * Gets a UUID from a given String.
   * <p>If not, it returns a null value.</p>
   * @param name
   * @return
   */
  public static UUID getUUIDFromName(String name) {
    if (UUIDs.containsKey(name))
      return (UUID)UUIDs.get(name);
    return null;
  }

  public static void remove(String name) {
    if (UUIDs.containsKey(name))
      UUIDs.remove(name);
  }

  public static void remove(UUID uuid)
  {
    if (UUIDs.containsKey(getNameFromUUID(uuid)))
      UUIDs.remove(uuid);
  }

  public void remove()
  {
    if (UUIDs.containsKey(this.name))
      UUIDs.remove(this.name);
  }
}
