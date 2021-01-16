public class AlarmThread extends Thread {
    private final Object lock = new Object();
    private boolean pause = false;
    private int maxCount;
    private UserGUI gui;

    public AlarmThread(int maxCount) {
        this.maxCount = maxCount;
    }

    public AlarmThread(int maxCount, UserGUI gui) {
        this.maxCount = maxCount;
        this.gui = gui;
    }
    public void setGUI(UserGUI gui) {
        this.gui = gui;
    }

    public int getMaxCount() {
        return maxCount;
    }

    private void myPause() {
        synchronized (lock) {
            try {
                System.out.println("waiting~");
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void onPause() {
        pause = true;
    }

    public void onResume() {
        pause = false;
        synchronized (lock) {
            lock.notify();
        }
    }

    @Override
    public void run() {
        while (true) {
            if (pause) {
                myPause();
            }
            System.out.println("current count = " + maxCount);
            gui.setMsgValue(String.valueOf(maxCount));
            maxCount--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (maxCount == 0){
                this.interrupt();
                gui.setMsgValue("count end~");
                return;
            }
        }
    }
}
