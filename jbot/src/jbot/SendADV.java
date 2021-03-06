/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jbot;

import GeoUtilis.Coordinate;
import GeoUtilis.Distance;
import TelegramAPI.Telegram;

/**
 *
 * @author Davide
 */
public class SendADV extends Thread {

    private Coordinate coordinate;
    private String message;
    private int raggio;

    public SendADV(Coordinate coordinate, String message, int raggio) {
        this.coordinate = coordinate;
        this.message = message;
        this.raggio = raggio;
    }

    @Override
    public void run() {
        UserList userList = UserList.GetInstance();
        Telegram t = Telegram.Init("");
        for (int i = 0; i < userList.GetSize(); i++) {
            User u = userList.GetUserAt(i);
            if (Distance.CalculateDistanceInMeters(u.getCoordinate(), coordinate) < raggio) {
                synchronized (SendADV.class) {
                    t.SendMessage(u.getChat_id(), "<b>Ciao " + u.getName() + ", è presente la seguente offerta vicino a te:</b>\n" + message);
                    t.SendLocation(u.getChat_id(), coordinate.getLatitude(), coordinate.getLongitude());
                }
            }
        }

    }

}
