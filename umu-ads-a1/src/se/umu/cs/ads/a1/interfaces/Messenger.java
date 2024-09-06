package se.umu.cs.ads.a1.interfaces;

import se.umu.cs.ads.a1.types.Message;
import se.umu.cs.ads.a1.types.MessageId;
import se.umu.cs.ads.a1.types.Topic;
import se.umu.cs.ads.a1.types.Username;

public interface Messenger
{
  //----------------------------------------------------------
  //----------------------------------------------------------
  // message interface

  //----------------------------------------------------------
	/***
	 * Store a message
	 * @param message Message to be stored
	 */
	void store (Message message);

  //----------------------------------------------------------
  /***
   * Batch store a set of messages
   * @param message Messages to be stored
   */
  void store (Message[] messages);

  //----------------------------------------------------------
  /***
   * Retrieve a message
   * @param id Message to retrieve
   * @return Message
   * @throws IllegalArgumentException if message is not found
   */
  Message retrieve (MessageId message);

  //----------------------------------------------------------
  /***
   * Batch retrieve a set of messages
   * @param ids Messages to retrieve
   * @return Messages
   * @throws IllegalArgumentException if message is not found
   */
  Message[] retrieve (MessageId[] message);

  //----------------------------------------------------------
  /***
   * Deletes a message
   * @param message Message to delete
   */
  void delete (MessageId message);

  //----------------------------------------------------------
  /***
   * Batch delete a set of messages
   * @param message Messages to delete
   */
  void delete (MessageId[] messages);


  //----------------------------------------------------------
  //----------------------------------------------------------
  // subscription interface

  //----------------------------------------------------------
  /***
   * Subscribe a user to a topic
   * @param username Name of user
   * @param topic Message topic
   * @return identifiers of created subscriptions
   */
  Topic[] subscribe (Username username, Topic topic);

  //----------------------------------------------------------
  /***
   * Unsubscribe a user from a topic
   * @param username Name of user
   * @param topic Message topics
   * @return identifiers of deleted subscriptions
   */
  Topic[] unsubscribe (Username username, Topic topic);


  //----------------------------------------------------------
  //----------------------------------------------------------
  // query interface

  //----------------------------------------------------------
  /***
   * Lists users  
   * @return all active users
   */
  Username[] listUsers ();

  //----------------------------------------------------------
  /***
   * Lists topics  
   * @return all available topics
   */
  Topic[] listTopics ();

  //----------------------------------------------------------
  /***
   * Lists user topics
   * @param username Name of user
   * @return User topics
   */
  Topic[] listTopics (Username username);

  //----------------------------------------------------------
  /***
   * Lists topic(s) subscribers (users subscribed to topic(s))
   * @param topic Message topic
   * @return Names of users subscribed to topic(s)
   */
  Username[] listSubscribers (Topic topic);

  //----------------------------------------------------------
  /***
   * List identifiers of all messages for a specific user
   * @param topic Name of user
   * @return Message identifiers
   */
  MessageId[] listMessages (Username username);

  //----------------------------------------------------------
  /***
   * List identifiers of all messages for specified topic(s)
   * @param topic Message topic
   * @return Message identifiers
   */
  MessageId[] listMessages (Topic topic);
}
