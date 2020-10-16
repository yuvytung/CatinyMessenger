package org.regitiny.catiny.messenger.exception;

public class NotExistException extends NullPointerException
{
  public NotExistException()
  {
    super("This object not exist");
  }
  public NotExistException(String message)
  {
    super(message);
  }
}
