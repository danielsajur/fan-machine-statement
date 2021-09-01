package com.fanmachine.app;

import com.fanmachine.domain.FanMachine;

public class Application {

  public static void main(String[] args) {
    usages();
    var fan = new FanMachine();
    fan.run();
  }

  private static void usages() {
    System.out.println("FAN MACHINE: Choose one option below anytime:");
    System.out.println("0 - Exit");
    System.out.println("1 - To increase speed fan");
    System.out.println("2 - To change fan direction");
  }
}
