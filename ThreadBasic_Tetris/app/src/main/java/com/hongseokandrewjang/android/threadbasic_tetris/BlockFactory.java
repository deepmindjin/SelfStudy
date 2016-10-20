package com.hongseokandrewjang.android.threadbasic_tetris;

import android.os.Handler;

import java.util.Random;

/**
 * Created by HongSeokAndrewJang on 2016-10-18.
 */

public class BlockFactory {
    Handler handler;
    BlockFactory(Handler handler){
        this.handler = handler;
    }

    // Return random Block
    public Block getRandomBlock(){
        Block randomBlock;
        // Type of newly made block is randomly set
        Random random = new Random();
        int type = random.nextInt(7);
        randomBlock = new Block(type,handler);
        randomBlock.setDaemon(true);
        return randomBlock;
    }
}
