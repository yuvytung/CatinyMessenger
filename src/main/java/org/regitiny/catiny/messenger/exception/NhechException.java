package org.regitiny.catiny.messenger.exception;

public class NhechException extends RuntimeException
{
  public NhechException(String message)
  {
    super(message);
  }

  public NhechException()
  {
    super("Lỗi Logic : Nhếch xuất hiện ở đây để chửi bạn thay tôi .");
  }
}
