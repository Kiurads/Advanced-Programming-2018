
package model.data.files;

import java.io.*;

/** 
 * @author Jose Marinho
 *
 */

public class FileUtility
{
    public static final String FILE_TO_SAVE_GAME = "savedgame.bin";

    public static Object LoadGameGUI(File ficheiro) throws IOException, ClassNotFoundException{
        ObjectInputStream in = null;

        try{
            in = new ObjectInputStream(new FileInputStream(ficheiro));
            return in.readObject();
        }finally{
            if(in!=null){
                in.close();
            }
        }
    }
    public static void SaveGameGUI(File file,Object o)throws IOException{
        ObjectOutputStream out = null;

        try{
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(o);
        }finally{
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {}
            }
        }
    }
    
}
