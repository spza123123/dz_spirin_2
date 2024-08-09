package server.server;

import server.client.ClientGUI;

import java.awt.event.ActionListener;
import java.util.List;

public  class ServerController {
    private boolean work;
    ServerController serverController;
    ServerFileReader fileReader;
    ServerFileReader reader;
    List<ClientGUI> clientGUIList;
    private ClientGUI clientGUI;

    public boolean connectUsr(ClientGUI clientGUI){
        if (!work){
            return false;
        }
        clientGUIList.add(clientGUI);
        return true;
    }

    public String getLog() {
        return reader.readLog();
    }

    public void disconnectUsr(ClientGUI clientGUI){
        clientGUIList.remove(clientGUI);
        if (clientGUI != null){
            clientGUI.disconnectedFromServer();
        }
    }
    public boolean connectUser(ClientGUI clientGUI){
        if (!work){
            return false;
        }
        clientGUIList.add(clientGUI);
        return true;
    }
    public void answerAll(String text){
        for (ClientGUI clientGUI: clientGUIList){
            clientGUI.answer(text);
        }
    }
    public void disconnectUser(ClientGUI clientGUI){
        this.clientGUI = clientGUI;
        clientGUIList.remove(clientGUI);
        if (clientGUI != null){
            clientGUI.disconnectedFromServer();
        }
    }

    public void message(String text){
        if (!work){
            return;
        }

        fileReader.appendLog(text);

        serverController.answerAll(text);
        fileReader.saveInLog(text);
    }

}
