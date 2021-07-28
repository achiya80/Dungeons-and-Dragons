package PresentationLayer.GameUI;

import BusinessLayer.ActionHandler.Movement;
import BusinessLayer.Board.Board;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Players.Player;
import BusinessLayer.Tiles.Tile;
import PresentationLayer.FileHandler.TileFactory;

import javax.swing.*;
import javax.swing.plaf.multi.MultiTextUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class GameUILevel {

    private JTextArea playerInfo;
    private JTextArea battleInfo;
    private Label[][] btnArr;
    private static JFrame frame;
    private JButton left = new JButton("<-");
    private JButton right = new JButton("->");
    private JButton up = new JButton("/|\\");
    private JButton down = new JButton("\\|/");
    private JButton stay = new JButton("o");
    private JButton ability = new JButton("A");
    private ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JButton) {
                battleInfo.setText("");
                String push = ((JButton)e.getSource()).getText();
                player.performAction(movesMap.get(push), enemies);
                for(Enemy en : enemies) en.performAction(player, enemies);
                playerInfo.setText(Arrays.stream(player.describe().split("\\s+")).map(t -> t+" ").collect(Collectors.joining()));
                if(gameEnded() || levelEnded()){
                    frame.remove(left);frame.remove(right);frame.remove(up);frame.remove(down);frame.remove(ability);frame.remove(stay);
                    Arrays.stream(btnArr).forEach(i -> Arrays.stream(i).forEach(j -> frame.remove(j)));
                    frame.remove(battleInfo);frame.remove(playerInfo);
                }
                updateBoard();
            }
        }
    };
    private static final Map<String, Character> movesMap = new HashMap<>(){
        {
            put("->", 'd');put("<-", 'a');put("/|\\", 'w');put("\\|/",'s');put("o", 'q');put("A", 'e');
        }
    };

    public void messageBox(String message,String title){
        JOptionPane.showMessageDialog(null, message,title,1);
    }
    private static Scanner reader = new Scanner(System.in);
    private Board gameBoard;
    private Player player;
    private List<Enemy> enemies = new ArrayList<>();

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void init(){
        if (frame == null){
            frame = new JFrame();
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    if (JOptionPane.showConfirmDialog(frame,
                            "Are you sure you want to end the game?", "End Game?",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){

                        System.exit(0);
                    }
                }
            });
        }
        Tile[][]  arr = gameBoard.toTileArray();
        frame.setSize(arr.length*width + arr.length*addedWidth,arr[0].length*height + arr[0].length*addedHeight);//400 width and 500 height
        updateBoard();
        playerInfo = new JTextArea(Arrays.stream(player.describe().split("\\s+")).map(t -> t+" ").collect(Collectors.joining()));
        playerInfo.setEnabled(false);
        playerInfo.setBounds(arr.length*width + 2*addedWidth,height + addedHeight-20, 500, 100);
        battleInfo = new JTextArea();
        battleInfo.setBounds(arr.length*width + 2*addedWidth,arr[0].length*height-80, 500, 400);
        battleInfo.setEnabled(false);
        left.addActionListener(listener);right.addActionListener(listener);up.addActionListener(listener);
        down.addActionListener(listener);ability.addActionListener(listener);stay.addActionListener(listener);
        int widthe = 50;
        int heighte = 50;
        left.setBounds(arr.length*width + addedWidth*2,height + addedHeight+150, widthe, heighte);
        up.setBounds(arr.length*width + 50 + addedWidth*2,height + addedHeight+100, widthe, heighte);
        stay.setBounds(arr.length*width + 50 +  addedWidth*2,height + addedHeight+150, widthe, heighte);
        right.setBounds(arr.length*width +100 + addedWidth*2,height + addedHeight+150, widthe, heighte);
        down.setBounds(arr.length*width+ 50+ addedWidth*2,height + addedHeight+200, widthe, heighte);
        ability.setBounds(arr.length*width + addedWidth*2 + 150,height + addedHeight+100, widthe, heighte);
        frame.add(left); frame.add(right); frame.add(down); frame.add(up); frame.add(ability); frame.add(stay);
        frame.add(battleInfo);
        frame.add(playerInfo);
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    private void setBattleInfo(String msg){
        battleInfo.setText(battleInfo.getText() + " " + msg);
    }

    private static final int addedWidth = 30;
    private static final int addedHeight = 20;
    public void startLevel(){
        while(!gameEnded() && !levelEnded()){
            updateBoard();
            if(!gameEnded()) {
                String s = reader.nextLine();
                while (!Movement.getMoves().contains(s)) {
                    s = reader.nextLine();
                }
                char c = s.charAt(0);
                player.performAction(c, enemies);
                for(Enemy e : enemies) e.performAction(player, enemies);
            }
        }

    }

    public void addEnemy(Enemy e){
        enemies.add(e);
    }

    public void onPlayerDeath(){
        playerInfo.setText(Arrays.stream(player.describe().split("\\s+")).map(t -> t+" ").collect(Collectors.joining()));
        messageBox("you lost", "Game Over");
    }

    public void onEnemyDeath(Enemy e){
        enemies.remove(e);
        gameBoard.removeTile(e);
        gameBoard.addTile(new TileFactory().produceEmpty(e.getPosition()));
    }

    public boolean levelEnded(){
        return enemies.isEmpty();
    }


    public boolean gameEnded(){
        return !player.alive();
    }

    @Override
    public String toString() {
        return String.format("%s\n%s\n", gameBoard, player.describe());
    }

    public void getMessage(String msg){
        setBattleInfo(msg);
    }

    private static final int width = 20;
    private static final int height = 20;
    public void updateBoard(){
        Tile[][] tiles = gameBoard.toTileArray();
        btnArr = (btnArr != null) ? btnArr : new Label[tiles.length][tiles[0].length];
        for (int row=0;row<btnArr.length;row++){
            for (int col=0;col<btnArr[0].length;col++){
                int finalRow = row;
                int finalCol = col;
                btnArr[row][col] = (btnArr[row][col] != null) ? btnArr[row][col] : new Label(String.valueOf(tiles[finalRow][finalCol].getTile())) {
                    {
                        setBounds(finalRow * width + finalRow, finalCol * height + finalCol, width, height);
                        setFont(new Font("Arial", Font.PLAIN, 15));
                        frame.add(this);
                    }
                };
                btnArr[row][col].setText(tiles[row][col].toString());
            }
        }
    }

}
