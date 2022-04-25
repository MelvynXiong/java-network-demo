package main.java.top.melvyn.learn;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.ZoneId;

interface WorldClock extends Remote {
  LocalDateTime getLocalDateTime(String zoneId) throws RemoteException;
}

public class WorldClockService implements WorldClock {
  @Override
  public LocalDateTime getLocalDateTime(String zoneId) throws RemoteException {
    return LocalDateTime.now(ZoneId.of(zoneId)).withNano(0);
  }
}