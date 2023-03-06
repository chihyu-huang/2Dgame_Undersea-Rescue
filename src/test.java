public class test {

//    Thread gameThread;
//
//    public void startGameThread() {
//        gameThread = new Thread(this);
//        gameThread.start();
//    }
//
//    public void run() {
//
//        double drawInterval = 1000 / TargetFPS;
//        double nextDrawTime = System.currentTimeMillis() + drawInterval;
//
//
//        while(gameThread != null){
////			1 update info
////			2 draw with the updated info
//            update();
//            repaint();
//
//            try{
//                double remainingTime = nextDrawTime - System.currentTimeMillis();
//
//                if(remainingTime < 0){
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//
//
//
//        }
//    }
}
