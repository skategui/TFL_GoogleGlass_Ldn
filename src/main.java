import java.util.Timer;

/**
 * Created by guillaume on 28/09/2014.
 */
public class main {


    public static void main(String[] args){

        Timer t = new Timer();
        dockersManagement dockManagement = new dockersManagement();
        TFLXmlReader reader = new TFLXmlReader();
        userInfos user = new userInfos();

        // set  pos of the place where he wants to go, should be dynamic
        user.setCurrentPos(51.528, -0.1099);



        TFLCron cron = new TFLCron(dockManagement, reader, user);

         //This task is scheduled to run every 60 seconds
         t.scheduleAtFixedRate(cron, 0, 60000);


    }

}
