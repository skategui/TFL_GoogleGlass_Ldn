package dockers;
import java.util.TimerTask;

import containers.userInfos;

/**
 * Created by guillaume on 28/09/2014.
 */
public class TFLCron  extends TimerTask {


    private dockersManagement dockers;
    private TFLXmlReader reader;
    private userInfos user;

    public TFLCron(dockersManagement dockersM, TFLXmlReader reader, userInfos user) {
        this.dockers = dockersM;
        this.reader = reader;
        this.user = user;
    }

    @Override
    public void run() {
        System.out.println("Hi see you after 60 seconds");
        dockers.setDockers(reader.updateDocks());
       int current =  dockers.updateDockers(user);
        System.out.println("current : " + current);
    }

}


// get nearest docks next to B
//  get pos user
// determine top 5 docks

// cron :
// get xml
// convert at objects list
// get pos user
// arrived ? no/yes
// update number 1
// if 0 ? get new number 1
