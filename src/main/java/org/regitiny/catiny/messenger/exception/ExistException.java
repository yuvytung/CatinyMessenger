package org.regitiny.catiny.messenger.exception;

public class ExistException extends RuntimeException
{
  public ExistException(String message)
  {
    super(message);
  }
  public ExistException()
  {
    super("This object already exists");
  }
}
