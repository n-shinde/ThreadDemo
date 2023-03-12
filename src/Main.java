
public class Main {

    private static void wait(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            System.out.println("Killing thread");
        }
    }


    private static void kill_thread(Thread thread) {
        try {
            thread.interrupt();
        } catch (Throwable e) {
            System.out.println("Killing thread");
        }
    }

    private static void wait_for_thread_to_finish(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Player drums = new Player("rock_drums.wav");
        Player trumpets = new Player("incredibles-trumpet.wav");
        // Player guitarRiff = new Player("slow.wav");
        Player jaz_piano = new Player("latin-jaz-piano.wav");

        // play not in a thread
        System.out.println("Playing drums not in a thread");
        drums.play();
        System.out.println("Playing trumpet not in a thread");
        trumpets.play();

        // outer sound loop
        for (int i = 0; i < 10; i++) {
            Thread drumsThread = new Thread(drums);
            Thread trumpetThread = new Thread(trumpets);
            Thread pianoThread = new Thread(jaz_piano);

            System.out.println("Starting Drum Thread");
            drumsThread.start();


            // wait before bringing in guitar riff
            wait(3);

            System.out.println("Starting Piano Thread");
            pianoThread.start();

            // wait before bringing in trumpets
            wait(5);
            System.out.println("Playing Trumpet thread");
            trumpetThread.start();
            // let trumpets play for 3 seconds
            wait(3);
            System.out.println("Killing Trumpet thread");
            kill_thread(trumpetThread);


            wait_for_thread_to_finish(pianoThread);
        }


    }

}
